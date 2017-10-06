package com.icecreamshop.creator;

import android.app.Application;

import com.icecreamshop.creator.di.components.AppComponent;
import com.icecreamshop.creator.di.components.DaggerAppComponent;
import com.icecreamshop.creator.di.modules.AppModule;

/**
 * Created on 10/6/17.
 *
 * @author Andrii S.
 */

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        buildAppComponent();
    }

    private void buildAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }
}
