package com.example.user.mvvmtestwithdaggerrefractor.di.module;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.example.user.mvvmtestwithdaggerrefractor.db.ProjectDao;
import com.example.user.mvvmtestwithdaggerrefractor.service.repository.GitHubService;
import com.example.user.mvvmtestwithdaggerrefractor.service.repository.ProjectRepository;
import com.example.user.mvvmtestwithdaggerrefractor.viewmodel.ProjectViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    ProjectRepository provideProjectRepository(GitHubService service, ProjectDao projectDao) {
        return new ProjectRepository(service,projectDao);
    }


    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(Application application, ProjectRepository repository) {
        return new ProjectViewModelFactory(application, repository);
    }

}
