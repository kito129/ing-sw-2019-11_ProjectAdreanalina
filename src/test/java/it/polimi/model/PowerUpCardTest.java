package it.polimi.model;

import it.polimi.model.PowerUp.Newton;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PowerUpCardTest {


    PowerUpCard powerUpCard;


    @Before

    public void setUp(){

        powerUpCard=new Newton(EnumColorCardAndAmmo.BLU);
    }

    @Test

    public void getDescriptionTest(){

        assertEquals("You may play this card on your turn before or after any action." +
                        " Choose any other player's figure and move it 1 or 2 squares in one direction." +
                        " (You can't use this to move a figure after it respawns at the end of your turn. That would be too late.)",
                powerUpCard.getDescription());

    }

    @Test

    public void getNameCard(){

        assertEquals("NEWTON",powerUpCard.getNameCard());
    }

    @Test

    public void getColorRespawnTest(){

        assertEquals(EnumColorSquare.BLU,powerUpCard.getColorRespawn());
    }

    @Test

    public void getColorPowerUpCardTest(){

        assertEquals(EnumColorCardAndAmmo.BLU,powerUpCard.getColorPowerUpCard());
    }
}
