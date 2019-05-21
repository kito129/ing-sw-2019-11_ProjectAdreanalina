package it.polimi.model;


import it.polimi.model.Exception.ModelException.NotValidSquareException;
import it.polimi.model.Exception.NotVisibleTarget;
import it.polimi.model.Weapon.LockRifle;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MainPartita {

    public static void main(String args[]) {


        Player player1 = new Player(1, "andrea", EnumColorPlayer.YELLOW);
        Player player2 = new Player(2, "marco", EnumColorPlayer.BLU);
        Player player3 = new Player(3, "simone", EnumColorPlayer.GREEN);
        Player player4 = new Player(4,"niko",EnumColorPlayer.PINK);
        Player player5 = new Player(5,"teo",EnumColorPlayer.GREY);
        
        
        Map map=new Map(MapCreator.createB(),"mappa a");
        map.print();
        
        
        KillShotTrack killShotTrack=new KillShotTrack();
        ArrayList<Player> players = new ArrayList<>();
    
        GameModel gameModel=new GameModel(map,killShotTrack,players);
        ActionModel actionModel= new ActionModel(gameModel);
        
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        
        

        for(Player p:players){

            p.stampa();
        }
    
    
        try {
            map.addPlayerOnSquare(map.getSquare(0,1),player1);
        } catch (NotValidSquareException e) {
            System.out.println("error");
        }
        try {
            map.addPlayerOnSquare(map.getSquare(0,2),player2);
        } catch (NotValidSquareException e) {
            System.out.println("error");
        }
        try {
            map.addPlayerOnSquare(map.getSquare(1,2),player3);
        } catch (NotValidSquareException e) {
            System.out.println("error");
        }
        try {
            map.addPlayerOnSquare(map.getSquare(1,2),player4);
        } catch (NotValidSquareException e) {
            System.out.println("error");
        }
        try {
            map.addPlayerOnSquare(map.getSquare(1,3),player5);
        } catch (NotValidSquareException e) {
            System.out.println("error");
        }
    
        for(Player p:players){
        
            p.stampa();
        }
        
        map.print();
        
        
        System.out.println(map.isVisible(player1,player2));
        
        //spara
        LockRifle lockRifle=new LockRifle();
        try{
        
            lockRifle.baseEffect(map,player1,player2);
        
        }catch(NotVisibleTarget notVisibleTarget){
        
            System.out.println("non visibile");
        }
        //spara
        
        try{
        
            lockRifle.baseEffect(map,player1,player2);
        
        }catch(NotVisibleTarget notVisibleTarget){
        
            System.out.println("non visibile");
        }
        //spara
    
        try{
        
            lockRifle.baseEffect(map,player1,player2);
        
        }catch(NotVisibleTarget notVisibleTarget){
        
            System.out.println("non visibile");
        }
    
    
        //spara con altro player
        LockRifle lockRifle1=new LockRifle();
       
    
        try{
        
            lockRifle.secondLockEffect(map,player4,player2);
        
        }catch(NotVisibleTarget notVisibleTarget) {
    
            System.out.println("non visibile");
        }
    
    
        try{
        
            lockRifle.baseEffect(map,player4,player2);
        
        }catch(NotVisibleTarget notVisibleTarget){
        
            System.out.println("non visibile");
        }
    
    
    
    
    
        //stampa player
        for(Player p:players){
        
            p.stampa();
        }
        
        actionModel.scoringPlayerBoard(player2);
    
        //stampa player
        for(Player p:players){
        
            p.stampa();
        }

    }


}
