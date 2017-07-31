package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.mine.model.EventBusUserInfo;
import com.sports.limitsport.mine.presenter.SetPresenter;
import com.sports.limitsport.mine.ui.ISetView;
import com.sports.limitsport.model.UserSettingInfoResponse;
import com.sports.limitsport.util.CleanMessageUtil;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
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
    }

    public void getData() {
        if (mPresenter == null) {
            mPresenter = new SetPresenter(this);
        }
        mPresenter.getUserSettingInfo();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_done:
                exitApp();
                break;
        }
    }

    private void exitApp() {
        NoticeDelDialog dialog = new NoticeDelDialog(this);
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                SharedPrefsUtil.clearUserInfo();
                EventBusUserInfo param = new EventBusUserInfo();
                param.isResfreh = true;
                param.isResfrehDongtai = true;
                EventBus.getDefault().post(param);
                finish();
            }
        });
        dialog.show();
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
