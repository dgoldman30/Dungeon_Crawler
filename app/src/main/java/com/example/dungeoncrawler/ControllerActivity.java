package com.example.dungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.Race;
import com.example.dungeoncrawler.view.CharCreationFragment;
import com.example.dungeoncrawler.view.ICharCreationView;
import com.example.dungeoncrawler.view.IMainView;
import com.example.dungeoncrawler.view.MainView;

public class ControllerActivity extends AppCompatActivity implements ICharCreationView.Listener{

    ICharCreationView charCreationView;
    IMainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_char_creation);

        this.mainView = new MainView(this);
        //this.mainView.displayFragment(new CharCreationFragment(), false, "char-creation");

        setContentView(mainView.getRootView());
    }


    @Override
    public void onAttIncrease(int index) {

    }

    @Override
    public void onConfirm(String race, String caste, int[] att) {
        Race pcRace;
        Caste pcCaste;
        for (Race r : Race.values()) { if (r.name() == race) pcRace = r; }
        for (Caste c : Caste.values()) { if (c.name() == race) pcCaste = c; }

        
    }
}