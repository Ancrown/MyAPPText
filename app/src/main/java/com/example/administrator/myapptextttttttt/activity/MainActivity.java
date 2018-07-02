package com.example.administrator.myapptextttttttt.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.MovieSubject;
import com.example.administrator.myapptextttttttt.adapter.MusicAdapter;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.base.BaseApplication;
import com.example.administrator.myapptextttttttt.beans.Card;
import com.example.administrator.myapptextttttttt.interf.ActionBarClickListener;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.RefreshListenerAdapter;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.TwinklingRefreshLayout;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.header.progresslayout.ProgressLayout;
import com.example.administrator.myapptextttttttt.utils.AddressRequestString;
import com.example.administrator.myapptextttttttt.utils.PermissionManager;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.utils.okhttp.OkHttpUtil;
import com.example.administrator.myapptextttttttt.views.CustomDrawerLayout;
import com.example.administrator.myapptextttttttt.views.gradualchange.TranslucentActionBar;
import com.squareup.okhttp.Request;
import com.umeng.message.PushAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.view.Gravity.START;


/**
 * 镶嵌Listview的 下拉加载更多
 */

//TODO 有FixedHeader的界面fling有问题
public class MainActivity extends BaseActivity implements ActionBarClickListener {

    private MusicAdapter adapter;
    private List<Card> cards = new ArrayList<>();

    //标题
    @BindView(R.id.actionbar)
    TranslucentActionBar actionBar;

    private CustomDrawerLayout drawer;
    private float offset;
    private boolean flipped;

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!ToolUtils.isNotificationEnabled(this)) {
            PermissionManager.showDialog(this,
                    "通知管理权限被禁止，通知功能无法正常使用。是否开启该权限？(步骤：通知管理->'勾选'允许通知)");
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setNavigationIcon(R.drawable.back);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        setupListView((ListView) findViewById(R.id.listView));


        //初始actionBar
        getTitleView().setData("Ｉ Lᵒᵛᵉᵧₒᵤ", R.color.white, R.drawable.dian_dian_dian, "", R.drawable.barrage_fill, "", this);

        drawer = (CustomDrawerLayout) findViewById(R.id.drawer_layout);

        drawer.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                offset = slideOffset;

                // Sometimes slideOffset ends up so close to but not quite 1 or 0.
                if (slideOffset >= .995) {
                    flipped = true;
                    //    drawerArrowDrawable.setFlip(flipped);
                } else if (slideOffset <= .005) {
                    flipped = false;
                    //   drawerArrowDrawable.setFlip(flipped);
                }

                // drawerArrowDrawable.setParameter(offset);
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private TwinklingRefreshLayout refreshLayout;

    private void setupListView(ListView listView) {
        refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
        ProgressLayout headerView = new ProgressLayout(this);
        refreshLayout.setHeaderView(headerView);
        View exHeader = View.inflate(this, R.layout.header_music, null);
        //添加位置固定的头部
        refreshLayout.addFixedExHeader(exHeader);

        //添加位置跟listview滑动的头部
        // listView.addHeaderView(exHeader);

        refreshLayout.setOverScrollRefreshShow(false);

        //支持切换到像SwipeRefreshLayout一样的悬浮刷新模式了。
        //refreshLayout.setFloatRefresh(true);

        getdata();

        //  getEntity("");
        adapter = new MusicAdapter(cards);
        listView.setAdapter(adapter);

        exHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("eeeee", "头部");
                for (int i = 0; i < cards.size(); i++) {
                    cards.get(i).setType(i % 2);
                    cards.get(i).notifyObservers();
                }
            }
        });


        adapter.setOnClick(new MusicAdapter.OnClick() {
            @Override
            public void onItem(Card card) {
                Log.e("eeeeeeeee", card.getTitle());
                switch (card.getTitle()) {
                    case "RefreshRecyclerActivity":
                        startActivity(new Intent(MainActivity.this, RefreshRecyclerActivity.class));
                        break;
                    case "TabLayoutActivity":
                        startActivity(new Intent(MainActivity.this, TabLayoutActivity.class));
                        break;
                    case "BroadcastActivity":
                        startActivity(new Intent(MainActivity.this, BroadcastActivity.class));
                        break;
                    case "BroadcastNewActivity":
                        startActivity(new Intent(MainActivity.this, BroadcastNewActivity.class));
                        break;
                    case "FontRollingActivity":
                        startActivity(new Intent(MainActivity.this, FontRollingActivity.class));
                        break;
                    case "NetworkRequestActivity":
                        startActivity(new Intent(MainActivity.this, NetworkRequestActivity.class));
                        break;
                    case "WebTextImgActivity":
                        startActivity(new Intent(MainActivity.this, WebTextImgActivity.class));
                        break;

                    case "WebTextImgNewActivity":
                        startActivity(new Intent(MainActivity.this, WebTextImgNewActivity.class));
                        break;

                    case "PersonalDetailsActivity":
                        startActivity(new Intent(MainActivity.this, PersonalDetailsActivity.class));
                        break;
                    case "AnswerActivity":
                        startActivity(new Intent(MainActivity.this, AnswerActivity.class));
                        break;
                    case "RecyclerviewActivity":
                        startActivity(new Intent(MainActivity.this, RecyclerviewActivity.class));
                        break;
                    case "GradualChangeActivity":
                        startActivity(new Intent(MainActivity.this, GradualChangeActivity.class));
                        break;
                    case "CameraActivity":
                        startActivity(new Intent(MainActivity.this, CameraTextActivity.class));
                        break;
                    case "DisplayPhotoActivity":
                        startActivity(new Intent(MainActivity.this, DisplayPhotoActivity.class));
                        break;
                    case "DrawerActivity":
                        startActivity(new Intent(MainActivity.this, DrawerActivity.class));
                        break;
                    case "PopupDownMenuActivity":
                        startActivity(new Intent(MainActivity.this, PopupDownMenuActivity.class));
                        break;
                    case "PopupWindowBottomActivity":
                        startActivity(new Intent(MainActivity.this, PopupWindowBottomActivity.class));
                        break;

                    case "CompressImg":
                        startActivity(new Intent(MainActivity.this, CompressImg.class));
                        break;

                    case "DialogActivity":
                        startActivity(new Intent(MainActivity.this, DialogActivity.class));
                        break;

                    case "FabulousActivity":
                        startActivity(new Intent(MainActivity.this, FabulousActivity.class));
                        break;

                    case "LoadActivity":
                        startActivity(new Intent(MainActivity.this, LoadActivity.class));
                        break;
                    case "SaoYiSaoActivity":
                        startActivity(new Intent(MainActivity.this, SaoYiSaoActivity.class));
                        break;

                    case "TextViewSizeActivity":
                        startActivity(new Intent(MainActivity.this, TextViewSizeActivity.class));
                        break;

                    case "UmengActivity":
                        startActivity(new Intent(MainActivity.this, UmengActivity.class));
                        break;


                }
            }
        });


        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout1) {
                Log.e("eeeee", "ListView" + "下拉");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        getEntity("Refresh");
                        //  refreshLayout.finishRefreshing();
                    }
                }, 1000);
