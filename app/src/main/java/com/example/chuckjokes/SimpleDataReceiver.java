package com.example.chuckjokes;

import android.util.Log;

import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleDataReceiver implements Runnable {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private final OkHttpClient client;
    private String url;

    SimpleDataReceiver(MainActivity mainActivity, String url) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.client = new OkHttpClient();
        this.url = url;
    }

    public void setUrl(String url) {
        this.url=url;
    }

    public void run() {
        receiveData();
    }

    public boolean receiveData() {
        return receiveData(10);
    }

    public boolean receiveData(int count) {
        Request request = new Request.Builder().url(url+count).build();
        try (Response response = client.newCall(request).execute()){
            Log.d("answer", response.toString());
            if(response.isSuccessful()) {

            }
        } catch (Exception error) {
            ErrorFragment errorFragment = new ErrorFragment(error);
            final MainActivity activity = mainActivityWeakReference.get();
            activity.changeFragment(errorFragment);
        }
        return false;
    }
}
