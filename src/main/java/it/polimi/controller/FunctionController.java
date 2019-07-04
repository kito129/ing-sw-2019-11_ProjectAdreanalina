package it.polimi.controller;


import it.polimi.model.*;
//chiedere perche devo importare tutto
import it.polimi.model.Exception.*;
import it.polimi.model.Map;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;
import it.polimi.model.Weapon.Electroscythe;
import it.polimi.view.RemoteView;

import java.rmi.RemoteException;
import java.util.*;


public class FunctionController {
    
    WeaponController weaponController;
    FunctionModel functionModel;
    private transient Timer timer;
    private transient Timer timerLobby;
    private int delay;
    
    public FunctionController(FunctionModel functionModel){
        
        this.functionModel=functionModel;
        this.weaponController = new WeaponController(this);
        this.delay=100;
        
    }
    
    /**
     * starts a timer when the state is LOBBY
     * when expired, go on start turn
     */
    private void startTimerLobby(){
        timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        timerLobby.cancel();
                        functionModel.getGameModel().setState(State.PUTSPAWN);
                    }
                }, delay
        );
    }
    
    /**
     * starts a timer to check if someone has been disconnected in the LOBBY
     */
    private void startTimerCheckLobby(){
        timerLobby = new Timer();
        timerLobby.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                       //here go the verify observer
                        if (functionModel.getGameModel().getPlayers(true).size() == 3)
                            timer.cancel();
                        else
                            startTimerCheckLobby();
                        if ( functionModel.getGameModel().getPlayers(true).size() ==5)
                            functionModel.getGameModel().setState(State.PUTSPAWN);
                    }
                }, 2000
        );
    }
    
    public void lobby() throws RemoteException {
    
        GameModel gameModel = this.functionModel.getGameModel();
        
        /*
        //for test army
        // game can start
        //for the moment add another player for testing army
        Player player1 = new Player(2,"AA",EnumColorPlayer.PINK,gameModel);
        Player player2 = new Player(3,"BB",EnumColorPlayer.BLU,gameModel);
        Player player3 = new Player(4,"CC",EnumColorPlayer.YELLOW,gameModel);
        Player player4 = new Player(5,"DD",EnumColorPlayer.GREEN,gameModel);
        Player player5 = new Player(6,"EE",EnumColorPlayer.PINK,gameModel);
        Player player6 = new Player(7,"FF",EnumColorPlayer.BLU,gameModel);
        Player player7 = new Player(8,"GG",EnumColorPlayer.YELLOW,gameModel);
        Player player8 = new Player(9,"HH",EnumColorPlayer.GREEN,gameModel);
        Player player9 = new Player(10,"II",EnumColorPlayer.GREEN,gameModel);
        Player player10 = new Player(11,"LL",EnumColorPlayer.GREEN,gameModel);
        Player player11 = new Player(12,"MM",EnumColorPlayer.GREEN,gameModel);
        Player player12 = new Player(13,"NN",EnumColorPlayer.GREEN,gameModel);
    
    
        //add on square
        try {
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(0,0),player1);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(0,1),player2);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(0,2),player3);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(0,3),player4);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(1,0),player5);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(1,1),player6);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(1,2),player7);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(1,3),player8);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(2,1),player9);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(2,2),player10);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(2,3),player11);
            gameModel.getMap().addPlayerOnSquare(gameModel.getMap().getSquare(2,2),player12);
        
            gameModel.getPlayers(true).add(player1);
            gameModel.getPlayers(true).add(player2);
            gameModel.getPlayers(true).add(player3);
            gameModel.getPlayers(true).add(player4);
            gameModel.getPlayers(true).add(player5);
            gameModel.getPlayers(true).add(player6);
            gameModel.getPlayers(true).add(player7);
            gameModel.getPlayers(true).add(player8);
            gameModel.getPlayers(true).add(player9);
            gameModel.getPlayers(true).add(player10);
            gameModel.getPlayers(true).add(player11);
            gameModel.getPlayers(true).add(player12);
        } catch (MapException e) {
            e.printStackTrace();
        }
    
        //TEST ARMY
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new LockRifle());
        
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new TractorBeam());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Thor());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new VortexCannon());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Furnace());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new PlasmaGun());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Heatseeker());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Whisper());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Hellion());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Flamethrower());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Zx2());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new GrenadeLauncher());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Shotgun());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new RocketLauncher());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new PowerGlove());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Railgun());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Shockwave());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Cyberblade());
        gameModel.getActualPlayer().getPlayerBoard().addWeapon(new Sledgehammer());
    
        //TEST POWER UP
        gameModel.getActualPlayer().getPlayerBoard().addPowerUp(new TagBackGrenade(EnumColorCardAndAmmo.BLU));
        gameModel.getActualPlayer().getPlayerBoard().addPowerUp(new Teleporter(EnumColorCardAndAmmo.BLU));
        gameModel.getActualPlayer().getPlayerBoard().addPowerUp(new Newton(EnumColorCardAndAmmo.RED));
        gameModel.getActualPlayer().getPlayerBoard().addPowerUp(new TargetingScope(EnumColorCardAndAmmo.BLU));
   
         
         */
        
          //if (gameModel.getPlayers(true).size() == 3) {
    
            //    startTimerLobby();
              //  startTimerCheckLobby();
            
            //}else
                if(gameModel.getPlayers(true).size() == 5){
            
                //timer.cancel();
                //timerLobby.cancel();
                //now game can start
                gameModel.setActualPlayer(gameModel.getPlayers(true).get(0));
                gameModel.setState(State.PUTSPAWN);
            
            } else {
            
                gameModel.setState(State.LOBBY);
            }
   
    }
    
    public void checkActionCount(){
        
        if (functionModel.getGameModel().getActionCount()==3){
           
           setErrorState("YOU ALREADY USE 3 ACTION. YOU CAN ONLY GO ON MENU OR PASS TURN (HERE YOU CAN RECHARGE YOUR WEAPON)");
        }
        
    }
    
    
    public void choseAction(RemoteView view){
    
        int choice;
        
        try {
            
            choice = view.getIndex();
            
            switch (choice){
                
                case -1:
                    this.functionModel.getGameModel().setState(State.MENU);
                    break;
                case 1:
                    checkActionCount();
                    this.functionModel.getGameModel().setState(State.SELECTRUN);
                    break;
                case 2:
                    checkActionCount();
                    this.functionModel.getGameModel().setState(State.SELECTGRAB);
                    break;
                case 3:
                    
                    checkActionCount();
                    if(this.functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons().size()>0) {
                        
                        this.functionModel.getGameModel().setState(State.SELECTWEAPON);
                    } else {
                        
                        setErrorState("YOU HAVE NOT WEAPON TO SHOOT");
                        
                    }
                    break;
                case 4:
    
                    checkActionCount();
                    if(this.functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().size()>0){
            
                        this.functionModel.getGameModel().setState(State.SELECTPOWERUP);
                    } else {
                        
                        setErrorState("YOU HAVE NOT POWER UP TO USE");
                    }
                    break;
                case 0:
                    
                    endActionGestor();
                    this.functionModel.getGameModel().setState(State.ENDACTIONSELECTION);
                    
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    
    }
    
    
    
    
    public void errorState(RemoteView view) throws RemoteException {
        
        System.out.println("ERROR STATE-->\n"+"ERROR MESSAGE: "+ functionModel.getGameModel().getErrorMessage() +"\nRESTART IN STATE CHOICE STATE-->");
        view.resetInput();
        resetParameterWeapon();
        switch (this.functionModel.getGameModel().getBeforeError()){
            
            case SELECTEFFECT:
                this.functionModel.getGameModel().setState(State.SELECTEFFECT);
                break;
            case SHOOT:
                this.functionModel.getGameModel().setState(State.SELECTEFFECT);
                break;
            case SELECTRECHARGE:
                if (functionModel.getGameModel().isEndTurn()){
                    
                    //if recharge don't go and player want to end turn go to respawn player
                    this.functionModel.getGameModel().setState(State.RESPWANPLAYERSELECTION);
                    
                }
                
            default:
                this.functionModel.getGameModel().setState(State.CHOSEACTION);
                break;
                
            
        }
    }
    
    public void startTurn() throws RemoteException {
        
        //set the lowest id to the current player
        this.functionModel.getGameModel().setActualPlayer(this.functionModel.getGameModel().getPlayers(true).get(0));
    
        //put ammo and weapon card
        refreshMapEndTurn();
        functionModel.getGameModel().getActualPlayer().getPlayerBoard().addWeapon(new Electroscythe());
        
        //now game can start
        this.functionModel.getGameModel().setState(State.CHOSEACTION);
        
    }
    
    private void refreshMapEndTurn(){
    
        //PUT CARD
        this.functionModel.refreshMapAmmoCard();
        this.functionModel.refreshMapWeaponCard();
        
    }
    
    public void resetParameterWeapon (){
    
        functionModel.getGameModel().setWeaponSelected(null);
        functionModel.getGameModel().setWeaponState(null);
        functionModel.getGameModel().setActualWeaponEffect(null);
        functionModel.getGameModel().setWeaponName(null);
        functionModel.getGameModel().setBeforeEffect(null);
        functionModel.getGameModel().getAvailableEffect().removeAll(functionModel.getGameModel().getAvailableEffect());
        functionModel.getGameModel().getWeaponToCharge().removeAll(functionModel.getGameModel().getWeaponToCharge());
    }
    
    public void menu(RemoteView view) throws RemoteException {
        
        if (view.getIndex()==1){
            
            functionModel.getGameModel().setState(State.CHOSEACTION);
            
        } else {
            
            functionModel.getGameModel().setState(State.MENU);
        }
    }
    
    //state gestor and map error gestor
    public void setErrorState(String string){
        
        if (functionModel.getGameModel().getAvailableEffect().contains(WeaponsEffect.BaseEffect) ||functionModel.getGameModel().getAvailableEffect().contains(WeaponsEffect.BaseMode)){
            functionModel.getGameModel().getAvailableEffect().removeAll(functionModel.getGameModel().getAvailableEffect());
        }
        this.functionModel.getGameModel().setErrorMessage(string);
        this.functionModel.getGameModel().setBeforeError(this.functionModel.getGameModel().getState());
        this.functionModel.getGameModel().setState(State.ERROR);
    }
    
    public void mapErrorGestor() throws RemoteException {
        
        this.functionModel.getGameModel().setMessageToCurrentView("YOUR INPUT IS NOT VALID");
        this.functionModel.getGameModel().setBeforeError(this.functionModel.getGameModel().getState());
        this.functionModel.getGameModel().setState(State.ERROR);
    }
    
    
    public void runActionController (RemoteView view) throws RemoteException {
        
        //take necessary
        Map map= this.functionModel.getGameModel().getMap();
        Square inputSquare;
        
        try {
            
            inputSquare = map.getSquare(view.getRow(),view.getColumn());
            
            if(map.existInMap(inputSquare)) {
                
                this.functionModel.runFunctionModel(inputSquare);
                this.functionModel.getGameModel().setMessageToAllView("CURRENT PLAYER " + this.functionModel.getGameModel().getActualPlayer().getName().toString() +" MOVED IN SQUARE: " + inputSquare.toString());
                this.functionModel.getGameModel().setState(State.RUN);
                
            }
        } catch (MapException e) {
    
            mapErrorGestor();
            
        } catch (RunActionMaxDistLimitException e) {
            
            this.functionModel.getGameModel().setMessageToCurrentView("YOUR MOVE EXCED MAX DISTANCE LIMIT");
            this.functionModel.getGameModel().setBeforeError(this.functionModel.getGameModel().getState());
            this.functionModel.getGameModel().setState(State.ERROR);
        }
        
    }
    
    public void run(RemoteView view) throws RemoteException {
        
        view.resetInput();
        functionModel.getGameModel().incrementgetSpawnedPlayer();
        this.functionModel.getGameModel().setState(State.CHOSEACTION);
       
    }
    
    public void grabActionController (RemoteView view) throws RemoteException{
    
        //take necessary
        Map map = this.functionModel.getGameModel().getMap();
        
        //answer to view an input Square
        Square inputSquare;
        
        try {
            inputSquare = map.getSquare(view.getRow(), view.getColumn());
    
            int indexWeapon = -1;
    
            //temp variables
            if (map.existInMap(inputSquare)) {
        
            try {
                
                //guardo se la square è di generation, se si devo chidere alla view l'index dell'arma
                if (map.isGenerationSquare(inputSquare)) {
                    
                    //effective catch gia con l'index giusto se è una Generation Square
                    indexWeapon = view.getIndex();
                    this.functionModel.grabActionModel(inputSquare, indexWeapon);
                    this.functionModel.getGameModel().setState(State.GRAB);
                    
                } else {
    
                    NormalSquare asNormal = (NormalSquare) inputSquare;
                    if (asNormal.containAmmoCard()) {
    
                        this.functionModel.grabActionModel(inputSquare, indexWeapon);
                        this.functionModel.getGameModel().setState(State.GRAB);
                    } else {
                        
                        setErrorState("THIS SQUARE NOT CONTAIN AMMO CARD TO GRAB");
                    }
                }
        
                
                } catch(GrabActionMaxDistLimitException catchActionMaxDistExpetion){
    
                    this.functionModel.getGameModel().setMessageToCurrentView("YOUR MOVE FOR GRAB EXCED MAX DISTANCE LIMIT");
                    this.functionModel.getGameModel().setBeforeError(this.functionModel.getGameModel().getState());
                    this.functionModel.getGameModel().setState(State.ERROR);
    
                } catch(GrabActionFullObjException e){
    
                    this.functionModel.getGameModel().setMessageToCurrentView("YOU DON'T HAVE MORE SPACE FOR GRAB OBJECT");
                    this.functionModel.getGameModel().setBeforeError(this.functionModel.getGameModel().getState());
                    this.functionModel.getGameModel().setState(State.ERROR);
                } catch(MapException e){
    
                    mapErrorGestor();
                }
            }else{
    
                mapErrorGestor();
            }
        } catch(MapException e){
    
            mapErrorGestor();
        }
        
    }
    
    
    
    public void grab(RemoteView view) throws RemoteException {
    
        view.resetInput();
        functionModel.getGameModel().incrementActionCount();
        this.functionModel.getGameModel().setState(State.CHOSEACTION);
        
    }
    
    public void selectPowerUp(RemoteView view) throws RemoteException {
        
        PowerUpCard powerUpCard;
        
        int i;
        
        i = view.getIndex();
        
        if (this.functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().get(i) != null) {
            
            powerUpCard = this.functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().get(i);
            
            if(!powerUpCard.getNameCard().equals("TAGBACK GRENADE")) {
                
                this.functionModel.getGameModel().setPowerUpSelected(powerUpCard);
                this.functionModel.getGameModel().setState(State.SELECTPOWERUPINPUT);
            } else {
                
                this.functionModel.getGameModel().setMessageToCurrentView("YOU CAN'T USE TAGBACK GRENADE IN YOUR TURN");
                this.functionModel.getGameModel().setState(State.ERROR);
            }
        } else {
            
            mapErrorGestor();
        }
        
    }
    
    public void selectPowerUpInput (RemoteView view) throws RemoteException{

        //NEWTON
        if (Newton.class.equals(this.functionModel.getGameModel().getPowerUpSelected().getClass())) {
            Player targetPlayer;
            Square targetSquare;
            
            try {
                //get input
                targetPlayer = this.functionModel.getGameModel().getPlayerById(view.getTarget1());
                targetSquare = this.functionModel.getGameModel().getMap().getSquare(view.getRow(), view.getColumn());
                //effect
                this.functionModel.usePowerUpNewton((Newton)this.functionModel.getGameModel().getPowerUpSelected(),targetPlayer, targetSquare);
                functionModel.getGameModel().setMessageToAllView("CURRENT PLAYER " + functionModel.getGameModel().getActualPlayer().getName() +" USE POWER UP NEWTON");
                functionModel.getGameModel().setState(State.USEPOWERUP);
            } catch (NotInSameDirection notInSameDirection) {
                
                setErrorState("ERROR: THE CHOSEN TARGETS ARE NOT IN THE SAME DIRECTION");
            } catch (NotValidDistance notValidDistance) {
                
                setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
            } catch (MapException e) {
                mapErrorGestor();
            }
            //TAGBACK GRANATE
        } else if (this.functionModel.getGameModel().getPowerUpSelected().getClass().equals(TagBackGrenade.class)) {
    
            Player targetPlayer;
            try {
                
                //get input
                targetPlayer = this.functionModel.getGameModel().getPlayerById(view.getTarget1());
                //effect
                this.functionModel.usePowerUpTagBackGrenade((TagBackGrenade) this.functionModel.getGameModel().getPowerUpSelected(), targetPlayer);
                functionModel.getGameModel().setMessageToAllView("CURRENT PLAYER " + functionModel.getGameModel().getActualPlayer().getName() +" USE POWER UP TAGBACK GRENADE");
                functionModel.getGameModel().setState(State.USEPOWERUP);
            } catch (NotVisibleTarget notVisibleTarget) {
    
                setErrorState("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
            } catch (MapException e) {
                mapErrorGestor();
            }
    
    
            //TELEPORTER
        } else if (this.functionModel.getGameModel().getPowerUpSelected().getClass().equals(Teleporter.class)) {
    
            Square targetSquare;
            try {
    
                //get input
                targetSquare = this.functionModel.getGameModel().getMap().getSquare(view.getRow(), view.getColumn());
                //effect
                this.functionModel.usePowerUpTeleporter((Teleporter) this.functionModel.getGameModel().getPowerUpSelected(), targetSquare);
                functionModel.getGameModel().setMessageToAllView("CURRENT PLAYER " + functionModel.getGameModel().getActualPlayer().getName() +" USE POWER UP TELEPORTER");
                functionModel.getGameModel().setState(State.USEPOWERUP);
            
            }catch (MapException e) {
                
                mapErrorGestor();
            }
    
            //TARGETING SCOPE
        } else if (this.functionModel.getGameModel().getPowerUpSelected().getClass().equals(TargetingScope.class)) {
    
            Player targetPlayer;
            
            try {
                
                //get input
                targetPlayer = this.functionModel.getGameModel().getPlayerById(view.getTarget1());
                //effect
                this.functionModel.usePowerUpTargetingScope((TargetingScope) this.functionModel.getGameModel().getPowerUpSelected(), targetPlayer);
                functionModel.getGameModel().setMessageToAllView("CURRENT PLAYER " + functionModel.getGameModel().getActualPlayer().getName() +" USE POWER UP TARGETING SCOPE");
                functionModel.getGameModel().setState(State.USEPOWERUP);
            } catch (MapException e) {
                
                mapErrorGestor();
            }
        }
    }
    
    public void usePowerUp(RemoteView view) throws RemoteException {
    
        view.resetInput();
        functionModel.getGameModel().incrementActionCount();
        this.functionModel.getGameModel().setState(State.CHOSEACTION);
    }
    
    
    
    public void selectRecharge (RemoteView view,int i,ArrayList<EnumColorCardAndAmmo> payExtra ) throws RemoteException {
    
       
        ArrayList<PowerUpCard> powerUpToPay = new ArrayList<>();
        if (i==1) {
        if (view.getIndex2() != -1) {
    
            powerUpToPay.add(functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().get(view.getIndex2()));
    
            if (view.getIndex3() != -1) {
        
                powerUpToPay.add(functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().get(view.getIndex3()));
        
                if (view.getIndex4() != -1) {
            
                    powerUpToPay.add(functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().get(view.getIndex4()));
            
                    if (view.getIndex5() != -1) {
                
                        powerUpToPay.add(functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().get(view.getIndex2()));
                
                    }
                }
            }
        }
        
            try {
                WeaponCard weaponToCharge = functionModel.getGameModel().getWeaponToCharge().get(view.getIndex());
                payAmmoController(weaponToCharge.getRechargeCost(), powerUpToPay);
            } catch (NotValidAmmoException e) {
        
                setErrorState("YOU HAVE NOT AMMO TO PAY, OR YOUR INPUT ABOUT POWER UP IS NOT CORRECT");
            }
        } else  if(i==2){
    
            try {
                payAmmoController(payExtra, powerUpToPay);
            } catch (NotValidAmmoException e) {
    
                setErrorState("YOU HAVE NOT AMMO TO PAY, OR YOUR INPUT ABOUT POWER UP IS NOT CORRECT");
            }
        }
    }
    
    public void payAmmoController (ArrayList<EnumColorCardAndAmmo> toPay , ArrayList<PowerUpCard> powerUpToPay) throws NotValidAmmoException {
    
        PlayerBoard actualPlayerBoard = functionModel.getGameModel().getActualPlayer().getPlayerBoard();
        boolean canGo = false;
        
        //check for pay
        //available
        int redAv = Collections.frequency(actualPlayerBoard.getAmmo(), EnumColorCardAndAmmo.RED);
        int yellowAv = Collections.frequency(actualPlayerBoard.getAmmo(), EnumColorCardAndAmmo.YELLOW);
        int blueAv = Collections.frequency(actualPlayerBoard.getAmmo(), EnumColorCardAndAmmo.BLU);
        //to pay
        int redToPay = Collections.frequency(toPay, EnumColorCardAndAmmo.RED);
        int yellowToPay = Collections.frequency(toPay, EnumColorCardAndAmmo.YELLOW);
        int blueToPay = Collections.frequency(toPay, EnumColorCardAndAmmo.BLU);
        
        //add powerUp value in available
        if (powerUpToPay.size()>0) {
            
            for (PowerUpCard power : powerUpToPay) {
        
                if (power.getColorPowerUpCard() == EnumColorCardAndAmmo.RED) {
            
                    redAv++;
                }
                if (power.getColorPowerUpCard() == EnumColorCardAndAmmo.BLU) {
            
                    blueAv++;
                }
                if (power.getColorPowerUpCard() == EnumColorCardAndAmmo.YELLOW) {
            
                    yellowAv++;
                }
        
            }
        }
        
        if (redToPay > 0) {
            canGo = redToPay <= redAv;
        }
        if (blueToPay > 0) {
            canGo = blueToPay <= blueAv;
        }
        if (yellowToPay > 0) {
            canGo = yellowToPay <= yellowAv;
        }
        
        if(canGo) {
            
            if (powerUpToPay.size() != 0) {
        
                //pay with ammo and powerUp selected
        
                //first pay powerUp
                for (int i = 0; i < powerUpToPay.size(); i++) {
                    PowerUpCard power = powerUpToPay.get(i);
            
                    if (toPay.contains(power.getColorPowerUpCard())) {
                
                        powerUpToPay.remove(power);
                        actualPlayerBoard.getPlayerPowerUps().remove(power);
                        toPay.remove(power.getColorPowerUpCard());
                        i--;
                    }
                }
            }
            if (toPay.size() > 0) {
        
                // pay with ammo
                for (int i = 0; i < toPay.size(); i++) {
            
                    EnumColorCardAndAmmo pay = toPay.get(i);
                    actualPlayerBoard.decreaseAmmo(pay);
                    toPay.remove(pay);
                    i--;
                }
            }
        } else {
            
            throw new NotValidAmmoException();
        }
    }
    
    
    public void recharge(RemoteView view) throws RemoteException {
        
        
        view.resetInput();
        if (functionModel.getGameModel().isEndTurn()) {
            
            functionModel.getGameModel().setState(State.CHOSEACTION);
        } else {
    
            functionModel.getGameModel().setState(State.ENDACTIONSELECTION);
        }
    }
    
    public void drawnPowerUp () throws RemoteException {
        
        GameModel gameModel = this.functionModel.getGameModel();
        
        for(Player a :gameModel.getPlayers(true)){
            ArrayList<PowerUpCard> tempPowerUp = new ArrayList<>();
            tempPowerUp.add(gameModel.getPowerUpDeck().drawnPowerUpCard());
            tempPowerUp.add(gameModel.getPowerUpDeck().drawnPowerUpCard());
            //System.out.println(tempPowerUp.toString());
            a.setPowerUpCardsSpawn(tempPowerUp);
        }
        gameModel.setState(State.FIRSTSPAWN);
    }
    
    public void firstSpawn(RemoteView view) throws RemoteException {
        
        //put 2 power up for all palyer in game
        
        if (functionModel.getGameModel().getSpawnedPlayer()==functionModel.getGameModel().getPlayers(true).size()){
            
            //all player are spawned correctly. Now can start the turn.
            functionModel.getGameModel().setState(State.STARTTURN);
        } else {
            
            for (Player a : functionModel.getGameModel().getPlayers(true)) {
        
                if (a.getRow() == -1 && a.getColumn() == -1) {
            
                    functionModel.getGameModel().setSpawnPlayer(a);
                    functionModel.getGameModel().setState(State.SELECTSPAWN);
                }
            }
        }
    }
    
    public void selectSpawn(RemoteView view) throws RemoteException {
        
        if(view.getUser().equals(functionModel.getGameModel().getSpawnPlayer().getName())){
            
            respawnPlayerController(functionModel.getGameModel().getSpawnPlayer(),view);
            functionModel.getGameModel().incrementgetSpawnedPlayer();
            functionModel.getGameModel().setState(State.FIRSTSPAWN);
            
        } else {
            //vedere cosa gare
        }
        
    }
    
    public void respawnPlayerController (Player player, RemoteView view) throws RemoteException {
        
        int chosenPowerUp;
        EnumColorSquare colorSquare;
        PowerUpCard  powerUpCard;
        
        try {
            
            chosenPowerUp = view.getIndex();
            
            if(player.getPowerUpCardsSpawn().get(chosenPowerUp)!=null){
                
                //get the color to respawn
                colorSquare = player.getPowerUpCardsSpawn().get(chosenPowerUp).getColorRespawn();
                player.getPowerUpCardsSpawn().remove(chosenPowerUp);
                //add player on generation square of the color chosed
                this.functionModel.getGameModel().getMap().addPlayerOnSquare(this.functionModel.getGameModel().getMap().getGenerationSquare(colorSquare),player);
                
                //add the other power up to player list
                powerUpCard = player.getPowerUpCardsSpawn().get(0);
                player.getPlayerBoard().getPlayerPowerUps().add(powerUpCard);
                player.getPowerUpCardsSpawn().remove(0);
                
            } else {
                
                setErrorState("INPUT FOR SPAWN NOT CORRECT");
            }
            
        } catch (MapException e) {
            
            mapErrorGestor();
        
        }
    }
    
    public void setWeaponToCharge(){
        
        for (WeaponCard a: functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons()){
            
            if (!a.isCharge()){
                
                functionModel.getGameModel().getWeaponToCharge().add(a);
            }
        }
        
    }
    
    public void endActionSelect(RemoteView view) throws RemoteException {
        
        if (view.isBooleanChose()){
            
            functionModel.getGameModel().setEndTurn(true);
            functionModel.getGameModel().setState(State.SELECTRECHARGE);
        } else {
            
            functionModel.getGameModel().setState(State.RESPWANPLAYERSELECTION);
        }
    }
    
    public void endActionGestor (){
    
        setWeaponToCharge();
        functionModel.getGameModel().setDeadPlayer();
        
    
    }
    
    public void scoringPlayerBoardController (){
        
        //get dead Player
        ArrayList<Player> deadPlayer = this.functionModel.getGameModel().getDeadPlayers();
        
        for (Player a:deadPlayer){
            //incasso una plancia alla volta e gestisco le mort
            this.functionModel.scoringPlayerBoard(a);
        }
    }
}


