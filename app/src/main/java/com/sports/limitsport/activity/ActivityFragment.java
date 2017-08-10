package com.sports.limitsport.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ActivitysAdapter;
import com.sports.limitsport.activity.ui.IActivityListView;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.activity.presenter.ActivityListPresenter;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.view.CustomLoadMoreView;
import com.sports.limitsport.view.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class ActivityFragment extends BaseFragment implements IActivityListView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.rl_nodata)
    RelativeLayout rlNodata;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;

    private List<Act> data = new ArrayList<>();
    private ActivitysAdapter adapter;

    private ActivityListPresenter mPresenter;
    private int pageNumber = 1;

    private long totalSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, null);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();

    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ActivityListPresenter(this);
        }

        mPresenter.getActivityList(pageNumber);
    }

    private void init() {
        tvFocusHouse.setText("极限领秀");
        imvFocusHouseBack.setVisibility(View.GONE);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//可防止Item切换

        recyclerView.setLayoutManager(layoutManager);

        adapter = new ActivitysAdapter(data);
        adapter.bindToRecyclerView(recyclerView);

        adapter.setLoadMoreView(new CustomLoadMoreView());

        SpacesItemDecoration decoration = new SpacesItemDecoration(5);
        recyclerView.addItemDecoration(decoration);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Act act = (Act) adapter.getItem(position);
                if (act != null) {
                    Intent intent = new Intent(ActivityFragment.this.getContext(), ActivityDetailActivity.class);
                    intent.putExtra("id", act.getId() + "");
                    intent.putExtra("week", act.getWeek());
                    intent.putExtra("minMoney", act.getMinMoney());
                    ActivityFragment.this.startActivity(intent);
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
        }, recyclerView);
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
            mPresenter.getActivityList(pageNumber);
        }
    }

    private void refresh() {
        pageNumber = 1;
        if (mPresenter != null) {
            mPresenter.getActivityList(pageNumber);
        }
    }


    private void doLoadBitmap(List<Act> mData) {
        Observable.from(mData).map(new Func1<Act, Act>() {
            @Override
            public Act call(Act baseStaggeredEntity) {
                //TODO
                baseStaggeredEntity.setCoverUrl("http://image.tianjimedia.com/uploadImages/2015/318/28/J0IUZ1ST711A.jpg");
                Bitmap bitmap = Batman.getInstance().getBitMap(ActivityFragment.this.getContext(), baseStaggeredEntity.getCoverUrl());
                if (bitmap != null) {
                    baseStaggeredEntity.setWidth(bitmap.getWidth());
                    baseStaggeredEntity.setHeight(bitmap.getHeight());
                } else {
                    baseStaggeredEntity.setWidth(180);
                    baseStaggeredEntity.setHeight(320);
                }
                return baseStaggeredEntity;
            }
        }).collect(new Func0<List<Act>>() {
            @Override
            public List<Act> call() {
                return new ArrayList<Act>();
            }
        }, new Action2<List<Act>, Act>() {
            @Override
            public void call(List<Act> baseStaggeredEntities, Act baseStaggeredEntity) {
                if (baseStaggeredEntities != null) {
                    baseStaggeredEntities.add(baseStaggeredEntity);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Act>>() {
            @Override
            public void call(List<Act> baseStaggeredEntities) {
                if (rlAll.isRefreshing()) {
                    data.clear();
                    data.addAll(baseStaggeredEntities);
                    adapter.notifyDataSetChanged();
                    rlAll.refreshComplete();
                    adapter.loadMoreComplete();
                } else {
                    adapter.addData(baseStaggeredEntities);
                    if (adapter.getData().size() >= totalSize) {
                        adapter.loadMoreEnd();
                    } else {
                        adapter.loadMoreComplete();
                    }
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                XLog.e("throwable exception");
            }
        });
    }

    @OnClick(R.id.imv_nonewdata)
    public void onViewClicked() {
        rlNodata.setVisibility(View.GONE);
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

    @Override
    public void showList(ActivityResponse response) {
        if (response.getData() != null) {
            if (response.getData().getQuery() > 0) {
                rlNodata.setVisibility(View.GONE);
            } else {
                rlNodata.setVisibility(View.VISIBLE);
            }
            totalSize = response.getData().getTotalSize();
            List<Act> tmp = response.getData().getData();
            doLoadBitmap(tmp);
        }
    }

    @Override
    public void onError(Throwable e) {
        adapter.loadMoreFail();
    }
}