package com.example.chuckjokes.fragments.browser

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.chuckjokes.databinding.BrowserFragmentBinding
import com.example.chuckjokes.util.viewModelsExt

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

    @SuppressLint("SetJavaScriptEnabled")
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

        binding.browser.settings.apply {
            loadWithOverviewMode = LOAD_WITH_OVERVIEW_MODE
            useWideViewPort = USE_WIDE_VIEWPORT
            builtInZoomControls = BUILT_IN_ZOOM_CONTROLS
            displayZoomControls = DISPLAY_ZOOM_CONTROLS
            javaScriptEnabled = JAVA_SCRIPT_ENABLED
        }
//        TODO: save all this shit in onPause() at viewModel, restore after rotation (MAYBE) if it wasn't initialized, use code above
//        binding.browser.webChromeClient
//        binding.browser.webViewClient
//        binding.browser.settings

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

        viewModel.url.observe(viewLifecycleOwner) {
            binding.browser.loadUrl(it)
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        //todo:
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}