package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlasmaGunTest {

    PlasmaGun plasmaGun;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        plasmaGun=new PlasmaGun();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertNull(plasmaGun.getPhaseGlideCost().get(0));
        assertEquals(EnumColorCardAndAmmo.BLU,plasmaGun.getChargedShotCost().get(0));

    }

    @Test

    public void baseEffectTest() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 2), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        plasmaGun.baseEffect(map,currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&player1.getPlayerBoard().getDamages()
                .contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(0,0));
        assertThrows(NotVisibleTarget.class, () -> {
            plasmaGun.baseEffect(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&player1.getPlayerBoard().getDamages()
                .contains(currentPlayer.getColor()));
    }

    @Test

    public void phaseGlideEffectTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0,0), currentPlayer);
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==0);
        plasmaGun.phaseGlideEffect(map,map.getSquare(0,2),currentPlayer);
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==2);
        assertThrows(NotValidDistance.class, () -> {
            plasmaGun.phaseGlideEffect(map,map.getSquare(2,3),currentPlayer);
        });
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==2);
    }

    @Test

    public void chargedShotEffectTest() throws MapException {

        map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 2), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        plasmaGun.chargedShotEffect(currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));


    }
}