//                adapter.refreshCard();
//                //结束下拉
//                refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout2) {
                Log.e("eeeee", "ListView" + "上拉");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        getEntity("Loadmore");
                        //   refreshLayout.finishLoadmore();
                    }
                }, 1000);
//                adapter.loadMoreCard();
//                //结束加载更多
//                refreshLayout.finishLoadmore();
            }

        });
//        //进入到界面的时候主动调用下刷新  下拉
//        refreshLayout.startRefresh();
//        //进入到界面的时候主动调用下刷新  加载更多
//        refreshLayout.startLoadMore();
    }

    //根据手势 结束下拉上拉
    private void endEntity(String gesture) {
        switch (gesture) {
            case "":
                break;
            case "Refresh":
                refreshLayout.finishRefreshing();
                break;
            case "Loadmore":
                refreshLayout.finishLoadmore();
                break;
        }
    }

    public void getdata() {

        cards.add(new Card("RefreshRecyclerActivity", "上拉+加载更多", R.drawable.avatar0));
        cards.add(new Card("TabLayoutActivity", "TabLayout", R.drawable.avatar0));
        cards.add(new Card("BroadcastActivity", "图片轮播", R.drawable.avatar0));
        cards.add(new Card("BroadcastNewActivity", "图片轮播View", R.drawable.avatar0));
        cards.add(new Card("FontRollingActivity", "文字轮播，生成验证图片", R.drawable.avatar0));
        cards.add(new Card("NetworkRequestActivity", "网络请求", R.drawable.avatar0));
        cards.add(new Card("WebTextImgActivity", "html 显示文本", R.drawable.avatar0));
        cards.add(new Card("WebTextImgNewActivity", "html 显示文本新", R.drawable.avatar0));

        cards.add(new Card("PersonalDetailsActivity", "WWWW", R.drawable.avatar0));
        cards.add(new Card("AnswerActivity", "答题", R.drawable.avatar0));
        cards.add(new Card("RecyclerviewActivity", "RecyclerView", R.drawable.avatar0));
        cards.add(new Card("GradualChangeActivity", "渐变标题", R.drawable.avatar0));
        cards.add(new Card("CameraActivity", "相机", R.drawable.avatar0));
        cards.add(new Card("DisplayPhotoActivity", "测试查看相机", R.drawable.avatar0));
        cards.add(new Card("DrawerActivity", "抽屉菜单", R.drawable.avatar0));
        cards.add(new Card("PopupDownMenuActivity", "popupDown", R.drawable.avatar0));
        cards.add(new Card("PopupWindowBottomActivity", "popupDownBBBB", R.drawable.avatar0));
        cards.add(new Card("CompressImg", "图片文件压缩！", R.drawable.avatar0));
        cards.add(new Card("DialogActivity", "提示框Dialog", R.drawable.avatar0));
        cards.add(new Card("FabulousActivity", "点赞", R.drawable.avatar0));
        cards.add(new Card("LoadActivity", "加载框", R.drawable.avatar0));

        cards.add(new Card("SaoYiSaoActivity", "加载框", R.drawable.avatar0));

        cards.add(new Card("TextViewSizeActivity", "字体", R.drawable.avatar0));

        cards.add(new Card("UmengActivity", "友盟", R.drawable.avatar0));

    }

    private int page;

    public void getEntity(final String gesture) {

        Map map = new HashMap<>();
        map.put("start", page == 0 ? 1 : page);
        map.put("count", 5);
        OkHttpUtil.getInstance(this).doPostList(AddressRequestString.BASE_URL + "top250", new OkHttpUtil.ResultCallback<MovieSubject>() {
            @Override
            public void onError(Request request, Exception e) {
                endEntity(gesture);
            }

            @Override
            public void onResponse(MovieSubject response) {

                ToolUtils.showToast(MainActivity.this, response.getTitle() + "  " + response.getStart());
                endEntity(gesture);
            }
        }, map, "", page);
    }


    @Override
    public void onLeftClick() {
        if (drawer.isDrawerVisible(START)) {
            drawer.closeDrawer(START);
        } else {
            drawer.openDrawer(START);
        }
    }

    @Override
    public void onRightClick() {
        Log.e("eeeeee", "右边");
    }
}

