package com.example.chuckjokes.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chuckjokes.databinding.ErrorFragmentBinding
import com.example.chuckjokes.viewModelsExt
import java.io.PrintWriter
import java.io.StringWriter

class ErrorFragment(private val error: Exception) : Fragment() {

    private lateinit var binding: ErrorFragmentBinding

    private val viewModel by viewModelsExt {
        ErrorViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = ErrorFragmentBinding.inflate(inflater)

        val sw = StringWriter()
        val pw = PrintWriter(sw)
        error.printStackTrace(pw)

        binding.errorBaseInfo.text = error.localizedMessage
        binding.moreAboutError.text = sw.toString()
        binding.moreAboutError.visibility = View.GONE

        binding.moreAboutErrorBut.setOnClickListener {viewModel.moreAboutErrorClicked()}

        viewModel.selectedNavItemId.observe(viewLifecycleOwner) {
            binding.moreAboutError.visibility = it
        }

        return binding.root
    }
}
