package com.sports.limitsport.jpush;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.ActivityDetailActivity;
import com.sports.limitsport.activity.DongTaiDetailActivity;
import com.sports.limitsport.base.LimitSportApplication;
import com.sports.limitsport.discovery.FineShowDetailActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.MyFansListActivity;
import com.sports.limitsport.mine.OrderDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            XLog.e("[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                XLog.e(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                XLog.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                processCustomMessage(context, bundle);

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                XLog.e(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                XLog.e(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                XLog.e(TAG, "[MyReceiver] 用户点击打开了通知");
//
//				//打开自定义的Activity
//				Intent i = new Intent(context, TestActivity.class);
//				i.putExtras(bundle);
//				//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//				context.startActivity(i);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                XLog.e(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                XLog.e(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                XLog.e(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }

    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    XLog.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    XLog.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        XLog.e("[MyReceiver] message = " + message);
        XLog.e("[MyReceiver] extras = " + extras);

        Intent intent = null;
        String from = null;
//        if (!isRunningForeground(context)) {
        from = "outer";
//        }
//        if (LimitSportApplication.getInstance() == null) {

//        }
        ExtraReceive extraReceive = processBundleExtra(bundle);
        if (extraReceive != null) {

            if ("0".equals(extraReceive.getCatalogType())) { //活动
                if ("0".equals(extraReceive.getBizType()) || "3".equals(extraReceive.getBizType())) { //活动上线
                    intent = new Intent(context, ActivityDetailActivity.class);
                    if (!TextUtils.isEmpty(from)) {
                        intent.putExtra("from", from);
                    }
                    intent.putExtra("fromType", "receiver");
                    intent.putExtra("id", extraReceive.getBizId());
                } else if ("5".equals(extraReceive.getBizType()) || "4".equals(extraReceive.getBizType()) || "6".equals(extraReceive.getBizType())) { //活动异常
                    intent = new Intent(context, OrderDetailActivity.class);
                    if (!TextUtils.isEmpty(from)) {
                        intent.putExtra("from", from);
                    }
                    intent.putExtra("fromType", "receiver");
                    intent.putExtra("orderNo", extraReceive.getBizId());
                }
            } else if ("2".equals(extraReceive.getCatalogType())) { //工作 评论
                if ("0".equals(extraReceive.getBizType()) || "1".equals(extraReceive.getBizType())
                        || "2".equals(extraReceive.getBizType())
                        || "4".equals(extraReceive.getBizType())
                        || "5".equals(extraReceive.getBizType())) { //动态详情页

                    intent = new Intent(context, DongTaiDetailActivity.class);
                    if (!TextUtils.isEmpty(from)) {
                        intent.putExtra("from", from);
                    }
                    intent.putExtra("fromType", "receiver");
                    intent.putExtra("id", extraReceive.getBizId());
                } else if ("3".equals(extraReceive.getBizType())) { //被@ 我的粉丝页

                    intent = new Intent(context, MyFansListActivity.class);
                    if (!TextUtils.isEmpty(from)) {
                        intent.putExtra("from", from);
                    }
                    intent.putExtra("fromType", "receiver");
                } else if ("6".equals(extraReceive.getBizType()) || "8".equals(extraReceive.getBizType())) { //精彩秀详情页
                    intent = new Intent(context, FineShowDetailActivity.class);
                    if (!TextUtils.isEmpty(from)) {
                        intent.putExtra("from", from);
                    }
                    intent.putExtra("fromType", "receiver");
                    intent.putExtra("id", extraReceive.getBizId());

                } else if ("9".equals(extraReceive.getBizType())) { //活动详情页  活动评论被回复
                    intent = new Intent(context, ActivityDetailActivity.class);
                    if (!TextUtils.isEmpty(from)) {
                        intent.putExtra("from", from);
                    }
                    intent.putExtra("fromType", "receiver");
                    intent.putExtra("id", extraReceive.getBizId());
                }
            }
        }

        try {
            Bitmap LargeBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            Notification.Builder myBuilder = new Notification.Builder(context);
            myBuilder.setContentTitle("极限领秀")
                    .setContentText(bundle.getString(JPushInterface.EXTRA_MESSAGE) + "")
                    .setTicker(bundle.getString(JPushInterface.EXTRA_MESSAGE))
                    .setSmallIcon(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ? R.mipmap.ic_launcher : R.mipmap.ic_launcher)
                    .setLargeIcon(LargeBitmap)
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                    .setAutoCancel(true)//点击后取消
                    .setWhen(System.currentTimeMillis())//设置通知时间
                    .setPriority(Notification.PRIORITY_HIGH);//高优先级
//                .setVisibility(Notification.VISIBILITY_PUBLIC)
            //android5.0加入了一种新的模式Notification的显示等级，共有三种：
            //VISIBILITY_PUBLIC  只有在没有锁屏时会显示通知
            //VISIBILITY_PRIVATE 任何情况都会显示通知
            //VISIBILITY_SECRET  在安全锁和没有锁屏的情况下显示通知
//                    .setContentIntent(pendingIntent);  //3.关联PendingInte

            if (intent != null) {
                PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
                myBuilder.setContentIntent(pendingIntent);
            }

            Notification myNotification = myBuilder.build();
            NotificationManager myManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

            myManager.notify((int) System.currentTimeMillis(), myNotification);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private ExtraReceive processBundleExtra(Bundle bundle) {
        ExtraReceive extraReceive = null;
        String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        if (!TextUtils.isEmpty(extra)) {
            Gson gson = new Gson();
            extraReceive = gson.fromJson(extra, ExtraReceive.class);
        }

        return extraReceive;
    }

    private boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (currentPackageName != null && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }
}
