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
import com.sports.limitsport.activity.adapter.NamesAdapter;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.discovery.adapter.PraiseHeadAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.adapter.TagActivityAdapter;
import com.sports.limitsport.model.DongTaiDetailResponse;
import com.sports.limitsport.model.PraiseList;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/17.
 */

public class DongTaiDetialHeadView extends LinearLayout {

    @BindView(R.id.imv_head)
    ImageView imvHead;
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
    @BindView(R.id.tg_tag)
    TagCloudLayout tgTag;
    @BindView(R.id.imv_zan)
    ImageView imvZan;
    @BindView(R.id.tv_san)
    TextView tvSan;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_at)
    LinearLayout llAt;
    @BindView(R.id.tv_comments_num)
    TextView tvCommentsNum;
    @BindView(R.id.tv_sign_num)
    TextView tvSignNum;
    private PraiseHeadAdapter namesAdapter; // 他们也觉得赞
    private CommentDialog commentDialog;
    private DongTaiDetailResponse.DataBean item;


    public DongTaiDetialHeadView(Context context) {
        super(context);
        initView();
    }

    public DongTaiDetialHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DongTaiDetialHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_dongtaidetail, this);
        ButterKnife.bind(this, this);

    }

    /**
     * 他们也报名了
     */
    public void setNameRecy(List<PraiseList> data) {
        rlNames.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        namesAdapter = new PraiseHeadAdapter(data);
        rlNames.setAdapter(namesAdapter);


        SpacesItemHDecoration decoration = new SpacesItemHDecoration(3);
        rlNames.addItemDecoration(decoration);
    }

    @OnClick(R.id.tv_sign_num)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), ElsePriseActivity.class);
        intent.putExtra("id", item.getId() + "");
        intent.putExtra("praiseType", "2");
        getContext().startActivity(intent);
    }

    public void setData(DongTaiDetailResponse.DataBean data) {
        if (data == null) {
            return;
        }

        this.item = data;

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);
        tvName.setText(item.getPublishUserName());
        tvTime.setText(item.getShowCreateTime());

        if (item.getAttentionFlag() == 0) {
            tvFocus.setText("+关注");
        } else if (item.getAttentionFlag() == 1) {
            tvFocus.setText("进入主页");
        }

        if (item.getResourceType() == 1) { //1 图片 2:视频
            Batman.getInstance().fromNet(item.getImgUrl(), imvCover);
        } else {
            Batman.getInstance().fromNet(item.getVedioThumbnailUrl(), imvCover);
        }

        if (!TextViewUtil.isEmpty(item.getContent())) {
            tvContent.setText(item.getContent());
        }

        if (item.getAtUserList() != null && item.getAtUserList().size() > 0) {
            for (int i = 0; i < item.getAtUserList().size(); i++) {
                DongTaiDetailResponse.DataBean.AtUserList atUserList = item.getAtUserList().get(i);
                if (atUserList != null) {
                    llAt.addView(getAtText(atUserList.getName(), atUserList.getUserId()));
                }
            }
        }

        List<String> tags = new ArrayList<>();
        if (!TextViewUtil.isEmpty(item.getActivityName())) {
            tags.add(item.getActivityName());
        }
        tgTag.setAdapter(new TagActivityAdapter(getContext(), tags));

        if (TextViewUtil.isEmpty(item.getPraiseNum())) {
            tvSan.setText("0");
            tvSignNum.setText("0");
        } else {
            tvSan.setText(item.getPraiseNum() + "");
            tvSignNum.setText(item.getPraiseNum() + "");
        }
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

        String num = item.getPraiseNum();

        if (TextViewUtil.isEmpty(num)) {
            num = "0";
        }
        int numI = 0;
        try {
            numI = Integer.parseInt(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("1".equals(item.getPraiseFlag())) { //1:已点赞 0:未点赞
            numI++;
            tvSan.setSelected(true);
            imvZan.setSelected(true);
        } else {
            numI--;
            tvSan.setSelected(false);
            imvZan.setSelected(false);
        }
        item.setPraiseNum(numI + "");
        tvSan.setText(numI + "");
        tvSignNum.setText(numI + "");
    }

    public DongTaiDetailResponse.DataBean getData() {
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
