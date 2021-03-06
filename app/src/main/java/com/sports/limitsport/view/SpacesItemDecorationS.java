package com.sports.limitsport.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liuworkmac on 17/7/10.
 */

public class SpacesItemDecorationS extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecorationS(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int postion = parent.getChildLayoutPosition(view);
        postion = postion - 1;

        outRect.bottom = space;

        if (postion != 0 && postion != 1 && postion != -1) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }

        if (postion == -1) {
            outRect.bottom = 20;
        }

        if (postion == 3 || postion == 2) {
            if (postion == 3) {
                outRect.left = 0;
                outRect.right = space;
            }

            if (postion == 2) {
                outRect.left = space;
                outRect.right = 0;
            }
        } else {
//                    XLog.e("postion + 1 = " + (postion + 1));

            int i = (postion + 1) % 2;
//        XLog.e("p(postion + 1) % 2 = " + i);

            if (i == 0) {
                outRect.left = space;
                outRect.right = 0;
            } else {
                outRect.left = 0;
                outRect.right = space;
            }
        }


    }
}