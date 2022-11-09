package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;

public class CharCreationFragment extends Fragment implements ICharCreationView {

    FragmentCharCreationBinding binding ;
    Listener listener;

    String race;
    String caste;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CharCreationFragment(Listener listener) { this.listener = listener; }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentCharCreationBinding.inflate(inflater);
        return this.binding.getRoot();
        // return inflater.inflate(R.layout.fragment_char_creation, container, false);
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
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int[] attPoints = new int[4];
        final int[] attCounter = {3};

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
                race = (String) binding.raceSpinner.getSelectedItem();
                caste = (String) binding.casteSpinner.getSelectedItem();
                listener.onConfirm(race, caste, attPoints);
                Log.d("Dungeon Crawler", "enter");
            }
        });
    }



    @Override
    public View getRootView() { return this.binding.getRoot(); }

}
