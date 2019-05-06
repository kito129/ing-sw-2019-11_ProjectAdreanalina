package it.polimi.controller;

import it.polimi.model.*;

import java.util.ArrayList;

public class ManagerController {
    
    private ActionController actionController;
    private ActionModel actionModel;
    
    
    public void turn(){
        
        //2 action and multiple power up use
        while (actionModel.checkTurn() && actionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().size()>0){
            //scegli una mossa
            String mossa = new String("RUN");
            
            switch (mossa) {
                case "RUN":
                    actionController.runActionController(actionModel, actionModel.getGameModel().getMap());
                case "GRAB":
                    actionController.grabActionController(actionModel, actionModel.getGameModel().getMap());
                case "USE POWER UP":
                    //chiedo che power up vuole usare
                    ArrayList<PowerUpCard> powerUpAvailable = actionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps();
    
                    //chiedo al player qualche vuole usare
                    PowerUpCard usedPowerUp = powerUpAvailable.get(0);
                    actionController.usePowerUpController(actionModel,usedPowerUp);
                    
                default:
                
                
            }
        }
    }
    
}
