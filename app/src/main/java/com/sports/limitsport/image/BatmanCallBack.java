package com.sports.limitsport.image;

import android.graphics.Bitmap;

/**
 * Created by jingyouliu on 16/8/16.
 * 图片返回接口
 */
public interface BatmanCallBack {
    void onSuccess(Bitmap bitmap);
    void onFailure(Exception error);
}
