package it.polimi.controller;


import it.polimi.model.*;
//chiedere perche devo importare tutto
import it.polimi.model.Exception.*;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;
import it.polimi.model.Weapon.*;
import it.polimi.view.RemoteView;

import java.rmi.RemoteException;
import java.time.chrono.HijrahEra;
import java.util.ArrayList;


public class FunctionController {
    
    private State beforeError;
    WeaponCard weaponSelected; //current weapon for current Player
    PowerUpCard powerUpSelected; //current weapon effect for current Player
    String weaponName;
    WeaponsEffect beforeEffect;
    int playerDamage;
    Player playerDameged;
    ArrayList<State> actionCurrentCompleted = new ArrayList<>();
    
    
    public void lobby(FunctionModel functionModel, RemoteView view){
    
        GameModel gameModel = functionModel.getGameModel();
    
        try {
            gameModel.setPlayers(new Player(gameModel.getPlayers(true).size()+1, view.getUser(), gameModel.getRandomColor() ));
            
            System.out.println(gameModel.getActualPlayer().toString());
            
            if (gameModel.getPlayers(true).size() == 3) {
             
                //gestione quando non ci sono abbastanza player
                } else if (gameModel.getPlayers(true).size() == 1) {
                
                    // game can start
                    drawnPowerUp(functionModel);
                    gameModel.setState(State.SPAWNPLAYER);
                    
                } else {
                
                    gameModel.setState(State.LOBBY);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
    }
    
    public void drawnPowerUp (FunctionModel functionModel) throws RemoteException {
        
        GameModel gameModel = functionModel.getGameModel();
        Player a = gameModel.getActualPlayer();
            
        ArrayList<PowerUpCard> tempPowerUp = new ArrayList<>();
        tempPowerUp.add(gameModel.getPowerUpDeck().drawnPowerUpCard());
        tempPowerUp.add(gameModel.getPowerUpDeck().drawnPowerUpCard());
        System.out.println(tempPowerUp.toString());
        a.setPowerUpCardsSpawn(tempPowerUp);
        
        System.out.println(gameModel.getActualPlayer().toString());
    }
    
    
    public void choseAction(FunctionModel functionModel, RemoteView view){
    
        int choice;
        
        try {
            
            choice = view.getIndex();
            
            switch (choice){
                
                case -1:
                    functionModel.getGameModel().setState(State.MENU);
                    break;
                case 1:
                    functionModel.getGameModel().setState(State.SELECTRUN);
                    break;
                case 2:
                    functionModel.getGameModel().setState(State.SELECTGRAB);
                    break;
                case 3:
                    
                    if(functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons().size()>0) {
                        
                        functionModel.getGameModel().setState(State.SELECTWEAPON);
                    } else {
                        
                        functionModel.getGameModel().setMessageToCurrentView("YOU HAVE NOT WEAPON TO SHOOT");
                        beforeError= functionModel.getGameModel().getState();
                        functionModel.getGameModel().setState(State.ERROR);
                        
                    }
                    break;
                case 4:
                    
                    if(functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().size()>0){
    
                        functionModel.getGameModel().setState(State.SELECTPOWERUP);
                    } else {
        
                        functionModel.getGameModel().setMessageToCurrentView("YOU HAVE NOT POWER UP TO USE");
                        beforeError= functionModel.getGameModel().getState();
                        functionModel.getGameModel().setState(State.ERROR);
                
                    }
            }
            
            
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    
    }
    
    
    public void errorState(FunctionModel functionModel) throws RemoteException {
        
        System.out.println("ERROR STATE-->\nRESTART IN STATE CHOICE-->");
        
        switch (beforeError){
            case SELECTWEAPON:
                functionModel.getGameModel().setState(State.CHOSEACTION);
            case SELECTPOWERUP:
                functionModel.getGameModel().setState(State.CHOSEACTION);
            case SELECTRUN:
                functionModel.getGameModel().setState(State.CHOSEACTION);
            case SELECTEFFECT:
                functionModel.getGameModel().setState(State.SELECTEFFECT);
            case SELECTGRAB:
                functionModel.getGameModel().setState(State.CHOSEACTION);
            case SELECTPOWERUPINPUT:
                functionModel.getGameModel().setState(State.CHOSEACTION);
            case CHOSEACTION:
        }
    }
    
    public void startTurn(FunctionModel functionModel, RemoteView view) throws RemoteException {
        
        //set the lowest id to the current player
        functionModel.getGameModel().setActualPlayer(functionModel.getGameModel().getPlayers(true).get(0));
    
        //put ammo and weapon card
        refreshMapEndTurn(functionModel);
        
        //now game can start
        functionModel.getGameModel().setState(State.CHOSEACTION);
        
    }
    
    private void refreshMapEndTurn(FunctionModel functionModel){
    
        //PUT CARD
        functionModel.refreshMapAmmoCard();
        functionModel.refreshMapWeaponCard();
        
    }
    
    
    //state gestor and map error gestor
    public void setErrorState(FunctionModel functionModel, String string){
        
        functionModel.getGameModel().setErrorMessage(string);
        this.beforeError = functionModel.getGameModel().getState();
        functionModel.getGameModel().setState(State.ERROR);
    }
    
    public void mapErrorGestor(FunctionModel functionModel) throws RemoteException {
        
        functionModel.getGameModel().setMessageToCurrentView("YOUR INPUT IS NOT CORRECT");
        this.beforeError = functionModel.getGameModel().getState();
        functionModel.getGameModel().setState(State.ERROR);
    }
    
    
    public void runActionController (FunctionModel functionModel, RemoteView view) throws RemoteException {
        
        //take necessary
        Map map= functionModel.getGameModel().getMap();
        Square inputSquare;
        
        try {
            
            inputSquare = map.getSquare(view.getRow(),view.getColumn());
            
            if(map.existInMap(inputSquare)) {
                
                functionModel.runFunctionModel(inputSquare);
                view.resetInput();
                functionModel.getGameModel().setMessageToAllView("CURRENT PLAYER " + functionModel.getGameModel().getActualPlayer().getName().toString() +" MOVED IN SQUARE: " + inputSquare.toString());
                functionModel.getGameModel().setState(State.RUN);
                
            }
        } catch (MapException e) {
    
            mapErrorGestor(functionModel);
            
        } catch (RunActionMaxDistLimitException e) {
            
            functionModel.getGameModel().setMessageToCurrentView("YOUR MOVE EXCED MAX DISTANCE LIMIT");
            this.beforeError= functionModel.getGameModel().getState();
            functionModel.getGameModel().setState(State.ERROR);
        }
        
    }
    
    public void run(FunctionModel functionModel) throws RemoteException {
    
        functionModel.getGameModel().setState(State.CHOSEACTION);
        
    }
    
    
    public void grabActionController (FunctionModel functionModel, RemoteView view) throws RemoteException{
    
        //take necessary
        Map map = functionModel.getGameModel().getMap();
        
        
        //answer to view an input Square
        Square inputSquare;
        try {
            inputSquare = map.getSquare(view.getRow(), view.getColumn());
    
            int indexWeapon = -1;
    
            //temp variables
            if (map.existInMap(inputSquare)) {
        
        
                //guardo se la square è di generation, se si devo chidere alla view l'index dell'arma , altrimenti passo a null
                if (map.isGenerationSquare(inputSquare)) {
                    indexWeapon = view.getIndex();
                }
        
                //effective catch gia con l'index giusto se è una Generation Square
                try {
                    
                    functionModel.grabActionModel(inputSquare, indexWeapon);
                    view.resetInput();
                    functionModel.getGameModel().setState(State.GRAB);
                        
                    
                } catch(GrabActionMaxDistLimitException catchActionMaxDistExpetion){
    
                    functionModel.getGameModel().setMessageToCurrentView("YOUR MOVE FOR GRAB EXCED MAX DISTANCE LIMIT");
                    this.beforeError = functionModel.getGameModel().getState();
                    functionModel.getGameModel().setState(State.ERROR);
    
                } catch(GrabActionFullObjException e){
    
                    functionModel.getGameModel().setMessageToCurrentView("YOU DON'T HAVE MORE SPACE FOR GRAB OBJECT");
                    this.beforeError = functionModel.getGameModel().getState();
                    functionModel.getGameModel().setState(State.ERROR);
                } catch(MapException e){
    
                    mapErrorGestor(functionModel);
                }
            }else{
    
                mapErrorGestor(functionModel);
            }
        } catch(MapException e){
    
            mapErrorGestor(functionModel);
        }
        
    }
    
    
    
    public void grab(FunctionModel functionModel) throws RemoteException {
    
        functionModel.getGameModel().setState(State.CHOSEACTION);
    }
    
    public void selectPowerUp(FunctionModel functionModel, RemoteView view) throws RemoteException {
        
        PowerUpCard powerUpCard;
        
        int i;
        
        i = view.getIndex();
        
        if (functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().get(i) != null) {
            
            powerUpCard = functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().get(i);
            
            if(!powerUpCard.getNameCard().equals("TAGBACK GRENADE")) {
                
                powerUpSelected = powerUpCard;
                functionModel.getGameModel().setState(State.SELECTPOWERUPINPUT);
            } else {
                
                functionModel.getGameModel().setMessageToCurrentView("YOU CAN'T USE TAGBACK GRENADE IN YOUR TURN");
                this.beforeError = functionModel.getGameModel().getState();
                functionModel.getGameModel().setState(State.ERROR);
            }
        } else {
            
            mapErrorGestor(functionModel);
        }
        
    }
    
    public void usePowerUpController(FunctionModel functionModel, RemoteView view) throws RemoteException{

        //NEWTON
        if (Newton.class.equals(powerUpSelected.getClass())) {
            Player targetPlayer;
            Square targetSquare;
            
            try {
                //get input
                targetPlayer = functionModel.getGameModel().getPlayerById(view.getTarget1());
                targetSquare = functionModel.getGameModel().getMap().getSquare(view.getRow(), view.getColumn());
                //effect
                    functionModel.usePowerUpNewton((Newton)powerUpSelected,targetPlayer, targetSquare);
            } catch (NotInSameDirection notInSameDirection) {


            } catch (NotValidDistance notValidDistance) {

  
            } catch (MapException e) {
    
            }
            //TAGBACK GRANATE
        } else if (powerUpSelected.getClass().equals(TagBackGrenade.class)) {
    
            Player targetPlayer;
            try {
                
                //get input
                targetPlayer = functionModel.getGameModel().getPlayerById(view.getTarget1());
                //effect
                functionModel.usePowerUpTagBackGrenade((TagBackGrenade) powerUpSelected, targetPlayer);
            } catch (NotVisibleTarget notVisibleTarget) {

            } catch (MapException e) {
            
            }
    
    
            //TELEPORTER
        } else if (powerUpSelected.getClass().equals(Teleporter.class)) {
    
            Square targetSquare;
            try {
    
                //get input
                targetSquare = functionModel.getGameModel().getMap().getSquare(view.getRow(), view.getColumn());
                //effect
                functionModel.usePowerUpTeleporter((Teleporter) powerUpSelected, targetSquare);
            }catch (MapException e) {
                e.printStackTrace();
            }
    
            //TARGETING SCOPE
        } else if (powerUpSelected.getClass().equals(TargetingScope.class)) {
    
            Player targetPlayer;
            
            try {
                
                //get input
                targetPlayer = functionModel.getGameModel().getPlayerById(view.getTarget1());
                //effect
                functionModel.usePowerUpTargetingScope((TargetingScope) powerUpSelected, targetPlayer);
            } catch (MapException e) {
            
            }
        }
    }
    
    public void rechargeController(Player player, ArrayList<WeaponCard> weapon,RemoteView view){
    
        int viewSelection;
        WeaponCard weaponToCharge;
        
        try {
        //fino a che ho armi disponibili
        while (weapon.size()>0) {

            // faccio scegliere al player quali armi sono scariche,
            // mi tornerà un index, che setto qui sotto
            viewSelection = view.getIndex();
            
            if(weapon.get(viewSelection)!=null) {
    
                weaponToCharge = weapon.get(viewSelection);
                //TODO AVANTI DA QUI
                //prendo il costo di ricarica
                ArrayList<EnumColorCardAndAmmo> rechargeCost = weaponToCharge.getRechargeCost();
                
                payAmmoController(player, rechargeCost,weaponToCharge,view);
                weaponToCharge.setCharge(true);
                weapon.remove(0);
            } else {
                
                //errore input
            }
            
        }
        } catch (NotValidAmmoException e) {
        
        } catch (NoPowerUpAvailable noPowerUpAvailable) {
            noPowerUpAvailable.printStackTrace();
        } catch (RemoteException e) {
        
        }
    }
    
    public void payAmmoController (Player player, ArrayList<EnumColorCardAndAmmo> ammoToPay,WeaponCard weaponCard,RemoteView view) throws NotValidAmmoException, NoPowerUpAvailable, RemoteException {

        //prendo playerboard
        PlayerBoard playerBoard = player.getPlayerBoard();

        //var temporanee
        ArrayList<EnumColorCardAndAmmo> availableAmmo = new ArrayList<>(playerBoard.getAmmo());
        ArrayList<EnumColorCardAndAmmo> availablePowerUpAsAmmo = new ArrayList<>();
    
        if (playerBoard.getAmmo().containsAll(weaponCard.getRechargeCost())) {
            
            //pago e rendo carica l'arma
            playerBoard.decreaseAmmo(ammoToPay);

        } else {

            // non bastano le semplici ammo
            //verifico allora se usando i power up può pagare,
            if (availablePowerUpAsAmmo.size()==0){
        
                throw  new NoPowerUpAvailable();
            } else {
                
                for (PowerUpCard a : playerBoard.getPlayerPowerUps()) {
        
                    availablePowerUpAsAmmo.add(a.getColorPowerUpCard());
                }
            }

            ArrayList<EnumColorCardAndAmmo> tempAvaible = new ArrayList<>();
            tempAvaible.addAll(availableAmmo);
            tempAvaible.addAll(availablePowerUpAsAmmo);
            
            if (tempAvaible.containsAll(ammoToPay)) {

                // posso pagare usando ammo e power up

                // chiedo alla view se lo vuole fare
                Boolean viewAnswer = view.isBooleanChose();

                if (viewAnswer) {
    
                    for (int i = 0; i < ammoToPay.size(); i++) {
                        EnumColorCardAndAmmo a = ammoToPay.get(i);
        
                        if (availableAmmo.contains(a)) {
            
                            playerBoard.decreaseAmmo(a);
                        } else if (availablePowerUpAsAmmo.contains(a)) {
            
                            playerBoard.getPlayerPowerUps().remove(a);
                        } else {
                            throw new NotValidAmmoException();
                        }
                    }
                }
            } else {
                throw new NotValidAmmoException();
            }
        }
        if(weaponCard!=null){
            weaponCard.setCharge(true);
        }
    }
    
    
    public void respawnPlayerController (FunctionModel model, RemoteView view){
        
        int chosedPowerUp;
        EnumColorSquare colorSquare;
        PowerUpCard  powerUpCard;
        Player player = model.getGameModel().getActualPlayer();
        
        
        try {
            
            chosedPowerUp = view.getIndex();
            
            if(player.getPowerUpCardsSpawn().get(chosedPowerUp)!=null){
                
                //get the color to respawn
                colorSquare = player.getPowerUpCardsSpawn().get(chosedPowerUp).getColorRespawn();
                player.getPowerUpCardsSpawn().remove(chosedPowerUp);
                //add player on generation square of the color chosed
                model.getGameModel().getMap().addPlayerOnSquare(model.getGameModel().getMap().getGenerationSquare(colorSquare),player);
                
                //add the other power up to player list
                powerUpCard = player.getPowerUpCardsSpawn().get(0);
                player.getPlayerBoard().getPlayerPowerUps().add(powerUpCard);
                player.getPowerUpCardsSpawn().remove(0);
                
                model.getGameModel().setState(State.STARTTURN);
                
            } else {
                
                //errore di input
            }
            
        } catch (RemoteException e) {
        
        } catch (MapException e) {
        
        }
    }
    
    
    
    public void scoringPlayerBoardController (FunctionModel functionModel){
        
        //get dead Player
        ArrayList<Player> deadPlayer = functionModel.getGameModel().getDeadPlayers();
        
        for (Player a:deadPlayer){
            //incasso una plancia alla volta e gestisco le mort
            functionModel.scoringPlayerBoard(a);
        }
    
    }
    
    
    public void selectWeapon(FunctionModel functionModel, RemoteView view) throws RemoteException {
    
        int i;
        WeaponCard weapon;
        GameModel gameModel = functionModel.getGameModel();
        
        i = view.getIndex();
        if(functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons().get(i)!=null){
            
            weapon = functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons().get(i);
            
            if(weapon.isCharge()){
                
                //weapon is set controller
                this.weaponSelected = weapon;
                String name = weapon.getNameWeaponCard();
                
                
                //set extra state
                switch (name) {
                    case "CYBERBLADE":
                        
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Cyberblade);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "ELECTOSCYTHE":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Electroscythe);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "FLAME THROWER":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Flamethrower);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "FURNACE":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Furnace);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "GRENADE LAUNCHER":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.GrenadeLauncher);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "HEAT SEEKER":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Heatseeker);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "HELLION":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Hellion);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "LOCK RIFLE":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.LockRifle);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "MACHINE GUN":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.MachineGun);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "PLASMA GUN":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.PlasmaGun);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "POWER GLOVE":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.PowerGlove);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "RAILGUN":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Railgun);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "ROCKET LAUNCHER":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.RocketLauncher);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "SHOCKWAVE":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Shockwave);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "SHOTGUN":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Shotgun);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "SLADGEHAMMER":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Sledgehammer);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "T.H.O.R":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Thor);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "TRACTOR BEAM":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.TractorBeam);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "VORTEX CANNON":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.VortexCannon);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "WHISPER":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Whisper);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                    case "ZX-2":
    
                        this.weaponSelected = weapon;
                        this.weaponName = weaponSelected.getNameWeaponCard();
                        gameModel.setWeaponState(WeaponState.Zx2);
                        //set the effect list
                        setListEffect(functionModel,weapon);
                        break;
                }
                } else {
                
                //weapon not charge
                setErrorState(functionModel,"THIS WEAPON IS NOT CHARGE: YOU CAN'T USE IT NOW");
            }
            
        gameModel.setState(State.SELECTEFFECT);
        
        } else {
            
            
            mapErrorGestor(functionModel);
        }
        
    }
    
    public void setListEffect(FunctionModel functionModel, WeaponCard weapon){
        
        GameModel gameModel = functionModel.getGameModel();
        
        if(!weapon.isOptional()){
            
            gameModel.setAvailableEffect(weapon.getWeaponEffects());
        } else {
            
            //for the moment take the first, for complex weapon, switch with list of avaible effect
            ArrayList<WeaponsEffect> baseArray = new ArrayList<>();
            baseArray.add(weapon.getWeaponEffects().get(0));
            gameModel.setAvailableEffect(baseArray);
        }
        
        
    }
    
    
    public void selectWeaponEffect(FunctionModel functionModel, RemoteView view) throws RemoteException {
        
        GameModel gameModel= functionModel.getGameModel();
        int i;
    
        i = view.getIndex2();
        if (gameModel.getAvailableEffect().get(i)!=null) {
            
            gameModel.setWeaponsEffect(weaponSelected.getWeaponEffects().get(i));
        } else {
    
            setErrorState(functionModel,"NOT VALID INPUT");
        }
        gameModel.setState(State.SELECTSHOOTINPUT);
    }
    
    public WeaponCard getCorrectWeapon(FunctionModel functionModel,String weaponName) throws NotValidInput {
        
        for (WeaponCard a: functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerWeapons()){
            
            if(a.getNameWeaponCard().equals(weaponName)){
                
                return a;
            }
        }
        throw  new NotValidInput();
    }
    
    public void selectShootInput(FunctionModel functionModel, RemoteView view) throws RemoteException {
        
        GameModel gameModel = functionModel.getGameModel();
    
        try {
            switch (functionModel.getGameModel().getWeaponState()){
                
                case LockRifle:
    
                    LockRifle lockRifle = (LockRifle) getCorrectWeapon(functionModel,this.weaponName);
                    
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseEffect :
                            
                            this.LockRifleweapon(gameModel,lockRifle,view);
                            
                            break;
                        //II effect
                        case SecondLockEffect :
    
                            this.LockRifleweapon(gameModel,lockRifle,view);
    
                            break;
                    
                    }
                    break;
            
                case Electroscythe:
                
                    Electroscythe electroscythe = (Electroscythe) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.ElectroscytheWeapon(gameModel,electroscythe,view);
                            
                            break;
                        //II effect
                        case ReaperMode :
    
                            this.ElectroscytheWeapon(gameModel,electroscythe,view);
    
                            break;
                    
                    }
                    break;
                    
                case MachineGun:
                
                    MachineGun machineGun = (MachineGun) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseEffect :
                        
                            this.MachineGun(gameModel,machineGun,view);
                            
                            break;
                        //II effect
                        case FocusShotEffect :
    
                            this.MachineGun(gameModel,machineGun,view);
    
                            break;
                        //III effect
                        case TurretTripodEffect :
    
                            this.MachineGun(gameModel,machineGun,view);
    
                            break;
                    
                    }
                    break;
                    
                case TractorBeam:
                
                    TractorBeam tractorBeam = (TractorBeam) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.TractorBeam(gameModel,tractorBeam,view);
                            
                            break;
                        //II effect
                        case PunisherMode :
                            
                            this.TractorBeam(gameModel,tractorBeam,view);
    
                            break;
                    
                    }
                    break;
                    
                case Thor:
                
                    Thor thor =(Thor) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseEffect :
                        
                            this.Thor(gameModel,thor,view);
                            
                            break;
                        //II effect
                        case ChainReactionEffect :
    
                            this.Thor(gameModel,thor,view);
    
                            break;
                        //III effect
                        case HighVoltageEffect :
    
                            this.Thor(gameModel,thor,view);
    
                            break;
                    
                    }
                    break;
                    
                case VortexCannon:
                
                    VortexCannon vortexCannon = (VortexCannon) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseEffect :
                        
                            this.VortexCannon(gameModel,vortexCannon,view);
                            
                            break;
                        //II effect
                        case BlackHoleEffect :
    
                            this.VortexCannon(gameModel,vortexCannon,view);
    
                            break;
                    
                    }
                    break;
            
                case Furnace:
                
                    Furnace furnace = (Furnace) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.Furnace(gameModel,furnace,view);
                            
                            break;
                        //II effect
                        case CozyFireMode :
    
                            this.Furnace(gameModel,furnace,view);
    
                            break;
                    
                    }
                    break;
                    
                case PlasmaGun:
                    
                    PlasmaGun plasmaGun = (PlasmaGun) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseEffect :
                        
                            this.PlasmaGun(gameModel,plasmaGun,view);
                            
                            break;
                        //II effect
                        case PhaseGlideEffect :
    
                            this.PlasmaGun(gameModel,plasmaGun,view);
    
                            break;
                        //III effect
                        case BaseEffectPlusChargedShotEffect :
    
                            this.PlasmaGun(gameModel,plasmaGun,view);
    
                            break;
                    
                    }
                    break;
                    
                case Heatseeker:
                    
                    Heatseeker heatseeker = (Heatseeker) getCorrectWeapon(functionModel,this.weaponName);
                    
                    this.HeatSeeker(gameModel,heatseeker,view);
                    
                    //only I effect
                    
                    break;
            
            
                case Whisper:
                    
                    Whisper whisper = (Whisper) getCorrectWeapon(functionModel,this.weaponName);
                    
                    this.Whisper(gameModel,whisper,view);
                    
                    //only I effect
                    
                    break;
            
                case Hellion:
                
                    Hellion hellion = (Hellion) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.Hellion(gameModel,hellion,view);
                            
                            break;
                        //II effect
                        case NanoTracerMode :
    
                            this.Hellion(gameModel,hellion,view);
                            
                            break;
                    
                    }
                    break;
                    
                case Flamethrower:
                
                    Flamethrower flamethrower = (Flamethrower) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.Flamethrower(gameModel,flamethrower,view);
                            
                            break;
                        //II effect
                        case BarbecueMode :
    
                            this.Flamethrower(gameModel,flamethrower,view);
                            
                            break;
                    
                    }
                    break;
            
                case Zx2:
                
                    Zx2 zx2 = (Zx2) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.Zx2(gameModel,zx2,view);
                            
                            break;
                        //II effect
                        case ScannerMode :
    
                            this.Zx2(gameModel,zx2,view);
    
                            break;
                    
                    }
                    break;
                    
                case GrenadeLauncher:
                
                    GrenadeLauncher grenadeLauncher = (GrenadeLauncher) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseEffect :
                        
                            this.GrenadeLauncher(gameModel,grenadeLauncher,view);
                            
                            break;
                        //II effect
                        case ExtraGrenadeEffect :
    
                            this.GrenadeLauncher(gameModel,grenadeLauncher,view);
    
                            break;
                    
                    }
                    break;
                    
                case Shotgun:
                
                    Shotgun shotgun = (Shotgun) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.Shotgun(gameModel,shotgun,view);
                            
                            break;
                        //II effect
                        case LongBarrelMode :
    
                            this.Shotgun(gameModel,shotgun,view);
    
                            break;
                    }
                    break;
                    
                case RocketLauncher:
                
                    RocketLauncher rocketLauncher = (RocketLauncher) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseEffect :
                        
                            this.RocketLauncher(gameModel,rocketLauncher,view);
                            
                            break;
                        //II effect
                        case BaseEffectPlusFragmentingWarheadEffect :
    
                            this.RocketLauncher(gameModel,rocketLauncher,view);
    
                            break;
                        //III effect
                        case RocketJumpEffect :
    
                            this.RocketLauncher(gameModel,rocketLauncher,view);
    
                            break;
                    }
                    break;
            
                case PowerGlove:
                
                    PowerGlove powerGlove = (PowerGlove) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.PowerGlove(gameModel,powerGlove,view);
                            
                            break;
                        //II effect
                        case RocketFistMode :
                            
                            this.PowerGlove(gameModel,powerGlove,view);
    
                            break;
                    
                    }
                    break;
            
                case Railgun:
                
                    Railgun railGun = (Railgun) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                            
                            this.RailGun(gameModel,railGun,view);
                            
                            break;
                        //II effect
                        case PiercingMode :
    
                            this.RailGun(gameModel,railGun,view);
    
                            break;
                    
                    }
                    break;
                    
                case Shockwave:
                
                    Shockwave shockwave = (Shockwave) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.Shockwave(gameModel,shockwave,view);
                            
                            break;
                        //II effect
                        case TsunamiMode :
                            
                            this.Shockwave(gameModel,shockwave,view);
    
                            break;
                    }
                    break;
            
                case Cyberblade:
                
                    Cyberblade cyberblade = (Cyberblade) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseEffect :
                        
                            this.Cyberblade(gameModel,cyberblade,view);
                            
                            break;
                        //II effect
                        case ShadowstepEffect :
    
                            this.Cyberblade(gameModel,cyberblade,view);
                            
                            break;
                        //III effect
                        case SliceAndDiceEffect :
    
                            this.Cyberblade(gameModel,cyberblade,view);
                            
                            break;
                    }
                    break;
                    
                case Sledgehammer:
                
                    Sledgehammer sledgehammer = (Sledgehammer) getCorrectWeapon(functionModel,this.weaponName);
                    switch(functionModel.getGameModel().getWeaponsEffect()){
                        //I effect
                        case BaseMode :
                        
                            this.Sledgehammer(gameModel,sledgehammer,view);
                            
                            break;
                        //II effect
                        case PulverizeMode :
    
                            this.Sledgehammer(gameModel,sledgehammer,view);
    
                            break;
                    }
                    break;
            }
        } catch (NotValidInput notValidInput) {
            notValidInput.printStackTrace();
        }
        functionModel.getGameModel().setState(State.SHOOT);
        
    }
    
    
    //WEAPON
    public void LockRifleweapon(GameModel gameModel, LockRifle weapon, RemoteView view) throws RemoteException {

        Player currentPlayer = gameModel.getActualPlayer();
        Map map = gameModel.getMap();
        Player targetBase;
        Player targetSecondLock;

        switch (gameModel.getWeaponsEffect()) {

            case BaseEffect:

                try {

                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                    beforeEffect=WeaponsEffect.BaseEffect;
                } catch (NotVisibleTarget notVisibleTarget) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException e) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;

            case SecondLockEffect:

                if(beforeEffect==WeaponsEffect.BaseEffect) {

                    try {
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetSecondLock = gameModel.getPlayerById(view.getTarget2());
                        if (targetBase != targetSecondLock) {

                            weapon.secondLockEffect(map, currentPlayer, targetSecondLock);
                        } else {

                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {

                        gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS THE SAME OF BASE EFFECT");
                    } catch (NotVisibleTarget notVisibleTarget) {

                        gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                    } catch (MapException e) {

                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                }else{

                    gameModel.setErrorMessage("ERROR!");
                }
                break;
        }
    }

    public void ElectroscytheWeapon(GameModel gameModel, Electroscythe weapon, RemoteView view) throws RemoteException{
    
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
    
        switch (gameModel.getWeaponsEffect()) {

            case BaseMode:

                try {

                    weapon.baseMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {
    
                    gameModel.setErrorMessage("ERROR: NO TARGET IN YOUR SQUARE");
                } catch (MapException mapException){
    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            case ReaperMode:

                try {
                    
                    weapon.reaperMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {
    
                    gameModel.setErrorMessage("ERROR: NO TARGET IN YOUR SQUARE");
                } catch (MapException mapException ) {
    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    

    public void TractorBeam(GameModel gameModel, TractorBeam weapon, RemoteView view) throws RemoteException {
    
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        Square destSquareBase;
        Player targetBaseOrPunisher;
    
        switch (gameModel.getWeaponsEffect()) {

            case BaseMode:
                
                try {

                    destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    targetBaseOrPunisher = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map, destSquareBase, currentPlayer, targetBaseOrPunisher);
                }catch (NotVisibleTarget notVisibleTarget) {

                    gameModel.setErrorMessage("ERROR: THE DESTINATION SQUARE IS NOT VISIBLE FROM YOUR POSITION");
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: YOU CAN'T MOVE YOUR TARGET MORE THAN TWO MOVES");
                } catch (MapException mapException) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;

            case PunisherMode:
              
                try {

                    targetBaseOrPunisher = gameModel.getPlayerById(view.getTarget1());
                    weapon.punisherMode(map, currentPlayer, targetBaseOrPunisher);
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS MORE THAN THO MOVES FROM YOU");
                } catch (MapException mapException) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void Whisper(GameModel gameModel, Whisper weapon, RemoteView view) throws RemoteException {
    
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;

        switch (gameModel.getWeaponsEffect()) {
            
            case BaseEffect:
                try {
            
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                } catch (NotValidDistance notValidDistance) {
            
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT AT LEAST 2 MOVES FROM YOU");
                } catch (NotVisibleTarget notVisibleTarget) {
            
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NON VISIBLE");
                } catch (MapException e) {
            
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void VortexCannon (GameModel gameModel, VortexCannon weapon, RemoteView view) throws RemoteException {
    

        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square vortexSquare;
        Player target1BlackHole;
        Player target2BlackHole;
        ArrayList<Player> targetsBlackHole;
    
        switch (gameModel.getWeaponsEffect()) {

            case BaseEffect:

                try {
    
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    vortexSquare = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.baseEffect(map, vortexSquare, currentPlayer, targetBase);
                    beforeEffect = WeaponsEffect.BaseEffect;
                } catch (NotVisibleTarget notVisibleTarget) {
    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARE IS NOT VISIBLE FROM YOUR POSITION");
                } catch (NotValidDistance notValidDistance) {
    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARE IN NOT AT LEAST ONE MOVE FROM YOU OR THE CHOSEN TARGET IS NOT " +
                            "ON VORTEX SQUARE OR IS NOT DISTANCE ONE MOVE FROM THE VORTEX SQUARE");
                } catch (MapException mapException) {
    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
                
            case BlackHoleEffect:
            
                if(beforeEffect==WeaponsEffect.BaseEffect) {

                    try {
        
                        vortexSquare = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        target1BlackHole = gameModel.getPlayerById(view.getTarget2());
                        if (view.getTarget3() == -1) {
            
                            if (target1BlackHole != targetBase) {

                                targetsBlackHole=new ArrayList<>();
                                targetsBlackHole.add(target1BlackHole);
                                weapon.blackHoleEffect(map, vortexSquare, currentPlayer, targetsBlackHole);
                            } else {
                
                                throw new NotValidInput();
                            }
                        } else {
            
                            target2BlackHole = gameModel.getPlayerById(view.getTarget3());
                            if ((target1BlackHole != target2BlackHole) && (target1BlackHole != targetBase) && (target2BlackHole != targetBase)) {

                                targetsBlackHole=new ArrayList<>();
                                targetsBlackHole.add(target1BlackHole);
                                targetsBlackHole.add(target2BlackHole);
                                weapon.blackHoleEffect(map, vortexSquare, currentPlayer, targetsBlackHole);
                            } else {
                
                                throw new NotValidInput();
                            }
                        }
                    } catch (NotValidDistance notValidDistance) {
        
                        gameModel.setErrorMessage("THE CHOSEN TARGET IS NOT ON VORTEX SQUARE OR IS NOT DISTANCE ONE MOVE FROM THE VORTEX SQUARE");
                    } catch (NotValidInput notValidInput) {
        
                        gameModel.setErrorMessage("ONE OR TWO BLACK HOLE TARGET ARE THE SAME OF BASE EFFECT OR ARE EQUAL BETWEEN THEM");
                    } catch (MapException e) {
        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
            }
            
    }

    public void Furnace(GameModel gameModel, Furnace weapon, RemoteView view) throws RemoteException {

        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        EnumColorSquare roomTarget;
        Square targetSquareCozy;

        switch (gameModel.getWeaponsEffect()) {

            case BaseMode:

                try {

                    roomTarget = view.getColorRoom();
                    weapon.baseMode(map, currentPlayer, roomTarget);
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: CHOOSE A DIFFERENT ROOM THAN YOURS ");
                } catch (NotVisibleTarget notVisibleTarget) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN ROOM IS NOT VISIBLE ");
                } catch (MapException mapException) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;

            case CozyFireMode:

                try {

                    targetSquareCozy = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.cozyFireMode(map, currentPlayer, targetSquareCozy);
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARE IS NOT DISTANCE ONE MOVE");
                } catch (MapException mapException) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }

    public void HeatSeeker(GameModel gameModel, Heatseeker weapon, RemoteView view) throws RemoteException{

        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
    
        switch (gameModel.getWeaponsEffect()) {
            
            case BaseEffect:

                try {

                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.BaseEffect(map, currentPlayer, targetBase);
                } catch (VisibleTarget visibleTarget) {
        
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS VISIBLE");
                } catch (MapException e) {
        
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }

    public void Hellion(GameModel gameModel, Hellion weapon, RemoteView view) throws RemoteException{
    
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBaseOrTracer;
    
        switch (gameModel.getWeaponsEffect()) {

            case BaseMode:

                try {

                    targetBaseOrTracer = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map, currentPlayer, targetBaseOrTracer);
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IN NOT AT LEAST ONE MOVE FROM YOU" );
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException mapException) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
                
            case NanoTracerMode:

                try {

                    targetBaseOrTracer = gameModel.getPlayerById(view.getTarget1());
                    weapon.nanoTracerMode(map, currentPlayer, targetBaseOrTracer);
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IN NOT AT LEAST ONE MOVE FROM YOU" );
                } catch (NotVisibleTarget notVisibleTarget) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException mapException) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void RailGun(GameModel gameModel, Railgun weapon,RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player target1;
        Player target2;
        EnumCardinalDirection direction;
        ArrayList<Player> piercingTargets = new ArrayList<>();
    
        switch (gameModel.getWeaponsEffect()) {
    
            case BaseMode:

                try {

                    target1 = gameModel.getPlayerById(view.getTarget1());
                    direction = view.getCardinalDirection();
                    weapon.baseMode(map, currentPlayer, target1, direction);
                } catch (NotValidCardinalDirection notValidCardinalDirection) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN DIRECTION DOESN'T EXIST");

                } catch (NotInDirection notInDirection) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN CHOSEN CARDINAL DIRECTION");
                } catch (MapException e) {
    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
    
            case PiercingMode:
                
                try {

                    target1 = gameModel.getPlayerById(view.getTarget1());
                    direction = view.getCardinalDirection();
                    if (view.getTarget2() != -1) {
        
                        target2 = gameModel.getPlayerById(view.getTarget2());
                        piercingTargets.add(target1);
                        piercingTargets.add(target2);
                        weapon.piercingMode(map, currentPlayer, piercingTargets, direction);
                    } else {

                        piercingTargets.add(target1);
                        weapon.piercingMode(map, currentPlayer, piercingTargets, direction);
                    }
                } catch (NotValidCardinalDirection notValidCardinalDirection) {
        
                    gameModel.setErrorMessage("ERROR: THE CHOSEN DIRECTION DON'T EXIST");
                } catch (NotInDirection notInDirection) {
        
                    gameModel.setErrorMessage("ERROR: ONE OF CHOSEN TARGET IS NOT IN CHOSEN CARDINAL DIRECTION");
                } catch (MapException e) {
        
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    
    public void Zx2(GameModel gameModel, Zx2 weapon,RemoteView view) throws RemoteException {
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player target1;
        Player target2;
        Player target3;
        ArrayList<Player> scannerModeTargets = new ArrayList<>();
    
        switch (gameModel.getWeaponsEffect()) {

            case BaseMode:

                try {

                    target1 = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map, currentPlayer, target1);
                } catch (NotVisibleTarget notVisibleTarget) {

                     gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException e) {

                     gameModel.setErrorMessage("ERROR: MAP ERROR");
                }

                break;
                
            case ScannerMode:
                
                try {
    
                    target1 = gameModel.getPlayerById(view.getTarget1());
                    if ((view.getTarget2() == -1) && (view.getTarget3() == -1)) {

                        scannerModeTargets.add(target1);
                        weapon.scannerMode(map, currentPlayer, scannerModeTargets);
                    } else if (view.getTarget3() == -1) {

                        target2 = gameModel.getPlayerById(view.getTarget2());
                        if (target1 != target2) {

                            scannerModeTargets.add(target1);
                            scannerModeTargets.add(target2);
                            weapon.scannerMode(map, currentPlayer, scannerModeTargets);
                        } else {
    
                                throw new NotValidInput();
                            }

                    } else {

                        target2 = gameModel.getPlayerById(view.getTarget2());
                        target3 = gameModel.getPlayerById(view.getTarget3());
                        if ((target1 != target2) && (target1 != target3) && (target2 != target3)) {

                            scannerModeTargets.add(target1);
                            scannerModeTargets.add(target2);
                            scannerModeTargets.add(target3);
                            weapon.scannerMode(map, currentPlayer, scannerModeTargets);
                        } else {

                            throw new NotValidInput();

                        }
                    }
                } catch (NotVisibleTarget notVisibleTarget) {

                    gameModel.setErrorMessage("ERROR: ONE OF CHOSEN TARGET IS NOT VISIBLE");
                } catch (NotValidInput notValidInput) {

                     gameModel.setErrorMessage("TWO OR THREE TARGETS ARE EQUAL BETWEEN THEM");
                } catch (MapException e) {
    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }

    public void PlasmaGun (GameModel gameModel, PlasmaGun weapon, RemoteView view) throws RemoteException  {

        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        Player targetBase;
        Square destSquarePhaseGlide;
    
        switch (gameModel.getWeaponsEffect()) {

            case BaseEffectPlusChargedShotEffect:

            try {

                targetBase = gameModel.getPlayerById(view.getTarget1());
                weapon.baseEffect(map, currentPlayer, targetBase);
                if(view.isBooleanChose()) {
                    
                    weapon.chargedShotEffect(currentPlayer, targetBase);
                }
            } catch (NotVisibleTarget notVisibleTarget) {
                
                gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
            } catch (MapException e) {
    
                gameModel.setErrorMessage("ERROR: MAP ERROR");
            }
            break;
            
            case PhaseGlideEffect:

                try {

                    destSquarePhaseGlide = gameModel.getMap().getSquare(view.getRow(),view.getColumn());
                    weapon.phaseGlideEffect(map,destSquarePhaseGlide,currentPlayer);
                } catch (MapException e) {
                    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: YOU CAN MOVE YOUR TARGET ONLY ONE OR TWO MOVES");
                }
                break;
            }
        }

    public void Cyberblade(GameModel gameModel, Cyberblade weapon,RemoteView view) throws RemoteException{

        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square destSquareShadow;
        Player targetSliceAndDice;
    
        switch (gameModel.getWeaponsEffect()) {
            
            case BaseEffect:
                
                try {

                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                    beforeEffect= WeaponsEffect.BaseEffect;
                } catch (NotValidDistance notValidDistance) {

                     gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE");
                } catch (MapException mapException) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
            case ShadowstepEffect:
                
                try{

                    destSquareShadow = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.shadowstepEffect(map,currentPlayer,destSquareShadow);
                }catch(NotValidDistance notValidDistance){

                    gameModel.setErrorMessage("ERROR: YOU CAN MOVE YOURSELF ONLY ONE MOVES");
                }catch (MapException mapException){

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
            case SliceAndDiceEffect:
                
                if(beforeEffect==WeaponsEffect.BaseEffect) {
    
                    try {
        
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetSliceAndDice = gameModel.getPlayerById(view.getTarget2());
                        if (targetBase != targetSliceAndDice) {
            
                            weapon.sliceAndDiceEffect(map, currentPlayer, targetSliceAndDice);
                        } else {
            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
        
                        gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS THE SAME OF BASE EFFECT");
                    } catch (NotValidDistance notValidDistance) {

                        gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE");
                    } catch (MapException mapException) {

                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
    
                    gameModel.setErrorMessage("ERROR!");
                }
        }
    }

    public void GrenadeLauncher(GameModel gameModel, GrenadeLauncher weapon, RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square destSquareBase;
        Square targetSquareExtra;
    
        switch (gameModel.getWeaponsEffect()) {
            
            case BaseEffect:
                
                try {

                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, targetBase, currentPlayer);
                    beforeEffect = WeaponsEffect.BaseEffect;
                } catch (NotVisibleTarget notVisibleTarget) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException e) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case MoveTarget:

                try {

                    destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.moveTarget(map, targetBase, destSquareBase);
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: YOU CAN MOVE YOUR TARGET ONLY ONE MOVES");
                } catch (MapException mapException) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
            
            case ExtraGrenadeEffect:
                
                if(beforeEffect == WeaponsEffect.BaseEffect) {

                    try {

                        targetSquareExtra = gameModel.getMap().getSquare(view.getRow2(), view.getColumn2());
                        weapon.extraGrenadeEffect(map, currentPlayer, targetSquareExtra);
                    } catch (NotVisibleTarget notVisibleTarget) {
        
                        gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARE IS NOT VISIBLE");
                    } catch (MapException mapExcpetion) {

                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    } catch (NoTargetInSquare noTargetInSquare) {
        
                        gameModel.setErrorMessage("ERROR: NO TARGET IN THE CHOSEN SQUARE");
                    }
                } else {
    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
        }
    }

    public void Thor(GameModel gameModel, Thor weapon, RemoteView view) throws RemoteException {

        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        Player targetBase;
        Player targetChainReaction;
        Player targetHighVoltage;

        switch (gameModel.getWeaponsEffect()) {
            case BaseEffect:

                try {

                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseEffect(map, currentPlayer, targetBase);
                    beforeEffect = WeaponsEffect.BaseEffect;
                } catch (NotVisibleTarget notVisibleTarget) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                } catch (MapException e) {
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
            case ChainReactionEffect:

                if(beforeEffect == WeaponsEffect.BaseEffect) {

                    try {

                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetChainReaction = gameModel.getPlayerById(view.getTarget2());
                        if ((map.isVisible(targetBase, targetChainReaction)) && (targetBase != targetChainReaction)) {

                            weapon.chainReactionEffect(currentPlayer, targetChainReaction);
                            beforeEffect = WeaponsEffect.ChainReactionEffect;
                        } else {

                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
        
                        gameModel.setErrorMessage("ERROR: THE TARGET OF BASE EFFECT DON'T SEE THE CHOSEN TARGET OR THE CHOSEN TARGET IS NOT DIFFERENT " +
                                "FROM THE TARGET OF BASE EFFECT ");
                    } catch (MapException e) {
        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
                
            case HighVoltageEffect:
                
                if(beforeEffect == WeaponsEffect.ChainReactionEffect) {

                    try {

                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        targetChainReaction = gameModel.getPlayerById(view.getTarget2());
                        targetHighVoltage = gameModel.getPlayerById(view.getTarget3());
                        if ((map.isVisible(targetChainReaction, targetHighVoltage)) && (targetChainReaction != targetHighVoltage)
                                && (targetBase != targetHighVoltage)) {

                            weapon.highVoltageEffect(currentPlayer, targetHighVoltage);
                        } else {
            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
        
                        gameModel.setErrorMessage("ERROR: THE TARGET OF CHAIN REACTION DON'T SEE THE CHOSEN TARGET OR THE CHOSEN TARGET IS NOT DIFFERENT" +
                                "FROM THE TARGET OF BASE EFFECT AND FROM THE TARGET OF CHAIN REACTION");
                    } catch (MapException e) {
        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
            }
        }

    public void Shockwave(GameModel gameModel, Shockwave weapon,RemoteView view) throws RemoteException{
        
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player target1Base;
        Player target2Base;
        Player target3Base;
    
        switch (gameModel.getWeaponsEffect()) {

            case BaseMode:

                try {

                    target1Base = gameModel.getPlayerById(view.getTarget1());
                    if ((view.getTarget3() == -1) && (view.getTarget2() == -1)) {

                        weapon.baseMode(map, currentPlayer, target1Base);
                        
                    } else if ((view.getTarget3() == -1) && (view.getTarget2() != -1)) {

                        target2Base = gameModel.getPlayerById(view.getTarget2());
                        weapon.baseMode(map, currentPlayer, target1Base, target2Base);
                    } else {

                        target2Base = gameModel.getPlayerById(view.getTarget2());
                        target3Base = gameModel.getPlayerById(view.getTarget3());
                        weapon.baseMode(map, currentPlayer, target1Base, target2Base, target3Base);
                    }
                } catch (NotValidDistance notValidDistance) {
                    
                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET ARE NOT DIFFERENT AMONG THEM OR EACH ONE ARE NOT DISTANCE" +
                            "EXACTLY ONE MOVE");
                } catch (MapException e) {
    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
                
            case TsunamiMode:

                try{

                    ArrayList<Player> allPlayerInGame=new ArrayList<>(gameModel.getPlayers(false));
                    weapon.tsunamiMode(map,currentPlayer,allPlayerInGame);
                }catch (NotValidDistance notValidDistance){

                    gameModel.setErrorMessage("ERROR: NO PLAYER AT ONE MOVE FROM YOU");
                }
                break;
        }
    }
    

    //TODO andre
    public void MachineGun(GameModel gameModel, MachineGun weapon, RemoteView view) throws RemoteException{
        
        //necessary from model
        Player currentPlayer = gameModel.getActualPlayer();
        Map map = gameModel.getMap();
        //necessary input
        Player target1Base;
        Player target2Base; // se l'utente per l'effetto base non vuole colpire due target, questo viene messo a null.
        Player targetFocusShot;
        Player targetTurretTripod;
        //necessary input
        WeaponsEffect effect = view.getWeaponsEffect();
    
        boolean choice1;
        boolean choice2;
        choice1 = view.isUseSecondEffect();
        choice2 = view.isUseThirdEffect();
        
        //array for target
        ArrayList<Player> targetBase = new ArrayList<>();
    
        switch (gameModel.getWeaponsEffect()) {
            
            case BaseEffect:
                //base mode
                try {
                    
                    //get the target
                    target1Base = gameModel.getPlayerById(view.getTarget1());
                    target2Base = gameModel.getPlayerById(view.getTarget2()); //possible null
                    
                    if (target2Base == null) {
                        
                        targetBase.add(target1Base);
                        weapon.baseEffect(map, currentPlayer, targetBase);
                        
                        beforeEffect = WeaponsEffect.BaseEffect;
                        
                        playerDamage=1;
                        
                    } else if (target1Base != target2Base) {
                        
                        targetBase.add(target1Base);
                        targetBase.add(target2Base);
                        weapon.baseEffect(map, currentPlayer, targetBase);
    
                        beforeEffect = WeaponsEffect.BaseEffect;
                        
                        playerDamage=2;
                        
                    } else {
                        
                        throw new NotValidInput();
                    }
                    
                } catch (NotVisibleTarget notVisibleTarget) {
                    
                    //gestione target 1 o 2 effetto base non visibili con il curretnPLayer
                } catch (NotValidInput notValidInput) {
                    
                    //gestione target 1==target 2 effetto base
                } catch (MapException e) {
        
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
    
      
            case FocusShotEffect:
                
                if(beforeEffect== WeaponsEffect.BaseEffect) {
    
                    try {
        
                        //get the target
                        target1Base = gameModel.getPlayerById(view.getTarget1());
                        target2Base = gameModel.getPlayerById(view.getTarget2()); // possible null
                        targetFocusShot = gameModel.getPlayerById(view.getTarget3());
        
                        if (targetFocusShot == target1Base) {
    
                            weapon.focusShotEffect(currentPlayer, targetFocusShot);
                            
                            playerDameged = target1Base;
                            
                            if(targetFocusShot == target2Base) {
    
                                weapon.focusShotEffect(currentPlayer, targetFocusShot);
    
                                playerDameged = target2Base;
                                
                            } else {
    
                                throw new NotValidInput();
                            }
            
                        } else {
            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {
        
                        //gestione se target focus shot dovesse essere diverso sia da target 1 sia target 2 effetto base
                    } catch (MapException e) {
        
                        gameModel.setErrorMessage("ERROR: MAP ERROR");
                    }
                } else {
    
                    gameModel.setErrorMessage("ERROR!");
                }

                break;
                
            case TurretTripodEffect:
                
                if(beforeEffect == WeaponsEffect.BaseEffect) {
                    
                    if (playerDamage == 1) {
                        if(choice1) {
    
                            //get the target
                            try {
        
                                //TODO andre
                                target1Base = gameModel.getPlayerById(view.getTarget1());
                                target2Base = gameModel.getPlayerById(view.getTarget2());
                                targetFocusShot = gameModel.getPlayerById(view.getTarget3());
                                targetTurretTripod = gameModel.getPlayerById(view.getTarget4());
        
                            } catch (MapException e) {
        
                                gameModel.setErrorMessage("ERROR: MAP ERROR");
                            }
                        }
        
                        
        
                    } else if (playerDamage == 2) {
        
                        //get the target
                        try {
                            //TODO andre
                            target1Base = gameModel.getPlayerById(view.getTarget1());
                            target2Base = gameModel.getPlayerById(view.getTarget2());
                            targetFocusShot = gameModel.getPlayerById(view.getTarget3());
                            targetTurretTripod = gameModel.getPlayerById(view.getTarget4());
                        } catch (MapException e) {
            
                            gameModel.setErrorMessage("ERROR: MAP ERROR");
                        }
        
                    }
                } else {
    
                    gameModel.setErrorMessage("ERROR!");
                }
                break;
            }
        }
    
    public void RocketLauncher(GameModel gameModel, RocketLauncher weapon,RemoteView view) throws RemoteException {

        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square destSquareBase;
        Square destSquareJump;
        boolean choice1;


        switch (gameModel.getWeaponsEffect()) {
            
            case BaseEffectPlusFragmentingWarheadEffect:
                
                try {

                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    choice1 = view.isUseSecondEffect();
                    if(!choice1){

                        if ((view.getRow() != -1) && ((view.getColumn() != -1))) {

                            destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                            weapon.baseEffect(map, targetBase, currentPlayer, destSquareBase);
                        } else {

                            destSquareBase = null;
                            weapon.baseEffect(map, targetBase, currentPlayer, destSquareBase);
                        }
                    }else{

                        if ((view.getRow() != -1) && ((view.getColumn() != -1))) {

                            destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                            weapon.baseEffectWithFragmenting(map, targetBase, currentPlayer, destSquareBase);
                        } else {

                            destSquareBase = null;
                            weapon.baseEffectWithFragmenting(map, targetBase, currentPlayer, destSquareBase);
                        }
                    }
                } catch (NotVisibleTarget notVisibleTarget) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");

                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS IN YOUR SQUARE OR YOU ARE NOT MOVING ONE MOVE YOUR TARGET ");
                } catch (MapException mapExcpetion) {
    
                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                } catch (NoTargetInSquare noTargetInSquare){

                    gameModel.setErrorMessage("ERROR: NO TARGET IN CHOSEN SQUARE ");
                }

                break;
            
            case RocketJumpEffect:
                
                try{

                    destSquareJump = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.rocketJumpEffect(map,currentPlayer,destSquareJump);
                }catch (NotValidDistance notValidDistance){
                    gameModel.setErrorMessage("ERROR: YOU MOVE ONLY ONE OR TWO MOVES");
                }catch (MapException mapException){

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }
                break;
        }
    }
    

    public void Shotgun(GameModel gameModel, Shotgun weapon,RemoteView view) throws RemoteException{
    
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Square destSquareBase;
    
        switch (gameModel.getWeaponsEffect()) {
            
            case BaseMode:

                try {
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    if ((view.getRow() == -1) && (view.getColumn() == -1)) {

                        destSquareBase = null;
                        weapon.baseMode(map, currentPlayer,targetBase,destSquareBase);
                    }else{

                        destSquareBase = map.getSquare(view.getRow(),view.getColumn());
                        weapon.baseMode(map, currentPlayer,targetBase,destSquareBase);
                    }

                }catch (MapException mapExcpetion){

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }catch (NotValidDistance notValidDistance){

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE OR YOU ARE NOT MOVING " +
                            "ONE MOVE THE TARGET");
                }
                break;
            case LongBarrelMode:

                try{

                    targetBase=gameModel.getPlayerById(view.getTarget1());
                    weapon.longBarrelMode(map,currentPlayer,targetBase);
                }catch (MapException mapException){

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }catch (NotValidDistance notValidDistance){

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT EXACTLY AT ONE MOVE FROM YOU");
                }
                break;
        }
    }
    
    public void Flamethrower(GameModel gameModel, Flamethrower weapon,RemoteView view) throws RemoteException{
    
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase;
        Player target2;
        Square targetSquare1;
        Square targetSquare2;
       
        switch (gameModel.getWeaponsEffect()) {
    
            case BaseEffect:

                try{

                    targetBase=gameModel.getPlayerById(view.getTarget1());
                    if(view.getTarget2()==-1){

                        weapon.baseMode(map,currentPlayer,targetBase);
                    }else{

                        target2 = gameModel.getPlayerById(view.getTarget2());
                        weapon.baseMode(map,currentPlayer,targetBase,target2);
                    }

                }catch (MapException mapException){

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                }catch (NotValidDistance notValidDistance){

                    gameModel.setErrorMessage("ERROR: THE FIRST CHOSEN TARGET ARE NOT AT ONE MOVE FROM YOU OR" +
                            "THE SECOND CHOSEN TARGET ARE NOT ONE MOVE FROM THE FIRST");

                }catch (NotInSameDirection notInSameDirection){

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGETS ARE NOT IN THE SAME DIRECTION");

                }
                break;
            case BarbecueMode:

                try{

                    targetSquare1 = map.getSquare(view.getRow(),view.getColumn());
                    targetSquare2 = map.getSquare(view.getRow2(),view.getColumn2());
                    weapon.barbecueMode(map,currentPlayer,targetSquare1,targetSquare2);
                }catch(MapException mapExcpetion){

                    gameModel.setErrorMessage("ERROR: MAP ERROR");

                } catch (NoTargetInSquare noTargetInSquare) {

                    gameModel.setErrorMessage("ERROR: IN ONE OF SQUARES THERE ARE NOT PLAYERS");

                } catch (NotInSameDirection notInSameDirection) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARES ARE NOT IN THE SAME DIRECTION");
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE FIRST CHOSEN SQUARE ARE NOT AT ONE MOVE FROM YOU OR" +
                            "THE SECOND CHOSEN SQUARE ARE NOT ONE MOVE FROM THE FIRST");
                }
                break;
        }
    }

    public void PowerGlove(GameModel gameModel, PowerGlove weapon,RemoteView view) throws RemoteException{
    
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase ;
        Player targetRocket1;
        Player targetRocket2;
    
        switch (gameModel.getWeaponsEffect()) {
        
            case BaseMode:

                try {
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map,currentPlayer,targetBase);

                } catch (MapException mapExcpetion) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");

                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT EXACTLY AT ONE MOVE FROM YOU");
                }
                break;
            case RocketFistMode:

                try{

                    targetRocket1 = gameModel.getPlayerById(view.getTarget2());
                    if(view.getTarget3()==-1){

                        weapon.rocketFistMode(map,currentPlayer,targetRocket1);
                    }else{

                        targetRocket2 = gameModel.getPlayerById(view.getTarget3());
                        weapon.rocketFistMode(map,currentPlayer,targetRocket1,targetRocket2);
                    }
                }catch (MapException mapException){

                    gameModel.setErrorMessage("ERROR: MAP ERROR");
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE FIRST CHOSEN SQUARE ARE NOT AT ONE MOVE FROM YOU OR" +
                            "THE SECOND CHOSEN SQUARE ARE NOT ONE MOVE FROM THE FIRST");
                } catch (NotInSameDirection notInSameDirection) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN SQUARES ARE NOT IN THE SAME DIRECTION");
                }
                break;
        }
    
    }
    

    public void Sledgehammer(GameModel gameModel, Sledgehammer weapon,RemoteView view) throws RemoteException{
    
    
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        Player targetBase ;
        Player targetPulverize;
        Square destSquare;
    
        switch (gameModel.getWeaponsEffect()) {
        
            case BaseMode:

                try {

                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    weapon.baseMode(map,currentPlayer,targetBase);
                } catch (MapException e) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");

                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE");
                }

                break;
        
            case PulverizeMode:

                try{

                    destSquare = map.getSquare(view.getRow(),view.getColumn());
                    targetPulverize=gameModel.getPlayerById(view.getTarget1());
                    weapon.pulverizeMode(map,currentPlayer,targetPulverize,destSquare);
                } catch (NotInSameDirection notInSameDirection) {

                    gameModel.setErrorMessage("ERROR: YOU ARE NOT MOVING THE TARGET IN ONE DIRECTION");
                } catch (NotValidDistance notValidDistance) {

                    gameModel.setErrorMessage("ERROR: THE CHOSEN TARGET IS NOT IN YOUR SQUARE OR YOU ARE NOT MOVING THE " +
                            "TARGET ZERO, ONE OR TWO SQUARE");
                } catch (MapException e) {

                    gameModel.setErrorMessage("ERROR: MAP ERROR");

                }

                break;
        }
    
    }
    

}

