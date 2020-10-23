package com.example.chuckjokes.Jokes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckjokes.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH>{

    static class VH extends RecyclerView.ViewHolder {

        private final TextView ID;
        private final TextView jokeText;

        public VH(View itemView) {
            super(itemView);
            ID = itemView.findViewById(R.id.jokeID);
            jokeText = itemView.findViewById(R.id.jokeText);
        }
    }

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private JSONArray jsonArray = new JSONArray();
    private final SimpleDataReceiver dataReceiver;

    RecyclerAdapter(SimpleDataReceiver dataReceiver) {
        this.dataReceiver = dataReceiver;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        try {
            holder.ID.setText("#" + jsonArray.getJSONObject(position).getString("id")); //meh, "you will can not translate text" they said;  i said "How the fuck you gonna translate '#'?".
            holder.jokeText.setText(jsonArray.getJSONObject(position).getString("joke"));
        } catch (Exception ex) {
            Log.d("exception",ex.toString());
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public void receiveData(RecyclerView recyclerView, int count) {
        FutureTask<String> future = new FutureTask<>(dataReceiver.receiveData("http://api.icndb.com/jokes/random/", count));
        executor.submit(future);

        try {
            JSONArray answer = new JSONObject(future.get()).getJSONArray("value");
            for(int i=0;i<count;i++) {
                this.jsonArray.put(answer.getJSONObject(i));
            }
        } catch (Exception ex) {
            Log.d("exception",ex.toString());
        }

        recyclerView.post(this::notifyDataSetChanged);

    }



}
