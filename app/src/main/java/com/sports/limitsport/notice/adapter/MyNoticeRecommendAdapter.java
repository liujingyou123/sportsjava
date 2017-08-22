package com.sports.limitsport.notice.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.DongTaiDetailActivity;
import com.sports.limitsport.activity.adapter.ShallAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.model.RecommendDongTai;
import com.sports.limitsport.model.RecommendFriendsList;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.SpacesItemHDecoration;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/13.
 * 为你推荐
 */

public class MyNoticeRecommendAdapter extends BaseQuickAdapter<RecommendFriendsList, BaseViewHolder> {
    SpacesItemHDecoration decoration = null;

    public MyNoticeRecommendAdapter(@Nullable List<RecommendFriendsList> data) {
        super(R.layout.adapter_mynoticerecommedn, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendFriendsList item) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvFocus = helper.getView(R.id.tv_focus);
        TextView tvTime = helper.getView(R.id.tv_time);
        RecyclerView recyclerView = helper.getView(R.id.rcv_dongtai);

        if (0 == item.getAttentionFlag()) {
            tvFocus.setText("+关注");
            tvFocus.setSelected(true);
        } else {
            tvFocus.setText("进入主页");
            tvFocus.setSelected(true);
        }
        helper.addOnClickListener(R.id.tv_focus);
        tvName.setText(item.getName());
        if (!TextViewUtil.isEmpty(item.getIntroduction())) {
            tvTime.setText(item.getIntroduction());
        }

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        RecommendDongTaiAdapter adapter = new RecommendDongTaiAdapter(item.getTrendInfoDTOList());
        adapter.bindToRecyclerView(recyclerView);

        if (decoration == null) {
            decoration = new SpacesItemHDecoration(UnitUtil.dip2px(mContext, 3));
        }
        if (decoration != null) {
            recyclerView.removeItemDecoration(decoration);
            recyclerView.addItemDecoration(decoration);
        }


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RecommendDongTai dongTaiList = (RecommendDongTai) adapter.getItem(position);
                Intent intent = new Intent(mContext, DongTaiDetailActivity.class);
                intent.putExtra("id", dongTaiList.getId() + "");
                mContext.startActivity(intent);
            }
        });

//        if (item.getTrendInfoDTOList() != null) {
//            for (int i = 0; i < item.getTrendInfoDTOList().size(); i++) {
//                if (i == 0) {
//                    if (item.getTrendInfoDTOList().get(0).getResourceType() == 1) {
//                        Batman.getInstance().fromNet(item.getTrendInfoDTOList().get(0).getImgUrl(), imvOne);
//                    } else {
//                        Batman.getInstance().fromNet(item.getTrendInfoDTOList().get(0).getVedioImgUrl(), imvOne);
//                    }
//                } else if (i == 1) {
//                    if (item.getTrendInfoDTOList().get(1).getResourceType() == 1) {
//                        Batman.getInstance().fromNet(item.getTrendInfoDTOList().get(1).getImgUrl(), imvTwo);
//                    } else {
//                        Batman.getInstance().fromNet(item.getTrendInfoDTOList().get(1).getVedioImgUrl(), imvTwo);
//                    }
//                } else if (i == 2) {
//                    if (item.getTrendInfoDTOList().get(2).getResourceType() == 1) {
//                        Batman.getInstance().fromNet(item.getTrendInfoDTOList().get(2).getImgUrl(), imvThr);
//                    } else {
//                        Batman.getInstance().fromNet(item.getTrendInfoDTOList().get(2).getVedioImgUrl(), imvThr);
//                    }
//                }
//            }
//        }
    }
}
