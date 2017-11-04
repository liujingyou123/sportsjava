package com.sports.limitsport.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.view.H5Activity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 广告页
 */
public class AdvertActivity extends BaseActivity {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.skip)
    TextView skip;

    private int TIME = 1000;
    private Handler handler;
    private int i = 3;

    private ArrayList<AdvertiseInfoResponse.DataBean> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advert_activity);
        ButterKnife.bind(this);
        init();
    }


    private void init() {

        list = (ArrayList<AdvertiseInfoResponse.DataBean>) getIntent().getSerializableExtra("data");
        Batman.getInstance().fromNet(list.get(0).getAdPicUrl(), img);

        handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // handler自带方法实现定时器
                try {
                    if (i == 1) {
                        Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                        startActivity(intent);
                        AdvertActivity.this.finish();
//                        return;
                    }
                    handler.postDelayed(this, TIME);
                    skip.setText("跳过  " + (--i));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, TIME); //每隔1s执行


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @OnClick({R.id.img, R.id.skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                if(list!=null&&list.get(0)!=null){
                    if (!TextUtils.isEmpty(list.get(0).getToUrl()) && !"null".equals(list.get(0).getToUrl())) {
                        Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                        startActivity(intent);
                        Intent bannerDetails = new Intent(AdvertActivity.this, H5Activity.class);
                        bannerDetails.putExtra("type", 4);
                        bannerDetails.putExtra("url", list.get(0).getToUrl());
                        startActivity(bannerDetails);
                    }
                }
                AdvertActivity.this.finish();
                break;
            case R.id.skip:
                Intent intent = new Intent(AdvertActivity.this, MainActivity.class);
                startActivity(intent);
                AdvertActivity.this.finish();
                break;
        }
    }



}
