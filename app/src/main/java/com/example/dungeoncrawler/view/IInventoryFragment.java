package com.example.dungeoncrawler.view;

import com.example.dungeoncrawler.model.*;

public interface IInventoryFragment {

    interface Listener {
        void onEquip(Item item);
        void onClose();
    }
}
