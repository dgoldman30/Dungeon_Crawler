package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.ControllerActivity;
import com.example.dungeoncrawler.R;
import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;
import com.example.dungeoncrawler.model.*;

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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int[] attPoints = new int[4];
        final int[] attCounter = {3};

        //STR button
        this.binding.STRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attPoints[0]++;
                listener.onAttIncrease(attCounter[0], binding);
                attCounter[0]--;

            }
        });
        //DEX button
        this.binding.DEXbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attPoints[1]++;
                listener.onAttIncrease(attCounter[0], binding);
                attCounter[0]--;
            }
        });
        //INT button
        this.binding.INTbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attPoints[2]++;
                listener.onAttIncrease(attCounter[0], binding);
                attCounter[0]--;
            }
        });
        //WILL button
        this.binding.WILLbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attPoints[3]++;
                listener.onAttIncrease(attCounter[0], binding);
                attCounter[0]--;
            }
        });


        this.binding.enterChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                race = (String) binding.raceSpinner.getSelectedItem();
                caste = (String) binding.casteSpinner.getSelectedItem();

                listener.onConfirm(race, caste, attPoints);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentCharCreationBinding.inflate(inflater);
        return inflater.inflate(R.layout.fragment_char_creation, container, false);
    }


    @Override
    public View getRootView() { return this.binding.getRoot(); }

}
