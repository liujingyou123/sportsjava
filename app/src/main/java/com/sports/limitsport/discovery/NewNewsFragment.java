package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.R;
import com.sports.limitsport.discovery.adapter.DongTaiAdapter;
import com.sports.limitsport.discovery.model.DongTai;
import com.sports.limitsport.view.NewNewsHeadView;
import com.sports.limitsport.view.SpacesItemDecorationS;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/29.
 * 动态
 */

public class NewNewsFragment extends Fragment {
    @BindView(R.id.rlv_new)
    RecyclerView rlvNew;
    Unbinder unbinder;

    private DongTaiAdapter adapter;
    private List<DongTai> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_news, null);
        unbinder = ButterKnife.bind(this, view);
        getTestData();
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        View headView = new NewNewsHeadView(this.getContext());
        rlvNew.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new DongTaiAdapter(data);
        adapter.bindToRecyclerView(rlvNew);
        adapter.addHeaderView(headView);
        SpacesItemDecorationS decoration = new SpacesItemDecorationS(5);
        rlvNew.addItemDecoration(decoration);

//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(ActivityFragment.this.getContext(), ActivityDetailActivity.class);
//                ActivityFragment.this.startActivity(intent);
//            }
//        });



    }

    private void getTestData() {
        DongTai act = new DongTai();
        act.imageUrl = "http://img2.imgtn.bdimg.com/it/u=4144902998,2125657744&fm=11&gp=0.jpg";
        data.add(act);

        DongTai act2 = new DongTai();
        act2.imageUrl = "http://pic.58pic.com/58pic/13/60/97/48Q58PIC92r_1024.jpg";
        data.add(act2);

        DongTai act3 = new DongTai();
        act3.imageUrl = "http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg";
        data.add(act3);

        for (int i = 0; i < 4; i++) {
            DongTai act4 = new DongTai();
            act4.imageUrl = "http://sc.jb51.net/uploads/allimg/150623/14-150623111Z1308.jpg";
            data.add(act4);
        }

        DongTai act5 = new DongTai();
        act5.imageUrl = "http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg";
        data.add(act5);

        for (int i = 0; i < 5; i++) {
            DongTai act4 = new DongTai();
            act4.imageUrl = "http://pic.58pic.com/58pic/13/60/97/48Q58PIC92r_1024.jpg";
            data.add(act4);
        }

        for (int i = 0; i < 4; i++) {
            DongTai act4 = new DongTai();
            act4.imageUrl = "http://sc.jb51.net/uploads/allimg/150623/14-150623111Z1308.jpg";
            data.add(act4);
        }

        for (int i = 0; i < 5; i++) {
            DongTai act6 = new DongTai();
            act6.imageUrl = "http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg";
            data.add(act6);

        }
    }
}
