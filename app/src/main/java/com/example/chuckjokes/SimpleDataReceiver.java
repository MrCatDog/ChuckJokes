package com.example.chuckjokes;

import android.util.Log;

import androidx.fragment.app.FragmentManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleDataReceiver implements Runnable {

    private FragmentManager fragmentManager;
    private final OkHttpClient client;
    private String url;

    SimpleDataReceiver(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.client = new OkHttpClient();
    }

    public void setUrl(String url) {
        this.url=url;
    }

    private boolean checkConnection() {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()){
            Log.d("answer", response.toString());
            if(response.isSuccessful())
                return true;
        } catch (Exception error) {
            ErrorFragment errorFragment = new ErrorFragment(error);
            fragmentManager.beginTransaction().replace(R.id.fragmentView,errorFragment);
        }
        return false;
    }

    public boolean setConnection(String url) {
        setUrl(url);
        return checkConnection();
    }

    public void run() {
        setConnection("https://api.icndb.com");
    }

    public void receiveData(String url) {
        //setConnection(url);
        //return receiveData(url, 10);
    }

    /*
    public JSONArray receiveData(String url, int count) {
        if(setConnection(url)) {

        }
        return ;
    }
     */
}
