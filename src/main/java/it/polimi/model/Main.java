package it.polimi.model;


import it.polimi.model.PowerUp.Newton;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {

        ArrayList<Player> players=new ArrayList<>();
        Player p1=new Player(1,"ANDREA",EnumColorPlayer.BLU);
        Player p2=new Player(2,"MARCO",EnumColorPlayer.PINK);
        Player p3=new Player(3,"SIMO",EnumColorPlayer.YELLOW);
        players.add(p1);
        players.add(p2);
        players.add(p3);

        ArrayList<EnumColorPlayer> ANDREADANNI=new ArrayList<>();
        ANDREADANNI.add(EnumColorPlayer.BLU);
        ANDREADANNI.add(EnumColorPlayer.BLU);
        ANDREADANNI.add(EnumColorPlayer.BLU);

        ArrayList<EnumColorPlayer> ANDREAMARCHI=new ArrayList<>();
        ANDREAMARCHI.add(EnumColorPlayer.BLU);

        ArrayList<EnumColorPlayer> SIMONEDANNI=new ArrayList<>();
        SIMONEDANNI.add(EnumColorPlayer.YELLOW);
        SIMONEDANNI.add(EnumColorPlayer.YELLOW);
        SIMONEDANNI.add(EnumColorPlayer.YELLOW);
        ArrayList<EnumColorPlayer> SIMONEMARCHI=new ArrayList<>();
        SIMONEMARCHI.add(EnumColorPlayer.YELLOW);
        SIMONEMARCHI.add(EnumColorPlayer.YELLOW);

        ArrayList<EnumColorPlayer> MARCODANNI=new ArrayList<>();
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);
        MARCODANNI.add(EnumColorPlayer.PINK);

        ArrayList<EnumColorPlayer> MARCOMARCHI=new ArrayList<>();
        MARCOMARCHI.add(EnumColorPlayer.PINK);
        MARCOMARCHI.add(EnumColorPlayer.PINK);



        for(Player p:players){

            p.stampa();
        }
        System.out.println();
        System.out.println();
        System.out.println();

        p3.multipleDamages(MARCODANNI);
        p3.multipleDamages(MARCODANNI);




        for(Player p:players){

            p.stampa();
        }
        System.out.println();
        System.out.println();
        System.out.println();






















    }

}