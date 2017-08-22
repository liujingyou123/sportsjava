package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.sports.limitsport.R;
import com.sports.limitsport.main.MainActivity;
import com.sports.limitsport.util.GlideImageLoader;
import com.sports.limitsport.util.MyTestData;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/7/10.
 */

public class NewNewsHeadView extends LinearLayout {
    @BindView(R.id.banner)
    Banner banner;
    private List<String> advs = new ArrayList<>();


    public NewNewsHeadView(Context context) {
        super(context);
        init();
    }

    public NewNewsHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewNewsHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_newnews, this);
        ButterKnife.bind(this, this);
//        showBanner();
    }

    private void showBanner() {
        GlideImageLoader glideImageLoader;
        if (getContext() instanceof MainActivity) {
            glideImageLoader = ((MainActivity) getContext()).getGlideImageLoader();
        } else {
            glideImageLoader = new GlideImageLoader();
        }
        //设置图片加载器
        banner.setImageLoader(glideImageLoader);
        //设置图片集合
        banner.setImages(advs);
        //设置标题集合（当banner样式有显示title时）
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public void setImagesList(List<String> images) {
        if (advs != null) {
            advs.clear();
            advs.addAll(images);
        }
        showBanner();
    }

}
