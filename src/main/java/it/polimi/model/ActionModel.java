package it.polimi.model;

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
    
    
    private PowerUpCard getPowerUp(PowerUpCard powerUp) throws NoPowerUpAvaible {
        
        for(PowerUpCard a:gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps()){
            if(a.equals(powerUp)){
                return a;
            } else {
                throw new NoPowerUpAvaible();
            }
        }
        return null;
    }
    
    public void usePowerUpNewton(Newton newton,Player targetPlayer, Square targetSquare) throws NoPowerUpAvaible, NotInSameDirection, NotValidDistance {
        
        gameModel.setState(State.POWERUP);
        Newton newton1=(Newton)getPowerUp(newton);
        newton1.effect(gameModel.getMap(),targetSquare,targetPlayer);
        
    }
    
    public void usePowerUpTeleporter(Teleporter teleporter, Square targetSquare) throws NoPowerUpAvaible {
    
        gameModel.setState(State.POWERUP);
        Teleporter teleporter1=(Teleporter) getPowerUp(teleporter);
        teleporter1.effect(gameModel.getActualPlayer(),gameModel.getMap(),targetSquare);
        
    }
    
    public void usePowerUpTargetingScope(TargetingScope tagertingScope,Player targetPlayer) throws NoPowerUpAvaible {
    
        gameModel.setState(State.POWERUP);
        TargetingScope tagertingScope1=(TargetingScope) getPowerUp(tagertingScope);
        tagertingScope1.effect(gameModel.getActualPlayer(),targetPlayer);
        
    }
    
    public void usePowerUpTagBackGrenade(TagBackGrenade tagBackGrenade, Player targetPlayer) throws NoPowerUpAvaible, NotVisibleTarget {
        
        gameModel.setState(State.POWERUP);
        TagBackGrenade tagBackGrenade1=(TagBackGrenade) getPowerUp(tagBackGrenade);
        tagBackGrenade1.effect(gameModel.getMap(),gameModel.getActualPlayer(),targetPlayer);
        
    }
    
}
