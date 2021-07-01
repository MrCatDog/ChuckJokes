package com.example.chuckjokes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.chuckjokes.databinding.ErrorFragmentBinding;

import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorFragment extends Fragment {

    private final Exception error;
    private ErrorFragmentBinding binding;

    public ErrorFragment(Exception err) {
        this.error = err;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = ErrorFragmentBinding.inflate(inflater);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        error.printStackTrace(pw);

        binding.errorBaseInfo.setText(error.getLocalizedMessage());
        binding.moreAboutError.setText(sw.toString());
        binding.moreAboutError.setVisibility(View.GONE);

        binding.moreAboutErrorBut.setOnClickListener(unused -> {
            if (binding.moreAboutError.getVisibility() == View.GONE) {
                binding.moreAboutError.setVisibility(View.VISIBLE);
            } else {
                binding.moreAboutError.setVisibility(View.GONE);
            }
        });

        return binding.getRoot();
    }
}
