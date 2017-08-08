package com.sports.limitsport.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.ClubDetailResponse;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by liuworkmac on 17/7/11.
 * 创始人View
 */

public class CreatPersonView extends LinearLayout {
    @BindView(R.id.imv_head)
    ImageView imvHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_jiaose)
    TextView tvJiaose;
    @BindView(R.id.tv_des)
    TextView tvDes;

    public CreatPersonView(Context context) {
        super(context);
        initView();
    }

    public CreatPersonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CreatPersonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_creatperson, this);
        ButterKnife.bind(this, this);

//        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);

    }

    public void setData(ClubDetailResponse.DataBean.ManagerListBean managerListBean) {
        if (managerListBean != null) {
            Batman.getInstance().getImageWithCircle(managerListBean.getHeadPortrait(), imvHead, 0, 0);
            tvName.setText(managerListBean.getMemberName());
            tvJiaose.setText(managerListBean.getRuleDesc());
            if ("创始人".equals(managerListBean.getRuleDesc())) {
                tvJiaose.setBackgroundResource(R.drawable.bg_done_pressed);
                tvJiaose.setTextColor(Color.parseColor("#ffffff"));
            }
            tvDes.setText(managerListBean.getUserIntroduction());
        }
    }
}
