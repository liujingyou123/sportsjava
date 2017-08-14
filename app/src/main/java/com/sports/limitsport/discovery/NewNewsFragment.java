package com.sports.limitsport.discovery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.DongTaiDetailActivity;
import com.sports.limitsport.discovery.adapter.DongTaiAdapter;
import com.sports.limitsport.discovery.presenter.NewNewsPresenter;
import com.sports.limitsport.discovery.ui.INewNewsView;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.CustomLoadMoreView;
import com.sports.limitsport.view.NewNewsHeadView;
import com.sports.limitsport.view.SpacesItemDecoration;
import com.sports.limitsport.view.SpacesItemDecorationS;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liuworkmac on 17/6/29.
 * 动态
 */

public class NewNewsFragment extends Fragment implements INewNewsView {
    @BindView(R.id.rlv_new)
    RecyclerView rlvNew;
    Unbinder unbinder;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;

    private DongTaiAdapter adapter;
    private List<DongTaiList> data = new ArrayList<>();
    private NewNewsHeadView newNewsHeadView;
    private NewNewsPresenter mPresenter;
    private int totalSize;
    private int pageNumber = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_news, null);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new NewNewsPresenter(this);
        }
        mPresenter.getAdvList();
        mPresenter.getDongTaiList(pageNumber);
    }

    private void init() {
        newNewsHeadView = new NewNewsHeadView(this.getContext());

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//可防止Item切换
        rlvNew.setLayoutManager(layoutManager);
        adapter = new DongTaiAdapter(data);
        adapter.bindToRecyclerView(rlvNew);
        adapter.setLoadMoreView(new CustomLoadMoreView());

        adapter.addHeaderView(newNewsHeadView);
        SpacesItemDecoration decoration = new SpacesItemDecoration(UnitUtil.dip2px(getContext(), 3));
        rlvNew.addItemDecoration(decoration);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                DongTaiList dongTaiList = (DongTaiList) adapter.getItem(position);
                if (dongTaiList == null) {
                    return;
                }
                if (view.getId() == R.id.imv_cover) {
                    gotoDongTaiDetail(dongTaiList.getId() + "");
                } else if (view.getId() == R.id.imv_head || view.getId() == R.id.tv_name) {
                    gotoPersonInfo(dongTaiList.getPublishUserId() + "");
                } else if (view.getId() == R.id.imv_zan || view.getId() == R.id.tv_san) {
                    if ("1".equals(dongTaiList.getPraiseFlag())) { //1:已点赞 0:未点赞
                        if (mPresenter != null) {
                            mPresenter.cancelPraise(dongTaiList.getId() + "", "2");
                        }
                    } else {
                        if (mPresenter != null) {
                            mPresenter.praise(dongTaiList.getId() + "", "2");
                        }
                    }
                }
            }
        });

        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlvNew);
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
            adapter.loadMoreComplete();
        }
    }


    private void doLoadBitmap(List<DongTaiList> mData) {
        Observable.from(mData).map(new Func1<DongTaiList, DongTaiList>() {
            @Override
            public DongTaiList call(DongTaiList dongTai) {
                Bitmap bitmap = null;
                if (dongTai.getResourceType() == 1) { //1 图片 2:视频
                    bitmap = Batman.getInstance().getBitMap(NewNewsFragment.this.getContext(), dongTai.getImgUrl());
                } else {
                    bitmap = Batman.getInstance().getBitMap(NewNewsFragment.this.getContext(), dongTai.getVedioThumbnailUrl());
                }

                if (bitmap != null) {
                    dongTai.setWidth(bitmap.getWidth());
                    dongTai.setHeight(bitmap.getHeight());
                } else {
                    dongTai.setWidth(372);
                    dongTai.setHeight(460);
                }
                return dongTai;
            }
        }).collect(new Func0<List<DongTaiList>>() {
            @Override
            public List<DongTaiList> call() {
                return new ArrayList<DongTaiList>();
            }
        }, new Action2<List<DongTaiList>, DongTaiList>() {
            @Override
            public void call(List<DongTaiList> dongTais, DongTaiList dongTai) {
                if (dongTais != null) {
                    dongTais.add(dongTai);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<DongTaiList>>() {
            @Override
            public void call(List<DongTaiList> dongTais) {
                if (rlAll.isRefreshing()) {
                    data.clear();
                    data.addAll(dongTais);
                    adapter.notifyDataSetChanged();
                    rlAll.refreshComplete();
                } else {
                    adapter.addData(dongTais);
                    if (adapter.getData().size() >= totalSize) {
                        adapter.loadMoreEnd();
                    } else {
                        adapter.loadMoreComplete();
                    }
                }
            }
        });
    }

    /**
     * 动态详情
     */
    private void gotoDongTaiDetail(String id) {
        Intent intent = new Intent(getContext(), DongTaiDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    /**
     * 个人主页
     */
    private void gotoPersonInfo(String userId) {
        Intent intent = new Intent(getContext(), PersonInfoActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    @Override
    public void showAdvList(AdvertiseInfoResponse response) {
        if (response.getData() != null) {
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i < response.getData().size(); i++) {
                tmp.add(response.getData().get(i).getAdPicUrl());
            }

            if (newNewsHeadView != null) {
                newNewsHeadView.setImagesList(tmp);
            }
        }
    }

    @Override
    public void showAllDongTai(DongTaiListResponse response) {
        if (response.getData() != null) {

            totalSize = response.getData().getTotalSize();
            List<DongTaiList> tmp = response.getData().getData();
            doLoadBitmap(tmp);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (rlAll.isRefreshing()) {
            rlAll.refreshComplete();
        } else {
            adapter.loadMoreFail();
        }
    }

    @Override
    public void onCancelPraiseResult(boolean b, String articleId, String type) {
        if (b) {
            doPraise(articleId);
        } else {
            ToastUtil.showTrueToast(getContext(), "取消点赞失败");
        }
    }

    @Override
    public void onPraiseResult(boolean b, String articleId, String type) {
        if (b) {
            doPraise(articleId);
        } else {
            ToastUtil.showTrueToast(getContext(), "点赞失败");
        }
    }

    private void doPraise(String id) {
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (id.equals(adapter.getData().get(i).getId() + "")) {
                int num = adapter.getData().get(i).getPraiseNum();
                if ("1".equals(adapter.getData().get(i).getPraiseFlag())) { //已点赞
                    num--;
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
