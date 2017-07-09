package com.sports.limitsport.main.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sports.limitsport.log.XLog;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class HobbySpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public HobbySpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = space;
        outRect.left = space;

//        outRect.bottom = space;
//        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
//        if (parent.getChildLayoutPosition(view) % 3 == 2) {
//            outRect.right = 0;
//        }
    }
}
