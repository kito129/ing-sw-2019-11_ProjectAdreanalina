package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NoTargetInSquare;
import it.polimi.model.Exception.NotValidDistance;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ElectroscytheTest {

    Electroscythe electroscythe;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;
    Player player3;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        electroscythe =new Electroscythe();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3 = new Player(3, "NIKO", EnumColorPlayer.GREY, gameModel);
    }

    @Test

    public void getCostEffectTest(){

        assertEquals(EnumColorCardAndAmmo.BLU,electroscythe.getReaperModeCost().get(0));
        assertEquals(EnumColorCardAndAmmo.RED,electroscythe.getReaperModeCost().get(1));

    }

    @Test

    public void baseModeTest() throws MapException, NoTargetInSquare {

        map.addPlayerOnSquare(map.getSquare(2, 2), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 2), player1);
        map.addPlayerOnSquare(map.getSquare(2, 2), player2);
        map.addPlayerOnSquare(map.getSquare(2, 2), player3);
        assertTrue(currentPlayer.getPlayerBoard().getDamages().size() == 0 &&player1.getPlayerBoard().getDamages().size()==0
                &&player2.getPlayerBoard().getDamages().size()==0&&player3.getPlayerBoard().getDamages().size()==0);
        electroscythe.baseMode(map,currentPlayer);
        assertTrue(currentPlayer.getPlayerBoard().getDamages().size() == 0 &&player1.getPlayerBoard().getDamages().size()==1
                &&player2.getPlayerBoard().getDamages().size()==1&&player3.getPlayerBoard().getDamages().size()==1&&
                player1.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU)&&
                player2.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU)&&
                player3.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU));
        map.movePlayer(currentPlayer,map.getSquare(2,3));
        assertThrows(NoTargetInSquare.class, () -> {
            electroscythe.baseMode(map, currentPlayer);
        });
        assertTrue(currentPlayer.getPlayerBoard().getDamages().size() == 0 &&player1.getPlayerBoard().getDamages().size()==1
                &&player2.getPlayerBoard().getDamages().size()==1&&player3.getPlayerBoard().getDamages().size()==1&&
                player1.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU)&&
                player2.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU)&&
                player3.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU));
    }

    @Test

    public void reaperModeTest() throws MapException, NoTargetInSquare {

        map.addPlayerOnSquare(map.getSquare(2, 2), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 2), player1);
        map.addPlayerOnSquare(map.getSquare(2, 2), player2);
        map.addPlayerOnSquare(map.getSquare(2, 2), player3);
        assertTrue(currentPlayer.getPlayerBoard().getDamages().size() == 0 &&player1.getPlayerBoard().getDamages().size()==0
                &&player2.getPlayerBoard().getDamages().size()==0&&player3.getPlayerBoard().getDamages().size()==0);
        electroscythe.reaperMode(map,currentPlayer);
        assertTrue(currentPlayer.getPlayerBoard().getDamages().size() == 0 &&player1.getPlayerBoard().getDamages().size()==2
                &&player2.getPlayerBoard().getDamages().size()==2&&player3.getPlayerBoard().getDamages().size()==2&&
                player1.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU)&&
                player2.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU)&&
                player3.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU));
        map.movePlayer(currentPlayer,map.getSquare(2,3));
        assertThrows(NoTargetInSquare.class, () -> {
            electroscythe.reaperMode(map, currentPlayer);
        });
        assertTrue(currentPlayer.getPlayerBoard().getDamages().size() == 0 &&player1.getPlayerBoard().getDamages().size()==2
                &&player2.getPlayerBoard().getDamages().size()==2&&player3.getPlayerBoard().getDamages().size()==2&&
                player1.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU)&&
                player2.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU)&&
                player3.getPlayerBoard().getDamages().contains(EnumColorPlayer.BLU));
    }



}
