package com.sensedog.web;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class InternalWebClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if(request.getUrl().getHost().length() == 0) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
        view.getContext().startActivity(intent);
        return true;
    }
//
//    @Override
//    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//        String url = request.getUrl().toString();
//        if (url.startsWith("server:")) {
//            return request;
//        }
//
//        return super.shouldInterceptRequest(view, request);
//    }
//
//    @NonNull
//    private WebResourceResponse handleRequest(@NonNull String urlString) throws MalformedURLException {
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//            connection.setRequestProperty("User-Agent", "");
//            connection.setRequestMethod("GET");
//            connection.setDoInput(true);
//            connection.connect();
//
//            InputStream inputStream = connection.getInputStream();
//            return new WebResourceResponse("text/json", "utf-8", inputStream);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        catch (ProtocolException e) {
//            e.printStackTrace();
//            return null;
//        }catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
}
