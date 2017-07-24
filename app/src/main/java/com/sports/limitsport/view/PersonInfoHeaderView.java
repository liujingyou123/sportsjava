package com.sports.limitsport.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.discovery.JoinActivityActivity;
import com.sports.limitsport.discovery.JoinClubActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.UnitUtil;

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
                showCancleFocusDailog();
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
}
