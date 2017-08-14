package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.AllShaiAdapter;
import com.sports.limitsport.activity.presenter.AllShaiPresenter;
import com.sports.limitsport.activity.ui.IAllShaiView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.notice.EditNewDongTaiActivity;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/23.
 * 大家都在晒
 */

public class AllShaiActivity extends BaseActivity implements IAllShaiView {
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ry_all)
    RecyclerView ryAll;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.view_nonet)
    RelativeLayout viewNonet;
    private AllShaiAdapter allShaiAdapter;
    private AllShaiPresenter mPresenter;
    private int pageNumber = 1;
    private String id;//活动ID
    private List<DongTaiList> data = new ArrayList<>();
    private int totalSize;
    private CommentDialog commentDialog;

    private int selectId; //动态ID；

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allshai);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new AllShaiPresenter(this);
        }

        rlAll.autoRefresh();
//        mPresenter.getAllShai(id, pageNumber);
    }

    private void initView() {
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        tvFocusHouse.setText("大家都在晒");
        initRy();
    }

    private void initRy() {
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_with_btn_full, null);
        emptyView.findViewById(R.id.tv_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoEditDongtai();
            }
        });
        ryAll.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        allShaiAdapter = new AllShaiAdapter(data);
        allShaiAdapter.bindToRecyclerView(ryAll);
        allShaiAdapter.setEmptyView(emptyView);
        allShaiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DongTaiList dongTaiList = (DongTaiList) adapter.getItem(position);
                if (dongTaiList != null) {
                    gotoDongtaiDetail(dongTaiList.getId() + "");
                }
            }
        });

        allShaiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (SharedPrefsUtil.getUserInfo() == null) {
                    Intent intent = new Intent(AllShaiActivity.this, LoginActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                    return;
                } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent = new Intent(AllShaiActivity.this, IdentifyMainActivity.class);
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
                                Intent intent = new Intent(AllShaiActivity.this, PersonInfoActivity.class);
                                intent.putExtra("userId", dongTaiList.getPublishUserId() + "");
                                startActivity(intent);
                            }

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
                            ReportDialog reportDialog = new ReportDialog(AllShaiActivity.this, "2", dongTaiList.getId() + "");
                            reportDialog.show();
                        }
                    }
                }

            }
        });

        allShaiAdapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        allShaiAdapter.disableLoadMoreIfNotFullPage();
        allShaiAdapter.setEnableLoadMore(true);
        allShaiAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, ryAll);
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
            mPresenter.getAllShai(id, pageNumber);
        }
    }

    private void refresh() {
        pageNumber = 1;
        if (mPresenter != null) {
            mPresenter.getAllShai(id, pageNumber);
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

    /**
     * 前往编辑动态页面
     */
    private void gotoEditDongtai() {
        Intent intent = new Intent(this, EditNewDongTaiActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_right, R.id.tv_reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_right:
                gotoEditDongtai();
                break;
            case R.id.tv_reload:
                viewNonet.setVisibility(View.GONE);
                rlAll.autoRefreshDelay();
                break;
        }
    }

    @Override
    public void showAllShiList(DongTaiListResponse response) {
        if (response != null && response.getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {
                data.clear();
                data.addAll(response.getData().getData());
                allShaiAdapter.setNewData(data);
//                allShaiAdapter.notifyDataSetChanged();
                rlAll.refreshComplete();
            } else {
                allShaiAdapter.addData(response.getData().getData());
                if (allShaiAdapter.getData().size() >= totalSize) {
                    allShaiAdapter.loadMoreEnd();
                } else {
                    allShaiAdapter.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (allShaiAdapter.isLoading()) {
            allShaiAdapter.loadMoreFail();
        } else {
            if (rlAll.isRefreshing()) {
                rlAll.refreshComplete();
            }
            if (e != null && (e.getClass().getName().equals(UnknownHostException.class.getName()) || e.getClass().getName().equals(SocketTimeoutException.class.getName()))) {
                viewNonet.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onFocusReslult(boolean b, String id) {
        if (b) {
            ToastUtil.showTrueToast(this, "关注成功");
            doFuces(id);
        } else {
            ToastUtil.showTrueToast(this, "关注失败");
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

    private void doPraise() {
        for (int i = 0; i < allShaiAdapter.getData().size(); i++) {
            if (selectId == allShaiAdapter.getData().get(i).getId()) {
                int num = allShaiAdapter.getData().get(i).getPraiseNum();
                if ("1".equals(allShaiAdapter.getData().get(i).getPraiseFlag())) {
                    if (num > 0) {
                        num--;
                    }
                    allShaiAdapter.getData().get(i).setPraiseFlag("0");
                } else {
                    num++;
                    allShaiAdapter.getData().get(i).setPraiseFlag("1");
                }
                allShaiAdapter.getData().get(i).setPraiseNum(num);
                allShaiAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    private void doComment() {
        for (int i = 0; i < allShaiAdapter.getData().size(); i++) {
            if (selectId == allShaiAdapter.getData().get(i).getId()) {
                DongTaiList.CommentListBean commentListBean = new DongTaiList.CommentListBean();
                commentListBean.setCommentatorName(SharedPrefsUtil.getUserInfo().getData().getName());
                commentListBean.setContent(commentDialog.getContent());

                List<DongTaiList.CommentListBean> listBeen = allShaiAdapter.getData().get(i).getCommentList();
                if (listBeen == null) {
                    listBeen = new ArrayList<>();
                }
                listBeen.add(0, commentListBean);
                allShaiAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    private void doFuces(String mid) {
        for (int i = 0; i < allShaiAdapter.getData().size(); i++) {
            if (mid.equals(allShaiAdapter.getData().get(i).getId() + "")) {
                allShaiAdapter.getData().get(i).setAttentionFlag(1);
                allShaiAdapter.notifyDataSetChanged();
                break;
            }
        }
    }
}
