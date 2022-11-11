package com.example.dungeoncrawler.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.ControllerActivity;
import com.example.dungeoncrawler.R;
import com.example.dungeoncrawler.databinding.FragmentCharCreationBinding;
import com.example.dungeoncrawler.databinding.FragmentExploreBinding;
import com.example.dungeoncrawler.model.Attribute;
import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.NPC;
import com.example.dungeoncrawler.model.Race;
import com.example.dungeoncrawler.model.Tile;

import java.util.Collections;
import java.util.List;

public class ExploreFragment extends Fragment implements IExploreFragment {

    FragmentExploreBinding binding;
    Listener listener;
    Game game;

    int depth = 0;

    public ExploreFragment(Listener listener, Game game) {
        this.listener = listener;
        this.game = game;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentExploreBinding.inflate(inflater);

        String name = game.pc.race.name() + " " + game.pc.caste.name();
        String level = "Level " + game.pc.level + " ";
        this.binding.fightButton.setEnabled(false);
        this.binding.nameField.setText(name);
        this.binding.levelField.setText(level);
        this.binding.mapView.setText(printMap(game));

        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setMove();
        View xp = this.binding.xpBar;


    }

    private String printMap(Game game) {
        String ret = "";
        Tile[][] map = game.map;
        char[][] charMap = new char[map.length][map.length];

        for(int i = 0; i < map.length; ++i) {
            for(int j = 0; j < map.length; ++j) {
                charMap[i][j] = map[i][j].display();
                ret = ret + charMap[i][j] + " ";
                if (j == map.length - 1) {
                    ret = ret + "\n";
                }
            }
        }
        return ret;
    }

    private void setMove() {
        TextView mapView = this.binding.mapView;
        this.binding.upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.pc.move(game, "w");
                game.enemy.move(game.map);
                mapView.setText(printMap(game));
                if (game.checkAdjacent()) {
                    onCombat();
                }
            }
        });

        this.binding.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.pc.move(game, "a");
                game.enemy.move(game.map);
                mapView.setText(printMap(game));
                if (game.checkAdjacent()) {
                    onCombat();
                }
            }
        });
        this.binding.rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.pc.move(game, "d");
                game.enemy.move(game.map);
                mapView.setText(printMap(game));
                if (game.checkAdjacent()) {
                    onCombat();
                }
            }
        });
        this.binding.downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.pc.move(game, "s");
                game.enemy.move(game.map);
                mapView.setText(printMap(game));
                if (game.checkAdjacent()) {
                    onCombat();
                }
            }
        });
    }

    public void onCombat() {
        this.binding.upButton.setEnabled(false);
        this.binding.downButton.setEnabled(false);
        this.binding.leftButton.setEnabled(false);
        this.binding.rightButton.setEnabled(false);
        this.binding.fightButton.setEnabled(true);
        this.binding.combatView.setText("You have entered combat. Spam the combat button!");
        int countdown = 10000;
        new CountDownTimer(countdown, 1000) {
            int counter;
            public void onTick(long millisUntilFinished) {
                binding.combatView.setText("seconds remaining: " + millisUntilFinished / 1000);
                binding.fightButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        counter++;
                        Log.d("Dungeon Crawler", "counter:" + String.valueOf(counter));
                    }
                });
            }
            public void onFinish() {
                int pcPoints = 0;
                int enemyPoints = 0;
                for (Attribute a : game.pc.attributes) {pcPoints += a.value;}
                for (Attribute a : game.enemy.attributes) {enemyPoints +=a.value;}
                pcPoints *= counter;
                enemyPoints *= Math.random() * 20 * game.enemy.level;
                Log.d("Dungeon Crawler",  "pc: " +pcPoints);
                Log.d("Dungeon Crawler",  "enemy: " +enemyPoints);

                if (pcPoints > enemyPoints) {
                    binding.combatView.setText("You have defeated the enemy, but a new one has appeared!");
                    game.pc.experience += game.enemy.level * 10;
                    if (checkLevelUp()) { levelUp(); }

                }
                else
                    binding.combatView.setText("You died!");
                    System.exit(10);

            }
        }.start();
        spawnEnemy();

        setXpBar();
    }

    private void setXpBar() {
        this.binding.xpBar.setMax(game.pc.xpToLevel);
        this.binding.xpBar.setProgress(game.pc.experience);
    }

    private boolean checkLevelUp() {
        if (game.pc.experience >= game.pc.xpToLevel) {
            return true;
        } else return false;
    }

    private void levelUp() {
        game.pc.level++;
        game.pc.experience = 0;
        game.pc.setXpToLevel();
        setXpBar();
        for (Attribute a : game.pc.attributes) {
            a.value++;
            if (a.name.equals("hitpoints")) { a.value += game.pc.level * 4; }
        }
    }

    private void spawnEnemy() {
        game.enemy = new NPC(Race.values()[(int) Math.random()*7], Caste.values()[(int) Math.random()*6], true, depth);
    }

    @Override
    public View getRootView() { return this.binding.getRoot(); }
}
