package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/7/17.
 */

public class SignUpHeadView extends RelativeLayout {
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_ticket_type)
    TextView tvTicketType;

    public SignUpHeadView(Context context) {
        super(context);
        initView();
    }

    public SignUpHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SignUpHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.sign_up_header_view, this);
        ButterKnife.bind(this, this);
    }

    public void setImvCover(String imvCoverUrl) {
        Batman.getInstance().fromNet(imvCoverUrl, imvCover);
    }

    public void setTvName(String name) {
        tvName.setText(name);
    }

    public void setTvTicketType(String msg) {
        tvTicketType.setText(msg);
    }
}
