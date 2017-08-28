package com.sports.limitsport.base;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/8/28.
 */

public abstract class BaseSelectionByItemAdapter<T extends SelectEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> implements BaseQuickAdapter.OnItemChildClickListener {

    public List<T> mSelectedItems = new ArrayList<>();
    private boolean isSingleCheck;
    private BaseSelectionAdapter.OnItemChildSelectClickListener mOnItemSelectClickListener;

    public BaseSelectionByItemAdapter(int layoutResId, @Nullable List<T> data, boolean isSingleCheck) {
        super(layoutResId, data);
        this.isSingleCheck = isSingleCheck;
        setOnItemChildClickListener(this);
    }

    public BaseSelectionByItemAdapter(@Nullable List<T> data, boolean isSingleCheck) {
        super(data);
        this.isSingleCheck = isSingleCheck;
        setOnItemChildClickListener(this);
    }

    @Override
    protected void convert(K helper, T item) {
        helper.addOnClickListener(getSelectId());
        if (mSelectedItems != null && mSelectedItems.size() > 0 && mSelectedItems.indexOf(item) > -1 && !item.isSelect) {
            item.isSelect = true;
        }

//        int position = getData().indexOf(item);
//        //影响报名票务选择
//        if (mSelectedPositions != null && mSelectedPositions.indexOf(position) != -1 && !item.isSelect) {
//            item.isSelect = true;
//        }
    }


    public abstract int getSelectId();

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (isSingleCheck) {
            if (mSelectedItems != null) {
                T item = (T) adapter.getItem(position);
                if (mSelectedItems.size() > 0) {
                    T itemSelect = mSelectedItems.get(0);
                    if (!item.equals(itemSelect)) {
                        if (adapter.getData().indexOf(itemSelect) > -1) {
                            ((T) adapter.getData().get(adapter.getData().indexOf(itemSelect))).isSelect = false;
                        }
//                        ((T) adapter.getItem(selectPosition)).isSelect = false;
                        mSelectedItems.clear();
                        mSelectedItems.add(item);
                        ((T) adapter.getItem(position)).isSelect = true;
                        notifyDataSetChanged();
                    } else {
                        mSelectedItems.clear();
                        ((T) adapter.getItem(position)).isSelect = false;
                        notifyDataSetChanged();
                    }
                } else {
                    mSelectedItems.add(item);
                    ((T) adapter.getItem(position)).isSelect = true;
                    notifyDataSetChanged();
                }

            }
        } else {
            T item = (T) adapter.getItem(position);
            if (mSelectedItems != null && mSelectedItems.contains(item)) {
                mSelectedItems.remove(item);
                ((T) adapter.getItem(position)).isSelect = false;
                notifyDataSetChanged();
            } else if (mSelectedItems != null && !mSelectedItems.contains(item)) {
                mSelectedItems.add(item);
                ((T) adapter.getItem(position)).isSelect = true;
                notifyDataSetChanged();
            }
        }

        if (mOnItemSelectClickListener != null) {
            mOnItemSelectClickListener.onItemClick(adapter, view, position);
        }
    }

    public void setOnItemChildSelectClickListener(BaseSelectionAdapter.OnItemChildSelectClickListener onItemSelectClickListener) {
        this.mOnItemSelectClickListener = onItemSelectClickListener;
    }

    public interface OnItemChildSelectClickListener {
        void onItemClick(BaseQuickAdapter adapter, View view, int position);
    }
}
