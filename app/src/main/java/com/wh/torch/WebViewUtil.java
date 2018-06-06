package com.wh.torch;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by lenovo on 2017/1/14.
 */
public class WebViewUtil {

    public static void open(WebView mView, String mUrl)
    {
        mView.loadUrl(mUrl);
        mView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = mView.getSettings();
        mView.getSettings().setJavaScriptEnabled(true);
        mView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //不论如何都会从缓存中加载
        mView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); //
        mView.getSettings().setDomStorageEnabled(true);//设置适应HTML5的一些方法
//        mView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        mView.getSettings().setAllowFileAccess(true);
        mView.getSettings().setAppCacheEnabled(true);
        mView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mView.getSettings().setUseWideViewPort(true);



        webSettings.setJavaScriptEnabled(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//开发阶段开启
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//正式阶段开启
    }
}
