package com.tests;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.dungeoncrawler.model.*;

import java.util.Arrays;


public class CharCreationTest {
    Game game = new Game(10);

    @Test
    public void setAttributes() {
        Player pc = new Player(Race.HUMAN, Caste.GLADIATOR, new int[] {0,0,0,0});
        for (int i = 0; i < 5; i++) {
            assertEquals(pc.attributes[i].value, Race.HUMAN.attributeAdjustments[i]);
        }
    }

    @Test
    public void equipWeapon() {
        Player pc = new Player(Race.NYMPH, Caste.APPRENTICE, new int[] {0, 0, 0, 0});
        assertEquals(pc.weapon, pc.caste.startingItems.get(0));
    }

}
