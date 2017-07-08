package com.sports.limitsport.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuworkmac on 17/6/23.
 */

public class SpacesItemVDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemVDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = space;
    }
}
