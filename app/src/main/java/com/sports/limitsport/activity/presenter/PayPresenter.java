package com.sports.limitsport.activity.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.sports.limitsport.model.PayResult;
import com.sports.limitsport.net.Ironman;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;

import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by liuworkmac on 17/7/25.
 */

public class PayPresenter {
    private Subscription mPaySb;
    private Activity activity;
    private Alipay alipay;

    private Alipay getAlipay() {
        if (alipay == null) {
            alipay = new Alipay(activity);
        }
        return alipay;
    }

    private void aliPay(String orderInfo) {
        unsubscribePay();
        final String orderInfotmp = orderInfo;
        mPaySb = Observable.just(orderInfo)
                .map(new Func1<String, PayResult>() {
                    @Override
                    public PayResult call(String s) {
                        return getAlipay().doPay(s);
                    }
                }).compose(ToolsUtil.<PayResult>applayScheduers()).subscribe(new Action1<PayResult>() {
                    @Override
                    public void call(PayResult payResult) {
                        if (doPayResult(payResult)) {
                            comfirmPayResult(payResult.getRawResult());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        unsubscribePay();
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        unsubscribePay();
                    }
                });

    }


    /**
     * ＊ 验签
     */
    public void comfirmPayResult(Map<String, String> params) {
//        params.put("payType", payType);
//        mConfirmResult = Ironman.getInstance()
//                .createConsultantService(OrderService.class)
//                .comfirmPayResult(BaseRequestParams.toJsonString(params))
//                .compose(ToolsUtil.<BaseResponse>applayScheduers())
//                .subscribe(new NetSubscriber<BaseResponse>() {
//                    @Override
//                    public void response(BaseResponse response) {
//                        if (iPayOrderView != null) {
//                            iPayOrderView.onConfirmResult(response.isSuccess());
//                        }
//                    }
//                });

    }

    /**
     * 处理支付结果
     *
     * @param payResult
     */
    public boolean doPayResult(PayResult payResult) {
        if (payResult == null) {
            return false;
        }
        /**
         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
         */
        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
        String resultStatus = payResult.getResultStatus();
//        Log.e("pay", resultStatus);
        // 判断resultStatus 为9000则代表支付成功
        if (TextUtils.equals(resultStatus, "9000")) {
            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
            ToastUtil.show(activity, "支付成功");
//            Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            return true;
        } else if (TextUtils.equals(resultStatus, "6001")) {
            ToastUtil.show(activity, "取消支付");
            return false;
        } else {
//            Log.e("pay", "支付失败");
            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
            ToastUtil.show(activity, "支付失败");
            return false;
        }
    }

    private void unsubscribePay() {
        if (mPaySb != null && !mPaySb.isUnsubscribed()) {
            mPaySb.unsubscribe();
        }
    }

}
