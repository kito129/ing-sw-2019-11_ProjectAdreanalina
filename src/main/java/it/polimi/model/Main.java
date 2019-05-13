package it.polimi.model;

import it.polimi.model.PowerUp.Newton;
import it.polimi.model.Weapon.Cyberblade;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        PlayerBoard playerBoard= new PlayerBoard();

        System.out.println(playerBoard.getAmmo());
        System.out.println(playerBoard.getAmmoR());
        System.out.println(playerBoard.getAmmoB());
        System.out.println(playerBoard.getAmmoY());
        System.out.println(playerBoard.getBoardValue());
        System.out.println(playerBoard.getNumberOfDeaths());
        System.out.println(playerBoard.getDamages());
        System.out.println(playerBoard.getMarks());
        System.out.println(playerBoard.getPlayerPowerUps());
        System.out.println(playerBoard.getPlayerWeapons());
        System.out.println();





        ArrayList<EnumColorPlayer> damages =new ArrayList<>();
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.BLU);
        damages.add(EnumColorPlayer.PINK);
        damages.add(EnumColorPlayer.PINK);
        damages.add(EnumColorPlayer.PINK);
        damages.add(EnumColorPlayer.PINK);

        playerBoard.increaseDamages(damages);

        System.out.println(playerBoard.getAmmo());
        System.out.println(playerBoard.getAmmoR());
        System.out.println(playerBoard.getAmmoB());
        System.out.println(playerBoard.getAmmoY());
        System.out.println(playerBoard.getBoardValue());
        System.out.println(playerBoard.getNumberOfDeaths());
        System.out.println(playerBoard.getDamages());
        System.out.println(playerBoard.getMarks());
        System.out.println(playerBoard.getPlayerPowerUps());
        System.out.println(playerBoard.getPlayerWeapons());
        System.out.println();

        playerBoard.increaseMarks(EnumColorPlayer.BLU);
        playerBoard.increaseMarks(EnumColorPlayer.BLU);
        playerBoard.increaseMarks(EnumColorPlayer.BLU);
        playerBoard.increaseMarks(EnumColorPlayer.BLU);

        System.out.println(playerBoard.getAmmo());
        System.out.println(playerBoard.getAmmoR());
        System.out.println(playerBoard.getAmmoB());
        System.out.println(playerBoard.getAmmoY());
        System.out.println(playerBoard.getBoardValue());
        System.out.println(playerBoard.getNumberOfDeaths());
        System.out.println(playerBoard.getDamages());
        System.out.println(playerBoard.getMarks());
        System.out.println(playerBoard.getPlayerPowerUps());
        System.out.println(playerBoard.getPlayerWeapons());
        System.out.println();

        playerBoard.shiftMarks(EnumColorPlayer.BLU);
        System.out.println(playerBoard.getAmmo());
        System.out.println(playerBoard.getAmmoR());
        System.out.println(playerBoard.getAmmoB());
        System.out.println(playerBoard.getAmmoY());
        System.out.println(playerBoard.getBoardValue());
        System.out.println(playerBoard.getNumberOfDeaths());
        System.out.println(playerBoard.getDamages());
        System.out.println(playerBoard.getMarks());
        System.out.println(playerBoard.getPlayerPowerUps());
        System.out.println(playerBoard.getPlayerWeapons());
        System.out.println();





 // todo testare le funzioni di player che incassano danno


    }

}