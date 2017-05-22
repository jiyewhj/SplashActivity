package com.dragon.splashactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main extends Activity{
    private WebView webview;
    private KeyboardChangeListener keyboardChangeListener;
    @Override
    @JavascriptInterface
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //实例化WebView对象
        webview = new WebView(this);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //不论如何都会从缓存中加载
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); //
        webview.getSettings().setDomStorageEnabled(true);//设置适应HTML5的一些方法
        webview.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webview.getSettings().setUseWideViewPort(true);
        webview.setWebViewClient(new WebViewClient());
        //加载需要显示的网页
        webview.loadUrl("http://60.212.191.90/zxt/index.html");

        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        //设置Web视图
        setContentView(webview);
//        keyboardChangeListener=new KeyboardChangeListener(this);
//        keyboardChangeListener.setKeyboardLisener(Main.this);
        SplashActivity.mActivity.finish();
    }
//    @Override
//    public void onKeyboardChange(boolean isShow, int keyboardHeight){
//        if(isShow){
//            webview.scrollTo(0,keyboardHeight);
//        }else{
//            webview.setPadding(0,0,0,0);
//            webview.scrollTo(0,10);
//        }
//    };


    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//    }
}
