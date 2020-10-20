package com.example.chuckjokes;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class JokesFragment extends Fragment {


    private final SimpleDataReceiver dataReceiver;

    JokesFragment(MainActivity mainActivity) {
        dataReceiver = new SimpleDataReceiver(mainActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView jokesList = (RecyclerView) inflater.inflate(R.layout.jokes_fragment, null);
        LinearLayoutManager LLM = new LinearLayoutManager(getContext());
        jokesList.setLayoutManager(LLM);

        RecyclerAdapter RA = new RecyclerAdapter(dataReceiver);

        jokesList.setAdapter(RA);

        RA.reciveData(10);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(jokesList.getContext(),
                LLM.getOrientation());
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_item_shape, null));//Да блять, а тут откуда

        jokesList.addItemDecoration(dividerItemDecoration);

        return jokesList;
    }
}