package it.polimi.model;

import it.polimi.controller.ActionController;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NoPowerUpAvailable;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotValidSquareException;
import it.polimi.view.cli.ViewCLI;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class MainMarcoController {
    
    public static void main (String args[]) {
    
        Player player1 = new Player(1, "andrea", EnumColorPlayer.YELLOW);
        Player player2 = new Player(2, "marco", EnumColorPlayer.BLU);
        Player player3 = new Player(3, "simone", EnumColorPlayer.GREEN);
        Player player4 = new Player(4, "niko", EnumColorPlayer.PINK);
        Player player5 = new Player(5, "teo", EnumColorPlayer.GREY);
        
        
        Map map = new Map(MapCreator.createB(), "mappa a");
        map.print();
    
        KillShotTrack killShotTrack = new KillShotTrack();
        ArrayList<Player> players = new ArrayList<>();
        
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        
        
        GameModel gameModel = new GameModel(map, killShotTrack, players);
        gameModel.setActualPlayer(player1);
        ActionModel actionModel = new ActionModel(gameModel);
        ActionController actionController = new ActionController();
        
        for (Player p : players) {
            
            p.stampa();
        }
        
       
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
        }
        
        
        //stampa player
        for (Player p : players) {
            
            p.toString();
            p.stampa();
        }
        
        
        ViewCLI viewCLI = new ViewCLI();
        viewCLI.setRow(2);
        viewCLI.setColumn(0);
        viewCLI.setIndexWeapon(0);
    
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
    
        System.out.println(map.isNotVisible(player1,player5));
        
    }
}
