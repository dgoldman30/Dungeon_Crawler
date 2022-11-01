package com.example.dungeoncrawler.view;

import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.dungeoncrawler.databinding.ActivityMainBinding;
import com.example.dungeoncrawler.model.Race;

public class CharCreationView implements ICharCreationView {
    ActivityMainBinding binding;
    Listener listener;

    public CharCreationView(Context context, Listener listener) {
        this.binding = ActivityMainBinding.inflate(LayoutInflater.from(context));
        this.listener = listener;

        
    }

    @Override
    public View getRootView() { return this.binding.getRoot();}

}
