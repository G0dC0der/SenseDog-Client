package com.sensedog.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class NotificationReceiver extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}