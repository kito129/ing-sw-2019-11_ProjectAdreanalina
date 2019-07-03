package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RocketLauncherTest {


    RocketLauncher rocketLauncher;
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;
    Player player3;

    @Before

    public void setUp() {

        gameModel = new GameModel();
        rocketLauncher=new RocketLauncher();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
        player3=new Player(4,"NIKO",EnumColorPlayer.GREY,gameModel);

    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.YELLOW,rocketLauncher.getFragmentingWarheadCost().get(0));
        assertEquals(EnumColorCardAndAmmo.BLU,rocketLauncher.getRocketJumpCost().get(0));
    }

    @Test

    public void baseEffectTest() throws MapException, NotVisibleTarget, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&player1.getRow()==0&&
                player1.getColumn()==1);
        rocketLauncher.baseEffect(map,player1,currentPlayer,map.getSquare(0,0));
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&player1.getRow()==0&&
                player1.getColumn()==0&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        assertThrows(NotValidDistance.class, () -> {

            rocketLauncher.baseEffect(map,player1,currentPlayer,map.getSquare(1,0));

        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&player1.getRow()==0&&
                player1.getColumn()==0&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(0,2));
        assertThrows(NotVisibleTarget.class, () -> {

            rocketLauncher.baseEffect(map,player1,currentPlayer,map.getSquare(1,0));

        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&player1.getRow()==0&&
                player1.getColumn()==2&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        map.movePlayer(player1,map.getSquare(0,1));
        assertThrows(NotValidDistance.class, () -> {

            rocketLauncher.baseEffect(map,player1,currentPlayer,map.getSquare(2,3));

        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==2&&player1.getRow()==0&&
                player1.getColumn()==1&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));
        rocketLauncher.baseEffect(map,player1,currentPlayer,null);
        assertTrue(player1.getPlayerBoard().getDamages().size()==4&&player1.getRow()==0&&
                player1.getColumn()==1&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor()));

    }

    @Test

    public void baseEffectWithFragmenting() throws MapException, NotVisibleTarget, NotValidDistance, NoTargetInSquare {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        map.addPlayerOnSquare(map.getSquare(0,1),player2);
        assertTrue(player1.getPlayerBoard().getDamages().size()==0&&player1.getRow()==0&&
                player1.getColumn()==1&&player2.getPlayerBoard().getDamages().size()==0&&
                player2.getRow()==0&&player2.getColumn()==1);
        rocketLauncher.baseEffectWithFragmenting(map,player1,currentPlayer,map.getSquare(0,0));
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&player1.getRow()==0&&
                player1.getColumn()==0&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().size()==1&&player2.getRow()==0&&
                player2.getColumn()==1);
        assertThrows(NotValidDistance.class, () -> {

            rocketLauncher.baseEffectWithFragmenting(map,player1,currentPlayer,map.getSquare(1,0));

        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&player1.getRow()==0&&
                player1.getColumn()==0&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().size()==1);
        map.movePlayer(player1,map.getSquare(0,2));
        assertThrows(NotVisibleTarget.class, () -> {

            rocketLauncher.baseEffectWithFragmenting(map,player1,currentPlayer,map.getSquare(1,0));

        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&player1.getRow()==0&&
                player1.getColumn()==2&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().size()==1);
        map.movePlayer(player1,map.getSquare(0,1));
        assertThrows(NotValidDistance.class, () -> {

            rocketLauncher.baseEffectWithFragmenting(map,player1,currentPlayer,map.getSquare(2,3));

        });
        assertTrue(player1.getPlayerBoard().getDamages().size()==3&&player1.getRow()==0&&
                player1.getColumn()==1&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().size()==1);
        rocketLauncher.baseEffectWithFragmenting(map,player1,currentPlayer,null);
        assertTrue(player1.getPlayerBoard().getDamages().size()==6&&player1.getRow()==0&&
                player1.getColumn()==1&&player1.getPlayerBoard().getDamages().contains(currentPlayer.getColor())&&
                player2.getPlayerBoard().getDamages().size()==2);

    }

    @Test

    public void rocketJumpEffect() throws MapException, NotValidDistance {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==0);
        rocketLauncher.rocketJumpEffect(map,currentPlayer,map.getSquare(0,1));
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==1);
        assertThrows(NotValidDistance.class, () -> {

            rocketLauncher.rocketJumpEffect(map,currentPlayer,map.getSquare(0,1));

        });
        assertTrue(currentPlayer.getRow()==0&&currentPlayer.getColumn()==1);




    }





}



