package com.sports.limitsport.view.imagepreview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.image.Batman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 16/11/1.
 */
public class ImagePreViewAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mData = new ArrayList<>();

    public ImagePreViewAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ZoomImageView imageView = new ZoomImageView(mContext.getApplicationContext());
        Batman.getInstance().fromNetWithFitCenter(mData.get(position), imageView);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
