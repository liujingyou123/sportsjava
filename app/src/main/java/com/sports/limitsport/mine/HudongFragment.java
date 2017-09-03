package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sports.limitsport.R;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.EventBusAtMeNews;
import com.sports.limitsport.model.EventBusComment;
import com.sports.limitsport.model.EventBusFansNews;
import com.sports.limitsport.model.EventBusFavNews;
import com.sports.limitsport.model.EventBusNewHudong;
import com.sports.limitsport.model.NewNoticeResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/5.
 */

public class HudongFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.tb)
    TabLayout tb;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    List<String> titles = new ArrayList<>();
    @BindView(R.id.imv_at_tip)
    ImageView imvAtTip;
    @BindView(R.id.imv_fans_tip)
    ImageView imvFansTip;
    @BindView(R.id.imv_zan_tip)
    ImageView imvZanTip;
    @BindView(R.id.imv_comment_tip)
    ImageView imvCommentTip;
    private NewNoticeResponse.DataBean mDataBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fudong, null);
        unbinder = ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getBundleData();
        initView();
        return view;
    }

    private void getBundleData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mDataBean = (NewNoticeResponse.DataBean) bundle.getSerializable("notice");
        }
    }

    private void initView() {
        titles.add("评论");
        titles.add("@我");
        titles.add("粉丝");
        titles.add("获赞");

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new EachCommentFragment());
        fragments.add(new FocusMeFragment());
        fragments.add(new FansFragment());
        fragments.add(new GetFavFragment());

        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        tb.setupWithViewPager(viewpager);

        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                XLog.e("onTabSelected");
                String tag = tab.getText().toString();
                if (tag.equals("评论")) {
                    imvCommentTip.setVisibility(View.GONE);
                } else if (tag.equals("@我")) {
                    imvAtTip.setVisibility(View.GONE);
                } else if (tag.equals("粉丝")) {
                    imvFansTip.setVisibility(View.GONE);
                } else if (tag.equals("获赞")) {
                    imvZanTip.setVisibility(View.GONE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (mDataBean != null) {
            if (mDataBean.getComment() > 0) {
                imvCommentTip.setVisibility(View.VISIBLE);
            }

            if (mDataBean.getAite() > 0) {
                imvAtTip.setVisibility(View.VISIBLE);
            }

            if (mDataBean.getFans() > 0) {
                imvFansTip.setVisibility(View.VISIBLE);
            }

            if (mDataBean.getPraise() > 0) {
                imvZanTip.setVisibility(View.VISIBLE);
            }
        }
    }

    @Subscribe
    public void updateCommentNotice(EventBusComment param) {
        if (param != null) {
            if (!param.hasComments) {
                imvCommentTip.setVisibility(View.GONE);
            }
            isHasHudongNews();
        }

    }

    @Subscribe
    public void updateAtMeNotice(EventBusAtMeNews param) {
        if (param != null) {
            if (!param.hasNews) {
                imvAtTip.setVisibility(View.GONE);
            }
            isHasHudongNews();
        }
    }

    @Subscribe
    public void updateFansNotice(EventBusFansNews param) {
        if (param != null) {
            if (!param.hasFansNew) {
                imvFansTip.setVisibility(View.GONE);
            }
            isHasHudongNews();
        }
    }

    @Subscribe
    public void updateFavNotice(EventBusFavNews param) {
        if (param != null) {
            if (!param.hasFavNews) {
                imvZanTip.setVisibility(View.GONE);
            }
            isHasHudongNews();
        }
    }


    public boolean isHasHudongNews() {
        boolean ret = false;
        if (imvCommentTip.getVisibility() == View.GONE && imvAtTip.getVisibility() == View.GONE
                && imvFansTip.getVisibility() == View.GONE && imvZanTip.getVisibility() == View.GONE) {
            EventBusNewHudong eventBusNewHudong = new EventBusNewHudong();
            eventBusNewHudong.hasHuDongNews = false;
            EventBus.getDefault().post(eventBusNewHudong);
        } else {
            EventBusNewHudong eventBusNewHudong = new EventBusNewHudong();
            eventBusNewHudong.hasHuDongNews = true;
            EventBus.getDefault().post(eventBusNewHudong);
        }
        return ret;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    class FragAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
