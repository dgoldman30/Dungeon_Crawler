package com.example.dungeoncrawler.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeoncrawler.R;
import com.example.dungeoncrawler.databinding.FragmentExploreBinding;
import com.example.dungeoncrawler.model.Game;

public class ExploreFragment extends Fragment implements IExploreFragment {

    public FragmentExploreBinding binding;
    Listener listener;
    Game game;
    private final String CURGAME = "cur_Game";


    LinearLayout combatLayout;

    public ExploreFragment(Listener listener, Game game) {
        this.listener = listener;
        this.game = game;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentExploreBinding.inflate(inflater);
        return this.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            this.game = (Game) savedInstanceState.getSerializable(CURGAME);
        }
        if (game.enemy != null) { game.gameState = Game.GameStates.EXPLORE; }
        populate();
        menuButtons();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CURGAME, this.game);
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle saveInstanceState){
        super.onViewStateRestored(saveInstanceState);
        if (saveInstanceState != null) {
            this.game = (Game) saveInstanceState.getSerializable(CURGAME);
        }
    }


    public void populate() {
        String name = game.pc.race.name() + " " + game.pc.caste.name();
        String level = "Level " + game.pc.level + " ";
        this.binding.combatButtons.setVisibility(View.GONE);
        this.binding.nameField.setText(name);
        this.binding.levelField.setText(level);

        setMap();
        createLog();

        String strLevel = "Level " + game.pc.level + ": ";
        binding.levelField.setText(strLevel);

        String location = game.gameState + " depth " + game.depth;
        binding.locationField.setText(location);

        listener.setBinding(this.binding);
        listener.printMap(game);
    }

    private void setMap() {
        for (int i = 0; i < 10; i++) {
            TableRow row = new TableRow(this.getRootView().getContext());
            for (int j = 0; j < 10; j++) {
                ImageView cell = new ImageView(this.getRootView().getContext());
                row.addView(cell);
            }
            binding.mapLayout.addView(row);
        }
    }



    private void menuButtons() {
        this.binding.characterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCharSheet();
            }
        });
        this.binding.inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { listener.onInventory(); }
        });
    }


    public String regen() {
        String log = "";
        int regen = (int) (game.pc.WILL.value / 2) + 1;
        if (game.pc.HP.value < game.pc.maxHP) {
            game.pc.HP.value += regen;
            log += " and regenerate " + regen + " HP.";
        }
        listener.updateHP();
        return log;
    }

    public void setMove(String input) {
        TextView log = new TextView(this.getRootView().getContext());
        switch (input) {
            case "w": {
                log.setText("You moved up" + regen());
                clearLog();
                addToLog(log);
                break;
            }
            case "s": {
                log.setText("You moved down" + regen());
                clearLog();
                addToLog(log);
                break;
            }
            case "a": {
                log.setText("You moved left" + regen());
                clearLog();
                addToLog(log);
                break;
            }
            case "d": {
                log.setText("You moved right" + regen());
                clearLog();
                addToLog(log);
                break;
            }

        }
        if (game.gameState == Game.GameStates.EXPLORE) {
            if (game.checkAdjacent()) {
                onCombat();
                //this will disable the map when combat occurs
                ViewGroup layout = getView().findViewById(R.id.mapLayout);
                int count = layout.getChildCount();
                for (int i = 0; i < count; i++) {
                    // Get the view at the current index
                    TableRow view = (TableRow) layout.getChildAt(i);
                    for (int j = 0; j < view.getChildCount(); j++) {
                        view.getChildAt(j).setEnabled(false);
                    }
                }
            }
        }

    }

    private void createLog() {
        this.combatLayout = new LinearLayout(this.getContext());
        combatLayout.setOrientation(LinearLayout.VERTICAL);
        binding.combatLog.addView(combatLayout);

    }

    public void addToLog(TextView view) {
        combatLayout.addView(view);
        binding.combatLog.scrollToDescendant(view);
    }

    private void clearLog() {
        combatLayout.removeAllViews();
    }

    public void onCombat() {
        binding.combatLog.scrollTo(0, binding.combatLog.getBottom());
        TextView enterCombat = new TextView(this.getRootView().getContext());
        enterCombat.setText("You have entered combat.");
        combatLayout.addView(enterCombat);

        listener.updateHP();

        this.binding.combatButtons.setVisibility(View.VISIBLE);
        this.binding.fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView combatRound = new TextView(getRootView().getContext());
                String combatText = listener.onCombat(game, "f");

                // push round to log
                combatRound.setText(combatText);
                addToLog(combatRound);
            }
        });
        this.binding.blockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView combatRound = new TextView(getRootView().getContext());

                // call to listener to run on combat and update combatText
                String combatText = listener.onCombat(game, "b");

                // push round to log
                combatRound.setText(combatText);
                addToLog(combatRound);
            }
        });

    }

    private View getRootView() { return this.binding.getRoot(); }
}
