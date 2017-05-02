package com.sensedog;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ServerClient {

    public static class PostRequest extends JsonObjectRequest {

        Map<String, String> headers = new HashMap<>();

        public PostRequest(String url,
                           JSONObject jsonObject,
                           Map<String, String> headers) {
            this(url, jsonObject, headers, DEFAULT_LISTENER, DEFAULT_ERROR_LISTENER);
        }

        public PostRequest(String url,
                           JSONObject jsonRequest,
                           Map<String, String> headers,
                           Response.Listener<JSONObject> listener,
                           Response.ErrorListener errorListener) {
            super(Method.POST, url, jsonRequest, listener, errorListener);
            this.headers = headers;
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return headers;
        }
    };

    private static final Response.Listener<JSONObject> DEFAULT_LISTENER = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            System.out.println("Request succeed.");
        }
    };

    private static final Response.ErrorListener DEFAULT_ERROR_LISTENER = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println("Request Failed.");
        }
    };
}
