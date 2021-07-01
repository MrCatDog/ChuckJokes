package com.example.chuckjokes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.chuckjokes.databinding.BrowserFragmentBinding;

import org.jetbrains.annotations.NotNull;

public class BrowserFragment extends Fragment {

    public static final boolean LOAD_WITH_OVERVIEW_MODE = true;
    public static final boolean USE_WIDE_VIEWPORT = true;
    public static final boolean BUILT_IN_ZOOM_CONTROLS = true;
    public static final boolean DISPLAY_ZOOM_CONTROLS = false;
    public static final boolean JAVA_SCRIPT_ENABLED = true;
    public static final int MAX_PROGRESS = 100;

    public static final String DEFAULT_URL = "http://www.icndb.com/api/";

    private BrowserFragmentBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = BrowserFragmentBinding.inflate(inflater);

        binding.browser.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        binding.browser.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView wv, int progress) {
                binding.progressBar.setProgress(progress);
                if (progress == MAX_PROGRESS) {
                    binding.progressBar.setVisibility(View.GONE);
                } else {
                    binding.progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        WebSettings settings = binding.browser.getSettings();
        settings.setLoadWithOverviewMode(LOAD_WITH_OVERVIEW_MODE);
        settings.setUseWideViewPort(USE_WIDE_VIEWPORT);
        settings.setBuiltInZoomControls(BUILT_IN_ZOOM_CONTROLS);
        settings.setDisplayZoomControls(DISPLAY_ZOOM_CONTROLS);
        settings.setJavaScriptEnabled(JAVA_SCRIPT_ENABLED);

        binding.browser.loadUrl(DEFAULT_URL);

        return binding.getRoot();
    }
}
