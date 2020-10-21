package com.example.chuckjokes.Browser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import com.example.chuckjokes.R;

public class BrowserFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View browserView = inflater.inflate(R.layout.browser_fragment, null);

        WebView browser = browserView.findViewById(R.id.browser);
        browser.setWebViewClient(new EasyWebClient());
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl("www.icndb.com");

        return browserView;
    }
}
