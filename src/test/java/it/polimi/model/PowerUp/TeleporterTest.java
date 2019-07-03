package it.polimi.model.PowerUp;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeleporterTest {

    Teleporter teleporter;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        teleporter=new Teleporter(EnumColorCardAndAmmo.BLU);
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void effectTest() throws MapException {

        map.addPlayerOnSquare(map.getSquare(0,1),currentPlayer);
        assertEquals(0,currentPlayer.getRow());
        assertEquals(1,currentPlayer.getColumn());
        teleporter.effect(currentPlayer,map,map.getSquare(2,3));
        assertEquals(2,currentPlayer.getRow());
        assertEquals(3,currentPlayer.getColumn());

    }
}
