package com.kcode.reamlexample.app;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by caik on 2016/11/15.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
    }
}
