package com.example.dungeoncrawler.view;


import android.view.View;

import androidx.fragment.app.Fragment;


public interface IMainView {

    View getRootView();

    void displayFragment(Fragment fragment, boolean allowBack, String name);

}
