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

        gameModel = new GameModel();
        functionModel = new FunctionModel(gameModel);
        functionController = new FunctionController(functionModel);

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
                case USEPOWERUP:
                    functionController.usePowerUpController(view);
                    break;
                case SELECTRUN:
                    functionController.runActionController(view);
                    break;
                case RUN:
                    functionController.run();
                    break;
                case SELECTGRAB:
                    functionController.grabActionController(view);
                case GRAB:
                    functionController.grab();
                case SELECTWEAPON:
                    functionController.weaponController.selectWeapon(view);
                case SELECTEFFECT:
                    functionController.weaponController.selectWeaponEffect(view);
                case SELECTSHOOTINPUT:
                    functionController.weaponController.selectShootInput(view);
                case SHOOT:
                    functionController.weaponController.afterShoot(view);
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
                    functionController.scoringPlayerBoardController();
                    break;
                case RESPWANPLAYER:
                    //fase di rianimazione
                    //creazione del player è temporanea
                    functionController.respawnPlayerController( view);
                    break;
                case ENDTURN:
                    break;
                case FINALSCORING:
                    break;
                case CHECKILLSHOOT:
                    break;
                case ERROR:
                    functionController.errorState();
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









    

