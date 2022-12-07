package com.example.dungeoncrawler.view;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.R;
import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;
import com.example.dungeoncrawler.databinding.FragmentInventoryBinding;
import com.example.dungeoncrawler.model.*;

import java.util.List;

public class InventoryFragment extends Fragment implements IInventoryFragment {
    int armorGreen = Color.rgb( 0, 200, 0);
    int potionTeal = Color.rgb(102, 255, 255);
    int weaponRed = Color.rgb(255, 0, 0);
    public FragmentInventoryBinding binding;
    Listener listener;
    Game game;

    public InventoryFragment(Listener listener, Game game) {
        this.listener = listener;
        this.game = game;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentInventoryBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Item> inventory = game.pc.inventory;
        setEquipmentButtons();
        displayInventory(inventory);

        binding.closeInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClose();
            }
        });

    }

    private void setEquipmentButtons() {

        if (game.pc.weapon != null) {
            binding.weaponButton.setText(game.pc.weapon.name);
        } else binding.weaponButton.setText("No weapon");
        binding.weaponButton.setBackgroundColor(weaponRed);
        if (game.pc.body != null) {
            binding.armorButton.setText(game.pc.body.name);
        } else binding.armorButton.setText("No armor");
        binding.armorButton.setBackgroundColor(armorGreen);

        if (game.pc.potion != null) {
            binding.potionButton.setText(game.pc.potion.name);
        } else binding.potionButton.setText("No potion");
        binding.potionButton.setBackgroundColor(potionTeal);
    }

    private void displayInventory(List<Item> inventory) {
        TableRow row = new TableRow(this.binding.getRoot().getContext());
        String itemText = "";
            for (Item i : inventory) {
                Button item = new Button(this.binding.getRoot().getContext());
                setColor(item, i);
                item.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                itemText = i.getName();
                item.setText(itemText);
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectItem(i);
                    }
                });
                row.addView(item);
                if (row.getChildCount() == 3) {
                    binding.inventoryTable.addView(row);
                    row = new TableRow(this.binding.getRoot().getContext());
                }
            }


    }

    private void setColor(Button button, Item i) {
        if (i instanceof Weapon) {button.getBackground().setColorFilter(weaponRed, PorterDuff.Mode.DARKEN); }
        if (i instanceof Armor) { button.getBackground().setColorFilter(armorGreen, PorterDuff.Mode.DARKEN);; }
        if (i instanceof Potion) {button.getBackground().setColorFilter(potionTeal, PorterDuff.Mode.DARKEN);}
    }

    private void selectItem(Item item) {
        if (item instanceof Weapon) {
            binding.weaponButton.setEnabled(true);
            binding.potionButton.setEnabled(false);
            binding.armorButton.setEnabled(false);

            binding.weaponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEquip(item);
                binding.weaponButton.setText(item.getName());
                binding.weaponButton.setEnabled(false);
            }
        }); }
        else if (item instanceof Armor) {
            binding.armorButton.setEnabled(true);
            binding.weaponButton.setEnabled(false);
            binding.potionButton.setEnabled(false);

            binding.armorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEquip(item);
                binding.armorButton.setText(item.getName());
                binding.armorButton.setEnabled(false);
            }
        }); }
        else if (item instanceof Potion) {
            binding.potionButton.setEnabled(true);
            binding.weaponButton.setEnabled(false);
            binding.armorButton.setEnabled(false);

            binding.potionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEquip(item);
                    binding.potionButton.setText(item.getName());
                    binding.potionButton.setEnabled(false);
                }
            });
        }
    }

}
