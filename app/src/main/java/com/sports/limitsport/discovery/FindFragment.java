package com.sports.limitsport.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.AllShaiActivity;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.discovery.adapter.SlidingTabPagerAdapter;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.notice.EditNewDongTaiActivity;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.SlidingTagPagerItem;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class FindFragment extends BaseFragment {
    @BindView(R.id.id_tab)
    SlidingTabLayout idTab;
    Unbinder unbinder;
    @BindView(R.id.id_view_pager)
    ViewPager idViewPager;
    private List<SlidingTagPagerItem> mTab = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTab.add(new FindPageItem("头条", "0"));
        mTab.add(new FindPageItem("动态", "1"));
        idViewPager.setAdapter(new SlidingTabPagerAdapter(this.getChildFragmentManager(), mTab));
        idTab.setViewPager(idViewPager, UnitUtil.dip2px(this.getContext(), 134));
//        idViewPager.setCurrentItem(0);
    }

    @OnClick({R.id.imv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_right:
                gotoEditDongTai();
                break;
            case R.id.id_tab:
                break;
        }
    }

    /**
     * 编辑动态页
     */
    private void gotoEditDongTai() {
        if (SharedPrefsUtil.getUserInfo() == null) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("type", "2");
            startActivity(intent);
            return;
        } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
            Intent intent = new Intent(getContext(), IdentifyMainActivity.class);
            intent.putExtra("type", "2");
            startActivity(intent);
        } else {
            Intent intent = new Intent(getContext(), EditNewDongTaiActivity.class);
            getContext().startActivity(intent);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}