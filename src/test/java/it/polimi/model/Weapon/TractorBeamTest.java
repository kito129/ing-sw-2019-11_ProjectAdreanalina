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

public class TractorBeamTest {

    TractorBeam tractorBeam;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        tractorBeam=new TractorBeam();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.RED,tractorBeam.getPunisherModeCost().get(0));
        assertEquals(EnumColorCardAndAmmo.YELLOW,tractorBeam.getPunisherModeCost().get(1));
    }

    @Test

    public void baseModeTest() throws MapException, NotVisibleTarget, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 2), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        tractorBeam.baseMode(map,map.getSquare(0,2),currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(player1.getRow()==0&&player1.getColumn()==2);
        map.movePlayer(player1,map.getSquare(2,1));
        assertThrows(NotVisibleTarget.class, () -> {

            tractorBeam.baseMode(map,map.getSquare(1,1),currentPlayer,player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(player1.getRow()==2&&player1.getColumn()==1);
        map.movePlayer(player1,map.getSquare(2,3));
        assertThrows(NotValidDistance.class, () -> {

            tractorBeam.baseMode(map,map.getSquare(0,1),currentPlayer,player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertTrue(player1.getRow()==2&&player1.getColumn()==3);


    }

    @Test

    public void punisherModeTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 0), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        tractorBeam.punisherMode(map,currentPlayer,player1);
        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player1.getColumn()==0&&player1.getRow()==0);
        map.movePlayer(player1,map.getSquare(2,3));
        assertThrows(NotValidDistance.class, () -> {

            tractorBeam.punisherMode(map,currentPlayer,player1);
        });
        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player1.getColumn()==3&&player1.getRow()==2);




    }
}
