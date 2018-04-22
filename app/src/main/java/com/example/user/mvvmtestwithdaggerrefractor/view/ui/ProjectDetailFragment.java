package com.example.user.mvvmtestwithdaggerrefractor.view.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.mvvmtestwithdaggerrefractor.R;
import com.example.user.mvvmtestwithdaggerrefractor.baseview.BaseFragment;
import com.example.user.mvvmtestwithdaggerrefractor.service.model.Project;
import com.example.user.mvvmtestwithdaggerrefractor.service.model.User;
import com.example.user.mvvmtestwithdaggerrefractor.viewmodel.ProjectDetailViewModel;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";

    TextView name;
    TextView projectDescription;
    TextView languages;
    TextView projectWatchers;
    TextView projectOpenIssues;
    TextView projectCreatedAt;
    TextView projectUpdatedAt;
    TextView cloneUrl;

    ImageView userImage;
    private View mainView;
    private String mProjectId;

    private ProjectDetailViewModel viewModel;

    public ProjectDetailFragment() {
        // Required empty public constructor
    }



    public static ProjectDetailFragment newInstance(String param1) {
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProjectId = getArguments().getString(ARG_PARAM1);
            Log.e("ProjectDetailFragment", mProjectId);
            //  mParam2 = getArguments().getString(ARG_PARAM2);
        }

        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProjectDetailViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView= inflater.inflate(R.layout.fragment_project_detail, container, false);
        findView();
        init();
        return mainView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getObservableProject(mProjectId).observe(this, new Observer<Project>() {
            @Override
            public void onChanged(@Nullable Project project) {
                if (project == null) return;

                setProjectDetails(project);
            }
        });

    }

    public void findView() {
        name = (TextView) mainView.findViewById(R.id.name);
        projectDescription = (TextView) mainView.findViewById(R.id.project_desc);
        languages = (TextView) mainView.findViewById(R.id.languages);
        projectWatchers = (TextView) mainView.findViewById(R.id.project_watchers);
        projectOpenIssues = (TextView) mainView.findViewById(R.id.project_open_issues);
        projectCreatedAt = (TextView) mainView.findViewById(R.id.project_created_at);
        projectUpdatedAt = (TextView) mainView.findViewById(R.id.project_updated_at);
        cloneUrl = (TextView) mainView.findViewById(R.id.clone_url);
        userImage=(ImageView)mainView.findViewById(R.id.user_image);
    }

    public void init() {

    }

    public void setProjectDetails(Project project) {
        name.setText(project.name);
        projectDescription.setText(project.description);
        languages.setText(project.language);
        int s = project.watchers;
        String watchers = String.valueOf(s);
        projectWatchers.setText(watchers);
        int s1 = project.open_issues;
        String openIssues = String.valueOf(s1);
        projectOpenIssues.setText(openIssues);
        Log.e("createdDate", project.created_at + "");
        // projectCreatedAt.setText((CharSequence) project.created_at);
        //  Date updatedAt = project.updated_at;
        //  projectUpdatedAt.setText((CharSequence) updatedAt);
        Date cdate = project.created_at;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Log.e("date", simpleDateFormat.format(cdate));
        projectCreatedAt.setText(simpleDateFormat.format(cdate));

        Date udate = project.updated_at;
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        projectUpdatedAt.setText(simpleDateFormat1.format(udate));
        cloneUrl.setText(project.clone_url);

        User user= project.owner;
        String s3=user.avatar_url;
        Log.e("avatarUrl",s3);

        Glide.with(getContext())
                .load(s3)
                .into(userImage);
    }
}
