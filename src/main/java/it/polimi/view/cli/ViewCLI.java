package it.polimi.view.cli;

import it.polimi.controller.ManagerController;
import it.polimi.controller.RemoteGameController;
import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Weapon.LockRifle;
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
    private int index;
    //attribute for shoot
    private int target1;
    private int target2;
    private int target3;
    private int target4;
    
    public ViewCLI(ManagerController managerController){
        this.state=managerController.getGameModel().getState();
        this.gameModel=managerController.getGameModel();
        this.gameController=managerController;
        
    }
  
    
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
    public int getTarget1() {
        return target1;
    }

    @Override
    public int getTarget2() {
        return target2;
    }

    @Override
    public int getTarget3() {
        return target3;
    }

    @Override
    public int getTarget4() {
        return target4;
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

    public void setTarget1(int target1) {
        this.target1 = target1;
    }

    public void setTarget2(int target2) {
        this.target2 = target2;
    }

    public void setTarget3(int target3) {
        this.target3 = target3;
    }

    public void setTarget4(int target4) {
        this.target4 = target4;
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
    
    @Override
    public void reserInput () throws RemoteException {
        setColumn(-1);
        setRow(-1);
        setIndex(-1);
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
                viewLockRifleBasicEffect(gameModel);
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
        
        if (true) {
            this.state=gameController.getGameModel().getState();
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
    
        Square target = null;
    
        while (target==null) {
    
            PrintSelectMove.printRow();
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setRow(input.nextInt());
    
            System.out.println();
    
            PrintSelectMove.printColumn();
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setColumn(input.nextInt());
    
            if ((gameModel.getMap().existInMap(getRow(), getColumn()))) {
                try {
                    target = gameModel.getMap().getSquare(getRow(), getColumn());
                } catch (MapException e) {
                    e.printStackTrace();
                }
            }
        }
        
        //notifica che hai preso i valori

        notifyController();
    }
    
    
    public void viewRun() throws RemoteException {
        
        PrintMap.printMap(gameModel.getMap().getSquares());
    }

    public void viewGrabSelection() throws RemoteException {

        PrintGrabAction.printGrabStuff();
        Square target = null;
        
        while (target==null) {
            Scanner input = new Scanner(System.in);
    
            PrintSelectMove.printRow();
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setRow(input.nextInt());
    
            System.out.println();
    
            PrintSelectMove.printColumn();
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setColumn(input.nextInt());
            
            if((gameModel.getMap().existInMap(getRow(),getColumn()))){
                try {
                    target=gameModel.getMap().getSquare(getRow(),getColumn());
                } catch (MapException e) {
                    e.printStackTrace();
                }
            }
            
        }
        
        System.out.println("input corretto");
        
        if(gameModel.getMap().isGenerationSquare(target)){

            PrintGrabAction.printGrabWeapon();
            PrintWeapon.print(((GenerationSquare) target).getWeaponList());
            PrintSelectMove.printIndexWeapon();
            Scanner input = new Scanner(System.in);
            
            while(!input.hasNextInt())
                input = new Scanner(System.in);
            setIndex(input.nextInt());
        }
        
        notifyController();
    }

    public void viewGrab() throws RemoteException {

        PrintMap.printMap(gameModel.getMap().getSquares());
    }

    //WEAPON

    //LOCK RIFLE
    public void viewLockRifleBasicEffect(RemoteGameModel gameModel) throws RemoteException{

        PrintEffectWeapon.printLockRifleBasicEffect(gameModel);
        Scanner input = new Scanner(System.in);

        //do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);

        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers().size());

        setTarget1( input.nextInt());
        notifyController();
    }

    public void viewLockRifleSecondLock(RemoteGameModel gameModel) throws RemoteException{

        System.out.println("Do you want to use the Second Lock?");
        System.out.println("0 -> YES");
        System.out.println("1 -> NO");
        
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        if(input.nextInt()==0){

            PrintEffectWeapon.printLockRifleSecondLock(gameModel);

            PrintTarget.print();
            do {
    
                while(!input.hasNextInt())
                    input = new Scanner(System.in);
                
            } while (input.nextInt()>0 && input.nextInt()<gameModel.getPlayers().size());

            setTarget1(input.nextInt());
        }
    }

    //ELECTROSCYTHE
    public void viewElectroscytheBasicMode() throws RemoteException{

        PrintEffectWeapon.printElectroscytheBasicMode();
    }

    public void viewElectroscytheReaperMode() throws RemoteException{

        PrintEffectWeapon.printElectroscytheReaperMode();
    }

    //MACHINE GUN
    public void viewMachineGunBasicEffect(RemoteGameModel gameModel) throws RemoteException{

        PrintEffectWeapon.printMachineGunBasicEffect(gameModel);
        Scanner input = new Scanner(System.in);

        PrintTarget.print();
        //do {

        while (!input.hasNextInt())
            input = new Scanner(System.in);

        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers().size());
        setTarget1( input.nextInt());

        PrintTarget.print();
        //do {

        while (!input.hasNextInt())
            input = new Scanner(System.in);

        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers().size());
        setTarget2( input.nextInt());
    }

    public void viewMachineGunFocusShot(RemoteGameModel gameModel) throws RemoteException{

        System.out.println("Do you want to use the Focus Shot?");
        System.out.println("0 -> YES");
        System.out.println("1 -> NO");
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        if(input.nextInt()==0){

            PrintEffectWeapon.printMachineGunFocusShot(gameModel);

            PrintTarget.print();
            do {

                while (!input.hasNextInt())
                    input = new Scanner(System.in);

            } while (input.nextInt()>0 && input.nextInt()<gameModel.getPlayers().size());

            setTarget1(input.nextInt());
        }
    }

    public void viewMachineGunTurretTripod(RemoteGameModel gameModel) throws RemoteException{

        System.out.println("Do you want to use the Turret Tripod?");
        System.out.println("0 -> YES");
        System.out.println("1 -> NO");
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        if(input.nextInt()==0){

            PrintEffectWeapon.printMachineGunTurretTripod(gameModel);

            PrintTarget.print();
            //do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);

            //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers().size());
            setTarget1( input.nextInt());

            PrintTarget.print();
            //do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);

            //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers().size());
            setTarget2( input.nextInt());
        }
    }

    //THOR
    public void viewThorBasicEffect(RemoteGameModel gameModel) throws RemoteException{

        PrintEffectWeapon.printThorBasicEffect(gameModel);
        Scanner input = new Scanner(System.in);

        PrintTarget.print();
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);

        } while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers().size());

        setTarget1( input.nextInt());
    }

    public void viewThorChainReaction(RemoteGameModel gameModel) throws RemoteException{

        System.out.println("Do you want to use the Chain Reaction?");
        System.out.println("0 -> YES");
        System.out.println("1 -> NO");
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        if(input.nextInt()==0){

            PrintEffectWeapon.printThorChainReaction(gameModel);

            PrintTarget.print();
            do {

                while (!input.hasNextInt())
                    input = new Scanner(System.in);

            } while (input.nextInt()>0 && input.nextInt()<gameModel.getPlayers().size());

            setTarget1(input.nextInt());
        }
    }

    public void viewThorHighVoltage(RemoteGameModel gameModel) throws RemoteException{

        System.out.println("Do you want to use the High Voltage?");
        System.out.println("0 -> YES");
        System.out.println("1 -> NO");
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        if(input.nextInt()==0){

            PrintEffectWeapon.printThorHighVoltage(gameModel);

            PrintTarget.print();
            do {

                while (!input.hasNextInt())
                    input = new Scanner(System.in);

            } while (input.nextInt()>0 && input.nextInt()<gameModel.getPlayers().size());

            setTarget1(input.nextInt());
        }
    }
}