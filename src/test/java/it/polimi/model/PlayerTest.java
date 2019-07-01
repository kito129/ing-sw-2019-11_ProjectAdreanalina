package it.polimi.model;

import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.Teleporter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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

    @Test

    public void setPowerUpCardSpawnTest(){

        assertEquals(0,player.getPowerUpCardsSpawn().size());
        ArrayList<PowerUpCard>powerUpCards=new ArrayList<>();
        Newton newton=new Newton(EnumColorCardAndAmmo.BLU);
        Teleporter teleporter=new Teleporter(EnumColorCardAndAmmo.BLU);
        assertFalse(player.getPowerUpCardsSpawn().contains(newton));
        assertFalse(player.getPowerUpCardsSpawn().contains(teleporter));
        powerUpCards.add(newton);
        powerUpCards.add(teleporter);
        player.setPowerUpCardsSpawn(powerUpCards);
        assertTrue(player.getPowerUpCardsSpawn().contains(newton));
        assertTrue(player.getPowerUpCardsSpawn().contains(teleporter));
    }

    @Test

    public void catchAmmoCardTest(){

        AmmoCard ammoCard=new AmmoCard(1,2,0,true);
        Newton newton=new Newton(EnumColorCardAndAmmo.BLU);
        ammoCard.setPowerUpCard(newton);
        assertTrue(player.getPlayerBoard().getAmmoR().size()==1&&player.getPlayerBoard().getAmmoB().size()==1&&
                player.getPlayerBoard().getAmmoY().size()==1&&player.getPlayerBoard().getPlayerPowerUps().size()==0);
        player.catchAmmoCard(ammoCard);
        assertTrue(player.getPlayerBoard().getPlayerPowerUps().size()==1
                &&player.getPlayerBoard().getPlayerPowerUps().contains(newton));
        assertTrue(player.getPlayerBoard().getAmmoR().size()==2&&player.getPlayerBoard().getAmmoB().size()==1&&
                player.getPlayerBoard().getAmmoY().size()==3);
    }

    @Test

    public void singleMarkTest(){

        assertEquals(0, player.getPlayerBoard().getMarks().size());
        assertEquals(0,gameModel.getPlayerMarked().size());
        assertFalse(gameModel.getPlayerMarked().contains(player));
        player.singleMark(EnumColorPlayer.BLU);
        assertTrue(gameModel.getPlayerMarked().size()==1&&gameModel.getPlayerMarked().contains(player));
        assertTrue(player.getPlayerBoard().getMarks().contains(EnumColorPlayer.BLU)&&
                player.getPlayerBoard().getMarks().size()==1);
    }

    @Test

    public void multipleMarksTest(){

        assertEquals(0, player.getPlayerBoard().getMarks().size());
        assertEquals(0,gameModel.getPlayerMarked().size());
        assertFalse(gameModel.getPlayerMarked().contains(player));
        ArrayList<EnumColorPlayer> marks=new ArrayList<>();
        marks.add(EnumColorPlayer.BLU);
        marks.add(EnumColorPlayer.BLU);
        marks.add(EnumColorPlayer.YELLOW);
        player.multipleMarks(marks);
        assertTrue(gameModel.getPlayerMarked().size()==1&&gameModel.getPlayerMarked().contains(player));
        assertTrue(player.getPlayerBoard().getMarks().contains(EnumColorPlayer.BLU)&&
                player.getPlayerBoard().getMarks().contains(EnumColorPlayer.YELLOW)
                &&player.getPlayerBoard().getMarks().size()==3);
    }

    @Test

    public void singleDamageTest(){

        assertEquals(0,player.getPlayerBoard().getDamages().size());
        assertEquals(0,player.getPlayerBoard().getMarks().size());
        assertFalse(gameModel.getPlayerDamaged().contains(player));
        player.singleMark(EnumColorPlayer.BLU);
        assertTrue(player.getPlayerBoard().getMarks().size()==1&&
                player.getPlayerBoard().getMarks().contains(EnumColorPlayer.BLU));
        assertEquals(0,player.getPlayerBoard().getDamages().size());
        player.singleDamage(EnumColorPlayer.BLU);
        assertTrue(player.getPlayerBoard().getMarks().size()==0&&
                player.getPlayerBoard().getDamages().size()==2&&player.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU));
        assertTrue(gameModel.getPlayerDamaged().size()==1&&gameModel.getPlayerDamaged().contains(player));


    }

    @Test

    public void multipleDamagesTest(){

        assertEquals(0,player.getPlayerBoard().getDamages().size());
        assertEquals(0,player.getPlayerBoard().getMarks().size());
        assertFalse(gameModel.getPlayerDamaged().contains(player));
        player.singleMark(EnumColorPlayer.BLU);
        assertTrue(player.getPlayerBoard().getMarks().size()==1&&
                player.getPlayerBoard().getMarks().contains(EnumColorPlayer.BLU));
        assertEquals(0,player.getPlayerBoard().getDamages().size());
        ArrayList<EnumColorPlayer> damages=new ArrayList<>();
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.BLU);
        player.multipleDamages(damages);
        assertTrue(player.getPlayerBoard().getMarks().size()==0&&
                player.getPlayerBoard().getDamages().size()==3&&player.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU));
        assertTrue(gameModel.getPlayerDamaged().size()==1&&gameModel.getPlayerDamaged().contains(player));

    }

    @Test

    public void multipleDamagesSingleMarkTest(){

        assertTrue(gameModel.getPlayerDamaged().size()==0&&gameModel.getPlayerMarked().size()==0);
        assertTrue(player.getPlayerBoard().getMarks().size()==0&&
                player.getPlayerBoard().getDamages().size()==0);
        ArrayList<EnumColorPlayer> damages=new ArrayList<>();
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.BLU);
        player.singleMark(EnumColorPlayer.BLU);
        assertTrue(gameModel.getPlayerMarked().size()==1&&gameModel.getPlayerMarked().contains(player));
        gameModel.getPlayerMarked().remove(player);
        player.multipleDamagesSingleMark(damages,EnumColorPlayer.BLU);
        assertTrue(player.getPlayerBoard().getMarks().size()==1&&player.getPlayerBoard().getDamages().size()==3&&
                player.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU));
        assertTrue(gameModel.getPlayerMarked().size()==1&&gameModel.getPlayerDamaged().size()==1&&
                gameModel.getPlayerDamaged().contains(player)&&gameModel.getPlayerMarked().contains(player));
    }

    @Test

    public void singleDamageMultipleMarksTest(){

        assertTrue(gameModel.getPlayerDamaged().size()==0&&gameModel.getPlayerMarked().size()==0);
        assertTrue(player.getPlayerBoard().getMarks().size()==0&&
                player.getPlayerBoard().getDamages().size()==0);
        ArrayList<EnumColorPlayer> marks=new ArrayList<>();
        marks.add(EnumColorPlayer.BLU);
        marks.add(EnumColorPlayer.BLU);
        player.singleMark(EnumColorPlayer.BLU);
        assertTrue(gameModel.getPlayerMarked().size()==1&&gameModel.getPlayerMarked().contains(player));
        gameModel.getPlayerMarked().remove(player);
        player.singleDamageMultipleMarks(EnumColorPlayer.BLU,marks);
        assertTrue(player.getPlayerBoard().getMarks().size()==2&&player.getPlayerBoard().getDamages().size()==2&&
                player.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU));
        assertTrue(gameModel.getPlayerMarked().size()==1&&gameModel.getPlayerDamaged().size()==1&&
                gameModel.getPlayerDamaged().contains(player)&&gameModel.getPlayerMarked().contains(player));


    }
}
