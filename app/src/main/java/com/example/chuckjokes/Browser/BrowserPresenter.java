package com.example.chuckjokes.Browser;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserPresenter {

    private final BrowserFragment wireframe;
    private final BrowserModel model;

    public BrowserPresenter(BrowserFragment wireframe) {
        this.wireframe = wireframe;
        this.model = new BrowserModel();
    }

    public void initWebView() {
        wireframe.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        wireframe.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView wv, int progress) {
                wireframe.setProgress(progress);
                if (progress == model.getMaxProgress())
                    wireframe.setProgressBarVisibility(View.GONE);
                else
                    wireframe.setProgressBarVisibility(View.VISIBLE);
            }
        });

        WebSettings settings = wireframe.getSettings();
        settings.setLoadWithOverviewMode(model.isLoadWithOverviewMode());
        settings.setUseWideViewPort(model.isUseWideViewPort());
        settings.setBuiltInZoomControls(model.isBuiltInZoomControls());
        settings.setDisplayZoomControls(model.isDisplayZoomControls());
        settings.setJavaScriptEnabled(model.isJavaScriptEnabled());

        wireframe.loadURL(model.getDefaultUrl());
    }
}
