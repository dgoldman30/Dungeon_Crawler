package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;
import com.example.dungeoncrawler.model.*;

public class CharCreationFragment extends Fragment implements ICharCreationView {

    FragmentCharCreationBinding binding;
    Listener listener;

    String race = binding.raceSpinner.getSelectedItem().toString();
    String caste = binding.casteSpinner.getSelectedItem().toString();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CharCreationFragment(Listener listener) { this.listener = listener; }

    public CharCreationFragment newInstance(String param1, String param2) {
        CharCreationFragment fragment = new CharCreationFragment(listener);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    }

    @Override
    public View getRootView() { return this.binding.getRoot(); }

}
