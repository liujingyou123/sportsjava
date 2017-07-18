package com.sports.limitsport.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class CountDownButton extends Button {
    private static final int COUNT = 60;//60 second default
    private Runnable mTicker;
    private Handler mHandler;

    private boolean mTickerStopped = false;

    private OnCountDownListener onCountDownListener;// 监听回调
    private OnUpdateTextListener updateTextListener;//文字更新
    private int count = 0;// 倒计时的步数
    private CharSequence text;// 原始文字

    public CharSequence sendText(CharSequence text) {
        return text;
    }

    public CountDownButton(Context context) {
        this(context, null);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        text = getText();
        setText(text);
        setAllCaps(false);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTickerStopped = true;
    }

    @Override
    protected void onAttachedToWindow() {
        mTickerStopped = false;
        mHandler = new Handler();
        super.onAttachedToWindow();
    }

    private void runCountButton() {
        /**
         * requests a tick on the next hard-second boundary
         */
        mTicker = new Runnable() {
            public void run() {
                if (mTickerStopped)
                    return;
                count--;
                if (onCountDownListener != null) {
                    onCountDownListener.onTick(count);
                }
                if (count <= 0) {
                    if (onCountDownListener != null) {
                        onCountDownListener.onFinish();
                    }
                    if (updateTextListener != null) {
                        setText(updateTextListener.onEndText());
                    }
                    setEnabled(true);
                    return;
                } else {
                    if (updateTextListener != null) {
                        setText(updateTextListener.onCountingText(count));
                    }
                }
                long now = SystemClock.uptimeMillis();
                long next = now + (1000 - now % 1000);
                mHandler.postAtTime(mTicker, next);
            }
        };
        mTicker.run();
    }

    /**
     * 倒计时监听
     */
    public interface OnCountDownListener {
        void onFinish();

        void onTick(int time);
    }

    public void setOnCountDownListener(OnCountDownListener onCountDownListener) {
        this.onCountDownListener = onCountDownListener;
    }

    /**
     * 倒计时文字更新
     */
    public interface OnUpdateTextListener {
        String onPreText();

        String onCountingText(int count);

        String onEndText();
    }

    public void setOnUpdateTextListener(OnUpdateTextListener updateTextListener) {
        this.updateTextListener = updateTextListener;
        if (updateTextListener != null) {
            setText(updateTextListener.onPreText());
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 0) {
            this.count = 0;
            return;
        }
        this.count = count;
    }

    public void start() {
        setCount(COUNT);
        setEnabled(false);
        runCountButton();
    }

    public void reset() {
        if (!mTickerStopped && count > 0) {
            count = 0;
        }
    }

    public void start(int count) {
        setCount(count);
        setEnabled(false);
        runCountButton();
    }
}
