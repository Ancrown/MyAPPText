package com.example.administrator.myapptextttttttt.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.utils.glideutils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图
 */

public class BroadcastView extends RelativeLayout implements ViewPager.OnPageChangeListener {


    private Context context;
    public String text;

    private ViewPager viewPager;
    //存轮播图
    private List<String> imageResIds;
    private ArrayList<ImageView> imageViewList;
    //标题栏
    private LinearLayout ll_point_container;
    //存标题
    private List<String> contentDescs;

    private TextView tv_desc;
    private LinearLayout llBackground;
    private int previousSelectedPosition = 0;
    boolean isRunning = true;

    //圆点样式
    private int circularPoint;
    //标题大小
    private float titleSize;
    //标题颜色
    private int titleColor;
    //是否显示标题
    private boolean showTitle;

    private Thread thread;

    public BroadcastView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public BroadcastView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setCustomAttributes(attrs);
        init();
    }

    public BroadcastView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setCustomAttributes(attrs);
        init();
    }

    private void setCustomAttributes(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BroadcastView);
        circularPoint = a.getResourceId(R.styleable.BroadcastView_circular_point, R.drawable.selector_bg_point);
        titleSize = a.getDimension(R.styleable.BroadcastView_title_size, 10);
        titleColor = a.getColor(R.styleable.BroadcastView_title_color, Color.parseColor("#ffffff"));
        showTitle = a.getBoolean(R.styleable.BroadcastView_show_title, true);
    }

    public void init() {
        LayoutInflater.from(context).inflate(R.layout.aty_broadcast, this);
        // 初始化布局 View视图
        initViews();

        // Model数据
        //  initData();


        // 开启轮询
        new Thread() {
            public void run() {

                while (isRunning) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 往下跳一位
                    ((Activity) context).runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            //System.out.println("设置当前位置: " + viewPager.getCurrentItem());
                            Log.e("eeeee", text + ":  ViewPage在运行：" + viewPager.getCurrentItem());
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

                        }
                    });
                }
            }

        }.start();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == View.VISIBLE) {
            Log.e("eeeee!", text + " View可见");
            //开始某些任务
            isRunning = true;
        } else if (visibility == INVISIBLE || visibility == GONE) {
            Log.e("eeeee!", text + " View不可见");
            //停止某些任务
            isRunning = false;
        }
    }


    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOnPageChangeListener(this);// 设置页面更新监听
//      viewPager.setOffscreenPageLimit(1);// 左右各保留几个对象
        ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);
        llBackground = (LinearLayout) findViewById(R.id.ll_background);
        //showTitle为true 显示标题
        if (showTitle) {
            tv_desc = (TextView) findViewById(R.id.tv_desc);
            tv_desc.setTextSize(titleSize);
            tv_desc.setTextColor(titleColor);
            llBackground.setBackgroundColor(Color.parseColor("#66000000"));
        } else {
            llBackground.setBackgroundColor(Color.parseColor("#00000000"));
        }


    }


    public void setData(List<String> imageResIds, List<String> contentDescs) {
        this.imageResIds = imageResIds;
        this.contentDescs = contentDescs;
        initData();
    }

    /**
     * 初始化要显示的数据
     */
    private void initData() {
        // 图片资源id数组
        //    imageResIds = new int[]{R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food5, R.drawable.food4};
//        imageResIds = new ArrayList<>();
//        imageResIds.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
//        imageResIds.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
//        imageResIds.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
//        imageResIds.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
//        imageResIds.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
        // 文本描述
//        contentDescs = new String[]{
//                "巩俐不低俗，我就不能低俗",
//                "扑树又回来啦！再唱经典老歌引万人大合唱",
//                "揭秘北京电影如何升级",
//                "乐视网TV版大派送",
//                "热血屌丝的反杀"
//        };
//        contentDescs = new ArrayList<>();
//        contentDescs.add("巩俐不低俗，我就不能低俗");
//        contentDescs.add("扑树又回来啦！再唱经典老歌引万人大合唱");
//        contentDescs.add("揭秘北京电影如何升级");
//        contentDescs.add("乐视网TV版大派送");
//        contentDescs.add("热血屌丝的反杀");


        // 初始化要展示的5个ImageView
        imageViewList = new ArrayList<ImageView>();
        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.size(); i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(context);
            //  imageView.setBackgroundResource(imageResIds.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            GlideUtils.LoadImage(context, imageResIds.get(i), imageView);
            imageViewList.add(imageView);

            // 加小白点, 指示器
            pointView = new View(context);
            //小白点 样式
            pointView.setBackgroundResource(circularPoint);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (i != 0)
                layoutParams.leftMargin = 10;
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }

        // Controller 控制器
        initAdapter();
    }

    private void initAdapter() {
        ll_point_container.getChildAt(0).setEnabled(true);
        //showTitle为true 显示标题
        if (showTitle) {
            tv_desc.setText(contentDescs.get(0));
        }
        previousSelectedPosition = 0;

        // 设置适配器
        viewPager.setAdapter(new MyAdapter());

        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
        // 2147483647 / 2 = 1073741823 - (1073741823 % 5)
        viewPager.setCurrentItem(5000000); // 设置到某个位置
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        // 3. 指定复用的判断逻辑, 固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
//          System.out.println("isViewFromObject: "+(view == object));
            // 当划到新的条目, 又返回来, view是否可以被复用.
            // 返回判断规则
            return view == object;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // System.out.println("instantiateItem初始化: " + position);
            // container: 容器: ViewPager
            // position: 当前要显示条目的位置 0 -> 4

//          newPosition = position % 5
            final int newPosition = position % imageViewList.size();

            final ImageView imageView = imageViewList.get(newPosition);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onImgClick != null) {
                        onImgClick.onItme(newPosition + "");
                    }
                }
            });
            // a. 把View对象添加到container中
            container.addView(imageView);
            // b. 把View对象返回给框架, 适配器
            return imageView; // 必须重写, 否则报异常
        }

        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // object 要销毁的对象
            //  System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 滚动时调用
    }

    @Override
    public void onPageSelected(int position) {
        // 新的条目被选中时调用
        System.out.println("onPageSelected: " + position);
        int newPosition = position % imageViewList.size();

        if (showTitle) {
            //设置文本
            tv_desc.setText(contentDescs.get(newPosition));
        }

        // 把之前的禁用, 把最新的启用, 更新指示器
        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        ll_point_container.getChildAt(newPosition).setEnabled(true);

        // 记录之前的位置
        previousSelectedPosition = newPosition;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 滚动状态变化时调用
    }

    private OnImgClick onImgClick;


    public void setOnImgClick(OnImgClick onImgClick) {
        this.onImgClick = onImgClick;
    }

    public interface OnImgClick {
        void onItme(String id);
    }
}