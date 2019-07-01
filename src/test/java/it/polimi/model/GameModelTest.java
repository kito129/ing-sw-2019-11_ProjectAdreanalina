package it.polimi.model;

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

    public void test(){

        
   }


}
