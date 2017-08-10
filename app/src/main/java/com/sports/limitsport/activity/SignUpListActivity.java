package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.SignUpAdapter;
import com.sports.limitsport.activity.presenter.SignUpListPresenter;
import com.sports.limitsport.activity.ui.ISignUpListView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.SignUpListResponse;
import com.sports.limitsport.model.SignUpUser;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/23.
 * 报名列表
 */

public class SignUpListActivity extends BaseActivity implements ISignUpListView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_signup_list)
    RecyclerView rlSignupList;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;

    private SignUpAdapter adapter;
    private SignUpListPresenter mPresenter;
    private String id;//活动ID
    private List<SignUpUser> data = new ArrayList<>();
    private int pageNumber;
    private int totalSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuplist);
        ButterKnife.bind(this);
        getIntentData();
        initView();

        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new SignUpListPresenter(this);
        }
        mPresenter.getSignUpList(id, pageNumber);
    }

    private void initView() {
        tvFocusHouse.setText("报名列表");
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        initRy();
    }

    private void initRy() {
        rlSignupList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SignUpAdapter(data);
        adapter.bindToRecyclerView(rlSignupList);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                XLog.e("onItemChildClick");
                SignUpUser item = (SignUpUser) adapter.getItem(position);
                if (item != null) {
                    if ("0".equals(item.getStatus()) || "2".equals(item.getStatus())) { //0:互相不关注 1:我关注他 2:他关注我 3:互相关注
                        gotoPersonInfo(item.getId() + "");
                    }
                }
            }
        });

        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlSignupList);
        rlAll.setEnableLoadMore(false);

        rlAll.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                XLog.e("onRefreshing");
                refresh();
            }
        });
    }

    private void loadMore() {
        if (mPresenter != null) {
            pageNumber++;
            mPresenter.getSignUpList(id, pageNumber);
        }
    }

    private void refresh() {
        pageNumber = 1;
        if (mPresenter != null) {
            mPresenter.getSignUpList(id, pageNumber);
        }
    }


    @OnClick({R.id.imv_focus_house_back, R.id.imv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_share:
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
                break;
        }
    }

    @Override
    public void showSignUpList(SignUpListResponse response) {
        if (response != null && response.getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {
                data.clear();
                data.addAll(response.getData().getData());
                adapter.notifyDataSetChanged();
                rlAll.refreshComplete();
            } else {
                adapter.addData(response.getData().getData());
                if (adapter.getData().size() >= totalSize) {
                    adapter.loadMoreEnd();
                } else {
                    adapter.loadMoreComplete();
                }
            }
        }

    }

    @Override
    public void onError(Throwable e) {
        if (rlAll.isRefreshing()) {
            rlAll.refreshComplete();
        } else {
            adapter.loadMoreFail();
        }
    }

    /**
     * 进入个人主页
     */
    private void gotoPersonInfo(String id) {
        Intent intent = new Intent(this, PersonInfoActivity.class);
        intent.putExtra("userId", id);
        startActivity(intent);
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
