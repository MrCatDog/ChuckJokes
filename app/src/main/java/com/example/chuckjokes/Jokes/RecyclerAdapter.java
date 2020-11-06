package com.example.chuckjokes.Jokes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckjokes.Error.ErrorFragment;
import com.example.chuckjokes.MainActivity;
import com.example.chuckjokes.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH>{

    static class VH extends RecyclerView.ViewHolder {

        private final TextView ID;
        private final TextView jokeText;
        private final TextView categories;

        public VH(View itemView) {
            super(itemView);
            ID = itemView.findViewById(R.id.jokeID);
            categories = itemView.findViewById(R.id.categories);
            jokeText = itemView.findViewById(R.id.jokeText);
        }
    }

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final WeakReference<MainActivity> mainActivityWeakReference;
    private JSONArray jsonArray = new JSONArray();
    private final SimpleDataReceiver dataReceiver;

    RecyclerAdapter(WeakReference<MainActivity> mainActivityWeakReference) {
        this.mainActivityWeakReference = mainActivityWeakReference;
        this.dataReceiver = new SimpleDataReceiver(this.mainActivityWeakReference);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            holder.ID.setText("#" + jsonObject.getString("id")); //meh, "you will can not translate text" they said;  i said "How the fuck you gonna translate '#'?".
            holder.categories.setText(jsonToString(jsonObject.getJSONArray("categories")));//no foreach for JSONArray in org.json.JSONArray?
            holder.jokeText.setText(jsonObject.getString("joke"));
        } catch (Exception ex) {
            ErrorFragment errorFragment = new ErrorFragment(ex);
            final MainActivity activity = this.mainActivityWeakReference.get();
            activity.changeFragment(errorFragment);
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    private String jsonToString(JSONArray ja) throws org.json.JSONException {
        if(ja.length() <= 0)
            return "";
        String str = ja.getString(0);
        for(int i = 1; i<ja.length(); i++)
            str = str.concat(","+ja.getString(i));
        return str;
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
