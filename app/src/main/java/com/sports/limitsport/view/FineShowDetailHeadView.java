package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sports.limitsport.R;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.image.Batman;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/12.
 */

public class FineShowDetailHeadView extends LinearLayout {
    @BindView(R.id.imv_cover)
    ImageView imvCover;

    public FineShowDetailHeadView(Context context) {
        super(context);
        init();
    }

    public FineShowDetailHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FineShowDetailHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_fineshowdetail_head, this);
        ButterKnife.bind(this, this);

        Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imvCover);

    }

    @OnClick({R.id.imv_pinglun, R.id.imv_share, R.id.imv_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_pinglun:
                break;
            case R.id.imv_share:
                ShareDialog shareDialog = new ShareDialog(getContext());
                shareDialog.show();
                break;
            case R.id.imv_report:
                ReportDialog reportDialog = new ReportDialog(getContext());
                reportDialog.show();
                break;
        }
    }
}
