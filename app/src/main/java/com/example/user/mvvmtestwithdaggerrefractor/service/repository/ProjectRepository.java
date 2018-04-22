package com.example.user.mvvmtestwithdaggerrefractor.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.user.mvvmtestwithdaggerrefractor.db.ProjectDao;
import com.example.user.mvvmtestwithdaggerrefractor.service.model.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepository {
    private GitHubService gitHubService;
    private ProjectDao projectDao;


    @Inject
    public ProjectRepository(GitHubService gitHubService,ProjectDao projectDao) {
        this.gitHubService = gitHubService;
        this.projectDao=projectDao;
    }

    public LiveData<List<Project>> getProjectList(String userId) {
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();
        Log.e("request","requesting to getProjectList");
        gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, final Response<List<Project>> response) {
                Log.e("response",response.body().size()+"");
                data.setValue(response.body());


               // projectDao.insert(response.body());
                new AsyncTask<List<Project>,Void,Void>() {

                    @Override
                    protected Void doInBackground(List<Project>... args) {
                        projectDao.insert(args[0]);
                        return null;
                    }
                }.execute(response.body());


                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        Log.e("kjsas","doInBackground");
                        return null;

                    }

                    @Override
                    protected void onProgressUpdate(Void... values) {
                        super.onProgressUpdate(values);

                        Log.e("kjsas","onProgressUpdate");
                    }
                }.execute();






                projectDao.getProjectList();

            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Log.e("failure",t.getMessage());
            }
        });
        return data;
    }


//    public void getData(){
//       Log.e("projectData", projectDao.getProjectList()+"");
//    }




    public LiveData<Project> getProjectDetail(String user, String projectName){
        final MutableLiveData<Project> liveData=new MutableLiveData<>();
        gitHubService.getProjectDetails(user,projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                Log.e("response",response.body()+"");


              if (response!=null){
                    Log.e("response!null","response not null");
                    Log.e("response",response.body().toString());
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Log.e("responseFail",t.getMessage());
            }
        });
        return liveData;
    }


    public LiveData<List<Project>> getProjectListItem(){
        return projectDao.getProjectList();
    }


}
