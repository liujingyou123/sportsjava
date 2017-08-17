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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.ActivityDiscussActivity;
import com.sports.limitsport.activity.AllShaiActivity;
import com.sports.limitsport.activity.DongTaiDetailActivity;
import com.sports.limitsport.activity.MapActivity;
import com.sports.limitsport.activity.SignUpListActivity;
import com.sports.limitsport.activity.adapter.NamesAdapter;
import com.sports.limitsport.activity.adapter.ShallAdapter;
import com.sports.limitsport.activity.adapter.TagDetailAdapter;
import com.sports.limitsport.discovery.ClubDetailActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.ApplicantListBean;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.model.TicketList;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.tagview.TagCloudLayout;
import com.sports.limitsport.view.video.JCVideoPlayerStandardShowShareButtonAfterFullscreen;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_sign_end)
    TextView tvSignEnd;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_ticket_type_num)
    TextView tvTicketTypeNum;
    @BindView(R.id.ll_tickets)
    LinearLayout llTickets;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.tv_refund_rule)
    TextView tvRefundRule;
    @BindView(R.id.tv_last_num)
    TextView tvLastNum;
    @BindView(R.id.tv_date_end)
    TextView tvDateEnd;
    @BindView(R.id.imv_auth)
    ImageView imvAuth;

    private ShallAdapter shallAdapter; //大家都在晒
    private NamesAdapter namesAdapter; //他们也报名了
    private ActivityDetailResponse.DataBean mData;
    private List<DongTaiList> data = new ArrayList<>();

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
        setAllRcy();
    }

    /**
     * dajia都在晒
     */
    private void setAllRcy() {
        rvAllSee.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        shallAdapter = new ShallAdapter(data);
        shallAdapter.bindToRecyclerView(rvAllSee);
        shallAdapter.setEmptyView(R.layout.empty_text);

        SpacesItemHDecoration decoration = new SpacesItemHDecoration(8);
        rvAllSee.addItemDecoration(decoration);

        shallAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DongTaiList dongTaiList = (DongTaiList) adapter.getItem(position);
                gotoDongtaiDetail(dongTaiList.getId() + "");
            }
        });
    }

    /**
     * 他们也报名了
     */
    private void setNameRecy(List<ApplicantListBean> data) {
        rlNames.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        namesAdapter = new NamesAdapter(data);
        rlNames.setAdapter(namesAdapter);


        SpacesItemHDecoration decoration = new SpacesItemHDecoration(3);
        rlNames.addItemDecoration(decoration);
    }


    /**
     * type: 0 video 1:图片
     */
    private void setViewType(int type) {
        if (type == 1) {
            Batman.getInstance().fromNet(mData.getCoverUrl(), imvCover);
//            Batman.getInstance().fromNetWithFitCenter(mData.getCoverUrl(), imvCover);
            imvCover.setVisibility(View.VISIBLE);
            jcVideo.setVisibility(View.INVISIBLE);
        } else {
            jcVideo.setVisibility(View.VISIBLE);
            imvCover.setVisibility(View.INVISIBLE);

            jcVideo.setUp(mData.getActivityVideo()
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, mData.getName());
            Batman.getInstance().fromNet(mData.getActivityVideoImg(), jcVideo.thumbImageView);
//            Batman.getInstance().fromNetWithFitCenter(mData.getActivityVideoImg(), jcVideo.thumbImageView);
        }
    }


    @OnClick({R.id.rl_allshai, R.id.tv_sign_num, R.id.ll_location, R.id.rl_discuss, R.id.imv_org, R.id.tv_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_allshai:
                Intent intent = new Intent(getContext(), AllShaiActivity.class);
                intent.putExtra("id", mData.getId());
                getContext().startActivity(intent);
                break;
            case R.id.tv_sign_num:
                gotoSignUpList();
                break;
            case R.id.ll_location:
                gotoMapActivity();
                break;
            case R.id.rl_discuss:
                gotoDiscussActivity();
                break;
            case R.id.imv_org:
                gotoOrigDetail();
                break;
            case R.id.tv_detail:
                gotoH5Detail();
                break;
        }
    }

    public void showDetail(ActivityDetailResponse.DataBean data) {
        if (data == null) {
            return;
        }
        mData = data;
        if (TextViewUtil.isEmpty(mData.getCoverUrl())) { //视频
            setViewType(0);
        } else {
            setViewType(1);
        }

        tvName.setText(mData.getName());
        tvPrice.setText(mData.getMoney());

        List<String> tags = new ArrayList<>(); //参加须知 标签

        if (TextViewUtil.isEmpty(mData.getRefundRule())) {
            tvPayback.setVisibility(View.GONE);
        } else {
            tvPayback.setVisibility(View.VISIBLE);
            if ("0".equals(mData.getRefundRule())) { //随时可退
                tvPayback.setText("随时可退");
                tags.add("随时可退");
                tvRefundRule.setText("该活动随时可退");
            } else if ("1".equals(mData.getRefundRule())) { //限时退款
                tvPayback.setText("限时退款");
                tags.add("限时退款");
                tvRefundRule.setText("该活动限时退款");
            } else if ("2".equals(mData.getRefundRule())) { //不可退款
                tvPayback.setText("不可退款");
                tags.add("不可退款");
                tvRefundRule.setText("该活动不可退款");
            }
        }
        tvDes.setText(mData.getActivityDetail());

        //组织人
        Batman.getInstance().getImageWithCircle(mData.getOrganizerHeadPor(), imvOrgCover, R.mipmap.icon_club_defaul, R.mipmap.icon_club_defaul);
        tvOrgName.setText(mData.getOrganizerClub());
        tvOrgGroup.setText(mData.getOrganizerdesc());

        //他们也报名了
        setNameRecy(mData.getApplicantList());
        tvSignNum.setText(mData.getApplicantNumber() + "");

        tvDate.setText(mData.getStartDate() + " " + UnitUtil.stringToWeek(mData.getWeek()) + " " + mData.getStartTime());
        tvDateEnd.setText(mData.getEndDate() + " " + mData.getEndTime());
        tvSignEnd.setText(mData.getSignEndDate());
        tvAddress.setText(mData.getAddress());

        if (mData.getTicketsList() != null) {
            tvTicketTypeNum.setText("本次活动分为" + mData.getTicketsList().size() + "种:");
            for (int i = 0; i < mData.getTicketsList().size(); i++) {
                TicketList ticketsListBean = mData.getTicketsList().get(i);
                TextView textView = new TextView(getContext());
                textView.setTextAppearance(getContext(), R.style.text_normal_gray);
                textView.setMaxLines(1);
                textView.setPadding(0, UnitUtil.dip2px(getContext(), 5), 0, 0);
                textView.setText(ticketsListBean.getName() + "：¥" + UnitUtil.formatSNum(ticketsListBean.getMoney()) + " (" + ticketsListBean.getDescContent() + ")");
                llTickets.addView(textView);
            }

        } else {
            tvTicketTypeNum.setText("暂无票种信息");
        }

        if ("1".equals(mData.getInsuranceInformation())) {
            tags.add("不包含保险");
        } else if ("0".equals(mData.getInsuranceInformation())) {
            tags.add("含保险");
        }

        if (mData.getOwnItems() == 0) {
            tags.add("无需自备物品");
        } else if (mData.getOwnItems() == 1) {
            tags.add("需自备物品");
        }
        TagDetailAdapter tagDetailAdapter = new TagDetailAdapter(this.getContext(), tags);
        tg.setAdapter(tagDetailAdapter);

        tvEndtime.setText(mData.getSignEndDate());
        tvLastNum.setText(mData.getLastGroups() + "人");

        if (mData.getAuthEntity() == 2) {
            imvAuth.setVisibility(VISIBLE);
        } else {
            imvAuth.setVisibility(GONE);
        }
    }

    public void showAllShai(List<DongTaiList> data) {
        shallAdapter.addData(data);
    }

    /**
     * 前往动态详情页
     */
    private void gotoDongtaiDetail(String id) {
        Intent intent = new Intent(getContext(), DongTaiDetailActivity.class);
        intent.putExtra("id", id);
        getContext().startActivity(intent);
    }

    /**
     * 组织者详情页(俱乐部详情页， 个人主页)
     */
    private void gotoOrigDetail() {
        Intent intent = new Intent(getContext(), ClubDetailActivity.class);
        intent.putExtra("id", mData.getOrganizerClubId() + "");
        getContext().startActivity(intent);
    }

    /**
     * 前往活动讨论区
     */
    private void gotoDiscussActivity() {
        Intent intent = new Intent(getContext(), ActivityDiscussActivity.class);
        intent.putExtra("id", mData.getId());
        getContext().startActivity(intent);
    }

    /**
     * 前往地图页
     */
    private void gotoMapActivity() {
        Intent intent1 = new Intent(getContext(), MapActivity.class);
        intent1.putExtra("lon", mData.getXpostion());
        intent1.putExtra("lat", mData.getYpostion());
        intent1.putExtra("address", mData.getAddress());
        getContext().startActivity(intent1);
    }

    /**
     * 前往报名列表
     */
    private void gotoSignUpList() {
        Intent intent2 = new Intent(getContext(), SignUpListActivity.class);
        intent2.putExtra("id", mData.getId());
        getContext().startActivity(intent2);
    }

    private void gotoH5Detail() {
        Intent intent = new Intent(getContext(), H5Activity.class);
        intent.putExtra("id", mData.getId());
        intent.putExtra("type", 2);
        getContext().startActivity(intent);
    }
}
