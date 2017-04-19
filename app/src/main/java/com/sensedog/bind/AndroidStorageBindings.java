package com.sensedog.bind;

import android.webkit.JavascriptInterface;
import com.sensedog.persistance.DictionaryStorage;

public class AndroidStorageBindings {

    private final DictionaryStorage storage;

    public AndroidStorageBindings(DictionaryStorage storage) {
        this.storage = storage;
    }

    @JavascriptInterface
    public void put(String key, String value) {
        storage.put(key, value);
    }

    @JavascriptInterface
    public String  get(String key) {
        return storage.get(key);
    }

    @JavascriptInterface
    public void remove(String key) {
        storage.remove(key);
    }
}
