package com.example.user.mvvmtestwithdaggerrefractor.baseview;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.user.mvvmtestwithdaggerrefractor.BaseApplication;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseApplication)getActivity().getApplication()).appComponent().inject(this);
    }

    public ViewModelProvider.Factory getViewModelFactory() {
        return viewModelFactory;
    }
}
