package com.limushan.scroll.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by free on 16/4/15.
 */
public class InitLoadWebClient extends WebViewClient {

    private boolean mReceivedError=true;
    private String mErrorUrl;
    private OnLoadFinishListener mListener;

    public boolean isInitLoadSuccess() {
        return !mReceivedError;
    }

    public String getErrorUrl(){
        return mErrorUrl;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        mReceivedError=false;
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        mReceivedError=true;
        mErrorUrl=failingUrl;
        view.loadUrl("about:blank");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if(mListener!=null){
            mListener.onLoadFinished(url, mReceivedError);
        }
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    public void setOnLoadFinishListener(OnLoadFinishListener listener){
        mListener=listener;
    }

    public interface OnLoadFinishListener {
        void onLoadFinished(String url, boolean loadingError);
    }
}
