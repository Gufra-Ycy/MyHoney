package com.gufra.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.gufra.gufra.R;

public class MyWebActivity extends AppCompatActivity {

    private static String tag = "MyWebActivity";
    private static String url = "https://www.facebook.com/";
    //    private static String url = "https://www.baidu.com/";
    private WebView mWebView;
    private FrameLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web);
        initView();

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(tag, "shouldOverrideUrlLoading");
                view.loadUrl(url);
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);*/
                return false;
            }

            //加载网页替换资源
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String Url) {
                WebResourceResponse response = null;
                Log.d(tag, "shouldInterceptRequest");
                Log.d(tag, "Url=" + Url);
                return response;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // 开始加载网页时处理 如：显示"加载提示" 的加载对话框

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 网页加载完成时处理  如：让 加载对话框 消失

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // 加载网页失败时处理 如：提示失败，或显示新的界面

            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                Log.d(tag, "shouldOverrideKeyEvent");

                Log.d(tag, "keyEvent" + event.toString());
                Log.d(tag, "keyEvent" + event);

                return super.shouldOverrideKeyEvent(view, event);
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setAllowFileAccess(true);
        //自适应手机屏幕
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        //隐藏webview缩放按钮
        mWebView.getSettings().setDisplayZoomControls(false);
        //webView.loadUrl("https://springgame.com");
//        mWebView.loadUrl(url);
        mWebView.loadUrl("file:///android_asset/yu.html");

    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.web_fb);
        back = (FrameLayout) findViewById(R.id.fram_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//其中webView.canGoBack()在webView含有一个可后退的浏览记录时返回true
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                finish();
            }
        }
     /*   if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }*/
        return false;
    }
}
