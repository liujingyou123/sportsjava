package com.sports.limitsport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/17.
 */

public class SignUpFooterView extends RelativeLayout {
    @BindView(R.id.tv_ticket_des)
    TextView tvTicketDes;
    @BindView(R.id.tv_ticket_nums)
    TextView tvTicketNums;
    @BindView(R.id.ncv)
    NumCheckView ncv;
//    @BindView(R.id.imv_server)
//    ImageView imvServer;
//    @BindView(R.id.tv_server)
//    TextView tvServer;

    public SignUpFooterView(Context context) {
        super(context);
        initView();
    }

    public SignUpFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SignUpFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.sign_up_footer_view, this);
        ButterKnife.bind(this, this);
//        imvServer.setSelected(true);
    }

//    @OnClick({R.id.imv_server, R.id.tv_server})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.imv_server:
//                if (imvServer.isSelected()) {
//                    imvServer.setSelected(false);
//                } else {
//                    imvServer.setSelected(true);
//                }
//                break;
//            case R.id.tv_server:
//                break;
//        }
//    }
}
