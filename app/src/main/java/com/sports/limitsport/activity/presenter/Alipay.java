package com.sports.limitsport.activity.presenter;

import android.app.Activity;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.sports.limitsport.model.PayResult;

import java.util.Map;

/**
 * Created by liuworkmac on 17/4/5.
 * 支付宝
 */

public class Alipay {
    private Activity activity;
    public Alipay(Activity activity) {
        this.activity = activity;
    }



    /**
     * 支付
     * @param orderInfo
     * @return
     */
    public PayResult doPay(String orderInfo) {
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        PayTask alipay = new PayTask(activity);
        Map<String, String> result = alipay.payV2(orderInfo, true);
        Log.i("msp", result.toString());
        return new PayResult(result);
    }


//    /**
//     * 处理支付结果
//     * @param payResult
//     */
//    public boolean doPayResult(PayResult payResult) {
//        if (payResult == null) {
//            return false;
//        }
//        /**
//         对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//         */
//        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//        String resultStatus = payResult.getResultStatus();
//        // 判断resultStatus 为9000则代表支付成功
//        if (TextUtils.equals(resultStatus, "9000")) {
//            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
////            ToastUtil.show(activity,"支付成功");
////            Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//            return true;
//        } else {
//            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//            ToastUtil.show(activity,"支付失败");
//            return false;
//
////            Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void exitPay() {
        activity = null;
    }
}
