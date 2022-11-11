package com.example.dungeoncrawler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;
import com.example.dungeoncrawler.model.*;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Race;
import com.example.dungeoncrawler.view.CharCreationFragment;
import com.example.dungeoncrawler.view.ExploreFragment;
import com.example.dungeoncrawler.view.ICharCreationView;
import com.example.dungeoncrawler.view.IExploreFragment;
import com.example.dungeoncrawler.view.IMainView;
import com.example.dungeoncrawler.view.MainView;

import java.util.Locale;

public class ControllerActivity extends AppCompatActivity implements ICharCreationView.Listener, IExploreFragment.Listener {


    Game.GameStates gameState = Game.GameStates.START;
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
    public void onConfirm(String race, String caste, int[] att) {
        Race pcRace = Race.HUMAN;
        Caste pcCaste = Caste.GLADIATOR;
        for (Race r : Race.values()) { if (r.name().equals(race.toUpperCase())) pcRace = r; }
        for (Caste c : Caste.values()) { if (c.name().equals(caste.toUpperCase()))  pcCaste = c;  }

        Game game = new Game(10);
        game.pc = new Player(pcRace, pcCaste, att);
        game.pc.occupy(game.map[0][0]);

        game.enemy = new NPC(Race.HUMAN, Caste.GLADIATOR, true, 1);
        game.enemy.occupy(game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)]);
        game.enemy.setTarget(game.pc);

        gameState = Game.GameStates.EXPLORE;

        ExploreFragment exploreFragment = new ExploreFragment(this, game);

        this.mainView.displayFragment(exploreFragment, false, "explore");
    }

    @Override
    public void onInventory() {

    }

    @Override
    public void onCharSheet() {

    }

    @Override
    public void onEquipment() {

    }
}