package com.sports.limitsport.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/8/14.
 */

public class DealActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.tv_close)
    TextView tvClose;
    private int type; //1:用户协议，2:关于我们

    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        ButterKnife.bind(this);
        getIntentData();
        init();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getIntExtra("type", 1);
        }
    }

    private void init() {
        tvClose.setVisibility(View.GONE);

        if (type == 1) {
            tvFocusHouse.setText("用户协议");
            url = "file:///android_asset/user_service_deal.html";
        } else if (type == 2) {
            tvFocusHouse.setText("关于我们");
            url = "file:///android_asset/aboutus.htm";
//            tvContent.setText("极限领秀平台是为个人或组织提供活动、活动发布管理、参与功能的第三方平台，是上海XX网络科技有限公司拥有所有权与经营权的产品，包括XX.com、hudong.ba、hudongba.mobi、极限领秀客户端以及后续开发的其他产品。");
        }

        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(url);
    }


    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }
}
