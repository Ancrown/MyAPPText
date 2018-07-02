package com.example.administrator.myapptextttttttt.activity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.interf.ActionBarClickListener;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.utils.web.MJavascriptInterface;
import com.example.administrator.myapptextttttttt.utils.web.MyWebViewClient;
import com.example.administrator.myapptextttttttt.views.ScrollViewExt;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/25
 * 描述:
 */

public class WebTextImgNewActivity extends BaseActivity implements ActionBarClickListener {
    @BindView(R.id.webview)
    WebView contentWebView;
    @BindView(R.id.scrollviewext)
    ScrollViewExt scrollviewext;

    //   private String rul = "http://a.mp.uc.cn/article.html?uc_param_str=frdnsnpfvecpntnwprdssskt&client=ucweb&wm_aid=c51bcf6c1553481885da371a16e33dbe&wm_id=482efebe15ed4922a1f24dc42ab654e6&pagetype=share&btifl=100";

    private String rul = "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_14209175215010159714%22%7D&n_type=0&p_from=1";

   private String[] imageUrls = ToolUtils.returnImageUrlsFromHtml();


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("HTILLLL", 0, R.drawable.back, null, 0, null, this);

        contentWebView.getSettings().setJavaScriptEnabled(true);
        contentWebView.getSettings().setAppCacheEnabled(true);
        contentWebView.getSettings().setDatabaseEnabled(true);
        contentWebView.getSettings().setDomStorageEnabled(true);
        contentWebView.loadUrl("http://a.mp.uc.cn/article.html?uc_param_str=frdnsnpfvecpntnwprdssskt&client=ucweb&wm_aid=c51bcf6c1553481885da371a16e33dbe&wm_id=482efebe15ed4922a1f24dc42ab654e6&pagetype=share&btifl=100");
        contentWebView.addJavascriptInterface(new MJavascriptInterface(this, imageUrls), "imagelistener");
        contentWebView.setWebViewClient(new MyWebViewClient());

    }

    @Override
    protected int getLayout() {
        return R.layout.aty_web_text_img;
    }


    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}
