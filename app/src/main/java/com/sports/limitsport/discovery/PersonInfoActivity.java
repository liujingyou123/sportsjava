package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.mine.adapter.MineAdapter;
import com.sports.limitsport.mine.model.Dongtai;
import com.sports.limitsport.view.PersonInfoHeaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 个人主页
 */

public class PersonInfoActivity extends BaseActivity {
    @BindView(R.id.ry_person)
    RecyclerView ryPerson;
    private List<Dongtai> data = new ArrayList<>();
    private MineAdapter mineAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);
        ButterKnife.bind(this);
        testData();
        initView();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_report, R.id.imv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_report:
                ReportDialog dialog = new ReportDialog(this);
                dialog.show();
                break;
            case R.id.imv_share:
                break;
        }
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

    private void initView() {

        View headerView = new PersonInfoHeaderView(this);

        ryPerson.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mineAdapter = new MineAdapter(data);
        mineAdapter.addHeaderView(headerView);
        mineAdapter.setHeaderAndEmpty(true);
        mineAdapter.bindToRecyclerView(ryPerson);

        mineAdapter.setEmptyView(R.layout.empty_dongtai_two);
    }
}
