package com.example.user.mvvmtestwithdaggerrefractor.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.user.mvvmtestwithdaggerrefractor.service.model.Project;
import com.example.user.mvvmtestwithdaggerrefractor.service.model.User;

@Database(entities = {User.class, Project.class}, version = 1,exportSchema = false)
@TypeConverters({DateConverter.class})

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;


    public static AppDatabase getDatabase(Context context) {
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract UserDao userDao();

    public abstract ProjectDao projectDao();

}
