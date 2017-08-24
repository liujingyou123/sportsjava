package com.sports.limitsport.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.util.SharedPrefsUtil;

/**
 * Created by liuworkmac on 17/7/28.
 */

public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, AdActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                LauncherActivity.this.finish();
//                Intent intent = null;
//                if (SharedPrefsUtil.getUserInfo() != null) {
//                    //TODO 调试用
////                    intent = new Intent(LauncherActivity.this, IdentifyFragment.class);
//                    intent = new Intent(LauncherActivity.this, MainActivity.class);
//                } else {
//                    intent = new Intent(LauncherActivity.this, LoginActivity.class);
//                }
//
//                startActivity(intent);
//
//
            }
        }, 200);
    }
}
