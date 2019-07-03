package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SledgehammerTest {

    Sledgehammer sledgehammer;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        sledgehammer=new Sledgehammer();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.RED,sledgehammer.getPulverizeModeCost().get(0));

    }

    @Test

    public void baseModeTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 1), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        sledgehammer.baseMode(map,currentPlayer,player1);
        assertEquals(2, player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(2,2));
        assertThrows(NotValidDistance.class, () -> {

            sledgehammer.baseMode(map,currentPlayer,player1);
        });
        assertEquals(2, player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));

    }

    @Test

    public void pulverizeModeTest() throws MapException, NotInSameDirection, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(2, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 0), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        sledgehammer.pulverizeMode(map,currentPlayer,player1,map.getSquare(2,2));
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&
                player1.getRow()==2&&player1.getColumn()==2);
        map.movePlayer(player1,map.getSquare(2,0));
        assertThrows(NotValidDistance.class, () -> {

            sledgehammer.pulverizeMode(map,currentPlayer,player1,map.getSquare(2,3));
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&
                player1.getRow()==2&&player1.getColumn()==0);
        assertThrows(NotInSameDirection.class, () -> {

            sledgehammer.pulverizeMode(map,currentPlayer,player1,map.getSquare(1,1));
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&
                player1.getRow()==2&&player1.getColumn()==0);

    }
}
