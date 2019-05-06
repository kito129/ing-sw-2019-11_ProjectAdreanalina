package it.polimi.controller;

import it.polimi.model.*;
import it.polimi.model.Exception.ControllerException.RoudControllerException.SquareNotExistException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionMaxDistException;
import it.polimi.model.Exception.ModelException.RoundModelException.MoveActionNotValidException;
import it.polimi.model.Exception.ModelException.RoundModelException.NoPowerUpAvaible;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;

import javax.print.DocFlavor;


public class ActionController {
    
    
    public void runActionController (ActionModel actionModel,Map map){
        
        //answer to view an input Square
        Square inputSquare = new Square(1,0,EnumColorSquare.RED);
        //temp variables
        
        //controllo qui se la square esiste nella mappa
        try{
            
            map.existInMap(inputSquare);
            
        } catch (SquareNotExistException squareEx){
            //da gestire es square non esiste
        }
        
        //effective move
        try {
            actionModel.runActionModel(inputSquare);
        } catch (MoveActionNotValidException e) {
            //da gestire se mossa non valida
        }
    }
    
    public void grabActionController (ActionModel actionModel,Map map){
        
        //answer to view an input Square
        Square inputSquare = new Square(1,0,EnumColorSquare.RED);
        Integer indexWeapon=null;
        //temp variables
        
    
        //controllo qui se la square esiste nella mappa
        try{
    
            map.existInMap(inputSquare);
        
        } catch (SquareNotExistException squareEx){
            //da gestire es square non esiste
        }
        
        //guardo se la square è di generation, se si devo chidere alla view l'index dell'arma , altrimenti passo a null
        if(map.isGenerationSquare(inputSquare)){
            
            //chiedi alla view l'index dellarma
            //solo prova
            indexWeapon= 1;
        } else {
            indexWeapon=null;
        }
        
        //effective catch gia con l'index giusto se è una Generation Square
        try {
            
            actionModel.grabActionModel(inputSquare,indexWeapon);
        } catch (CatchActionMaxDistException catchActionMaxDistExpetion) {
        
        } catch (CatchActionFullObjException e) {
        
        }
    }
    
    public void usePowerUpController(ActionModel actionModel,PowerUpCard powerUpCard){
    
        //NEWTON
        if (powerUpCard.getClass().equals(Newton.class)) {
            
            //chiedi i parametri di newton
            Player targetPlayer = new Player(1,"marco", EnumColorPlayer.BLU);
            Square targetSquare = new Square(1,1,EnumColorSquare.RED);
            try {
                
                actionModel.usePowerUpNewton((Newton)powerUpCard,targetPlayer, targetSquare);
            } catch (NotInSameDirection notInSameDirection) {
                
                //TODO
            } catch (NoPowerUpAvaible noPowerUpAvaible) {
                
                //TODO
            } catch (NotValidDistance notValidDistance) {
                
                //TODO
            }
        //TAGBACK GRANATE
        } else if (powerUpCard.getClass().equals(TagBackGrenade.class)) {
            
            //chiedi i parametri di teleporter
            Player targetPlayer = new Player(1,"marco", EnumColorPlayer.BLU);
            try {
        
                actionModel.usePowerUpTagBackGrenade((TagBackGrenade) powerUpCard,targetPlayer );
            } catch (NoPowerUpAvaible noPowerUpAvaible) {
        
                //TODO
            } catch (NotVisibleTarget notVisibleTarget) {
                notVisibleTarget.printStackTrace();
            }
    
        //TELEPORTER
        } else if (powerUpCard.getClass().equals(Teleporter.class)) {
            
            //chiedi i parametri di teleporter
            Square targetSquare = new Square(1,1,EnumColorSquare.RED);
            try {
        
                actionModel.usePowerUpTeleporter((Teleporter) powerUpCard, targetSquare);
            } catch (NoPowerUpAvaible noPowerUpAvaible) {
        
                //TODO
            }
        //TARGETING SCOPE
        } else if (powerUpCard.getClass().equals(TargetingScope.class)) {
            
            //chiedi i parametri di TargetingScope
            Player targetPlayer = new Player(1,"marco", EnumColorPlayer.BLU);
            try {
        
                actionModel.usePowerUpTargetingScope((TargetingScope) powerUpCard, targetPlayer);
            } catch (NoPowerUpAvaible noPowerUpAvaible) {
        
                //TODO
            }
        }
    
    }
}