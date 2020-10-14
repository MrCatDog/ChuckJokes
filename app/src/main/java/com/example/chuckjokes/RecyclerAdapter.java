package com.example.chuckjokes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

public class RecyclerAdapter extends RecyclerView.Adapter {

    private JSONArray jsonArray;

    public RecyclerAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
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
}
