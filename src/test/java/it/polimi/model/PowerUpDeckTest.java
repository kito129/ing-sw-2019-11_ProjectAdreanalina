package it.polimi.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PowerUpDeckTest {

    PowerUpDeck powerUpDeck;



    @Before

    public void setUp(){

        powerUpDeck=new PowerUpDeck();
    }

    @Test

    public void drawnPowerUpCardTest(){

        for(int i=0;i<48;i++){

            assertNotNull(powerUpDeck.drawnPowerUpCard());

        }

    }
}
