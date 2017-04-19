package com.sensedog.service;

import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class TokenHandler extends FirebaseInstanceIdService {



    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("POJAHN", "Refreshed token: " + refreshedToken);

        /*
         * Check if there is an auth token in internal space.
         * If true, call $rootScope which should broadcast some angular event.
         * If false, we assume that the webview will fetch the ID manually soon thus skip the broadcast.
         */

        /*
         * New thoughts: The app might not be running when this method is called, so we might never update the token if use the idea suggested above.
         * Perhaps this method should persist the token to the server?
         * If so, the webview should save the auth token to internal storage when service is created so it can be fetched here and persisted.
         */
    }
}
