package com.sports.limitsport.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.sports.limitsport.R;
import com.sports.limitsport.main.MainActivity;
import com.sports.limitsport.util.StatusBarUtil;

import java.util.List;


public class BaseActivity extends AppCompatActivity {
    protected Context context;
    private static final String TAG = "BaseActivity";
    private int stackSize;
    protected ExitController exitCtrl = new ExitController();
    protected static boolean isInBackground = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager
                .OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                BaseFragment fragment = getCurrentFragment();
                if (fragment == null) return;
                int newStackSize = getSupportFragmentManager().getBackStackEntryCount();
                fragment.onStackTop(newStackSize < stackSize);
                stackSize = newStackSize;
            }
        });
//        RxBus.get().register(this);
//        SelectDialogUtil.getInstance().init(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
    }

    protected void setStatusBar(int color) {
//        StatusBarUtil.setColor(this, color, 0);
    }

    public class ExitController {
        private static final int TIME_GAP = 2500;
        private long lastBackEventTime;

        public boolean requestExit() {
            long currentTime = System.currentTimeMillis();
            if (lastBackEventTime == 0 || currentTime <= lastBackEventTime || (currentTime -
                    lastBackEventTime) >= TIME_GAP) {
                lastBackEventTime = currentTime;
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                return false;
            }
            try {
                return true;
            } finally {
                lastBackEventTime = 0;
            }
        }

    }

    public void onResume() {
        super.onResume();
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void pushFragment(BaseFragment fragment) {
        String tag = fragment.getClass().getName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int count = fm.getBackStackEntryCount();
        if (count >= 1) {

            ft.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit);
        }
        ft.replace(R.id.rl_fragment_content, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    protected void replaceFragment(BaseFragment fragment) {
        String tag = fragment.getClass().getName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int count = fm.getBackStackEntryCount();
        if (count >= 1) {

            ft.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit);
        }
//        ft.setCustomAnimations(R.anim.activity_open_enter,R.anim.activity_open_exit,R.anim.activity_close_enter,R.anim.activity_close_exit);
        ft.replace(R.id.rl_fragment_content, fragment, tag);
        ft.commit();
    }


    protected void addFragment(BaseFragment fragment, boolean addToBackStack) {
        String tag = fragment.getClass().getName();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int count = fm.getBackStackEntryCount();
        if (count >= 1) {

            ft.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit);
        }
        if (fm.findFragmentByTag(tag) == null) {
            ft.add(R.id.rl_fragment_content, fragment, tag);
        }
        ft.show(fragment);
        List<Fragment> list = fm.getFragments();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null && list.get(i) != fragment) {
                    ft.hide(list.get(i));
                }
            }
        }
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    protected boolean popFragment() {
        FragmentManager fm = getSupportFragmentManager();
        final int entryCount = fm.getBackStackEntryCount();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit);
        boolean popSucceed = true;
        if (entryCount <= 1) {
            onFragmentEmpty();
        } else {
            popSucceed = fm.popBackStackImmediate();
            ft.commit();
        }

        return popSucceed;
    }

    protected void onFragmentEmpty() {
        finish();
    }

    protected void clearFragmentStack() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public BaseFragment getCurrentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        return (BaseFragment) fm.findFragmentById(R.id.rl_fragment_content);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && this instanceof MainActivity) {
            if (exitCtrl.requestExit()) {
                exit();
            }

            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            handleBack();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        BaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null && currentFragment.handleDispatchKeyEvent(event)) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

//    private boolean handlHomeBack() {
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context
//                    .INPUT_METHOD_SERVICE);
//            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//
//        BaseFragment currentFragment = getCurrentFragment();
//
//        if (currentFragment instanceof HomeFragment) {
//            try {
//                if (currentFragment != null) {
//                    return currentFragment.handleBack();
//                } else {
//                    return false;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return false;
//    }

    protected void handleBack() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        BaseFragment currentFragment = getCurrentFragment();

        try {
            if (currentFragment != null) {
                if (!currentFragment.handleBack()) {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                        popFragment();
                    }
                }
            } else {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void exit() {
        finish();
    }

    protected void overrideQuiteTransition() {
        overridePendingTransition(0, 0);
    }

    @Override
    public void finish() {
        super.finish();
//        overrideQuiteTransition();
    }


    public void setWindowAlpha(boolean transparent) {
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        if (transparent) {
            wl.alpha = 0.5f;
        } else {
            wl.alpha = 1.0f;
        }
        window.setAttributes(wl);
    }


    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    protected void forceSoftKeyBoardVisible(boolean show) {
        getWindow().setSoftInputMode(show ? WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE : WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void showSoftInput(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

//    LoadingDialog loading;
//    protected void showLoading() {
//        if (loading == null) {
//            loading = new LoadingDialog(this);
//        }
//        loading.show();
//    }
//
//    protected void showLoading(CharSequence msg) {
//        if (loading == null) {
//            loading = new LoadingDialog(this);
//        }
//        loading.setMessage(msg);
//        loading.show();
//    }
//
//    protected void hideLoading() {
//        if (loading != null) {
//            loading.dismiss();
//        }
//    }

}
