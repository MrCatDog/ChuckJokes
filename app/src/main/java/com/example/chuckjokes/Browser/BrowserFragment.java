package com.example.chuckjokes.Browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.chuckjokes.databinding.BrowserFragmentBinding;

import org.jetbrains.annotations.NotNull;

public class BrowserFragment extends Fragment {

    private BrowserFragmentBinding binding;
    private BrowserPresenter presenter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = BrowserFragmentBinding.inflate(inflater);

        presenter = new BrowserPresenter(this);
        presenter.initWebView();

        return binding.getRoot();
    }

    public void setProgress(int progress) {
        binding.progressBar.setProgress(progress);
    }

    public void setProgressBarVisibility(int state) {
        binding.progressBar.setVisibility(state);
    }

    public void setWebViewClient(WebViewClient client) {
        binding.browser.setWebViewClient(client);
    }

    public void setWebChromeClient(WebChromeClient client) {
        binding.browser.setWebChromeClient(client);
    }

    public WebSettings getSettings() {
        return binding.browser.getSettings();
    }

    public void loadURL(String url) {
        binding.browser.loadUrl(url);
    }
}
