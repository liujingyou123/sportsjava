package com.sports.limitsport.base;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/14.
 */

public class BaseSelectionAdapter<T extends SelectEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> implements BaseQuickAdapter.OnItemClickListener {
    public List<Integer> mSelectedPositions = new ArrayList<>();
    private boolean isSingleCheck;
    private OnItemSelectClickListener mOnItemSelectClickListener;

    public BaseSelectionAdapter(int layoutResId, @Nullable List<T> data, boolean isSingleCheck) {
        super(layoutResId, data);
        setOnItemClickListener(this);
        this.isSingleCheck = isSingleCheck;
    }

    public BaseSelectionAdapter(@Nullable List<T> data, boolean isSingleCheck) {
        super(data);
        setOnItemClickListener(this);
        this.isSingleCheck = isSingleCheck;
    }

    @Override
    protected void convert(K helper, T item) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (isSingleCheck) {
            if (mSelectedPositions != null) {
                if (mSelectedPositions.size() > 0) {
                    int selectPosition = mSelectedPositions.get(0);
                    if (selectPosition != position) {
                        ((T) adapter.getItem(selectPosition)).isSelect = false;
                        mSelectedPositions.clear();
                        mSelectedPositions.add(position);
                        ((T) adapter.getItem(position)).isSelect = true;
                        notifyDataSetChanged();
                    }
                } else {
                    mSelectedPositions.add(position);
                    ((T) adapter.getItem(position)).isSelect = true;
                    notifyDataSetChanged();
                }

            }
        } else {
            if (mSelectedPositions != null && mSelectedPositions.contains(position)) {
                mSelectedPositions.remove(mSelectedPositions.indexOf(position));
                ((T) adapter.getItem(position)).isSelect = false;
                notifyDataSetChanged();
            } else if (mSelectedPositions != null && !mSelectedPositions.contains(position)) {
                mSelectedPositions.add(position);
                ((T) adapter.getItem(position)).isSelect = true;
                notifyDataSetChanged();
            }
        }

        if (mOnItemSelectClickListener != null) {
            mOnItemSelectClickListener.onItemClick(adapter, view, position);
        }
    }

    public void setOnItemSelectClickListener(OnItemSelectClickListener onItemSelectClickListener) {
        this.mOnItemSelectClickListener = onItemSelectClickListener;
    }
    public interface OnItemSelectClickListener {
        void onItemClick(BaseQuickAdapter adapter, View view, int position);
    }
}
