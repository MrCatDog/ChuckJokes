package com.example.chuckjokes.fragments.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chuckjokes.databinding.ErrorFragmentBinding
import com.example.chuckjokes.util.viewModelsExt

const val ERROR_EXCEPTION_TAG = "errorException"

class ErrorFragment : Fragment() {

    private var _binding: ErrorFragmentBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModelsExt {
        ErrorViewModel(requireArguments().getSerializable(ERROR_EXCEPTION_TAG) as Throwable)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ErrorFragmentBinding.inflate(inflater)

        binding.moreAboutErrorBut.setOnClickListener {
            viewModel.moreAboutErrorClicked()
        }

        viewModel.additionalInfoVisibility.observe(viewLifecycleOwner) {
            binding.moreAboutError.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        viewModel.errorBaseInfoText.observe(viewLifecycleOwner) {
            binding.errorBaseInfo.text = it
        }

        viewModel.moreAboutErrorText.observe(viewLifecycleOwner) {
            binding.moreAboutError.text = it
        }

        return binding.root
    }

    companion object ErrorFragmentFactory {
        fun newInstance(ex: Throwable): ErrorFragment {
            val myFragment = ErrorFragment()
            val args = Bundle()

            args.putSerializable(ERROR_EXCEPTION_TAG, ex)
            return myFragment.also { it.arguments = args }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
