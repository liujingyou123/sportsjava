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
import com.sports.limitsport.activity.ActivityDetailActivity;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.discovery.ClubDetailActivity;
import com.sports.limitsport.discovery.JoinActivityActivity;
import com.sports.limitsport.discovery.JoinClubActivity;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.mine.adapter.TagFavAdapter;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.Club;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.EventBusUserModel;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.tv_activity_size)
    TextView tvActivitySize;
    @BindView(R.id.tv_club_size)
    TextView tvClubSize;
    private UserInfoResponse mResponse;
    private Act act;

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
//        setData();
//        addClubs();
//        setEmpty();
    }

    @OnClick({R.id.tv_focus, R.id.imv_activity_go, R.id.imv_club_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_focus:
                if (SharedPrefsUtil.getUserInfo() == null) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.putExtra("type", "2");
                    getContext().startActivity(intent);
                    return;
                } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent = new Intent(getContext(), IdentifyMainActivity.class);
                    intent.putExtra("type", "2");
                    getContext().startActivity(intent);
                } else {
                    if (mResponse != null) {
                        if ("0".equals(mResponse.getData().getIsAttenttion())) { //0:互相不关注 1:我关注他 2:他关注我 3:互相关注
                            foucesFans("0", mResponse.getData().getId() + "");
                        } else {
                            showCancleFocusDailog();
                        }
                    }
                }


                break;
            case R.id.imv_activity_go:
                if (mResponse != null) {
                    Intent intent = new Intent(this.getContext(), JoinActivityActivity.class);
                    intent.putExtra("userId", mResponse.getData().getId() + "");
                    this.getContext().startActivity(intent);
                }
                break;
            case R.id.imv_club_go:
                if (mResponse != null) {
                    Intent intent1 = new Intent(this.getContext(), JoinClubActivity.class);
                    intent1.putExtra("userId", mResponse.getData().getId() + "");
                    this.getContext().startActivity(intent1);
                }
                break;
            case R.id.ll_activity:
                if (act != null) {
                    Intent intent2 = new Intent(getContext(), ActivityDetailActivity.class);
                    intent2.putExtra("id", act.getId() + "");
                    intent2.putExtra("week", act.getWeek());
                    intent2.putExtra("minMoney", act.getMinMoney());
                    getContext().startActivity(intent2);
                }
                break;
        }
    }

