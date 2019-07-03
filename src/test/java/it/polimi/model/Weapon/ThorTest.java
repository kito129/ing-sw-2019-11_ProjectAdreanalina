package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThorTest {

    Thor thor;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        thor=new Thor();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.BLU,thor.getChainReactionCost().get(0));
        assertEquals(EnumColorCardAndAmmo.BLU,thor.getHighVoltageCost().get(0));

    }

    @Test

    public void baseEffectTest() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 1), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        thor.baseEffect(map,currentPlayer,player1);
        assertEquals(2, player1.getPlayerBoard().getDamages().size());
        map.movePlayer(player1,map.getSquare(1,0));
        assertThrows(NotVisibleTarget.class, () -> {

            thor.baseEffect(map,currentPlayer,player1);
        });
        assertEquals(2, player1.getPlayerBoard().getDamages().size());

    }

    @Test

    public void chainReactionTest() throws MapException {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 1), player1);
        thor.chainReactionEffect(currentPlayer,player1);
        assertEquals(1, player1.getPlayerBoard().getDamages().size());
    }

    @Test

    public void highVoltageTest() throws MapException {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 1), player1);
        thor.highVoltageEffect(currentPlayer,player1);
        assertEquals(2, player1.getPlayerBoard().getDamages().size());
    }
}
