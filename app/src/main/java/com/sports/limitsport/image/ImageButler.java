package com.sports.limitsport.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.Transformation;

/**
 * Created by jingyouliu on 16/4/18.
 * 图片加载接口(管家)
 */
public interface ImageButler {


    /**
     * 加载网络图片
     *
     * @param url      图片地址URL
     * @param callBack 返回接口
     */
    void fromNet(Context context, String url, BatmanCallBack callBack);

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
    void fromNet(Context context, String url, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight);

    /**
     * 加载本地图片
     *
     * @param filePath     本地图片路径
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  下载失败图片
     * @param maxWidth     图片的最大宽度 0：原图大小
     * @param maxHeight    图片的最大高度 0：原图大小
     */
    void fromLocal(Context context, String filePath, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight);

    /**
     * 加载Asset图片
     *
     * @param fileName     图片名称
     * @param imageView    显示图片的ImageView
     * @param defaultImage 默认图片
     * @param failedImage  下载失败图片
     * @param maxWidth     图片的最大宽度  0：原图大小
     * @param maxHeight    图片的最大高度  0：原图大小
     */
    void fromAsset(Context context, String fileName, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight);


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
    void fromNetWithFitCenter(Context context, String url, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight);


    /**
     * 同步获取bitmap
     *
     * @param context
     * @param url
     * @return
     */
    Bitmap getBitMap(Context context, String url);

    /**
     * 获取transformation
     * @param context
     * @param url
     * @param imageView
     * @param bitmapTransformation
     * @param defaultImage
     * @param failedImage
     * @param maxWidth
     * @param maxHeight
     */
    void fromNetWithTransfromat(Context context, String url, ImageView imageView, Transformation bitmapTransformation, int defaultImage, int failedImage, int maxWidth, int maxHeight);

}


