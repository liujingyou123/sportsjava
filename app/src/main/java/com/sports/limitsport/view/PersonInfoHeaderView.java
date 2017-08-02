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
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.discovery.JoinActivityActivity;
import com.sports.limitsport.discovery.JoinClubActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.adapter.TagFavAdapter;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 */

public class PersonInfoHeaderView extends LinearLayout {
    @BindView(R.id.imv_head)
    ImageView imvHead;
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.ll_clubs)
    LinearLayout llClubs;
    @BindView(R.id.ll_activity)
    LinearLayout llActivity;
    @BindView(R.id.ll_clubs_all)
    LinearLayout llClubsAll;
    @BindView(R.id.tv_dongtai_tip)
    TextView tvDongtaiTip;
    @BindView(R.id.imv_gender)
    ImageView imvGender;
    @BindView(R.id.tv_focus)
    TextView tvFocus;
    @BindView(R.id.tv_fensi)
    TextView tvFensi;
    @BindView(R.id.ll_fensi)
    LinearLayout llFensi;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
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
    @BindView(R.id.tg)
    TagCloudLayout tg;
    @BindView(R.id.imv_activity_go)
    ImageView imvActivityGo;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_activity_name)
    TextView tvActivityName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.imv_club_go)
    ImageView imvClubGo;
    private String status;
    private UserInfoResponse response;

    public PersonInfoHeaderView(Context context) {
        super(context);
        init();
    }

    public PersonInfoHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PersonInfoHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_head_person, this);
        ButterKnife.bind(this, this);
        setData();
        addClubs();
//        setEmpty();
    }

    @OnClick({R.id.tv_focus, R.id.imv_activity_go, R.id.imv_club_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_focus:
                if (response != null) {
                    if ("0".equals(status) || "2".equals(status)) { //0:互相不关注 1:我关注他 2:他关注我 3:互相关注
                        foucesFans("0", response.getData().getId() + "");
                    } else {
                        foucesFans("2", response.getData().getId() + "");
                    }
                }

//                showCancleFocusDailog();
                break;
            case R.id.imv_activity_go:
                Intent intent = new Intent(this.getContext(), JoinActivityActivity.class);
                this.getContext().startActivity(intent);
                break;
            case R.id.imv_club_go:
                Intent intent1 = new Intent(this.getContext(), JoinClubActivity.class);
                this.getContext().startActivity(intent1);
                break;
        }
    }

    public void setData() {
        Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imvCover);
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);
    }

    public void addClubs() {
        for (int i = 0; i < 2; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_myclubs, null);
            ImageView imvHead = (ImageView) view.findViewById(R.id.imv_head);
            Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, UnitUtil.dip2px(getContext(), 15));
            llClubs.addView(view, lp);
        }

    }

    public void setData(UserInfoResponse response, String status) {
        if (response != null && response.getData() != null) {
            this.status = status;
            this.response = response;
            UserInfoResponse.DataBean dataBean = response.getData();
            Batman.getInstance().getImageWithCircle(dataBean.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);
            if ("男".equals(dataBean.getSex())) { //男的
                imvGender.setVisibility(VISIBLE);
                imvGender.setSelected(false);

            } else if ("女".equals(dataBean.getSex())) {//女的
                imvGender.setVisibility(VISIBLE);
                imvGender.setSelected(true);

            }

            tvFensi.setText(dataBean.getFansNum() + "");

            tvGuanzhu.setText(dataBean.getAttentionNum() + "");


            if (!TextViewUtil.isEmpty(dataBean.getName())) {
                tvName.setText(dataBean.getName());
            }

            if (!TextViewUtil.isEmpty(dataBean.getCity())) {
                tvLocation.setText(dataBean.getCity());
            }

            if (!TextViewUtil.isEmpty(dataBean.getIntroduction())) {
                tvDes.setText(dataBean.getIntroduction());
            }

            if (!TextViewUtil.isEmpty(dataBean.getHobby())) {
                String[] strs = dataBean.getHobby().split(",");
                setTagData(Arrays.asList(strs));
            }

            if ("0".equals(status) || "2".equals(status)) { //0:互相不关注 1:我关注他 2:他关注我 3:互相关注
                tvFocus.setVisibility(VISIBLE);
                tvFocus.setText("+关注");
                tvFocus.setSelected(true);
            } else if ("1".equals(status)) {
                tvFocus.setVisibility(VISIBLE);
                tvFocus.setText("取消关注");
                tvFocus.setSelected(false);
            } else if ("3".equals(status)) {
                tvFocus.setVisibility(VISIBLE);
                tvFocus.setText("取消关注");
                tvFocus.setSelected(false);
            } else {
                tvFocus.setVisibility(View.GONE);
            }
        }
    }

    private void setTagData(List<String> data) {
        if (data != null) {
            tg.setAdapter(new TagFavAdapter(getContext(), data));
        }
    }

    public void setEmpty() {
        llActivity.setVisibility(View.GONE);
        llClubsAll.setVisibility(View.GONE);
        tvDongtaiTip.setVisibility(View.GONE);
    }

    public void showCancleFocusDailog() {
        NoticeDelDialog dialog = new NoticeDelDialog(this.getContext());
        dialog.setMessage("确定要取消关注吗？");
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {

            }
        });
        dialog.show();
    }

    /**
     * 0:添加 1:移除 2:取消关注
     *
     * @param type
     */
    private void foucesFans(final String type, String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", type);
        hashMap.put("receiveUserId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).focusFans(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    if ("0".equals(type)) {
                        ToastUtil.showTrueToast(getContext(), "关注成功");
                        tvFocus.setText("取消关注");
                        tvFocus.setSelected(false);
                        //TODO
//                        status = "1";
//                        status = "3";

                    } else {
                        ToastUtil.showTrueToast(getContext(), "已取消关注");
                        tvFocus.setText("+关注");
                        tvFocus.setSelected(true);
                        //TODO
//                        status = "0";
//                        status = "2";
                    }

                } else {
                    if ("0".equals(type)) {
                        ToastUtil.showTrueToast(getContext(), "关注失败");
                    } else {
                        ToastUtil.showTrueToast(getContext(), "取消关注失败");
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if ("0".equals(type)) {
                    ToastUtil.showTrueToast(getContext(), "关注失败");
                } else {
                    ToastUtil.showTrueToast(getContext(), "取消关注失败");
                }
            }
        });
    }
}
