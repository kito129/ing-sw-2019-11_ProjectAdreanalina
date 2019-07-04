package it.polimi.model;

import it.polimi.model.Exception.MapException;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.Weapon.LockRifle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameModelTest {

    GameModel gameModel;

    @Before

    public void setUp(){

        gameModel=new GameModel();
    }

    @Test

    public void getRandomColorTest() {

        ArrayList<EnumColorPlayer> colorRandom = new ArrayList<>();
        colorRandom.add(gameModel.getRandomColor());
        colorRandom.add(gameModel.getRandomColor());
        colorRandom.add(gameModel.getRandomColor());
        colorRandom.add(gameModel.getRandomColor());
        colorRandom.add(gameModel.getRandomColor());
        assertTrue(colorRandom.size() == 5 && colorRandom.contains(EnumColorPlayer.BLU) && colorRandom.contains(EnumColorPlayer.YELLOW) &&
                colorRandom.contains(EnumColorPlayer.PINK) && colorRandom.contains(EnumColorPlayer.GREEN) &&
                colorRandom.contains(EnumColorPlayer.GREY));
        assertNull(gameModel.getRandomColor());
    }


   @Test

    public void getPlayerDamagedTest(){

        Player player=new Player(1,"ANDREA",EnumColorPlayer.PINK,gameModel);
        assertEquals(0,gameModel.getPlayerDamaged().size());
        player.singleDamage(EnumColorPlayer.BLU);
        assertEquals(1,gameModel.getPlayerDamaged().size());
        assertTrue(gameModel.getPlayerDamaged().contains(player));
    }

    @Test

    public void setGetBeforeErrorTest(){

        gameModel.setBeforeError(State.CHOSEACTION);
        assertEquals(State.CHOSEACTION,gameModel.getBeforeError());
        gameModel.setBeforeError(State.MENU);
        assertEquals(State.MENU,gameModel.getBeforeError());
    }

    @Test

    public void setGetPowerUpSelectedTest(){

        assertNull(gameModel.getPowerUpSelected());
        Newton newton=new Newton(EnumColorCardAndAmmo.BLU);
        gameModel.setPowerUpSelected(newton);
        assertEquals(newton,gameModel.getPowerUpSelected());
    }

    @Test

    public void setGetWeaponNameTest(){

        gameModel.setWeaponName("LOCK RIFLE");
        assertEquals("LOCK RIFLE",gameModel.getWeaponName());
    }

    @Test

    public void setGetBeforeEffectTest(){

        gameModel.setBeforeEffect(WeaponsEffect.BaseEffect);
        assertEquals(WeaponsEffect.BaseEffect,gameModel.getBeforeEffect());
    }

    @Test

    public void setGetActualPlayerTest(){

        Player player=new Player(1,"ANDREA",EnumColorPlayer.BLU,gameModel);
        gameModel.setActualPlayer(player);
        assertEquals(player,gameModel.getActualPlayer());
    }

    @Test

    public void setGetErrorMessageTest(){

        gameModel.setErrorMessage("ERROR");
        assertEquals("ERROR",gameModel.getErrorMessage());
    }

    @Test

    public void actionCountTest(){

        assertEquals(0,gameModel.getActionCount());
        gameModel.incrementActionCount();
        gameModel.incrementActionCount();
        assertEquals(2,gameModel.getActionCount());
        gameModel.resetActionCount();
        assertEquals(0,gameModel.getActionCount());
    }

    @Test

    public void spawnedPlayerTest(){

        assertEquals(0,gameModel.getSpawnedPlayer());
        gameModel.incrementSpawnedPlayer();
        assertEquals(1,gameModel.getSpawnedPlayer());
    }

    @Test

    public void setGetSpawnPlayerTest(){

        Player player=new Player(1,"ANDREA",EnumColorPlayer.BLU,gameModel);
        assertNull(gameModel.getSpawnPlayer());
        gameModel.setSpawnPlayer(player);
        assertEquals(player,gameModel.getSpawnPlayer());
    }

    @Test

    public void endTurnTest(){

        gameModel.setEndTurn(true);
        assertTrue(gameModel.isEndTurn());
    }

    @Test

    public void setGetPlayersTest(){

        Player player=new Player(1,"ANDREA",EnumColorPlayer.BLU,gameModel);
        Player player1=new Player(2,"MARCO",EnumColorPlayer.YELLOW,gameModel);
        Player player2=new Player(3,"SIMO",EnumColorPlayer.GREEN,gameModel);
        gameModel.setPlayers(player);
        gameModel.setPlayers(player1);
        gameModel.setPlayers(player2);
        gameModel.setActualPlayer(player);
        assertTrue(gameModel.getPlayers(false).size()==2&&
                !gameModel.getPlayers(false).contains(player));
        assertTrue(gameModel.getPlayers(true).size()==3&&
                gameModel.getPlayers(true).contains(player));

    }

    @Test

    public void getTest(){

        assertNotNull(gameModel.getMap());
        assertNotNull(gameModel.getPowerUpDeck());
        assertNotNull(gameModel.getWeaponDeck());
        assertNotNull(gameModel.getAmmoDeck());
        assertNotNull(gameModel.getKillShotTrack());


    }

    @Test

    public void getWeaponToChargeTest(){

        LockRifle lockRifle=new LockRifle();
        gameModel.getWeaponToCharge().add(lockRifle);
        assertEquals(lockRifle,gameModel.getWeaponToCharge().get(0));

    }

    @Test

    public void getSetStateTest(){

        assertEquals(State.LOBBY,gameModel.getState());
        gameModel.setState(State.CHOSEACTION);
        assertEquals(State.CHOSEACTION,gameModel.getState());
    }

    @Test

    public void seGetMessageToAllViewTest(){

        gameModel.setMessageToAllView("Error");
        assertEquals("Error",gameModel.getMessageToAllView());


    }

    @Test

    public void setGetMessageToCurrentView(){

        gameModel.setMessageToCurrentView("Error");
        assertEquals("Error",gameModel.getMessageToCurrentView());
    }

    @Test

    public void setGetDeadPlayerTest(){

        Player player=new Player(1,"ANDREA",EnumColorPlayer.BLU,gameModel);
        Player player1=new Player(2,"MARCO",EnumColorPlayer.YELLOW,gameModel);
        Player player2=new Player(3,"SIMO",EnumColorPlayer.GREEN,gameModel);
        gameModel.setPlayers(player);
        gameModel.setPlayers(player1);
        gameModel.setPlayers(player2);
        assertEquals(3,gameModel.getPlayers(true).size());
        gameModel.setDeadPlayer();
        assertEquals(0,gameModel.getDeadPlayers().size());
        player.setAlive(false);
        gameModel.setDeadPlayer();
        assertEquals(1,gameModel.getDeadPlayers().size());
        assertTrue(gameModel.getDeadPlayers().contains(player)&&!gameModel.getDeadPlayers().contains(player1)&&
                !gameModel.getDeadPlayers().contains(player2));
        assertEquals(3,gameModel.getPlayers(true).size());


    }

    @Test

    public void getPlayerColorTest(){

        assertEquals(0,gameModel.getPlayerColor().size());
        Player player=new Player(1,"ANDREA",EnumColorPlayer.BLU,gameModel);
        Player player1=new Player(2,"MARCO",EnumColorPlayer.YELLOW,gameModel);
        Player player2=new Player(3,"SIMO",EnumColorPlayer.GREEN,gameModel);
        gameModel.setPlayers(player);
        gameModel.setPlayers(player1);
        gameModel.setPlayers(player2);
        assertTrue(gameModel.getPlayerColor().size()==3&&gameModel.getPlayerColor().contains(EnumColorPlayer.BLU)&&
                gameModel.getPlayerColor().contains(EnumColorPlayer.YELLOW)&&gameModel.getPlayerColor().contains(EnumColorPlayer.GREEN));

    }

    @Test

    public void getPlayerByColorTest(){

        Player player=new Player(1,"ANDREA",EnumColorPlayer.BLU,gameModel);
        Player player1=new Player(2,"MARCO",EnumColorPlayer.YELLOW,gameModel);
        gameModel.setPlayers(player);
        gameModel.setPlayers(player1);
        assertEquals(player,gameModel.getPlayerByColor(EnumColorPlayer.BLU));
        assertEquals(player1,gameModel.getPlayerByColor(EnumColorPlayer.YELLOW));
        assertNull(gameModel.getPlayerByColor(EnumColorPlayer.GREEN));

    }

    @Test

    public void setGetActualWeaponEffectTest(){

        gameModel.setActualWeaponEffect(WeaponsEffect.BaseEffect);
        assertEquals(WeaponsEffect.BaseEffect,gameModel.getActualWeaponEffect());
    }

    @Test

    public void setGetWeaponStateTest(){

        gameModel.setWeaponState(WeaponState.LockRifle);
        assertEquals(WeaponState.LockRifle,gameModel.getWeaponState());
    }

    @Test

   public void getPlayerByIdTest() throws MapException {

        Player player=new Player(1,"ANDREA",EnumColorPlayer.BLU,gameModel);
        Player player1=new Player(2,"MARCO",EnumColorPlayer.YELLOW,gameModel);
        gameModel.setPlayers(player);
        gameModel.setPlayers(player1);
        assertEquals(player,gameModel.getPlayerById(1));
        assertEquals(player1,gameModel.getPlayerById(2));
        assertThrows(MapException.class, () -> {

            gameModel.getPlayerById(4);
        });


    }







}
