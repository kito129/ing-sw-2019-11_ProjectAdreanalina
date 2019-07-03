package it.polimi.model.PowerUp;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Weapon.Cyberblade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewtonTest {

    Newton newton;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        newton=new Newton(EnumColorCardAndAmmo.BLU);
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

   @Test

   public void effectTest() throws MapException, NotInSameDirection, NotValidDistance {

       map.addPlayerOnSquare(map.getSquare(0, 0), player1);
       assertTrue(player1.getRow()==0&&player1.getColumn()==0);
       newton.effect(map,map.getSquare(2,0),player1);
       assertTrue(player1.getRow()==2&&player1.getColumn()==0);
       assertThrows(NotInSameDirection.class, () -> {

           newton.effect(map,map.getSquare(0,1),player1);
       });
       assertTrue(player1.getRow()==2&&player1.getColumn()==0);
       assertThrows(NotValidDistance.class, () -> {

           newton.effect(map,map.getSquare(2,0),player1);
       });
       assertTrue(player1.getRow()==2&&player1.getColumn()==0);
       assertThrows(NotInSameDirection.class, () -> {

           newton.effect(map,map.getSquare(1,1),player1);
       });
       assertTrue(player1.getRow()==2&&player1.getColumn()==0);






   }
}
