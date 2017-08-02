package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/5.
 */

public class HudongFragment extends Fragment {
    //    @BindView(R.id.tv_comment)
//    TextView tvComment;
//    @BindView(R.id.tv_me)
//    TextView tvMe;
//    @BindView(R.id.tv_fans)
//    TextView tvFans;
//    @BindView(R.id.tv_fav)
//    TextView tvFav;
    Unbinder unbinder;
    @BindView(R.id.tb)
    TabLayout tb;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
//
//    private EachCommentFragment eachCommentFragment; //评论
//    private FocusMeFragment focusMeFragment; //@我
//    private FansFragment fansFragment; //粉丝
//    private GetFavFragment getFavFragment;//获赞
    List<String> titles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fudong, null);
        unbinder = ButterKnife.bind(this, view);
//        initView();
        initView();
        return view;
    }

    private void initView() {
        titles.add("评论");
        titles.add("@我");
        titles.add("粉丝");
        titles.add("获赞");
//
//        tb.addTab(tb.newTab().setText("评论"));
//        tb.addTab(tb.newTab().setText("@我"));
//        tb.addTab(tb.newTab().setText("粉丝"));
//        tb.addTab(tb.newTab().setText("获赞"));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new EachCommentFragment());
        fragments.add(new FocusMeFragment());
        fragments.add(new FansFragment());
        fragments.add(new GetFavFragment());

        FragAdapter adapter = new FragAdapter(getChildFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        tb.setupWithViewPager(viewpager);
    }

//    private void initView() {
//        setSelect(1);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    @OnClick({R.id.tv_comment, R.id.tv_me, R.id.tv_fans, R.id.tv_fav})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_comment:
//                setSelect(1);
//                break;
//            case R.id.tv_me:
//                setSelect(2);
//                break;
//            case R.id.tv_fans:
//                setSelect(3);
//                break;
//            case R.id.tv_fav:
//                setSelect(4);
//                break;
//        }
//    }
//
//    private void setSelect(int index) {
//        tvComment.setSelected(false);
//        tvMe.setSelected(false);
//        tvFans.setSelected(false);
//        tvFav.setSelected(false);
//        if (index == 1) {
//            tvComment.setSelected(true);
//            if (eachCommentFragment == null) {
//                eachCommentFragment = new EachCommentFragment();
//            }
//
//            addFragment(eachCommentFragment);
//        } else if (index == 2) {
//            tvMe.setSelected(true);
//            if (focusMeFragment == null) {
//                focusMeFragment = new FocusMeFragment();
//            }
//            addFragment(focusMeFragment);
//        } else if (index == 3) {
//            tvFans.setSelected(true);
//            if (fansFragment == null) {
//                fansFragment = new FansFragment();
//            }
//            addFragment(fansFragment);
//        } else if (index == 4) {
//            tvFav.setSelected(true);
//            if (getFavFragment == null) {
//                getFavFragment = new GetFavFragment();
//            }
//            addFragment(getFavFragment);
//        }
//    }
//
//    protected void addFragment(Fragment fragment) {
//        String tag = fragment.getClass().getName();
//        FragmentManager fm = this.getChildFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        int count = fm.getBackStackEntryCount();
//        if (count >= 1) {
//
//            ft.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit);
//        }
//        if (fm.findFragmentByTag(tag) == null) {
//            ft.add(R.id.fl_content, fragment, tag);
//        }
//        ft.show(fragment);
//        List<Fragment> list = fm.getFragments();
//        if (list != null) {
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i) != null && list.get(i) != fragment) {
//                    ft.hide(list.get(i));
//                }
//            }
//        }
//        ft.addToBackStack(tag);
//        ft.commit();
//    }

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
