package com.example.chuckjokes.Jokes.wireframe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckjokes.Jokes.model.JokeItem;
import com.example.chuckjokes.R;

import com.example.chuckjokes.databinding.JokeItemBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

    static class VH extends RecyclerView.ViewHolder {

        private final JokeItemBinding binding;

        public VH(View itemView) {
            super(itemView);
            binding = JokeItemBinding.bind(itemView);
        }
    }

    private final JokesFragment wireframe;
    private List<JokeItem> items = new ArrayList<>();

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
        JokeItem item = items.get(position);
        holder.binding.jokeID.setText(wireframe.getResources().getString(R.string.joke_id_symbol, item.getId()));
        holder.binding.categories.setText(item.getCategories());
        holder.binding.jokeText.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<JokeItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
