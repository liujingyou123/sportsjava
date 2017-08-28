package com.sports.limitsport.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.ElsePriseActivity;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.discovery.adapter.PraiseHeadAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.FineShowDetailResponse;
import com.sports.limitsport.model.PraiseList;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.view.video.JCVideoPlayerStandardShowShareButtonAfterFullscreen;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by liuworkmac on 17/7/12.
 */

public class FineShowDetailHeadView extends LinearLayout {
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.rl_names)
    RecyclerView rlNames;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_focus)
    TextView tvFocus;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.imv_zan)
    ImageView imvZan;
    @BindView(R.id.tv_san)
    TextView tvSan;
    @BindView(R.id.imv_pinglun)
    ImageView imvPinglun;
    @BindView(R.id.imv_share)
    ImageView imvShare;
    @BindView(R.id.imv_report)
    ImageView imvReport;
    @BindView(R.id.tv_sign_num)
    TextView tvSignNum;
    @BindView(R.id.ll_players)
    LinearLayout llPlayers;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_at)
    LinearLayout llAt;
    @BindView(R.id.imv_head)
    ImageView imvHead;
    @BindView(R.id.tv_comments_num)
    TextView tvCommentsNum;
    @BindView(R.id.jc_video)
    JCVideoPlayerStandardShowShareButtonAfterFullscreen jcVideo;
    private FineShowDetailResponse.DataBean item;


    public FineShowDetailHeadView(Context context) {
        super(context);
        init();
    }

    public FineShowDetailHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FineShowDetailHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_fineshowdetail_head, this);
        ButterKnife.bind(this, this);
        rlNames.setFocusable(false);
//        rlNames.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
//        rlNames.setFocusable(true);
//        rlNames.setFocusableInTouchMode(true);
//        rlNames.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.requestFocusFromTouch();
//                return false;
//            }
//        });

    }

    /**
     * type: 0 video 1:图片
     */
    private void setViewType(String type) {
        if ("1".equals(type)) {
            Batman.getInstance().fromNet(item.getImgUrl(), imvCover, R.mipmap.icon_default_detail, R.mipmap.icon_default_detail);
//            Batman.getInstance().fromNetWithFitCenter(mData.getCoverUrl(), imvCover);
            imvCover.setVisibility(View.VISIBLE);
            jcVideo.setVisibility(View.INVISIBLE);
        } else {
            jcVideo.setVisibility(View.VISIBLE);
            imvCover.setVisibility(View.INVISIBLE);

            jcVideo.setUp(item.getVedioUrl()
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, item.getTitle() == null ? "" : item.getTitle());
            Batman.getInstance().fromNet(item.getImgUrl(), jcVideo.thumbImageView, R.mipmap.icon_default_detail, R.mipmap.icon_default_detail);
//            Batman.getInstance().fromNetWithFitCenter(mData.getActivityVideoImg(), jcVideo.thumbImageView);
        }
    }

    /**
     * 他们也报名了
     */
    public void setNameRecy(List<PraiseList> data) {
        rlNames.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        PraiseHeadAdapter namesAdapter = new PraiseHeadAdapter(data);
        rlNames.setAdapter(namesAdapter);


        SpacesItemHDecoration decoration = new SpacesItemHDecoration(3);
        rlNames.addItemDecoration(decoration);
    }


    @OnClick(R.id.tv_sign_num)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), ElsePriseActivity.class);
        intent.putExtra("id", item.getId() + "");
        intent.putExtra("praiseType", "1");
        getContext().startActivity(intent);
    }

    public void setData(FineShowDetailResponse.DataBean data) {
        if (data == null) {
            return;
        }

        this.item = data;

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);
        tvName.setText(item.getPublishUserName());
        tvTime.setText(item.getShowCreateTime());

        if (TextViewUtil.isEmpty(data.getTitle())) {
            tvTitle.setText(data.getTitle());
        }

        if (item.getAttentionFlag() == 0) {
            tvFocus.setText("+关注");
        } else if (item.getAttentionFlag() == 1) {
            tvFocus.setText("进入主页");
        }

//        if (item.getResourceType() == 1) { //1 图片 2:视频
//            Batman.getInstance().fromNet(item.getImgUrl(), imvCover, R.mipmap.icon_ver_default, R.mipmap.icon_ver_default);
//        } else {
//            Batman.getInstance().fromNet(item.getVedioThumbnailUrl(), imvCover, R.mipmap.icon_ver_default, R.mipmap.icon_ver_default);
//        }


        setViewType(item.getResourceType() + "");
        if (!TextViewUtil.isEmpty(item.getContent())) {
            tvContent.setText(item.getContent());
        }

        if (item.getAtUserList() != null && item.getAtUserList().size() > 0) {
            llAt.removeAllViews();
            for (int i = 0; i < item.getAtUserList().size(); i++) {
                FineShowDetailResponse.DataBean.AtUserListBean atUserList = item.getAtUserList().get(i);
                if (atUserList != null) {
                    llAt.addView(getAtText(atUserList.getName(), atUserList.getUserId()));
                }
            }
        }


        tvSan.setText(item.getPraiseNum() + "");
        tvSignNum.setText(item.getPraiseNum() + "");

        if ("1".equals(item.getPraiseFlag())) { //1:已点赞 0:未点赞
            tvSan.setSelected(true);
            imvZan.setSelected(true);
        } else {
            tvSan.setSelected(false);
            imvZan.setSelected(false);
        }
    }

    public void setFocus() {
        item.setAttentionFlag(1);
        tvFocus.setText("进入主页");
    }

    public void setPraise() {
        if ("1".equals(item.getPraiseFlag())) {
            item.setPraiseFlag("0");
        } else {
            item.setPraiseFlag("1");
        }

        int numI = item.getPraiseNum();

        if ("1".equals(item.getPraiseFlag())) { //1:已点赞 0:未点赞
            numI++;
            tvSan.setSelected(true);
            imvZan.setSelected(true);
        } else {
            numI--;
            tvSan.setSelected(false);
            imvZan.setSelected(false);
        }
        item.setPraiseNum(numI);
        tvSan.setText(numI + "");
        tvSignNum.setText(numI + "");
    }

    public FineShowDetailResponse.DataBean getData() {
        return item;
    }

    private TextView getAtText(String name, final String userId) {
        TextView textView = new TextView(getContext());
        textView.setText("@" + name);
        textView.setTag(userId);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        textView.setTextColor(Color.parseColor("#FF4795FB"));
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PersonInfoActivity.class);
                intent.putExtra("userId", userId);
                getContext().startActivity(intent);
            }
        });
        return textView;
    }

    public void setChildClickListener(OnClickListener onClickListener) {
        findViewById(R.id.imv_pinglun).setOnClickListener(onClickListener);
        findViewById(R.id.imv_share).setOnClickListener(onClickListener);
        findViewById(R.id.imv_report).setOnClickListener(onClickListener);
        findViewById(R.id.tv_san).setOnClickListener(onClickListener);
        findViewById(R.id.imv_zan).setOnClickListener(onClickListener);
        findViewById(R.id.tv_focus).setOnClickListener(onClickListener);
    }

    public void setCommentNum(int num) {
        tvCommentsNum.setText("全部评论（" + num + "条）");
    }
}
