package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.NewNoticeResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.util.UnitUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jingyouliu on 17/11/12.
 * 成为商户
 */

public class BecomeBusinessSubmitActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_done)
    TextView tvDone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_business_submit);
        ButterKnife.bind(this);
        tvFocusHouse.setText("成为商户");
        etPhone.setFilters(new InputFilter[]{TextViewUtil.phoneFormat()});
        etPhone.setInputType(InputType.TYPE_CLASS_PHONE);
    }


    @OnClick({R.id.imv_focus_house_back, R.id.tv_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_done:
                submit();
                break;
        }
    }

    private void submit() {
        HashMap<String, String> hashMap = new HashMap<>();
        if (etName.getText() == null || TextUtils.isEmpty(etName.getText().toString())) {
            ToastUtil.showFalseToast(this, "请输入称呼");
            return;
        }
        if (etPhone.getText() == null || TextUtils.isEmpty(etPhone.getText().toString())) {
            ToastUtil.showFalseToast(this, "请输入手机号码");
            return;
        }
        hashMap.put("name", etName.getText().toString());
        hashMap.put("phone", UnitUtil.trim(etPhone.getText().toString().trim()));

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).becomeBusiness(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    ToastUtil.showFalseToast(BecomeBusinessSubmitActivity.this, "提交成功");
                } else {
                    ToastUtil.showFalseToast(BecomeBusinessSubmitActivity.this, "提交失败");
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.showFalseToast(BecomeBusinessSubmitActivity.this, "提交失败");
            }
        });
    }
}
