package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.DongTaiDetailActivity;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.dialog.DelAndReportDialog;
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.mine.adapter.MineAdapter;
import com.sports.limitsport.mine.model.EventBusUserInfo;
import com.sports.limitsport.mine.presenter.MinePresenter;
import com.sports.limitsport.mine.ui.IMineView;
import com.sports.limitsport.model.DongTaiDetailResponse;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.EventBusLogin;
import com.sports.limitsport.model.NewNoticeResponse;
import com.sports.limitsport.model.TokenTimeOutEvent;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.H5Address;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;
import com.sports.limitsport.view.MineHeaderView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class MineFragment extends BaseFragment implements IMineView {
    @BindView(R.id.ry_mine)
    RecyclerView ryMine;
    Unbinder unbinder;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private MineAdapter mineAdapter;
    private List<DongTaiList> data = new ArrayList<>();

    private MineHeaderView headerView;
    private MinePresenter mPresenter;
    private boolean isGetData = true;
    private int pageNumber = 1;
    private CommentDialog commentDialog;
    private int selectId;
    private int totalSize;
    private ShareDialog shareDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        unbinder = ButterKnife.bind(this, view);
        initView();

        if (mPresenter == null) {
            mPresenter = new MinePresenter(this);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.e("onResume");
        XLog.e("isGetData = " + isGetData);
        if (isGetData) {
            isGetData = false;
            getUserInfoData();
        }

        if (SharedPrefsUtil.getUserInfo() != null) {
            if (mPresenter != null) {
                mPresenter.getNewNotice();
            }
        }
    }

    @Subscribe
    public void getLoginIn(EventBusLogin param) {
        XLog.e("param.isLogin = " + param.isLogin);
        if (param != null && param.isLogin) {
            isGetData = param.isLogin;
        }
    }

    @Subscribe
    public void getUserEdit(EventBusUserInfo param) {
        if (param != null && param.isResfreh) {
            isGetData = param.isResfreh;
        }
    }

    @Subscribe
    public void isTimeOut(TokenTimeOutEvent param) {
        if (param != null) {
            isGetData = true;
        }
    }

    private void getUserInfoData() {
        if (mPresenter == null) {
            mPresenter = new MinePresenter(this);
        }

        rlAll.setEnablePullToRefresh(false);
        if (SharedPrefsUtil.getUserInfo() != null) {
            if (SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 0) {
                mPresenter.getUserInfo();
                pageNumber = 1;
                mPresenter.getDongTaiList(pageNumber);
                rlAll.setEnablePullToRefresh(true);
            } else if (SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                if (headerView != null) {
                    headerView.setType(2);
                }

                if (mineAdapter != null && mineAdapter.getData().size() > 0) {
                    mineAdapter.getData().clear();
                    mineAdapter.notifyDataSetChanged();
                }
            }
        } else {
            if (headerView != null) {
                headerView.setType(0);
            }
            if (mineAdapter != null && mineAdapter.getData().size() > 0) {
                mineAdapter.getData().clear();
                mineAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initView() {
        headerView = new MineHeaderView(this.getContext());
        ryMine.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        mineAdapter = new MineAdapter(data);
        mineAdapter.addHeaderView(headerView);
        mineAdapter.setHeaderAndEmpty(true);
        mineAdapter.bindToRecyclerView(ryMine);
        mineAdapter.setEmptyView(R.layout.empty_dongtai);

        mineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DongTaiList dongTaiList = (DongTaiList) adapter.getItem(position);
                if (dongTaiList != null) {
                    gotoDongtaiDetail(dongTaiList.getId() + "");
                }
            }
        });

        mineAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
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
                    DongTaiList dongTaiList = (DongTaiList) adapter.getItem(position);
                    if (dongTaiList != null) {
                        if (view.getId() == R.id.imv_pinglun) {
                            selectId = dongTaiList.getId();
                            showCommentDialog();
                        } else if (view.getId() == R.id.tv_san || view.getId() == R.id.imv_zan) {
                            selectId = dongTaiList.getId();
                            if ("1".equals(dongTaiList.getPraiseFlag())) { //1:已点赞 0:未点赞
                                if (mPresenter != null) {
                                    mPresenter.cancelPraise(dongTaiList.getId() + "");
                                }
                            } else {
                                if (mPresenter != null) {
                                    mPresenter.praise(dongTaiList.getId() + "");
                                }
                            }
                        } else if (view.getId() == R.id.imv_share) {
                            if (shareDialog == null) {
                                shareDialog = new ShareDialog(MineFragment.this.getActivity());
                            }
                            if (!shareDialog.isShowing() && dongTaiList != null) {
                                shareDialog.setTitle(dongTaiList.getPublishUserName() + "发布的精彩秀");
                                shareDialog.setDes(dongTaiList.getContent());
                                if ("1".equals(dongTaiList.getResourceType())) {
                                    shareDialog.setImage(dongTaiList.getImgUrl());
                                } else {
                                    shareDialog.setImage(dongTaiList.getVedioImgUrl());
                                }
                                shareDialog.setUrl(H5Address.getDongTai(dongTaiList.getId()+""));
                                shareDialog.show();
                            }

                        } else if (view.getId() == R.id.imv_report) {
                            DelAndReportDialog reportDialog = new DelAndReportDialog(MineFragment.this.getActivity(), "2", dongTaiList.getId() + "",dongTaiList.getPublishUserId()+"");
                            reportDialog.show();
                        }
                    }
                }

            }
        });
        mineAdapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        mineAdapter.disableLoadMoreIfNotFullPage();
        mineAdapter.setEnableLoadMore(true);
        mineAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, ryMine);
        rlAll.setEnableLoadMore(false);

        rlAll.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                XLog.e("onRefreshing");
                refresh();
            }
        });


    }

    private void showCommentDialog() {
        if (commentDialog == null) {
            commentDialog = new CommentDialog(getContext());

            commentDialog.setOkDoneListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentDialog.dismiss();
                    mPresenter.publishActivityComment(selectId + "", commentDialog.getContent());

                }
            });
        }

        commentDialog.show();

    }

    private void loadMore() {
        if (mPresenter != null) {
            pageNumber++;
            mPresenter.getDongTaiList(pageNumber);
        }
    }

    private void refresh() {
        pageNumber = 1;
        if (mPresenter != null) {
            mPresenter.getDongTaiList(pageNumber);
            getUserInfoData();
        }
    }


    /**
     * 前往动态详情页
     */
    private void gotoDongtaiDetail(String id) {
        Intent intent = new Intent(getContext(), DongTaiDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }


    @OnClick({R.id.imv_focus_house_back, R.id.imv_focus_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                Intent intent1 = new Intent(this.getContext(), SetActivity.class);
                startActivity(intent1);
                break;
            case R.id.imv_focus_right:
                Intent intent = new Intent(this.getContext(), BecomeBusinessActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.clear();
        }

        mPresenter = null;
    }

    @Override
    public void showUserInfo(UserInfoResponse response) {
        if (headerView != null) {
            headerView.setData(response);
        }
    }

    @Override
    public void showNewNotice(NewNoticeResponse response) {
        if (response != null && response.isSuccess() && response.getData() != null) {
            NewNoticeResponse.DataBean dataBean = response.getData();
            headerView.setNoticeNews(dataBean.getTotalUnRead() + "");
//            if (dataBean.getSystem() > 0 || dataBean.getActivity() > 0 || dataBean.getComment() > 0 || dataBean.getAite() > 0 || dataBean.getFans() > 0 || dataBean.getPraise() > 0) {
//            } else {
//                headerView.setNoticeNews("0");
//            }

        }
    }

    @Override
    public void showDongTaiList(DongTaiListResponse response) {
        if (response != null && response.getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {
                data.clear();
                data.addAll(response.getData().getData());
                mineAdapter.notifyDataSetChanged();
                rlAll.refreshComplete();
            } else {
                mineAdapter.addData(response.getData().getData());
                if (mineAdapter.getData().size() >= totalSize) {
                    mineAdapter.loadMoreEnd();
                } else {
                    mineAdapter.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (rlAll.isRefreshing()) {
            rlAll.refreshComplete();
        }
        mineAdapter.loadMoreFail();
    }

    @Override
    public void showPublishActivityComent(boolean b) {
        if (b) {
            ToastUtil.showTrueToast(getContext(), "评论成功");
            doComment();
            if (commentDialog != null) {
                commentDialog.setContent("");
            }
        } else {
            ToastUtil.showTrueToast(getContext(), "评论失败");
        }
    }

    @Override
    public void onPraiseResult(boolean b) {
        if (b) {
            doPraise();
        } else {
            ToastUtil.showTrueToast(getContext(), "点赞失败");
        }
    }

    @Override
    public void onCancelPraiseResult(boolean b) {
        if (b) {
            doPraise();
        } else {
            ToastUtil.showTrueToast(getContext(), "取消点赞失败");
        }
    }

    private void doPraise() {
        for (int i = 0; i < mineAdapter.getData().size(); i++) {
            if (selectId == mineAdapter.getData().get(i).getId()) {
                int num = mineAdapter.getData().get(i).getPraiseNum();
                if ("1".equals(mineAdapter.getData().get(i).getPraiseFlag())) {
                    if (num > 0) {
                        num--;
                    }
                    mineAdapter.getData().get(i).setPraiseFlag("0");
                } else {
                    num++;
                    mineAdapter.getData().get(i).setPraiseFlag("1");
                }
                mineAdapter.getData().get(i).setPraiseNum(num);
                mineAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    private void doComment() {
        for (int i = 0; i < mineAdapter.getData().size(); i++) {
            if (selectId == mineAdapter.getData().get(i).getId()) {
                DongTaiList.CommentListBean commentListBean = new DongTaiList.CommentListBean();
                commentListBean.setCommentatorName(SharedPrefsUtil.getUserInfo().getData().getName());
                if (commentDialog != null) {
                    commentListBean.setContent(commentDialog.getContent());
                }
                List<DongTaiList.CommentListBean> listBeen = mineAdapter.getData().get(i).getCommentList();
                if (listBeen == null) {
                    listBeen = new ArrayList<>();
                }
                listBeen.add(0, commentListBean);
                mineAdapter.notifyDataSetChanged();
                break;
            }
        }
    }
}