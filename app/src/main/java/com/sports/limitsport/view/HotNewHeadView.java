package com.sports.limitsport.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.ClubDetailActivity;
import com.sports.limitsport.discovery.FindClubActivity;
import com.sports.limitsport.discovery.FineShowActivity;
import com.sports.limitsport.discovery.FineShowDetailActivity;
import com.sports.limitsport.discovery.NewPersonReportActivity;
import com.sports.limitsport.discovery.adapter.ClubAdapter;
import com.sports.limitsport.discovery.adapter.FineShowAdapter;
import com.sports.limitsport.model.Club;
import com.sports.limitsport.model.FineShowList;
import com.sports.limitsport.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private FineShowAdapter fineShowAdapter;

    private List<Club> clubs = new ArrayList<>();
    private List<FineShowList> fineShows = new ArrayList<>();
    private List<String> advs = new ArrayList<>();

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
//        showBanner();
        showClubs();
        showFines();
    }

    private void showBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(advs);
        //设置标题集合（当banner样式有显示title时）
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void showClubs() {
        rlvClub.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        clubAdapter = new ClubAdapter(clubs);
        rlvClub.setAdapter(clubAdapter);

        SpacesItemHDecoration decoration = new SpacesItemHDecoration(5);
        rlvClub.addItemDecoration(decoration);

        clubAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Club club = (Club) adapter.getItem(position);
                gotoClubDetail(club.getId());
            }
        });
    }

    /**
     * 精彩秀
     */
    private void showFines() {
        rlvFanshow.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        fineShowAdapter = new FineShowAdapter(fineShows);
        rlvFanshow.setAdapter(fineShowAdapter);


        SpacesItemHDecoration decoration = new SpacesItemHDecoration(5);
        rlvFanshow.addItemDecoration(decoration);

        fineShowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FineShowList fineShowList = (FineShowList) adapter.getItem(position);
                gotoFineShowDetail(fineShowList.getId() + "");
            }
        });
    }

    @OnClick({R.id.tv_clubs, R.id.tv_fineshow, R.id.tv_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_clubs:
                Intent intent = new Intent(getContext(), FindClubActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.tv_fineshow:
                Intent intent1 = new Intent(getContext(), FineShowActivity.class);
                getContext().startActivity(intent1);
                break;
            case R.id.tv_news:
                gotoNewPersonReport();
                break;
        }
    }

    /**
     * 俱乐部详情页
     */
    private void gotoClubDetail(String id) {
        Intent intent = new Intent(getContext(), ClubDetailActivity.class);
        intent.putExtra("id", id);
        getContext().startActivity(intent);
    }

    /**
     * 精彩秀详情
     */
    private void gotoFineShowDetail(String id) {
        Intent intent = new Intent(getContext(), FineShowDetailActivity.class);
        intent.putExtra("id", id);
        getContext().startActivity(intent);
    }

    /**
     * 前往新人报到页
     */
    private void gotoNewPersonReport() {
        Intent intent2 = new Intent(getContext(), NewPersonReportActivity.class);
        getContext().startActivity(intent2);
    }

    public void setClubsList(List<Club> data) {
        clubs.clear();
        clubs.addAll(data);
        clubAdapter.notifyDataSetChanged();
    }

    public void setImgUrls(List<String> imgUrls) {
        if (advs != null) {
            advs.clear();
            advs.addAll(imgUrls);
        }
        showBanner();
    }

    public void setFineShowList(List<FineShowList> data) {
        if (data != null && data.size() > 0) {
            fineShows.clear();
            fineShows.addAll(data);
            fineShowAdapter.notifyDataSetChanged();
        }
    }
}
