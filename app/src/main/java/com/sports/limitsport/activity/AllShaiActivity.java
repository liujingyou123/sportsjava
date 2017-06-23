package com.sports.limitsport.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.AllShaiAdapter;
import com.sports.limitsport.activity.adapter.ShallAdapter;
import com.sports.limitsport.activity.model.Shai;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.view.SpacesItemHDecoration;
import com.sports.limitsport.view.SpacesItemVDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/23.
 * 大家都在晒
 */

public class AllShaiActivity extends BaseActivity {
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ry_all)
    RecyclerView ryAll;
    private AllShaiAdapter allShaiAdapter;
    private List<Shai> data = new ArrayList<>(5);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allshai);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        tvFocusHouse.setText("大家都在晒");
        initRy();
    }

    private void initRy() {
        List<Shai> dataShai = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Shai shai = new Shai();
            if (i == 0) {
                shai.imageUrl = "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D220/sign=965c17148f94a4c21523e0293ef51bac/3812b31bb051f819c6ccf551d2b44aed2f73e7c4.jpg";
                shai.avtorUrl = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg";
            } else if (i == 1) {
                shai.imageUrl = "https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D220/sign=e8ad4ce10246f21fd6345951c6256b31/00e93901213fb80e448b63cd3ed12f2eb9389455.jpg";
                shai.avtorUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498192057580&di=ef84966dca93c975c989224ac94eaa64&imgtype=0&src=http%3A%2F%2Fimg8.zol.com.cn%2Fbbs%2Fupload%2F4022%2F4021881.jpg";

            } else if (i == 2) {
                shai.imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498191947398&di=d03a0115e8cf8f6eb714f23722fc1941&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2015%2F227%2F37%2FSU4O4L7V51U5.jpg";
                shai.avtorUrl = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=982737746,434320139&fm=26&gp=0.jpg";

            } else {
                shai.imageUrl = "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D220/sign=965c17148f94a4c21523e0293ef51bac/3812b31bb051f819c6ccf551d2b44aed2f73e7c4.jpg";
                shai.avtorUrl = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg";
            }

            dataShai.add(shai);
        }

        ryAll.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        allShaiAdapter = new AllShaiAdapter(dataShai);
        ryAll.setAdapter(allShaiAdapter);

        SpacesItemVDecoration decoration = new SpacesItemVDecoration(100);
        ryAll.addItemDecoration(decoration);

        allShaiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                XLog.e("onItemChildClick");
            }
        });
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }
}
