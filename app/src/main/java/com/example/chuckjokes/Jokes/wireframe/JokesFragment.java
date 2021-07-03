package com.example.chuckjokes.Jokes.wireframe;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuckjokes.Jokes.JokesPresenter;
import com.example.chuckjokes.MainActivity;

import com.example.chuckjokes.R;
import com.example.chuckjokes.databinding.JokesFragmentBinding;

import org.jetbrains.annotations.NotNull;

public class JokesFragment extends Fragment {

    public static final int START_JOKES_VALUE = 15;

    private final JokesPresenter presenter;
    private JokesFragmentBinding binding;

    public JokesFragment() {
        this.presenter = new JokesPresenter(this);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = JokesFragmentBinding.inflate(inflater);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.JokesList.setLayoutManager(linearLayoutManager);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(presenter);
        binding.JokesList.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.JokesList.getContext(), linearLayoutManager.getOrientation());
        //requireNonNull? узнать подробнее.
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_item_shape, null));

        binding.JokesList.addItemDecoration(dividerItemDecoration);

        binding.JokesList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager, recyclerAdapter));

        recyclerAdapter.receiveData(START_JOKES_VALUE);
        //я бы мог сделать так (в EndlessRecyclerViewScrollListener тоже)
//        presenter.receiveData(START_JOKES_VALUE);
//        recyclerAdapter.notifyDataSetChanged();
        //и тогда reciveData() из адаптера исчезнет,
        // но это в каждом новом классе тащить и адаптер и презентер, вместо одного адаптера

        return binding.JokesList;
    }

    public void changeFragment(Fragment newFragment) {
        ((MainActivity) requireActivity()).changeFragment(newFragment);
    }
}