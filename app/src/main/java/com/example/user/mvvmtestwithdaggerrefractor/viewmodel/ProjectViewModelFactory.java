package com.example.user.mvvmtestwithdaggerrefractor.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.user.mvvmtestwithdaggerrefractor.service.repository.ProjectRepository;

import javax.inject.Inject;

public class ProjectViewModelFactory implements ViewModelProvider.Factory{
    Application application;
    ProjectRepository repository;

    @Inject
    public ProjectViewModelFactory(Application application, ProjectRepository repository) {
        this.repository = repository;
        this.application = application;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProjectListViewModel.class)) {
            return (T) new ProjectListViewModel(application, repository);
        }else   if (modelClass.isAssignableFrom(ProjectDetailViewModel.class)) {
            return (T) new ProjectDetailViewModel(application, repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}





