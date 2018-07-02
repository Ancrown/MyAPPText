package com.example.administrator.myapptextttttttt.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 作者 ：刘晓伟
 * 工程名 ：dis_
 * 包名 ：com.ioi.distribution.view
 * 描述 ：不可滑动的listview
 * 创建时间 ：2016/11/30
 */
public class NoSlideListView extends ListView {
    public NoSlideListView(Context context) {
        super(context);
    }
    public NoSlideListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoSlideListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    @Override
    public int getCount() {
        return super.getCount();
    }
}
