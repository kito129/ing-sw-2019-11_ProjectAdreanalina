package it.polimi.model;

import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionMaxDistException;
import it.polimi.model.Exception.ModelException.RoundModelException.MoveActionNotValidException;

public class RoundActionModel {
    
    private GameModel gameModel;
    
    public void moveActionModel(Map map, Player actualPlayer, Square tagetSquare) throws MoveActionNotValidException{
        
        if(map.distance(map.findPlayer(actualPlayer),tagetSquare)<4){
            
            map.movePlayer(actualPlayer,tagetSquare);
        } else {
            
            throw new MoveActionNotValidException();
        }
    }
    
    public void cathActionModel(Map map, Player actualPlayer, Square tagetSquare, int weaponIndex) throws CatchActionMaxDistException, CatchActionFullObjException {
        
        //adrenalinic distance
        int maxDist;
        
        if(actualPlayer.getPlayerBoard().getDamages().size()<2){
            
            maxDist=1;
        } else {
            
            maxDist = 2;
        }
        
        if(map.distance(map.findPlayer(actualPlayer),tagetSquare)<maxDist) {
            
            map.movePlayer(actualPlayer,tagetSquare);
            if(!map.isGenerationSquare(tagetSquare) && actualPlayer.getPlayerBoard().getPlayerPowerUps().size()<4) {
                
                actualPlayer.catchAmmoCard(((NormalSquare) map.findPlayer(actualPlayer)).catchAmmoCard());
            } else if (map.isGenerationSquare(tagetSquare) && actualPlayer.getPlayerBoard().getPlayerWeapons().size()<4) {
    
                actualPlayer.getPlayerBoard().addWeapon(((GenerationSquare) map.findPlayer(actualPlayer)).catchWeapon(weaponIndex));
            }else {
                throw new CatchActionFullObjException();
            }
        } else {
        
            throw new CatchActionMaxDistException();
        }
        
    }
    
}
