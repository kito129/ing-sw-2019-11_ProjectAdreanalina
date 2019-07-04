package it.polimi.model;

import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Weapon.Cyberblade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapTest {

    Map map;
    Player player1;
    Player player2;
    GameModel gameModel;

    @Before

    public void setUp() {

        this.map = new Map(MapCreator.createD(), "D");
        gameModel=new GameModel();
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getSquareTest() throws MapException {

        assertEquals(0,map.getSquare(0,2).getRow());
        assertEquals(2,map.getSquare(0,2).getColumn());
        assertThrows(MapException.class, () -> {
            map.getSquare(5,5);
        });
    }

    @Test

    public void findPlayerTest() throws MapException {

        map.addPlayerOnSquare(map.getSquare(1, 2), player1);
        assertEquals(1,map.findPlayer(player1).getRow());
        assertEquals(2,map.findPlayer(player1).getColumn());
        assertThrows(MapException.class, () -> {
            map.findPlayer(player2);
        });

    }


}