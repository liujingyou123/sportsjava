package com.sports.limitsport.discovery.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.model.FindClubSection;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.Club;

import java.util.List;


/**
 * Created by liuworkmac on 17/7/11.
 */

public class FindClubAdapter extends BaseQuickAdapter<Club, BaseViewHolder> {

    public FindClubAdapter(List<Club> data) {
        super(R.layout.adapter_myclubs, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Club club) {
        View view_data = helper.getView(R.id.rl_data);
        TextView tv_head = helper.getView(R.id.tv_head);
        ImageView imvHead = helper.getView(R.id.imv_head);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvDes = helper.getView(R.id.tv_location);
        TextView tvNumbers = helper.getView(R.id.tv_des);
        TextView tvTip = helper.getView(R.id.tv_tip);
        TextView tvFocus = helper.getView(R.id.tv_focus);

        if (club.isHeader) {
            tv_head.setVisibility(View.VISIBLE);
            view_data.setVisibility(View.GONE);
            tv_head.setText(club.getClubName());
        } else {
            tv_head.setVisibility(View.GONE);
            view_data.setVisibility(View.VISIBLE);
            Batman.getInstance().getImageWithCircle(club.getLogoUrl(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);
            tvName.setText(club.getClubName());
            tvDes.setText(club.getClubIntroduction());
            tvNumbers.setText(club.getMemberNum() + "成员");
            if ("1".equals(club.getIsActivity())) {//1:活动中 0:没有活动
                tvTip.setVisibility(View.VISIBLE);
            } else {
                tvTip.setVisibility(View.GONE);
            }

            if ("1".equals(club.getIsJoin())) { //1:已加入 0:未加入
                tvFocus.setEnabled(false);
                tvFocus.setText("已加入");
            } else {
                tvFocus.setEnabled(true);
                tvFocus.setText("了解");
            }
        }

    }
}
