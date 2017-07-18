package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ActivitysAdapter;
import com.sports.limitsport.activity.model.Act;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.view.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class ActivityFragment extends BaseFragment {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.rl_nodata)
    RelativeLayout rlNodata;

    private List<Act> data = new ArrayList<>();
    private ActivitysAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, null);
        unbinder = ButterKnife.bind(this, view);
        getTestData();
        init();
        return view;
    }

    private void init() {
        tvFocusHouse.setText("极限领秀");
        imvFocusHouseBack.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new ActivitysAdapter(data);
        adapter.bindToRecyclerView(recyclerView);

        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.end_list, null);
        adapter.addFooterView(footerView);

        SpacesItemDecoration decoration = new SpacesItemDecoration(5);
        recyclerView.addItemDecoration(decoration);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ActivityFragment.this.getContext(), ActivityDetailActivity.class);
                ActivityFragment.this.startActivity(intent);
            }
        });


    }

    private void getTestData() {
        Act act = new Act();
        act.imageUrl = "http://img2.imgtn.bdimg.com/it/u=4144902998,2125657744&fm=11&gp=0.jpg";
        data.add(act);

        Act act2 = new Act();
        act2.imageUrl = "http://pic.58pic.com/58pic/13/60/97/48Q58PIC92r_1024.jpg";
        data.add(act2);

        Act act3 = new Act();
        act3.imageUrl = "http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg";
        data.add(act3);

        for (int i = 0; i < 4; i++) {
            Act act4 = new Act();
            act4.imageUrl = "http://sc.jb51.net/uploads/allimg/150623/14-150623111Z1308.jpg";
            data.add(act4);
        }

        Act act5 = new Act();
        act5.imageUrl = "http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg";
        data.add(act5);

        for (int i = 0; i < 5; i++) {
            Act act4 = new Act();
            act4.imageUrl = "http://pic.58pic.com/58pic/13/60/97/48Q58PIC92r_1024.jpg";
            data.add(act4);
        }

        for (int i = 0; i < 4; i++) {
            Act act4 = new Act();
            act4.imageUrl = "http://sc.jb51.net/uploads/allimg/150623/14-150623111Z1308.jpg";
            data.add(act4);
        }

        for (int i = 0; i < 5; i++) {
            Act act6 = new Act();
            act6.imageUrl = "http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg";
            data.add(act6);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.imv_nonewdata)
    public void onViewClicked() {
        rlNodata.setVisibility(View.GONE);
    }
}