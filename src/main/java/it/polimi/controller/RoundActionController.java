package it.polimi.controller;

import it.polimi.model.*;
import it.polimi.model.Exception.ControllerException.RoudControllerException.SquareNotExistException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionMaxDistException;
import it.polimi.model.Exception.ModelException.RoundModelException.MoveActionNotValidException;


public class RoundActionController {
    
    public void moveActionController(GameModel gameModel){
        
        //answer to view an input Square
        Square inputSquare = new Square(1,0,EnumColorSquare.RED);
    
        //controllo qui se la square esiste nella mappa
        try{
            
            gameModel.getMap().existInMap(inputSquare);
            
        } catch (SquareNotExistException squareEx){
            //da gestire es square non esiste
        }
        
        //effective move
        try {
           gameModel.getRoundActionModel().moveActionModel(gameModel.getMap(),gameModel.getActualPlayer(),inputSquare);
        } catch (MoveActionNotValidException e) {
            //da gestire se mossa non valida
        }
    }
    
    public void cathActionController(GameModel gameModel){
        
        //answer to view an input Square
        Square inputSquare = new Square(1,0,EnumColorSquare.RED);
        Integer indexWeapon=null;
        
    
        //controllo qui se la square esiste nella mappa
        try{
        
            gameModel.getMap().existInMap(inputSquare);
        
        } catch (SquareNotExistException squareEx){
            //da gestire es square non esiste
        }
        
        //guardo se la square è di generation, se si devo chidere alla view l'index dell'arma , altrimenti passo a null
        if(gameModel.getMap().isGenerationSquare(inputSquare)){
            
            //chiedi alla view l'index dellarma
            //solo prova
            indexWeapon= 1;
        } else {
            indexWeapon=null;
        }
        
        //effective catch gia con l'index giusto se è una Generation Square
        try {
            
            gameModel.getRoundActionModel().catchActionModel(gameModel.getMap(),gameModel.getActualPlayer(),inputSquare,indexWeapon);
        } catch (CatchActionMaxDistException catchActionMaxDistExpetion) {
        
        } catch (CatchActionFullObjException e) {
        
        }
    
    
    }
    
}