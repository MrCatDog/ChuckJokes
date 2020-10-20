package com.example.chuckjokes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private JSONArray jsonArray;
    private final SimpleDataReceiver dataReceiver;

    RecyclerAdapter(SimpleDataReceiver dataReceiver) {
        this.dataReceiver = dataReceiver;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item,parent,false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView jokeID = holder.itemView.findViewById(R.id.jokeID);
        TextView jokeText = holder.itemView.findViewById(R.id.jokeText);

        try {
            jokeID.setText("#" + jsonArray.getJSONObject(position).getString("id")); //meh, "you will can not translate text" they said;  i said "How the fuck you gonna translate '#'?".
            jokeText.setText(jsonArray.getJSONObject(position).getString("joke"));
        } catch (Exception ex) {
            Log.d("exception",ex.toString());
        }

    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public void reciveData(int count) {
        FutureTask<String> future = new FutureTask<>(dataReceiver.receiveData("http://api.icndb.com/jokes/random/", count));
        executor.submit(future);

        try {
            this.jsonArray =  new JSONObject(future.get()).getJSONArray("value");//тут добавить через цикл хотяб
        } catch (Exception ex) {
            Log.d("exception",ex.toString());
        }

        notifyDataSetChanged();
    }



}
