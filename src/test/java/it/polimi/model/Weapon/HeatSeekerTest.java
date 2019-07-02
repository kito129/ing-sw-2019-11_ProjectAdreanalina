package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NoTargetInSquare;
import it.polimi.model.Exception.VisibleTarget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeatSeekerTest {

    Heatseeker heatseeker;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;
    Player player3;

    @Before

    public void setUp() {

        gameModel = new GameModel();
        heatseeker=new Heatseeker();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3 = new Player(3, "NIKO", EnumColorPlayer.GREY, gameModel);
    }

    @Test

    public void BaseEffect() throws MapException, VisibleTarget {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(2, 0), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        heatseeker.BaseEffect(map,currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(1,0));
        assertThrows(VisibleTarget.class, () -> {
            heatseeker.BaseEffect(map,currentPlayer,player1);
            assertTrue(player1.getPlayerBoard().getDamages().size()==1&&
                    player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        });


    }

}
