package com;
import org.junit.Assert;
import org.junit.Test;

import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.NPC;
import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Race;
import com.example.dungeoncrawler.model.Tile;

public class CombatTest {
    Game game = new Game(10);


    @Test
    public void strike() {
        game.pc = new Player(Race.NYMPH, Caste.URCHIN, new int[]{2, 0, 0, 0});
        game.enemy = new NPC(Race.HUMAN, Caste.APPRENTICE, true, 1);
        double damage = game.pc.weapon.strike(game.pc, game.enemy);
        Assert.assertEquals((int) damage, game.enemy.maxHP-game.enemy.HP.value);
    }

    @Test
    public void occupy() {
        game.pc = new Player(Race.NYMPH, Caste.URCHIN, new int[]{2, 0, 0, 0});
        Tile tile = game.map[5][5];

        game.pc.occupy(tile);
        Assert.assertEquals(game.pc.location, tile);
    }

}
