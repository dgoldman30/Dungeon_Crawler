package com;

import com.example.dungeoncrawler.model.*;

import org.junit.Assert;
import org.junit.Test;

public class PotionTest {
    Game game = new Game(10);
    int[] att = {0,0,0,0,0};
    Player testPlayer = new Player(Race.NYMPH, Caste.APPRENTICE, att);

    @Test
    public void drinkHealthPotion() {
        testPlayer.attributes[4].value = 0;
        Potion.Potions.HEALTH.po.drink(testPlayer);
        Assert.assertEquals(testPlayer.attributes[4].value, testPlayer.maxHP);
    }

    @Test
    public void drinkStrengthPotion() {
        Potion.Potions.STR.po.drink(testPlayer);
        Assert.assertEquals(testPlayer.STR.value, Race.NYMPH.attributeAdjustments[0]+1);
    }

    @Test
    public void drinkDexterityPotion() {
        Potion.Potions.DEX.po.drink(testPlayer);
        Assert.assertEquals(testPlayer.DEX.value, Race.NYMPH.attributeAdjustments[1]+1);
    }

    @Test
    public void drinkIntelligencePotion() {
        Potion.Potions.INT.po.drink(testPlayer);
        Assert.assertEquals(testPlayer.INT.value, Race.NYMPH.attributeAdjustments[2]+1);
    }

    @Test
    public void drinkWillpowerPotion() {
        Potion.Potions.WILL.po.drink(testPlayer);
        Assert.assertEquals(testPlayer.WILL.value, Race.NYMPH.attributeAdjustments[3]+1);
    }

    @Test
    public void drinkPotion() {
        testPlayer.potion = Potion.Potions.HEALTH.po;
        testPlayer.drinkPotion();
        Assert.assertEquals(testPlayer.potion, null);
    }
}
