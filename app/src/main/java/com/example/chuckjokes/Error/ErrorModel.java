package com.example.chuckjokes.Error;

public class ErrorModel {
    private Exception error;

    public ErrorModel(Exception error) {
        setError(error);
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public Exception getError() {
        return error;
    }
}
