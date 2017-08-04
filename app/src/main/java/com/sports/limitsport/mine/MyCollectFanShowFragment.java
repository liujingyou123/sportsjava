package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.FineShowDetailActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.MyCollectFanShowAdapter;
import com.sports.limitsport.mine.presenter.MyCollectFanShowPresenter;
import com.sports.limitsport.mine.ui.IMyCollectFineShowView;
import com.sports.limitsport.model.FineShowList;
import com.sports.limitsport.model.MyCollectFineShowResponse;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/7.
 * 我的收藏 精彩秀
 */

public class MyCollectFanShowFragment extends Fragment implements IMyCollectFineShowView {
    @BindView(R.id.rclv)
    RecyclerView rclv;
    Unbinder unbinder;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private MyCollectFanShowAdapter adapter;
    private MyCollectFanShowPresenter mPresenter;
    private int pageNumber = 1;
    private List<FineShowList> data = new ArrayList<>();
    private int totail;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycollectfanshow, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getData();
        return view;
    }

    private void getData() {
        XLog.e("getData");
        if (mPresenter == null) {
            mPresenter = new MyCollectFanShowPresenter(this);
        }
        mPresenter.getFineShow(pageNumber);
    }

    private void initView() {
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_commentlist, null);
        TextView tvTip = (TextView) emptyView.findViewById(R.id.tv_empty);
        TextView tvGo = (TextView) emptyView.findViewById(R.id.tv_go);
        tvGo.setVisibility(View.GONE);
        tvTip.setText("好像什么都没有～");
        tvGo.setText("去逛逛");
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rclv.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new MyCollectFanShowAdapter(data);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FineShowList fineShowList = (FineShowList) adapter.getItem(position);
                Intent intent = new Intent(getContext(), FineShowDetailActivity.class);
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

    @Override
    public void showFineShow(MyCollectFineShowResponse response) {
        if (response != null && response.getData() != null) {
            if (rlAll.isRefreshing()) {
                data.clear();
                data.addAll(response.getData().getData());
                adapter.notifyDataSetChanged();
                rlAll.refreshComplete();
            } else {
                adapter.addData(response.getData().getData());
                if (response.getData() == null || response.getData().getData().size() >= totail) {
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
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.clear();
        }
        mPresenter = null;
    }
}
