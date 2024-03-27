package com.department.assessment.application;

import android.app.Application;

public class App extends Application {
    public static App mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }
}
