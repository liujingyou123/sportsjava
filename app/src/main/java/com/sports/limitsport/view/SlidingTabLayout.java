package com.sports.limitsport.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.util.UnitUtil;

import java.util.List;


/**
 * Created by moon.zhong on 2015/3/9.
 */
public class SlidingTabLayout extends LinearLayout {

    private OnTabItemClickListener onTabItemClickListener;
    /*默认的页卡颜色*/
    private final int DEFAULT_INDICATOR_COLOR = 0xffff00ff;
    /*默认分割线的颜色*/
    private final int DEFAULT_DIVIDER_COLOR = 0xff000000;
    /*默认title字体的大小*/
    private final int DEFAULT_TEXT_SIZE = 14;
    /*默认padding*/
    private final int DEFAULT_TEXT_PADDING = 26;
    /*divider默认的宽度*/
    private final int DEFAULT_DIVIDER_WIDTH = 1;
    /*indicator 的高度*/
    private final int DEFAULT_INDICATOR_HEIGHT = 5;
    /*indicator 的宽度*/
    private final int DEFAULT_INDICATOR_WIDTH = 20;
    /*底部线条的高度默认值*/
    private final int DEFAULT_BOTTOM_LINE_HEIGHT = 2;
    /*分割线距离上下边缘的距离默认为8*/
    private final int DEFAULT_DIVIDER_MARGIN = 8;
    /*底部线条的颜色默认值*/
    private final int DEFAULT_BOTTOM_LINE_COLOR = 0xff000000;
    /*tab标题未选中的颜色默认值*/
    private final int DEFAULT_TAB_TITLE_COLOR = 0xff96b3f3;
    /*tab标题选中的颜色默认值*/
    private final int DEFAULT_TAB_TITLE_COLOR_SELECT = 0xff2c67e6;
    /*页面*/
    private ViewPager mViewPager;
    /*页面切换监听事件*/
    private ViewPager.OnPageChangeListener mListener;
    /*页卡的颜色*/
    private int mIndicatorColor = DEFAULT_INDICATOR_COLOR;
    /*分割线的颜色*/
    private int mDividerColor = DEFAULT_DIVIDER_COLOR;
    /*分割线距离上线边距的距离*/
    private int mDividerMargin = DEFAULT_DIVIDER_MARGIN;
    /*页卡画笔*/
    private Paint mIndicatorPaint;
    /*分割线画笔*/
    private Paint mDividerPaint;
    /*获取 tab 每个 item 的信息*/
    private TabItemName mItemName;
    /*当前选中的页面位置*/
    private int mSelectedPosition;
    /*页面的偏移量*/
    private float mSelectionOffset;
    /*底部线条的颜色*/
    private int mBottomLineColor = DEFAULT_BOTTOM_LINE_COLOR;
    /*底部线条的高度*/
    private int mBottomLineHeight = DEFAULT_BOTTOM_LINE_HEIGHT;
    /*滑动指示器的高度*/
    private int mIndicatorHeight = DEFAULT_INDICATOR_HEIGHT ;
    /*滑动指示器的宽度*/
    private int mIndicatorWidth = DEFAULT_INDICATOR_WIDTH ;
    /*分割线的宽度*/
    private int mDividerWidth = DEFAULT_DIVIDER_WIDTH;
    /*底部线条的画笔*/
    private Paint mBottomPaint ;
    /*默认title字体的大小*/
    private int mTextSize = DEFAULT_TEXT_SIZE;

