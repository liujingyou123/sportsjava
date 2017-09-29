package com.sports.limitsport.activity.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.sports.limitsport.activity.ui.IPayOrderView;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.OrderRequest;
import com.sports.limitsport.model.PayOrderResponse;
import com.sports.limitsport.model.PayResult;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.Ironman;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.Constants;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liuworkmac on 17/7/25.
 */

public class PayPresenter {
    private Subscription mPaySb;
    private Activity activity;
    private Alipay alipay;
    private IPayOrderView mIPayOrderView;
    private IWXAPI api;

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

    private IWXAPI getIWXpay() {
        if (api == null) {
            api = WXAPIFactory.createWXAPI(activity, Constants.APP_ID, true);
            api.registerApp(Constants.APP_ID);
        }
        return api;
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

    public void pay(String orderInfo, String payType) {
        if ("aliPay".equals(payType)) {
            aliPay(orderInfo);
        } else {
            weixinPay(orderInfo);
        }
    }

    /**
     * 提交支付订单
     *
     * @param request
     */
    public void payOrder(final OrderRequest request) {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).payOrder(request), new NetSubscriber<PayOrderResponse>() {
            @Override
            public void response(PayOrderResponse response) {
                if (response != null && response.data != null && !"1".equals(response.data.isFree)) {
                    pay(response.data.orderInfo, request.payType);
//                    if ("aliPay".equals(request.payType)) {
//                        aliPay(response.data.orderInfo);
//                    } else {
//                        weixinPay(response.data.orderInfo);
//                    }
                }
                if (mIPayOrderView != null) {
                    mIPayOrderView.showPayOrderResult(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPayOrderView != null) {
                    mIPayOrderView.showPayOrderResultFail(e);
                }
            }

            @Override
            public void error(PayOrderResponse response) {
                super.error(response);
                if (mIPayOrderView != null) {
                    mIPayOrderView.showPayOrderResult(response);
                }
            }
        });
    }

    private void weixinPay(final String orderInfo) {
        if (!isWXAppInstalledAndSupported()) {
            ToastUtil.show(activity, "您未安装微信客户端，无法使用微信支付");
            return;
        }
        final String orderInfotmp = orderInfo;
        String[] param = orderInfotmp.split("&");
        Observable.from(param).collect(new Func0<Map<String, String>>() {
            @Override
            public Map<String, String> call() {
                Map<String, String> map = new HashMap<String, String>();
                return map;
            }
        }, new Action2<Map<String, String>, String>() {
            @Override
            public void call(Map<String, String> map, String s) {
                String[] param = s.split("=");
                map.put(param[0], param[1]);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Map<String, String>>() {
            @Override
            public void call(Map<String, String> param) {
                pay(param);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    private void pay(Map<String, String> map) {
        PayReq request = new PayReq();
        request.appId = map.get("appid");
        request.partnerId = map.get("partnerid");
        request.prepayId = map.get("prepayid");
        request.packageValue = "Sign=WXPay";
        request.nonceStr = map.get("noncestr");
        request.timeStamp = map.get("timestamp");
        request.sign = map.get("sign");
        getIWXpay().sendReq(request);
    }

    private boolean isWXAppInstalledAndSupported() {
        boolean sIsWXAppInstalledAndSupported = getIWXpay().isWXAppInstalled();
        return sIsWXAppInstalledAndSupported;
    }

    public void clear() {
        if (mPaySb != null && !mPaySb.isUnsubscribed()) {
            mPaySb.unsubscribe();
        }
        mIPayOrderView = null;
    }

    public void comfirmPayResult(PayResult payResult) {
        Map<String, String> params = payResult.getRawResult();
        params.put("payType", "0");
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
        final String finalOrderNo = orderNo;
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).checkOrder(params), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIPayOrderView != null) {
                    mIPayOrderView.showPayResult(response.isSuccess(), finalOrderNo);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPayOrderView != null) {
                    mIPayOrderView.showPayResult(false, finalOrderNo);
                }
            }
        });

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
