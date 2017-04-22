package com.pbstudios.tripitaka.fragments;

/**
 * Created by Pasan on 2/14/2016.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;
import com.pbstudios.tripitaka.R;

public class HomeFragment extends WebViewFragment {

    private String url;
    public HomeFragment() {
        this.url = "file:///android_asset/tipitaka/index.html";
    }

    public HomeFragment(String url) {
        this.url = url;
    }
    public static WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        startWebView();
        return view;
    }

    private void startWebView() {
        webView = getWebView();
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });

    }

}