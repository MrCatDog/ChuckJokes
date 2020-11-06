package com.example.chuckjokes.Jokes;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuckjokes.MainActivity;
import com.example.chuckjokes.R;

import java.lang.ref.WeakReference;

public class JokesFragment extends Fragment {

    private final WeakReference<MainActivity> mainActivityWeakReference;

    public JokesFragment(MainActivity mainActivity) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView jokesList = (RecyclerView) inflater.inflate(R.layout.jokes_fragment, container, false);
        LinearLayoutManager LLM = new LinearLayoutManager(getContext());
        jokesList.setLayoutManager(LLM);

        RecyclerAdapter RA = new RecyclerAdapter(this.mainActivityWeakReference);
        jokesList.setAdapter(RA);
        RA.receiveData(jokesList,10);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(jokesList.getContext(), LLM.getOrientation());
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_item_shape, null));//Да блять, а тут откуда

        jokesList.addItemDecoration(dividerItemDecoration);

        jokesList.addOnScrollListener(new EndlessRecyclerViewScrollListener(LLM,RA));

        return jokesList;
    }
}