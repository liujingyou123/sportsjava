package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.main.adapter.LaunchPageAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jingyouliu on 17/11/5.
 * 成为商户
 */

public class BecomeBusinessActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.llPoint)
    LinearLayout llPoint;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    private LaunchPageAdapter adapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        ButterKnife.bind(this);
        initView();
        addPoint();
    }

    /*
    * 初始化ViewPager
    */
    public void initView() {
        tvFocusHouse.setText("俱乐部介绍");
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.clear();
        Fragment firstFragment = new BecomBusinessOneFragment();
        Fragment secondFragment = new BecomeBusinessTwoFragment();

        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);

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

    /**
     * 添加小圆点
     */
    private void addPoint() {
        // 1.根据图片多少，添加多少小圆点
        for (int i = 0; i < 2; i++) {
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
            iv.setBackgroundResource(R.drawable.gray_dot_select);
            llPoint.addView(iv);
        }
        llPoint.getChildAt(0).setBackgroundResource(R.drawable.white_dot_unselect);

    }

    /**
     * 判断小圆点
     *
     * @param position
     */
    private void monitorPoint(int position) {
        llPoint.setVisibility(View.VISIBLE);
        for (int i = 0; i < 2; i++) {
            if (i == position) {
                llPoint.getChildAt(position).setBackgroundResource(
                        R.drawable.gray_dot_select);
            } else {
                llPoint.getChildAt(i).setBackgroundResource(
                        R.drawable.white_dot_unselect);
            }
        }

    }

    @Override
    protected void onDestroy() {
        Log.e("AdActivity", "onDestroy");
        if (adapter != null) {
            adapter = null;
        }
        super.onDestroy();

    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_done:
                Intent intent = new Intent(this, BecomeBusinessSubmitActivity.class);
                startActivity(intent);
                break;
        }
    }
}
