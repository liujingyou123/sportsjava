package com.sports.limitsport.mine;

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
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.MyCollectDongTaiAdapter;
import com.sports.limitsport.util.MyTestData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class MyCollectDongTaiFragment extends Fragment {
    @BindView(R.id.rlcv)
    RecyclerView rlcv;
    Unbinder unbinder;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private MyCollectDongTaiAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycollectdongtai, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_commentlist, null);
        TextView tvTip = (TextView) emptyView.findViewById(R.id.tv_empty);
        TextView tvGo = (TextView) emptyView.findViewById(R.id.tv_go);
        tvTip.setText("好像什么都没有～");
        tvGo.setText("去逛逛");
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlcv.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new MyCollectDongTaiAdapter(MyTestData.getData());
        adapter.bindToRecyclerView(rlcv);

        adapter.setEmptyView(emptyView);

//        adapter.setUpFetchEnable(true);
//
//        adapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
//            @Override
//            public void onUpFetch() {
//                XLog.e("onUpFetch");
//                adapter.setUpFetching(true);
//            }
//        });
//
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
            }
        }, rlcv);
        rlAll.setEnableLoadMore(false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
