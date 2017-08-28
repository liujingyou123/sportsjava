package com.sports.limitsport.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.sports.limitsport.R;
import com.sports.limitsport.main.adapter.LaunchPageAdapter;
import com.sports.limitsport.model.EventBusAd;
import com.sports.limitsport.util.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 16/9/27.
 * 滚轮启动页
 */
public class AdActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.llPoint)
    LinearLayout llPoint;
    private LaunchPageAdapter adapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(R.layout.activity_ad);
        ButterKnife.bind(this);
        InitViewPager();
        addPoint();
    }

    protected void setStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /*
     * 初始化ViewPager
	 */
    public void InitViewPager() {
        if (fragmentList == null) {
            fragmentList = new ArrayList<Fragment>();
        }
        fragmentList.clear();
        Fragment firstFragment = AdFragment.newInstance(1);
        Fragment secondFragment = AdFragment.newInstance(2);
        Fragment thirdFragment = AdFragment.newInstance(3);
        Fragment fourthFragment = AdFragment.newInstance(4);
        Fragment fiveFragment = AdFragment.newInstance(5);
        Fragment sixFragment = AdFragment.newInstance(6);

        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);
        fragmentList.add(fourthFragment);
        fragmentList.add(fiveFragment);
        fragmentList.add(sixFragment);

        if (adapter == null) {
            adapter = new LaunchPageAdapter(getSupportFragmentManager(), fragmentList);
        }
        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);//设置当前显示标签页为第一页
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                monitorPoint(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.e("AdActivity", "onDestroy");
        if (adapter != null) {
            adapter = null;
        }
        super.onDestroy();

    }

    /**
     * 添加小圆点
     */
    private void addPoint() {
        // 1.根据图片多少，添加多少小圆点
        for (int i = 0; i < 6; i++) {
            LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i < 1) {
                pointParams.setMargins(0, 0, 0, 0);
            } else {
                pointParams.setMargins(20, 0, 0, 0);
            }
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(pointParams);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setBackgroundResource(R.drawable.bg_dot_circle_white);
            llPoint.addView(iv);
        }
        llPoint.getChildAt(0).setBackgroundResource(R.drawable.bg_dot_circle_black);

    }

    /**
     * 判断小圆点
     *
     * @param position
     */
    private void monitorPoint(int position) {
        if (position == 5) {
            llPoint.setVisibility(View.GONE);
            EventBus.getDefault().post(new EventBusAd());
        } else {
            llPoint.setVisibility(View.VISIBLE);
            for (int i = 0; i < 6; i++) {
                if (i == position) {
                    llPoint.getChildAt(position).setBackgroundResource(
                            R.drawable.bg_dot_circle_white);
                } else {
                    llPoint.getChildAt(i).setBackgroundResource(
                            R.drawable.bg_dot_circle_black);
                }
            }
        }

    }
}
