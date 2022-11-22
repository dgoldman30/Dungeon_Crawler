package com.example.dungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dungeoncrawler.databinding.FragmentExploreBinding;
import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.Character;
import com.example.dungeoncrawler.model.Floor;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.NPC;
import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Race;
import com.example.dungeoncrawler.model.Tile;
import com.example.dungeoncrawler.view.CharCreationFragment;
import com.example.dungeoncrawler.view.CharacterSheetFragment;
import com.example.dungeoncrawler.view.ExploreFragment;
import com.example.dungeoncrawler.view.ICharCreationView;
import com.example.dungeoncrawler.view.ICharacterSheetFragment;
import com.example.dungeoncrawler.view.IExploreFragment;
import com.example.dungeoncrawler.view.IMainView;
import com.example.dungeoncrawler.view.MainView;

public class ControllerActivity extends AppCompatActivity implements ICharCreationView.Listener, IExploreFragment.Listener, ICharacterSheetFragment.Listener {

    IMainView mainView;
    Game game;

    FragmentExploreBinding binding;
    ExploreFragment exploreFragment;

    CharacterSheetFragment charSheetFragment;


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

    // Char creation
    @Override
    public void onConfirmCharacter(String race, String caste, int[] att) {
        Race pcRace = Race.HUMAN;
        Caste pcCaste = Caste.GLADIATOR;
        for (Race r : Race.values()) { if (r.name().equals(race.toUpperCase())) pcRace = r; }
        for (Caste c : Caste.values()) { if (c.name().equals(caste.toUpperCase()))  pcCaste = c;  }

        this.game = new Game(10);
        game.pc = new Player(pcRace, pcCaste, att);
        game.pc.occupy(game.map[0][0]);


        game.enemy = new NPC(Race.HUMAN, Caste.GLADIATOR, true, 1);
        game.enemy.occupy(game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)]);
        game.enemy.setTarget(game.pc);

        game.gameState = Game.GameStates.EXPLORE;

        exploreFragment = new ExploreFragment(this, game);
        this.mainView.displayFragment(exploreFragment, false, "explore");
    }

    // Explore Fragment
    @Override
    public void onInventory() {

    }

    public String onCombat(Game game, String input) {
        String log = "";

        binding.enemyHP.setVisibility(LinearLayout.VISIBLE);
        binding.enemyHPBar.setVisibility(LinearLayout.VISIBLE);

        double pcToHit = (game.pc.DEX.value + (game.pc.INT.value / 2)) * Math.random() * 10;
        double enemyToHit = (game.enemy.DEX.value + (game.enemy.INT.value / 2)) * Math.random() * 10;
        double pcBlockBonus = Character.skills.get("Shield").value + game.pc.DEX.value + game.pc.WILL.value;
            switch (input) {
                case "f":
                    // if PC beats enemy's dodge and armor, PC strikes enemy
                    if (pcToHit >= game.enemy.DV.value && (game.pc.STR.value * Math.random() * 10) >= game.enemy.AV.value) {
                        log += "You hit the enemy for " + game.pc.weapon.strike(game.pc, game.enemy) + " damage.";

                    } else log += "You missed the enemy.";

                    // if enemy beats PC's dodge and armor, enemy strikes PC
                    if (enemyToHit >= game.pc.DV.value && (game.enemy.STR.value * Math.random() * 10) >= game.pc.AV.value) {
                        log += "\n" + "The enemy hit you for " + game.enemy.weapon.strike(game.enemy, game.pc) + " damage.";
                    } else log += "\n The enemy missed you.";
                    break;
                case "b":
                    // if block is greater than to hit, deflect the whole attack.
                    if (pcBlockBonus > enemyToHit) {
                        log += "You deflected the " + game.enemy.race.name() + " " + game.enemy.caste.name() + "'s attack!";
                        if (pcToHit >= game.enemy.DV.value && (game.pc.STR.value * Math.random()) >= game.enemy.AV.value) {
                            log += "\nYou counter attack for " + game.pc.weapon.strike(game.pc, game.enemy) + " damage.";
                        }
                    } else if ((pcBlockBonus + game.pc.DV.value) <= enemyToHit && (game.enemy.STR.value * Math.random()) >= game.pc.AV.value) {
                        log += "\nThe enemy hit you for " + game.enemy.weapon.strike(game.enemy, game.pc) + " damage.";
                    } else log += "\n The enemy missed you.";
                    break;
            }
        updateHP();

        if (game.enemy.HP.value <= 0) {
            log += onEnemyDefeated(game);
            if (checkLevelUp()) { performLevelUp(); }
            setXpProgress();
            binding.moveButtons.setVisibility(View.VISIBLE);
        }

        updateHP();

        if (game.pc.HP.value <= 0) {
            log += "\n You were killed by the " + game.enemy.race.name() + " " + game.enemy.caste.name();
            youDied();
        }
        return log;
    }

    @Override
    public String printMap(Game game) {
        String ret = "";
        Tile[][] map = game.map;
        char[][] charMap = new char[map.length][map.length];
        this.game = game;

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

    private boolean checkLevelUp() {
        if (game.pc.experience >= game.pc.nextLevelXp) {
            game.pc.readyForLevel = true;
            return true;
        } else return false;
    }

    private void setXpProgress() {
        String xp = "" + game.pc.experience + " / " + game.pc.nextLevelXp;
        String levelText = (String) binding.levelField.getText().subSequence(0, 9);
        String ret = levelText + xp;
        binding.levelField.setText(ret);
    }

    private void youDied() {
        binding.exploreConstraint.setVisibility(View.GONE);
        binding.deathConstraint.setVisibility(View.VISIBLE);

        String deathText = "You have been killed by a level " + game.enemy.name + "." + "\n"
                + "You reached level " + game.pc.level + " and explored down to depth " + game.depth + ".";
        binding.deathText.setText(deathText);
        
        binding.restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRestart();
            }
        });
    }

    @Override
    public String onEnemyDefeated(Game game) {
        String log = "\n You killed the enemy and recieved " + (game.enemy.level * 10) + "experience points.";
        game.pc.experience += game.enemy.level * 10;
        game.enemiesCleared++;

        binding.moveButtons.setEnabled(true);
        binding.combatButtons.setVisibility(View.INVISIBLE);

        binding.enemyHP.setVisibility(LinearLayout.INVISIBLE);
        binding.enemyHPBar.setVisibility(LinearLayout.INVISIBLE);

        log += replaceEnemy();
        return log;
    }

    @Override
    public void updateHP() {
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

    // private method to replace enemy on game map
    private String replaceEnemy() {
        String log = "";
        //not sure why replaceEnemy doesn't work
        /*for (int i = 0; i < game.map.length; i++) {
            for (int j = 0; j < game.map.length; j++) {
                if (game.map[i][j].occupant instanceof NPC) { game.map[i][j].occupant = null; } } }*/
        //they're refering to the same tile so i should be able to do this
        game.enemy.remove(game.enemy.location);
        if (game.enemiesCleared >= /*game.floor.enemies.length*/ 1) {
            log += levelCleared();
        } else log += "A new " + spawnEnemy() + " has spawned.";
        updateHP();
        binding.mapView.setText(printMap(game));
        return log;
    }

    // private method to create a new enemy in a random space on the map
    private String spawnEnemy() {
//        game.enemy = game.floor.enemies[game.enemiesCleared-1];
        // occupy a random square and set pc as target
        game.enemy = new NPC( Race.values()[(int) (Math.random() * Race.values().length - 1)], Caste.values()[(int) (Math.random() * Caste.values().length - 1)], true, game.enemy.level);
        game.enemy.occupy(game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)]);
        game.enemy.setTarget(game.pc);
        return game.enemy.name;
    }

    private String levelCleared() {
        String log = "You've cleared the level. Find the stairs to advance to the next map.";
        game.gameState = Game.GameStates.CLEARED;
        game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)].toStairs();
        binding.mapView.setText(printMap(game));
        return log;
    }


    @Override
    public void setBinding(FragmentExploreBinding binding) {
        this.binding = binding;
    }

    @Override
    public void performLevelUp() {
        String log = "";
        log += game.pc.levelUp();

        binding.exploreConstraint.setVisibility(View.INVISIBLE);
        binding.levelUpConstraint.setVisibility(View.VISIBLE);
        binding.levelUpText.setText(log);

        binding.levelUpOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.exploreConstraint.setVisibility(View.VISIBLE);
                binding.levelUpConstraint.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onMove(String input) {

        switch (game.gameState) {
            case EXPLORE:
                game.pc.move(game, input);
                game.enemy.move(game.map);
                binding.mapView.setText(printMap(game));
                break;
            case CLEARED:
                game.pc.move(game, input);
                binding.mapView.setText(printMap(game));
                if (game.pc.location.stairs) {
                    binding.stairsButton.setVisibility(View.VISIBLE);
                    binding.stairsButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            game.depth++;
                            // make a new, wider map and place the pc in the same xy location
                            game.floor = new Floor(game.depth, game.mapSize);
                            game.pc.occupy(game.map[game.pc.location.x][game.pc.location.y]);

                            game.gameState = Game.GameStates.EXPLORE;
                            binding.stairsButton.setVisibility(View.GONE);

                            // push the depth progress to the log
                            TextView log = new TextView(exploreFragment.getContext());
                            String logText = "\nYou've taken the stairs down to depth " + game.depth;
                            log.setText(logText);
                            exploreFragment.addToLog(log);
                        }
                    });
                }
                break;
        }
    }
    @Override
    public void onCharSheet() {
        charSheetFragment = new CharacterSheetFragment(this, game);
        this.mainView.displayFragment(charSheetFragment, true, "character sheet");
    }

    @Override
    public void onEquipment() {

    }

    @Override
    public void onRestart() {
        super.onRestart();
        CharCreationFragment charCreationFragment = new CharCreationFragment(this);
        mainView.displayFragment(charCreationFragment, false, "char creation");
    }


    // Character Sheet
    @Override
    public void onLevelUp() {

    }

    @Override
    public void onClose() {
        mainView.displayFragment(exploreFragment, false, "explore");
    }

    @Override
    public void onEquip() {

    }
}