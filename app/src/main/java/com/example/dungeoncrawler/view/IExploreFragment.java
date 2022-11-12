package com.example.dungeoncrawler.view;

import android.view.View;

public interface IExploreFragment {

    interface Listener {
        void onInventory();
        void onCharSheet();
        void onEquipment();
        void onRestart();
    }

    View getRootView();

}
