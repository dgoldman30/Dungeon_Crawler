package com.example.dungeoncrawler.view;

import com.example.dungeoncrawler.databinding.FragmentExploreBinding;
import com.example.dungeoncrawler.model.Game;

public interface IExploreFragment {

    interface Listener {
        void onInventory();
        void onCharSheet();
        void onSpell();
        void onGameRestart();
        String onMove();
        String onCombat(Game game, String input);
        String onEnemyDefeated(Game game);
        void updateHP();
        void printMap(Game game);
        void setBinding(FragmentExploreBinding binding);
        void performLevelUp();
        void onLeaderboard();

    }
}
