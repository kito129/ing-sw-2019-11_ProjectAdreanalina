package it.polimi.controller;

import com.sun.org.apache.bcel.internal.generic.PUSH;
import it.polimi.model.*;
import it.polimi.model.Exception.ControllerException.RoudControllerException.SquareNotExistException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionMaxDistLimitException;
import it.polimi.model.Exception.ModelException.RoundModelException.RunActionMaxDistLimitException;
import it.polimi.model.Exception.ModelException.RoundModelException.NoPowerUpAvaible;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;

import java.util.ArrayList;


public class ActionController {
    
    
    public void runActionController (ActionModel actionModel,Map map) throws SquareNotExistException {
        
        //answer to view an input Square
        Square inputSquare = new Square(1,0,EnumColorSquare.RED);
        //temp variables
         map.existInMap(inputSquare);
         
        //effective move
        try {
            actionModel.runActionModel(inputSquare);
        } catch (RunActionMaxDistLimitException e) {
            //da gestire se mossa non valida
        }
    }
    
    public void grabActionController (ActionModel actionModel,Map map) throws SquareNotExistException {
        
        //answer to view an input Square
        Square inputSquare = new Square(1,0,EnumColorSquare.RED);
        Integer indexWeapon=null;
        //temp variables
        map.existInMap(inputSquare);
        
        
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
        } catch (CatchActionMaxDistLimitException catchActionMaxDistExpetion) {
        
        } catch (CatchActionFullObjException e) {
        
        }
    }
    
    public void usePowerUpController(ActionModel actionModel,PowerUpCard powerUpCard) throws NoPowerUpAvaible {
        
        //NEWTON
        if (powerUpCard.getClass().equals(Newton.class)) {
            
            //chiedi i parametri di newton
            Player targetPlayer = new Player(1,"marco", EnumColorPlayer.BLU);
            Square targetSquare = new Square(1,1,EnumColorSquare.RED);
            try {
                
                actionModel.usePowerUpNewton((Newton)powerUpCard,targetPlayer, targetSquare);
            } catch (NotInSameDirection notInSameDirection) {
                
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
            } catch (NotVisibleTarget notVisibleTarget) {
                notVisibleTarget.printStackTrace();
            }
    
        //TELEPORTER
        } else if (powerUpCard.getClass().equals(Teleporter.class)) {
            
            //chiedi i parametri di teleporter
            Square targetSquare = new Square(1,1,EnumColorSquare.RED);
            actionModel.usePowerUpTeleporter((Teleporter) powerUpCard, targetSquare);
            
        //TARGETING SCOPE
        } else if (powerUpCard.getClass().equals(TargetingScope.class)) {
            
            //chiedi i parametri di TargetingScope
            Player targetPlayer = new Player(1,"marco", EnumColorPlayer.BLU);
            actionModel.usePowerUpTargetingScope((TargetingScope) powerUpCard, targetPlayer);
            
        }
    }
    
    public void rechargeController(ArrayList<WeaponCard> weapon){
        
        for (WeaponCard a: weapon){
            //provo a ricaricare in caso non abbia munizioni, lancia eccezione e  chideo aslttrinetin pagomi chiede se voglio pagare con PoweUp
            
            
            
            //se si se, le mie muni +  i powe up selti possono ricaraicare , ricrico,
        }
    }
    
    public void respawnPlayer(ArrayList<Player> deadPlayer){
        
        for (Player a: deadPlayer){
            //rianimo uno alla volta i player
            
        }
    }
    
    public void scoringPlayerBoard(ArrayList<Player> deadPlayer){
        
        for (Player a:deadPlayer){
            //incasso un plancia lal volta
    
    
            //TODO AVANTI DA QUI 6/05
        }
    
    }
    
    public void LockRifleWeapon(){
        //chiedo alla view che effeto vuole usare
        
        // come stringa
        
        switch (effect){
            case "Base Effect":
                //chiedo alla view i parametri che mi servono per questo spefico effetto
                //gestisco le eccezioni sugli imputi qui
                //
                
                //public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotVisibleTarget {
                
                //gestisco anche eccezioni di basso livello NotVisibleTarget
                
            case "Second lock effect":
                //chiedo alla view i parametri che mi servono per questo spefico effetto
                
                //gestisco le eccezioni sugli imputi qui
                // gestire il fatto che target 2 deve essere diverso da target 1
            
                //public void secondLockEffect(Map map, Player currentPlayer, Player target2)throws NotVisibleTarget {
                
                //gestisco anche eccezioni di basso livello NotVisibleTarget

        }
    
    
    
}
