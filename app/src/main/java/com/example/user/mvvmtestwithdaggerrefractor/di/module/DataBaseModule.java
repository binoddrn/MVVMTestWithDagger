package com.example.user.mvvmtestwithdaggerrefractor.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.user.mvvmtestwithdaggerrefractor.db.AppDatabase;
import com.example.user.mvvmtestwithdaggerrefractor.db.ProjectDao;
import com.example.user.mvvmtestwithdaggerrefractor.db.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {


    public DataBaseModule() {}

    @Singleton
    @Provides
    public AppDatabase getDBInstance(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration() // Add custom migration when needed
                .build();
    }

    @Singleton
    @Provides
    public UserDao getUserDao(AppDatabase database) {
        return database.userDao();
    }

    @Singleton
    @Provides
    public ProjectDao getProjectDao(AppDatabase database){
        return database.projectDao();
    }
}








