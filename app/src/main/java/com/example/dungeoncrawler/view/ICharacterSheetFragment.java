package com.example.dungeoncrawler.view;

import android.view.View;

public interface ICharacterSheetFragment {

    interface Listener {
        void onLevelUp();
        void onClose();
    }

    View getRootView();
}
