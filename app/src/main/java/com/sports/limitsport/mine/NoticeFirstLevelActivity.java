package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.model.EventBusNewHudong;
import com.sports.limitsport.model.EventBusNewNotice;
import com.sports.limitsport.model.NewNoticeResponse;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    private NewNoticeResponse.DataBean mDataBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticefirstlevel);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        showFragment(1);
        getNewNotice();
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
                Bundle bundle = new Bundle();
                bundle.putSerializable("notice", mDataBean);
                hudongFragment.setArguments(bundle);
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

    private void getNewNotice() {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getNewNotice(), new LoadingNetSubscriber<NewNoticeResponse>() {
            @Override
            public void response(NewNoticeResponse response) {
                if (response != null && response.isSuccess() && response.getData() != null) {
                    NewNoticeResponse.DataBean dataBean = response.getData();
//                    dataBean.setActivity(1);
//                    dataBean.setAite(1);
//                    dataBean.setComment(1);
//                    dataBean.setFans(1);
//                    dataBean.setPraise(1);
//                    dataBean.setSystem(1);
                    mDataBean = dataBean;
                    if (dataBean.getSystem() > 0 || dataBean.getActivity() > 0) {
                        imvNoticeTip.setVisibility(View.VISIBLE);
                    }

                    if (dataBean.getComment() > 0 || dataBean.getAite() > 0 || dataBean.getFans() > 0 || dataBean.getPraise() > 0) {
                        imvHudongTip.setVisibility(View.VISIBLE);
                    }

                    EventBusNewNotice param = new EventBusNewNotice();
                    param.activity = dataBean.getActivity();
                    param.aite = dataBean.getAite();
                    param.comment = dataBean.getComment();
                    param.fans = dataBean.getFans();
                    param.praise = dataBean.getPraise();
                    param.system = dataBean.getSystem();
                    EventBus.getDefault().post(param);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    @Subscribe
    public void updateNoticeNews(EventBusNewHudong param) {
        if (param != null) {
            if (param.hasHuDongNews) {
                imvHudongTip.setVisibility(View.VISIBLE);
            } else {
                imvHudongTip.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
