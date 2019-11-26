package it.polimi.view.cli;

import it.polimi.model.GameModel;
import it.polimi.model.Player;

public class PrintNotActualMenu {
    
    public static void printMenu(GameModel gameModel,ViewCLI view){
        
        System.out.println("YOU ARE NOT THE CURRENT PLAYER. WAIT FOR YOUR TURN");
        if (gameModel.getFinalFrezyMessage()!=null){
        
            System.out.println(gameModel.getFinalFrezyMessage());
        }
        System.out.print("MESSAGE FOR ALL PLAYER");
        view.printMessageAll();
        System.out.println("YOUR INFO: ");
        Player viewPlayer=null;
        for (Player a: view.gameModel.getPlayers(false,true)){
            
            if (a.getName().equals(view.user)){
                viewPlayer = a;
            }
        }
        
        PrintPlayerBoard.print(viewPlayer);
        System.out.println("\nMESSAGE FROM SERVER: ");
        view.printMessageAll();
        System.out.println("MAP :" +view.gameModel.getMap().getName() +"\n");
        view.printMap();
        System.out.println("\n\nWAITING...\n\n");
    }
    
}
