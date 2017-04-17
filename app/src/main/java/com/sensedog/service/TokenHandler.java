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


        //TODO: send the token to the server. We need to know the auth token.
    }
}
