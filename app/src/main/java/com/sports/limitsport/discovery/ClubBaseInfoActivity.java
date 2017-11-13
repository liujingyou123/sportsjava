package com.sports.limitsport.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.dialog.DelAndReportDialog;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.model.ClubDetail;
import com.sports.limitsport.model.ClubDetailResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CreatPersonView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/12.
 * 俱乐部基础信息
 */

public class ClubBaseInfoActivity extends BaseActivity {
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.ll_members_two)
    LinearLayout llMembersTwo;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.btn_done)
    TextView btnDone;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.imv_club_logo)
    ImageView imvClubLogo;
    @BindView(R.id.imv_status)
    ImageView imvStatus;
    @BindView(R.id.tv_club_name)
    TextView tvClubName;
    @BindView(R.id.ll_members)
    LinearLayout llMembers;
    @BindView(R.id.tv_club_type)
    TextView tvClubType;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.imv_report)
    ImageView imvReport;
    @BindView(R.id.imv_share)
    ImageView imvShare;
    private ClubDetail dataBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubbaseinfo);
        ButterKnife.bind(this);
        getIntentData();
        initView();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            dataBean = (ClubDetail) intent.getSerializableExtra("data");
        }
    }

    @OnClick({R.id.btn_done, R.id.imv_focus_house_back, R.id.imv_report, R.id.imv_share, R.id.tv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                if (SharedPrefsUtil.getUserInfo() == null) {
                    Intent intent2 = new Intent(ClubBaseInfoActivity.this, LoginActivity.class);
                    intent2.putExtra("type", "2");
                    startActivity(intent2);
                    return;
                } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent3 = new Intent(ClubBaseInfoActivity.this, IdentifyMainActivity.class);
                    intent3.putExtra("type", "2");
                    startActivity(intent3);
                } else if (dataBean != null && dataBean.getJoinClubFlag() == 0) {
                    joinClub(dataBean.getId() + "");

                } else if (dataBean != null && dataBean.getJoinClubFlag() == 1) {
                    exitDialog();
                }
                break;
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_report:
                DelAndReportDialog dialog = new DelAndReportDialog(this, "1", null,null);
                dialog.show();
                break;
            case R.id.imv_share:
                break;
            case R.id.tv_more:
                if (tvMore.isSelected()) {
                    tvMore.setText("更多管理着");
                    tvMore.setSelected(false);
                    llMembersTwo.setVisibility(View.GONE);
                } else {
                    tvMore.setText("收起");
                    tvMore.setSelected(true);
                    llMembersTwo.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void initView() {
        if (dataBean == null) {
            return;
        }
        Batman.getInstance().fromNet(dataBean.getClubImgUrl(), imvCover);
        Batman.getInstance().getImageWithCircle(dataBean.getLogoUrl(), imvClubLogo, R.mipmap.icon_club_defaul, R.mipmap.icon_club_defaul);
        tvClubName.setText(dataBean.getClubName());

        if (dataBean.getManagerList() != null) {
            if (dataBean.getManagerList().size() > 3) {
                tvMore.setVisibility(View.VISIBLE);
            } else {
                tvMore.setVisibility(View.GONE);
            }

            for (int i = 0; i < dataBean.getManagerList().size(); i++) {
                CreatPersonView creatPersonView = new CreatPersonView(this);
                creatPersonView.setData(dataBean.getManagerList().get(i));

                if (i < 3) {
                    llMembers.addView(creatPersonView, llMembers.getChildCount() - 1);
                } else {
                    llMembersTwo.addView(creatPersonView);
                }
            }
        }

        if (dataBean.getAuthEntity() == 2) {
            imvStatus.setVisibility(View.VISIBLE);
        } else {
            imvStatus.setVisibility(View.GONE);
        }

        if (dataBean.getJoinClubFlag() == 1) { //1:是 0:否
            btnDone.setText("退出俱乐部");
//                if (dataBean.getMemberRule() == 1) { //创始人
//                    llBottom.setVisibility(View.GONE);
//                } else {
//                    llBottom.setVisibility(View.VISIBLE);
//                }
        } else {
            llBottom.setVisibility(View.VISIBLE);
            btnDone.setText("申请加入");
        }
        tvClubType.setText("俱乐部类型 : " + dataBean.getClubTypeName());
        tvContent.setText(dataBean.getClubIntroduction());
    }

    public void joinClub(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clubId", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).joinClub(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                joinClubResult(response.isSuccess());

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                joinClubResult(false);


            }
        });
    }

    public void quiteClub(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clubId", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).quiteClub(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                quiteClubResult(response.isSuccess());

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                quiteClubResult(false);


            }
        });
    }

    public void joinClubResult(boolean isSuccess) {
        if (isSuccess) {
            dataBean.setJoinClubFlag(1);
            btnDone.setText("退出俱乐部");
        } else {
            ToastUtil.showFalseToast(this, "申请失败");
        }
    }

    public void quiteClubResult(boolean success) {
        if (success) {
            dataBean.setJoinClubFlag(0);
            btnDone.setText("申请加入");
        } else {
            ToastUtil.showFalseToast(this, "申请失败");
        }
    }

    private void exitDialog() {
        NoticeDelDialog dialog = new NoticeDelDialog(this);
        dialog.setMessage("您确定要退出俱乐部吗？");
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                quiteClub(dataBean.getId() + "");
            }
        });
        dialog.show();
    }
}
