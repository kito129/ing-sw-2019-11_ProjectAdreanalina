package it.polimi.model.PowerUp;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TargetingScopeTest {

    TargetingScope targetingScope;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        targetingScope=new TargetingScope(EnumColorCardAndAmmo.BLU);
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void effectTest() throws MapException {

        map.addPlayerOnSquare(map.getSquare(0, 0), player1);
        map.addPlayerOnSquare(map.getSquare(0,1),currentPlayer);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        targetingScope.effect(currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));

    }

    }
