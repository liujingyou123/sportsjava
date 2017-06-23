package com.sports.limitsport.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ShallAdapter;
import com.sports.limitsport.activity.adapter.TagDetailAdapter;
import com.sports.limitsport.activity.model.Shai;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.StatusBarUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.PositionScrollView;
import com.sports.limitsport.view.SpacesItemHDecoration;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class ActivityDetailActivity extends BaseActivity {
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.jc_video)
    JCVideoPlayerStandard jcVideo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_payback)
    TextView tvPayback;
    @BindView(R.id.imv_back)
    ImageView imvBack;
    @BindView(R.id.imv_share)
    ImageView imvShare;
    @BindView(R.id.imv_fav)
    ImageView imvFav;
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.rl_detail)
    RelativeLayout rlDetail;
    @BindView(R.id.imv_org_cover)
    ImageView imvOrgCover;
    @BindView(R.id.tv_org_name)
    TextView tvOrgName;
    @BindView(R.id.tv_org_group)
    TextView tvOrgGroup;
    @BindView(R.id.ll_players)
    LinearLayout llPlayers;
    @BindView(R.id.rv_all_see)
    RecyclerView rvAllSee;
    @BindView(R.id.tg)
    TagCloudLayout tg;
    @BindView(R.id.tv_price_bottom)
    TextView tvPriceBottom;
    @BindView(R.id.btn_done)
    TextView btnDone;
    @BindView(R.id.rl_call_pre)
    RelativeLayout rlCallPre;
    @BindView(R.id.scrollView)
    PositionScrollView scrollView;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_cover)
    RelativeLayout rlCover;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        init();
        setAllRcy();
        setViewType(1);
        StatusBarUtil.setTranslucent(this);
    }

    private void init() {
        jcVideo.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        Batman.getInstance().fromNet("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360", jcVideo.thumbImageView);

        TagDetailAdapter tagDetailAdapter = new TagDetailAdapter(this, null);
        tg.setAdapter(tagDetailAdapter);


        scrollView.setOnScrollChangedListener(new PositionScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (t > 0) {
                    rlTitle.setBackgroundColor(Color.parseColor("#000000"));
                    tvTitle.setVisibility(View.VISIBLE);
                } else {
                    rlTitle.setBackgroundColor(Color.parseColor("#00000000"));
                    tvTitle.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setAllRcy() {
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

        rvAllSee.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ShallAdapter shallAdapter = new ShallAdapter(dataShai);
        rvAllSee.setAdapter(shallAdapter);


        SpacesItemHDecoration decoration = new SpacesItemHDecoration(16);
        rvAllSee.addItemDecoration(decoration);
    }

    /**
     * type: 0 video 1:图片
     */
    public void setViewType(int type) {
        if (type == 1) {
            Batman.getInstance().fromNet("https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D220/sign=e8ad4ce10246f21fd6345951c6256b31/00e93901213fb80e448b63cd3ed12f2eb9389455.jpg", imvCover);
            imvCover.setVisibility(View.VISIBLE);
            jcVideo.setVisibility(View.GONE);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int left = UnitUtil.dip2px(this, 15);
            int top = UnitUtil.dip2px(this, 200);

            lp.setMargins(left, top, left, 0);
            rlCover.setBackgroundResource(R.drawable.bg_round_black);
            rlCover.setLayoutParams(lp);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    @OnClick({R.id.btn_done, R.id.imv_back, R.id.rl_allshai, R.id.tv_sign_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                break;
            case R.id.imv_back:
                finish();
                break;
            case R.id.rl_allshai:
                Intent intent = new Intent(this, AllShaiActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_sign_num:
                Intent intent2 = new Intent(this, SignUpListActivity.class);
                startActivity(intent2);
                break;
        }
    }
}