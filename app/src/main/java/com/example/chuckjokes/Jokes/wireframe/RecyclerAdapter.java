package com.example.chuckjokes.Jokes.wireframe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckjokes.Jokes.JokeItem;
import com.example.chuckjokes.Jokes.JokesPresenter;
import com.example.chuckjokes.R;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.chuckjokes.databinding.JokeItemBinding;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

    public final static String JOKE_ID_SYMBOL = "#"; //вот это бы из ресурсов взять, но страшно представить как их сюда пробросить

    static class VH extends RecyclerView.ViewHolder {

        private final JokeItemBinding binding;

        public VH(View itemView) {
            super(itemView);
            binding = JokeItemBinding.bind(itemView);
        }
    }

    private final JokesFragment wireframe;

    public RecyclerAdapter(JokesFragment wireframe) {
        this.wireframe = wireframe;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        try {
            JokeItem item = wireframe.getItem(position);
            holder.binding.jokeID.setText(JOKE_ID_SYMBOL + item.getId().toString()); //meh
            holder.binding.categories.setText(item.getCategories());//no foreach for JSONArray in org.json.JSONArray?
            holder.binding.jokeText.setText(item.getText());
        } catch (Exception ex) {
            wireframe.errorIntercepted(ex);
        }
    }

    @Override
    public int getItemCount() {
        return wireframe.getItemCount();
    }
}
