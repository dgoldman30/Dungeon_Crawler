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
import android.widget.LinearLayout;
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

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExploreFragment extends Fragment implements IExploreFragment {

    FragmentExploreBinding binding;
    Listener listener;
    Game game;

    LinearLayout combatLayout;

    int depth = 1;

    public ExploreFragment(Listener listener, Game game) {
        this.listener = listener;
        this.game = game;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentExploreBinding.inflate(inflater);
        this.combatLayout = new LinearLayout(this.getContext());
        combatLayout.setOrientation(LinearLayout.VERTICAL);

        String name = game.pc.race.name() + " " + game.pc.caste.name();
        String level = "Level " + game.pc.level + " ";
        this.binding.fightButton.setEnabled(false);
        this.binding.nameField.setText(name);
        this.binding.levelField.setText(level);
        this.binding.mapView.setText(printMap(game));

        createLog();

        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setMove();
        String strLevel = "Level " + game.pc.level + ": ";
        binding.levelField.setText(strLevel);

    }

    private String printMap(Game game) {
        String ret = "";
        Tile[][] map = game.map;
        char[][] charMap = new char[map.length][map.length];

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                charMap[i][j] = map[i][j].display();
                ret = ret + charMap[i][j] + " ";
                if (j == map.length - 1) {
                    ret = ret + "\n";
                }
            }
        }
        return ret;
    }

    private String regen() {
        int regen = (int) (game.pc.WILL.value / 2) + 1;
        game.pc.HP.value += regen;
        updateHP();

        String log = " and regenerate " + regen + " HP.";
        return log;
    }

    private void setMove() {
        TextView mapView = this.binding.mapView;
        TextView log = new TextView(this.getRootView().getContext());

        this.binding.upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.pc.move(game, "w");
                game.enemy.move(game.map);
                log.setText("You moved up" + regen());
                clearLog();
                addToLog(log);
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
                log.setText("You moved left" + regen());
                clearLog();
                addToLog(log);
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
                log.setText("You moved right" + regen());
                clearLog();
                addToLog(log);
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
                log.setText("You moved down" + regen());
                clearLog();
                addToLog(log);
                mapView.setText(printMap(game));
                if (game.checkAdjacent()) {
                    onCombat();

                }
            }
        });
    }

    private void setXpProgress() {
        String xp = "" + game.pc.experience + " / " + game.pc.xpToLevel;
        String levelText = (String) binding.levelField.getText().subSequence(0, 9);
        String ret = levelText + xp;
        binding.levelField.setText(ret);
    }

    private void createLog() {
        binding.combatLog.addView(combatLayout);
    }

    private void addToLog(TextView view) {
        combatLayout.addView(view);
        binding.combatLog.post(new Runnable() {
            @Override
            public void run() {
                binding.combatLog.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    private void clearLog() {
        combatLayout.removeAllViews();
    }

    private void updateHP() {
        binding.hpLayout.setVisibility(LinearLayout.VISIBLE);
        binding.pcHP.setText("" + game.pc.HP.value);
        binding.pcHPBar.setMax(game.pc.maxHP);
        binding.pcHPBar.setProgress(game.pc.HP.value);

        binding.enemyHP.setVisibility(LinearLayout.VISIBLE);
        binding.enemyHPBar.setVisibility(LinearLayout.VISIBLE);
        binding.enemyHP.setText("" + game.enemy.HP.value);
        binding.enemyHPBar.setMax(game.enemy.maxHP);
        binding.enemyHPBar.setProgress(game.enemy.HP.value);
    }

    public void onCombat() {
        TextView enterCombat = new TextView(this.getRootView().getContext());
        enterCombat.setText("You have entered combat.");
        combatLayout.addView(enterCombat);

        updateHP();

        this.binding.upButton.setEnabled(false);
        this.binding.downButton.setEnabled(false);
        this.binding.leftButton.setEnabled(false);
        this.binding.rightButton.setEnabled(false);
        this.binding.fightButton.setEnabled(true);


        this.binding.fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double pcToHit = (game.pc.DEX.value + (game.pc.INT.value / 2)) * Math.random() * 10;
                double enemyToHit = (game.enemy.DEX.value + (game.enemy.INT.value / 2)) * Math.random() * 10;

                TextView combatRound = new TextView(getRootView().getContext());
                String combatText = "";


                if (pcToHit >= game.enemy.DV.value && (game.pc.STR.value * Math.random()) >= game.enemy.AV.value) {
                    combatText += "You hit the enemy for " + game.pc.weapon.strike(game.pc, game.enemy) + " damage.";
                } else combatText += "You missed the enemy.";
                if (enemyToHit >= game.pc.DV.value && (game.enemy.STR.value * Math.random()) >= game.pc.AV.value) {
                    combatText += "\n" + "The enemy hit you for " + game.enemy.weapon.strike(game.enemy, game.pc) + " damage.";
                } else combatText += "\n The enemy missed you.";

                updateHP();

                if (game.enemy.HP.value <= 0) {
                    Log.d("Experience gained", "" + (game.enemy.level * 10));
                    game.pc.experience += (game.enemy.level) * 10;
                    replaceEnemy();

                    binding.fightButton.setEnabled(false);
                    binding.upButton.setEnabled(true);
                    binding.downButton.setEnabled(true);
                    binding.leftButton.setEnabled(true);
                    binding.rightButton.setEnabled(true);
                    combatText += "\n You killed the enemy and recieved " + (game.enemy.level * 10) + "experience points.";

                    setXpProgress();
                    binding.enemyHP.setVisibility(LinearLayout.INVISIBLE);
                    binding.enemyHPBar.setVisibility(LinearLayout.INVISIBLE);

                }

                if (game.pc.HP.value <= 0) {
                    combatText += "\n You were killed by the " + game.enemy.race.name() + " " + game.enemy.caste.name();
                    youDied();
                }

                combatRound.setText(combatText);
                addToLog(combatRound);
            }
        });
    }

    private void youDied() {
        binding.exploreConstraint.setVisibility(Button.GONE);
        binding.restartButton.setVisibility(Button.VISIBLE);

        binding.restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRestart();
            }
        });
    }

    private void replaceEnemy() {
        for (int i = 0; i < game.map.length; i++) {
            for (int j = 0; j < game.map.length; j++) {
                if (game.map[i][j].occupant instanceof NPC) { game.map[i][j].occupant = null; } } }
        spawnEnemy();
        binding.mapView.setText(printMap(game));
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
        for (Attribute a : game.pc.attributes) {
            a.value++;
            if (a.name.equals("hitpoints")) { a.value += game.pc.level * 4; }
        }
    }

    private void spawnEnemy() {
        game.enemy = new NPC(Race.values()[(int) Math.random()*7], Caste.values()[(int) Math.random()*6], true, depth);
        game.enemy.occupy(game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)]);
        game.enemy.setTarget(game.pc);
    }

    @Override
    public View getRootView() { return this.binding.getRoot(); }
}
