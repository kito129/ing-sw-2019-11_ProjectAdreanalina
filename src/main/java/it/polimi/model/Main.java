package it.polimi.model;

import it.polimi.model.PowerUp.Newton;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        PlayerBoard a = new PlayerBoard();
        PowerUpCard powerUpCard = new Newton(EnumColorCard.RED);
        AmmoCard ammoCard = new AmmoCard(1, 1, 1, powerUpCard);
        EnumColorPlayer singleDamage=EnumColorPlayer.YELLOW;
        ArrayList<EnumColorPlayer> multipleDamages= new ArrayList<>();
        multipleDamages.add(EnumColorPlayer.YELLOW);
        multipleDamages.add(EnumColorPlayer.BLU);
        EnumColorPlayer singleMark=EnumColorPlayer.BLU;
        ArrayList<EnumColorPlayer> multipleMarks=new ArrayList<>();
        multipleMarks.add(EnumColorPlayer.GREEN);
        multipleMarks.add(EnumColorPlayer.GREEN);
        ArrayList<EnumColorPlayer> markToAdd=new ArrayList<>();



        System.out.println("ammo gialle "+a.getAmmoY());
        System.out.println("ammo blu "+a.getAmmoB());
        System.out.println("ammo rosse "+a.getAmmoR());
        System.out.println("board value "+a.getBoardValue());
        System.out.println("number of deaths "+a.getNumberOfDeaths());
        System.out.println("damages "+a.getDamages());
        System.out.println("marks "+a.getMarks());
        System.out.println("weapons "+a.getPlayerWeapons());
        System.out.println("powerup "+a.getPlayerPowerUps());
        System.out.println();

        a.increaseDamages(singleDamage);
        a.increaseDamages(multipleDamages);


        System.out.println("ammo gialle "+a.getAmmoY());
        System.out.println("ammo blu "+a.getAmmoB());
        System.out.println("ammo rosse "+a.getAmmoR());
        System.out.println("board value "+a.getBoardValue());
        System.out.println("number of deaths "+a.getNumberOfDeaths());
        System.out.println("damages "+a.getDamages());
        System.out.println("marks "+a.getMarks());
        System.out.println("weapons "+a.getPlayerWeapons());
        System.out.println("powerup "+a.getPlayerPowerUps());
        System.out.println();

        markToAdd=a.removeMarkOfColor(EnumColorPlayer.GREEN);

        System.out.println(markToAdd);

        System.out.println("ammo gialle "+a.getAmmoY());
        System.out.println("ammo blu "+a.getAmmoB());
        System.out.println("ammo rosse "+a.getAmmoR());
        System.out.println("board value "+a.getBoardValue());
        System.out.println("number of deaths "+a.getNumberOfDeaths());
        System.out.println("damages "+a.getDamages());
        System.out.println("marks "+a.getMarks());
        System.out.println("weapons "+a.getPlayerWeapons());
        System.out.println("powerup "+a.getPlayerPowerUps());
        System.out.println();





    }
}