    /*tab标题没选中的颜色*/
    private int mTabNonSelectColor = DEFAULT_TAB_TITLE_COLOR;
    /*tab标题没选中的颜色*/
    private int mTabSelectColor = DEFAULT_TAB_TITLE_COLOR_SELECT;

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*获取TypedArray*/
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.SlidingTabLayout);
        /*获取自定义属性的个数*/
        int N = typedArray.getIndexCount();
        Log.v("zgy","=========getIndexCount========="+N) ;
        for (int i = 0; i < N; i++) {
            int attr = typedArray.getIndex(i);
            if(attr == R.styleable.SlidingTabLayout_indicatorColor){
                /*获取页卡颜色值*/
                mIndicatorColor = typedArray.getColor(attr, DEFAULT_INDICATOR_COLOR);
            }
            else if(attr == R.styleable.SlidingTabLayout_dividerColor){
                mDividerColor = typedArray.getColor(attr, DEFAULT_DIVIDER_COLOR);
            }
            else if(attr == R.styleable.SlidingTabLayout_bottomLineColor){
                mBottomLineColor = typedArray.getColor(attr, DEFAULT_BOTTOM_LINE_COLOR);
            }
            else if(attr == R.styleable.SlidingTabLayout_dividerMargin){
                mDividerMargin = (int) typedArray.getDimension(attr, DEFAULT_DIVIDER_MARGIN * getResources().getDisplayMetrics().density);
                Log.v("zgy","=========mDividerMargin========="+mDividerMargin) ;
            }
            else if(attr == R.styleable.SlidingTabLayout_indicatorHeight){
                mIndicatorHeight = (int) typedArray.getDimension(attr, DEFAULT_INDICATOR_HEIGHT * getResources().getDisplayMetrics().density);
            }
            else if(attr == R.styleable.SlidingTabLayout_bottomLineHeight){
                mBottomLineHeight = (int) typedArray.getDimension(attr, DEFAULT_BOTTOM_LINE_HEIGHT * getResources().getDisplayMetrics().density);
            }
            else if(attr == R.styleable.SlidingTabLayout_dividerWidth){
                mDividerWidth = (int) typedArray.getDimension(attr, DEFAULT_DIVIDER_WIDTH * getResources().getDisplayMetrics().density);
            }
            else if(attr == R.styleable.SlidingTabLayout_tabTitleColor){
                mTabNonSelectColor = typedArray.getColor(attr, DEFAULT_TAB_TITLE_COLOR);
            }
            else if(attr == R.styleable.SlidingTabLayout_tabTitleColorSelect){
                mTabSelectColor = typedArray.getColor(attr, DEFAULT_TAB_TITLE_COLOR_SELECT);
            } else if (attr == R.styleable.SlidingTabLayout_indicatorWidth) {
                mIndicatorWidth = (int) typedArray.getDimension(attr, DEFAULT_INDICATOR_HEIGHT * getResources().getDisplayMetrics().density);
            } else if (attr == R.styleable.SlidingTabLayout_tabTextSize) {
                mTextSize = UnitUtil.px2sp(getContext(), typedArray.getDimensionPixelSize(attr, DEFAULT_TEXT_SIZE));
            }


        }
        /*释放TypedArray*/
        typedArray.recycle();
        initView();
    }

    private void initView() {
        setWillNotDraw(false);

        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setColor(mDividerColor);
        mDividerPaint.setStrokeWidth(mDividerWidth);

        mIndicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatorPaint.setColor(mIndicatorColor);

        mBottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBottomPaint.setColor(mBottomLineColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getChildCount() == 0) {
            return;
        }
        final int height = getHeight();
        /*当前页面的View tab*/
        View selectView = getChildAt(mSelectedPosition);
        /*计算开始绘制的位置*/
        int left = selectView.getLeft();
        /*计算结束绘制的位置*/
        int right = selectView.getRight();
        if (mSelectionOffset > 0) {
            View nextView = getChildAt(mSelectedPosition + 1);
            /*如果有偏移量，重新计算开始绘制的位置*/
            left = (int) (mSelectionOffset * nextView.getLeft() + (1.0f - mSelectionOffset) * left);
            /*如果有偏移量，重新计算结束绘制的位置*/
            right = (int) (mSelectionOffset * nextView.getRight() + (1.0f - mSelectionOffset) * right);
        }
        /*绘制滑动的页卡*/
        canvas.drawRect(left+(right-left)/2-mIndicatorWidth, height - mIndicatorHeight-15, right-(right-left)/2+mIndicatorWidth, height-15, mIndicatorPaint);
        canvas.drawRect(0,height - mBottomLineHeight,getWidth(),height,mBottomPaint);
//        for (int i = 0; i < getChildCount() - 1; i++) {
//            View child = getChildAt(i);
//            canvas.drawLine(child.getRight(), mDividerMargin,
//                    child.getRight(), height - mDividerMargin,
//                    mDividerPaint);
//        }
    }

    /**
     * 设置viewPager，初始化SlidingTab，
     * 在这个方法中为SlidingLayout设置
     * 内容，
     *
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        /*先移除所以已经填充的内容*/
        removeAllViews();
        /* viewPager 不能为空*/
        if (viewPager == null) {
            throw new RuntimeException("ViewPager不能为空");
        }
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(new InternalViewPagerChange());
        populateTabLayout();
    }

    public void setViewPager(ViewPager viewPager, int size) {
        /*先移除所以已经填充的内容*/
        removeAllViews();
        /* viewPager 不能为空*/
        if (viewPager == null) {
            throw new RuntimeException("ViewPager不能为空");
        }
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(new InternalViewPagerChange());
        populateTabLayout(size);
    }

    public void setViewPagerOnChangeListener(ViewPager.OnPageChangeListener pagerOnChangeListener) {
        mListener = pagerOnChangeListener;
    }

    public void setTabTitle(List<String> list){
        final OnClickListener tabOnClickListener = new TabOnClickListener();
        for (int i = 0; i < list.size(); i++) {
            TextView textView = createDefaultTabView(getContext(),list.size());
            textView.setOnClickListener(tabOnClickListener);
            textView.setText(list.get(i));
            addView(textView);
        }

    }

    private void populateTabLayout(int size) {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final OnClickListener tabOnClickListener = new TabOnClickListener();
        mItemName = (TabItemName) adapter;
        for (int i = 0; i < adapter.getCount(); i++) {
            TextView textView = createDefaultTabView(getContext(),adapter.getCount(), size);
            textView.setOnClickListener(tabOnClickListener);
            textView.setText(mItemName.getTabName(i));
            if (i ==0 ) {
                textView.setTextColor(mTabSelectColor);
            }
            addView(textView);
        }

    }

    /**
     * 填充layout，设置其内容
     */
    private void populateTabLayout() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final OnClickListener tabOnClickListener = new TabOnClickListener();
        mItemName = (TabItemName) adapter;
        for (int i = 0; i < adapter.getCount(); i++) {
            TextView textView = createDefaultTabView(getContext(),adapter.getCount());
            textView.setOnClickListener(tabOnClickListener);
            textView.setText(mItemName.getTabName(i));
            addView(textView);
        }
    }

    private TextView createDefaultTabView(Context context,int size, int width) {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        TextView textView = new TextView(context);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
        textView.setGravity(Gravity.CENTER);

        layoutParams = new LayoutParams(width/size, LayoutParams.MATCH_PARENT);


        textView.setLayoutParams(layoutParams);
        textView.setTextColor(mTabNonSelectColor);
        textView.setAllCaps(true);
        return textView;
    }
    /**
     * 创建默认的TabItem
     *
     * @param context
     * @return
     */
    private TextView createDefaultTabView(Context context,int size) {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        TextView textView = new TextView(context);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
        textView.setGravity(Gravity.CENTER);
        if(size==1){
            layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            textView.setVisibility(View.GONE);
        }else if(size==2){
            layoutParams = new LayoutParams(getResources().getDisplayMetrics().widthPixels/2, LayoutParams.MATCH_PARENT);
        }else if(size==3){
            layoutParams = new LayoutParams(getResources().getDisplayMetrics().widthPixels/3, LayoutParams.MATCH_PARENT);
        }else if(size>=4){
            layoutParams = new LayoutParams(getResources().getDisplayMetrics().widthPixels/4, LayoutParams.MATCH_PARENT);
        }


//        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        textView.setLayoutParams(layoutParams);
//        int padding = (int) (DEFAULT_TEXT_PADDING * getResources().getDisplayMetrics().density);
//        textView.setPadding(padding, padding, padding, padding);
        textView.setTextColor(mTabNonSelectColor);
        textView.setAllCaps(true);
        return textView;
    }

    private class TabOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < getChildCount(); i++) {
                if (v == getChildAt(i)) {
                    if(mViewPager!=null){

                        mViewPager.setCurrentItem(i);
                    }
                    if(onTabItemClickListener!=null){
                        viewPagerChange(i,0.0f);
                        onTabItemClickListener.clickItem(i);
                    }
                    return;
                }
            }
        }
    }

    /**
     * @param position
     * @param positionOffset
     */
    private void viewPagerChange(int position, float positionOffset) {
        mSelectedPosition = position;
        mSelectionOffset = positionOffset;
//        for (int i=0;i<getChildCount();i++){
//            if(i==position){
//                ((TextView)getChildAt(i)).setTextColor(mTabSelectColor);
//            }
//            else{
//                ((TextView)getChildAt(i)).setTextColor(mTabNonSelectColor);
//            }
//        }


        invalidate();
    }

    private class InternalViewPagerChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            Log.v("zgy", "=============position=" + position + ",====positionOffset=" + positionOffset);
            /*
            * 规律：
            * 当positionOffset为0时，position就是当前view的位置
            * 当positionOffset不为0时，position为左边页面的位置
            *                         position + 1为右边页面的位置
            * */

            viewPagerChange(position, positionOffset);
            if (mListener != null) {
                mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {

            if (mListener != null) {
                mListener.onPageSelected(position);
            }

            for (int i=0;i<getChildCount();i++){
                if(i==position){
                    ((TextView)getChildAt(i)).setTextColor(mTabSelectColor);
                }
                else{
                    ((TextView)getChildAt(i)).setTextColor(mTabNonSelectColor);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

            if (mListener != null) {
                mListener.onPageScrollStateChanged(state);
            }
        }
    }

    /**
     * 回调获取 item name 的接口
     */
    public interface TabItemName {
        /**
         * 获取 tab name
         *
         * @param position
         * @return
         */
        String getTabName(int position);
    }

    public void setOnTabItemClickListener(OnTabItemClickListener onTabItemClickListener){
        this.onTabItemClickListener = onTabItemClickListener;
    }

    public interface OnTabItemClickListener{
        void clickItem(int position);
    }
}
