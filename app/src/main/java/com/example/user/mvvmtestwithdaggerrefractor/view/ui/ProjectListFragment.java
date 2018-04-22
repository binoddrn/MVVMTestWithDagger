package com.example.user.mvvmtestwithdaggerrefractor.view.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.mvvmtestwithdaggerrefractor.R;
import com.example.user.mvvmtestwithdaggerrefractor.baseview.BaseFragment;
import com.example.user.mvvmtestwithdaggerrefractor.service.model.Project;
import com.example.user.mvvmtestwithdaggerrefractor.view.adapter.ProjectAdapter;
import com.example.user.mvvmtestwithdaggerrefractor.viewmodel.ProjectListViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectListFragment extends BaseFragment {
    private View mainView;

    private RecyclerView recyclerView;
    private ProjectAdapter adapter;

    private ProjectListViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_project_list, container, false);
        findView();
        init();
        return mainView;
    }


    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // ProjectAdapter adapter = new ProjectAdapter(projectClickCallback);
        recyclerView.setAdapter(adapter);
    }

    public void findView() {
        recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProjectListViewModel.class);
        viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects == null) return;
                updateProjectList(projects);
            }
        });
    }

    private void updateProjectList(List<Project> projects) {
        Log.e("projectFragment", projects.size() + "");
        ProjectAdapter projectAdapter = new ProjectAdapter(projects, getContext());
        recyclerView.setAdapter(projectAdapter);
    }
}
