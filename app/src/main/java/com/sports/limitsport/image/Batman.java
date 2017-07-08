package com.sports.limitsport.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by jingyouliu on 16/8/16.
 * 图片加载工具
 */
public final class Batman {

    private Context mContext;
    private static Batman INSTANCE = new Batman();
    private ImageButler mButler;

    private Batman() {
        mButler = new Afo();
    }

    public static Batman getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        if (mContext != null) {
            throw new IllegalStateException("Batman has already initialized , Don't init more than once");
        }
        this.mContext = context.getApplicationContext();
    }

    /**
     * 加载网络图片缩略图图
     *
     * @param url       图片地址URL
     * @param imageView 显示图片的ImageView
     */
    public void loadThumbnail(String url, ImageView imageView) {
        mButler.fromNet(mContext, url, imageView, -1, -1, 0, 0);
    }

    /**
     * 加载网络图片
     *
     * @param url          图片地址URL
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  失败图片
     */
    public void loadThumbnail(String url, ImageView imageView, int defaultImage, int failedImage) {
        mButler.fromNet(mContext, url, imageView, defaultImage, failedImage, 0, 0);
    }


    /**
     * 加载网络图片
     *
     * @param url      图片地址URL
     * @param callBack 返回接口
     */
    public void fromNet(String url, BatmanCallBack callBack) {
        mButler.fromNet(mContext, url, callBack);
    }

    /**
     * 加载网络图片原图
     *
     * @param url       图片地址URL
     * @param imageView 显示图片的ImageView
     */
    public void fromNet(String url, ImageView imageView) {
        mButler.fromNet(mContext, url, imageView, -1, -1, 0, 0);
    }

    /**
     * 加载网络图片
     *
     * @param url          图片地址URL
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  失败图片
     */
    public void fromNet(String url, ImageView imageView, int defaultImage, int failedImage) {
        mButler.fromNet(mContext, url, imageView, defaultImage, failedImage, 0, 0);
    }

    /**
     * 加载网络图片
     *
     * @param url          图片地址URL
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  下载失败图片
     * @param maxWidth     图片的最大宽度 0：原图大小
     * @param maxHeight    图片的最大高度 0：原图大小
     */
    public void fromNet(String url, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
        mButler.fromNet(mContext, url, imageView, defaultImage, failedImage, maxWidth, maxHeight);
    }

    /**
     * 加载本地图片
     *
     * @param filePath     本地图片路径 "file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  下载失败图片
     * @param maxWidth     图片的最大宽度 0：原图大小
     * @param maxHeight    图片的最大高度 0：原图大小
     */
    public void fromLocal(String filePath, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
        if (!TextUtils.isEmpty(filePath)) {
            filePath = "file://" + filePath;
        }
        mButler.fromLocal(mContext, filePath, imageView, defaultImage, failedImage, maxWidth, maxHeight);
    }

    /**
     * 加载本地图片
     *
     * @param filePath  本地图片路径 "file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"
     * @param imageView 显示图片的ImageView
     */
    public void fromLocal(String filePath, ImageView imageView) {
        if (!TextUtils.isEmpty(filePath)) {
            filePath = "file://" + filePath;
        }
        mButler.fromLocal(mContext, filePath, imageView, -1, -1, 0, 0);
    }

    /**
     * 加载Asset图片
     *
     * @param fileName     图片名称 "f003.gif"
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  下载失败图片
     * @param maxWidth     图片的最大宽度  0：原图大小
     * @param maxHeight    图片的最大高度  0：原图大小
     */
    public void fromAsset(String fileName, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
        if (!TextUtils.isEmpty(fileName)) {
            fileName = "file:///android_asset/" + fileName;
        }
        mButler.fromAsset(mContext, fileName, imageView, defaultImage, failedImage, maxWidth, maxHeight);
    }

    /**
     * 加载Asset图片
     *
     * @param fileName  图片名称 "f003.gif"
     * @param imageView 显示图片的ImageView
     */
    public void fromAsset(String fileName, ImageView imageView) {
        if (!TextUtils.isEmpty(fileName)) {
            fileName = "file:///android_asset/" + fileName;
        }
        mButler.fromAsset(mContext, fileName, imageView, -1, -1, 0, 0);
    }

    /**
     * 加载网络图片原图
     *
     * @param url       图片地址URL
     * @param imageView 显示图片的ImageView
     */
    public void fromNetWithFitCenter(String url, ImageView imageView) {
        mButler.fromNetWithFitCenter(mContext, url, imageView, -1, -1, 0, 0);
    }

    public void fromNetWithFitCenter(String url, ImageView imageView, int defaultImage, int failedImage) {
        mButler.fromNetWithFitCenter(mContext, url, imageView, defaultImage, failedImage, 0, 0);
    }

    /**
     * 同步获取bitmap
     *
     * @param context
     * @param url
     * @return
     */
    public Bitmap getBitMap(Context context, String url) {
        return mButler.getBitMap(context, url);
    }

    /**
     * 加载圆角网络图片
     *
     * @param url          图片地址URL
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  失败图片
     */
    public void getImageWithCircle(String url, ImageView imageView, int defaultImage, int failedImage) {
        mButler.fromNetWithTransfromat(mContext, url, imageView, new CropCircleTransformation(mContext), defaultImage, failedImage, 0, 0);
    }

    /**
     * 加载圆角网络图片
     *
     * @param url          图片地址URL
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  失败图片
     */
    public void getImageWithRoundBottom(String url, ImageView imageView, int defaultImage, int failedImage, int radius) {
        mButler.fromNetWithTransfromat(mContext, url, imageView, new RoundedCornersTransformation(mContext, 30, 0,
                RoundedCornersTransformation.CornerType.BOTTOM), defaultImage, failedImage, 0, 0);
    }

    /**
     * 加载圆角网络图片
     *
     * @param url          图片地址URL
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  失败图片
     */
    public void getImageWithRoundLeft(String url, ImageView imageView, int defaultImage, int failedImage, int radius) {
        mButler.fromNetWithTransfromat(mContext, url, imageView, new RoundedCornersTransformation(mContext, 30, 0,
                RoundedCornersTransformation.CornerType.LEFT), defaultImage, failedImage, 0, 0);
    }
}
