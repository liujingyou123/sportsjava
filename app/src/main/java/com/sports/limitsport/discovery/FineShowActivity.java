package com.sports.limitsport.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.discovery.adapter.FineShowListAdapter;
import com.sports.limitsport.discovery.presenter.FineShowPresenter;
import com.sports.limitsport.discovery.ui.IFineShowView;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.FineShowList;
import com.sports.limitsport.model.MyCollectFineShowResponse;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 精彩秀列表
 */

public class FineShowActivity extends BaseActivity implements IFineShowView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rclv)
    RecyclerView rclv;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private FineShowListAdapter adapter;
    private FineShowPresenter mPresenter;
    private List<FineShowList> data = new ArrayList<>();
    private int pageNumber = 1;
    private int total;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fineshow);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new FineShowPresenter(this);
        }
        mPresenter.getFineShow(pageNumber);
    }

    private void initView() {
        tvFocusHouse.setText("精彩秀");
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_commentlist, null);
        TextView tvTip = (TextView) emptyView.findViewById(R.id.tv_empty);
        TextView tvGo = (TextView) emptyView.findViewById(R.id.tv_go);
        tvTip.setText("好像什么都没有～");
        tvGo.setVisibility(View.GONE);

        rclv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new FineShowListAdapter(data);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FineShowList fineShowList = (FineShowList) adapter.getItem(position);
                Intent intent = new Intent(FineShowActivity.this, FineShowDetailActivity.class);
                intent.putExtra("id", fineShowList.getId() + "");
                startActivity(intent);
            }
        });
        adapter.bindToRecyclerView(rclv);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setEmptyView(emptyView);

        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rclv);
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
            mPresenter.getFineShow(pageNumber);
        }
    }

    private void refresh() {
        pageNumber = 1;
        if (mPresenter != null) {
            mPresenter.getFineShow(pageNumber);
        }
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showFineShow(MyCollectFineShowResponse response) {
        if (response != null && response.getData() != null) {
            total = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {
                if (response.getData().getData() != null) {
                    data.clear();
                    data.addAll(response.getData().getData());
                    adapter.notifyDataSetChanged();
                }
                rlAll.refreshComplete();
            } else {
                if (response.getData().getData() != null) {
                    adapter.addData(response.getData().getData());
                    if (response.getData() == null || adapter.getData().size() >= total) {
                        adapter.loadMoreEnd();
                    } else {
                        adapter.loadMoreComplete();
                    }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }

        mPresenter = null;
    }
}
