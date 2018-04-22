package com.example.user.mvvmtestwithdaggerrefractor.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.example.user.mvvmtestwithdaggerrefractor.service.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);


    @Insert
    void insert(List<User> userList);
}