package com.example.chuckjokes.Browser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EasyWebClient extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
