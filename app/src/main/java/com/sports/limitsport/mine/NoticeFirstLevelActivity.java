package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/5.
 */

public class NoticeFirstLevelActivity extends BaseActivity {

    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.imv_notice_tip)
    ImageView imvNoticeTip;
    @BindView(R.id.tv_hudong)
    TextView tvHudong;
    @BindView(R.id.imv_hudong_tip)
    ImageView imvHudongTip;
    private NoticeSubFirstFragment noticeSubFirstFragment;
    private HudongFragment hudongFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticefirstlevel);
        ButterKnife.bind(this);
        showFragment(1);
    }

    private void showFragment(int type) {
        if (type == 1) {
            if (noticeSubFirstFragment == null) {
                noticeSubFirstFragment = new NoticeSubFirstFragment();
            }
            addFragment(noticeSubFirstFragment);

            tvNotice.setSelected(true);
            tvHudong.setSelected(false);
        } else if (type == 2) {
            if (hudongFragment == null) {
                hudongFragment = new HudongFragment();
            }
            addFragment(hudongFragment);
            tvNotice.setSelected(false);
            tvHudong.setSelected(true);
        }
    }


    protected void addFragment(Fragment fragment) {
        String tag = fragment.getClass().getName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int count = fm.getBackStackEntryCount();
        if (count >= 1) {

            ft.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit);
        }
        if (fm.findFragmentByTag(tag) == null) {
            ft.add(R.id.fl_content, fragment, tag);
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
        ft.addToBackStack(tag);
        ft.commit();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_notice, R.id.tv_hudong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_notice:
                showFragment(1);
                break;
            case R.id.tv_hudong:
                showFragment(2);
                break;
        }
    }
}
