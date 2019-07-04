package it.polimi.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AmmoDeckTest {

   AmmoDeck ammoDeck;
   PowerUpDeck powerUpDeck;


    @Before

    public void setUp(){

        ammoDeck=new AmmoDeck();
        powerUpDeck=new PowerUpDeck();
    }

    @Test

    public void drawAmmoCardTest() {

        for(int i=0;i<72;i++){

            AmmoCard ammoCard=ammoDeck.drawAmmoCard(powerUpDeck);
            assertNotNull(ammoCard);
            if(ammoCard.hasPowerUpCard()){

                assertNotNull(ammoCard.getPowerUpCard());
            }else{

                assertNull(ammoCard.getPowerUpCard());
            }
        }



    }

}
