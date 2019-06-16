package it.polimi.controller;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NoPowerUpAvailable;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Weapon.LockRifle;
import it.polimi.view.RemoteView;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameController extends UnicastRemoteObject implements RemoteGameController {

    private ActionController actionController;
    private ActionModel actionModel;
    private GameModel gameModel;
    private boolean gameStarted;
    private State state;
    private State beforeError;


    public GameController() throws RemoteException {

        gameModel = new GameModel();
        actionController = new ActionController();
        actionModel = new ActionModel(gameModel);

    }

    public boolean isGameStarted() {

        return gameStarted;
    }

    public GameModel getGameModel() throws RemoteException {

        return this.gameModel;
    }

    public ActionModel getActionModel() {

        return actionModel;
    }

    public void setGameModel(GameModel gameModel) {

        this.gameModel = gameModel;
    }

    public void setActionController(ActionController actionController) {

        this.actionController = actionController;
    }

    public void setActionModel(ActionModel actionModel) {

        this.actionModel = actionModel;
    }

    public void setGameStarted(boolean gameStarted) {

        this.gameStarted = gameStarted;
    }

    @Override
    public void pingToServer() throws RemoteException {

    }

    @Override
    public void update(RemoteView view) throws RemoteException {

        pingClient();

        //2 action and multiple power up use
        int action = 0;
        while (action < 2) {

            switch (gameModel.getState()) {
                case LOBBY:
                    actionController.lobby(actionModel, view);
                    break;
                case SPAWNPLAYER:
                    actionController.respawnPlayerController(actionModel, view);
                    break;
                case STARTTURN:
                    actionController.startTurn(actionModel, view);
                    break;
                case CHOSEACTION:
                    actionController.choseAction(actionModel, view);
                    break;
                case SELECTPOWERUP:
                    actionController.selectPowerUp(actionModel, view);
                    break;
                case USEPOWERUP:
                    actionController.usePowerUpController(actionModel, view);
                    break;
                case SELECTRUN:
                    //oggi
                    actionController.runActionController(actionModel, view);
                    action++;

                case RUN:

                case SELECTGRAB:
                    actionController.grabActionController(actionModel, view);
                    action++;

                case GRAB:

                case SELECTWEAPON:
                    actionController.selectWeapon(actionModel, view);
                    action++;

                case SELECTEFFECT:
                    actionController.selectWeaponEffect(actionModel, view);

                case SHOOT:
                    LockRifle lock = new LockRifle();
                    actionController.LockRifleweapon(gameModel, lock, view);
                    action++;

                case ENDACTION:

                case RECHARGE:
                    /*
                    //vedo se posso ricaricare ricarica
                    if (actualPlayerBoard.getWeaponToCharge().size() > 0) {

                        //chiedi alla view se vuoi ricaricare??
                        State recharge = State.RECHARGE;
                        //nel caso la view voglia ricaricare

                        gameModel.setState(State.RECHARGE);
                        // se si chiama metodo che verfica se puoi ricarcaire, lui ricaciehraà

                        if (recharge == State.RECHARGE) {

                            //chiamo la ricarica
                            actionController.rechargeController(actualPlayer, actualPlayerBoard.getWeaponToCharge(),view);
                        }
                        gameModel.setState(State.PASSTURN);
                    }

                     */
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
                    //creazione del player è temporanea
                    actionController.respawnPlayerController(actionModel, view);
                    break;
                case ENDTURN:
                    break;
                case FINALSCORING:
                    break;
                case CHECKILLSHOOT:
                    break;
                case ERROR:
                    actionController.errorState(actionModel);
            }
        }
    }

    @Override
    public void addObserver (RemoteView view)throws RemoteException  {

        gameModel.addObserver(view);


    }

    @Override
    public void reAddObserver(RemoteView view) throws RemoteException {

        gameModel.reAddObserver(view);
    }

    public void pingClient(){

        for(RemoteView remoteView:gameModel.getRemoteView()){
            try {

                if(remoteView!=null) {

                    remoteView.pingToClient();
                }
            }catch (RemoteException remoteException){

                //gestione disconnessione del giocatore
                //vedere anche sagrada se sono il lobby fanno robe diverse..devo aspettare marco per capire cosa istanzia.
                //dobbiamo metterlo offline
                gameModel.removeObserver(remoteView);

            }
        }
    }
}









    

