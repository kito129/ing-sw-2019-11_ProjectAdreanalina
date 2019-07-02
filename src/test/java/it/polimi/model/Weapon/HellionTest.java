package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HellionTest {


    Hellion hellion;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        hellion = new Hellion();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.RED, hellion.getNanoTracerModeCost().get(0));
    }

    @Test

    public void baseModeTest() throws MapException, NotVisibleTarget, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(1, 1), player1);
        map.addPlayerOnSquare(map.getSquare(1, 1), player2);
        assertTrue(player1.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getDamages().size() == 0 &&
                player1.getPlayerBoard().getMarks().size() == 0 && player2.getPlayerBoard().getMarks().size() == 0);
        hellion.baseMode(map, currentPlayer, player1);
        assertTrue(player1.getPlayerBoard().getDamages().size() == 1
                && player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
                player1.getPlayerBoard().getMarks().size() == 1 && player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()) &&
                player2.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getMarks().size() == 1 &&
                player2.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
        map.movePlayer(player1, map.getSquare(2, 1));
        map.movePlayer(player2, map.getSquare(2, 1));
        assertThrows(NotVisibleTarget.class, () -> {
            hellion.baseMode(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 1
                && player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
                player1.getPlayerBoard().getMarks().size() == 1 && player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()) &&
                player2.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getMarks().size() == 1 &&
                player2.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
        map.movePlayer(player1, map.getSquare(0, 1));
        map.movePlayer(player2, map.getSquare(0, 1));
        assertThrows(NotValidDistance.class, () -> {
            hellion.baseMode(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 1
                && player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
                player1.getPlayerBoard().getMarks().size() == 1 && player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()) &&
                player2.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getMarks().size() == 1 &&
                player2.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
    }

    @Test

    public void nanoTracerModeTest() throws MapException, NotVisibleTarget, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(1, 1), player1);
        map.addPlayerOnSquare(map.getSquare(1, 1), player2);
        assertTrue(player1.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getDamages().size() == 0 &&
                player1.getPlayerBoard().getMarks().size() == 0 && player2.getPlayerBoard().getMarks().size() == 0);
        hellion.nanoTracerMode(map, currentPlayer, player1);
        assertTrue(player1.getPlayerBoard().getDamages().size() == 1
                && player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
                player1.getPlayerBoard().getMarks().size() == 2 && player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()) &&
                player2.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getMarks().size() == 2 &&
                player2.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
        map.movePlayer(player1, map.getSquare(2, 1));
        map.movePlayer(player2, map.getSquare(2, 1));
        assertThrows(NotVisibleTarget.class, () -> {
            hellion.nanoTracerMode(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 1
                && player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
                player1.getPlayerBoard().getMarks().size() == 2 && player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()) &&
                player2.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getMarks().size() == 2 &&
                player2.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
        map.movePlayer(player1, map.getSquare(0, 1));
        map.movePlayer(player2, map.getSquare(0, 1));
        assertThrows(NotValidDistance.class, () -> {
            hellion.nanoTracerMode(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 1
                && player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
                player1.getPlayerBoard().getMarks().size() == 2&& player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()) &&
                player2.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getMarks().size() == 2 &&
                player2.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
    }


}
