package com.sports.limitsport.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.ActivityFragment;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.discovery.FindFragment;
import com.sports.limitsport.mine.MineFragment;
import com.sports.limitsport.notice.NoticeFragment;
import com.sports.limitsport.util.GlideImageLoader;
import com.sports.limitsport.view.BottomTabView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements BottomTabView.OnTabSelectedListener {
    public static final int ACTIVITY = 0;
    public static final int DISCOVERY = 1;
    public static final int NOTICE = 2;
    public static final int MINE = 3;
    @BindView(R.id.tabView)
    BottomTabView tabView;
    private FragmentManager fm;
    private GlideImageLoader glideImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fm = getSupportFragmentManager();
        tabView.setOnTabSelectedListener(this);
        tabView.setTabResIds(new int[]{R.drawable.selector_bottom_tab_activity
                , R.drawable.selector_bottom_tab_discovery, R.drawable
                .selector_bottom_tab_notice, R.drawable
                .selector_bottom_tab_mine});

        Intent intent = getIntent();
        if (intent == null) {
            onTabSelected(ACTIVITY);
        } else {
            int tab = intent.getIntExtra("tab", ACTIVITY);
            onTabSelected(tab);
        }

    }

    @Override
    public void onTabSelected(int index) {

        switch (index) {
            case ACTIVITY:
                handleActivity();
                break;
            case DISCOVERY:
                handleDiscovery();
                break;
            case NOTICE:
                handleNotice();
                break;
            case MINE:
                handleMine();
                break;
        }
    }

    private void handleActivity() {
        ActivityFragment homeFragment = (ActivityFragment) fm
                .findFragmentByTag(ActivityFragment.class.getName());
        if (homeFragment == null) {
            homeFragment = new ActivityFragment();
        }
        tabView.setIndicatorDisplay(0, true);
        addHomeFragment(homeFragment, true);
    }

    private void handleDiscovery() {
        FindFragment serviceFragment = (FindFragment) fm
                .findFragmentByTag(FindFragment.class.getName());
        if (serviceFragment == null) {
            serviceFragment = new FindFragment();
        }

        tabView.setIndicatorDisplay(1, true);
        addHomeFragment(serviceFragment, true);
    }

    private void handleNotice() {
        NoticeFragment tradeCircleFragment = (NoticeFragment) fm
                .findFragmentByTag(NoticeFragment.class.getName());
        if (tradeCircleFragment == null) {
            tradeCircleFragment = new NoticeFragment();
        }
        tabView.setIndicatorDisplay(2, true);
        addHomeFragment(tradeCircleFragment, false);
    }

    private void handleMine() {
        MineFragment mineFragment = (MineFragment) fm
                .findFragmentByTag(MineFragment.class.getName());
        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }
        tabView.setIndicatorDisplay(3, true);
        addHomeFragment(mineFragment, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        int tab = intent.getIntExtra("tab", ACTIVITY);
        onTabSelected(tab);
    }

    protected void addHomeFragment(BaseFragment fragment, boolean addToBackStack) {
        String tag = fragment.getClass().getName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int count = fm.getBackStackEntryCount();
        if (fm.findFragmentByTag(tag) == null) {
            ft.add(R.id.rl_fragment_content, fragment, tag);
        }
        ft.show(fragment);
        List<Fragment> list = fm.getFragments();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null && list.get(i) != fragment) {
                    ft.hide(list.get(i));
                }
            }
        }
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    public GlideImageLoader getGlideImageLoader() {
        if (glideImageLoader == null) {
            glideImageLoader = new GlideImageLoader();
        }

        return glideImageLoader;
    }

}
