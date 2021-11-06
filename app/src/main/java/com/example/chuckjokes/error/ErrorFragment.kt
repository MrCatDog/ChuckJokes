package com.example.chuckjokes.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chuckjokes.R
import com.example.chuckjokes.Shared
import com.example.chuckjokes.databinding.ErrorFragmentBinding
import com.example.chuckjokes.viewModelsExt

class ErrorFragment : Fragment() {

    private lateinit var binding: ErrorFragmentBinding

    private val viewModel by viewModelsExt {
        ErrorViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = ErrorFragmentBinding.inflate(inflater)

        binding.moreAboutErrorBut.setOnClickListener {
            viewModel.moreAboutErrorClicked()
        }

        viewModel.additionalInfoVisibility.observe(viewLifecycleOwner) {
            if(it) {
                binding.moreAboutError.visibility = View.VISIBLE
            } else {
                binding.moreAboutError.visibility = View.GONE
            }
        }

        viewModel.errorBaseInfoText.observe(viewLifecycleOwner) {
            binding.errorBaseInfo.text = it
        }

        viewModel.moreAboutErrorText.observe(viewLifecycleOwner) {
            binding.moreAboutError.text = it
        }

        viewModel.setArguments(requireArguments()) // TODO: Разобраться с исключениями котлина: ну оно же может тут возникнуть, верно? почему я его не обрабатываю?

        return binding.root
    }

    companion object ErrorFragmentFactory {
        fun newInstance(args: Bundle): ErrorFragment {
            val myFragment = ErrorFragment()
            myFragment.arguments = args
            return myFragment
        }
    }
}
