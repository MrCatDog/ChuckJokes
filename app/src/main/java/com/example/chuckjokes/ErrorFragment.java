package com.example.chuckjokes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ErrorFragment extends Fragment {
    String errMsg;

    ErrorFragment(Exception err) {
        errMsg = err.getLocalizedMessage();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView errContainer = (TextView) inflater.inflate(R.layout.error_fragment,null);
        errContainer.setText(errMsg);
        return errContainer;
    }

}
