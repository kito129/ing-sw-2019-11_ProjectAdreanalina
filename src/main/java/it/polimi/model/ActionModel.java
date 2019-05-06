package it.polimi.model;

import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionMaxDistException;
import it.polimi.model.Exception.ModelException.RoundModelException.MoveActionNotValidException;

public class ActionModel {
    
    private GameModel gameModel;
    
    private int action=0;
    
    public int getAction () {
        
        return action;
    }
    
    public GameModel getGameModel () {
        
        return gameModel;
    }
    
    public void runActionModel (Square targetSquare) throws MoveActionNotValidException{
        
        Map map=gameModel.getMap();
        Player actualPlayer=gameModel.getActualPlayer();
        
        if(map.distance(map.findPlayer(actualPlayer),targetSquare)<4){
            
            map.movePlayer(actualPlayer,targetSquare);
            action++;
        } else {
            
            throw new MoveActionNotValidException();
        }
    }
    
    public void grabActionModel (Square targetSquare, int weaponIndex) throws CatchActionMaxDistException, CatchActionFullObjException {
        
        Map map=gameModel.getMap();
        Player actualPlayer=gameModel.getActualPlayer();
        
        //adrenalinic distance
        int maxDist;
        
        if(actualPlayer.getPlayerBoard().getDamages().size()<2){
            
            maxDist=1;
        } else {
            
            maxDist = 2;
        }
        
        if(map.distance(map.findPlayer(actualPlayer),targetSquare)<maxDist) {
            
            map.movePlayer(actualPlayer,targetSquare);
            if(!map.isGenerationSquare(targetSquare) && actualPlayer.getPlayerBoard().getPlayerPowerUps().size()<4) {
                
                actualPlayer.catchAmmoCard(((NormalSquare) map.findPlayer(actualPlayer)).catchAmmoCard());
                action++;
            } else if (map.isGenerationSquare(targetSquare) && actualPlayer.getPlayerBoard().getPlayerWeapons().size()<4) {
    
                actualPlayer.getPlayerBoard().addWeapon(((GenerationSquare) map.findPlayer(actualPlayer)).catchWeapon(weaponIndex));
                action++;
            }else {
                throw new CatchActionFullObjException();
            }
        } else {
        
            throw new CatchActionMaxDistException();
        }
    }
    
    public boolean checkTurn(){
        if(action==1){
            gameModel.setState(State.ACTION1);
            return true;
        }
        else if (action==2){
            gameModel.setState(State.ACTION2);
            return true;
        }
        else {
            return false;
        }
    }
    
    public void usePowerUpActionModel(){
    
    }
    
}
