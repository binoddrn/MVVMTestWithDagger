package com.example.user.mvvmtestwithdaggerrefractor.db;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.user.mvvmtestwithdaggerrefractor.service.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Project> projectList);

    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getProjectList();
}