package com.sports.limitsport.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sports.limitsport.R;
import com.sports.limitsport.util.SpUtil;

import java.lang.ref.WeakReference;

/**
 * Created by liuworkmac on 17/7/28.
 */

public class LauncherActivity extends AppCompatActivity {

    private TimerHandler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        handler = new TimerHandler(this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFist = SpUtil.getInstance().getBooleanData("isFirstLauncher", false);
                if (!isFist) {
                    Intent intent = new Intent(LauncherActivity.this, AdActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                    startActivity(intent);
                }
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

    public static class TimerHandler extends Handler {
        public WeakReference<LauncherActivity> activityWeakReference;

        public TimerHandler(LauncherActivity activity) {
            activityWeakReference = new WeakReference<LauncherActivity>(activity);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
