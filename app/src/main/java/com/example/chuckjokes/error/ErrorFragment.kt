package com.example.chuckjokes.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chuckjokes.Shared
import com.example.chuckjokes.databinding.ErrorFragmentBinding
import com.example.chuckjokes.viewModelsExt
import java.lang.IllegalStateException

class ErrorFragment : Fragment() {

    private lateinit var binding: ErrorFragmentBinding

    private val viewModel by viewModelsExt {
        ErrorViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ErrorFragmentBinding.inflate(inflater)

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

        viewModel.setArguments(
            try {
                requireArguments()
            } catch (ex: IllegalStateException) {
                /*
                    Это абсолютно рандомная мысль, но прикольно же!
                    это не должно переехать во вьюмодель?
                    это вообще нужно обрабатывать? Теоретически, это невозможно и бесполезно использовать без аргументов
                */
                val args = Bundle()
                args.putSerializable(Shared.ERROR_EXCEPTION_TAG, ex)
                args
            }
        )

        return binding.root
    }

    companion object ErrorFragmentFactory {
        fun newInstance(args: Bundle?): ErrorFragment {
            val myFragment = ErrorFragment()
            myFragment.arguments = args
            return myFragment
        }
    }
}
