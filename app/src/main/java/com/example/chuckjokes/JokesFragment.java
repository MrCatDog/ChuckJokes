package com.example.chuckjokes;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class JokesFragment extends Fragment {

    SimpleDataReceiver dataReceiver;
    private Handler mHandler = new Handler();

    JokesFragment(MainActivity mainActivity) {
        dataReceiver = new SimpleDataReceiver(mainActivity, "http://api.icndb.com/jokes/random/");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView jokesList = (RecyclerView) inflater.inflate(R.layout.jokes_fragment, null);
        jokesList.setLayoutManager(new LinearLayoutManager(getContext()));
        jokesList.setAdapter(new RecyclerAdapter());
        new Thread(dataReceiver).start();
        //mHandler.post(dataReceiver);
        //dataReceiver.run();
        //dataReceiver.receiveData("https://api.icndb.com");
        return jokesList;
    }
}