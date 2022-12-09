package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;
import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Race;

public class CharCreationFragment extends Fragment implements ICharCreationView {

    FragmentCharCreationBinding binding ;
    Listener listener;
    Game testGame = new Game(10);

    public CharCreationFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentCharCreationBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    public void onAttIncrease() {
        int holdy = Integer.parseInt(this.binding.attPointsLeft.getText().toString());
        holdy--;
        this.binding.attPointsLeft.setText(Integer.toString(holdy));
        if (holdy == 0) {
            this.binding.STRbutton.setEnabled(false);
            this.binding.WILLbutton.setEnabled(false);
            this.binding.DEXbutton.setEnabled(false);
            this.binding.INTbutton.setEnabled(false);
        }
    }

    private void updateDescriptions() {
        String rString = (String) binding.raceSpinner.getSelectedItem();
        String cString = (String) binding.casteSpinner.getSelectedItem();
        int[] test = {};

        Race race = Race.HUMAN;
        Caste caste = Caste.APPRENTICE;
        for (Race r : Race.values()) { if (r.name().equals(rString.toUpperCase())) race = r; }
        for (Caste c : Caste.values()) { if (c.name().equals(cString.toUpperCase()))  caste = c; }
        Player testChar = new Player(race, caste, test);

        String raceDesc = "\nBase Attributes: \n";
        for (int i = 0; i < race.attributeAdjustments.length; i ++) {
            raceDesc += testChar.attributes[i].name + " - " + race.attributeAdjustments[i] + "\n";
        }
        raceDesc += "\nFavored Skills: \n";
        for (int i = 0; i < race.favoredSkills.length; i++) {
            raceDesc += race.favoredSkills[i] + "\n";
        }

        String casteDesc = "\nStarting Equipment: \n " ;
        for (int i = 0; i < caste.startingItems.size(); i++) {
            casteDesc += caste.startingItems.get(i).getName() + "\n";
        }
        casteDesc += "\nFavored Skills: \n";
        for (int i = 0; i < caste.favoredSkills.length; i++) {
            casteDesc += caste.favoredSkills[i] + "\n";
        }

        binding.raceDescription.setText(raceDesc);
        binding.casteDescription.setText(casteDesc);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int[] attPoints = new int[4];
        final int[] attCounter = {3};

        updateDescriptions();
        binding.raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateDescriptions();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                updateDescriptions();
            }
        });

        binding.casteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateDescriptions();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                updateDescriptions();
            }
        });

        //STR button
        this.binding.STRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAttIncrease();
                attPoints[0]++;
                attCounter[0]--;

                Log.d("Dungeon Crawler", "str");

            }
        });
        //DEX button
        this.binding.DEXbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAttIncrease();
                attPoints[1]++;
                attCounter[0]--;
                Log.d("Dungeon Crawler", "dex");
            }
        });
        //INT button
        this.binding.INTbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAttIncrease();
                attPoints[2]++;
                attCounter[0]--;
                Log.d("Dungeon Crawler", "int");
            }
        });
        //WILL button
        this.binding.WILLbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAttIncrease();
                attPoints[3]++;
                attCounter[0]--;
                Log.d("Dungeon Crawler", "will");
            }
        });


        this.binding.enterChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String race = (String) binding.raceSpinner.getSelectedItem();
                String caste = (String) binding.casteSpinner.getSelectedItem();
                listener.onConfirmCharacter(race, caste, attPoints);
                Log.d("Dungeon Crawler", "enter");
            }
        });
    }

    public View getRootView() { return this.binding.getRoot(); }
}
