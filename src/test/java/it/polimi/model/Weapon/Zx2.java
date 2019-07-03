package it.polimi.model.Weapon;

import it.polimi.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Zx2 {

    Zx2 zx2;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        zx2=new Zx2();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        
    }



}
