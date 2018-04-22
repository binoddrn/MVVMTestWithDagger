package com.example.user.mvvmtestwithdaggerrefractor.service.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = CASCADE))
public class Project {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String full_name;
    public String userId;

    @Ignore
    public User owner;
    public String html_url;
    public String description;
    public String url;
    public Date created_at;
    public Date updated_at;
    public Date pushed_at;
    public String git_url;
    public String ssh_url;
    public String clone_url;
    public String svn_url;
    public String homepage;
    public int stargazers_count;
    public int watchers_count;
    public String language;
    public boolean has_issues;
    public boolean has_downloads;
    public boolean has_wiki;
    public boolean has_pages;
    public int forks_count;
    public int open_issues_count;
    public int forks;
    public int open_issues;
    public int watchers;
    public String default_branch;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }
}
