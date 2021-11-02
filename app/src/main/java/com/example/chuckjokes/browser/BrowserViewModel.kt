package com.example.chuckjokes.browser

import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val MAX_PROGRESS = 100
const val DEFAULT_URL = "http://www.icndb.com/api/"

const val LOAD_WITH_OVERVIEW_MODE = true
const val USE_WIDE_VIEWPORT = true
const val BUILT_IN_ZOOM_CONTROLS = true
const val DISPLAY_ZOOM_CONTROLS = false
const val JAVA_SCRIPT_ENABLED = true

class BrowserViewModel : ViewModel() {

    private val _progress = MutableLiveData<Int>()

    val progress : LiveData<Int>
        get() = _progress

    private val _visibility = MutableLiveData<Int>()

    val visibility : LiveData<Int>
        get() = _visibility

    private val _url = MutableLiveData<String>()

    val url : LiveData<String>
        get() = _url

    init {
        _url.value = DEFAULT_URL
    }

    fun modifyWebSettings(settings: WebSettings) {
        settings.loadWithOverviewMode = LOAD_WITH_OVERVIEW_MODE
        settings.useWideViewPort = USE_WIDE_VIEWPORT
        settings.builtInZoomControls = BUILT_IN_ZOOM_CONTROLS
        settings.displayZoomControls = DISPLAY_ZOOM_CONTROLS
        settings.javaScriptEnabled = JAVA_SCRIPT_ENABLED
    }

    fun getWebViewClient() = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    fun getWebChromeClient() = object : WebChromeClient() {
        override fun onProgressChanged(wv: WebView, progress: Int) {
            _progress.value = progress
            if (progress == MAX_PROGRESS) {
                _visibility.value = View.GONE
            } else {
                _visibility.value = View.VISIBLE
            }
        }
    }
}