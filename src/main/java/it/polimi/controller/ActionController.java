package it.polimi.controller;

import it.polimi.model.*;
import it.polimi.model.Exception.ControllerException.RoudControllerException.SquareNotExistException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionMaxDistException;
import it.polimi.model.Exception.ModelException.RoundModelException.MoveActionNotValidException;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;


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
    
    public void usePowerUpActionController(PowerUpCard powerUpCard){
        
        if(powerUpCard.getClass().equals(Newton.class)){
            //do newton
            //chiedi i parametri di newton
            
        } else  if (powerUpCard.getClass().equals(TagBackGrenade.class)){
            //do tag back
        } else if (powerUpCard.getClass().equals(Teleporter.class)){
            //do teleport
        } else if (powerUpCard.getClass().equals(TargetingScope.class)){
            //Do targeting scope
        }
    
    }
}