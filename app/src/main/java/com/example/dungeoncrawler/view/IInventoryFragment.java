package com.example.dungeoncrawler.view;

public interface IInventoryFragment {

    interface Listener {
        void onInventoryItem();
        void onWeapon();
        void onArmor();
        void onPotion();
    }
}
