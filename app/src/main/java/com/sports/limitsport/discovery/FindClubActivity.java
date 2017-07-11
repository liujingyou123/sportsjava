package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.discovery.adapter.FindClubAdapter;
import com.sports.limitsport.discovery.model.Club;
import com.sports.limitsport.discovery.model.FindClubSection;
import com.sports.limitsport.view.SpacesItemHDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 */

public class FindClubActivity extends BaseActivity {
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_clubs)
    RecyclerView rlClubs;

    private FindClubAdapter adapter;
    private List<FindClubSection> clubs = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findclub);
        ButterKnife.bind(this);
        initView();
        showClubs();
    }

    private void initView() {
        tvFocusHouse.setText("发现俱乐部");
        tvFocusRight.setText("创建");
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_right:
                break;
        }
    }

    private void showClubs() {
        for (int i = 0; i < 30; i++) {
            Club club = new Club();
            FindClubSection findClubSection = new FindClubSection(club);
            if (i == 0) {
                findClubSection.isHeader = true;
                findClubSection.header = "今日推荐";
            } else if (i == 5) {
                findClubSection.isHeader = true;
                findClubSection.header = "全部俱乐部";
            }

            clubs.add(findClubSection);
        }
        rlClubs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new FindClubAdapter(clubs);
        rlClubs.setAdapter(adapter);


        SpacesItemHDecoration decoration = new SpacesItemHDecoration(16);
        rlClubs.addItemDecoration(decoration);
    }
}
