package com.sensedog.bind;

import android.webkit.JavascriptInterface;
import com.google.firebase.iid.FirebaseInstanceId;

public class AndroidCloudBindings {

    @JavascriptInterface
    public String getCloudToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }
}
