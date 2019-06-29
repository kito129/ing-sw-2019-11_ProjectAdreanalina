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

        assertEquals("Puoi giocare questa carta nel tuo turno prima o dopo aver svolto qualsiasi azione.\n" +
                "Scegli la miniatura di un altro giocatore e muovila di 1 o 2 quadrati in una direzione.\n" +
                "(Non puoi usare questo potenziamento per muovere una miniatura dopo che è stata rigenerata alla fine del tuo turno, è troppo tardi.)",
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
