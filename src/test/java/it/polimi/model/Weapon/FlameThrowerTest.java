package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NoTargetInSquare;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlameThrowerTest {


    Flamethrower flamethrower;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;
    Player player3;

    @Before

    public void setUp() {

        gameModel = new GameModel();
        flamethrower=new Flamethrower();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3 = new Player(3, "NIKO", EnumColorPlayer.GREY, gameModel);
    }

    @Test

    public void getCostEffectTest(){

        assertEquals(EnumColorCardAndAmmo.YELLOW,flamethrower.getBarbecueModeCost().get(0));
        assertEquals(EnumColorCardAndAmmo.YELLOW,flamethrower.getBarbecueModeCost().get(1));

    }


    @Test

    public void baseModeTest() throws MapException, NotInSameDirection, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        map.addPlayerOnSquare(map.getSquare(0, 2), player2);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&player2.getPlayerBoard().getDamages().size()==0);
        flamethrower.baseMode(map,currentPlayer,player1,player2);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&player2.getPlayerBoard().getDamages().size()==1);
        map.movePlayer(player2,map.getSquare(1,0));
        assertThrows(NotValidDistance.class, () -> {
            flamethrower.baseMode(map, currentPlayer,player1,player2);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&player2.getPlayerBoard().getDamages().size()==1);
        map.movePlayer(player2,map.getSquare(1,1));
        assertThrows(NotInSameDirection.class, () -> {
            flamethrower.baseMode(map, currentPlayer,player1,player2);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&player2.getPlayerBoard().getDamages().size()==1);
    }

    @Test

    public void baseMode1TargetTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        flamethrower.baseMode(map,currentPlayer,player1);
        assertEquals(1, player1.getPlayerBoard().getDamages().size());
        map.movePlayer(player1,map.getSquare(2,0));
        assertThrows(NotValidDistance.class, () -> {
            flamethrower.baseMode(map, currentPlayer,player1);
        });
        assertEquals(1, player1.getPlayerBoard().getDamages().size());
    }

    @Test

    public void barbecueModeTest() throws MapException, NotValidDistance, NoTargetInSquare, NotInSameDirection {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        map.addPlayerOnSquare(map.getSquare(0, 2), player2);
        map.addPlayerOnSquare(map.getSquare(0, 1), player3);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&player2.getPlayerBoard().getDamages().size()==0
        &&player3.getPlayerBoard().getDamages().size()==0);
        flamethrower.barbecueMode(map,currentPlayer,map.getSquare(0,1),map.getSquare(0,2));
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&
                player2.getPlayerBoard().getDamages().size()==1&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player3.getPlayerBoard().getDamages().size()==2&&player3.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player2,map.getSquare(1,2));
        assertThrows(NoTargetInSquare.class, () -> {

            flamethrower.barbecueMode(map,currentPlayer,map.getSquare(0,1),map.getSquare(0,2));
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&
                player2.getPlayerBoard().getDamages().size()==1&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player3.getPlayerBoard().getDamages().size()==2&&player3.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player2,map.getSquare(1,1));
        assertThrows(NotInSameDirection.class, () -> {

            flamethrower.barbecueMode(map,currentPlayer,map.getSquare(0,1),map.getSquare(1,1));
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&
                player2.getPlayerBoard().getDamages().size()==1&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player3.getPlayerBoard().getDamages().size()==2&&player3.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player2,map.getSquare(1,0));
        assertThrows(NotValidDistance.class, () -> {

            flamethrower.barbecueMode(map,currentPlayer,map.getSquare(0,1),map.getSquare(1,0));
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&
                player2.getPlayerBoard().getDamages().size()==1&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player3.getPlayerBoard().getDamages().size()==2&&player3.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));




    }
}
