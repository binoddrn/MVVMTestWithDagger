package com.example.user.mvvmtestwithdaggerrefractor.view.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.mvvmtestwithdaggerrefractor.R;
import com.example.user.mvvmtestwithdaggerrefractor.service.model.Project;
import com.example.user.mvvmtestwithdaggerrefractor.view.ui.ProjectDetailFragment;

import java.util.List;
import java.util.Objects;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    Context context;
    List<Project> projectList;


    public ProjectAdapter(List<Project> projectList,Context context) {

        this.projectList=projectList;
        this.context=context;
    }

    public void setProjectList(final List< Project> projectList) {
        if (this.projectList == null) {
            this.projectList =projectList;
            notifyItemRangeInserted(0,projectList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProjectAdapter.this.projectList.size();
                }

                @Override
                public int getNewListSize() {
                    return projectList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProjectAdapter.this.projectList.get(oldItemPosition).id == projectList.get(newItemPosition).id;
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Project project = projectList.get(newItemPosition);
                    Project old = projectList.get(oldItemPosition);
                    return project.id == old.id && Objects.equals(project.git_url, old.git_url);
                }
            });
            this.projectList = projectList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_list_item, parent, false);

        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projectList.get(position);
        holder.bindView(project,context);
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView languages;
        TextView projectWatchers;
        LinearLayout linearLayout;

        public ProjectViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            languages = (TextView) itemView.findViewById(R.id.languages);
            projectWatchers = (TextView) itemView.findViewById(R.id.project_watchers);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linearLayout);
        }



        public void bindView(final Project project, final Context context) {
            name.setText(project.name);
            languages.setText(project.language);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("Project Id",project.name);
                    ProjectDetailFragment projectDetailFragment=ProjectDetailFragment.newInstance(project.name);


                    ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, projectDetailFragment,"hostelDetailFragment")
                            .addToBackStack("directionFragment")
                            .commit();

                }
            });
//            projectWatchers.setText(project.watchers);
        }
    }

}

