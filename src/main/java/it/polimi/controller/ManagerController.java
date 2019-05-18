package it.polimi.controller;

import it.polimi.model.*;
import it.polimi.model.Exception.ControllerException.RoudControllerException.SquareNotExistException;
import it.polimi.model.Exception.ModelException.RoundModelException.NoPowerUpAvailable;
import it.polimi.model.Weapon.Electroscythe;

import java.util.ArrayList;

public class ManagerController {
    
    private ActionController actionController;
    private ActionModel actionModel;
    private GameModel gameModel= actionModel.getGameModel();
    
    
    public void turn() {
    
        Player actualPlayer = gameModel.getActualPlayer();
        PlayerBoard actualPlayerBoard = actualPlayer.getPlayerBoard();
    
        //2 action and multiple power up use
        while ((actionModel.checkActionCount() || actualPlayerBoard.getPlayerPowerUps().size() > 0) && !gameModel.getState().equals(State.ENDACTION)) {
    
            switch (gameModel.getState()) {
                case SETUP:
                    break;
                case PLAYERSETUP:
                    break;
                case SPAWNPLAYER:
                    break;
                case STARTTURN:
                    break;
                case USEPOWERUP:
                    //chiedo che power up vuole usare
                    ArrayList<PowerUpCard> powerUpAvailable = gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps();
    
                    //chiedo al player qualche vuole usare
                    PowerUpCard usedPowerUp = powerUpAvailable.get(0);
                    try {
    
                        actionController.usePowerUpController(actionModel, usedPowerUp);
                    } catch (NoPowerUpAvailable noPowerUpAvailable) {
    
                        //TODO
                    }
                    break;
                case RUN:
                    try {
    
                        actionController.runActionController(actionModel, gameModel.getMap());
                    } catch (SquareNotExistException e) {
    
                        //TODO
                    }
                    break;
                case GRAB:
                    try {
    
                        actionController.grabActionController(actionModel, gameModel.getMap());
                    } catch (SquareNotExistException e) {
    
                        //TODO
                    }
                    break;
                case SHOOT:
                    //prendo le armi che ho, le mostro alla vieee che decide cosa usare
    
                    //es
                    String string = "Lock Rifle ";
    
    
                    switch (string) {
                        case "ELECTOSCYTHE":
                            //Electroscythe electroscythe =
                            //actionController.ElectroscytheWeapon(gameModel,electroscythe);
        
                        case "LOCKRIFLE":
                            // LockRifle lockRifle = (LockRifle) getWeaponPlayer(gameModel.getActualPlayer(),string);//todo ti commento perchè npn mi compila
                            // actionController.LockRifleweapon(gameModel,lockRifle);
        
                    }
                    break;
                case ENDACTION:
                    break;
                case RECHARGE:
                    //vedo se posso ricaricare ricarica
                    if (actualPlayerBoard.getWeaponToCharge().size() > 0) {
    
                        //chiedi alla view se vuoi ricaricare??
                        State recharge = State.RECHARGE;
                        //nel caso la view voglia ricaricare
    
                        gameModel.setState(State.RECHARGE);
                        // se si chiama metodo che verfica se puoi ricarcaire, lui ricaciehraà
    
                        if (recharge == State.RECHARGE) {
                            
                            //chiamo la ricarica
                            actionController.rechargeController(actualPlayer, actualPlayerBoard.getWeaponToCharge());
                        }
                        gameModel.setState(State.PASSTURN);
                    }
                    break;
                case PASSTURN:
                    break;
                case DEADPLAYER:
                    break;
                case SCORINGPLAYERBOARD:
                    gameModel.setState(State.SCORINGPLAYERBOARD);
                    //PRIMA INCASSO PLANCE DI TUTTI POI RIANIMO TUTTI
    
                    // fase incasso plancie
                    actionController.scoringPlayerBoardController(actionModel);
                    break;
                case RESPWANPLAYER:
                    //fase di rianimazione
                    actionController.respawnPlayerController(actionModel);
                    break;
                case ENDTURN:
                    break;
                case FINALSCORING:
                    break;
                case CHECKILLSHOOT:
        
            }
        }
    }
    
    
    public GameModel getGameModel(){
        
        
        return this.gameModel;
    }
}
