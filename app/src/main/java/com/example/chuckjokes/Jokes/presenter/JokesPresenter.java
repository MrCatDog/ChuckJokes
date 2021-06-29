package com.example.chuckjokes.Jokes.presenter;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.chuckjokes.Jokes.JokesFragment;
import com.example.chuckjokes.Jokes.JokesModel;
import com.example.chuckjokes.R;

public class JokesPresenter {

    private final JokesFragment wireframe;
    private final JokesModel model;

    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter recyclerAdapter;

    public JokesPresenter(JokesFragment wireframe) {
        this.wireframe = wireframe;
        this.model = new JokesModel();
    }

    public void init() {
        linearLayoutManager = new LinearLayoutManager(wireframe.getContext());
        wireframe.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter(wireframe.getMainActivityWeakReference());
        wireframe.setAdapter(recyclerAdapter);

        recyclerAdapter.receiveData(model.getBaseJokesCount());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(wireframe.getRecyclerContext(), linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ResourcesCompat.getDrawable(wireframe.getResources(), R.drawable.divider_item_shape, null));

        wireframe.addItemDecoration(dividerItemDecoration);

        wireframe.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager, recyclerAdapter));

    }
}
