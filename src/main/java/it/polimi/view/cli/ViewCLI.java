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
    protected int index;
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
    private ViewWeapon viewWeapon;
    private CLIPrintMap CLIPrintMap;


    public ViewCLI(){

        
        connection();
        pingToServer();
        System.out.println("WELCOME TO ADRENALINA");
        
        try {
            gameModel = gameController.getGameModel();
            String random = new String("ciao");
            this.CLIPrintMap = new CLIPrintMap(gameModel);
            if(!gameController.isGameStarted()) {  //todo partita inizia solo quando si sono connessi tutti.se entri qui siamo sicuro in lobby.partita parte dopo la lobby

                do {

                    setUser();
                    } while (verifyName(user));
                gameController.addObserver(this);
                notifyController();
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

    @Override
    public boolean getOnline() throws RemoteException {

        return online;
    }

    public void pingToServer(){

        Timer timer=new Timer();
        TaskPingServer taskPingServer=new TaskPingServer(gameController,timer);
        timer.schedule(taskPingServer,0,2000);
    }

    @Override
    public void pingToClient() throws RemoteException {

    }



    //potrebbe essere sync
    /*public void setOnline (boolean online){
        this.online = online;
        if(!online){
            this.print("\n\nYOU ARE NOW INACTIVE! TO JOIN AGAIN THE MATCH, PLEASE PRESS 0");
        }
    }


     */

    public boolean canJoinAgain(){

        for(RemoteView remoteView: gameModel.getRemoteViews()){

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
    
    public void printMessageCurrent(){
        
        System.out.println(gameModel.getMessageToCurrentView());
    }
    
    public void printMessageAll(){
        
        System.out.println(gameModel.getMessageToAllView());
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
    
    @Override
    public void resetInput(){
        setColumn(-1);
        setRow(-1);
        setIndex(-2);
        setColumn2(-1);
        setRow2(-1);
        setTarget1(-1);
        setTarget2(-1);
        setTarget3(-1);
        setTarget4(-1);
        setBooleanChose(false);
        setUseSecondEffect(false);
        setUseThirdEffect(false);
        setCardinalDirection(null);
        setOptionWeapon(false);
        setWeaponsEffect(null);
        
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
    
    public void CLIViewMap(){

        CLIPrintMap.viewMapNew();
    }
    
    public void run() throws RemoteException {
    
        switch (gameModel.getState()) {
           
            case LOBBY:
                viewLobby();
                break;
            case MENU:
                viewMenu();
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
            case SELECTPOWERUP:
                viewSelectPowerUp();
                break;
            case SELECTPOWERUPINPUT:
                viewSelectPowerUpInput();
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
                viewWeapon.viewSelectWeapon();
                break;
            case SELECTEFFECT:
                viewWeapon.viewSelectWeaponEffect();
                break;
            case SELECTSHOOTINPUT:
                viewWeapon.viewSelectShootInuput();
                break;
            case SHOOT:
                viewWeapon.viewShoot();
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
                viewError();
                break;
        
        }
    }
    
    public void viewError() throws RemoteException {
        
        print("\nERROR!!\n ");
        print(gameModel.getMessageToCurrentView() + "\n");
        notifyController();
        
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
    
        }while (!gameModel.getMap().existInMap(tempRow,tempColumn));
        
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
    
    public boolean checkCurrent(){
        
        if(gameModel.getActualPlayer().getName().equals(this.user)){
            
            return true;
        } else {
            
            return false;
        }
    }
    
    //metodi di rete e observer
    protected void notifyController() throws RemoteException {

        gameController.update(this);
    }

    
    //metodi di case
    public void viewLobby() throws RemoteException{
    
        System.out.println("GAMERS IN THE LOBBY:");
        for(Player a: gameModel.getPlayers(true)){
            System.out.println("- " + a.getId() +": "+ a.getName());
        }
        notifyController();
    }
    
    public void viewMenu(){
        
        PrintMenu.print();
        
        
    }
    
    public  void viewSpawnPowerUp () throws RemoteException {
    
        System.out.println("CHOSE POWERUP TO MANTAIN, WHERE YOU RESPAWN, THE OTHER ONE GOES IN YOUR PLAYER BOARD:");
        Player player = gameModel.getActualPlayer();
    
        System.out.println(player.toString());
    
        System.out.println("\nPOWER UP TOO CHOOSE: ");
        
        PrintPowerUp.print(player.getPowerUpCardsSpawn(),false);
    
        System.out.println("YOUR CHOISE: ");
        
        setIndex(getUserInput(0,1));
        notifyController();
    
    }
    
    public void viewStartTurn() throws RemoteException {
        
        //create wepoan view controller
        viewWeapon = new ViewWeapon();
        
        CLIViewMap();
        PrintPlayer.print(gameModel.getActualPlayer());
        notifyController();
    }
    
    public void viewChoiseAction() throws RemoteException {
    
        System.out.println("\nYOUR INFO: \n");
        PrintPlayer.print(gameModel.getActualPlayer());
        System.out.println();
        PrintSelectAction.print();
        setIndex(getUserInput(-1,4));
        
        notifyController();
        
        
    }
    
    
    
    //RUN action method
    public void viewRunSelection() throws RemoteException {

        PrintRunAction.print();
        getSquareInput(1);
        
        //notifica che hai preso i valori
        notifyController();
    }
    
    public void viewRun() throws RemoteException {
        
        printMessageAll();
        
        if(checkCurrent()){
            
            printMessageCurrent();
        }
        
        CLIViewMap();
    
        notifyController();
        
    }
    
    //GRAB action method

    public void  viewGrabSelection() throws RemoteException {

        PrintGrabAction.printGrabStuff();
        getSquareInput(1);
        Square target;
        try {
            
            target = gameModel.getMap().getSquare(row,column);
            
            if(gameModel.getMap().isGenerationSquare(target)){
    
                PrintGrabAction.printGrabWeapon();
                PrintWeapon.print(((GenerationSquare) target).getWeaponList(),false);
                PrintSelectAction.printIndexWeapon();
                Scanner input = new Scanner(System.in);
                
                while(!input.hasNextInt())
                    input = new Scanner(System.in);
                setIndex(input.nextInt());
            }
            
            notifyController();
        } catch (MapException e) {
            e.printStackTrace();
        }
    }

    public void viewGrab() throws RemoteException {
    
        printMessageAll();
    
        if(checkCurrent()){
        
            printMessageCurrent();
        }
    
        CLIViewMap();
    
        notifyController();
    }
    
    
    //powerUP method
    public void viewSelectPowerUp(){
    
        ArrayList<PowerUpCard> powerUp = gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps();
        PrintPowerUp.print(powerUp,false);
    
        try {
        
            setIndex(getUserInput(0,powerUp.size()));
            notifyController();
            
        } catch (RemoteException e) {
            e.printStackTrace();
        }
       
    }
    
    public void viewSelectPowerUpInput() throws RemoteException {
        
        print("here have to select power up");
        notifyController();
    
    }
    
}