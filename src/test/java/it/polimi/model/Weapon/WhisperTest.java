package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WhisperTest {

    Whisper whisper;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        whisper=new Whisper();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }


    @Test

    public void baseEffectTest() throws MapException, NotVisibleTarget, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 2), player1);
        assertEquals(0,player1.getPlayerBoard().getDamages().size());
        assertEquals(0,player1.getPlayerBoard().getMarks().size());
        whisper.baseEffect(map,currentPlayer,player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player1.getPlayerBoard().getMarks().size()==1&&
                player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(2,0));
        assertThrows(NotVisibleTarget.class, () -> {

            whisper.baseEffect(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player1.getPlayerBoard().getMarks().size()==1&&
                player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(0,1));
        assertThrows(NotValidDistance.class, () -> {

            whisper.baseEffect(map, currentPlayer, player1);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player1.getPlayerBoard().getMarks().size()==1&&
                player1.getPlayerBoard().getMarks().contains(currentPlayer.getColor()));






    }

}
