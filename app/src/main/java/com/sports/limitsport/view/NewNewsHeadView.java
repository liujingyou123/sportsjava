package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.sports.limitsport.R;
import com.sports.limitsport.util.GlideImageLoader;
import com.sports.limitsport.util.MyTestData;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/7/10.
 */

public class NewNewsHeadView extends LinearLayout {
    @BindView(R.id.banner)
    Banner banner;

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
        showBanner();
    }

    private void showBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(MyTestData.getData());
        //设置标题集合（当banner样式有显示title时）
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

}
