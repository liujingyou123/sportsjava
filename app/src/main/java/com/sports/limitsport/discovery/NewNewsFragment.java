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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.DongTaiDetailActivity;
import com.sports.limitsport.discovery.adapter.DongTaiAdapter;
import com.sports.limitsport.discovery.model.DongTai;
import com.sports.limitsport.discovery.presenter.NewNewsPresenter;
import com.sports.limitsport.discovery.ui.INewNewsView;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.view.NewNewsHeadView;
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

    private DongTaiAdapter adapter;
    private List<DongTai> data = new ArrayList<>();
    private NewNewsHeadView newNewsHeadView;
    private NewNewsPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_news, null);
        unbinder = ButterKnife.bind(this, view);
        getTestData();
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
            mPresenter.getAdvList();
        }
    }

    private void init() {
        newNewsHeadView = new NewNewsHeadView(this.getContext());

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//可防止Item切换
        rlvNew.setLayoutManager(layoutManager);
        adapter = new DongTaiAdapter(data);
        adapter.bindToRecyclerView(rlvNew);
        adapter.addHeaderView(newNewsHeadView);
        SpacesItemDecorationS decoration = new SpacesItemDecorationS(5);
        rlvNew.addItemDecoration(decoration);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.imv_cover) {
                    gotoDongTaiDetail();
                } else if (view.getId() == R.id.imv_head || view.getId() == R.id.tv_name) {
                    gotoPersonInfo();
                }
            }
        });


    }

    private void getTestData() {
        List<DongTai> mdata = new ArrayList<>();

        DongTai act = new DongTai();
        act.imageUrl = "http://img2.imgtn.bdimg.com/it/u=4144902998,2125657744&fm=11&gp=0.jpg";
        mdata.add(act);

        DongTai act2 = new DongTai();
        act2.imageUrl = "http://pic.58pic.com/58pic/13/60/97/48Q58PIC92r_1024.jpg";
        mdata.add(act2);

        DongTai act3 = new DongTai();
        act3.imageUrl = "http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg";
        mdata.add(act3);

        for (int i = 0; i < 4; i++) {
            DongTai act4 = new DongTai();
            act4.imageUrl = "http://sc.jb51.net/uploads/allimg/150623/14-150623111Z1308.jpg";
            mdata.add(act4);
        }

        DongTai act5 = new DongTai();
        act5.imageUrl = "http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg";
        mdata.add(act5);

        for (int i = 0; i < 5; i++) {
            DongTai act4 = new DongTai();
            act4.imageUrl = "http://pic.58pic.com/58pic/13/60/97/48Q58PIC92r_1024.jpg";
            mdata.add(act4);
        }

        for (int i = 0; i < 4; i++) {
            DongTai act4 = new DongTai();
            act4.imageUrl = "http://sc.jb51.net/uploads/allimg/150623/14-150623111Z1308.jpg";
            mdata.add(act4);
        }

        for (int i = 0; i < 5; i++) {
            DongTai act6 = new DongTai();
            act6.imageUrl = "http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg";
            mdata.add(act6);
        }
        doLoadBitmap(mdata);
    }

    private void doLoadBitmap(List<DongTai> mData) {
        Observable.from(mData).map(new Func1<DongTai, DongTai>() {
            @Override
            public DongTai call(DongTai dongTai) {
                DongTai act4 = new DongTai();
                Bitmap bitmap = Batman.getInstance().getBitMap(NewNewsFragment.this.getContext(), dongTai.imageUrl);
                if (bitmap != null) {
                    act4.width = bitmap.getWidth();
                    act4.height = bitmap.getHeight();
                }
                act4.imageUrl = dongTai.imageUrl;
                return act4;
            }
        }).collect(new Func0<List<DongTai>>() {
            @Override
            public List<DongTai> call() {
                return new ArrayList<DongTai>();
            }
        }, new Action2<List<DongTai>, DongTai>() {
            @Override
            public void call(List<DongTai> dongTais, DongTai dongTai) {
                if (dongTais != null) {
                    dongTais.add(dongTai);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<DongTai>>() {
            @Override
            public void call(List<DongTai> dongTais) {
                adapter.addData(dongTais);
            }
        });
    }

    /**
     * 动态详情
     */
    private void gotoDongTaiDetail() {
        Intent intent = new Intent(getContext(), DongTaiDetailActivity.class);
        startActivity(intent);
    }

    /**
     * 个人主页
     */
    private void gotoPersonInfo() {
        Intent intent = new Intent(getContext(), PersonInfoActivity.class);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.clear();
        }

        mPresenter = null;
    }

}
