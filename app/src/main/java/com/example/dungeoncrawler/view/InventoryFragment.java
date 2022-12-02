package com.example.dungeoncrawler.view;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    }

    private void setEquipmentButtons() {
        if (game.pc.weapon != null) {
            binding.weaponButton.setText(game.pc.weapon.name);
        } else binding.weaponButton.setText("No weapon");
        binding.weaponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onWeapon();
            }
        });

        if (game.pc.body != null) {
            binding.armorButton.setText(game.pc.body.name);
        } else binding.armorButton.setText("No armor");
        binding.armorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onArmor();
            }
        });

        if (game.pc.potion != null) {
            binding.potionButton.setText(game.pc.potion.name);
        } else binding.potionButton.setText("No potion");
        binding.potionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPotion();
            }
        });
    }

    private void displayInventory(List<Item> inventory) {
        for (Item i : inventory) {
            TextView item = new TextView(this.binding.getRoot().getContext());
            item.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            String itemText = i.name + ": " + i.getClass();
            item.setText(itemText);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onInventoryItem();
                }
            });
            this.binding.inventoryLayout.addView(item);
        }
    }

}
