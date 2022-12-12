package com;

import com.example.dungeoncrawler.model.Armor;
import com.example.dungeoncrawler.model.Caste;
import com.example.dungeoncrawler.model.Game;
import com.example.dungeoncrawler.model.Player;
import com.example.dungeoncrawler.model.Potion;
import com.example.dungeoncrawler.model.Race;
import com.example.dungeoncrawler.model.Weapon;

import org.junit.Assert;
import org.junit.Test;

public class EquipmentTest {

    Game game = new Game(10);
    int[] att = {0, 0, 0, 0, 0};
    Player testPlayer = new Player(Race.NYMPH, Caste.APPRENTICE, att);


    @Test
    public void equipWeapon() {
        testPlayer.inventory.add(Weapon.Weapons.SABRE.wn);
        testPlayer.equip(Weapon.Weapons.SABRE.wn);
        Assert.assertEquals(testPlayer.inventory.get(0), Weapon.Weapons.CLUB.wn);
        Assert.assertEquals(testPlayer.weapon, Weapon.Weapons.SABRE.wn);
    }

    @Test
    public void equipArmor() {
        testPlayer.inventory.add(Armor.Armors.CHAIN.armor);
        testPlayer.equip(Armor.Armors.CHAIN.armor);
        Assert.assertEquals(testPlayer.inventory.get(0), Armor.Armors.ROBES.armor);
        Assert.assertEquals(testPlayer.body, Armor.Armors.CHAIN.armor);
    }

    @Test
    public void equipPotion() {
        testPlayer.inventory.add(Potion.Potions.STR.po);
        testPlayer.equip(Potion.Potions.STR.po);
        Assert.assertEquals(testPlayer.inventory.get(0), Potion.Potions.INT.po);
        Assert.assertEquals(testPlayer.potion, Potion.Potions.STR.po);
    }
}
