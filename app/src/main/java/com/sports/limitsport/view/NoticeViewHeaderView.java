package com.sports.limitsport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/7/13.
 * 我的关注
 */

public class NoticeViewHeaderView extends LinearLayout {
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.imv_close)
    ImageView imvClose;
    @BindView(R.id.tv_recommend)
    TextView tvRecommend;

    public NoticeViewHeaderView(Context context) {
        super(context);
        init();
    }

    public NoticeViewHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoticeViewHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_notice_head, this);
        ButterKnife.bind(this, this);
    }

    public void setEmpty() {
        tvMore.setVisibility(View.GONE);
        tvTip.setText("您还没有关注的人，以下是为您推荐的小伙伴！");
        tvRecommend.setVisibility(View.VISIBLE);
    }

    public void setTip(String msg) {
        tvTip.setText(msg);
    }

    public void setButtonText(String msg) {
        tvMore.setText(msg);
    }

    public void setTvRecommendVisible(int visible) {
        tvRecommend.setVisibility(visible);
    }

    public void setButtonClickListener(OnClickListener onClickListener) {
        tvMore.setOnClickListener(onClickListener);
    }

    public void setCloseClickListener(OnClickListener onClickListener) {
        imvClose.setOnClickListener(onClickListener);
    }

}
