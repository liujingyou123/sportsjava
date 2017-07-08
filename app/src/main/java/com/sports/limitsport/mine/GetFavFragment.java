package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.R;
import com.sports.limitsport.mine.adapter.FocusMeAdapter;
import com.sports.limitsport.mine.adapter.GetFavAdapter;
import com.sports.limitsport.mine.model.FocusMe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/6.
 * 获赞
 */

public class GetFavFragment extends Fragment {
    @BindView(R.id.rl_focus)
    RecyclerView rlFocus;
    Unbinder unbinder;

    private GetFavAdapter adapter;
    private List<FocusMe> data = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_getfav, null);
        unbinder = ButterKnife.bind(this, view);
        testData();
        initView();
        return view;
    }

    private void testData() {
        for (int i = 0; i < 5; i++) {
            FocusMe dongtai = new FocusMe();
            data.add(dongtai);
        }
    }

    private void initView() {

        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_commentlist, null);
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlFocus.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new GetFavAdapter(data);
        adapter.bindToRecyclerView(rlFocus);

        adapter.setEmptyView(emptyView);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
