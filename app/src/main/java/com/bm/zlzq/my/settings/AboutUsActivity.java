package com.bm.zlzq.my.settings;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by Administrator on 2015/12/20.
 */
public class AboutUsActivity extends BaseActivity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_about_us);
        initView();
        initTitle("关于我们");
    }

    private String getWidthPx() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        int dipWidth = (int) (mScreenWidth / dm.density);
        int realWidth = (int) (dipWidth - 5 * dm.density);
        return "" + realWidth;
    }

    private void initView() {

        Intent intent = getIntent();
        String URL = intent.getStringExtra("url");
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDefaultTextEncodingName("UTF-8");
        webview.loadDataWithBaseURL(null,
                URL, "text/html",
                "utf-8", null);
        String widthPx = getWidthPx();
        Log.e("widthPx", "-------------------" + widthPx + "Build.VERSION.SDK_INT:" + Build.VERSION.SDK_INT);
        //widthPx="320";
        if (Build.VERSION.SDK_INT >= 19) {
            webview.evaluateJavascript("javascript:(function(){" +
                    "var script = document.createElement('script');"
                    + "script.type = 'text/javascript';"
                    + "script.text = \"function ResizeImages() { "
                    + "var myimg;"
                    + "for(i=0;i <document.images.length;i++)" + "{"
                    + "myimg = document.images[i];" + "myimg.setAttribute('style','max-width:" + widthPx + "px;height:auto');"
                    + "}" + "}\";"
                    + "document.getElementsByTagName('head')[0].appendChild(script);" +
                    "})()", new ValueCallback<String>() {

                @Override
                public void onReceiveValue(String value) {
                    webview.loadUrl("javascript:ResizeImages()");//必需在回调中加这句话，放在其它地方无效
                    try {
                        Thread.sleep(1000);//防止ResizeImages()没执行
                        webview.loadUrl("javascript:ResizeImages()");
                    } catch (Exception ex) {
                        Log.e("widthPxExcepton", "-------------------" + ex.toString());
                    }

                }
            });
        } else {
            // 自适应图片大小
            webview.loadUrl("javascript:(function(){" +
                    "var script = document.createElement('script');"
                    + "script.type = 'text/javascript';"
                    + "script.text = \"function ResizeImages() { "
                    + "var myimg;"
                    + "for(i=0;i <document.images.length;i++)" + "{"
                    + "myimg = document.images[i];" + "myimg.setAttribute('style','max-width:" + widthPx + "px;height:auto');"
                    + "}" + "}\";"
                    + "document.getElementsByTagName('head')[0].appendChild(script);" +
                    "})()");

            webview.loadUrl("javascript:ResizeImages()");
            try {
                Thread.sleep(1000);//防止ResizeImages()没执行
            } catch (Exception ex) {
            }
            webview.loadUrl("javascript:ResizeImages()");
        }
    }

}
