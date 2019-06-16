package it.polimi.view.cli;

import it.polimi.controller.GameController;
import it.polimi.controller.RemoteGameController;
import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.view.RemoteView;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;



public class ViewCLI implements RemoteView, Serializable {

    //dobbiamo capire quali classi della view devono essere serializzabili

    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    
    protected State state;
    protected String user;
    public GameModel gameModel;
    public Map map;
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

        //todo completare il caso in cui lo stato del gioco non sia lobby.

        connection();
        pingToServer();
        System.out.println("WELCOME TO ADRENALINA");

        try {
            gameModel = gameController.getGameModel();
            if(!gameController.isGameStarted()) {  //todo partita inizia solo quando si sono connessi tutti.se entri qui siamo sicuro in lobby.partita parte dopo la lobby

                do {

                    setUser();
                    } while (verifyName(user));
                gameController.addObserver(this);
                gameController.update(this);
            }else{

                if(canJoinAgain()){

                    do{

                        setUser();
                    }while(!tryToReadd(user));

                    gameController.reAddObserver(this);
                    //todo manca da settare il player online
                    System.out.println("AGAIN ONLINE");
                }else {

                    System.out.println("GAME IS ALREADY STARTED");
                    System.exit(0);
                }
            }
        } catch (RemoteException e) {

            System.out.println("NETWORK ERROR ");
            System.exit(0);
        }
    }

    //da eliminare
    public  ViewCLI(GameController controller) throws RemoteException {
        
        
        this.gameModel=controller.getGameModel();
        this.gameController=controller;
        
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
        
        if (gameModel.getPlayers(true)!=null){

        for(Player player:gameModel.getPlayers(true)) {
    
            if (name.equals(player.getName())) {
                System.out.println("THIS USERNAME ALREADY EXIST");
                return true;
            }
        }
        }
        return false;
    }
    
    protected void setUser() {
        
        Scanner input;
        input = new Scanner(System.in);
        System.out.println("ENTER YOUR USERNAME:");
        this.user = input.next().toUpperCase();
    }

    public void pingToServer(){

        Timer timer=new Timer();
        TaskPingServer taskPingServer=new TaskPingServer(gameController,timer);
        timer.schedule(taskPingServer,0,2000);
    }

    @Override
    public void pingToClient() throws RemoteException {

    }

    @Override
    public void setOnline (boolean online){
        this.online = online;
        if(!online){
            this.print("\n\nYOU ARE NOW INACTIVE! TO JOIN AGAIN THE MATCH, PLEASE PRESS 0");
        }
    }

    public boolean canJoinAgain(){

        for(RemoteView remoteView: gameModel.getRemoteView()){

            if(remoteView==null){

                return true;
            }
        }
        return false;
    }

    public boolean tryToReadd(String user){

        for(Player player:gameModel.getPlayers(true)){

            if(user.equals(player.getName())){

                if(player.getOnline()){

                    return false;
                }else{

                    return true;
                }
            }
        }
        return false;
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
    
    public void printMap(){
        
        PrintMap.printMap(gameModel.getMap());
    }
    
    public void run() throws RemoteException {
    
        switch (gameModel.getState()) {
           
            case LOBBY:
                viewLobby();
                break;
            case SPAWNPLAYER:
                viewSpawnPowerUp();
                break;
           case STARTTURN:
               viewStartTurn();
                break;
            case CHOSEACTION:
                viewChoiseAction();
                break;
            case USEPOWERUP:
                break;
            case RUN:
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
    
    //metodi di input e controllo
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
            
            PrintSelectAction.printRow();
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            tempRow = input.nextInt();
            
            PrintSelectAction.printColumn();
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
        
        return gameModel.getMap().getRoomColor().get(getUserInput(0,gameModel.getMap().getRoomColor().size()));
    }
    
    
    //todo da sistemare: metodo per la scelta se si vuole spostare o meno un player dopo aver sparato (presente in molte armi)
    public int getMoveYesNo(){
        
        return getUserInput(0,1);
    }
    
    public int getUserInput (int min, int max){
        
        int i;
        Scanner input = new Scanner(System.in);
        
        do {
            
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            i = input.nextInt();
            
        } while (i<min || i>max);
        
        return i;
    }
    
    //metodi di rete e observer
    private void notifyController() throws RemoteException {
        
        if (true) {
            //this.state=gameController.getGameModel().getState();
            gameController.update(this);
        } else {
            // vedere cosa fare
        }
    }
    
    //metodi di case
    public void viewLobby() throws RemoteException{
    
        System.out.println("GAMERS IN THE LOBBY:");
        for(Player a: gameModel.getPlayers(true)){
            System.out.println("- " + a.getId() +": "+ a.getName());
        }
        notifyController();
    }
    
    public  void viewSpawnPowerUp () throws RemoteException {
    
        System.out.println("CHOSE POWERUP TO MANTAIN, WHERE YOU RESPAWN, THE OTHER ONE GOES IN YOUR PLAYER BOARD:");
        Player player = gameModel.getActualPlayer();
    
        System.out.println(player.toString());
        
        PrintPowerUp.print(player.getPowerUpCardsSpawn());
    
        System.out.println("YOUR CHOISE: ");
        
        setIndex(getUserInput(0,1));
        notifyController();
    
    }
    
    public void viewStartTurn() throws RemoteException {
        
        PrintMap.printMap(map);
        PrintPlayer.print(gameModel.getActualPlayer());
        notifyController();
    }
    
    public void viewChoiseAction() throws RemoteException {
    
        PrintSelectAction.print();
        getUserInput(0,3);
        
        notifyController();
        
        
    }
    public void viewRunSelection() throws RemoteException {

        PrintRunAction.print();
        getSquareInput(1);
        
        //notifica che hai preso i valori
        notifyController();
    }
    
    //action method

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
            PrintSelectAction.printIndexWeapon();
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
    
    //weapon method
    
    public void viewSelectWeapon() {
        
        ArrayList<WeaponCard> weapons = gameModel.getActualPlayer().getPlayerBoard().getPlayerWeapons();
        PrintEffectWeapon.printSelectWeapon();
        PrintWeapon.printName(weapons);

        try {
            
            setIndex(getUserInput(0,weapons.size()));
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
            
            setIndex2(getUserInput(0,weaponEffects.size()));
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
        setIndex(getUserInput(0,3));
        //TODO NON VA BENE
        /*
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
        
         */
    }
    
    

    //GRENADE LAUNCHER
    public void viewGrenadeLauncherBasicEffect(GameModel gameModel) throws RemoteException {

        PrintEffectWeapon.printGrenadeLauncherBasicEffect(gameModel);
        //get the player target
        setTarget1(getPlayerInput());

        PrintTarget.printYesNo();
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
        int i = getUserInput(0,1);

        if(i==0){
            
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
        getSquareInput(1);

        PrintTarget.printYesNo();
        int i = getUserInput(0,1);

        if(i==0){
            //get the player target 2
            setTarget2(getPlayerInput());
        }

        PrintEffectWeapon.printPowerGloveRocketFirstMode2(gameModel);

        PrintTarget.printYesNo();
        
        int j= getUserInput(0,1);
        if(j==0){

            getSquareInput(2);

            PrintTarget.printYesNo();
            
            int h = getUserInput(0,1);
            if(h==0){
                
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
        
        int j = getUserInput(0,1);
        
        //TODO NON VA BENE
        for(int h = 0; j < j; j++){

            PrintTarget.print();
            if(h==0){

                setTarget1(getPlayerInput());
            }

            if(h==1){

                setTarget2(getPlayerInput());
            }

            if(h==2){

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
    
}