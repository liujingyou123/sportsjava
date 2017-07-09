package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.adapter.NewPersionAdapter;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.util.GlideImageLoader;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.view.HotNewHeadView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/29.
 */

public class HotNewsFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.rlv_new)
    RecyclerView rlvNew;
    private NewPersionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_news, null);

        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initView() {
        View headView = new HotNewHeadView(this.getContext());
        rlvNew.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new NewPersionAdapter(MyTestData.getData());
        adapter.addHeaderView(headView);
        adapter.setHeaderAndEmpty(true);
        adapter.bindToRecyclerView(rlvNew);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                XLog.e("onItemChildClick");
                view.setEnabled(false);
            }
        });
    }

}
