package com.limushan.scroll.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ScrollView;

import com.limushan.scroll.R;

public class WebViewActivity extends AppCompatActivity implements InitLoadWebClient.OnLoadFinishListener, View.OnScrollChangeListener {

    private ScrollView mScrollView;
    private WebView mWebview;
    private InitLoadWebClient mWebClient = new InitLoadWebClient();


    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, WebViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebview = (WebView) findViewById(R.id.web);
        mScrollView = (ScrollView) findViewById(R.id.layout);
        setH5WebSettings(mWebview, true);
        mWebview.setWebViewClient(mWebClient);
        mWebview.loadUrl("http://m.biyao.com/nativeHtmls/Details?suid=1300476020010100001");
        mWebClient.setOnLoadFinishListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScrollView.setOnScrollChangeListener(this);
        }
        mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int measuredHeight = mScrollView.getMeasuredHeight();
                Log.e("log---------", "onGlobalLayout(WebViewActivity.java:46)-->>ScrollView" + measuredHeight);
            }
        });
    }

    public void loadData(String url) {
        if (!mWebClient.isInitLoadSuccess()) {
            mWebview.loadUrl(url);
        }
    }

    public static WebSettings setH5WebSettings(WebView webView, boolean isSupport) {
        WebSettings settings = webView.getSettings();
     /*   settings.setUseWideViewPort(true);//支持viewport meta*/
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);//内容充满屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSaveFormData(true);//保存表单数据
        settings.setJavaScriptEnabled(true);//启用js
        setSupportZoom(settings, isSupport);
        settings.setDomStorageEnabled(true);//启用dom sotrage api
        settings.setAppCacheEnabled(true);
        settings.setAllowFileAccess(true);//webview可以进行文件访问
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//js可以通过window.open()，打开新window
        return settings;
    }

    private static void setSupportZoom(WebSettings settings, boolean isSupport) {
        if (isSupport) {
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
        }
    }


    @Override
    public void onLoadFinished(String url, boolean loadingError) {
        int contentHeight = mWebview.getContentHeight();
        float scale = mWebview.getScale();
        int measuredHeight = mWebview.getMeasuredHeight();
        mWebview.measure(0, 0);
        int measuredHeight1 = mWebview.getMeasuredHeight();
        Log.e("log---------", "onLoadFinished(WebViewActivity.java:89)-->>" + loadingError);
        Log.e("log---------", "onLoadFinished(GoodsWebDescFragment.java:133)-->>" + contentHeight);
        Log.e("log---------", "onLoadFinished(GoodsWebDescFragment.java:136)-->>" + scale);
        Log.e("log---------", "onLoadFinished(GoodsWebDescFragment.java:137)-->>" + scale * contentHeight);
        Log.e("log---------", "onLoadFinished(GoodsWebDescFragment.java:141)-->>" + measuredHeight);
        Log.e("log---------", "onLoadFinished(GoodsWebDescFragment.java:142)-->>" + measuredHeight1);
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.e("log---------", "onScrollChange(WebViewActivity.java:87)-->>" + scrollY);
    }
}
