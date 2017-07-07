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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.SignUpAdapter;
import com.sports.limitsport.activity.model.SignUpUser;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.FansAdapter;
import com.sports.limitsport.mine.model.Comment;
import com.sports.limitsport.mine.model.Fans;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/6.
 * 粉丝
 */

public class FansFragment extends Fragment {
    @BindView(R.id.rl_fans)
    RecyclerView rlFans;
    Unbinder unbinder;
    private FansAdapter adapter;
    private List<Fans> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fans, null);
        unbinder = ButterKnife.bind(this, view);
        testData();
        initView();
        return view;
    }

    private void initView() {
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_commentlist, null);
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlFans.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new FansAdapter(data);
        adapter.bindToRecyclerView(rlFans);

        adapter.setEmptyView(emptyView);


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                XLog.e("onItemChildClick");
                view.setEnabled(false);
                if (view instanceof TextView) {
                    ((TextView) view).setText("互相关注");
                }
            }
        });
    }

    private void testData() {
        for (int i = 0; i < 15; i++) {
            Fans dongtai = new Fans();
            data.add(dongtai);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
