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

import com.sports.limitsport.R;
import com.sports.limitsport.mine.adapter.MyCollectFanShowAdapter;
import com.sports.limitsport.util.MyTestData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/7.
 * 我的收藏 精彩秀
 */

public class MyCollectFanShowFragment extends Fragment {
    @BindView(R.id.rclv)
    RecyclerView rclv;
    Unbinder unbinder;
    private MyCollectFanShowAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycollectfanshow, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
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

        adapter = new MyCollectFanShowAdapter(null);
        adapter.bindToRecyclerView(rclv);

        adapter.setEmptyView(emptyView);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
