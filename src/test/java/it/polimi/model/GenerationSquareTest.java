package it.polimi.model;

import it.polimi.model.Weapon.Cyberblade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenerationSquareTest {

    GenerationSquare generationSquare;
    Cyberblade cyberblade;

    @Before

    public void setUp(){

        generationSquare=new GenerationSquare(1,2,EnumColorSquare.BLU);
        cyberblade=new Cyberblade();

    }

    @Test

    public void addGetWeaponCardTest(){

        assertEquals(0,generationSquare.getWeaponList().size());
        generationSquare.addWeaponCard(cyberblade);
        assertEquals(1,generationSquare.getWeaponList().size());
        assertTrue(generationSquare.getWeaponList().contains(cyberblade));

    }

    @Test

    public void catchWeaponTest(){

        generationSquare.addWeaponCard(cyberblade);
        assertEquals(1,generationSquare.getWeaponList().size());
        assertTrue(generationSquare.getWeaponList().contains(cyberblade));
        assertEquals("CYBERBLADE",generationSquare.catchWeapon(0).getNameWeaponCard());
        assertEquals(0,generationSquare.getWeaponList().size());
        assertFalse(generationSquare.getWeaponList().contains(cyberblade));



    }

}
