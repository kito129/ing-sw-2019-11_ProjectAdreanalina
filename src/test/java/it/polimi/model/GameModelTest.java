package it.polimi.model;

import it.polimi.model.PowerUp.Newton;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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


}
