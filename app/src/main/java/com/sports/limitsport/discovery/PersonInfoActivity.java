package com.sports.limitsport.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.presenter.PersonInfoPresenter;
import com.sports.limitsport.discovery.ui.IPersonInfoView;
import com.sports.limitsport.mine.adapter.MineAdapter;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.view.PersonInfoHeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 个人主页
 */

public class PersonInfoActivity extends BaseActivity implements IPersonInfoView {
    @BindView(R.id.ry_person)
    RecyclerView ryPerson;
    private MineAdapter mineAdapter;
    private String userId = "";
    private PersonInfoPresenter mPresenter;
    private PersonInfoHeaderView headerView;
    private int actSize = -1;
    private int clubSize = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new PersonInfoPresenter(this);
        }
        mPresenter.getUserInfo(userId);
        mPresenter.getActivityList(userId);
        mPresenter.getAllClubsList(userId);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_report, R.id.imv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_report:
                ReportDialog dialog = new ReportDialog(this, "2", null);
                dialog.show();
                break;
            case R.id.imv_share:
                break;
        }
    }

    private void initView() {

        headerView = new PersonInfoHeaderView(this);

        ryPerson.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mineAdapter = new MineAdapter(null);
        mineAdapter.addHeaderView(headerView);
        mineAdapter.setHeaderAndEmpty(true);
        mineAdapter.bindToRecyclerView(ryPerson);

        mineAdapter.setEmptyView(R.layout.empty_dongtai_two);
    }

    @Override
    public void showUserInfo(UserInfoResponse response) {
        if (headerView != null) {
            headerView.setData(response);
        }
    }

    @Override
    public void showActivityList(ActivityResponse response) {
        if (headerView != null) {
            headerView.setActivityList(response);
        }

        if (response != null && response.getData() != null && response.getData().getData() != null && response.getData().getTotalSize() > 0) {
            actSize = response.getData().getTotalSize();
        } else {
            actSize = 0;
        }
    }

    @Override
    public void showClubsList(ClubListResponse response) {
        if (headerView != null) {
            headerView.setClubsList(response);
        }
        if (response != null && response.getData() != null && response.getData().getData() != null && response.getData().getTotalSize() > 0) {
            clubSize = response.getData().getTotalSize();
        } else {
            clubSize = 0;
        }
    }

    @Override
    public void onError(Throwable e, String type) {
        if ("activity".equals(type)) {
            actSize = 0;
        } else if ("club".equals(type)) {
            clubSize = 0;
        }
    }

    private void checkIsEmpty() {
        if (actSize == 0 && clubSize == 0) {

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
