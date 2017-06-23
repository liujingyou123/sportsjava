package com.sports.limitsport.activity.adapter;
/**
 * Created by liuworkmac on 17/5/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sports.limitsport.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TagDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData;

    public TagDetailAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mData != null) {
            ret = mData.size();
        }
        return 3;
    }

    @Override
    public String getItem(int i) {
        String ret = null;
        if (mData != null) {
            ret = mData.get(i);
        }
        return ret;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_tagdetail, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

//        Tag tag = mData.get(i);
//        if (tag != null) {
////            if (!TextUtils.isEmpty(tag.getColor())) {
////                viewHolder.tvTagDetail.setTextColor(Color.parseColor(tag.getColor()));
////                viewHolder.tvTagDetail.setBackgroundDrawable(getDrawable(tag.getColor()));
////
////            }
//            viewHolder.tvTagDetail.setText(mData.get(i));
//        }

//        viewHolder.tvTagDetail.setText(mData.get(i));

        return view;
    }


    static class ViewHolder {
        @BindView(R.id.tv_tag_detail)
        TextView tvTagDetail;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}