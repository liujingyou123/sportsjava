package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.mine.adapter.MyCollectActivityAdapter;
import com.sports.limitsport.util.MyTestData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/12.
 */

public class TabActivityFragment extends Fragment {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    Unbinder unbinder;
    private MyCollectActivityAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabactivity, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_noticelist, null);
        TextView tvTip = (TextView) emptyView.findViewById(R.id.tv_empty);
        tvTip.setText("还没有发布活动哦～");
        rlv.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new MyCollectActivityAdapter(MyTestData.getData());
        adapter.bindToRecyclerView(rlv);

        adapter.setEmptyView(R.layout.empty_noticelist);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
