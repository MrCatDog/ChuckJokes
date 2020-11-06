package com.example.chuckjokes.Browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.example.chuckjokes.R;

public class BrowserFragment extends Fragment {

    WebView content;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View browserView = inflater.inflate(R.layout.browser_fragment, container, false);

        content = browserView.findViewById(R.id.browser);
        progressBar = browserView.findViewById(R.id.progressBar);

        content.setWebViewClient(new EasyWebClient());

        content.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView wv, int progress) {
                progressBar.setProgress(progress);
                if(progress == 100)
                    progressBar.setVisibility(View.GONE);
                else
                    progressBar.setVisibility(View.VISIBLE);
            }
        });

        WebSettings settings = content.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);

        content.loadUrl("http://www.icndb.com/api/");

        return browserView;
    }
}
