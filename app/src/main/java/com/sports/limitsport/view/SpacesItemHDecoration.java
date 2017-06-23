package com.sports.limitsport.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class SpacesItemHDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemHDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildLayoutPosition(view);
        if (position == 0) {
            outRect.left = 0;
        } else {
            outRect.left = space;
        }
    }
}
