package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.TicketsTypeAdapter;
import com.sports.limitsport.activity.presenter.SignUpPresenter;
import com.sports.limitsport.activity.ui.ISignUpView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.base.BaseSelectionAdapter;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.SelectTicket;
import com.sports.limitsport.model.TicketList;
import com.sports.limitsport.model.TicketListResponse;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.H5Activity;
import com.sports.limitsport.view.NumCheckView;
import com.sports.limitsport.view.SignUpFooterView;
import com.sports.limitsport.view.SignUpHeadView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/23.
 * 活动报名
 */

public class SignUpActivity extends BaseActivity implements ISignUpView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv_ticket_type)
    RecyclerView rlvTicketType;

    @BindView(R.id.imv_server)
    ImageView imvServer;
    @BindView(R.id.tv_server)
    TextView tvServer;
    @BindView(R.id.tv_price_bottom)
    TextView tvPriceBottom;
    private TicketsTypeAdapter adapter;
    private SignUpHeadView signUpHeadView;
    private SignUpFooterView signUpFooterView;
    private SignUpPresenter mPresenter;
    private String id; //活动ID
    private String title;//活动title
    private String imgCover;//活动封面
    private List<TicketList> data = new ArrayList<>();
    private SelectTicket selectTicket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        getIntentData();
        init();
        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            title = intent.getStringExtra("title");
            imgCover = intent.getStringExtra("imgCover");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new SignUpPresenter(this);
        }
        mPresenter.getTicketList(id);
    }

    private void init() {
        tvFocusHouse.setText("活动报名");

        TextViewUtil.setPartAndColorAndClick(tvServer, onClickListener, 2, tvServer.getText().length(), getResources().getColor(R.color.color_text_green));
        signUpHeadView = new SignUpHeadView(this);
        signUpHeadView.setImvCover(imgCover);
        signUpHeadView.setTvName(title);

        signUpFooterView = new SignUpFooterView(this);
        signUpFooterView.setNumCheckViewEnable(false);
        signUpFooterView.getNumCheckView().setOnNumChangedListener(new NumCheckView.OnNumChangedListener() {
            @Override
            public void onNumChanged(int num) {
                selectTicket.num = num;
                showSelectTicket();
            }

            @Override
            public void isAdd(boolean isAdd) {

            }
        });
        rlvTicketType.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new TicketsTypeAdapter(data);
        adapter.bindToRecyclerView(rlvTicketType);
        adapter.setHeaderAndEmpty(true);
        adapter.setHeaderView(signUpHeadView);
        adapter.setFooterView(signUpFooterView);

        adapter.setOnItemChildSelectClickListener(new BaseSelectionAdapter.OnItemChildSelectClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                XLog.e("select position = " + position);
                TicketList ticketList = (TicketList) adapter.getItem(position);
                if (ticketList != null) {
                    selectTicket = new SelectTicket();

                    selectTicket.id = ticketList.getId()+"";
                    selectTicket.maxNum = ticketList.getSoldNumber();
                    selectTicket.des = ticketList.getDescContent();
                    selectTicket.name = ticketList.getName();
                    selectTicket.price = ticketList.getMoney();

                    signUpFooterView.setNumCheckViewEnable(true);

                    showSelectTicket();
                }
            }
        });
        imvServer.setSelected(true);
    }

    private boolean check() {
        boolean ret = true;
        if (selectTicket == null || selectTicket.name == null) {
            ToastUtil.showFalseToast(this, "请选择票类");
            ret = false;
        }
        if (!imvServer.isSelected()) {
            ToastUtil.showFalseToast(this, "未同意服务协议");
            ret = false;
        }

        return ret;
    }

    private void showSelectTicket() {
        //设置最大可选择数量
        signUpFooterView.getNumCheckView().setMaxNum(selectTicket.maxNum);
        //设置数量为0
        signUpFooterView.getNumCheckView().setDefaultNum(selectTicket.num);
        //设置票种说明
        signUpFooterView.setTvTicketDes(selectTicket.des);
        //已选择票种和数量
        signUpHeadView.setTvTicketType("已选择 " + selectTicket.name + "x" + selectTicket.num);

        BigDecimal numB = new BigDecimal(selectTicket.num);
        BigDecimal numPrice = new BigDecimal(selectTicket.price);
        selectTicket.totalPrice = UnitUtil.formatSNum(numB.multiply(numPrice).toString());

        tvPriceBottom.setText("¥" + selectTicket.totalPrice);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done, R.id.imv_server})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_done:
                if (check()) {
                    gotoPayOrder();
                }
                break;
            case R.id.imv_server:
                if (imvServer.isSelected()) {
                    imvServer.setSelected(false);
                } else {
                    imvServer.setSelected(true);
                }
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gotoServerDeal();
        }
    };

    /**
     * 前往订单支付页
     */
    private void gotoPayOrder() {
        Intent intent = new Intent(this, PayOrderActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("title", title);
        intent.putExtra("imgCover",imgCover);
        intent.putExtra("selectTicket",selectTicket);
        startActivity(intent);
    }

    /**
     * 服务协议
     */
    private void gotoServerDeal() {
        Intent intent = new Intent(this, H5Activity.class);
        intent.putExtra("type", 1);
        intent.putExtra("url", "https://www.baidu.com");
        startActivity(intent);
    }

    @Override
    public void showTicketList(TicketListResponse response) {
        if (response != null) {
            adapter.addData(response.getData());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }
        mPresenter = null;
    }
}
