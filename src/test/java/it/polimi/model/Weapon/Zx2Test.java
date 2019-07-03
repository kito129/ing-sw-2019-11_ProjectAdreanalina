package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Zx2Test {

    Zx2 zx2;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;
    Player player3;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        zx2 =new Zx2();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3=new Player(4,"NIKO",EnumColorPlayer.GREEN,gameModel);
    }
    @Test

    public void getCostEffectTest() {

        assertNull(zx2.getScannerModeCost().get(0));
    }

    @Test

    public void baseModeTest() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        assertTrue(player1.getPlayerBoard().getMarks().size()==0&&player1.getPlayerBoard().getDamages().size()==0);
        zx2.baseMode(map,currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getMarks().size()==2&&player1.getPlayerBoard().getDamages().size()==1);
        map.movePlayer(player1,map.getSquare(2,0));
        assertThrows(NotVisibleTarget.class, () -> {

            zx2.baseMode(map,currentPlayer,player1);
        });
        assertTrue(player1.getPlayerBoard().getMarks().size()==2&&player1.getPlayerBoard().getDamages().size()==1);

    }

    @Test

    public void scannerModeTest() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(1, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        map.addPlayerOnSquare(map.getSquare(2,1),player2);
        map.addPlayerOnSquare(map.getSquare(0,2),player3);
        ArrayList<Player>players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        assertTrue(player1.getPlayerBoard().getMarks().size()==0&&player2.getPlayerBoard().getMarks().size()==0&&
                player3.getPlayerBoard().getMarks().size()==0);
        zx2.scannerMode(map,currentPlayer,players);
        assertTrue(player1.getPlayerBoard().getMarks().size()==1&&player2.getPlayerBoard().getMarks().size()==1&&
                player3.getPlayerBoard().getMarks().size()==1);
        map.movePlayer(player1,map.getSquare(1,2));
        assertThrows(NotVisibleTarget.class, () -> {

            zx2.scannerMode(map,currentPlayer,players);
        });
        assertTrue(player1.getPlayerBoard().getMarks().size()==1&&player2.getPlayerBoard().getMarks().size()==1&&
                player3.getPlayerBoard().getMarks().size()==1);






    }



}
