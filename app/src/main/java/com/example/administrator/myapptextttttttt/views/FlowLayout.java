package com.example.administrator.myapptextttttttt.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Author  ：龙 on 2017/3/3 13:56
 * Function: 流式布局
 */
public class FlowLayout extends ViewGroup {

    private OnViewClickListener listener;

    private View checkChild = null;//点击的子view
    private int position = -1;//点击子view的位置

    public FlowLayout(Context context) {
        super(context);
        init();
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
//        Log.e("measure", "---------------" +
//                "\nwSize =" + wSize +
//                "\nhSize =" + hSize +
//                "\nwMode =" + wMode +
//                "\nhMode =" + hMode +
//                "\n---------------");
        int count = getChildCount();
//        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        //如果当前ViewGroup的宽高为wrap_content的情况
        int width = 0;//自己测量的 宽度
        int height = 0;//自己测量的高度

        int childTotalWidth = 0;//记录每行的宽度
        int childTotalHeight = 0;//记录每行的高度

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams cmlp = (MarginLayoutParams) child.getLayoutParams();
            //测量子View
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidth = child.getMeasuredWidth() + cmlp.leftMargin + cmlp.rightMargin;
            int childHeight = child.getMeasuredHeight() + cmlp.topMargin + cmlp.bottomMargin;
            if (childTotalWidth + childWidth > wSize) {
                //记录每行的长度，选取最大值做为wrap_content时的值
                width = Math.max(width, childTotalWidth);
                //记录高度值
                height += childTotalHeight;
                //重置宽高
                childTotalWidth = childWidth;
                childTotalHeight = childHeight;
            } else {
                childTotalWidth += childWidth;
                childTotalHeight = Math.max(childHeight, childTotalHeight);
            }

            if (i == count - 1) {
                width = Math.max(width, childTotalWidth);
                height += childTotalHeight;
            }
        }
        setMeasuredDimension(wMode == MeasureSpec.EXACTLY ? wSize : width,
                hMode == MeasureSpec.EXACTLY ? hSize : height);
    }

    //记录子View的所在行的高度
    private List<Integer> lineHeight = new ArrayList<>();
    //记录子View
    private List<View> views = new ArrayList<>();
    //记录子View所在行
    private List<Integer> lines = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        lineHeight.clear();
        views.clear();
        lines.clear();
        int count = getChildCount();
        int width = getWidth();//viewGroup宽度
        int totalWidth = 0;
        int totalHeight = 0;
        int line = 0;//行
        int maxHeight = 0;//每行最大高度
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            totalWidth += child.getMeasuredWidth() + params.leftMargin + params.rightMargin;

            if (totalWidth > width) {
                totalWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                totalHeight = maxHeight;
                maxHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
                line++;
            } else {
                //记录每行最大高度
                maxHeight = Math.max(child.getMeasuredHeight() + params.topMargin + params.bottomMargin, maxHeight);
            }
            lines.add(line);
            views.add(child);
            lineHeight.add(totalHeight);
        }

        //为子view设置位置
        int left = 0;
        int top = 0;
        for (int i = 0; i < count; i++) {
            View view = views.get(i);
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
            view.layout(left + params.leftMargin, top + params.topMargin,
                    left + view.getMeasuredWidth() + params.leftMargin,
                    top + view.getMeasuredHeight() + params.topMargin);
            left += params.leftMargin + view.getMeasuredWidth() + params.rightMargin;
            if (i != count - 1 && lines.get(i + 1) > lines.get(i)) {
                left = 0;
                top += lineHeight.get(i + 1);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                checkClickChildView(x, y);
                break;
            case MotionEvent.ACTION_UP:

                clickViewCallback(x, y);
                break;
        }
        return true;
    }

    /**
     * 判断是否点击了子view
     */
    private boolean checkClickChildView(int x, int y) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int[] location = new int[2];
            child.getLocationOnScreen(location);
            int childX = location[0];//测量view的左X坐标
            int childY = location[1];//测量view的上Y坐标
//            Utils.e("----------");
//            Utils.e(((TextView)child).getText().toString());
//            Utils.e(childX+"=="+x);
//            Utils.e(childY+"=="+y);
//            Utils.e(child.getMeasuredWidth()+"=="+child.getMeasuredHeight());
            if (x >= childX && x <= childX + child.getMeasuredWidth()
                    && y >= childY && y <= childY + child.getMeasuredHeight()) {
                checkChild = child;
                position = i;
                return true;
            } else {
                checkChild = null;
                position = -1;
            }
        }
        return false;
    }

    /**
     * 设置点击回调
     */
    private void clickViewCallback(int x, int y) {
        if (listener != null && checkChild != null && position != -1) {
            int[] location = new int[2];
            checkChild.getLocationOnScreen(location);
            int childX = location[0];//测量view的左X坐标
            int childY = location[1];//测量view的上Y坐标
            if (x >= childX && x <= childX + checkChild.getMeasuredWidth()
                    && y >= childY && y <= childY + checkChild.getMeasuredHeight()) {
                checkChild.setSelected(!checkChild.isSelected());
                listener.click(checkChild, position,checkChild.isSelected());
            }

        }
    }

//    public void refresh(){
//        postInvalidate();
//    }

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.listener = listener;
    }

    public interface OnViewClickListener {
        void click(View v, int position, boolean select);
    }
}
