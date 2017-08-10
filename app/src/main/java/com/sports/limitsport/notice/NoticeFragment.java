package com.sports.limitsport.notice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.AllShaiActivity;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.DongTaiOrRecommendResponse;
import com.sports.limitsport.model.RecomendFriendsListResponse;
import com.sports.limitsport.model.RecommendFriendsList;
import com.sports.limitsport.notice.adapter.MyNoticeDongTaiAdapter;
import com.sports.limitsport.notice.adapter.MyNoticeRecommendAdapter;
import com.sports.limitsport.notice.presenter.NoticePersonPresenter;
import com.sports.limitsport.notice.ui.INoticeView;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;
import com.sports.limitsport.view.NoticeViewHeaderView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/21.
 * 关注
 */

public class NoticeFragment extends BaseFragment implements INoticeView {
    @BindView(R.id.rlv_my_notice)
    RecyclerView rlvMyNotice;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.rl_refresh)
    RelativeLayout rlRefresh;
    Unbinder unbinder;

    private MyNoticeDongTaiAdapter adapter;
    private MyNoticeRecommendAdapter adapterRecommend;
    private NoticePersonPresenter mPresenter;

    private List<DongTaiList> data = new ArrayList<>();
    private List<RecommendFriendsList> dataR = new ArrayList<>();

    private int totalSize;
    private int pageNumber = 1;
    private int selectId;
    private CommentDialog commentDialog;
    private int type;//1:推荐用户列表 2:关注人的动态列表

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, null);
        unbinder = ButterKnife.bind(this, view);
//        initView();
//        initEmptyView();
        getData();
        return view;
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new NoticePersonPresenter(this);
        }
