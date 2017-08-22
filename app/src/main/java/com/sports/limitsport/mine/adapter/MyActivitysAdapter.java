package com.sports.limitsport.mine.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.OrdersList;
import com.sports.limitsport.util.UnitUtil;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class MyActivitysAdapter extends BaseQuickAdapter<OrdersList, BaseViewHolder> {
    private List<Subscription> mSubs = new ArrayList<>();
    private WeakReference<List<Subscription>> observableWeakReferences = new WeakReference<List<Subscription>>(mSubs);

    public MyActivitysAdapter(@Nullable List<OrdersList> data) {
        super(R.layout.adapter_myactivity, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdersList item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        TextView tvOrderNum = helper.getView(R.id.tv_ordernum);
        TextView tvStatus = helper.getView(R.id.tv_order_status);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTicketName = helper.getView(R.id.tv_ticket_type);
        TextView tvTicketPrice = helper.getView(R.id.tv_ticket_price);
        TextView tvTotalPrice = helper.getView(R.id.tv_all_price);
        RelativeLayout rlBtn = helper.getView(R.id.rl_btn);

        Batman.getInstance().fromNet(item.getCoverUrl(), imageView, R.mipmap.icon_ar_default, R.mipmap.icon_ar_default);

        tvOrderNum.setText("订单编号：" + item.getOrderNo());
        tvName.setText(item.getName());
        tvTicketName.setText("票务类型：" + item.getTicketsName());
        tvTicketPrice.setText(UnitUtil.formatSNum(item.getMoneyNews()) + " 元 （" + item.getNumber() + "张）");
        tvTotalPrice.setText("¥" + UnitUtil.formatSNum(item.getTotalMoney()));

        helper.addOnClickListener(R.id.rl_btn);

        rlBtn.removeAllViews();
        ;
        if (item.getIsJoin() == 1) { //已参加
            tvStatus.setText("已参加");
            rlBtn.addView(addTextView("秀动态"));
        } else {  //(0待支付 1已支付 2订单关闭3:已取消 4:退款中 5:已退款)
            if ("0".equals(item.getOrderStatus())) {
                tvStatus.setText("待支付");
//                tvPayPrice.setVisibility(View.VISIBLE);
                TextView textView = addTextView("");
                rlBtn.addView(textView);
                timeCountDown(item.getLaveSecond(), textView, item);
            } else if ("1".equals(item.getOrderStatus())) {
                tvStatus.setText("已报名");
            } else if ("2".equals(item.getOrderStatus())) {
                tvStatus.setText("已关闭");
            } else if ("3".equals(item.getOrderStatus())) {
                tvStatus.setText("已取消");
            } else if ("4".equals(item.getOrderStatus())) {
                tvStatus.setText("退款中");
            } else if ("5".equals(item.getOrderStatus())) {
                tvStatus.setText("已退款");
            }
        }


    }

    public void timeCountDown(long time, final TextView tvPayPrice, final OrdersList item) {
        if (time < 0) time = 0;
        final long countTime = time;
        Subscription subscription = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long increaseTime) {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take((int) (countTime + 1))
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        item.setLaveSecond(aLong.longValue());
                        tvPayPrice.setText("付款" + UnitUtil.secondsToTimeString(aLong));
                    }
                });

        mSubs.add(subscription);

    }

    public List<Subscription> getListSubs() {
        return mSubs;
    }

//                <TextView
//    android:id="@+id/tv_pay_price"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:background="@drawable/bg_done"
//    android:paddingBottom="5dp"
//    android:paddingLeft="5dp"
//    android:paddingRight="5dp"
//    android:paddingTop="5dp"
//    android:text="付款59:33"
//    android:textColor="#FFFFFFFF"
//    android:textSize="14sp" />

    private TextView addTextView(String text) {
        TextView textView = new TextView(mContext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setBackgroundResource(R.drawable.bg_done);
        int padding = UnitUtil.dip2px(mContext, 5);
        textView.setPadding(padding, padding, padding, padding);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setText(text);
        textView.setLayoutParams(lp);
        return textView;
    }
}
