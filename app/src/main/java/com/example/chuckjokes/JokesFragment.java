package com.example.chuckjokes;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        jokesList.setLayoutManager(new LinearLayoutManager(getContext()));
        jokesList.setAdapter(new RecyclerAdapter());

        FutureTask<String> future = new FutureTask<>(dataReceiver.receiveData("http://api.icndb.com/jokes/random/", 10));
        executor.submit(future);

        try {
            JSONArray Jarray =  new JSONObject(future.get()).getJSONArray("value");
            Log.println(Log.INFO,"answer", Jarray.getJSONObject(1).getString("joke"));//ахуеть оно работает! осталось скормить!!!
        } catch (Exception ex) {
            Log.d("exception",ex.toString());
        }



        return jokesList;
    }
}