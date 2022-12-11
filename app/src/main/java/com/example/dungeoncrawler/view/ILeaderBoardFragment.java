package com.example.dungeoncrawler.view;

import com.example.dungeoncrawler.model.Item;

public interface ILeaderBoardFragment {
    interface Listener {
        void onClose();

        void onGameRestart();
    }
}
