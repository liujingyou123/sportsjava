package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.main.DealActivity;
import com.sports.limitsport.mine.model.EventBusUserInfo;
import com.sports.limitsport.mine.presenter.SetPresenter;
import com.sports.limitsport.mine.ui.ISetView;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.model.UserSettingInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoTimeOutSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.CleanMessageUtil;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.mine.ItemView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/6.
 * 设置
 */

public class SetActivity extends BaseActivity implements ISetView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.sc_system)
    SwitchCompat scSystem;
    @BindView(R.id.sc_each)
    SwitchCompat scEach;
    @BindView(R.id.it_cache)
    ItemView itCache;

    private SetPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        init();
        getData();
    }

    private void init() {
        tvFocusHouse.setText("设置");
        itCache.setLableTwo(CleanMessageUtil.getTotalCacheSize(this));
        itCache.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAlert();
            }
        });

        scSystem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mPresenter != null) {
                        mPresenter.setSystemNotice("0");
                    }
                } else {
                    if (mPresenter != null) {
                        mPresenter.setSystemNotice("1");
                    }
                }
            }
        });

        scEach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mPresenter != null) {
                        mPresenter.setMessage("0");
                    }
                } else {
                    mPresenter.setMessage("1");
                }
            }
        });
    }

    public void getData() {
        if (mPresenter == null) {
            mPresenter = new SetPresenter(this);
        }
        mPresenter.getUserSettingInfo();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done, R.id.it_aboutus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_done:
                exitApp();
                break;
            case R.id.it_aboutus:
                aboutUs();
                break;
        }
    }

    private void aboutUs() {
        Intent intent = new Intent(this, DealActivity.class);
        intent.putExtra("type", 2);
        startActivity(intent);
    }

    private void exitApp() {
        NoticeDelDialog dialog = new NoticeDelDialog(this);
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                exitNet();
            }
        });
        dialog.show();
    }

    private void exitNet() {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).exitApp(), new NoTimeOutSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                SharedPrefsUtil.clearUserInfo();
                EventBusUserInfo param = new EventBusUserInfo();
                param.isResfreh = true;
                param.isResfrehDongtai = true;
                EventBus.getDefault().post(param);
                finish();
            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showFalseToast(SetActivity.this, e != null ? e.getMessage() : "退出失败，稍后重试");
            }
        });
    }


    @Override
    public void showUserSettingInfo(UserSettingInfoResponse response) {
        if (response != null && response.getData() != null) {
            if (response.getData().getReceiveNotice() == 0) {
                scSystem.setChecked(true);
            } else if (response.getData().getReceiveNotice() == 1) {
                scSystem.setChecked(false);
            }

            if (response.getData().getReceiveNews() == 0) {
                scEach.setChecked(true);
            } else if (response.getData().getReceiveNews() == 1) {
                scEach.setChecked(false);
            }
        }
    }

    @Override
    public void setSystemNotice(boolean success) {
    }

    @Override
    public void setMessageNotice(boolean success) {

    }

    void showDeleteAlert() {
        NoticeDelDialog n = new NoticeDelDialog(context);
        n.setTitle("清除缓存");
        n.setMessage("确认删除所有缓存？");
        n.setPositiveBtn("确认");
        n.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                CleanMessageUtil.clearAllCache(context);
                ToastUtil.showTrueToast(context, "清除完成");
                itCache.setLableTwo(CleanMessageUtil.getTotalCacheSize(context));
            }
        });
        n.show();
    }
}
