package it.polimi.controller;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NoPowerUpAvailable;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotValidSquareException;
import it.polimi.view.RemoteView;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ManagerController implements RemoteGameController {
    
    private ActionController actionController;
    private ActionModel actionModel;
    private GameModel gameModel;
    private Player actualPlayer;
    private boolean gameStarted;
    
    public boolean getStaretd(){
        return gameStarted;
    }
    
    public GameModel getGameModel () {
        
        
        return this.gameModel;
    }
    
    public ActionModel getActionModel () {
        
        return actionModel;
    }
    
    public void setGameModel (GameModel gameModel) {
        
        this.gameModel = gameModel;
    }
    
    public void setActionController (ActionController actionController) {
        
        this.actionController = actionController;
    }
    
    public void setActionModel (ActionModel actionModel) {
        
        this.actionModel = actionModel;
    }
    
    public void setGameStarted (boolean gameStarted) {
        
        this.gameStarted = gameStarted;
    }
    
    public Player getActualPlayer () {
        
        return actualPlayer;
    }
    
    
    @Override
    public void update (RemoteView view) throws RemoteException {
    
        if(gameStarted)
            verifyObserver();
    
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
                case LOBBY:
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
                    } catch (NotValidInput notValidInput) {
                        notValidInput.printStackTrace();
                    } catch (MapException e) {
                        e.printStackTrace();
                    }
                    break;
                case RUN:
                    //oggi
                    try {
                        actionController.runActionController(actionModel, view);
                    } catch (NotValidSquareException | MapException e) {
                        e.printStackTrace();
                    }
                    break;
                case GRAB:
                    try {
                        actionController.grabActionController(actionModel, view);
                    } catch (NotValidSquareException | MapException e) {
                        e.printStackTrace();
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
    
    @Override
    public void setPlayerOnline (String user, boolean online){
        for(Player a : gameModel.getPlayers()){
            if(a.getName().equals(user)){
                a.setOnline(online);
            }
        }
    }
    
    //metodo che per ora non serve
    private void verifyObserver() {
        
        for(int i=0; i<gameModel.getObservers().size(); i++){
            try{
                if(gameModel.getObservers().get(i) != null)
                    gameModel.getObservers().get(i).getUser();
            } catch(RemoteException e){
                if(gameModel.getState().equals(State.LOBBY)) {
                    gameModel.getPlayers().get(i).setOnline(false);
                    gameModel.getPlayers().remove(i);
                    gameModel.getObservers().remove(i);
                }
                else {
                    gameModel.getPlayers().get(i).setOnline(false);
                    gameModel.removeObserver(gameModel.getObservers().get(i));
                }
            }
           
        }
    }
    /*
    @Override
    public void addObserver (RemoteView view) throws RemoteException {
    
    }
    
    @Override
    public void reAddObserver (RemoteView view) throws RemoteException {
    
    }
    */
}









    