//    public void setData() {
//        Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imvCover);
//        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);
//    }

    public void addClubs(List<Club> clubs) {
        if (clubs != null) {
            llClubs.removeAllViews();
            for (int i = 0; i < clubs.size(); i++) {
                final Club club = clubs.get(i);
                if (club != null) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_myclubs, null);
                    view.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), ClubDetailActivity.class);
                            intent.putExtra("id", club.getId());
                            getContext().startActivity(intent);
                        }
                    });
                    view.findViewById(R.id.rl_data).setVisibility(VISIBLE);
                    ImageView imvHead = (ImageView) view.findViewById(R.id.imv_head);
                    TextView tvName = (TextView) view.findViewById(R.id.tv_name);
                    TextView tvLocation = (TextView) view.findViewById(R.id.tv_location);
                    TextView tvNum = (TextView) view.findViewById(R.id.tv_des);
                    TextView tvTip = (TextView) view.findViewById(R.id.tv_tip);
                    TextView tvFocus = (TextView) view.findViewById(R.id.tv_focus);
                    tvFocus.setEnabled(true);
                    tvFocus.setText("了解");
                    tvName.setText(club.getClubName());
                    tvLocation.setText(club.getClubIntroduction());
                    tvNum.setText(club.getMemberNum() + "成员");
                    if ("1".equals(club.getIsActivity())) {
                        tvTip.setVisibility(View.VISIBLE);
                    } else {
                        tvTip.setVisibility(View.GONE);
                    }

                    Batman.getInstance().getImageWithCircle(club.getClubImgUrl(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);

                    LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                    lp.setMargins(0, 0, 0, UnitUtil.dip2px(getContext(), 15));
                    llClubs.addView(view, lp);
                }
            }
        }


    }

    public void setActivityList(ActivityResponse response) {
        if (response != null && response.getData() != null && response.getData().getData() != null && response.getData().getData().size() > 0) {
            llActivity.setVisibility(View.VISIBLE);
            tvActivitySize.setText("参加的活动(" + response.getData().getTotalSize() + ")");
            act = response.getData().getData().get(0);
            if (act != null) {
                if ("0".equals(act.getMinMoney())) {
                    tvPrice.setText("¥0");
                } else {
                    tvPrice.setText("¥" + UnitUtil.formatSNum(act.getMinMoney()) + " - ¥" + UnitUtil.formatSNum(act.getMaxMoney()));
                }

                if ("1".equals(act.getStatus())) { //报名中

                    if (act.getTicketNum() > 0) {
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setText("报名中");

                    } else {
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setText("名额已抢完");
                    }

                } else {
                    tvStatus.setVisibility(View.GONE);
                }

                //TODO 测试
//                Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imvCover);

                //正式
                Batman.getInstance().fromNet(act.getCoverUrl(), imvCover, R.mipmap.icon_ver_default, R.mipmap.icon_ver_default);

                tvName.setText(act.getName());
                tvTime.setText(act.getStartDate()
                        + " " + UnitUtil.stringToWeek(act.getWeek()) + " " + act.getStartTime());
                tvAddress.setText("活动地:" + act.getAddress());
            }
        }
    }

    public void setData(UserInfoResponse response) {
        if (response != null && response.getData() != null) {
            this.mResponse = response;
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

            if ("0".equals(response.getData().getIsAttenttion())) { //0:互相不关注 1:我关注他 2:他关注我 3:互相关注
                tvFocus.setVisibility(VISIBLE);
                tvFocus.setText("+关注");
                tvFocus.setSelected(true);
            } else if ("1".equals(response.getData().getIsAttenttion())) {
                tvFocus.setVisibility(VISIBLE);
                tvFocus.setText("取消关注");
                tvFocus.setSelected(false);
            } else {
                tvFocus.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setClubsList(ClubListResponse response) {
        if (response != null && response.getData() != null && response.getData().getTotalSize() > 0) {
            llClubsAll.setVisibility(VISIBLE);
            tvClubSize.setText("参加的俱乐部(" + response.getData().getTotalSize() + ")");
            addClubs(response.getData().getData());
        }
    }

    private void setTagData(List<String> data) {
        if (data != null) {
            tg.setAdapter(new TagFavAdapter(getContext(), data));
        }
    }

    public void setDongtaiSize(int dongTaiSize) {
        tvDongtaiTip.setText("全部动态(" + dongTaiSize + ")");
    }

    public void setDongtaiGone() {
        tvDongtaiTip.setVisibility(GONE);
    }

    public void showCancleFocusDailog() {
        NoticeDelDialog dialog = new NoticeDelDialog(this.getContext());
        dialog.setMessage("确定要取消关注吗？");
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                foucesFans("2", mResponse.getData().getId() + "");
            }
        });
        dialog.show();
    }

    public UserInfoResponse getUserInfo() {
        return mResponse;
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
                        mResponse.getData().setIsAttenttion("1");

                    } else {
                        ToastUtil.showTrueToast(getContext(), "已取消关注");
                        tvFocus.setText("+关注");
                        tvFocus.setSelected(true);
                        mResponse.getData().setIsAttenttion("0");
                    }

                } else {
                    if ("0".equals(type)) {
                        ToastUtil.showTrueToast(getContext(), "关注失败");
                    } else {
                        ToastUtil.showTrueToast(getContext(), "取消关注失败");
                    }
                }

                EventBusUserModel param = new EventBusUserModel();
                param.isRefresh = true;
                EventBus.getDefault().post(param);
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
