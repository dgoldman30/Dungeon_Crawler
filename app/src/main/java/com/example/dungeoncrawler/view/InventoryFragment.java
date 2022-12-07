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
        binding.weaponButton.getBackground().setColorFilter(new LightingColorFilter(Color.RED, Color.RED));
        if (game.pc.body != null) {
            binding.armorButton.setText(game.pc.body.name);
        } else binding.armorButton.setText("No armor");
        binding.weaponButton.getBackground().setColorFilter(new LightingColorFilter(Color.GREEN, Color.GREEN));

        if (game.pc.potion != null) {
            binding.potionButton.setText(game.pc.potion.name);
        } else binding.potionButton.setText("No potion");
        binding.weaponButton.getBackground().setColorFilter(new LightingColorFilter(Color.BLUE, Color.BLUE));
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
        if (i instanceof Weapon) {button.getBackground().setColorFilter(new LightingColorFilter(0xFF00F0, 0x00F0FF)); }
        if (i instanceof Armor) { button.getBackground().setColorFilter(new LightingColorFilter(0xF00FF0, 0x000000)); }
        if (i instanceof Potion) { button.getBackground().setColorFilter(new LightingColorFilter(0x00FFFF, 0x00FF00));}
    }

    private void selectItem(Item item) {
        if (item instanceof Weapon) {
            binding.weaponButton.setEnabled(true);

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
