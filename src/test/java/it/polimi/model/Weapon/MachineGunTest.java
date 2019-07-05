package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MachineGunTest {

    MachineGun machineGun=new MachineGun();
    Map map;
    Player currentPlayer;
    GameModel gameModel;
    Player player1;
    Player player2;


    @Before

    public void setUp() {

        gameModel = new GameModel();
        machineGun=new MachineGun();
        this.map = new Map(MapCreator.createD(), "D");
        currentPlayer = new Player(1, "ANDREA", EnumColorPlayer.BLU, gameModel);
        player1 = new Player(2, "MARCO", EnumColorPlayer.PINK, gameModel);
        player2 = new Player(3, "SIMO", EnumColorPlayer.YELLOW, gameModel);
    }

    @Test

    public void getCostEffectTest() {

        assertEquals(EnumColorCardAndAmmo.YELLOW,machineGun.getFocusShotCost().get(0));
        assertEquals(EnumColorCardAndAmmo.BLU,machineGun.getTurretTripodCost().get(0));
    }

    @Test

    public void baseEffectTest() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 0), player1);
        map.addPlayerOnSquare(map.getSquare(0, 1), player2);
        ArrayList<Player> players=new ArrayList<>();
        players.add(player1);
        players.add(player2);
        machineGun.baseEffect(map,currentPlayer,players);
        assertEquals(1, player1.getPlayerBoard().getDamages().size());
        assertEquals(1, player2.getPlayerBoard().getDamages().size());
        map.movePlayer(player2,map.getSquare(2,2));
        assertThrows(NotVisibleTarget.class, () -> {
            machineGun.baseEffect(map,currentPlayer,players);
        });
        assertEquals(1, player1.getPlayerBoard().getDamages().size());
        assertEquals(1, player2.getPlayerBoard().getDamages().size());

    }

    @Test

    public void focusShotEffectTest() throws MapException {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 0), player1);
        machineGun.focusShotEffect(currentPlayer,player1);
        assertEquals(1,player1.getPlayerBoard().getDamages().size());


    }
    @Test

    public void turretTripodEffectTest() throws MapException {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 0), player1);
        machineGun.turretTripodEffect(currentPlayer,player1);
        assertEquals(1,player1.getPlayerBoard().getDamages().size());
    }

    @Test

    public void turretTripodEffectTest2() throws MapException, NotVisibleTarget {

        map.addPlayerOnSquare(map.getSquare(0, 0), currentPlayer);
        map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        machineGun.turretTripodEffect(map,currentPlayer,player1);
        assertEquals(1,player1.getPlayerBoard().getDamages().size());
        map.movePlayer(player1,map.getSquare(2,2));
        assertThrows(NotVisibleTarget.class, () -> {
            machineGun.turretTripodEffect(map,currentPlayer,player1);
        });
        assertEquals(1,player1.getPlayerBoard().getDamages().size());




    }
}
