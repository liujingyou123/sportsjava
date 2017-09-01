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
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.mine.MyActivitysActivity;
import com.sports.limitsport.mine.MyClubsActivity;
import com.sports.limitsport.mine.MyCollectionActivity;
import com.sports.limitsport.mine.MyFansListActivity;
import com.sports.limitsport.mine.MyFocusListActivity;
import com.sports.limitsport.mine.UserInfoActivity;
import com.sports.limitsport.mine.adapter.TagFavAdapter;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    private int type;

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
    }

    private void setTagData(List<String> data) {
        if (data != null) {
            tg.setAdapter(new TagFavAdapter(getContext(), data));
        }
    }

    @OnClick({R.id.imv_go, R.id.tv_fav, R.id.ll_fensi, R.id.ll_guanzhu, R.id.tv_club, R.id.tv_activity, R.id.tv_name})
    public void onViewClicked(View view) {
        if (!checkIsLogin()) {
            return;
        }
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
            case R.id.tv_name:
                if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent6 = new Intent(getContext(), UserInfoActivity.class);
                    getContext().startActivity(intent6);
                }
                break;
        }
    }

    /**
     * @param type 0:游客模式 1:已完善用户信息 2:为完善用户信息
     */
    public void setType(int type) {
        this.type = type;
        if (type == 0 || type == 2) {
            imvHead.setImageResource(R.mipmap.icon_gerenzhuye_morentouxiang);
            imvGender.setVisibility(View.GONE);
            tvFensi.setText("0");
            tvGuanzhu.setText("0");
            if (type == 0) {
                tvName.setText("游客模式");
            } else if (type == 2) {
                tvName.setText("尚未设置昵称");
            }
            tvLocation.setText("未知");
            tvDes.setText("向小伙伴们介绍一下自己吧～");
            tg.setVisibility(View.GONE);
            tvFav.setText("收藏(0)");
            tvClub.setText("俱乐部(0)");
            tvActivity.setText("活动(0)");
            tvDongtai.setText("全部动态(0)");
        }
    }

    public void setData(UserInfoResponse response) {
        if (response != null && response.getData() != null) {
            UserInfoResponse.DataBean dataBean = response.getData();
            Batman.getInstance().getImageWithCircle(dataBean.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);
            if ("0".equals(dataBean.getSex())) { //男的
                imvGender.setVisibility(VISIBLE);
                imvGender.setSelected(false);

            } else if ("1".equals(dataBean.getSex())) {//女的
                imvGender.setVisibility(VISIBLE);
                imvGender.setSelected(true);

            } else {
                imvGender.setVisibility(GONE);
            }

            tvFensi.setText(dataBean.getFansNum() + "");


            tvGuanzhu.setText(dataBean.getAttentionNum() + "");


            if (!TextViewUtil.isEmpty(dataBean.getName())) {
                tvName.setText(dataBean.getName());
            }

            if (!TextViewUtil.isEmpty(dataBean.getCity())) {
                String location = TextViewUtil.isEmpty(dataBean.getProvince()) ? "" : dataBean.getProvince();
                location = location + " " + (TextViewUtil.isEmpty(dataBean.getCity()) ? "" : dataBean.getCity());
                tvLocation.setText(location);
            }

            if (!TextViewUtil.isEmpty(dataBean.getIntroduction())) {
                tvDes.setText(dataBean.getIntroduction());
            }

            if (!TextViewUtil.isEmpty(dataBean.getHobby())) {
                String[] strs = dataBean.getHobby().split(",");
                setTagData(Arrays.asList(strs));
            }

            tvFav.setText("收藏(" + dataBean.getCollectionNum() + ")");
            tvClub.setText("俱乐部(" + dataBean.getClubNum() + ")");
            tvActivity.setText("活动(" + dataBean.getActivityNum() + ")");

            tvDongtai.setText("全部动态(" + dataBean.getMyActivityNum() + ")");
        }
    }

    private boolean checkIsLogin() {
        if (SharedPrefsUtil.getUserInfo() == null) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("type", "login");
            getContext().startActivity(intent);
            return false;
        }
        return true;
    }
}
