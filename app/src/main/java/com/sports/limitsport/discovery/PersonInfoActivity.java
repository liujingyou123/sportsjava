package com.sports.limitsport.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.DongTaiDetailActivity;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.adapter.PersonInfoAdapter;
import com.sports.limitsport.discovery.presenter.PersonInfoPresenter;
import com.sports.limitsport.discovery.ui.IPersonInfoView;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;
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

public class PersonInfoActivity extends BaseActivity implements IPersonInfoView {
    @BindView(R.id.ry_person)
    RecyclerView ryPerson;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private PersonInfoAdapter mineAdapter;
    private String userId = "";
    private PersonInfoPresenter mPresenter;
    private PersonInfoHeaderView headerView;
    private int actSize = -1;
    private int clubSize = -1;
    private int pageNumber = 1;
    private CommentDialog commentDialog;
    private int selectId;
    private List<DongTaiList> data = new ArrayList<>();
    private int totalSize = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new PersonInfoPresenter(this);
        }
//        mPresenter.getUserInfo(userId);
//        mPresenter.getActivityList(userId);
//        mPresenter.getAllClubsList(userId);
        mPresenter.getDongTaiList(pageNumber, userId);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_report, R.id.imv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_report:
                ReportDialog dialog = new ReportDialog(this, "2", null);
                dialog.show();
                break;
            case R.id.imv_share:
                break;
        }
    }

    private void initView() {

        headerView = new PersonInfoHeaderView(this);

        ryPerson.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mineAdapter = new PersonInfoAdapter(data);
        mineAdapter.addHeaderView(headerView);
        mineAdapter.setHeaderAndEmpty(true);
        mineAdapter.bindToRecyclerView(ryPerson);

        mineAdapter.setEmptyView(R.layout.empty_dongtai_two);

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
                    Intent intent = new Intent(PersonInfoActivity.this, LoginActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                    return;
                } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent = new Intent(PersonInfoActivity.this, IdentifyMainActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                } else {
                    DongTaiList dongTaiList = (DongTaiList) adapter.getItem(position);
                    if (dongTaiList != null) {
                        if (view.getId() == R.id.imv_pinglun) {
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
                            ReportDialog reportDialog = new ReportDialog(PersonInfoActivity.this, "2", dongTaiList.getId() + "");
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
        }, ryPerson);
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

        commentDialog = new CommentDialog(this);

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
            mPresenter.getDongTaiList(pageNumber, userId);
        }
    }

    private void refresh() {
        pageNumber = 1;
        if (mPresenter != null) {
            mPresenter.getDongTaiList(pageNumber, userId);
        }
    }

    @Override
    public void showUserInfo(UserInfoResponse response) {
        if (headerView != null) {
            headerView.setData(response);
        }
    }

    @Override
    public void showActivityList(ActivityResponse response) {
        if (headerView != null) {
            headerView.setActivityList(response);
        }

        if (response != null && response.getData() != null && response.getData().getData() != null && response.getData().getTotalSize() > 0) {
            actSize = response.getData().getTotalSize();
        } else {
            actSize = 0;
        }
        checkIsEmpty();
    }

    @Override
    public void showClubsList(ClubListResponse response) {
        if (headerView != null) {
            headerView.setClubsList(response);
        }
        if (response != null && response.getData() != null && response.getData().getData() != null && response.getData().getTotalSize() > 0) {
            clubSize = response.getData().getTotalSize();
        } else {
            clubSize = 0;
        }
        checkIsEmpty();
    }

    @Override
    public void onError(Throwable e, String type) {
        if ("activity".equals(type)) {
            actSize = 0;
        } else if ("club".equals(type)) {
            clubSize = 0;
        } else if ("dongtai".equals(type)) {
            totalSize = 0;
        }
        checkIsEmpty();
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
        } else {
            totalSize = 0;
        }

        if (headerView != null) {
            headerView.setDongtaiSize(totalSize);
        }

        checkIsEmpty();
    }

    @Override
    public void onPraiseResult(boolean b) {
        if (b) {
            doPraise();
        } else {
            ToastUtil.showTrueToast(this, "点赞失败");
        }
    }

    @Override
    public void onCancelPraiseResult(boolean b) {
        if (b) {
            doPraise();
        } else {
            ToastUtil.showTrueToast(this, "取消点赞失败");
        }
    }

    @Override
    public void showPublishActivityComent(boolean b) {
        if (b) {
            ToastUtil.showTrueToast(this, "评论成功");
            doComment();
            commentDialog.setContent("");
        } else {
            ToastUtil.showTrueToast(this, "评论失败");
        }
    }

    private void checkIsEmpty() {
        if (actSize == 0 && clubSize == 0 && totalSize == 0) {
            if (headerView != null) {
                headerView.setDongtaiGone();
            }
        }
    }

    /**
     * 前往动态详情页
     */
    private void gotoDongtaiDetail(String id) {
        Intent intent = new Intent(this, DongTaiDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void doPraise() {
        for (int i = 0; i < mineAdapter.getData().size(); i++) {
            if (selectId == mineAdapter.getData().get(i).getId()) {
                int num = mineAdapter.getData().get(i).getPraiseNum();
                if ("1".equals(mineAdapter.getData().get(i).getPraiseFlag())) { // 已点赞
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
                commentListBean.setContent(commentDialog.getContent());

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }

        mPresenter = null;
    }
}
