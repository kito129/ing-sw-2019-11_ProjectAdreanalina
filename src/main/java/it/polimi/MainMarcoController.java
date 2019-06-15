package it.polimi;

import it.polimi.controller.ActionController;
import it.polimi.controller.GameController;
import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Weapon.LockRifle;
import it.polimi.view.cli.PrintMap;
import it.polimi.view.cli.ViewCLI;

import java.rmi.RemoteException;
import java.util.ArrayList;


public class MainMarcoController {


    
    public static void main (String args[]) throws RemoteException {

        
    
        System.out.println("creo killshot track" );
        ArrayList<Player> players = new ArrayList<>();
        
        System.out.println("creo player\n");
        Player player1 = new Player(1, "andrea", EnumColorPlayer.YELLOW);
        Player player2 = new Player(2, "marco", EnumColorPlayer.BLU);
        Player player3 = new Player(3, "simone", EnumColorPlayer.GREEN);
        Player player4 = new Player(4, "niko", EnumColorPlayer.PINK);
        Player player5 = new Player(5, "teo", EnumColorPlayer.GREY);
        
        for (Player p : players) {
        
            p.stampa();
        }
    
        System.out.println("creo mappa");
        
        
        //aggiungo player
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        
        //creo oggettti di modello
        System.out.println("creo oggetti modello");
        GameModel gameModel = new GameModel(); //setto lo stato in select run
        gameModel.getPlayers(true).addAll(players);
        
        gameModel.setActualPlayer(player1);
        ActionModel actionModel = new ActionModel(gameModel);
        ActionController actionController = new ActionController();
        GameController gameController = new GameController();
        
    
        //metto oggetti in mappa
        System.out.println("refresh mappa");
        //actionModel.refreshMapAmmoCard();
        //actionModel.refreshMapWeaponCard();
        
        Map map = gameModel.getMap();
    
        System.out.println("Stampa finale");
        map.print();
        
        
        //obesrver
        
        
        
        //piazza i player in mappa
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
    
        ViewCLI viewCLI = new ViewCLI(gameController);
        gameModel.addObserver(viewCLI);
        map.print();
        viewCLI.printMap();
        PrintMap.printMap(gameModel.getMap());
       

        
       /*
        //prova run
    
        try {
            gameModel.setState(State.SELECTRUN);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //chiamo la viees
    

        try {
            viewCLI.update(gameModel);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
      
      

    
        map.print();
        
        //prova grab
        try {
            gameModel.setState(State.SELECTGRAB);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
        

        try {
            viewCLI.update(gameModel);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        

    
    
        */
    
    }
}

