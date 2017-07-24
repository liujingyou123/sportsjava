package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.mine.adapter.MineAdapter;
import com.sports.limitsport.mine.model.Dongtai;
import com.sports.limitsport.view.MineHeaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.ry_mine)
    RecyclerView ryMine;
    Unbinder unbinder;
    private MineAdapter mineAdapter;
    private List<Dongtai> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        testData();
        initView();
        return view;
    }

    private void initView() {

        View headerView = new MineHeaderView(this.getContext());

        ryMine.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        mineAdapter = new MineAdapter(data);
        mineAdapter.addHeaderView(headerView);
        mineAdapter.setHeaderAndEmpty(true);
        mineAdapter.bindToRecyclerView(ryMine);
//        ryMine.setAdapter(mineAdapter);
        mineAdapter.setEmptyView(R.layout.empty_dongtai);
    }

    private void testData() {
        for (int i = 0; i < 5; i++) {
            Dongtai dongtai = new Dongtai();
            dongtai.imgUrl = "http://img1.juimg.com/160806/355860-160P620130540.jpg";
            dongtai.des = "如果有天堂，应该是这样的，做最棒的自己。";
            List<String> activitys = new ArrayList<>();
            activitys.add("松花湖滑雪第二期");
            activitys.add("南极探险题一起");
            activitys.add("被迹象学弟");

            List<String> recall = new ArrayList<>();
            recall.add("南极探险题一起");
            recall.add("被迹象学弟");
            dongtai.activitys = activitys;
            dongtai.recalls = recall;
            data.add(dongtai);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_focus_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                Intent intent1 = new Intent(this.getContext(), SetActivity.class);
                startActivity(intent1);
                break;
            case R.id.imv_focus_right:
                Intent intent = new Intent(this.getContext(), NoticeFirstLevelActivity.class);
                startActivity(intent);
                break;
        }
    }
}