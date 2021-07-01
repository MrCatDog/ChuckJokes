package com.example.chuckjokes.Jokes.presenter;

import com.example.chuckjokes.ErrorFragment;
import com.example.chuckjokes.MainActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleDataReceiver {

    private final WeakReference<MainActivity> mainActivityWeakReference;
    private final OkHttpClient client;

    SimpleDataReceiver(WeakReference<MainActivity> mainActivity) {
        this.mainActivityWeakReference = mainActivity;
        this.client = new OkHttpClient();
    }

    public Callable<String> receiveData(String url, int count) {
        return () -> {
            Request request = new Request.Builder().url(url + count + "?escape=javascript").build(); //"?escape=javascript" for correct form of quotes(non &quot;)
            try (Response response = this.client.newCall(request).execute()) {
                if (response.isSuccessful()) {
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
