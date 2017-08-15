package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.EachCommentAdapter;
import com.sports.limitsport.model.HuDongNoticeList;
import com.sports.limitsport.model.HuDongNoticeListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.notice.EditNewDongTaiActivity;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/6.
 * 评论
 */

public class EachCommentFragment extends Fragment {
    @BindView(R.id.rl_comments)
    RecyclerView rlComments;
    Unbinder unbinder;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.view_nonet)
    RelativeLayout viewNonet;
    private EachCommentAdapter adapter;
    private List<HuDongNoticeList> data = new ArrayList<>();
    private int pageNumber = 1;
    private int totalSize;
    private CommentDialog commentDialog;
    private HuDongNoticeList commentData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eachcomment, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
//        getList();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rlAll.autoRefreshDelay();
    }

    private void initView() {
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_commentlist, null);
        TextView etEmpty = (TextView) emptyView.findViewById(R.id.tv_empty);
        TextView tvGo = (TextView) emptyView.findViewById(R.id.tv_go);
        if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
            etEmpty.setText("好像什么都没有");
            tvGo.setVisibility(View.GONE);
        }
        tvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditNewDongTaiActivity.class);
                startActivity(intent);
            }
        });
        rlComments.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new EachCommentAdapter(data);
        adapter.bindToRecyclerView(rlComments);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setEmptyView(emptyView);


        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlComments);
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

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (commentDialog != null && !commentDialog.isShowing()) {
                    commentData = (HuDongNoticeList) adapter.getItem(position);
                    commentDialog.show();
                }
            }
        });

        commentDialog = new CommentDialog(getContext());

        commentDialog.setOkDoneListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog.dismiss();
                if (commentData != null) {
                    replayComment(commentData.getId() + "", commentData.getUserId() + "", commentData.getName(), commentDialog.getContent());
                }
            }
        });

    }

    private void loadMore() {
        pageNumber++;
        getList();
    }

    private void refresh() {
        pageNumber = 1;
        getList();
    }

    private void getList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "0");
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getHuDongList(hashMap), new LoadingNetSubscriber<HuDongNoticeListResponse>() {
            @Override
            public void response(HuDongNoticeListResponse response) {
                if (response != null && response.getData() != null) {
                    totalSize = response.getData().getTotalSize();
                    if (rlAll.isRefreshing()) {
                        data.clear();
                        data.addAll(response.getData().getData());
                        adapter.setNewData(data);
                        adapter.disableLoadMoreIfNotFullPage();
                        rlAll.refreshComplete();
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
                super.onError(e);
                if (adapter.isLoading()) {
                    adapter.loadMoreFail();
                } else {
                    if (e != null && (e.getClass().getName().equals(UnknownHostException.class.getName())
                            || e.getClass().getName().equals(SocketTimeoutException.class.getName())
                            || e.getClass().getName().equals(ConnectException.class.getName()))) {
                        viewNonet.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    /**
     * 回复活动评论
     *
     * @param replyCommentId 回复评论ID
     * @param replyUserId    回复对象ID
     * @param replyUserName  回复对象名称
     * @param replyContent   回复内容
     */
    public void replayComment(String replyCommentId, String replyUserId, String replyUserName, String replyContent) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("replyCommentId", replyCommentId);
        hashMap.put("replyUserId", replyUserId);
        hashMap.put("replyUserName", replyUserName);
        hashMap.put("replyContent", replyContent);

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).replayComments(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null) {
                    if (response.isSuccess()) {
                        ToastUtil.showTrueToast(getContext(), "评论成功");
                    } else {
                        ToastUtil.showTrueToast(getContext(), "评论失败");
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showTrueToast(getContext(), "评论失败");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_reload)
    public void onClick() {
        viewNonet.setVisibility(View.GONE);
        rlAll.autoRefreshDelay();
    }
}
