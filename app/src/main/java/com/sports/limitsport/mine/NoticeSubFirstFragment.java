package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/5.
 */

public class NoticeSubFirstFragment extends Fragment {
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticesubfirst, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_huodong, R.id.rl_sys})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_huodong:
                gotoList(1);
                break;
            case R.id.rl_sys:
                gotoList(2);
                break;
        }
    }

    private void gotoList(int type) {
        Intent intent = new Intent(this.getContext(), NoticeListActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
