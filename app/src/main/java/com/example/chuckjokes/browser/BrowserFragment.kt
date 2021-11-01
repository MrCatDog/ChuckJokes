package com.example.chuckjokes.browser

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chuckjokes.databinding.BrowserFragmentBinding
import com.example.chuckjokes.viewModelsExt

class BrowserFragment : Fragment() {

    private lateinit var binding: BrowserFragmentBinding

    private val viewModel by viewModelsExt {
        BrowserViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BrowserFragmentBinding.inflate(inflater)

        binding.browser.webViewClient = viewModel.getWebViewClient()

        binding.browser.webChromeClient = viewModel.getWebChromeClient()

        viewModel.progress.observe(viewLifecycleOwner) {
            binding.progressBar.progress = it
        }

        viewModel.visibility.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = it
        }

        viewModel.modifyWebSettings(binding.browser.settings)

        viewModel.url.observe(viewLifecycleOwner) {
            binding.browser.loadUrl(it)
        }

        return binding.root
    }
}