package com.example.user.mvvmtestwithdaggerrefractor.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.mvvmtestwithdaggerrefractor.service.model.Project;
import com.example.user.mvvmtestwithdaggerrefractor.service.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

public class ProjectListViewModel extends AndroidViewModel {
    private final ProjectRepository repo;


    @Inject
    public ProjectListViewModel(@NonNull Application application, @NonNull ProjectRepository projectRepository) {
        super(application);
        this.repo = projectRepository;
    }


    public LiveData<List<Project>> getProjectListObservable() {
       // LiveData<List<Project>> projectListObservable = repo.getProjectListItem();
        LiveData<List<Project>> projectListObservable = repo.getProjectList("Google");


        return Transformations.map(projectListObservable, new Function<List<Project>, List<Project>>() {
            @Override
            public List<Project> apply(List<Project> input) {
                Log.e("ProjectListViewModel",input.size()+"");
                if (input == null)

                    return repo.getProjectListItem().getValue();
                return input;
            }
        });
    }
}
