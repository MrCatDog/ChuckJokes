package com.example.chuckjokes.Jokes;

import com.example.chuckjokes.Jokes.wireframe.JokesFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JokesPresenter {

    public final static String BASE_URL = "http://api.icndb.com/jokes/random/";
    public final static String ESCAPE_JS = "?escape=javascript"; //for correct form of quotes(non &quot;)
    public final static String ANSWER_TAG = "value";
    public final static String JOKE_ID_TAG = "id";
    public final static String JOKE_CATEGORY_TAG = "categories";
    public final static String JOKE_TEXT_TAG = "joke";

    private final JokesFragment wireframe;
    private final JokesModel model;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final OkHttpClient client = new OkHttpClient();

    public JokesPresenter(JokesFragment wireframe) {
        this.wireframe = wireframe;
        this.model = new JokesModel();
    }

    public void receiveData(int count) {
        FutureTask<String> future = new FutureTask<>(() -> {
            Request request = new Request.Builder().url(BASE_URL + count + ESCAPE_JS).build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            }
            return null;
        });

        try {
            executor.submit(future);
            JSONArray answer = new JSONObject(future.get()).getJSONArray(ANSWER_TAG);
            JSONObject object;
            for (int i = 0; i < count; i++) {
                object = answer.getJSONObject(i);
                model.add(new JokeItem(object.getInt(JOKE_ID_TAG), object.getString(JOKE_TEXT_TAG), jsonToString(object.getJSONArray(JOKE_CATEGORY_TAG))));
            }
        } catch (Exception ex) {
            errorIntercepted(ex);
        }

    }

    public int getItemCount() {
        return model.getArrayLength();
    }

    public JokeItem getItem(int pos) {
        return model.getItem(pos);
    }

    public void errorIntercepted(Exception ex) {
        wireframe.setErrorFragment(ex);
    }

    private String jsonToString(JSONArray ja) throws org.json.JSONException {
        if (ja.length() <= 0)
            return "";
        String str = ja.getString(0);
        for (int i = 1; i < ja.length(); i++)
            str = str.concat("," + ja.getString(i));
        return str;
    }
}
