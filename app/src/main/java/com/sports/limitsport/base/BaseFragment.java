package com.sports.limitsport.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class BaseFragment extends Fragment {
    protected Context context;

    public void onStackTop(boolean isBack) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public String getName() {
        return this.getClass().getName() + this.hashCode();
    }

    @Override
    public void onPause() {
        super.onPause();
        hideSoftKeyboard();
//        MobclickAgent.onPageEnd(getClass().getSimpleName());

    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    protected void hideSoftKeyboard() {
        final View currentRoot = getView();
        if (currentRoot == null) return;
        final InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(currentRoot.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void forceSoftKeyboardVisible(boolean show) {
        BaseActivity base = (BaseActivity) getActivity();
        if (base != null) {
            base.forceSoftKeyBoardVisible(show);
        }
    }

    public boolean handleBack() {
        if (getActivity() == null) return false;
        return ((BaseActivity) getActivity()).popFragment();
    }

    public void pushFragment(BaseFragment fragment) {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).pushFragment(fragment);
    }

    public void replaceFragment(BaseFragment fragment) {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).replaceFragment(fragment);
    }


    public void addFragment(BaseFragment fragment, boolean addToBackStatck) {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).addFragment(fragment, addToBackStatck);
    }

    public void onFragmentEmpty() {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).onFragmentEmpty();
    }

    protected void clearFragmentStack() {
        if (getActivity() == null) return;
        ((BaseActivity) getActivity()).clearFragmentStack();
    }

    @Override
    public void startActivity(Intent intent) {
        if (getActivity() == null) return;
        super.startActivity(intent);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public boolean handleDispatchKeyEvent(KeyEvent event) {

        return false;
    }

//    LoadingDialog loading;
//
//    protected void showLoading() {
//        if (loading == null) {
//            loading = new LoadingDialog(context);
//        }
//        loading.setMessage("");
//        loading.show();
//    }
//
//    protected void showLoading(CharSequence msg) {
//        if (loading == null) {
//            loading = new LoadingDialog(context);
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

//    //获取区域板块信息
//    protected List<DistrictBlocks.DataBean> getDistricts() {
//        ConsultantApplication app = ConsultantApplication.getInstance();
//        if (app != null) {
//            return app.getDistrict();
//        }
//        return null;
//    }
//
//    //缓存区域板块信息
//    protected void setDistrict(List<DistrictBlocks.DataBean> district) {
//        ConsultantApplication app = ConsultantApplication.getInstance();
//        if (app != null) {
//            app.setDistrict(district);
//        }
//    }

}
