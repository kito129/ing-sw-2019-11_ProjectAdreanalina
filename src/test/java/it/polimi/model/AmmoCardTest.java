package it.polimi.model;

import it.polimi.model.PowerUp.Teleporter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AmmoCardTest {

    AmmoCard ammoCard;


    @Before

    public void setUp(){

        ammoCard=new AmmoCard(1,2,0,true);
    }

    @Test

    public void getAmmoTest() {

        assertEquals(EnumColorCardAndAmmo.RED,ammoCard.getAmmo().get(0));
        assertEquals(EnumColorCardAndAmmo.YELLOW,ammoCard.getAmmo().get(1));
        assertEquals(EnumColorCardAndAmmo.YELLOW,ammoCard.getAmmo().get(2));
        assertEquals(3, ammoCard.getAmmo().size());

    }

    @Test

    public void getSetPowerUpCardTest(){

        Teleporter teleporter=new Teleporter(EnumColorCardAndAmmo.BLU);
        assertNull(ammoCard.getPowerUpCard());
        ammoCard.setPowerUpCard(teleporter);
        assertEquals(teleporter,ammoCard.getPowerUpCard());
    }

    @Test

    public void hasPowerUpTest(){

        assertTrue(ammoCard.hasPowerUpCard());
    }
}
