package com.sports.limitsport.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.AllShaiActivity;
import com.sports.limitsport.activity.MapActivity;
import com.sports.limitsport.activity.SignUpListActivity;
import com.sports.limitsport.activity.adapter.NamesAdapter;
import com.sports.limitsport.activity.adapter.ShallAdapter;
import com.sports.limitsport.activity.adapter.TagDetailAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.view.tagview.TagCloudLayout;
import com.sports.limitsport.view.video.JCVideoPlayerStandardShowShareButtonAfterFullscreen;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by liuworkmac on 17/7/14.
 */

public class ActivityDetailHeaderView extends LinearLayout {
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.jc_video)
    JCVideoPlayerStandardShowShareButtonAfterFullscreen jcVideo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_payback)
    TextView tvPayback;
    @BindView(R.id.tv_remind_tickets)
    TextView tvRemindTickets;
    @BindView(R.id.rl_cover)
    RelativeLayout rlCover;
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
    @BindView(R.id.rl_names)
    RecyclerView rlNames;
    @BindView(R.id.tv_sign_num)
    TextView tvSignNum;
    @BindView(R.id.ll_players)
    LinearLayout llPlayers;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.tg)
    TagCloudLayout tg;
    @BindView(R.id.rl_allshai)
    RelativeLayout rlAllshai;
    @BindView(R.id.rv_all_see)
    RecyclerView rvAllSee;

    private ShallAdapter shallAdapter; //大家都在晒
    private NamesAdapter namesAdapter; //他们也报名了

    public ActivityDetailHeaderView(Context context) {
        super(context);
        initView();
    }

    public ActivityDetailHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ActivityDetailHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_activity_detail_head, this);
        ButterKnife.bind(this, this);

        jcVideo.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子不信");
        Batman.getInstance().fromNet("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360", jcVideo.thumbImageView);

        TagDetailAdapter tagDetailAdapter = new TagDetailAdapter(this.getContext(), null);
        tg.setAdapter(tagDetailAdapter);

        setAllRcy();
        setNameRecy();
    }

    private void setAllRcy() {
        rvAllSee.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        shallAdapter = new ShallAdapter(MyTestData.getData());
        shallAdapter.bindToRecyclerView(rvAllSee);
        shallAdapter.setEmptyView(R.layout.empty_text);

        SpacesItemHDecoration decoration = new SpacesItemHDecoration(8);
        rvAllSee.addItemDecoration(decoration);
    }

    /**
     * 他们也报名了
     */
    private void setNameRecy() {
        rlNames.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        namesAdapter = new NamesAdapter(MyTestData.getData());
        rlNames.setAdapter(namesAdapter);


        SpacesItemHDecoration decoration = new SpacesItemHDecoration(3);
        rlNames.addItemDecoration(decoration);
    }


    /**
     * type: 0 video 1:图片
     */
    public void setViewType(int type) {
        if (type == 1) {
            Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imvCover);
            imvCover.setVisibility(View.VISIBLE);
            jcVideo.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.rl_allshai, R.id.tv_sign_num, R.id.ll_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_allshai:
                Intent intent = new Intent(getContext(), AllShaiActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.tv_sign_num:
                Intent intent2 = new Intent(getContext(), SignUpListActivity.class);
                getContext().startActivity(intent2);
                break;
            case R.id.ll_location:
                Intent intent1 = new Intent(getContext(), MapActivity.class);
                getContext().startActivity(intent1);
                break;
        }
    }
}
