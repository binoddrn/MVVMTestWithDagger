package com.example.user.mvvmtestwithdaggerrefractor.di.module;


import android.arch.lifecycle.ViewModelProvider;

import com.example.user.mvvmtestwithdaggerrefractor.service.repository.GitHubService;
import com.example.user.mvvmtestwithdaggerrefractor.viewmodel.ProjectViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.user.mvvmtestwithdaggerrefractor.service.repository.GitHubService.HTTPS_API_GITHUB_URL;

@Module
public class NetModule {

    @Singleton
    @Provides
    GitHubService provideGitHubService(){
        return new Retrofit.Builder()
                .baseUrl(HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService.class);
    }
}
