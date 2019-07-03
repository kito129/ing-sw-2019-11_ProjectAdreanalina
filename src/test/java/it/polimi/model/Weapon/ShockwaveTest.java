package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShockwaveTest {

    Shockwave shockwave;
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
        shockwave=new Shockwave();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3=new Player(4,"NIKO",EnumColorPlayer.GREY,gameModel);
        player4=new Player(5,"TEO",EnumColorPlayer.GREEN,gameModel);


    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.YELLOW,shockwave.getTsunamiModeCost().get(0));
    }

    @Test

    public void baseModeTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(1, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        shockwave.baseMode(map,currentPlayer,player1);
        assertEquals(1,player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(2,2));
        assertThrows(NotValidDistance.class, () -> {

            shockwave.baseMode(map,currentPlayer,player1);

        });
        assertEquals(1,player1.getPlayerBoard().getDamages().size());
        assertTrue(player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
    }

    @Test

    public void baseModeTest2() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(1, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        map.addPlayerOnSquare(map.getSquare(2,1),player2);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&player2.getPlayerBoard().getDamages()
        .size()==0);
        shockwave.baseMode(map,currentPlayer,player1,player2);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&player2.getPlayerBoard().getDamages()
        .size()==1);
        map.movePlayer(player2,map.getSquare(0,1));
        assertThrows(NotValidDistance.class, () -> {

            shockwave.baseMode(map,currentPlayer,player1,player2);

        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&player2.getPlayerBoard().getDamages()
                .size()==1);
    }

    @Test

    public void baseModeTest3() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(1, 2), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 2), player1);
        map.addPlayerOnSquare(map.getSquare(1,3),player2);
        map.addPlayerOnSquare(map.getSquare(2,2),player3);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&player2.getPlayerBoard().getDamages()
                .size()==0&&player3.getPlayerBoard().getDamages().size()==0);
        shockwave.baseMode(map,currentPlayer,player1,player2,player3);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&player2.getPlayerBoard().getDamages()
                .size()==1&&player3.getPlayerBoard().getDamages().size()==1);
        map.movePlayer(player3,map.getSquare(1,3));
        assertThrows(NotValidDistance.class, () -> {

            shockwave.baseMode(map,currentPlayer,player1,player2,player3);

        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&player2.getPlayerBoard().getDamages()
                .size()==1&&player3.getPlayerBoard().getDamages().size()==1);

    }

    @Test

    public void tsunamiModeTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(1, 1), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 1), player1);
        map.addPlayerOnSquare(map.getSquare(2,1),player2);
        map.addPlayerOnSquare(map.getSquare(0,1),player3);
        map.addPlayerOnSquare(map.getSquare(1,2),player4);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&
                player2.getPlayerBoard().getDamages().size()==0&&
                player3.getPlayerBoard().getDamages().size()==0&&player4.getPlayerBoard().getDamages().size()==0&&
                currentPlayer.getPlayerBoard().getDamages().size()==0);
        ArrayList<Player>players=new ArrayList<>();
        players.add(player1);
        players.add(currentPlayer);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        shockwave.tsunamiMode(map,currentPlayer,players);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player2.getPlayerBoard().getDamages().size()==1&&
                player3.getPlayerBoard().getDamages().size()==1&&player4.getPlayerBoard().getDamages().size()==0&&
                currentPlayer.getPlayerBoard().getDamages().size()==0);
        map.movePlayer(currentPlayer,map.getSquare(2,3));
        assertThrows(NotValidDistance.class, () -> {

            shockwave.tsunamiMode(map,currentPlayer,players);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                player2.getPlayerBoard().getDamages().size()==1&&
                player3.getPlayerBoard().getDamages().size()==1&&player4.getPlayerBoard().getDamages().size()==0&&
                currentPlayer.getPlayerBoard().getDamages().size()==0);







    }

}
