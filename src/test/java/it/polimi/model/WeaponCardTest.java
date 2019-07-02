package it.polimi.model;

import it.polimi.model.Weapon.LockRifle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WeaponCardTest {

    WeaponCard weaponCard;


    @Before

    public void setUp(){


        weaponCard=new LockRifle();

    }

    @Test

    public void getNameWeaponCardTest(){

        assertEquals("LOCK RIFLE",weaponCard.getNameWeaponCard());
    }

    @Test

    public void getRechargeCostTest(){

        assertTrue(weaponCard.getRechargeCost().get(0)==EnumColorCardAndAmmo.BLU&& weaponCard.getRechargeCost().get(1)==EnumColorCardAndAmmo.BLU);


    }

    @Test

    public void getColorWeaponTest(){

        assertEquals(EnumColorCardAndAmmo.BLU,weaponCard.getColorWeaponCard());
    }

    @Test

    public void getWeaponEffectTest(){

        assertTrue(weaponCard.getWeaponEffects().get(0)==WeaponsEffect.BaseEffect&&
                weaponCard.getWeaponEffects().get(1)==WeaponsEffect.SecondLockEffect);
    }

    @Test

    public void getDescriptionTest(){

        assertEquals("Basic Effect: Deal 2 damage and 1 mark to 1 target you can see.\n" +
                "with Second Lock: Deal 1 mark to a different target you can see.",weaponCard.getDescription());
    }

    @Test

    public void isOptionalTest(){

        assertTrue(weaponCard.isOptional());
    }

    @Test

    public void setOptionalTest(){

        weaponCard.setOptional(false);
        assertFalse(weaponCard.isOptional());
    }

    @Test

    public void setRechargeCostTest(){

        ArrayList<EnumColorCardAndAmmo> rechargeCost=new ArrayList<>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        weaponCard.setRechargeCost(rechargeCost);
        assertEquals(EnumColorCardAndAmmo.BLU,weaponCard.getRechargeCost().get(0));
    }

    @Test

    public void setWeaponEffectTest(){

        ArrayList<WeaponsEffect> effect=new ArrayList<>();
        effect.add(WeaponsEffect.BaseMode);
        weaponCard.setWeaponEffects(effect);
        assertEquals(WeaponsEffect.BaseMode,weaponCard.getWeaponEffects().get(0));
    }

    @Test

    public void setChargeTest(){

        weaponCard.setCharge(false);
        assertFalse(weaponCard.isCharge());
    }

    @Test

    public void setDescriptionTest(){

        weaponCard.setDescription("Test");
        assertEquals("Test",weaponCard.getDescription());
    }

}
