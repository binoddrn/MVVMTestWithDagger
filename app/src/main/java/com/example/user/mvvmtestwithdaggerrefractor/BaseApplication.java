package com.example.user.mvvmtestwithdaggerrefractor;

import android.app.Application;

import com.example.user.mvvmtestwithdaggerrefractor.di.component.AppComponent;
import com.example.user.mvvmtestwithdaggerrefractor.di.component.DaggerAppComponent;
import com.example.user.mvvmtestwithdaggerrefractor.di.module.AppModule;
import com.example.user.mvvmtestwithdaggerrefractor.di.module.DataBaseModule;
import com.example.user.mvvmtestwithdaggerrefractor.di.module.NetModule;

public class BaseApplication extends Application{

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .dataBaseModule(new DataBaseModule())
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }
}
