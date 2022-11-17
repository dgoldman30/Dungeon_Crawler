package com;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Race;


public class CharCreationTest {
    Game game = new Game(10);

    @Test
    public void setAttributes() {
        Player pc = new Player(Race.HUMAN, Caste.GLADIATOR, new int[] {0,0,0,0});
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(pc.attributes[i].value, Race.HUMAN.attributeAdjustments[i]);
        }
    }

    @Test
    public void equipWeapon() {
        Player pc = new Player(Race.NYMPH, Caste.APPRENTICE, new int[] {0, 0, 0, 0});
        assertEquals(pc.weapon, pc.caste.startingItems.get(0));
    }

}
