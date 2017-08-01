package com.sports.limitsport.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.model.EventBusLogin;
import com.sports.limitsport.model.UserInfo;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.NetworkUtil;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.StringUtil;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.CountDownButton;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import rx.Subscription;

/**
 * Created by liuworkmac on 17/7/18.
 * 登录
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.phone_view)
    EditText phoneView;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.verify_code_view_image)
    EditText verifyCodeViewImage;
    @BindView(R.id.pic_code)
    ImageView picCode;
    @BindView(R.id.image_verify_code)
    LinearLayout imageVerifyCode;
    @BindView(R.id.view_line1)
    View viewLine1;
    @BindView(R.id.verify_code_view)
    EditText verifyCodeView;
    @BindView(R.id.countDown)
    CountDownButton countDown;
    @BindView(R.id.ll_verify_code)
    LinearLayout llVerifyCode;
    @BindView(R.id.view_line2)
    View viewLine2;
    @BindView(R.id.login)
    TextView login;

    private String userPhone;
    private String smsVerifyCode;
    private String messageId = "1111111111";
//
//    private static final int CODE_LIMIT_COUNT = 3;// 单词获取验证码限制次数
//    private int requestCodeCount;//获取验证码次数

//    private boolean isFirstPic;

    private Subscription mLogSb;

    private String inputType;  //进入类型


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        getIntentData();
        initView();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            inputType = intent.getStringExtra("type");
        }
    }

    private void initView() {
        imageVerifyCode.setVisibility(View.GONE);
        phoneView.setFilters(new InputFilter[]{TextViewUtil.phoneFormat()});
        phoneView.setInputType(InputType.TYPE_CLASS_PHONE);
        verifyCodeView.setInputType(InputType.TYPE_CLASS_NUMBER);
        login.setEnabled(false);

        initCountDownButton();

        phoneView.addTextChangedListener(watcher);
        verifyCodeView.addTextChangedListener(watcher);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(UnitUtil.trim(phoneView.getText().toString()))
                    && !TextUtils.isEmpty(verifyCodeView.getText().toString().trim())
                    ) {
                login.setEnabled(true);
            } else {
                login.setEnabled(false);
            }

        }
    };

    // 倒计时
    private void initCountDownButton() {
        countDown.setOnUpdateTextListener(new CountDownButton.OnUpdateTextListener() {
            @Override
            public String onPreText() {
                return "获取验证码";
            }

            @Override
            public String onCountingText(int count) {
                return count + "s";
            }

            @Override
            public String onEndText() {
                return "重新获取";
            }
        });

        countDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check(false)) {
                    counting();
                    getVerifyCode();

//                    verifyCodeView.requestFocus();
//                    if (requestCodeCount >= CODE_LIMIT_COUNT) {
//                        showPicCode();
//                    } else {
//                        if (!isFirstPic) {// 如果还没有图片验证码,就预加载一张
//                            getPicCode();
//                        }
//                    }
                }
            }
        });
        countDown.setOnCountDownListener(new CountDownButton.OnCountDownListener() {
            @Override
            public void onFinish() {
//                if (phoneView.length() > 0) {
//                    clear.setVisibility(View.VISIBLE);
//                }
                phoneView.setEnabled(true);
            }

            @Override
            public void onTick(int time) {
//                clear.setVisibility(View.GONE);
                phoneView.setEnabled(false);
            }
        });
    }

    private void getVerifyCode() {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("userPhone", userPhone);
//        params.put("sendType", 0);//0-短信 1-语音，默认0
//        params.put("useScene", 0);//0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺
//        UserManager.getInstance().getVerifyCode(params, new NetworkCallback<Message>() {
//            @Override
//            public void success(Message response) {
//                messageId = response.data.messageId;
//                requestCodeCount++;
//                ToastUtil.show(context, "验证码发送成功");
//            }
//
//            @Override
//            public void failure(Throwable throwable) {
//                if (getView() == null) return;
//                countDown.reset();
//                ToastUtil.show(context, throwable.getMessage());
//            }
//        });
    }


    // 获取图片验证码
//    private void getPicCode() {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("useScene", 0);//0-登录 1-贷款申请 2-租铺签约 3-寻租申请 4-带我踩盘 5-商铺纠错 6-预约看铺
//        UserManager.getInstance().getPicCode(params, new NetworkCallback<ImageVerifyCode>() {
//            @Override
//            public void success(ImageVerifyCode response) {
//                if (response != null && response.isSuccess()) {
//                    isFirstPic = true;
//                    picVerifyId = response.data.picVerifyId;
//                    picVerifyCode = response.data.picVerifyCode;
//                    picCode.setImageBitmap(fromBase64(response.data.base64Str));
//                }
//            }
//
//            @Override
//            public void failure(Throwable throwable) {
//
//            }
//        });
//    }


    private boolean check(boolean login) {
        userPhone = UnitUtil.trim(phoneView.getText().toString().trim());
        smsVerifyCode = verifyCodeView.getText().toString().trim();
        if (!NetworkUtil.isNetworkConnected(context)) {
            ToastUtil.show(context, "没有开启网络");
            return false;
        }
        if (!StringUtil.isCellPhone(userPhone)) {
            if (login) {
                ToastUtil.show(context, "登录失败请重新登录");
            } else {
                ToastUtil.show(context, "请输入正确手机号");
            }
            return false;
        }

        return true;
    }

    private void counting() {
        countDown.start();
    }

//    private void showPicCode() {
//        if (!isFirstPic && imageVerifyCode.getVisibility() == View.GONE) {
//            getPicCode();
//        }
//        imageVerifyCode.setVisibility(View.VISIBLE);
//    }

    @OnClick({R.id.tv_skip, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_skip:
                if (TextViewUtil.isEmpty(inputType)) {
                    gotoMainActivity();
                }
                finish();
                break;
            case R.id.login:
//                gotoIdentifyActivity();
                if (check(false)) {
                    login();
                }
                break;
        }
    }

    private void login() {
        userPhone = UnitUtil.trim(phoneView.getText().toString().trim());
        smsVerifyCode = verifyCodeView.getText().toString().trim();
        HashMap<String, String> params = new HashMap<>();
        params.put("userPhone", userPhone);
        params.put("smsVerifyCode", smsVerifyCode);
        params.put("messageId", messageId);

        params.put("deviceId", JPushInterface.getRegistrationID(context.getApplicationContext()));
        params.put("osType", "1");//0-iOS 1-Android

        mLogSb = ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).loginIn(params), new LoadingNetSubscriber<UserInfo>() {
            @Override
            public void response(UserInfo response) {

                if (response != null && response.isSuccess() && response.getData() != null) {

                    response.getData().setUserPhone(userPhone);
                    SharedPrefsUtil.saveUserInfo(response);

                    if (TextViewUtil.isEmpty(inputType)) {
                        if (response.getData().getIsPerfect() == 0) { //已完善
                            gotoMainActivity();
                        } else {
                            gotoIdentifyActivity();
                        }
                    }
                    EventBusLogin params = new EventBusLogin();
                    params.isLogin = true;
                    EventBus.getDefault().post(params);
                    LoginActivity.this.finish();
                } else {
                    verifyCodeView.setText("");
                    ToastUtil.show(context, response == null ? "null response" : response.errMsg);
                }

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                verifyCodeView.setText("");
                ToastUtil.show(context, e.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLogSb != null && mLogSb.isUnsubscribed()) {
            mLogSb.unsubscribe();
        }
    }

    private void gotoIdentifyActivity() {
        Intent intent = new Intent(this, IdentifyActivity.class);
        startActivity(intent);
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
