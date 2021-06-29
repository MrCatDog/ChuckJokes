package com.example.chuckjokes.Error;

import android.view.View;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorPresenter {

    private final ErrorFragment wireframe;
    private final ErrorModel model;

    public ErrorPresenter(ErrorFragment errorFragment, Exception err) {
        this.wireframe = errorFragment;
        this.model = new ErrorModel(err);
    }

    public void setError() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        model.getError().printStackTrace(pw);

        wireframe.setBaseText(model.getError().getLocalizedMessage());
        wireframe.setExtendText(sw.toString());
        wireframe.setExtendVisibility(View.GONE);
    }

    public void onMoreBtnClicked() {
        if (wireframe.getExtendVisibility() == View.GONE) {
            wireframe.setExtendVisibility(View.VISIBLE);
        }
        else {
            wireframe.setExtendVisibility(View.GONE);
        }
    }
}
