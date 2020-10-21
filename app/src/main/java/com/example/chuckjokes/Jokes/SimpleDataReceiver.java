package com.example.chuckjokes.Jokes;

import com.example.chuckjokes.Error.ErrorFragment;
import com.example.chuckjokes.MainActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleDataReceiver {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private final OkHttpClient client;

    SimpleDataReceiver(MainActivity mainActivity) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.client = new OkHttpClient();
    }

    public Callable<String> receiveData(String url, int count) {
        return () -> {
            Request request = new Request.Builder().url(url+count).build();
            try (Response response = this.client.newCall(request).execute()){
                if(response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (Exception error) {
                ErrorFragment errorFragment = new ErrorFragment(error);
                final MainActivity activity = this.mainActivityWeakReference.get();
                activity.changeFragment(errorFragment);
            }
            return "";
        };
    }
}
