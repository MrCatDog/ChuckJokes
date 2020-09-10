package com.example.chuckjokes;

import org.json.JSONArray;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleDataReceiver implements DataReceive {

    private boolean setConnection(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return true;
        } catch (java.io.IOException error) {
            return false;
        }
    }

    public JSONArray receiveData(String url) {
        return receiveData(url, 10);
    }

    public JSONArray receiveData(String url, int count) {


    }
}
