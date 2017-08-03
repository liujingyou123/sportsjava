package com.sports.limitsport.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;

/**
 * Created by liuworkmac on 17/8/3.
 */

public class IdentifyMainActivity extends BaseActivity {
    public static final int FLAG_IDENTFITY = 0x00001;
    public static final int FLAG_HOBBY = 0x00002;

    private int flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_identify);
        gotoFragment();
    }

    private void gotoFragment() {
        Intent intent = getIntent();
        if (intent != null) {
            flag = intent.getIntExtra("flag", FLAG_IDENTFITY);
        }

        switch (flag) {
            case FLAG_IDENTFITY:
                IdentifyFragment fragment = new IdentifyFragment();
                if (intent != null) {
                    String type = intent.getStringExtra("type");
                    Bundle bundle = new Bundle();
                    bundle.putString("type", type);
                    fragment.setArguments(bundle);
                }
                addFragment(fragment, true);
                break;
            case FLAG_HOBBY:
                SelectOwnHobbyFragment fragment1 = new SelectOwnHobbyFragment();
                if (intent != null) {
                    String type = intent.getStringExtra("type");
                    Bundle bundle = new Bundle();
                    bundle.putString("type", type);
                    fragment1.setArguments(bundle);
                }
                addFragment(fragment1, true);
                break;
        }
    }
}
