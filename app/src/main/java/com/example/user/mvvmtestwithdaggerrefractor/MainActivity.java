package com.example.user.mvvmtestwithdaggerrefractor;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.mvvmtestwithdaggerrefractor.view.ui.ProjectListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadProjectListFragment();
    }

    private void loadProjectListFragment() {
        replaceFragment(new ProjectListFragment());
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();
    }
}
