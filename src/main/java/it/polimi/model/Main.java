package it.polimi.model;



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


        PowerUpCard powerUpCard= new PowerUpCard("prova",EnumColorCardAndAmmo.BLU);





        AmmoCard ammoCard=new AmmoCard(1,1,1,powerUpCard);












    }

}