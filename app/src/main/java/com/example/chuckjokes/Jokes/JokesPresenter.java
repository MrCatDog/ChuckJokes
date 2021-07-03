package com.example.chuckjokes.Jokes;

import com.example.chuckjokes.ErrorFragment;
import com.example.chuckjokes.Jokes.wireframe.JokesFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JokesPresenter {

    //Насколько по-SOLID такой класс? вроде бы, он достаточно многозадачен, что протиоречит S-принципу

    public final static String BASE_URL = "http://api.icndb.com/jokes/random/";
    public final static String ESCAPE_JS = "?escape=javascript"; //for correct form of quotes(non &quot;)
    public final static String ANSWER_TAG = "value";

    private final JokesFragment wireframe;
    private final JokesModel model;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final OkHttpClient client = new OkHttpClient();

    public JokesPresenter(JokesFragment wireframe) {
        this.wireframe = wireframe;
        this.model = new JokesModel();
    }

    public void receiveData(int count) {

        //не уверен в этом блоке. Мне точно никто тут не отбросит исключение?
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
            for (int i = 0; i < count; i++) {
                model.putJSON(answer.getJSONObject(i));
            }
        } catch (Exception ex) {
            errorIntercepted(ex);
        }

    }

    public int getItemCount() {
        return model.getJsonArrayLength();
    }

    public JSONObject getItem(int pos) throws JSONException {
        return model.getItem(pos);
    }

    public void errorIntercepted(Exception ex) {
        ErrorFragment errorFragment = new ErrorFragment(ex);
        wireframe.changeFragment(errorFragment);
    }
}
