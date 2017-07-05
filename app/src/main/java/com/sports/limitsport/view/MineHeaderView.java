package com.sports.limitsport.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sports.limitsport.R;
import com.sports.limitsport.mine.UserInfoActivity;
import com.sports.limitsport.mine.adapter.TagFavAdapter;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/3.
 */

public class MineHeaderView extends LinearLayout {
    @BindView(R.id.tg)
    TagCloudLayout tg;

    public MineHeaderView(Context context) {
        super(context);
        init();
    }

    public MineHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MineHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_head_mine, this);
        ButterKnife.bind(this, this);
        setData();
    }

    private void setData() {
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mData.add("" + i);
        }
        tg.setAdapter(new TagFavAdapter(getContext(), mData));
    }

    @OnClick({R.id.rl_top, R.id.tv_fav})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_top:
                Intent intent = new Intent(getContext(), UserInfoActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.tv_fav:
                break;
        }
    }
}
