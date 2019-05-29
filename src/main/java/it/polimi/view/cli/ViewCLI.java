package it.polimi.view.cli;

import it.polimi.controller.RemoteGameController;
import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.view.RemoteView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ViewCLI implements RemoteView {

    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    
    protected State state;
    protected String user;
    protected Player player;
    public RemoteGameModel gameModel;
    public RemoteGameController gameController;
    protected boolean online;
    //attribute for run
    private int row;
    private int column;
    //attribute for grab
    private int index;
    //scelta arma
    private int choicePlayer;
    private boolean useSecondEffect;
    private boolean useThirdEffect;
    private WeaponsEffect weaponsEffect;
    //attribute for shoot
    private int target1;
    private int target2;
    private int target3;
    private int target4;
    //attribute for room
    private EnumColorSquare colorRoom;
    
    public ViewCLI(RemoteGameController remoteGameController){
        
        try {
            this.gameController=remoteGameController;
            this.state=this.gameController.getGameModel().getState();
            this.gameModel=this.gameController.getGameModel();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public int getIndex () {
        
        return index;
    }

    @Override
    public int getChoicePlayer() {

        return choicePlayer;
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
    public boolean isUseSecondEffect () {
        
        return useSecondEffect;
    }
    
    @Override
    public boolean isUseThirdEffect () {
        
        return useThirdEffect;
    }
    
    @Override
    public WeaponsEffect getWeaponsEffect () {
        
        return weaponsEffect;
    }
    
    @Override
    public EnumColorSquare getColorRoom() {
        return colorRoom;
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
    
    public void setWeaponsEffect (WeaponsEffect weaponsEffect) {
        
        this.weaponsEffect = weaponsEffect;
    }
    
    public void setUseSecondEffect (boolean useSecondEffect) {
        
        this.useSecondEffect = useSecondEffect;
    }
    
    public void setUseThirdEffect (boolean useThirdEffect) {
        
        this.useThirdEffect = useThirdEffect;
    }
    
    public void setColorRoom(EnumColorSquare colorRoom) {

        this.colorRoom = colorRoom;
    }

    public void setIndex (int index) {
        
        this.index = index;
    }

    public void setChoicePlayer(int choicePlayer) {

        this.choicePlayer = choicePlayer;
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
                setUseSecondEffect(true);
                viewLockRifleSecondLock(gameModel);
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
            //this.state=gameController.getGameModel().getState();
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

        PrintTarget.print();
        //metodo per prender in input un player con il controllo
        setTarget1(getPlayerInput());
        
        notifyController();
    }
    
    public void viewLockRifleSecondLock(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printLockRifleSecondLock(gameModel);

        PrintTarget.print();
        //metodo per prender in input un player con il controllo
        setTarget2(getPlayerInput());
    
        notifyController();
    }
    
    //ELECTROSCYTHE
    public void viewElectroscytheBasicMode() throws RemoteException{
        
        PrintEffectWeapon.printElectroscytheBasicMode();
        
        notifyController();
    }
    
    public void viewElectroscytheReaperMode() throws RemoteException{
        
        PrintEffectWeapon.printElectroscytheReaperMode();
        
        notifyController();
    }
    
    //MACHINE GUN
    public void viewMachineGunBasicEffect(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printMachineGunBasicEffect(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.print();
        setTarget1(getPlayerInput());
        
        PrintTarget.print();
        setTarget2(getPlayerInput());
        
        notifyController();
    }
    
    public void viewMachineGunFocusShot(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printMachineGunFocusShot(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.print();
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        
        //la scelta del target (se il primo o il secondo)
        setChoicePlayer(input.nextInt());
        
        notifyController();
    }
    
    public void viewMachineGunTurretTripod(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printMachineGunTurretTripod(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.print();
        setTarget3(getPlayerInput());
        
        notifyController();
    }
    
    //TRACTOR BEAM
    public void viewTractorBeamBasicMode(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printTractorBeamBasicMode(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.print();
        setTarget1(getPlayerInput());
        
        PrintTarget.printSquare();
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setRow( input.nextInt());
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setColumn( input.nextInt());

        notifyController();
    }
    
    public void viewTractorBeamPunisherMode(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printTractorBeamPunisherMode(gameModel);

        PrintTarget.print();
        setTarget2(getPlayerInput());
        
        notifyController();
    }
    
    //THOR
    public void viewThorBasicEffect(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printThorBasicEffect(gameModel);

        PrintTarget.print();
        setTarget1(getPlayerInput());
        
        notifyController();
    }
    
    public void viewThorChainReaction(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printThorChainReaction(gameModel);

        PrintTarget.print();
        setTarget2(getPlayerInput());
        
        notifyController();
    }
    
    public void viewThorHighVoltage(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printThorHighVoltage(gameModel);

        PrintTarget.print();
        setTarget3(getPlayerInput());
        
        notifyController();
    }
    
    //PLASMA GUN
    public void viewPlasmaGunBasicEffect(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunBasicEffect(gameModel);

        PrintTarget.print();
        setTarget1(getPlayerInput());
        
        notifyController();
    }
    
    public void viewPlasmaGunPhaseGlide(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunPhaseGlide(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.printSquare();
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setRow( input.nextInt());
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setColumn( input.nextInt());
        
        notifyController();
    }
    
    public void viewPlasmaGunChargedShot() throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunChargedShot();
        
        notifyController();
    }
    
    //WHISPER
    public void viewWhisperEffect(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printWhisperEffect(gameModel);

        PrintTarget.print();
        setTarget1(getPlayerInput());
        
        notifyController();
    }
    
    //CANNON VORTEX
    public void viewCannonVortexBasicEffect(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printCannonVortexBasicEffect(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.printSquare();
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setRow( input.nextInt());
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setColumn( input.nextInt());
        
        PrintTarget.print();
        setTarget1(getPlayerInput());
        
        notifyController();
    }
    
    public void viewCannonVortexBlackHole(RemoteGameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printCannonVortexBlackHole(gameModel);

        PrintTarget.print();
        setTarget2(getPlayerInput());

        PrintTarget.print();
        setTarget3(getPlayerInput());
        
        notifyController();
    }
    
    //FURNACE
    public void viewFurnaceBasicMode(RemoteGameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFurnaceBasicMode(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.printRoom();
        PrintEnumColorSquare.print(gameModel.getMap().getRoomColor());
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setColorRoom(gameModel.getMap().getRoomColor().get(input.nextInt()));
        
        notifyController();
    }
    
    public void viewFurnaceCozyFireMode(RemoteGameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFurnaceCozyFireMode(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.printSquare();
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setRow(input.nextInt());
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setColumn(input.nextInt());
        
        notifyController();
    }
    
    //HEATSEEKER
    public void viewHeatseekerEffect(RemoteGameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printHeatseekerEffect(gameModel);

        PrintTarget.print();
        setTarget1(getPlayerInput());
        
        notifyController();
    }
    
    //HELLION
    public void viewHellionBasicMode(RemoteGameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printHellionBasicMode(gameModel);

        PrintTarget.print();
        setTarget1(getPlayerInput());
        
        notifyController();
    }
    
    public void viewHellionNanoTracerMode(RemoteGameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printHellionNanoTracerMode(gameModel);

        PrintTarget.print();
        setTarget2(getPlayerInput());
        
        notifyController();
    }
    
    protected void connectionRequest() throws IOException {
        int tmp;
        System.out.println("IP ADDRESS: ");
        Scanner input = new Scanner(System.in);
        String ipAddress = input.next();
        
        try {
            Registry registry = LocateRegistry.getRegistry(ipAddress);
            gameController = (RemoteGameController) registry.lookup("network");
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            System.out.println("\n\nTHIS IP ADDRESS DOES NOT EXIST");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("\n\nOPS... AN ERROR OCCURRED. PLEASE RESTART THE GAME.");
            System.exit(0);
        }
    }
    
    public int getPlayerInput(){

        int i=-1;
       
        Scanner input = new Scanner(System.in);
        do {
            
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            i=input.nextInt();
        
        } while (i<0 || i>gameModel.getPlayers(true).size() || i==gameModel.getActualPlayer().getId());
        
        return i;
    }
}