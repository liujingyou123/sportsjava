package com.sports.limitsport.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.util.TextViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/7/4.
 */

public class TagFavAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mData;

    public TagFavAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mData != null) {
            ret = mData.size();
        }
        return ret;
    }

    @Override
    public String getItem(int position) {
        String ret = null;
        if (mData != null && mData.size() > 0) {
            ret = mData.get(position);
        }
        return ret;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tag_fav, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        viewHolder.tvTagsA.setText("dddddd");


        String tmp = mData.get(position);
        viewHolder.tvTagsF.setText(tmp);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_tags_f)
        TextView tvTagsF;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
