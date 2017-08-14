package com.toped.app.newsped;

import android.app.Application;
import android.content.Context;

import com.toped.app.newsped.presentation.di.component.AppComponent;
import com.toped.app.newsped.presentation.di.component.DaggerAppComponent;
import com.toped.app.newsped.presentation.di.module.AppModule;

/**
 * Created by arysuryawan on 8/12/17.
 */

public class App extends Application {
    private static AppComponent component;

    public App() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent(Context context) {
        return ((App) context.getApplicationContext()).component;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
