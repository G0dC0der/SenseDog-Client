package com.sensedog.service;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.sensedog.ServerClient;
import com.sensedog.persistance.AssetLoader;
import com.sensedog.persistance.DictionaryStorage;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TokenHandler extends FirebaseInstanceIdService {

    private DictionaryStorage storage;
    private String baseUrl;

    @Override
    public void onTokenRefresh() {
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        System.out.print("Refreshed token: " + refreshedToken);

        if (storage == null) {
            storage = new DictionaryStorage(this);
        }

        if (baseUrl == null) {
            baseUrl = readBaseUrl();
        }

        Map<String, String> payload = new HashMap<>();
        payload.put("cloudToken", refreshedToken);

        String alarmToken = storage.get("alarm-auth-token");
        if (alarmToken != null) {
            Map<String, String> headers = new HashMap<>();
            headers.put("alarm-auth-token", alarmToken);

            Volley.newRequestQueue(this).add(new ServerClient.PostRequest(
                    baseUrl + "/alarm/update/cloud",
                    new JSONObject(payload),
                    headers));
        } else {
            String masterToken = storage.get("master-auth-token");
            if (masterToken != null) {
                Map<String, String> headers = new HashMap<>();
                headers.put("master-auth-token", masterToken);

                Volley.newRequestQueue(this).add(new ServerClient.PostRequest(
                        baseUrl + "/master/update/cloud",
                        new JSONObject(payload),
                        headers));
            }
        }
    }

    private String readBaseUrl() {
        try {
            return new AssetLoader(this).loadJson("config.json").getString("baseUrl");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
