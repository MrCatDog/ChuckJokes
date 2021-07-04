package com.example.chuckjokes.Jokes.wireframe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckjokes.Jokes.JokeItem;
import com.example.chuckjokes.R;

import com.example.chuckjokes.databinding.JokeItemBinding;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

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
            holder.binding.jokeID.setText(wireframe.getResources().getString(R.string.joke_id_symbol, item.getId()));
            holder.binding.categories.setText(item.getCategories());
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
