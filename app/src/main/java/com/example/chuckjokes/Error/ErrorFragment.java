package com.example.chuckjokes.Error;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.chuckjokes.databinding.ErrorFragmentBinding;

import org.jetbrains.annotations.NotNull;

public class ErrorFragment extends Fragment {

    private final ErrorPresenter presenter;

    private ErrorFragmentBinding binding;

    public ErrorFragment(Exception err) {
        this.presenter = new ErrorPresenter(this, err);
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = ErrorFragmentBinding.inflate(inflater);

        presenter.setError();

        binding.moreAboutErrorBut.setOnClickListener(unused -> presenter.onMoreBtnClicked());

        return binding.getRoot();
    }

    public void setBaseText(String text) {
        binding.errorBaseInfo.setText(text);
    }

    public int getExtendVisibility() {
        return binding.moreAboutError.getVisibility();
    }

    public void setExtendVisibility(int state) {
        binding.moreAboutError.setVisibility(state);
    }

    public void setExtendText(String text) {
        binding.moreAboutError.setText(text);
    }

}
