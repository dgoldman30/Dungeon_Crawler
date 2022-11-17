package com.example.dungeoncrawler.view;

import android.view.View;

public interface ICharCreationView {

    interface Listener {
        void onConfirmCharacter(String race, String caste, int[] att);
    }

    View getRootView();
}
