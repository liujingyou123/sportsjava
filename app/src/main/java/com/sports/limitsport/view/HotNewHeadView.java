package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ShallAdapter;
import com.sports.limitsport.discovery.Club;
import com.sports.limitsport.discovery.adapter.ClubAdapter;
import com.sports.limitsport.util.GlideImageLoader;
import com.sports.limitsport.util.MyTestData;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class HotNewHeadView extends LinearLayout {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rlv_club)
    RecyclerView rlvClub;
    @BindView(R.id.rlv_fanshow)
    RecyclerView rlvFanshow;
    private ClubAdapter clubAdapter;

    private List<Club> clubs = new ArrayList<>();

    public HotNewHeadView(Context context) {
        super(context);
        init();
    }

    public HotNewHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HotNewHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_hotnew, this);
        ButterKnife.bind(this, this);
        showBanner();
        setClubs();
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

    private void setClubs() {
        for (int i = 0; i < 10; i++) {
            Club club = new Club();
            clubs.add(club);
        }
        rlvClub.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        clubAdapter = new ClubAdapter(clubs);
        rlvClub.setAdapter(clubAdapter);


        SpacesItemHDecoration decoration = new SpacesItemHDecoration(16);
        rlvClub.addItemDecoration(decoration);
    }
}
