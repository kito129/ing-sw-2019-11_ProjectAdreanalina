package it.polimi.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class WeaponDeckTest {

    WeaponDeck weaponDeck;


    @Before

    public void setUp(){

        weaponDeck=new WeaponDeck();
    }


    @Test

    public void testDrawnWeaponCard(){

        for(int i=0;i<21;i++){

            assertNotNull(weaponDeck.drawWeaponCard());
        }
        assertNull(weaponDeck.drawWeaponCard());
    }
}
