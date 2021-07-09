package com.example.chuckjokes.Jokes.wireframe;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuckjokes.Jokes.model.JokeItem;
import com.example.chuckjokes.Jokes.JokesPresenter;
import com.example.chuckjokes.MainActivity;

import com.example.chuckjokes.R;
import com.example.chuckjokes.databinding.JokesFragmentBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class JokesFragment extends Fragment {

    private static final int VISIBLE_THRESHOLD = 5;

    private final JokesPresenter presenter;
    private JokesFragmentBinding binding;
    private RecyclerAdapter recyclerAdapter;

    public JokesFragment() {
        this.presenter = new JokesPresenter(this);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = JokesFragmentBinding.inflate(inflater);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.JokesList.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter(this);
        binding.JokesList.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.JokesList.getContext(), linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_item_shape, null));

        binding.JokesList.addItemDecoration(dividerItemDecoration);

        binding.JokesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView unused, int dx, int dy) {
                if (linearLayoutManager.findLastVisibleItemPosition() + VISIBLE_THRESHOLD > linearLayoutManager.getItemCount()) {
                    presenter.onScrolledToEnd();
                }
            }
        });

        presenter.onScrolledToEnd();
        return binding.JokesList;
    }

    public void setErrorFragment(Exception exception) {
        ((MainActivity) requireActivity()).setErrorFragment(exception);
    }

    public void setData(ArrayList<JokeItem> arrayList) {
        recyclerAdapter.setData(arrayList);
    }
}