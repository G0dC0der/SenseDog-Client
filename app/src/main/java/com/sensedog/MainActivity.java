package com.sensedog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
//import com.google.android.gms.common.GoogleApiAvailability;
import com.sensedog.bind.AndroidCloudBindings;
import com.sensedog.bind.AndroidInformationBindings;
import com.sensedog.bind.AndroidServiceBindings;
import com.sensedog.bind.AndroidStorageBindings;
import com.sensedog.persistance.DictionaryStorage;
import com.sensedog.web.InternalWebClient;

public class MainActivity extends AppCompatActivity {

    public static final String SENSEDOG_VERSION = "1.0";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.sensedog.R.layout.activity_main);

//        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
//        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
//        if(status != ConnectionResult.SUCCESS) {
//            if(googleApiAvailability.isUserResolvableError(status)) {
////                googleApiAvailability.getErrorDialog(this, status, 2404).show();
//                googleApiAvailability.makeGooglePlayServicesAvailable(this);
//            }
//        }
        WebView.setWebContentsDebuggingEnabled(true);

        webView = (WebView) findViewById(com.sensedog.R.id.web_view);
        webView.setWebViewClient(new InternalWebClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new AndroidServiceBindings(this), AndroidServiceBindings.class.getSimpleName());
        webView.addJavascriptInterface(new AndroidInformationBindings(this), AndroidInformationBindings.class.getSimpleName());
        webView.addJavascriptInterface(new AndroidStorageBindings(new DictionaryStorage(this)), AndroidStorageBindings.class.getSimpleName());
        webView.addJavascriptInterface(new AndroidCloudBindings(), AndroidCloudBindings.class.getSimpleName());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
