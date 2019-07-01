package it.polimi.model;


import it.polimi.model.Exception.*;
import it.polimi.model.Weapon.LockRifle;


import java.util.ArrayList;


public class MainPartita {

    public static void main(String args[]) {


        /*
        Player player1 = new Player(1, "andrea", EnumColorPlayer.YELLOW);
        Player player2 = new Player(2, "marco", EnumColorPlayer.BLU);
        Player player3 = new Player(3, "simone", EnumColorPlayer.GREEN);
        Player player4 = new Player(4, "niko", EnumColorPlayer.PINK);
        Player player5 = new Player(5, "teo", EnumColorPlayer.GREY);


        Map map = new Map(MapCreator.createB(), "mappa a");
        map.printList();


        KillShotTrack killShotTrack = new KillShotTrack();
        ArrayList<Player> players = new ArrayList<>();

        GameModel gameModel = new GameModel(map, killShotTrack, players);
        FunctionModel actionModel = new FunctionModel(gameModel);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);


        for (Player p : players) {

            p.stampa();
        }


        try {
            map.addPlayerOnSquare(map.getSquare(0, 1), player1);
        } catch (MapException e) {
            System.out.println("error");
        }
        try {
            map.addPlayerOnSquare(map.getSquare(0, 2), player2);
        } catch (MapException e) {
            System.out.println("error");
        }
        try {
            map.addPlayerOnSquare(map.getSquare(1, 2), player3);
        } catch (MapException e) {
            System.out.println("error");
        }
        try {
            map.addPlayerOnSquare(map.getSquare(1, 2), player4);
        } catch (MapException e) {
            System.out.println("error");
        }
        try {
            map.addPlayerOnSquare(map.getSquare(1, 3), player5);
        } catch (MapException e) {
            System.out.println("error");
        }

        for (Player p : players) {

            p.stampa();
        }

        map.printList();


        System.out.println(map.isVisible(player1, player2));

        //spara
        LockRifle lockRifle = new LockRifle();
        player1.getPlayerBoard().addWeapon(lockRifle);
        
        try {

            lockRifle.baseEffect(map, player1, player2);

        } catch (NotVisibleTarget notVisibleTarget) {

            System.out.println("non visibile");
        }

        //stampa player
        for (Player p : players) {

            p.stampa();
        }
        //spara

        try {

            lockRifle.baseEffect(map, player1, player2);

        } catch (NotVisibleTarget notVisibleTarget) {

            System.out.println("non visibile");
        }

        //stampa player
        for (Player p : players) {

            p.stampa();
        }
        //spara

        try {

            lockRifle.baseEffect(map, player1, player2);

        } catch (NotVisibleTarget notVisibleTarget) {

            System.out.println("non visibile");
        }
        //stampa player
        for (Player p : players) {

            p.stampa();
        }

        //spara

        try {

            lockRifle.baseEffect(map, player1, player2);

        } catch (NotVisibleTarget notVisibleTarget) {

            System.out.println("non visibile");
        }
        //stampa player
        for (Player p : players) {

            p.stampa();
        }

        //spara

        try {

            lockRifle.baseEffect(map, player1, player2);

        } catch (NotVisibleTarget notVisibleTarget) {

            System.out.println("non visibile");
        }
        //stampa player
        for (Player p : players) {

            p.stampa();
        }
        
        actionModel.scoringPlayerBoard(player2);
        //stampa player
        for (Player p : players) {
        
            p.stampa();
        }
        
        System.out.println("\n\nFINE\n\n");
        
        
        
         */
        
    
    }
}

