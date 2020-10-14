package com.example.chuckjokes;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.zip.Inflater;

public class JokesFragment extends Fragment {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
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


        FutureTask<String> future = new FutureTask<>(dataReceiver.receiveData("http://api.icndb.com/jokes/random/", 10));
        executor.submit(future);

        try {
            JSONArray jArray =  new JSONObject(future.get()).getJSONArray("value");
            jokesList.setAdapter(new RecyclerAdapter(jArray));
        } catch (Exception ex) {
            Log.d("exception",ex.toString());
        }

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(jokesList.getContext(),
                LLM.getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable( R.drawable.divider_item_shape));
        jokesList.addItemDecoration(dividerItemDecoration);

        return jokesList;
    }
}