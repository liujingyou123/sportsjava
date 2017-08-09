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

public abstract class BaseSelectionAdapter<T extends SelectEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> implements BaseQuickAdapter.OnItemChildClickListener {
    public List<Integer> mSelectedPositions = new ArrayList<>();
    private boolean isSingleCheck;
    private OnItemChildSelectClickListener mOnItemSelectClickListener;

    public BaseSelectionAdapter(int layoutResId, @Nullable List<T> data, boolean isSingleCheck) {
        super(layoutResId, data);
        this.isSingleCheck = isSingleCheck;
        setOnItemChildClickListener(this);
    }

    public BaseSelectionAdapter(@Nullable List<T> data, boolean isSingleCheck) {
        super(data);
        this.isSingleCheck = isSingleCheck;
        setOnItemChildClickListener(this);
    }

    @Override
    protected void convert(K helper, T item) {
        helper.addOnClickListener(getSelectId());
        int position = helper.getAdapterPosition();
        if (mSelectedPositions != null && mSelectedPositions.indexOf(position) != -1 && !item.isSelect) {
            item.isSelect = true;
        }
    }


    public abstract int getSelectId();

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
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

    //    @Override
//    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        if (isSingleCheck) {
//            if (mSelectedPositions != null) {
//                if (mSelectedPositions.size() > 0) {
//                    int selectPosition = mSelectedPositions.get(0);
//                    if (selectPosition != position) {
//                        ((T) adapter.getItem(selectPosition)).isSelect = false;
//                        mSelectedPositions.clear();
//                        mSelectedPositions.add(position);
//                        ((T) adapter.getItem(position)).isSelect = true;
//                        notifyDataSetChanged();
//                    }
//                } else {
//                    mSelectedPositions.add(position);
//                    ((T) adapter.getItem(position)).isSelect = true;
//                    notifyDataSetChanged();
//                }
//
//            }
//        } else {
//            if (mSelectedPositions != null && mSelectedPositions.contains(position)) {
//                mSelectedPositions.remove(mSelectedPositions.indexOf(position));
//                ((T) adapter.getItem(position)).isSelect = false;
//                notifyDataSetChanged();
//            } else if (mSelectedPositions != null && !mSelectedPositions.contains(position)) {
//                mSelectedPositions.add(position);
//                ((T) adapter.getItem(position)).isSelect = true;
//                notifyDataSetChanged();
//            }
//        }
//
//        if (mOnItemSelectClickListener != null) {
//            mOnItemSelectClickListener.onItemClick(adapter, view, position);
//        }
//    }

    public void setOnItemChildSelectClickListener(OnItemChildSelectClickListener onItemSelectClickListener) {
        this.mOnItemSelectClickListener = onItemSelectClickListener;
    }

    public interface OnItemChildSelectClickListener {
        void onItemClick(BaseQuickAdapter adapter, View view, int position);
    }
}
