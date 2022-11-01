package com.example.dungeoncrawler.view;

import android.view.View;

public interface ICharCreationView {

    interface Listener {
        void onAttIncrease(int index);
    }

    View getRootView();


}
