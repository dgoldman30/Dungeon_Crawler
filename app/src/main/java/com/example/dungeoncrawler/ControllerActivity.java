package com.example.dungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;
import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Race;
import com.example.dungeoncrawler.view.CharCreationFragment;
import com.example.dungeoncrawler.view.ICharCreationView;
import com.example.dungeoncrawler.view.IMainView;
import com.example.dungeoncrawler.view.MainView;

public class ControllerActivity extends AppCompatActivity implements ICharCreationView.Listener{

    Game game = new Game(10);
    IMainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        CharCreationFragment charCreationFragment = new CharCreationFragment(this);
        this.mainView = new MainView(this);
        setContentView(mainView.getRootView());
        mainView.displayFragment(charCreationFragment, false, "char creation");
    }


    @Override
    public void onAttIncrease(int count, FragmentCharCreationBinding binding) {
        binding.attText.setText(count--);
    }

    @Override
    public void onConfirm(String race, String caste, int[] att) {
        Race pcRace = Race.HUMAN;
        Caste pcCaste = Caste.GLADIATOR;
        for (Race r : Race.values()) { if (r.name() == race) pcRace = r; }
        for (Caste c : Caste.values()) { if (c.name() == race) pcCaste = c; }

        game.pc = new Player(pcRace, pcCaste, att);
    }
}