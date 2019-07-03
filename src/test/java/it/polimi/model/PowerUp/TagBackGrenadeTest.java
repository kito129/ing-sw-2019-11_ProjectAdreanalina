package it.polimi.model.PowerUp;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TagBackGrenadeTest {

    TagBackGrenade tagBackGrenade;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        tagBackGrenade=new TagBackGrenade(EnumColorCardAndAmmo.BLU);
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void effectTest() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(0,1),currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 0), player1);
        assertEquals(0, currentPlayer.getPlayerBoard().getMarks().size());
        tagBackGrenade.effect(map,currentPlayer,player1);
        assertTrue(currentPlayer.getPlayerBoard().getMarks().size()==1&&
                currentPlayer.getPlayerBoard().getMarks().contains(player1.getColor()));
        map.movePlayer(currentPlayer,map.getSquare(2,2));
        assertThrows(NotVisibleTarget.class, () -> {

            tagBackGrenade.effect(map,currentPlayer,player1);
        });
        assertTrue(currentPlayer.getPlayerBoard().getMarks().size()==1&&
                currentPlayer.getPlayerBoard().getMarks().contains(player1.getColor()));




    }
}
