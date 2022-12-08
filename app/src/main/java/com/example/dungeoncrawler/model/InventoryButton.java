package com.example.dungeoncrawler.model;

import android.content.Context;
import android.view.View;
import android.widget.Button;

public class InventoryButton extends androidx.appcompat.widget.AppCompatButton {

    Item item;

    public InventoryButton(Context context, Item item) {
        super(context);
        this.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        this.item = item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() { return item; }


}
