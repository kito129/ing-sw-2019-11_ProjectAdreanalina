package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NoTargetInSquare;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FurnaceTest {

    Furnace furnace;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;
    Player player3;

    @Before

    public void setUp() {

        gameModel = new GameModel();
        furnace=new Furnace();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3 = new Player(3, "NIKO", EnumColorPlayer.GREY, gameModel);
    }

    @Test

    public void getCostEffectTest(){

        assertNull(furnace.getCozyFireModeCost().get(0));
    }

   @Test

   public void baseModeTest() throws MapException, NotVisibleTarget, NotValidDistance {

       map.addPlayerOnSquare(map.getSquare(2, 1), currentPlayer);
       map.addPlayerOnSquare(map.getSquare(1, 2), player1);
       map.addPlayerOnSquare(map.getSquare(1, 3), player2);
       map.addPlayerOnSquare(map.getSquare(2, 2), player3);
       assertTrue(player1.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getDamages().size() == 0
               && player3.getPlayerBoard().getDamages().size() == 0);
       furnace.baseMode(map, currentPlayer, EnumColorSquare.YELLOW);
       assertTrue(player1.getPlayerBoard().getDamages().size() == 1 && player2.getPlayerBoard().getDamages().size() == 1
               && player3.getPlayerBoard().getDamages().size() == 1 &&
               currentPlayer.getPlayerBoard().getDamages().size() == 0 &&
               player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
               player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
               player3.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
       map.movePlayer(currentPlayer, map.getSquare(1, 1));
       assertThrows(NotVisibleTarget.class, () -> {
           furnace.baseMode(map, currentPlayer, EnumColorSquare.YELLOW);
       });
       assertTrue(player1.getPlayerBoard().getDamages().size() == 1 && player2.getPlayerBoard().getDamages().size() == 1
               && player3.getPlayerBoard().getDamages().size() == 1 &&
               currentPlayer.getPlayerBoard().getDamages().size() == 0 &&
               player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
               player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
               player3.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
       map.movePlayer(currentPlayer,map.getSquare(1,2));
       assertThrows(NotValidDistance.class, () -> {
           furnace.baseMode(map, currentPlayer, EnumColorSquare.YELLOW);
       });
       assertTrue(player1.getPlayerBoard().getDamages().size() == 1 && player2.getPlayerBoard().getDamages().size() == 1
               && player3.getPlayerBoard().getDamages().size() == 1 &&
               currentPlayer.getPlayerBoard().getDamages().size() == 0 &&
               player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
               player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor()) &&
               player3.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
   }

   @Test

    public void t() throws MapException {

       map.addPlayerOnSquare(map.getSquare(1, 1), currentPlayer);
       assertFalse(map.isVisibleRoom(currentPlayer,EnumColorSquare.YELLOW));


   }


}
