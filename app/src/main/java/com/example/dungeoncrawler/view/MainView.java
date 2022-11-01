package com.example.dungeoncrawler.view;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dungeoncrawler.databinding.ActivityMainBinding;

public class MainView implements IMainView {
    FragmentManager fmanager;
    ActivityMainBinding binding;

    public MainView(FragmentActivity activity) {
        this.fmanager = activity.getSupportFragmentManager();
        this.binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
    }

}
