package com.sensedog.persistance;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class AssetLoader {

    private final Context context;

    public AssetLoader(Context context) {
        this.context = context;
    }

    public JSONObject loadJson(String filename) {
        String json;

        try (InputStream is = context.getAssets().open(filename)){
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
