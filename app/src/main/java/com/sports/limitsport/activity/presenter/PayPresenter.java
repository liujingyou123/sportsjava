package com.sports.limitsport.activity.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.sports.limitsport.activity.ui.IPayOrderView;
import com.sports.limitsport.model.OrderRequest;
import com.sports.limitsport.model.PayOrderResponse;
import com.sports.limitsport.model.PayResult;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.Ironman;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
    private IPayOrderView mIPayOrderView;

    public PayPresenter(IPayOrderView mIPayOrderView) {
        this.activity = (Activity) mIPayOrderView;
        this.mIPayOrderView = mIPayOrderView;
    }

    private Alipay getAlipay() {
        if (alipay == null) {
            alipay = new Alipay(activity);
        }
        return alipay;
    }

    public void aliPay(String orderInfo) {
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
                            comfirmPayResult(payResult);
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
     * 提交支付订单
     *
     * @param request
     */
    public void payOrder(OrderRequest request) {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).payOrder(request), new NetSubscriber<PayOrderResponse>() {
            @Override
            public void response(PayOrderResponse response) {
                if (mIPayOrderView != null) {
                    mIPayOrderView.showPayOrderResult(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPayOrderView != null) {
                    mIPayOrderView.onError(e);
                }
            }
        });
    }

    public void clear() {
        if (mPaySb != null && !mPaySb.isUnsubscribed()) {
            mPaySb.unsubscribe();
        }
        mIPayOrderView = null;
    }

    /**
     * ＊ 验签
     */
    public void comfirmPayResult(PayResult payResult) {
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

        String result = payResult.getResult();
        String orderNo = null;
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                orderNo = jsonObject.optString("out_trade_no");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (mIPayOrderView != null) {

            mIPayOrderView.showPayResult(true, orderNo);
        }

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
