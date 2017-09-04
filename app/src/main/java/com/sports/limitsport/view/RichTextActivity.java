package com.sports.limitsport.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/9/4.
 */

public class RichTextActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_text);
        ButterKnife.bind(this);
        tvFocusHouse.setText("图文详情");
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            String content = intent.getStringExtra("content");
            tvContent.setText(Html.fromHtml(content));
        }
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }
}
