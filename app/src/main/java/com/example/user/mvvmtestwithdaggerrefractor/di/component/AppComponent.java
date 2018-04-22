package com.example.user.mvvmtestwithdaggerrefractor.di.component;


import com.example.user.mvvmtestwithdaggerrefractor.baseview.BaseFragment;
import com.example.user.mvvmtestwithdaggerrefractor.di.module.AppModule;
import com.example.user.mvvmtestwithdaggerrefractor.di.module.DataBaseModule;
import com.example.user.mvvmtestwithdaggerrefractor.di.module.NetModule;
import com.example.user.mvvmtestwithdaggerrefractor.view.ui.ProjectDetailFragment;
import com.example.user.mvvmtestwithdaggerrefractor.view.ui.ProjectListFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {DataBaseModule.class, AppModule.class, NetModule.class})
public interface AppComponent {

    void inject(BaseFragment fragment);


    void inject(ProjectListFragment projectListFragment);
    void inject(ProjectDetailFragment projectDetailFragment);
}
