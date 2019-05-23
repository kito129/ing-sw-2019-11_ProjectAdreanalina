package it.polimi.view.cli;

import it.polimi.controller.RemoteGameController;
import it.polimi.model.RemoteGameModel;
import it.polimi.model.State;
import it.polimi.view.RemoteView;

import java.rmi.RemoteException;
import java.util.Scanner;

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
    private int indexWeapon;
    
  
    
    @Override
    public int getIndexWeapon() {
        
        return indexWeapon;
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
    
    public void setIndexWeapon(int indexWeapon) {
        
        this.indexWeapon = indexWeapon;
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
                viewLobby();
                break;
            case STARTTURN:
                break;
            case USEPOWERUP:
                break;
            case RUN:
                viewRun();
                break;
            case SELECTRUN:
                viewRunSelection();
                break;
            case GRAB:
                viewGrab();
                break;
            case SELECTGRAB:
                viewGrabSelection();
                break;
            case SHOOT:
                break;
            case SELECTSHOOT:
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

    public void viewLobby() throws RemoteException{

        PrintMenu.print();
    }
    
    public void viewRunSelection() throws RemoteException {
        
        PrintRunAction.print();

        Scanner input = new Scanner(System.in);

        PrintSelectMove.printRow();
        while(!input.hasNextInt())
            input = new Scanner(System.in);
        setRow(input.nextInt());

        System.out.println();

        PrintSelectMove.printColumn();
        while(!input.hasNextInt())
            input = new Scanner(System.in);
        setColumn(input.nextInt());
        
        //notifica che hai preso i valori
        notifyController();
    }
    
    public void viewRun() throws RemoteException {
        
        PrintMap.printMap(gameModel.getMap().getSquares());
    }

    public void viewGrabSelection() throws RemoteException {

        PrintGrabAction.printGrabStuff();

        Scanner input = new Scanner(System.in);

        PrintSelectMove.printRow();
        while(!input.hasNextInt())
            input = new Scanner(System.in);
        setRow(input.nextInt());

        System.out.println();

        PrintSelectMove.printColumn();
        while(!input.hasNextInt())
            input = new Scanner(System.in);
        setColumn(input.nextInt());

        System.out.println();

        if(gameModel.getMap().isGenerationSquare(gameModel.getMap().getSquare(row,column))){

            PrintGrabAction.printGrabWeapon();
            PrintWeapon.print(...);
            //todo stampare le armi così da poter vedere cosa scegliere
            PrintSelectMove.printIndexWeapon();
            while(!input.hasNextInt())
                input = new Scanner(System.in);
            setIndexWeapon(input.nextInt());
        }

        notifyController();
    }

    public void viewGrab() throws RemoteException {

        PrintMap.printMap(gameModel.getMap().getSquares());
    }
}
