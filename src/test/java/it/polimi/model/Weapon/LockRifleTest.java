package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockRifleTest {

    LockRifle lockRifle;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        lockRifle=new LockRifle();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.RED, lockRifle.getSecondLockCost().get(0));
    }

    @Test

    public void baseEffectTest() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(0, 2), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(1, 2), player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&
                player1.getPlayerBoard().getMarks().size()==0);
        lockRifle.baseEffect(map,currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&
                player1.getPlayerBoard().getMarks().size()==1);
        map.movePlayer(player1,map.getSquare(0,0));
        assertThrows(NotVisibleTarget.class, () -> {
            lockRifle.baseEffect(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&
                player1.getPlayerBoard().getMarks().size()==1);

    }

    @Test

    public void secondLockEffectTest() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(0, 2), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 3), player1);
        assertTrue(player1.getPlayerBoard().getMarks().size()==0&&player1.getPlayerBoard().getDamages().size()==0);
        lockRifle.secondLockEffect(map,currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getMarks().size()==1&&player1.getPlayerBoard().getDamages().size()==0);
        map.movePlayer(player1,map.getSquare(0,0));
        assertThrows(NotVisibleTarget.class, () -> {
            lockRifle.secondLockEffect(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getMarks().size()==1&&player1.getPlayerBoard().getDamages().size()==0);





    }

}
