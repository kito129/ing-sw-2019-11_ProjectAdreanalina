package it.polimi.view.cli;

import it.polimi.controller.RemoteGameController;
import it.polimi.model.RemoteGameModel;
import it.polimi.model.State;
import it.polimi.view.RemoteView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ViewCLI implements RemoteView {
    
    
    protected State state;
    protected String user;
    RemoteGameModel gameModel;
    RemoteGameController gameController;
    protected boolean online;
    //attribute for run
    private int row;
    private int column;
    //attribute for grab
    private int index;
    
    @Override
    public int getIndex () {
        
        return index;
    }
    
    @Override
    public int getRow () {
        
        return row;
    }
    
    @Override
    public int getColumn () {
        
        return column;
    }
    
    
    @Override
    public String getUser () throws RemoteException {
        
        return user;
    }
    
    @Override
    public boolean getOnline () throws RemoteException {
        
        return false;
    }

    public void setOnline (boolean online) {
        
        this.online = online;
    }
    
    public void setRow (int row) {
        
        this.row = row;
    }
    
    public void setColumn (int column) {
        
        this.column = column;
    }
    
    public void setIndex (int index) {
        
        this.index = index;
    }
    
    public void setState (State state) {
        
        this.state = state;
    }
    
    public void setUser (String user) {
        
        this.user = user;
    }
    
    public void setGameController (RemoteGameController gameController) {
        
        gameController = gameController;
    }
    
    public void setGameModel (RemoteGameModel gameModel) {
        
        this.gameModel = gameModel;
    }
    
    /**
     * modifies the view based on the current state
     * @param gameModel the gamemodel of the match
     */
    @Override
    public void update(RemoteGameModel gameModel) throws RemoteException {
        
        this.gameModel = gameModel;
        this.run();
    }
    
    public void run() throws RemoteException {
    
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
                break;
            case RUN:
                viewRunAction();
                break;
            case GRAB:
                break;
            case SHOOT:
                break;
            case ENDACTION:
                break;
            case RECHARGE:
                break;
            case PASSTURN:
                break;
            case DEADPLAYER:
                break;
            case SCORINGPLAYERBOARD:
                break;
            case RESPWANPLAYER:
                break;
            case ENDTURN:
                break;
            case FINALSCORING:
                break;
            case CHECKILLSHOOT:
                break;
            case ERROR:
                break;
        
        }
    }
    
    //metodi di rete e observer

    private void notifyController() throws RemoteException {
        
        if (getOnline()) {
            
            gameController.update(this);
        } else {
            
            gameController.setPlayerOnline(user, true);
            this.setOnline(true);
        }
    }
    
    //metodi di contr
    
    public void viewRunAction() throws RemoteException {
        
        PrintRunAction.print();
        Scanner input = new Scanner(System.in);
        while(!input.hasNextInt())
            input = new Scanner(System.in);
        
        setRow(input.nextInt());
        while(!input.hasNextInt())
            input = new Scanner(System.in);
        
        setColumn(input.nextInt());
        
        //notifica che hai preso i valori
        notifyController();
    }
}
