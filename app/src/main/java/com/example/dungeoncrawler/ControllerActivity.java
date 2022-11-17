package com.example.dungeoncrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dungeoncrawler.databinding.FragmentExploreBinding;
import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.Character;
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

    Game.GameStates gameState = Game.GameStates.START;
    IMainView mainView;
    Game game;

    FragmentExploreBinding binding;


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

        gameState = Game.GameStates.EXPLORE;

        ExploreFragment exploreFragment = new ExploreFragment(this, game);
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
        double pcBlockBonus = (Character.skills.get("Shield").value + game.pc.DEX.value + game.pc.WILL.value) / 3;

        switch (input) {
            case "f":
                // if PC beats enemy's dodge and armor, PC strikes enemy
                if (pcToHit >= game.enemy.DV.value && (game.pc.STR.value * Math.random()) >= game.enemy.AV.value) {
                    log += "You hit the enemy for " + game.pc.weapon.strike(game.pc, game.enemy) + " damage.";
                } else log += "You missed the enemy.";

                // if enemy beats PC's dodge and armor, enemy strikes PC
                if (enemyToHit >= game.pc.DV.value && (game.enemy.STR.value * Math.random()) >= game.pc.AV.value) {
                    log += "\n" + "The enemy hit you for " + game.enemy.weapon.strike(game.enemy, game.pc) + " damage.";
                } else log += "\n The enemy missed you.";
                break;
            case "b":
                // if block is greater than to hit, deflect the whole attack.
                if (pcBlockBonus > enemyToHit) { log += "You deflected the " + game.enemy.race.name() + " " + game.enemy.caste.name() + "'s attack!";
                } else if ((pcBlockBonus + game.pc.DV.value) <= enemyToHit && (game.enemy.STR.value * Math.random()) >= game.pc.AV.value) {
                    log += "\n" + "The enemy hit you for " + game.enemy.weapon.strike(game.enemy, game.pc) + " damage.";
                } else log += "\n The enemy missed you.";
                break;
        }
        updateHP();
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

    @Override
    public String onEnemyDefeated(Game game) {
        String log = "\n You killed the enemy and recieved " + (game.enemy.level * 10) + "experience points.";
        game.pc.experience += game.enemy.level * 10;

        binding.moveButtons.setEnabled(true);
        binding.combatButtons.setVisibility(View.INVISIBLE);

        binding.enemyHP.setVisibility(LinearLayout.INVISIBLE);
        binding.enemyHPBar.setVisibility(LinearLayout.INVISIBLE);
        replaceEnemy();

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
    private void replaceEnemy() {
        for (int i = 0; i < game.map.length; i++) {
            for (int j = 0; j < game.map.length; j++) {
                if (game.map[i][j].occupant instanceof NPC) { game.map[i][j].occupant = null; } } }
        spawnEnemy();
        binding.mapView.setText(printMap(game));
    }

    // private method to create a new enemy in a random space on the map
    private void spawnEnemy() {
        game.enemy = new NPC(Race.values()[(int) Math.random()*7], Caste.values()[(int) Math.random()*6], true, game.depth);
        game.enemy.occupy(game.map[(int) (Math.random() * game.map.length)][(int) (Math.random() * game.map.length)]);
        game.enemy.setTarget(game.pc);
    }


    @Override
    public void setBinding(FragmentExploreBinding binding) {
        this.binding = binding;
    }


    @Override
    public String performLevelUp() {
        String log = "";
        game.pc.levelUp();
        log += "You increased your level to " + game.pc.level;
        return log;
    }

    @Override
    public Game onMove(Game game, String input) {
        game.pc.move(game, input);
        game.enemy.move(game.map);
        binding.mapView.setText(printMap(game));
        return game;
    }
    @Override
    public void onCharSheet() {
        CharacterSheetFragment charSheetFrag = new CharacterSheetFragment(this, game);
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

    }

    @Override
    public void onEquip() {

    }
}