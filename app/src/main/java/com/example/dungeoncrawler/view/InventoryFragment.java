package com.example.dungeoncrawler.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.databinding.FragmentInventoryBinding;
import com.example.dungeoncrawler.model.*;

import java.util.List;

public class InventoryFragment extends Fragment implements IInventoryFragment {
    int armorGreen = Color.rgb( 0, 200, 0);
    int potionTeal = Color.rgb(102, 255, 255);
    int weaponRed = Color.rgb(255, 0, 0);
    int spellPurple = Color.rgb(200, 0, 200);
    public FragmentInventoryBinding binding;
    Listener listener;
    Game game;
    private final String CURGAME = "cur_Game";
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
   public void onViewStateRestored(@Nullable Bundle saveInstanceState){
        super.onViewStateRestored(saveInstanceState);
        if (saveInstanceState != null) {
            this.game = (Game) saveInstanceState.getSerializable(CURGAME);
        }
       }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            this.game = (Game) savedInstanceState.getSerializable(CURGAME);
        }
        List<Item> inventory = game.pc.inventory;
        setEquipmentButtons();
        displayInventory(inventory);
        updateInventory();

        binding.closeInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClose();
            }
        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CURGAME, this.game);
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

        if (game.pc.spell != null) {
            binding.setSpellButton.setText(game.pc.spell.name);
        } else binding.setSpellButton.setText("No spell");
        binding.setSpellButton.setBackgroundColor(spellPurple);

        binding.setSpellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void displaySpells(Spell[] knownSpells) {
        binding.inventoryTable.setVisibility(View.INVISIBLE);
        binding.spellView.setVisibility(View.VISIBLE);

        for (Spell s : knownSpells) {
            SpellButton spell = new SpellButton(this.binding.getRoot().getContext(), s);
            spell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    game.pc.spell = spell.getSpell();
                    binding.inventoryTable.setVisibility(View.VISIBLE);
                    binding.spellView.setVisibility(View.INVISIBLE);
                }
            });
            binding.spellView.addView(spell);
        }
    }

    private void displayInventory(List<Item> inventory) {
        TableRow row = new TableRow(this.binding.getRoot().getContext());
           for (Item i : inventory) {
                row.setGravity(Gravity.CENTER);
                InventoryButton item = new InventoryButton(this.binding.getRoot().getContext(), i);
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectItem(item.getItem(), item);
                    }
                });
                row.addView(item);
                if (row.getChildCount() == 3) {
                    binding.inventoryTable.addView(row);
                    row = new TableRow(this.binding.getRoot().getContext());
                }
            }
            if (row.getChildCount() < 3) {
                binding.inventoryTable.addView(row);
            }
            updateInventory();
    }

    private void updateInventory() {
        for (int i = 0; i < binding.inventoryTable.getChildCount(); i ++) {
            TableRow row = (TableRow) binding.inventoryTable.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                InventoryButton itemButton = (InventoryButton) row.getChildAt(j);
                int index = i * 3 + j;
                row.removeViewAt(j);
                if (index < game.pc.inventory.size()) {
                    itemButton.setItem(game.pc.inventory.get(index));
                    setColor(itemButton, itemButton.getItem());
                    String itemText = itemButton.getItem().getName();
                    itemButton.setText(itemText);
                    row.addView(itemButton, j);
                }
            }
        }
    }



    private void setColor(Button button, Item i) {
        if (i instanceof Weapon) {button.getBackground().setColorFilter(weaponRed, PorterDuff.Mode.DARKEN); }
        if (i instanceof Armor) { button.getBackground().setColorFilter(armorGreen, PorterDuff.Mode.DARKEN);; }
        if (i instanceof Potion) {button.getBackground().setColorFilter(potionTeal, PorterDuff.Mode.DARKEN);}
    }

    private void selectItem(Item item, InventoryButton button) {
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
                button.setItem(item);
                updateInventory();
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
                button.setItem(item);
                updateInventory();
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
                    button.setItem(item);
                    updateInventory();
                }
            });
        }
    }
}
