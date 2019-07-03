package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PowerGloveTest {

    PowerGlove powerGlove;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        powerGlove=new PowerGlove();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.BLU,powerGlove.getRocketFistModeCost().get(0));
    }

    @Test

    public void baseModeTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&
                player1.getPlayerBoard().getMarks().size()==0);
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==0);
        powerGlove.baseMode(map,currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player1.getPlayerBoard().getMarks().size()==2&&player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==1);
        map.movePlayer(player1,map.getSquare(1,0));
        assertThrows(NotValidDistance.class, () -> {
            powerGlove.baseMode(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player1.getPlayerBoard().getMarks().size()==2&&player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==1);
    }

    @Test

    public void rocketFistModeTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==0);
        powerGlove.rocketFistMode(map,currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==1);
        map.movePlayer(player1,map.getSquare(2,1));
        assertThrows(NotValidDistance.class, () -> {
            powerGlove.rocketFistMode(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==1);
    }

    @Test

    public void rocketFistModeTest2() throws MapException, NotInSameDirection, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        map.addPlayerOnSquare(map.getSquare(0,2),player2);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&player2.getPlayerBoard().getDamages().size()==0);
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==0);
        powerGlove.rocketFistMode(map,currentPlayer,player1,player2);
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&player2.getPlayerBoard().getDamages().size()==2&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==2);
        map.movePlayer(currentPlayer,map.getSquare(0,0));
        map.movePlayer(player2,map.getSquare(0,3));
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==0);
        assertTrue(player2.getRow()==0&&player2.getColumn()==3);
        assertThrows(NotValidDistance.class, () -> {
            powerGlove.rocketFistMode(map, currentPlayer, player1,player2);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&player2.getPlayerBoard().getDamages().size()==2&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==0&&
                player2.getRow()==0&&player2.getColumn()==3);
        map.movePlayer(player2,map.getSquare(1,1));
        assertTrue(player2.getRow()==1&&player2.getColumn()==1);
        assertThrows(NotInSameDirection.class, () -> {
            powerGlove.rocketFistMode(map, currentPlayer, player1,player2);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&player2.getPlayerBoard().getDamages().size()==2&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==0);
    }
}
