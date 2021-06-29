package com.example.chuckjokes.Jokes;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuckjokes.Jokes.presenter.JokesPresenter;
import com.example.chuckjokes.Jokes.presenter.RecyclerAdapter;
import com.example.chuckjokes.MainActivity;

import java.lang.ref.WeakReference;
import com.example.chuckjokes.databinding.JokesFragmentBinding;

import org.jetbrains.annotations.NotNull;

public class JokesFragment extends Fragment {

    private final WeakReference<MainActivity> mainActivityWeakReference;
    private JokesFragmentBinding binding;
    private final JokesPresenter presenter;

    public JokesFragment(MainActivity mainActivity) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.presenter = new JokesPresenter(this);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = JokesFragmentBinding.inflate(inflater);
        presenter.init();
        return binding.JokesList;
    }

    public void setLayoutManager(LinearLayoutManager linearLayoutManager) {
        binding.JokesList.setLayoutManager(linearLayoutManager);
    }

    public void setAdapter(RecyclerAdapter recyclerAdapter) {
        binding.JokesList.setAdapter(recyclerAdapter);
    }

    public Context getRecyclerContext() {
        return binding.JokesList.getContext();
    }

    public void addItemDecoration(DividerItemDecoration dividerItemDecoration) {
        binding.JokesList.addItemDecoration(dividerItemDecoration);
    }

    public void addOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        binding.JokesList.addOnScrollListener(onScrollListener);
    }

    public WeakReference<MainActivity> getMainActivityWeakReference() {
        return mainActivityWeakReference;
    }
}