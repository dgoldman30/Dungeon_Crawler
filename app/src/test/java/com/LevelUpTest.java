package com;


import com.example.dungeoncrawler.model.*;
import com.example.dungeoncrawler.model.Character;

import org.junit.Assert;
import org.junit.Test;

public class LevelUpTest {

    Game game = new Game(10);
    int[] att = {0,0,0};
    Player testPlayer = new Player(Race.NYMPH, Caste.APPRENTICE, att);

    NPC testEnemy = new NPC(Race.NYMPH, Caste.APPRENTICE, true, 2);


    @Test
    public void levelTest() {
        testPlayer.levelUp();
        Assert.assertEquals(testPlayer.level, 2);
    }

    @Test
    public void attributeTest() {
        testPlayer.levelUp();
        for (int i = 0; i < 4; i++) {
            Assert.assertEquals(testPlayer.attributes[i].value, testEnemy.attributes[i].value);
        }
    }

    @Test
    public void skillTest() {
        testPlayer.levelUp();
        for (Skill s : testPlayer.skills.values()) {
            Assert.assertEquals(testPlayer.skills.get(s.name), testEnemy.skills.get(s.name));
        }
    }
}
