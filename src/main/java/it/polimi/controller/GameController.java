package it.polimi.controller;

import it.polimi.model.*;
import it.polimi.view.RemoteView;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameController extends UnicastRemoteObject implements RemoteGameController {

    private FunctionController functionController;
    private FunctionModel functionModel;
    private GameModel gameModel;
    private boolean gameStarted;

    public GameController() throws RemoteException {
        
        this.functionModel = new FunctionModel();
        this.gameModel=functionModel.getGameModel();
        this.functionController= new FunctionController(functionModel);
        
    }

    public boolean isGameStarted() {

        return gameStarted;
    }

    public GameModel getGameModel() throws RemoteException {

        return this.gameModel;
    }
    

    public void setGameModel(GameModel gameModel) {

        this.gameModel = gameModel;
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
                    functionController.lobby();
                    break;
                case SPAWNPLAYER:
                    functionController.respawnPlayerController(view);
                    break;
                case STARTTURN:
                    functionController.startTurn();
                    break;
                case CHOSEACTION:
                    functionController.choseAction(view);
                    break;
                case SELECTPOWERUP:
                    functionController.selectPowerUp(view);
                    break;
                case SELECTPOWERUPINPUT:
                    functionController.selectPowerUpInput(view);
                    break;
                case USEPOWERUP:
                    functionController.usePowerUp(view);
                    break;
                case SELECTRUN:
                    functionController.runActionController(view);
                    break;
                case RUN:
                    functionController.run(view);
                    break;
                case SELECTGRAB:
                    functionController.grabActionController(view);
                case GRAB:
                    functionController.grab(view);
                case SELECTWEAPON:
                    functionController.weaponController.selectWeapon(view);
                case SELECTEFFECT:
                    functionController.weaponController.selectWeaponEffect(view);
                case PAYEFFECT:
                    functionController.weaponController.payWeaponExtraCost(view);
                    break;
                case SELECTSHOOTINPUT:
                    functionController.weaponController.selectShootInput(view);
                case SHOOT:
                    functionController.weaponController.afterShoot(view);
                case ENDACTION:
                    //TODO
                case SELECTRECHARGE:
                    functionController.selectRecharge(view);
                    break;
                case RECHARGE:
                    functionController.recharge(view);
                    break;
                case PASSTURN:
                    //TODO
                    break;
                case DEADPLAYER:
                    //TODO
                    break;
                case SCORINGPLAYERBOARD:
                    functionController.scoringPlayerBoardController();
                    break;
                case RESPWANPLAYER:
                    functionController.respawnPlayerController( view);
                    break;
                case ENDTURN:
                    break;
                case FINALSCORING:
                    break;
                case CHECKILLSHOOT:
                    break;
                case ERROR:
                    functionController.errorState(view);
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

        for(RemoteView remoteView:gameModel.getRemoteViews()){
            try {

                if(remoteView!=null) {

                    remoteView.pingToClient();
                }
            }catch (RemoteException remoteException){

                if((gameModel.getState()!=State.LOBBY)) {

                    int indexOfObserver = gameModel.getRemoteViews().indexOf(remoteView);
                    gameModel.getPlayers(true).get(indexOfObserver).setOnlineModel(false);
                    gameModel.removeObserver(remoteView);
                }else{

                    int indexOfObserver = gameModel.getRemoteViews().indexOf(remoteView);
                    if(indexOfObserver!=-1) {
                        gameModel.getPlayers(true).get(indexOfObserver).setOnlineModel(false);
                        gameModel.getPlayers(true).remove(indexOfObserver);
                    }
                    gameModel.getRemoteViews().remove(indexOfObserver);
                }

            }
        }
    }
}









    

