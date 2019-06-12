package it.polimi.view.cli;

import it.polimi.controller.RemoteGameController;
import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.view.RemoteView;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewCLI implements RemoteView, Serializable {

    //dobbiamo capire quali classi della view devono essere serializzabili

    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    
    protected State state;
    protected String user;
    protected Player player;
    public GameModel gameModel;
    public RemoteGameController gameController;
    protected boolean online;
    //attribute for run
    private int row;
    private int row2;
    private int column;
    private int column2;
    //attribute for grab
    private int index;
    private int index2;
    //attribute yes or no
    private boolean booleanChose;
    //scelta arma
    private int choicePlayer;
    private int choicePlayer2;
    private int choicePlayer3;
    private boolean useSecondEffect;
    private boolean useThirdEffect;
    private WeaponsEffect weaponsEffect;
    private boolean optionWeapon;
    //attribute for shoot
    private int target1;
    private int target2;
    private int target3;
    private int target4;
    //attribute for room
    private EnumColorSquare colorRoom;
    //attribute for direction
    private EnumCardinalDirection cardinalDirection;
    


    public ViewCLI(){

        connection();
        System.out.println("WELCOME TO ADRENALINA");

        try {
            gameModel=gameController.getGameModel();
            gameController.addObserver(this);
            gameController.update(this);
        }catch (RemoteException remoteException) {

            System.out.println("NETWORK ERROR ");
            System.exit(0);
        }


    }

    public void connection()  {

        try {

            Registry registry = LocateRegistry.getRegistry("localhost");
            gameController = (RemoteGameController) registry.lookup("gameController");
            UnicastRemoteObject.exportObject(this, 0);
            setOnline(true);
            
        } catch (RemoteException | NotBoundException e ) {
    
            System.out.println("NETWORK ERROR ");
            System.exit(0);
        }
    }

    private boolean verifyName(String name){

        for(Player player:gameModel.getPlayers(true)){

            if(name.equals(player.getName())){
                System.out.println("THIS USERNAME ALREADY EXIST");
                return true;
            }
        }return false;
    }
    
    @Override
    public void setOnline (boolean online){
        this.online = online;
        if(!online){
            this.print("\n\nYOU ARE NOW INACTIVE! TO JOIN AGAIN THE MATCH, PLEASE PRESS 0");
        }
    }
    
    public void print(String s) {

        System.out.println(s);
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
    public int getChoicePlayer2() {

        return choicePlayer2;
    }

    @Override
    public int getChoicePlayer3() {

        return choicePlayer3;
    }

    @Override
    public int getRow () {
        
        return row;
    }

    @Override
    public int getRow2() {

        return row2;
    }

    @Override
    public int getColumn () {
        
        return column;
    }

    @Override
    public int getColumn2() {

        return column2;
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
    public boolean isOptionWeapon () {
        
        return optionWeapon;
    }
    
    @Override
    public EnumColorSquare getColorRoom() {
        return colorRoom;
    }

    @Override
    public EnumCardinalDirection getCardinalDirection() {

        return cardinalDirection;
    }

    @Override
    public String getUser () throws RemoteException {
        
        return user;
    }
    
    @Override
    public boolean getOnline () throws RemoteException {
        
        return false;
    }
    
    @Override
    public boolean isBooleanChose () {
        
        return booleanChose;
    }
    
    @Override
    public int getIndex2 () {
        
        return index2;
    }
    
    public void setBooleanChose (boolean booleanChose) {
        
        this.booleanChose = booleanChose;
    }
    
    public void setIndex2 (int index2) {
        
        this.index2 = index2;
    }
    
    
    public void setRow (int row) {
        
        this.row = row;
    }

    public void setRow2(int row2) {

        this.row2 = row2;
    }

    public void setColumn (int column) {
        
        this.column = column;
    }

    public void setColumn2(int column2) {

        this.column2 = column2;
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
    
    public void setOptionWeapon (boolean optionWeapon) {
        
        this.optionWeapon = optionWeapon;
    }
    
    public void setColorRoom(EnumColorSquare colorRoom) {

        this.colorRoom = colorRoom;
    }

    public void setCardinalDirection(EnumCardinalDirection cardinalDirection) {

        this.cardinalDirection = cardinalDirection;
    }

    public void setIndex (int index) {
        
        this.index = index;
    }

    public void setChoicePlayer(int choicePlayer) {

        this.choicePlayer = choicePlayer;
    }

    public void setChoicePlayer2(int choicePlayer2) {

        this.choicePlayer2 = choicePlayer2;
    }

    public void setChoicePlayer3(int choicePlayer3) {

        this.choicePlayer3 = choicePlayer3;
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
    
    public void setGameModel (GameModel gameModel) {
        
        this.gameModel = gameModel;
    }
    
    @Override
    public void resetInput() throws RemoteException {
        setColumn(-1);
        setRow(-1);
        setIndex(-1);
    }
    
    /**
     * modifies the view based on the current state
     * @param gameModel the gamemodel of the match
     */
    @Override
    public void update(GameModel gameModel) throws RemoteException {
        
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
                viewRun();
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
            case SELECTWEAPON:
                viewSelectWeapon();
                break;
            case SHOOT:
                switch (gameModel.getWeaponsEffect()) {
                    
                    case BaseEffect:
                        viewLockRifleBasicEffect(gameModel);
                    case SecondLockEffect:
                        viewLockRifleSecondLock(gameModel);
            }
                break;
            case SELECTEFFECT:
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
        getSquareInput(1);
        
        //notifica che hai preso i valori
        notifyController();
    }
    
    
    public void viewRun() throws RemoteException {
        
        PrintMap.printMap(gameModel.getMap());
    }

    public void  viewGrabSelection() throws RemoteException {

        PrintGrabAction.printGrabStuff();
        getSquareInput(1);
        Square target = null;
        try {
            
            target = gameModel.getMap().getSquare(row,column);
        } catch (MapException e) {
            e.printStackTrace();
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

        PrintMap.printMap(gameModel.getMap());
    }
    
    public void viewSelectWeapon() {
        
        ArrayList<WeaponCard> weapons = gameModel.getActualPlayer().getPlayerBoard().getPlayerWeapons();
        PrintEffectWeapon.printSelectWeapon();
        PrintWeapon.printName(weapons);
        int i;

        Scanner input = new Scanner(System.in);
        try {
            do {
    
                while (!input.hasNextInt())
                    input = new Scanner(System.in);
                i = input.nextInt();
    
            } while (i<0 || i>weapons.size());
            
            setIndex(i);
            notifyController();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public void viewSelectWeaponEffect() {
        
        ArrayList<WeaponsEffect> weaponEffects = gameModel.getActualPlayer().getPlayerBoard().getPlayerWeapons().get(index).getWeaponEffects();
        PrintEffectWeapon.printSelectWeaponEffect();
        PrintWeapon.printEffectName(weaponEffects);
        int i;
        
        try {
            Scanner input = new Scanner(System.in);
            do {
    
                while (!input.hasNextInt())
                    input = new Scanner(System.in);
                i=input.nextInt();
                
    
            } while (input.nextInt()<0 || input.nextInt()>weaponEffects.size());
            
            setIndex2(i);
            notifyController();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    
    }
    
    //WEAPON
    
    //LOCK RIFLE
    public void viewLockRifleBasicEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printLockRifleBasicEffect(gameModel);

       //metodo per prender in input un player con il controllo
        setTarget1(getPlayerInput());
        
        notifyController();
    }
    
    public void viewLockRifleSecondLock(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printLockRifleSecondLock(gameModel);

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
    public void viewMachineGunBasicEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printMachineGunBasicEffect(gameModel);
        Scanner input = new Scanner(System.in);
        //get the player target 1
        setTarget1(getPlayerInput());
        //get the player target 2
        setTarget2(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    public void viewMachineGunFocusShot(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printMachineGunFocusShot(gameModel);
        Scanner input = new Scanner(System.in);
        
        PrintTarget.print();
        
        //do {
        
        while (!input.hasNextInt())
            input = new Scanner(System.in);
        
        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        
        //la scelta del target (se il primo o il secondo)
        setChoicePlayer(input.nextInt());
        //notify controller with new input
        notifyController();
    }
    
    public void viewMachineGunTurretTripod(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printMachineGunTurretTripod(gameModel);
        //get the player target 3
        setTarget3(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    //TRACTOR BEAM
    public void viewTractorBeamBasicMode(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printTractorBeamBasicMode(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //get the square target
        getSquareInput(1);
        //notify controller with new input
        notifyController();
    }
    
    public void viewTractorBeamPunisherMode(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printTractorBeamPunisherMode(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    //THOR
    public void viewThorBasicEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printThorBasicEffect(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    public void viewThorChainReaction(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printThorChainReaction(gameModel);
        //get the player target
        setTarget2(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    public void viewThorHighVoltage(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printThorHighVoltage(gameModel);
        //get the player target
        setTarget3(getPlayerInput());
        
        notifyController();
    }
    
    //PLASMA GUN
    public void viewPlasmaGunBasicEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunBasicEffect(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
    
        PrintEffectWeapon.printPlasmaGunChargedShot();
        
        if (getMoveYesNo()==1) {
            
            setBooleanChose(true);
            
        } else {
            setBooleanChose(false);
        }
        notifyController();
    }
    
    public void viewPlasmaGunPhaseGlide(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunPhaseGlide(gameModel);
        //get the square target
        getSquareInput(1);
        //notify controller with new input
        notifyController();
    }
    
    /*
    public void viewPlasmaGunChargedShot() throws RemoteException{
        
        PrintEffectWeapon.printPlasmaGunChargedShot();
        //notify controller with new input
        //TODO VEDERE SE é NECESsario notficare anche in caso non ci fossero input da parte della view
        notifyController();
    }
    
     */
    
    //WHISPER
    public void viewWhisperEffect(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printWhisperEffect(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    //VORTEX CANNON
    public void viewVortexCannonBasicEffect (GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printCannonVortexBasicEffect(gameModel);
        //get the square target
        getSquareInput(1);
        //get the player target 1
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    public void viewCannonVortexBlackHole(GameModel gameModel) throws RemoteException{
        
        PrintEffectWeapon.printCannonVortexBlackHole(gameModel);
        //get the player target 2
        setTarget2(getPlayerInput());
        //get the player target 3
        setTarget3(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    //FURNACE
    public void viewFurnaceBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFurnaceBasicMode(gameModel);
        
        PrintTarget.printRoom();
        //get the color target
        setColorRoom(getRoomColor(gameModel));
        //notify controller with new input
        notifyController();
    }
    
    public void viewFurnaceCozyFireMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printFurnaceCozyFireMode(gameModel);
        //get the square target
        getSquareInput(1);
        //notify controller with new input
        notifyController();
    }
    
    //HEATSEEKER
    public void viewHeatseekerEffect(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printHeatseekerEffect(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    //HELLION
    public void viewHellionBasicMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printHellionBasicMode(gameModel);
        //get the player target 1
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }
    
    public void viewHellionNanoTracerMode(GameModel gameModel) throws RemoteException {
        
        PrintEffectWeapon.printHellionNanoTracerMode(gameModel);
        //get the player target 1
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }

    //ZX-2
    public void viewZX2BasicMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printZX2BasicMode(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }

    public void viewZX2ScannerMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printZX2ScannerMode(gameModel);

        Scanner input = new Scanner(System.in);
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setIndex(input.nextInt());

        } while (input.nextInt()<0 || input.nextInt()>3 || input.nextInt()>gameModel.getPlayers(true).size());

        //TODO NON VA BENE
        for(int i = 0; i < input.nextInt(); i++){
    
            //get the player target
            if(i==0){

                setTarget2(getPlayerInput());
            }

            if(i==1){

                setTarget3(getPlayerInput());
            }

            if(i==2){

                setTarget4(getPlayerInput());
            }
        }
        //notify controller with new input
        notifyController();
    }

    //GRENADE LAUNCHER
    public void viewGrenadeLauncherBasicEffect(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printGrenadeLauncherBasicEffect(gameModel);
        //get the player target
        setTarget1(getPlayerInput());

        PrintTarget.printYesNo();
        Scanner input = new Scanner(System.in);
        //get the square target
        getSquareInput(1);
        //notify controller with new input
        notifyController();
    }

    public void viewGrenadeLauncherExtraGrenade(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printGrenadeLauncherExtraGrenade(gameModel);
        //get the square target ant put in row 2 and column 2
        getSquareInput(2);
        //notify controller with new input
        notifyController();
    }

    //SHOTGUN
    public void viewShotGunBasicMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printShotGunBasicMode(gameModel);
        //get the square target
        getSquareInput(1);
        //notify controller with new input
        notifyController();
    }

    public void viewShotgunLongBarrelMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printShotGunLongBarrelMode(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }

    //CYBERBLADE
    public void viewCyberbladeBasicEffect(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printCyberbladeBasicEffect(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }

    public void viewCyberbladeShadowstep(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printCyberbladeShadowstep(gameModel);
        Scanner input = new Scanner(System.in);
        //get the square target
        getSquareInput(1);
        //notify controller with new input
        notifyController();
        
    }

    public void viewCyberbladeSliceAndDice(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printCyberbladeSliceAndDice(gameModel);
        //get the player target
        setTarget2(getPlayerInput());
        //notify controller with new input
        notifyController();
    }

    //SLEDGEHAMMER
    public void viewSledgehammerBasicMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printSledgehammerBasicMode(gameModel);
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }

    public void viewSledgehammerPulverizeMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printSledgehammerPulverizeMode(gameModel);
        //get the player target
        setTarget2(getPlayerInput());
        //get the square target
        getSquareInput(1);
        //notify controller with new input
        notifyController();
    }

    //ROCKET LAUNCHER
    public void viewRocketLauncherBasicEffect(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printRocketLauncherBasicEffect(gameModel);

        PrintTarget.print();
        setTarget1(getPlayerInput());

        PrintTarget.printYesNo();
        Scanner input = new Scanner(System.in);
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setChoicePlayer(input.nextInt());

        } while (input.nextInt()<0 || input.nextInt()>1);

        if(input.nextInt()==0){
            
            //get the square target
            getSquareInput(1);
        }
        //notify controller with new input
        notifyController();
    }

    public void viewRocketLauncherRocketJump(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printRocketLauncherRocketJump(gameModel);
        getSquareInput(1);

        notifyController();
    }

    public void viewRocketLauncherFragmentingWarhead() throws RemoteException{

        PrintEffectWeapon.printRocketLauncherFragmentingWarhead(gameModel);
        //notify controller with new input
        //TODO vedere se è necessario
        notifyController();
    }

    //POWER GLOVE
    public void viewPowerGloveBasicMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printPowerGloveBasicMode(gameModel);
        //get the square target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }

    public void viewPowerGloveRocketFirstMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printPowerGloveRocketFirstMode(gameModel);
        Scanner input = new Scanner(System.in);
        getSquareInput(1);

        PrintTarget.printYesNo();
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setChoicePlayer(input.nextInt());

        } while (input.nextInt()<0 || input.nextInt()>1);

        if(input.nextInt()==0){
            //get the player target 2
            setTarget2(getPlayerInput());
        }

        PrintEffectWeapon.printPowerGloveRocketFirstMode2(gameModel);

        PrintTarget.printYesNo();
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setChoicePlayer2(input.nextInt());

        } while (input.nextInt()<0 || input.nextInt()>1);

        if(input.nextInt()==0){

            getSquareInput(2);

            PrintTarget.printYesNo();
            do {

                while (!input.hasNextInt())
                    input = new Scanner(System.in);
                setChoicePlayer3(input.nextInt());

            } while (input.nextInt()<0 || input.nextInt()>1);

            if(input.nextInt()==0){
                
                //get the player target 3
                setTarget3(getPlayerInput());
            }
        }
        //notify controller with new input
        notifyController();
    }

    //SHOCKWAVE
    public void viewShockwaveBasicMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printShockwaveBasicMode(gameModel);
        Scanner input = new Scanner(System.in);

        PrintTarget.printNumbTarget();
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setIndex(input.nextInt());

        } while (input.nextInt()<0 || input.nextInt()>3 || input.nextInt()>gameModel.getPlayers(true).size());
        
        //TODO NON VA BENE
        for(int i = 0; i < input.nextInt(); i++){

            PrintTarget.print();
            if(i==0){

                setTarget1(getPlayerInput());
            }

            if(i==1){

                setTarget2(getPlayerInput());
            }

            if(i==2){

                setTarget3(getPlayerInput());
            }
        }

        notifyController();
    }

    public void viewShockwaveTsunamiMode() throws RemoteException{

        PrintEffectWeapon.printShockwaveTsunamiMode();
        //notify controller with new input
        notifyController();
    }

    //RAILGUN
    public void viewRailgunBasicMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printRailgunBasicMode(gameModel);
        Scanner input = new Scanner(System.in);

        PrintTarget.printCardinalDirection();
        //do {

        while (!input.hasNextInt())
            input = new Scanner(System.in);

        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setCardinalDirection(gameModel.getMap().getCardinalDirection().get(input.nextInt()));
        //get the player target
        setTarget1(getPlayerInput());
        //notify controller with new input
        notifyController();
    }

    public void viewRailgunPiercingMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printRailgunPiercingMode(gameModel);
        Scanner input = new Scanner(System.in);

        PrintTarget.printCardinalDirection();
        //do {

        while (!input.hasNextInt())
            input = new Scanner(System.in);

        //} while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size() && input.nextInt()!=gameModel.getActualPlayer().getId());
        setCardinalDirection(gameModel.getMap().getCardinalDirection().get(input.nextInt()));

        PrintTarget.printNumbTarget();
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setIndex(input.nextInt());

        } while (input.nextInt()<0 || input.nextInt()>2);

        //TODO non va bene
        for(int i = 0; i < input.nextInt(); i++){

            PrintTarget.print();
            if(i==0){

                setTarget1(getPlayerInput());
            }

            if(i==1){

                setTarget2(getPlayerInput());
            }
        }
        //notify controller with new input
        notifyController();
    }

    //FLAMETHROWER
    public void viewFlamethrowerBasicMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printFlamethrowerBasicMode(gameModel);
        Scanner input = new Scanner(System.in);

        PrintTarget.printNumbTarget();
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setIndex(input.nextInt());

        } while (input.nextInt()<0 || input.nextInt()>2);

        if(index==1){
            //get the player target 1
            setTarget1(getPlayerInput());
        }

        if(index==2){
            //get the player target 1
            setTarget1(getPlayerInput());
            //get the player target 2
            setTarget2(getPlayerInput());
        }
        //notify controller with new input
        notifyController();
    }

    
    //TODO NON VA BENE
    public void viewFlamethrowerBarbecueMode(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printFlamethrowerBarbecueMode(gameModel);
        Scanner input = new Scanner(System.in);

        PrintTarget.printNumbTarget();
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);
            setIndex(input.nextInt());

        } while (input.nextInt()<0 || input.nextInt()>2);

        if(index==1){
            //get the player target 3
            setTarget3(getPlayerInput());
        }

        if(index==2){
            //get the player target 3
            setTarget3(getPlayerInput());
            //get the player target 4
            setTarget4(getPlayerInput());
        }
        //notify controller with new input
        notifyController();
    }

    //FINE ARMI
    //-------------------------------------------------------------------------------------------------


    
    public int getPlayerInput(){
    
        PrintTarget.print();
        
        int i=-1;
       
        Scanner input = new Scanner(System.in);
        do {
            
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            i=input.nextInt();
        
        } while (i<0 || i>gameModel.getPlayers(true).size() || i==gameModel.getActualPlayer().getId());
        
        return i;
    }
    
    public void getSquareInput(int i){
    
        PrintTarget.printSquare();
       
        Scanner input = new Scanner(System.in);
        int tempRow;
        int tempColumn;
    
        do {
    
            PrintSelectMove.printRow();
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            tempRow = input.nextInt();
    
            PrintSelectMove.printColumn();
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            tempColumn = input.nextInt();
            
            if(!gameModel.getMap().existInMap(tempRow,tempColumn)){
                System.out.println("SQUARE NOT EXIST IN MAP. RE INESERT CORRECT SQUARE");
            }
            
        }while (gameModel.getMap().existInMap(tempRow,tempColumn));
        
        if (i==1) {
            setRow(tempRow);
            setColumn(tempColumn);
        } else if(i==2){
            setRow2(tempRow);
            setColumn2(tempColumn);
        }
        
        
    }
    
    public EnumColorSquare getRoomColor(GameModel gameModel){
    
        PrintTarget.printColor(gameModel);
        
        Scanner input = new Scanner(System.in);
    
        //get the color room
        do {
        
            while (!input.hasNextInt())
                input = new Scanner(System.in);
        
        }while (input.nextInt()<gameModel.getMap().getRoomColor().size() && input.nextInt()>0);
        int i = input.nextInt();
        
        return gameModel.getMap().getRoomColor().get(i);
    }
    
    
    //todo da sistemare: metodo per la scelta se si vuole spostare o meno un player dopo aver sparato (presente in molte armi)
    public int getMoveYesNo(){

        int i=-1;

        Scanner input = new Scanner(System.in);
        do {

            while (!input.hasNextInt())
                input = new Scanner(System.in);
            i=input.nextInt();

        } while (i<0 || i>1);

        return i;
    }
}