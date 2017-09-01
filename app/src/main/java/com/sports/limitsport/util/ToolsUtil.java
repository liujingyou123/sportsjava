package com.sports.limitsport.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.net.Ironman;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoTimeOutSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuworkmac on 17/1/3.
 */
public class ToolsUtil {

    public static String toJsonString(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        XLog.json(json);
        return json;
    }

    public static String toJsonStringWithIgnore(Object object) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new FooAnnotationExclusionStrategy()).create();
        String json = gson.toJson(object);
        XLog.json(json);
        return json;
    }

    public static <T> List<T> jsonToList(String json, Class<T[]> clazz) {
        Gson gson = new Gson();
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }

    /**
     * @param json
     * @param clazz
     * @return
     */
    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }


    public static <T> Observable.Transformer<T, T> applayScheduers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.retryWhen(new RetryWithDelay()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> T createService(Class<T> serviceClazz) {
        return Ironman.getInstance().createService(serviceClazz);
    }

    public static <T> Subscription subscribe(Observable<T> observable, NetSubscriber<T> subscriber) {
        return observable.compose(ToolsUtil.<T>applayScheduers()).subscribe(subscriber);
    }

    public static <T> Subscription subscribe(Observable<T> observable, NoTimeOutSubscriber<T> subscriber) {
        return observable.compose(ToolsUtil.<T>applayScheduers()).subscribe(subscriber);
    }

    public static <T> Subscription subscribe(Observable<T> observable, NoneNetSubscriber<T> subscriber) {
        return observable.compose(ToolsUtil.<T>applayScheduers()).subscribe(subscriber);
    }

    public static Date stringToDate(String timeStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = df.parse(timeStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }

    public static String dateToGMTStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
        format.setCalendar(cal);
        return format.format(date);
    }

    public static Bitmap getVideoThumbnail(String videoPath) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(videoPath);
        Bitmap bitmap = media.getFrameAtTime();
        return bitmap;
    }

    public static String saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "sportlimit");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getPath();
//        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
    }

}