//        mPresenter.getFocusPersonDongTai(pageNumber);
//        mPresenter.getRecommendFriends(pageNumber);
        mPresenter.getRecommendOrDongTai(pageNumber);
    }

    private void initView() {
        NoticeViewHeaderView headerView = new NoticeViewHeaderView(getContext());
        headerView.setCloseClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeAllHeaderView();
            }
        });
        headerView.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFucusFriends();
            }
        });
        rlvMyNotice.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new MyNoticeDongTaiAdapter(data);
        adapter.bindToRecyclerView(rlvMyNotice);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());
        adapter.addHeaderView(headerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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
                        if (view.getId() == R.id.tv_focus) {
                            if (dongTaiList.getAttentionFlag() == 0) {
                                if (mPresenter != null) {
                                    mPresenter.foucesFans(dongTaiList.getPublishUserId() + "", dongTaiList.getId() + "");
                                }
                            } else if (dongTaiList.getAttentionFlag() == 1) {
                                Intent intent = new Intent(getContext(), PersonInfoActivity.class);
                                intent.putExtra("userId", dongTaiList.getPublishUserId() + "");
                                startActivity(intent);
                            }

                        } else if (view.getId() == R.id.rl_perison) {
                            Intent intent = new Intent(getContext(), PersonInfoActivity.class);
                            intent.putExtra("userId", dongTaiList.getPublishUserId() + "");
                            startActivity(intent);
                        } else if (view.getId() == R.id.imv_pinglun) {
                            selectId = dongTaiList.getId();
                            commentDialog.show();
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
                        } else if (view.getId() == R.id.imv_report) {
                            ReportDialog reportDialog = new ReportDialog(getContext(), "2", dongTaiList.getId() + "");
                            reportDialog.show();
                        }
                    }
                }

            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlvMyNotice);
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

        commentDialog = new CommentDialog(getContext());

        commentDialog.setOkDoneListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog.dismiss();
                mPresenter.publishActivityComment(selectId + "", commentDialog.getContent());

            }
        });
    }


    private void loadMore() {
        if (mPresenter != null) {
            pageNumber++;
            if (type == 1) {
                mPresenter.getRecommendFriends(pageNumber);
            } else if (type == 2) {
                mPresenter.getFocusPersonDongTai(pageNumber);
            }
        }
    }

    private void refresh() {
        pageNumber = 1;
        if (mPresenter != null) {
            if (type == 1) {
                mPresenter.getRecommendFriends(pageNumber);
            } else if (type == 2) {
                mPresenter.getFocusPersonDongTai(pageNumber);
            }
        }
    }


    /**
     * 没有关注的时候(推荐的人)
     */
    private void initEmptyView() {
        NoticeViewHeaderView headerView = new NoticeViewHeaderView(getContext());
        headerView.setEmpty();
        headerView.setCloseClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterRecommend.removeAllHeaderView();
            }
        });
        rlvMyNotice.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapterRecommend = new MyNoticeRecommendAdapter(dataR);
        adapterRecommend.bindToRecyclerView(rlvMyNotice);
        adapterRecommend.setLoadMoreView(new CustomLoadMoreNoEndView());
        adapterRecommend.addHeaderView(headerView);
        adapterRecommend.disableLoadMoreIfNotFullPage();
        adapterRecommend.setEnableLoadMore(true);
        adapterRecommend.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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
                    RecommendFriendsList recommendFriendsList = (RecommendFriendsList) adapter.getItem(position);
                    if (recommendFriendsList != null) {
                        if (0 == recommendFriendsList.getAttentionFlag()) {
                            if (mPresenter != null) {
                                mPresenter.foucesFansRecommend(recommendFriendsList.getId() + "");
                            }
                        } else {
                            Intent intent = new Intent(getContext(), PersonInfoActivity.class);
                            intent.putExtra("userId", recommendFriendsList.getId() + "");
                            startActivity(intent);
                        }
                    }
                }

            }
        });
        adapterRecommend.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlvMyNotice);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.imv_focus_house_back, R.id.imv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                gotoFucusFriends();
                break;
            case R.id.imv_right:
                Intent intent = new Intent(this.getContext(), EditNewDongTaiActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 关注好友页面
     */
    private void gotoFucusFriends() {
        Intent intent = new Intent(NoticeFragment.this.getContext(), FindMoreActivity.class);
        startActivity(intent);
    }

    private void gotoPersionInfo() {
        Intent intent = new Intent(NoticeFragment.this.getContext(), PersonInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void dongTaiList(DongTaiListResponse response) {
        if (response != null && response.getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {
                refreshTipVisible();
                data.clear();
                data.addAll(response.getData().getData());
                adapter.notifyDataSetChanged();
                rlAll.refreshComplete();
                adapter.loadMoreComplete();
            } else {
                adapter.addData(response.getData().getData());
                if (adapter.getData().size() >= totalSize) {
                    adapter.loadMoreEnd();
                } else {
                    adapter.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (rlAll.isRefreshing()) {
            rlAll.refreshComplete();
        } else {
            if (type == 1) {
                if (adapterRecommend.isLoading()) {
                    adapterRecommend.loadMoreFail();
                }
            } else if (type ==2){
                if (adapter.isLoading()) {
                    adapter.loadMoreFail();
                }
            }

        }
    }

    @Override
    public void onFocusReslult(boolean b, String id) {
        if (b) {
            ToastUtil.showTrueToast(getContext(), "关注成功");
            doFuces(id);
        } else {
            ToastUtil.showTrueToast(getContext(), "关注失败");
        }

    }

    @Override
    public void showPublishActivityComent(boolean b) {
        if (b) {
            ToastUtil.showTrueToast(getContext(), "评论成功");
            doComment();
            commentDialog.setContent("");
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

    @Override
    public void showFriendsList(RecomendFriendsListResponse response) {
        if (response != null && response.getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {
                refreshTipVisible();
                dataR.clear();
                dataR.addAll(response.getData().getData());
                adapterRecommend.notifyDataSetChanged();
                rlAll.refreshComplete();
            } else {
                adapterRecommend.addData(response.getData().getData());
                if (adapterRecommend.getData().size() >= totalSize) {
                    adapterRecommend.loadMoreEnd();
                } else {
                    adapterRecommend.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void showRecommendOrDongTai(DongTaiOrRecommendResponse response) {
        if (response != null && response.getData() != null && response.isSuccess()) {
//            XLog.json(response.getData().getPage().toString());
            type = response.getData().getType();
            if (type == 1) { //推荐
                ArrayList<RecommendFriendsList> list = ToolsUtil.jsonToArrayList(ToolsUtil.toJsonStringWithIgnore(response.getData().getPage().getData()), RecommendFriendsList.class);
                if (list != null) {
                    dataR.addAll(list);
                }
                initEmptyView();

            } else if (type == 2) {

                ArrayList<DongTaiList> list = ToolsUtil.jsonToArrayList(ToolsUtil.toJsonStringWithIgnore(response.getData().getPage().getData()), DongTaiList.class);
                if (list != null) {
                    data.addAll(list);
                }
                initView();
            }
        }
    }

    private void doPraise() {
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (selectId == adapter.getData().get(i).getId()) {
                int num = adapter.getData().get(i).getPraiseNum();
                if ("1".equals(adapter.getData().get(i).getPraiseFlag())) {
                    if (num > 0) {
                        num--;
                    }
                    adapter.getData().get(i).setPraiseFlag("0");
                } else {
                    num++;
                    adapter.getData().get(i).setPraiseFlag("1");
                }
                adapter.getData().get(i).setPraiseNum(num);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    private void doComment() {
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (selectId == adapter.getData().get(i).getId()) {
                DongTaiList.CommentListBean commentListBean = new DongTaiList.CommentListBean();
                commentListBean.setCommentatorName(SharedPrefsUtil.getUserInfo().getData().getName());
                commentListBean.setContent(commentDialog.getContent());

                List<DongTaiList.CommentListBean> listBeen = adapter.getData().get(i).getCommentList();
                if (listBeen == null) {
                    listBeen = new ArrayList<>();
                }
                listBeen.add(0, commentListBean);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    private void doFuces(String mid) {
        if (type == 1) {
            for (int i = 0; i < adapterRecommend.getData().size(); i++) {
                if (mid.equals(adapterRecommend.getData().get(i).getId() + "")) {
                    adapterRecommend.getData().get(i).setAttentionFlag(1);
                    adapterRecommend.notifyDataSetChanged();
                    break;
                }
            }
        } else if (type == 2) {
            for (int i = 0; i < adapter.getData().size(); i++) {
                if (mid.equals(adapter.getData().get(i).getId() + "")) {
                    adapter.getData().get(i).setAttentionFlag(1);
                    adapter.notifyDataSetChanged();
                    break;
                }
            }
        }

    }

    private void refreshTipVisible() {
        rlRefresh.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rlRefresh.setVisibility(View.GONE);
            }
        }, 1000);
    }
}
