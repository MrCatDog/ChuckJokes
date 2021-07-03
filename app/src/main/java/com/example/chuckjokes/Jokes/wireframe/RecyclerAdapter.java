package com.example.chuckjokes.Jokes.wireframe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuckjokes.Jokes.JokesPresenter;
import com.example.chuckjokes.R;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.chuckjokes.databinding.JokeItemBinding;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {


    public final static String JOKE_ID_TAG = "id";
    public final static String JOKE_CATEGORY_TAG = "categories";
    public final static String JOKE_TEXT_TAG = "joke";
    public final static String JOKE_ID_SYMBOL = "#"; //вот это бы из ресурсов взять, но страшно представить как их сюда пробросить

    static class VH extends RecyclerView.ViewHolder {

        private final JokeItemBinding binding;

        public VH(View itemView) {
            super(itemView);
            binding = JokeItemBinding.bind(itemView);
        }
    }

    private final JokesPresenter presenter;

    public RecyclerAdapter(JokesPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        try {
            JSONObject jsonObject = presenter.getItem(position);
            holder.binding.jokeID.setText(JOKE_ID_SYMBOL + jsonObject.getString(JOKE_ID_TAG)); //meh
            holder.binding.categories.setText(jsonToString(jsonObject.getJSONArray(JOKE_CATEGORY_TAG)));//no foreach for JSONArray in org.json.JSONArray?
            holder.binding.jokeText.setText(jsonObject.getString(JOKE_TEXT_TAG));
        } catch (Exception ex) {
            presenter.errorIntercepted(ex);
        }
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    //не должен ли я перенести это в презентер? этот шаг приведёт к почти к прямым обращениям к модели через презентер,
    //что выглядит странно, хотя и более MVPшно вроде как...
    private String jsonToString(JSONArray ja) throws org.json.JSONException {
        if (ja.length() <= 0)
            return "";
        String str = ja.getString(0);
        for (int i = 1; i < ja.length(); i++)
            str = str.concat("," + ja.getString(i));
        return str;
    }

    //это вызывается именно здесь так как нужен notifyDataSetChanged();
    //правильно ли это?
    public void receiveData(int count) {
        presenter.receiveData(count);
        //recyclerView.post(this::notifyDataSetChanged); это зачем было-то?
        notifyDataSetChanged();
    }
}
