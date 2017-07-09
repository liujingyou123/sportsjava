package com.sports.limitsport.util;

import android.content.Context;
import android.widget.ImageView;

import com.sports.limitsport.image.Batman;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Batman.getInstance().fromNet((String) path, imageView);
    }
}
