package com.sports.limitsport.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.main.adapter.HobbyAdapter;
import com.sports.limitsport.main.adapter.HobbySpaceItemDecoration;
import com.sports.limitsport.util.UnitUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class SelectOwnHobbyActivity extends BaseActivity {
    @BindView(R.id.rl_hobby)
    RecyclerView rlHobby;
    private HobbyAdapter adapter;
    private List<Hobby> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectownhabbits);
        ButterKnife.bind(this);
        testData();
        initView();
    }

    @OnClick({R.id.tv_skip, R.id.tv_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_skip:
                finish();
                break;
            case R.id.tv_done:
                finish();
                break;
        }
    }

    private void initView() {
        rlHobby.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new HobbyAdapter(mData);
        rlHobby.setAdapter(adapter);
        rlHobby.addItemDecoration(new HobbySpaceItemDecoration(UnitUtil.dip2px(this, 10)));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Hobby hobby = (Hobby) adapter.getItem(position);
                hobby.isChecked = !view.isSelected();
                view.setSelected(hobby.isChecked);
                if (view instanceof TextView) {
                    if (hobby.isChecked) {
                        ((TextView) view).setText("");
                    } else {
                        ((TextView) view).setText(hobby.name);
                    }
                }
            }
        });

    }

    private void testData() {
        for (int i = 0; i < 40; i++) {
            Hobby tmp = new Hobby();
            tmp.name = i + "";
            tmp.id = i;
            tmp.imgUrl = "http://up.qqjia.com/z/16/tu17317_45.png";
            mData.add(tmp);
        }
    }
}
