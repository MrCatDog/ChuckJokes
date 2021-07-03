package com.example.chuckjokes.Jokes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JokesModel {

    private JSONArray jsonArray = new JSONArray();

    public int getJsonArrayLength() {
        return jsonArray.length();
    }

    public void putJSON(JSONObject object) {
        jsonArray.put(object);
    }

    public JSONObject getItem(int pos) throws JSONException {
        return jsonArray.getJSONObject(pos);
    }
}
