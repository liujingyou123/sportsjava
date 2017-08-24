package com.sports.limitsport.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.sports.limitsport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 16/9/27.
 */
public class AdFragment extends Fragment {

    @BindView(R.id.imv_bg)
    ImageView imvBg;
    @BindView(R.id.btn_click)
    Button btnClick;

    public static AdFragment newInstance(int index) {
        AdFragment adFragment = new AdFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        adFragment.setArguments(bundle);
        return adFragment;
    }

    private int index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ad, null);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt("index");
        }

        if (index == 1) {
            imvBg.setImageResource(R.mipmap.launch_one);
            btnClick.setVisibility(View.GONE);
        } else if (index == 2) {
            imvBg.setImageResource(R.mipmap.launch_two);
            btnClick.setVisibility(View.GONE);
        } else if (index == 3) {
            imvBg.setImageResource(R.mipmap.launch_three);
            btnClick.setVisibility(View.GONE);
        } else if (index == 4) {
            imvBg.setImageResource(R.mipmap.launch_four);
            btnClick.setVisibility(View.GONE);
        } else if (index == 5) {
            imvBg.setImageResource(R.mipmap.launch_five);
            btnClick.setVisibility(View.GONE);
        } else if (index == 6) {
            imvBg.setImageResource(R.mipmap.launch_six);
            btnClick.setVisibility(View.VISIBLE);
        }

        return view;
    }


    @OnClick(R.id.btn_click)
    public void onClick() {
        gotoActivity();
    }


    private void gotoActivity() {
        startActivity(new Intent(AdFragment.this.getActivity(), MainActivity.class));
        AdFragment.this.getActivity().finish();
    }

    @Override
    public void onDestroy() {
        Log.e("AdFragment", "onDestroy");
        if (imvBg != null) {
            imvBg.setImageDrawable(null);
        }
        imvBg = null;
        btnClick = null;
        super.onDestroy();
    }
}
