package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.image.Batman;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubbaseinfo);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick({R.id.btn_done, R.id.imv_focus_house_back, R.id.imv_report, R.id.imv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                exitDialog();
                break;
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_report:
                ReportDialog dialog = new ReportDialog(this, "1", null);
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
        Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imvCover);
    }

    private void exitDialog() {
        NoticeDelDialog dialog = new NoticeDelDialog(this);
        dialog.setMessage("您确定要退出俱乐部吗？");
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {

            }
        });
        dialog.show();
    }
}
