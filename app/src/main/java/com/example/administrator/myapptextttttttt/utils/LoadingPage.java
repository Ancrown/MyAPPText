package com.example.administrator.myapptextttttttt.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.views.LoadingView;


/**
 * Author  ：
 * Function:
 */
public class LoadingPage {
    private FrameLayout frameLayout;
    private Context context;
    private float topHeight;
    private float bottomHeight;
    private View mDialogContentView;

    private View topView;
    private View bottomView;
    private RelativeLayout centerView;
    private RelativeLayout loadError;
    private LoadingView mLoadingView;
    private TextView refresh;

    public LoadingPage(FrameLayout frameLayout, Context context, float topHeight, float bottomHeight) {
        this.frameLayout = frameLayout;
        this.context = context;
        this.topHeight = topHeight;
        this.bottomHeight = bottomHeight;
        init();
    }

    private void init() {
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
        topView = mDialogContentView.findViewById(R.id.topView);
        bottomView = mDialogContentView.findViewById(R.id.bottomView);
        refresh = (TextView) mDialogContentView.findViewById(R.id.on_refresh);
        centerView = (RelativeLayout) mDialogContentView.findViewById(R.id.centerView);
        loadError = (RelativeLayout) mDialogContentView.findViewById(R.id.centerView_error);
        mLoadingView = (LoadingView) mDialogContentView.findViewById(R.id.loadView);
        Log.e("sssssssssssssssssss", topHeight + "percentage" + bottomHeight);
        frameLayout.addView(mDialogContentView);

        LinearLayout.LayoutParams tlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) topHeight);
        topView.setLayoutParams(tlp);
        LinearLayout.LayoutParams clp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ToolUtils.getScreenHeight(context)-ToolUtils.getStatusBarHeight(context)-((int) topHeight + (int) bottomHeight));
        centerView.setLayoutParams(clp);
        LinearLayout.LayoutParams blp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) bottomHeight);
        bottomView.setLayoutParams(blp);

        topView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sssssssssssss", "topView");
            }
        });
        bottomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sssssssssssss","bottomView");
            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
                if (listener != null) {
                    listener.onAgain();
                }
            }
        });
    }

    public void dismiss() {
        frameLayout.removeView(mDialogContentView);
    }

//    public void onRefresh(View view){
//        loading();
//        if (listener != null) {
//            listener.onAgain();
//        }
//    }

    /**
     * 加载失败
     */
    public void loadError() {
        centerView.setVisibility(View.GONE);
        loadError.setVisibility(View.VISIBLE);
    }

    /**
     * 加载中d
     */
    public void loading() {
        centerView.setVisibility(View.VISIBLE);
        loadError.setVisibility(View.GONE);
    }

    public OnAgainLoadingListener listener;

    public void setOnAgainLoadingListener(OnAgainLoadingListener listener) {
        this.listener = listener;
    }

    public interface OnAgainLoadingListener {
        void onAgain();
    }
}
