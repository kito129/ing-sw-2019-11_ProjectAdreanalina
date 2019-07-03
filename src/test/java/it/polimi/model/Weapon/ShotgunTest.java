package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShotgunTest {

    Shotgun shotgun;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        shotgun=new Shotgun();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertNull(shotgun.getLongBarrelModeCost().get(0));


    }

    @Test

    public void baseModeTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 1), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getRow()==2&&player1.getColumn()==1);
        shotgun.baseMode(map,currentPlayer,player1,map.getSquare(2,0));
        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getRow()==2&&player1.getColumn()==0);
        assertThrows(NotValidDistance.class, () -> {
            shotgun.baseMode(map,currentPlayer,player1,map.getSquare(2,2));
        });
        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getRow()==2&&player1.getColumn()==0&&
                currentPlayer.getColumn()==1&&currentPlayer.getRow()==2);
        map.movePlayer(currentPlayer,map.getSquare(2,0));
        assertThrows(NotValidDistance.class, () -> {

            shotgun.baseMode(map,currentPlayer,player1,map.getSquare(2,3));
        });
        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getRow()==2&&player1.getColumn()==0);
    }

    @Test

    public void longBarrelModeTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(1, 1), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        shotgun.longBarrelMode(map,currentPlayer,player1);
        assertEquals(2, player1.getPlayerBoard().getDamages().size());
        map.movePlayer(player1,map.getSquare(0,1));
        assertThrows(NotValidDistance.class, () -> {

            shotgun.longBarrelMode(map,currentPlayer,player1);
        });
        assertEquals(2, player1.getPlayerBoard().getDamages().size());




    }

}
