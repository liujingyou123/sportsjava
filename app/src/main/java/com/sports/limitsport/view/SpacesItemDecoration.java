package com.sports.limitsport.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sports.limitsport.log.XLog;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int postion = parent.getChildLayoutPosition(view);


        XLog.e("position = " + view.getLeft());

        outRect.bottom = space;

        outRect.right = space;

//        if (postion != 0 && postion != 1) {
//            outRect.top = space;
//        } else {
//            outRect.top = 0;
//        }
//
//        if (postion == 3 || postion == 2) {
//            if (postion == 3) {
//                outRect.left = 0;
//                outRect.right = space;
//            }
//
//            if (postion == 2) {
//                outRect.left = space;
//                outRect.right = 0;
//            }
//        } else {
//            int i = (postion + 1) % 2;
//            if (i == 0) {
//                outRect.left = space;
//                outRect.right = 0;
//            } else {
//                outRect.left = 0;
//                outRect.right = space;
//            }
//        }


    }
}
