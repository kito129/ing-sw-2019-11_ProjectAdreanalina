package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CyberbladeTest {

    Cyberblade cyberblade;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        cyberblade = new Cyberblade();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertNull(cyberblade.getShadowstepCost().get(0));
        assertEquals(EnumColorCardAndAmmo.YELLOW, cyberblade.getSliceAndDiceCost().get(0));

    }

    @Test

    public void baseEffectTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 0), player1);
        assertTrue(player1.getPlayerBoard().getMarks().size() == 0 && player1.getPlayerBoard().getDamages().size() == 0);
        cyberblade.baseEffect(map, currentPlayer, player1);
        assertTrue(player1.getPlayerBoard().getDamages().size() == 2 &&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player1, map.getSquare(0, 1));
        assertThrows(NotValidDistance.class, () -> {
            cyberblade.baseEffect(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 2 &&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
    }

    @Test

    public void shadowStepEffect() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        cyberblade.shadowstepEffect(map, currentPlayer, map.getSquare(1, 0));
        assertTrue(currentPlayer.getRow() == 1 && currentPlayer.getColumn() == 0);
        assertThrows(NotValidDistance.class, () -> {
            cyberblade.shadowstepEffect(map, currentPlayer, map.getSquare(0, 1));
        });


    }

    @Test

    public void sliceAndDiceTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 0), player1);
        assertTrue(player1.getPlayerBoard().getMarks().size() == 0 && player1.getPlayerBoard().getDamages().size() == 0);
        cyberblade.sliceAndDiceEffect(map, currentPlayer, player1);
        assertTrue(player1.getPlayerBoard().getDamages().size() == 2 &&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player1, map.getSquare(0, 1));
        assertThrows(NotValidDistance.class, () -> {
            cyberblade.sliceAndDiceEffect(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 2 &&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
    }

}
