package it.polimi.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NormalSquareTest {

    NormalSquare normalSquare;
    AmmoCard ammoCard;

    @Before

    public void setUp(){

        normalSquare= new NormalSquare(1,2,EnumColorSquare.BLU);
        ammoCard=new AmmoCard(1,2,1,false);

    }


    @Test

    public void containsAmmoCardTest(){

        assertFalse(normalSquare.containAmmoCard());
        normalSquare.setAmmoCard(ammoCard);
        assertTrue(normalSquare.containAmmoCard());
    }

    @Test

    public void getSetAmmoCardTest(){

        assertNull(normalSquare.getAmmoCard());
        normalSquare.setAmmoCard(ammoCard);
        assertEquals(EnumColorCardAndAmmo.RED,normalSquare.getAmmoCard().getAmmo().get(0));
        assertEquals(EnumColorCardAndAmmo.YELLOW,normalSquare.getAmmoCard().getAmmo().get(1));
        assertEquals(EnumColorCardAndAmmo.YELLOW,normalSquare.getAmmoCard().getAmmo().get(2));
        assertEquals(EnumColorCardAndAmmo.BLU,normalSquare.getAmmoCard().getAmmo().get(3));
        assertFalse(normalSquare.getAmmoCard().hasPowerUpCard());
    }

    @Test

    public void catchAmmoCardTest(){

        assertNull(normalSquare.catchAmmoCard());
        assertFalse(normalSquare.containAmmoCard());
        normalSquare.setAmmoCard(ammoCard);
        assertTrue(normalSquare.containAmmoCard());
        assertNotNull(normalSquare.catchAmmoCard());
        assertFalse(normalSquare.containAmmoCard());
    }
}
