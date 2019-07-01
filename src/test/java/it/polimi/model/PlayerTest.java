package it.polimi.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player player;
    GameModel gameModel;


    @Before

    public void setUp(){

        gameModel=new GameModel();
        player=new Player(1,"ANDREA",EnumColorPlayer.BLU,gameModel);
    }

    @Test

    public void getIdNameColor(){

        assertEquals(1,player.getId());
        assertEquals("ANDREA",player.getName());
        assertEquals(EnumColorPlayer.BLU,player.getColor());

    }

   @Test

   public void setPositionTest(){

        assertEquals(-1,player.getColumn());
        assertEquals(-1,player.getRow());
        player.setPosition(0,2);
        assertEquals(0,player.getRow());
        assertEquals(2,player.getColumn());
   }

   @Test

    public void scoreToAddTest(){

        player.increaseScore(5);
        assertEquals(5,player.getScore());
        player.increaseScore(3);
        assertEquals(8,player.getScore());
   }

    @Test

    public void setAliveTest(){

        assertTrue(player.isAlive());
        player.setAlive(false);
        assertFalse(player.isAlive());
    }

    @Test

    public void setOnlineModelTest(){

        assertTrue(player.getOnline());
        player.setOnlineModel(false);
        assertFalse(player.getOnline());
        player.setOnlineModel(true);
        assertTrue(player.getOnline());

    }
}
