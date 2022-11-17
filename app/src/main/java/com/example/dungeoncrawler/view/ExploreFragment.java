package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.ControllerActivity;
import com.example.dungeoncrawler.databinding.FragmentExploreBinding;
import com.example.dungeoncrawler.model.Attribute;
import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.Character;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.NPC;
import com.example.dungeoncrawler.model.Race;
import com.example.dungeoncrawler.model.Tile;

public class ExploreFragment extends Fragment implements IExploreFragment {

    public FragmentExploreBinding binding;
    Listener listener;
    Game game;


    LinearLayout combatLayout;

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
        this.binding.combatButtons.setVisibility(View.GONE);
        this.binding.nameField.setText(name);
        this.binding.levelField.setText(level);
        this.binding.mapView.setText(listener.printMap(game));
        createLog();

        listener.setBinding(this.binding);

        return this.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setMove();
        String strLevel = "Level " + game.pc.level + ": ";
        binding.levelField.setText(strLevel);

        // set log to scroll to bottom
        binding.combatLog.post(new Runnable() {
            @Override
            public void run() {
                binding.combatLog.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    private void menuButtons() {
        this.binding.characterButton.setVisibility(View.VISIBLE);
        this.binding.characterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCharSheet();
            }
        });
    }


    public String regen() {
        int regen = (int) (game.pc.WILL.value / 2) + 1;
        game.pc.HP.value += regen;
        listener.updateHP();

        String log = " and regenerate " + regen + " HP.";
        return log;
    }

    private void setMove() {
        TextView log = new TextView(this.getRootView().getContext());

        this.binding.upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMove(game, "w");
                log.setText("You moved up" + regen());
                clearLog();
                addToLog(log);

                if (game.checkAdjacent()) {
                    onCombat();
                }
            }
        });

        this.binding.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMove(game, "a");
                log.setText("You moved left" + regen());
                clearLog();
                addToLog(log);

                if (game.checkAdjacent()) {
                    onCombat();
                }
            }
        });
        this.binding.rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMove(game, "d");
                log.setText("You moved right" + regen());
                clearLog();
                addToLog(log);

                if (game.checkAdjacent()) {
                    onCombat();
                }
            }
        });
        this.binding.downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMove(game, "s");
                log.setText("You moved down" + regen());
                clearLog();
                addToLog(log);

                if (game.checkAdjacent()) {
                    onCombat();
                }
            }
        });
    }

    private void setXpProgress() {
        String xp = "" + game.pc.experience + " / " + game.pc.xpToLevel();
        String levelText = (String) binding.levelField.getText().subSequence(0, 9);
        String ret = levelText + xp;
        binding.levelField.setText(ret);
    }

    private void createLog() {
        binding.combatLog.addView(combatLayout);
    }

    private void addToLog(TextView view) {
        combatLayout.addView(view);
    }

    private void clearLog() {
        combatLayout.removeAllViews();
    }

    public void onCombat() {
        TextView enterCombat = new TextView(this.getRootView().getContext());
        enterCombat.setText("You have entered combat.");
        combatLayout.addView(enterCombat);

        listener.updateHP();

        this.binding.moveButtons.setEnabled(false);
        this.binding.combatButtons.setVisibility(View.VISIBLE);

        this.binding.fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView combatRound = new TextView(getRootView().getContext());
                String combatText = listener.onCombat(game, "f");

                if (game.enemy.HP.value <= 0) {
                    listener.onEnemyDefeated(game);
                    Log.d("Experience gained", "" + (game.enemy.level * 10));
                    setXpProgress();
                    if (checkLevelUp()) {
                        combatText += "\n" + listener.performLevelUp();
                    }
                }

                if (game.pc.HP.value <= 0) {
                    combatText += "\n You were killed by the " + game.enemy.race.name() + " " + game.enemy.caste.name();
                    youDied();
                }
                // push round to log
                combatRound.setText(combatText);
                addToLog(combatRound);
            }
        });
        this.binding.blockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView combatRound = new TextView(getRootView().getContext());
                String combatText = listener.onCombat(game, "b");

                if (game.pc.HP.value <= 0) {
                    combatText += "\n You were killed by the " + game.enemy.race.name() + " " + game.enemy.caste.name();
                    youDied();
                }
                // push round to log
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

    private boolean checkLevelUp() {
        if (game.pc.experience >= game.pc.xpToLevel()) {
            game.pc.readyForLevel = true;
            return true;
        } else return false;
    }

    public View getRootView() { return this.binding.getRoot(); }
}
