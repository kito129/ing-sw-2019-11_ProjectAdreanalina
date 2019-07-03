package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VortexCannonTest {

    VortexCannon vortexCannon;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        vortexCannon = new VortexCannon();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.RED, vortexCannon.getBlackHoleCost().get(0));
    }

    @Test

    public void baseEffectTest() throws MapException, NotVisibleTarget, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(1, 2), player1);
        map.addPlayerOnSquare(map.getSquare(1, 3), player2);

        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        vortexCannon.baseEffect(map, map.getSquare(1, 2), currentPlayer, player1);
        assertTrue(player1.getPlayerBoard().getDamages().size() == 2 &&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(player1.getRow() == 1 && player1.getColumn() == 2);
        assertEquals(0, player2.getPlayerBoard().getDamages().size());
        assertTrue(player2.getRow() == 1 && player2.getColumn() == 3);
        vortexCannon.baseEffect(map, map.getSquare(1, 2), currentPlayer, player2);
        assertTrue(player1.getPlayerBoard().getDamages().size() == 2 &&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(player1.getRow() == 1 && player1.getColumn() == 2);
        assertTrue(player2.getPlayerBoard().getDamages().size() == 2 && player2.getPlayerBoard().getDamages()
                .contains(currentPlayer.getColor()));
        assertTrue(player2.getRow() == 1 && player2.getColumn() == 2);
        map.movePlayer(player1, map.getSquare(0, 1));
        assertThrows(NotVisibleTarget.class, () -> {

            vortexCannon.baseEffect(map, map.getSquare(0, 1), currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 2 &&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(player1.getRow() == 0 && player1.getColumn() == 1);
        assertTrue(player2.getPlayerBoard().getDamages().size() == 2 && player2.getPlayerBoard().getDamages()
                .contains(currentPlayer.getColor()));
        assertTrue(player2.getRow() == 1 && player2.getColumn() == 2);
        map.movePlayer(player1, map.getSquare(2, 1));
        assertThrows(NotValidDistance.class, () -> {

            vortexCannon.baseEffect(map, map.getSquare(2, 1), currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 2 &&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(player1.getRow() == 2 && player1.getColumn() == 1);
        assertTrue(player2.getPlayerBoard().getDamages().size() == 2 && player2.getPlayerBoard().getDamages()
                .contains(currentPlayer.getColor()));
        assertTrue(player2.getRow() == 1 && player2.getColumn() == 2);
        map.movePlayer(player1,map.getSquare(0,3));
        assertThrows(NotValidDistance.class, () -> {

            vortexCannon.baseEffect(map,map.getSquare(1,2),currentPlayer,player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 2 &&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(player1.getRow() == 0 && player1.getColumn() == 3);
        assertTrue(player2.getPlayerBoard().getDamages().size() == 2 && player2.getPlayerBoard().getDamages()
                .contains(currentPlayer.getColor()));
        assertTrue(player2.getRow() == 1 && player2.getColumn() == 2);

    }
    @Test

    public void blackHoleEffectTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(1, 2), player1);
        map.addPlayerOnSquare(map.getSquare(1, 3), player2);
        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&
                player2.getPlayerBoard().getDamages().size()==0&&
                player1.getRow()==1&&player1.getColumn()==2&&
                player2.getRow()==1&&player2.getColumn()==3);
        vortexCannon.blackHoleEffect(map,map.getSquare(1,2),currentPlayer,players);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player2.getPlayerBoard().getDamages().size()==1);
        assertTrue(player1.getRow()==1&&player1.getColumn()==2);
        assertTrue(player2.getRow()==1&&player2.getColumn()==2);
        map.movePlayer(player2,map.getSquare(0,1));
        assertThrows(NotValidDistance.class, () -> {
            vortexCannon.blackHoleEffect(map,map.getSquare(1,2),currentPlayer,players);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player2.getPlayerBoard().getDamages().size()==1);
        assertTrue(player1.getRow()==1&&player1.getColumn()==2);
        assertTrue(player2.getRow()==0&&player2.getColumn()==1);

    }

}


