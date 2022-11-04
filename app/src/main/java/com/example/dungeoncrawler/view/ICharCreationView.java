package com.example.dungeoncrawler.view;

import android.view.View;

import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;
import com.example.dungeoncrawler.model.*;

public interface ICharCreationView {

    interface Listener {
        void onAttIncrease(int index, FragmentCharCreationBinding binding);
        void onConfirm(String race, String caste, int[] att);
    }

    View getRootView();
}
