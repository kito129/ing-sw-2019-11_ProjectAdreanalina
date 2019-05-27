package it.polimi.model;

import it.polimi.controller.ActionController;
import it.polimi.controller.ManagerController;
import it.polimi.model.Exception.MapException;
import it.polimi.view.cli.ViewCLI;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class MainMarcoController {
    
    public static void main (String args[]) {
    
        System.out.println("creo killshot track" );
        KillShotTrack killShotTrack = new KillShotTrack();
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
        Map map = new Map(MapCreator.createB(), "mappa B");
        map.print();
        
        //aggiungo player
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        
        //creo oggettti di modello
        System.out.println("creo oggetti modello");
        GameModel gameModel = new GameModel(map, killShotTrack, players); //setto lo stato in select run
        
        
        gameModel.setActualPlayer(player1);
        ActionModel actionModel = new ActionModel(gameModel);
        ActionController actionController = new ActionController();
        ManagerController managerController = new ManagerController(actionController,actionModel);
        ViewCLI viewCLI = new ViewCLI(managerController);
    
        //metto oggetti in mappa
        System.out.println("refresh mappa");
        actionModel.refreshMapAmmoCard();
        actionModel.refreshMapWeaponCard();
    
        System.out.println("Stampa finale");
        map.print();
        
        
        //obesrver
        
        gameModel.addObserver(viewCLI);
        
        
        
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

        try {
            gameModel.setState(State.SHOOT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            viewCLI.update(gameModel);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        map.print();
        for (Player p : players) {

            p.stampa();
        }

        
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
        
      */
      

    
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
        

        
        
        map.print();
        for (Player p : players) {
        
            p.stampa();
        }
        */
        
    
    


       /*

        
       
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
        
        map.print();
        
        
        //test powerup
        PowerUpCard powerUp = gameModel.getPowerUpDeck().drawnPowerUpCard();
        //poweup ad andre
        player1.getPlayerBoard().addPowerUp(powerUp);
        
        //move player
        try {
            map.movePlayer(player1, map.getSquare(0, 2));
        } catch (
                MapException e) {
            e.printStackTrace();
        }
    
        try {
            actionController.usePowerUpController(actionModel, powerUp);
        } catch (
                NoPowerUpAvailable noPowerUpAvailable) {
            noPowerUpAvailable.printStackTrace();
        } catch (
                NotValidInput notValidInput) {
            notValidInput.printStackTrace();
        } catch (MapException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        //stampa player
        for (Player p : players) {
            
            p.toString();
            p.stampa();
        }
    
        actionModel.refreshMapAmmoCard();
        actionModel.refreshMapWeaponCard();
        map.print();
        
        ViewCLI viewCLI = new ViewCLI();
        viewCLI.setRow(0);
        viewCLI.setColumn(2);
        viewCLI.setIndex(0);
    
        try {
            actionController.runActionController(actionModel, viewCLI);
            System.out.println("muovo andrea (actual player in 2,0)");
        } catch (
                RemoteException e) {
            e.printStackTrace();
        } catch (
                NotValidSquareException e) {
            e.printStackTrace();
        } catch (MapException e) {
            e.printStackTrace();
        }
        for (Player p : players) {
            
            p.toString();
            p.stampa();
        }
        
        //grab 1
        System.out.println("GRab action in 0,2");
        viewCLI.setRow(0);
        viewCLI.setColumn(2);
        viewCLI.setIndex(0);
    
        try {
            actionController.grabActionController(actionModel,viewCLI);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidSquareException e) {
            e.printStackTrace();
        } catch (MapException e) {
            e.printStackTrace();
        }
    
        for (Player p : players) {
        
            p.toString();
            p.stampa();
        }
        map.print();
    
    
    
        //grab 1
        System.out.println("GRab action in 0,1");
        viewCLI.setRow(0);
        viewCLI.setColumn(1);
        viewCLI.setIndex(0);
    
        try {
            actionController.grabActionController(actionModel,viewCLI);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidSquareException e) {
            e.printStackTrace();
        } catch (MapException e) {
            e.printStackTrace();
        }
    
        for (Player p : players) {
        
            p.toString();
            p.stampa();
        }
        map.print();
    
    
         */
    
    
    }
}
