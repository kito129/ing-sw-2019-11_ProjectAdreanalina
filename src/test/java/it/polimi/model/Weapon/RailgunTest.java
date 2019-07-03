package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotInDirection;
import it.polimi.model.Exception.NotValidCardinalDirection;
import it.polimi.model.Exception.NotValidDistance;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RailgunTest {

    Railgun railgun;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;
    Player player3;
    Player player4;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        railgun=new Railgun();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3=new Player(4,"NIKO",EnumColorPlayer.GREY,gameModel);
        player4=new Player(5,"GABRI",EnumColorPlayer.GREEN,gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertNull(railgun.getPiercingModeCost().get(0));
    }

    @Test

    public void baseModeTest() throws MapException, NotValidCardinalDirection, NotInDirection {

        map.addPlayerOnSquare(map.getSquare(1, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(1, 3), player1);
        map.addPlayerOnSquare(map.getSquare(0, 1), player2);
        map.addPlayerOnSquare(map.getSquare(2, 1), player3);
        map.addPlayerOnSquare(map.getSquare(1, 0), player4);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        assertEquals(0, player2.getPlayerBoard().getDamages().size());
        assertEquals(0, player3.getPlayerBoard().getDamages().size());
        assertEquals(0, player4.getPlayerBoard().getDamages().size());
        railgun.baseMode(map,currentPlayer,player1,EnumCardinalDirection.E);
        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertEquals(0, player2.getPlayerBoard().getDamages().size());
        assertEquals(0, player3.getPlayerBoard().getDamages().size());
        assertEquals(0, player4.getPlayerBoard().getDamages().size());
        railgun.baseMode(map,currentPlayer,player2,EnumCardinalDirection.N);
        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertEquals(3, player2.getPlayerBoard().getDamages().size());
        assertEquals(0, player3.getPlayerBoard().getDamages().size());
        assertEquals(0, player4.getPlayerBoard().getDamages().size());
        railgun.baseMode(map,currentPlayer,player3,EnumCardinalDirection.S);
        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertEquals(3, player2.getPlayerBoard().getDamages().size());
        assertEquals(3, player3.getPlayerBoard().getDamages().size());
        assertEquals(0, player4.getPlayerBoard().getDamages().size());
        railgun.baseMode(map,currentPlayer,player4,EnumCardinalDirection.W);
        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertEquals(3, player2.getPlayerBoard().getDamages().size());
        assertEquals(3, player3.getPlayerBoard().getDamages().size());
        assertEquals(3, player4.getPlayerBoard().getDamages().size());
        assertThrows(NotInDirection.class, () -> {

            railgun.baseMode(map,currentPlayer,player1,EnumCardinalDirection.N);

        });
        assertThrows(NotInDirection.class, () -> {

            railgun.baseMode(map,currentPlayer,player2,EnumCardinalDirection.E);

        });
        assertThrows(NotInDirection.class, () -> {

            railgun.baseMode(map,currentPlayer,player3,EnumCardinalDirection.W);

        });
        assertThrows(NotInDirection.class, () -> {

            railgun.baseMode(map,currentPlayer,player4,EnumCardinalDirection.S);

        });

        assertEquals(3, player1.getPlayerBoard().getDamages().size());
        assertEquals(3, player2.getPlayerBoard().getDamages().size());
        assertEquals(3, player3.getPlayerBoard().getDamages().size());
        assertEquals(3, player4.getPlayerBoard().getDamages().size());
    }

    @Test

    public void piercingModeTest() throws MapException, NotInDirection, NotValidCardinalDirection {

        map.addPlayerOnSquare(map.getSquare(1, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(1, 2), player1);
        map.addPlayerOnSquare(map.getSquare(1, 3), player2);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        assertEquals(0, player2.getPlayerBoard().getDamages().size());
        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        railgun.piercingMode(map,currentPlayer,players,EnumCardinalDirection.E);
        assertEquals(2, player1.getPlayerBoard().getDamages().size());
        assertEquals(2, player2.getPlayerBoard().getDamages().size());
        map.movePlayer(player2,map.getSquare(2,3));
        assertThrows(NotInDirection.class, () -> {

            railgun.piercingMode(map,currentPlayer,players,EnumCardinalDirection.E);

        });
        assertEquals(2, player1.getPlayerBoard().getDamages().size());
        assertEquals(2, player2.getPlayerBoard().getDamages().size());
        map.movePlayer(currentPlayer,map.getSquare(1,2));
        map.movePlayer(player1,map.getSquare(1,0));
        map.movePlayer(player2,map.getSquare(1,1));
        railgun.piercingMode(map,currentPlayer,players,EnumCardinalDirection.W);
        assertEquals(4, player1.getPlayerBoard().getDamages().size());
        assertEquals(4, player2.getPlayerBoard().getDamages().size());
        map.movePlayer(player2,map.getSquare(2,0));
        assertThrows(NotInDirection.class, () -> {

            railgun.piercingMode(map,currentPlayer,players,EnumCardinalDirection.W);

        });
        assertEquals(4, player1.getPlayerBoard().getDamages().size());
        assertEquals(4, player2.getPlayerBoard().getDamages().size());
        map.movePlayer(currentPlayer,map.getSquare(2,1));
        map.movePlayer(player1,map.getSquare(1,1));
        map.movePlayer(player2,map.getSquare(0,1));
        railgun.piercingMode(map,currentPlayer,players,EnumCardinalDirection.N);
        assertEquals(6, player1.getPlayerBoard().getDamages().size());
        assertEquals(6, player2.getPlayerBoard().getDamages().size());
        map.movePlayer(player1,map.getSquare(0,2));
        assertThrows(NotInDirection.class, () -> {

            railgun.piercingMode(map,currentPlayer,players,EnumCardinalDirection.N);

        });
        assertEquals(6, player1.getPlayerBoard().getDamages().size());
        assertEquals(6, player2.getPlayerBoard().getDamages().size());
        map.movePlayer(currentPlayer,map.getSquare(0,0));
        map.movePlayer(player1,map.getSquare(1,0));
        map.movePlayer(player2,map.getSquare(2,0));
        railgun.piercingMode(map,currentPlayer,players,EnumCardinalDirection.S);
        assertEquals(8, player1.getPlayerBoard().getDamages().size());
        assertEquals(8, player2.getPlayerBoard().getDamages().size());
        map.movePlayer(player1,map.getSquare(0,2));
        assertThrows(NotInDirection.class, () -> {

            railgun.piercingMode(map,currentPlayer,players,EnumCardinalDirection.S);

        });
        assertEquals(8, player1.getPlayerBoard().getDamages().size());
        assertEquals(8, player2.getPlayerBoard().getDamages().size());











    }
}
