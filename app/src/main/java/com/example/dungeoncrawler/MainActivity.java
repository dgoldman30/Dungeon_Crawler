package com.example.dungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dungeoncrawler.view.ICharCreationView;

public class MainActivity extends AppCompatActivity {

    ICharCreationView charCreationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}