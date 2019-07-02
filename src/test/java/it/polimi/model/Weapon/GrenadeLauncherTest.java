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

public class GrenadeLauncherTest {

    GrenadeLauncher grenadeLauncher;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;
    Player player3;

    @Before

    public void setUp() {

        gameModel = new GameModel();
        grenadeLauncher=new GrenadeLauncher();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3 = new Player(3, "NIKO", EnumColorPlayer.GREY, gameModel);
    }

    @Test

    public void getCostEffectTest(){

        assertEquals(EnumColorCardAndAmmo.RED,grenadeLauncher.getExtraGrenadeCost().get(0));
    }

    @Test

    public void baseEffectTest() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(1, 0), player1);
        assertEquals(0, player1.getPlayerBoard().getDamages().size());
        grenadeLauncher.baseEffect(map,player1,currentPlayer);
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&player1.getPlayerBoard().getDamages()
        .contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(1,1));
        assertThrows(NotVisibleTarget.class, () -> {
            grenadeLauncher.baseEffect(map,player1,currentPlayer);
        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==1&&player1.getPlayerBoard().getDamages()
                .contains(currentPlayer.getColor()));

    }

    @Test

    public void moveTargetTest() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(1, 0), player1);
        assertTrue(player1.getRow()==1&&player1.getColumn()==0);
        grenadeLauncher.moveTarget(map,player1,map.getSquare(2,0));
        assertTrue(player1.getRow()==2&&player1.getColumn()==0);
        assertThrows(NotValidDistance.class, () -> {
            grenadeLauncher.moveTarget(map,player1,map.getSquare(2,2));
        });
        assertTrue(player1.getRow()==2&&player1.getColumn()==0);

    }

    @Test

    public void extraGrenadeEffectTest() throws MapException, NotVisibleTarget, NoTargetInSquare {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        map.addPlayerOnSquare(map.getSquare(0, 1), player2);
        assertTrue(player1.getPlayerBoard().getDamages().size() == 0 && player2.getPlayerBoard().getDamages().size() == 0);
        grenadeLauncher.extraGrenadeEffect(map,currentPlayer,map.getSquare(0,1));
        assertTrue(player1.getPlayerBoard().getDamages().size() == 1 && player2.getPlayerBoard().getDamages().size() == 1&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                        player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(2,2));
        map.movePlayer(player2,map.getSquare(2,2));
        assertThrows(NoTargetInSquare.class, () -> {
            grenadeLauncher.extraGrenadeEffect(map,currentPlayer,map.getSquare(0,1));
        });
        assertTrue(player1.getPlayerBoard().getDamages().size() == 1 && player2.getPlayerBoard().getDamages().size() == 1&&
                player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertThrows(NotVisibleTarget.class, () -> {
            grenadeLauncher.extraGrenadeEffect(map,currentPlayer,map.getSquare(2,2));
        });

    }
}
