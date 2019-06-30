package it.polimi.model;

import it.polimi.model.PowerUp.Newton;
import it.polimi.model.Weapon.LockRifle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerBoardTest {

    PlayerBoard playerBoard;


    @Before

    public void setUp(){

        playerBoard=new PlayerBoard();
    }

    @Test

    public void getAmmoTest(){

        assertTrue(playerBoard.getAmmo().size()==3&&
                playerBoard.getAmmo().contains(EnumColorCardAndAmmo.BLU)&&playerBoard.getAmmo().contains(EnumColorCardAndAmmo.RED)
        &&playerBoard.getAmmo().contains(EnumColorCardAndAmmo.YELLOW));
    }

    @Test

    public void getAmmoYBRTest(){

        assertTrue(playerBoard.getAmmoY().contains(EnumColorCardAndAmmo.YELLOW)&& !playerBoard.getAmmoY().contains(EnumColorCardAndAmmo.BLU)
        &&!playerBoard.getAmmoY().contains(EnumColorCardAndAmmo.RED));
        assertTrue(playerBoard.getAmmoB().contains(EnumColorCardAndAmmo.BLU)&&!playerBoard.getAmmoB().contains(EnumColorCardAndAmmo.YELLOW)
        &&!playerBoard.getAmmoB().contains(EnumColorCardAndAmmo.RED));
        assertTrue(playerBoard.getAmmoR().contains(EnumColorCardAndAmmo.RED)&&!playerBoard.getAmmoR().contains(EnumColorCardAndAmmo.BLU)
        &&!playerBoard.getAmmoR().contains(EnumColorCardAndAmmo.YELLOW));
    }

    @Test

    public void getBoardValueTest(){

        assertTrue(playerBoard.getBoardValue()==8);
    }

    @Test

    public void manageAmmoCardTest(){

        AmmoCard ammoCard=new AmmoCard(4,5,6,false);
        playerBoard.manageAmmoCard(ammoCard);
        assertTrue(playerBoard.getAmmoY().size()==3&&playerBoard.getAmmoB().size()==3&&playerBoard.getAmmoR().size()==3
        &&playerBoard.getPlayerPowerUps().size()==0);
        AmmoCard ammoCard1=new AmmoCard(0,0,0,true);
        Newton newton=new Newton(EnumColorCardAndAmmo.BLU);
        ammoCard1.setPowerUpCard(newton);
        playerBoard.manageAmmoCard(ammoCard1);
        assertTrue(playerBoard.getPlayerPowerUps().contains(newton));
    }

    @Test

    public void decreaseAmmoTest(){

        playerBoard.decreaseAmmo(EnumColorCardAndAmmo.BLU);
        assertEquals(2, playerBoard.getAmmo().size());
        assertFalse(playerBoard.getAmmo().contains(EnumColorCardAndAmmo.BLU));

    }
    @Test

    public void decreaseAmmoTest2(){

        ArrayList<EnumColorCardAndAmmo>ammoToDecrease=new ArrayList<>();
        ammoToDecrease.add(EnumColorCardAndAmmo.BLU);
        ammoToDecrease.add(EnumColorCardAndAmmo.YELLOW);
        ammoToDecrease.add(EnumColorCardAndAmmo.RED);
        playerBoard.decreaseAmmo(ammoToDecrease);
        assertTrue(playerBoard.getAmmoY().size()==0&playerBoard.getAmmoB().size()==0&&playerBoard.getAmmoR().size()==0);
    }

    @Test

    public void decreaseBoardValueTest(){

        playerBoard.decreaseBoardValue();
        assertEquals(6,playerBoard.getBoardValue());
        playerBoard.decreaseBoardValue();
        assertEquals(4,playerBoard.getBoardValue());
        playerBoard.decreaseBoardValue();
        assertEquals(2,playerBoard.getBoardValue());
        playerBoard.decreaseBoardValue();
        assertEquals(1,playerBoard.getBoardValue());
        playerBoard.decreaseBoardValue();
        assertEquals(1,playerBoard.getBoardValue());
    }

    @Test


    public void removePowerUpTest(){

        PowerUpCard newton=new Newton(EnumColorCardAndAmmo.BLU);
        playerBoard.addPowerUp(newton);
        assertEquals(1, playerBoard.getPlayerPowerUps().size());
        playerBoard.removePowerUp(newton);
        assertEquals(0, playerBoard.getPlayerPowerUps().size());
    }

    @Test

    public void addRemoveWeaponTest(){

        LockRifle lockRifle=new LockRifle();
        assertEquals(0,playerBoard.getPlayerWeapons().size());
        playerBoard.addWeapon(lockRifle);
        assertTrue(playerBoard.getPlayerWeapons().size()==1&&playerBoard.getPlayerWeapons().contains(lockRifle));
        playerBoard.removeWeapon(lockRifle);
        assertTrue(playerBoard.getPlayerWeapons().size()==0&&!playerBoard.getPlayerWeapons().contains(lockRifle));
    }

    @Test

    public void increaseDamageTest(){

        EnumColorPlayer colorPlayer=EnumColorPlayer.BLU;

        for(int i=0;i<12;i++){

            playerBoard.increaseDamages(colorPlayer);
            assertEquals(i+1,playerBoard.getDamages().size());
        }
        assertTrue(playerBoard.getDamages().size()==12&&playerBoard.getDamages().contains(EnumColorPlayer.BLU));
        playerBoard.increaseDamages(colorPlayer);
        playerBoard.increaseDamages(colorPlayer);
        playerBoard.increaseDamages(colorPlayer);
        assertEquals(12,playerBoard.getDamages().size());
    }

    @Test

    public void increaseDamageTest2(){

        ArrayList<EnumColorPlayer> damages=new ArrayList<>();
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.BLU);
        playerBoard.increaseDamages(damages);
        assertEquals(3,playerBoard.getDamages().size());
        assertTrue(playerBoard.getDamages().contains(EnumColorPlayer.BLU)&&!playerBoard.getDamages().contains(EnumColorPlayer.YELLOW));
        ArrayList<EnumColorPlayer> damages2=new ArrayList<>();
        damages2.add(EnumColorPlayer.YELLOW);
        damages2.add(EnumColorPlayer.YELLOW);
        damages2.add(EnumColorPlayer.YELLOW);
        damages2.add(EnumColorPlayer.YELLOW);
        damages2.add(EnumColorPlayer.YELLOW);
        damages2.add(EnumColorPlayer.PINK);
        damages2.add(EnumColorPlayer.PINK);
        damages2.add(EnumColorPlayer.PINK);
        damages2.add(EnumColorPlayer.GREY);
        playerBoard.increaseDamages(damages2);
        assertEquals(12,playerBoard.getDamages().size());
        assertTrue(playerBoard.getDamages().contains(EnumColorPlayer.BLU)&&playerBoard.getDamages().contains(EnumColorPlayer.YELLOW)
        &&playerBoard.getDamages().contains(EnumColorPlayer.PINK)&&playerBoard.getDamages().contains(EnumColorPlayer.GREY));
        ArrayList<EnumColorPlayer>damages3=new ArrayList<>();
        damages.add(EnumColorPlayer.GREEN);
        playerBoard.increaseDamages(damages3);
        assertTrue(!playerBoard.getDamages().contains(EnumColorPlayer.GREEN)&&playerBoard.getDamages().size()==12);
    }

    @Test


    public void resetDamageTest(){


        playerBoard.increaseDamages(EnumColorPlayer.BLU);
        assertEquals(1,playerBoard.getDamages().size());
        playerBoard.resetDamage();
        assertEquals(0,playerBoard.getDamages().size());
    }

    @Test

    public void increaseMarksTest(){

        assertEquals(0, playerBoard.getMarks().size());
        playerBoard.increaseMarks(EnumColorPlayer.BLU);
        playerBoard.increaseMarks(EnumColorPlayer.BLU);
        playerBoard.increaseMarks(EnumColorPlayer.BLU);
        assertTrue(playerBoard.getMarks().size()==3&&playerBoard.getMarks().contains(EnumColorPlayer.BLU));
        playerBoard.increaseMarks(EnumColorPlayer.BLU);
        assertEquals(3,playerBoard.getMarks().size());
        playerBoard.increaseMarks(EnumColorPlayer.PINK);
        assertEquals(4,playerBoard.getMarks().size());
        playerBoard.increaseMarks(EnumColorPlayer.PINK);
        playerBoard.increaseMarks(EnumColorPlayer.PINK);
        playerBoard.increaseMarks(EnumColorPlayer.PINK);
        assertEquals(6,playerBoard.getMarks().size());
        assertTrue(playerBoard.getMarks().contains(EnumColorPlayer.PINK));

    }

    @Test


    public void increaseMarksTest2(){

        ArrayList<EnumColorPlayer>marks=new ArrayList<>();
        marks.add(EnumColorPlayer.YELLOW);
        marks.add(EnumColorPlayer.YELLOW);
        marks.add(EnumColorPlayer.YELLOW);
        marks.add(EnumColorPlayer.YELLOW);
        playerBoard.increaseMarks(marks);
        assertEquals(3,playerBoard.getMarks().size());
        assertTrue(playerBoard.getMarks().contains(EnumColorPlayer.YELLOW)&& !playerBoard.getMarks().contains(EnumColorPlayer.BLU));
        ArrayList<EnumColorPlayer> marks2=new ArrayList<>();
        marks2.add(EnumColorPlayer.BLU);
        marks2.add(EnumColorPlayer.BLU);
        playerBoard.increaseMarks(marks2);
        assertEquals(5,playerBoard.getMarks().size());
        playerBoard.increaseMarks(marks2);
        assertEquals(6,playerBoard.getMarks().size());


    }

    //ripartire da qui




}
