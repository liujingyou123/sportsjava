package com.sports.limitsport.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.mine.MyActivitysActivity;
import com.sports.limitsport.mine.MyClubsActivity;
import com.sports.limitsport.mine.MyCollectionActivity;
import com.sports.limitsport.mine.MyFansListActivity;
import com.sports.limitsport.mine.MyFocusListActivity;
import com.sports.limitsport.mine.UserInfoActivity;
import com.sports.limitsport.mine.adapter.TagFavAdapter;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/3.
 */

public class MineHeaderView extends LinearLayout {
    @BindView(R.id.tg)
    TagCloudLayout tg;
    @BindView(R.id.imv_head)
    ImageView imvHead;
    @BindView(R.id.imv_gender)
    ImageView imvGender;
    @BindView(R.id.imv_go)
    ImageView imvGo;
    @BindView(R.id.tv_fensi)
    TextView tvFensi;
    @BindView(R.id.ll_fensi)
    LinearLayout llFensi;
    @BindView(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_fav)
    TextView tvFav;
    @BindView(R.id.tv_club)
    TextView tvClub;
    @BindView(R.id.tv_activity)
    TextView tvActivity;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.tv_Dongtai)
    TextView tvDongtai;

    public MineHeaderView(Context context) {
        super(context);
        init();
    }

    public MineHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MineHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_head_mine, this);
        ButterKnife.bind(this, this);
        setData();
    }

    private void setData() {
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mData.add("" + i);
        }
        tg.setAdapter(new TagFavAdapter(getContext(), mData));
    }

    @OnClick({R.id.imv_go, R.id.tv_fav, R.id.ll_fensi, R.id.ll_guanzhu, R.id.tv_club, R.id.tv_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_go:
                Intent intent = new Intent(getContext(), UserInfoActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.tv_fav:
                Intent intent3 = new Intent(getContext(), MyCollectionActivity.class);
                getContext().startActivity(intent3);
                break;
            case R.id.ll_fensi:
                Intent intent2 = new Intent(getContext(), MyFansListActivity.class);
                getContext().startActivity(intent2);
                break;
            case R.id.ll_guanzhu:
                Intent intent1 = new Intent(getContext(), MyFocusListActivity.class);
                getContext().startActivity(intent1);
                break;
            case R.id.tv_club:
                Intent intent4 = new Intent(getContext(), MyClubsActivity.class);
                getContext().startActivity(intent4);
                break;
            case R.id.tv_activity:
                Intent intent5 = new Intent(getContext(), MyActivitysActivity.class);
                getContext().startActivity(intent5);
                break;
        }
    }

    /**
     * @param type 0:游客模式 1:非游客模式
     */
    public void setType(int type) {
        if (type == 0) {
            imvHead.setImageResource(R.mipmap.icon_gerenzhuye_morentouxiang);
            imvGender.setVisibility(View.GONE);
            tvFensi.setText("0");
            tvGuanzhu.setText("0");
            tvName.setText("游客模式");
            tvLocation.setText("未知");
            tvDes.setText("向小伙伴们介绍一下自己吧～");
            tg.setVisibility(View.GONE);
            tvFav.setText("收藏(0)");
            tvClub.setText("俱乐部(0)");
            tvActivity.setText("活动(0)");
            tvDongtai.setText("全部动态(0)");
        } else {

        }
    }
}
