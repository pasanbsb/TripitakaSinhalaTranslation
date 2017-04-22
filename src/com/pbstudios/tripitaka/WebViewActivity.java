package com.pbstudios.tripitaka;

/**
 * Created by Pasan on 2/14/2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);

        String summary = "<html><body>You scored <b>192</b> points.</body></html>";
        webview.loadData(summary, "text/html", null);
        setContentView(webview);
    }

}
