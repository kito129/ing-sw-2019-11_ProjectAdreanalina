package it.polimi.controller;


import it.polimi.model.*;
//chiedere perche devo importare tutto
import it.polimi.model.Exception.*;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;
import it.polimi.view.RemoteView;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class FunctionController {
    
    WeaponController weaponController;
    
    public FunctionController(){
        
        weaponController = new WeaponController(this);
        
    }
    
    public void lobby(FunctionModel functionModel){
    
        GameModel gameModel = functionModel.getGameModel();
    
        try {
            //todo marco guarda che ho spostato la creazione del player dentro l'add observer del modello.
            //ricordati di sistemare la cosa del colore
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
                        functionModel.getGameModel().setBeforeError(functionModel.getGameModel().getState());
                        functionModel.getGameModel().setState(State.ERROR);
                        
                    }
                    break;
                case 4:
                    
                    if(functionModel.getGameModel().getActualPlayer().getPlayerBoard().getPlayerPowerUps().size()>0){
    
                        functionModel.getGameModel().setState(State.SELECTPOWERUP);
                    } else {
        
                        functionModel.getGameModel().setMessageToCurrentView("YOU HAVE NOT POWER UP TO USE");
                        functionModel.getGameModel().setBeforeError(functionModel.getGameModel().getState());
                        functionModel.getGameModel().setState(State.ERROR);
                
                    }
            }
            
            
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    
    }
    
    
    public void errorState(FunctionModel functionModel) throws RemoteException {
        
        System.out.println("ERROR STATE-->\nRESTART IN STATE CHOICE-->");
        
        switch (functionModel.getGameModel().getBeforeError()){
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
        functionModel.getGameModel().setBeforeError(functionModel.getGameModel().getState());
        functionModel.getGameModel().setState(State.ERROR);
    }
    
    public void mapErrorGestor(FunctionModel functionModel) throws RemoteException {
        
        functionModel.getGameModel().setMessageToCurrentView("YOUR INPUT IS NOT CORRECT");
        functionModel.getGameModel().setBeforeError(functionModel.getGameModel().getState());
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
            functionModel.getGameModel().setBeforeError(functionModel.getGameModel().getState());
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
                    functionModel.getGameModel().setBeforeError(functionModel.getGameModel().getState());
                    functionModel.getGameModel().setState(State.ERROR);
    
                } catch(GrabActionFullObjException e){
    
                    functionModel.getGameModel().setMessageToCurrentView("YOU DON'T HAVE MORE SPACE FOR GRAB OBJECT");
                    functionModel.getGameModel().setBeforeError(functionModel.getGameModel().getState());
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
                
                functionModel.getGameModel().setPowerUpSelected(powerUpCard);
                functionModel.getGameModel().setState(State.SELECTPOWERUPINPUT);
            } else {
                
                functionModel.getGameModel().setMessageToCurrentView("YOU CAN'T USE TAGBACK GRENADE IN YOUR TURN");
                functionModel.getGameModel().setBeforeError(functionModel.getGameModel().getState());
                functionModel.getGameModel().setState(State.ERROR);
            }
        } else {
            
            mapErrorGestor(functionModel);
        }
        
    }
    
    public void usePowerUpController(FunctionModel functionModel, RemoteView view) throws RemoteException{

        //NEWTON
        if (Newton.class.equals(functionModel.getGameModel().getPowerUpSelected().getClass())) {
            Player targetPlayer;
            Square targetSquare;
            
            try {
                //get input
                targetPlayer = functionModel.getGameModel().getPlayerById(view.getTarget1());
                targetSquare = functionModel.getGameModel().getMap().getSquare(view.getRow(), view.getColumn());
                //effect
                    functionModel.usePowerUpNewton((Newton)functionModel.getGameModel().getPowerUpSelected(),targetPlayer, targetSquare);
            } catch (NotInSameDirection notInSameDirection) {


            } catch (NotValidDistance notValidDistance) {

  
            } catch (MapException e) {
    
            }
            //TAGBACK GRANATE
        } else if (functionModel.getGameModel().getPowerUpSelected().getClass().equals(TagBackGrenade.class)) {
    
            Player targetPlayer;
            try {
                
                //get input
                targetPlayer = functionModel.getGameModel().getPlayerById(view.getTarget1());
                //effect
                functionModel.usePowerUpTagBackGrenade((TagBackGrenade) functionModel.getGameModel().getPowerUpSelected(), targetPlayer);
            } catch (NotVisibleTarget notVisibleTarget) {

            } catch (MapException e) {
            
            }
    
    
            //TELEPORTER
        } else if (functionModel.getGameModel().getPowerUpSelected().getClass().equals(Teleporter.class)) {
    
            Square targetSquare;
            try {
    
                //get input
                targetSquare = functionModel.getGameModel().getMap().getSquare(view.getRow(), view.getColumn());
                //effect
                functionModel.usePowerUpTeleporter((Teleporter) functionModel.getGameModel().getPowerUpSelected(), targetSquare);
            }catch (MapException e) {
                e.printStackTrace();
            }
    
            //TARGETING SCOPE
        } else if (functionModel.getGameModel().getPowerUpSelected().getClass().equals(TargetingScope.class)) {
    
            Player targetPlayer;
            
            try {
                
                //get input
                targetPlayer = functionModel.getGameModel().getPlayerById(view.getTarget1());
                //effect
                functionModel.usePowerUpTargetingScope((TargetingScope) functionModel.getGameModel().getPowerUpSelected(), targetPlayer);
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
    
    
}


