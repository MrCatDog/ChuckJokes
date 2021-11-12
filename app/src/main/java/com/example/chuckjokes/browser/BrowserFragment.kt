package com.example.chuckjokes.browser

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.chuckjokes.databinding.BrowserFragmentBinding
import com.example.chuckjokes.viewModelsExt

const val LOAD_WITH_OVERVIEW_MODE = true
const val USE_WIDE_VIEWPORT = true
const val BUILT_IN_ZOOM_CONTROLS = true
const val DISPLAY_ZOOM_CONTROLS = false
const val JAVA_SCRIPT_ENABLED = true

class BrowserFragment : Fragment() {

    private var _binding: BrowserFragmentBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModelsExt {
        BrowserViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BrowserFragmentBinding.inflate(inflater)

        binding.browser.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean =
                viewModel.shouldOverrideUrlLoading()
        }

        binding.browser.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(wv: WebView, progress: Int) {
                viewModel.changeProgress(progress)
            }
        }

        viewModel.progress.observe(viewLifecycleOwner) {
            binding.progressBar.progress = it
        }

        viewModel.visibility.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        binding.browser.settings.also {
            it.loadWithOverviewMode = LOAD_WITH_OVERVIEW_MODE
            it.useWideViewPort = USE_WIDE_VIEWPORT
            it.builtInZoomControls = BUILT_IN_ZOOM_CONTROLS
            it.displayZoomControls = DISPLAY_ZOOM_CONTROLS
            it.javaScriptEnabled = JAVA_SCRIPT_ENABLED
        }

        viewModel.url.observe(viewLifecycleOwner) {
            binding.browser.loadUrl(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}