package com.example.chuckjokes.Jokes;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private final int visibleThreshold = 5;
    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;
    // True if we are still waiting for the last set of data to load.
    private boolean loading = true;

    LinearLayoutManager LLM;
    RecyclerAdapter RA;

    EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager, RecyclerAdapter recyclerAdapter) {
        this.LLM = layoutManager;
        this.RA = recyclerAdapter;
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScrolled(@NotNull RecyclerView view, int dx, int dy) {
        int totalItemCount = LLM.getItemCount();

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && (LLM.findLastVisibleItemPosition() + visibleThreshold) > totalItemCount) {
            onLoadMore(view);
            loading = true;
        }
    }

    // Call this method in case of reloading data
    public void resetState() {
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    // Defines the process for actually loading more data
    private void onLoadMore(RecyclerView recyclerView) {
        RA.receiveData(recyclerView,15);
    }

}
