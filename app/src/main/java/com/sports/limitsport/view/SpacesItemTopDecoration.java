package com.sports.limitsport.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuworkmac on 17/7/11.
 */

public class SpacesItemTopDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemTopDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int postion = parent.getChildLayoutPosition(view);

        if (postion == 0) {
            outRect.top = space;
        }

    }
}
