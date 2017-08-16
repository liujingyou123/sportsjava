package com.sports.limitsport.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.DongTaiDetailActivity;
import com.sports.limitsport.activity.adapter.AllShaiAdapter;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.presenter.ClubHistoryPresenter;
import com.sports.limitsport.discovery.ui.IClubHistoryView;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.EventBusScroll;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/19.
 */

public class TabHistoryFragment extends Fragment implements IClubHistoryView {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    Unbinder unbinder;
    private AllShaiAdapter adapter;
    private List<DongTaiList> data = new ArrayList<>();
    private ClubHistoryPresenter mPresenter;
    private int pageNumber = 1;
    private String id;
    private CommentDialog commentDialog;
    private int selectId;
    private int totalSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgent_tab_history, null);
        unbinder = ButterKnife.bind(this, view);
        getBundle();
        initView();
        getData();
        return view;
    }

    private void getBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ClubHistoryPresenter(this);
        }
        mPresenter.getDongTaiList(id, pageNumber);
    }

    private void initView() {
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_noticelist, null);
        TextView tvTip = (TextView) emptyView.findViewById(R.id.tv_empty);
        tvTip.setText("还没有发布动态呢～");
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rlv.setLayoutManager(linearLayoutManager);
        adapter = new AllShaiAdapter(data);
        adapter.bindToRecyclerView(rlv);
        adapter.setEmptyView(emptyView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DongTaiList dongTaiList = (DongTaiList) adapter.getItem(position);
                if (dongTaiList != null) {
                    gotoDongtaiDetail(dongTaiList.getId() + "");
                }
            }
        });

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
                                intent.putExtra("userId", dongTaiList.getPublishUserId());
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
                            ReportDialog reportDialog = new ReportDialog(getContext(), "2", dongTaiList.getId() + "");
                            reportDialog.show();
                        }
                    }
                }

            }
        });

        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlv);

        commentDialog = new CommentDialog(getContext());

        commentDialog.setOkDoneListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog.dismiss();
                mPresenter.publishActivityComment(selectId + "", commentDialog.getContent());

            }
        });

//        rlv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//
//                    int visiblePosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
//                    if (visiblePosition == 0) {
//                        EventBus.getDefault().post(new EventBusScroll());
////                        barLayout.setExpanded(true, true);
//                    }
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
    }

    private void loadMore() {
        if (mPresenter != null) {
            pageNumber++;
            mPresenter.getDongTaiList(id, pageNumber);
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

    @Override
    public void showDongTaiList(DongTaiListResponse response) {

        if (response.getData() != null) {
            totalSize = response.getData().getTotalSize();
        }
        if (response.getData() != null) {
            if (response.getData().getData().size() > 0) {
                if (adapter.getData() != null && adapter.getData().size() == 1 && adapter.getData().get(0) == null) {
                    adapter.getData().clear();
                }
                adapter.addData(response.getData().getData());
            }
        }
        if (adapter.getData().size() >= totalSize) {
            adapter.loadMoreEnd();
        } else {
            adapter.loadMoreComplete();
        }

    }

    @Override
    public void onError(Throwable e) {

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
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (mid.equals(adapter.getData().get(i).getId() + "")) {
                adapter.getData().get(i).setAttentionFlag(1);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        if (mPresenter != null) {
            mPresenter.clear();
        }
        mPresenter = null;
    }
}
