package com.example.user.mvvmtestwithdaggerrefractor.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.user.mvvmtestwithdaggerrefractor.service.model.Project;
import com.example.user.mvvmtestwithdaggerrefractor.service.repository.ProjectRepository;

import javax.inject.Inject;

public class ProjectDetailViewModel extends AndroidViewModel {
    ProjectRepository projectRepository;

    @Inject
    public ProjectDetailViewModel(@NonNull Application application, ProjectRepository projectRepository) {
        super(application);
        this.projectRepository = projectRepository;
    }

    public LiveData<Project> getObservableProject(String projectId){
       return projectRepository.getProjectDetail("Google",projectId);
    }


}
