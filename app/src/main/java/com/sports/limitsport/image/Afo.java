package com.sports.limitsport.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;


/**
 * Created by jingyouliu on 16/8/16.
 * 图片加载实现类
 */
public class Afo implements ImageButler {

    @Override
    public void fromNet(Context context, String url, final BatmanCallBack callBack) {
        SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            }
        };

        Glide.with(context).load(url).asBitmap().listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                callBack.onFailure(e);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                callBack.onSuccess(resource);
                return false;
            }
        }).into(simpleTarget);
    }

    @Override
    public void loadUri(Context context, Uri uri, ImageView imageView) {
        Glide.with(imageView.getContext()).load(uri).centerCrop().into(imageView);
    }


    @Override
    public void fromNet(Context context, String url, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight) {

        go(context, url, imageView, defaultImage, failedImage, maxWidth, maxHeight);


    }

    @Override
    public void fromLocal(Context context, String filePath, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
//        File file = new File(filePath);
//        if (file != null && file.exists()) {
//            if (maxHeight == 0 || maxHeight == 0) {
//                Glide.with(imageView.getContext()).load(filePath).fitCenter().placeholder(defaultImage).error(failedImage).into(imageView);
//            } else {
//                Glide.with(imageView.getContext()).load(filePath).fitCenter().override(maxWidth, maxHeight).placeholder(defaultImage).error(failedImage).into(imageView);
//
//            }
//        }

        go(context, filePath, imageView, defaultImage, failedImage, maxWidth, maxHeight);

    }

    @Override
    public void fromAsset(Context context, String fileName, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
        go(context, fileName, imageView, defaultImage, failedImage, maxWidth, maxHeight);
    }

    @Override
    public void fromNetWithFitCenter(Context context, String url, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
        goWithFitCenter(context, url, imageView, defaultImage, failedImage, maxWidth, maxHeight);
    }

    @Override
    public Bitmap getBitMap(Context context, String url) {
        try {
            return Glide.with(context).load(url).asBitmap().into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void fromNetWithTransfromat(Context context, String url, ImageView imageView, Transformation bitmapTransformation, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
        goWithBitmapTransform(context, url, imageView, bitmapTransformation, defaultImage, failedImage, maxWidth, maxHeight);

    }


    private void go(Context context, String fileName, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
        if (maxHeight == 0 || maxHeight == 0) {
            Glide.with(imageView.getContext()).load(fileName).centerCrop().placeholder(defaultImage).error(failedImage).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(fileName).centerCrop().override(maxWidth, maxHeight).placeholder(defaultImage).error(failedImage).into(imageView);

        }
    }

    private void goWithFitCenter(Context context, String fileName, ImageView imageView, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
        if (maxHeight == 0 || maxHeight == 0) {
            Glide.with(imageView.getContext()).load(fileName).fitCenter().placeholder(defaultImage).error(failedImage).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(fileName).fitCenter().override(maxWidth, maxHeight).placeholder(defaultImage).error(failedImage).into(imageView);

        }
    }

    private void goWithBitmapTransform(Context context, String fileName, ImageView imageView, Transformation bitmapTransformation, int defaultImage, int failedImage, int maxWidth, int maxHeight) {
        if (maxHeight == 0 || maxHeight == 0) {
            Glide.with(imageView.getContext()).load(fileName).centerCrop().bitmapTransform(bitmapTransformation).placeholder(defaultImage).error(failedImage).into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(fileName).bitmapTransform(bitmapTransformation).override(maxWidth, maxHeight).placeholder(defaultImage).error(failedImage).into(imageView);

        }
    }
}
