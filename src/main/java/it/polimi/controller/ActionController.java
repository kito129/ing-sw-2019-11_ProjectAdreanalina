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
import java.util.ArrayList;


/**
 * The type Action controller.
 */
public class ActionController {
    
    private State beforeError;
    
    
    public void errorState(ActionModel actionModel) throws RemoteException {
        System.out.println("STATO DI ERRORE, RIPARTO DAL PRECENDENTE\n");
        
        if(beforeError==State.RUN){
            System.out.println("input di RUN errarti\n Ripartiamo..");
            actionModel.getGameModel().setState(State.SELECTRUN);
        };
        
        
    }
    
    
    /**
     * Run action .
     *
     * @param actionModel the action model
     */
    public void runActionController (ActionModel actionModel, RemoteView view) throws RemoteException {
        
        
        //take necessary
        Map map= actionModel.getGameModel().getMap();
        
        //answer to view an input Square
        Square inputSquare = null;
        try {
            inputSquare = map.getSquare(view.getRow(),view.getColumn());
            if(map.existInMap(inputSquare)) {
                //set the state in run
                actionModel.getGameModel().setState(State.RUN);
    
                actionModel.runActionModel(actionModel.getGameModel().getActualPlayer(), inputSquare);
                view.resetInput();
            }
        } catch (MapException e) {
            e.printStackTrace();
            this.beforeError=actionModel.getGameModel().getState();
            actionModel.getGameModel().setState(State.ERROR);
        } catch (NotValidSquareException e) {
            e.printStackTrace();
            this.beforeError=actionModel.getGameModel().getState();
            actionModel.getGameModel().setState(State.ERROR);
        } catch (RunActionMaxDistLimitException e) {
            System.out.println("massima distanza superata");
            this.beforeError=actionModel.getGameModel().getState();
            actionModel.getGameModel().setState(State.ERROR);
        } catch (NotValidInput notValidInput) {
            notValidInput.printStackTrace();
            this.beforeError=actionModel.getGameModel().getState();
            actionModel.getGameModel().setState(State.ERROR);
        }
        
    }
    
    
    /**
     * Grab action.
     *
     * @param actionModel the action model
     */
    public void grabActionController (ActionModel actionModel,RemoteView view) throws RemoteException{
    
        //take necessary
        Map map =actionModel.getGameModel().getMap();
        
        
        //answer to view an input Square
        Square inputSquare = null;
        try {
            inputSquare = map.getSquare(view.getRow(),view.getColumn());
        } catch (MapException e) {
            e.printStackTrace();
        }
        Integer indexWeapon=-1;
        
        //temp variables
        if(map.existInMap(inputSquare)) {
    
    
            //guardo se la square è di generation, se si devo chidere alla view l'index dell'arma , altrimenti passo a null
            if (map.isGenerationSquare(inputSquare)) {
                indexWeapon=view.getIndex();
            }
    
            //effective catch gia con l'index giusto se è una Generation Square
            try {
        
                actionModel.grabActionModel(inputSquare, indexWeapon);
                
            } catch (GrabActionMaxDistLimitException catchActionMaxDistExpetion) {
                System.out.println("massima distanza superata");
                this.beforeError=actionModel.getGameModel().getState();
                actionModel.getGameModel().setState(State.ERROR);
        
            } catch (GrabActionFullObjException e) {
                e.printStackTrace();
                this.beforeError = actionModel.getGameModel().getState();
                actionModel.getGameModel().setState(State.ERROR);
    
            } catch (MapException e) {
                e.printStackTrace();
                this.beforeError=actionModel.getGameModel().getState();
                actionModel.getGameModel().setState(State.ERROR);
            }
        }else  {
            this.beforeError=actionModel.getGameModel().getState();
            actionModel.getGameModel().setState(State.ERROR);
        }
    }
    
    /**
     * Use power up.
     *
     * @param actionModel the action model
     * @param powerUpCard the power up card
     * @throws NoPowerUpAvailable  no power up available
     */
    public void usePowerUpController(ActionModel actionModel,PowerUpCard powerUpCard) throws NoPowerUpAvailable, NotValidInput, MapException, RemoteException {

        //NEWTON
        if (Newton.class.equals(powerUpCard.getClass())) {

            //chiedi i parametri di newton
            Player targetPlayer = actionModel.getGameModel().getMap().playersOnSquare(actionModel.getGameModel().getMap().getSquare(1,2)).get(0);
            Square targetSquare = new Square(1,1,EnumColorSquare.PINK);
            try {

                actionModel.usePowerUpNewton((Newton)powerUpCard,targetPlayer, targetSquare);
                System.out.println("NETWON MUOVO SIMO IN 1,1");
            } catch (NotInSameDirection notInSameDirection) {

                //TODO
            } catch (NotValidDistance notValidDistance) {

                //TODO
            } catch (MapException e) {
                e.printStackTrace();
            }
    
            //TAGBACK GRANATE
        } else if (powerUpCard.getClass().equals(TagBackGrenade.class)) {

            //chiedi i parametri di TAGBACK GRANATE
            Player targetPlayer = actionModel.getGameModel().getMap().playersOnSquare(actionModel.getGameModel().getMap().getSquare(1,2)).get(0);
            targetPlayer.stampa();
            try {

                actionModel.usePowerUpTagBackGrenade((TagBackGrenade) powerUpCard,targetPlayer );
                System.out.println("TAG BACKfaccio danno di uno ad andre con colre di simo");
            } catch (NotVisibleTarget notVisibleTarget) {
                notVisibleTarget.printStackTrace();
            }

        //TELEPORTER
        } else if (powerUpCard.getClass().equals(Teleporter.class)) {

            //chiedi i parametri di teleporter
            Square targetSquare = new Square(2,3,EnumColorSquare.YELLOW);
            actionModel.usePowerUpTeleporter((Teleporter) powerUpCard, targetSquare);
            System.out.println("TELPORTTE muovo adnre in 2,3");

        //TARGETING SCOPE
        } else if (powerUpCard.getClass().equals(TargetingScope.class)) {

            //chiedi i parametri di TargetingScope
            //gestione di quando farlo verifica sul danno
            Player targetPlayer = actionModel.getGameModel().getMap().playersOnSquare(actionModel.getGameModel().getMap().getSquare(1,3)).get(0);
            actionModel.usePowerUpTargetingScope((TargetingScope) powerUpCard, targetPlayer);
            System.out.println("TARGETIN SCOPE faccio danno di uno a teo");

        }
    }
    
    /**
     * Recharge.
     *
     * @param player the player want to recharge
     * @param weapon the weapon want to recharge
     */
    public void rechargeController(Player player, ArrayList<WeaponCard> weapon){

       WeaponCard weaponToCharge=weapon.get(0);
        //fino a che ho armi disponibili
        while (weapon.size()>0) {

            // faccio scegliere al player quali armi sono scariche,
            // mi tornerà un index, che setto qui sotto
            int viewSelection = 1;

            //seleziono l'arma e vedo il costo
                
            //prendo il costo di ricarica
            ArrayList<EnumColorCardAndAmmo> rechargeCost = weaponToCharge.getRechargeCost();
            System.out.println("costo di ricarica: " + rechargeCost);

            try {
                payAmmoController(player,rechargeCost);
                weaponToCharge.setCharge(true);
                weapon.remove(0);

            } catch (NotValidAmmoException e) {

            } catch (NoPowerUpAvailable noPowerUpAvailable) {
                noPowerUpAvailable.printStackTrace();
            }
            
        }
    }
    
    /**
     * Pay ammo controller..
     *
     * @param player    the player want to pay
     * @param ammoToPay Array list of  ammo to pay
     * @throws NotValidAmmoException  not valid ammo exception
     * @throws NoPowerUpAvailable       no power up available
     */
    public void payAmmoController (Player player, ArrayList<EnumColorCardAndAmmo> ammoToPay) throws NotValidAmmoException, NoPowerUpAvailable {

        //prendo playerboard
        PlayerBoard playerBoard = player.getPlayerBoard();

        //var temporanee
        ArrayList<EnumColorCardAndAmmo> avaibleAmmo = new ArrayList<>(playerBoard.getAmmo());
        ArrayList<EnumColorCardAndAmmo> avaiblePowerUpAsAmmo = new ArrayList<>();

        for (PowerUpCard a : playerBoard.getPlayerPowerUps()) {

            avaiblePowerUpAsAmmo.add(a.getColorPowerUpCard());
        }
        
        System.out.println("avaiable ammo" + avaibleAmmo);
        System.out.println("avaiable powerup ammo" + avaiblePowerUpAsAmmo);
        if (avaiblePowerUpAsAmmo.size()==0){
            throw  new NoPowerUpAvailable();
        }
        
    
        if (false) {
            
            //pago e rendo carica l'arma

            playerBoard.decreaseAmmo(ammoToPay);
            System.out.println("sono qui");

        } else {

            // avviso la view che con solo le ammo non può pagare
            //verifico allora se usando i power up può pagare,

            ArrayList<EnumColorCardAndAmmo> tempAvaible = new ArrayList<>();
            tempAvaible.addAll(avaibleAmmo);
            tempAvaible.addAll(avaiblePowerUpAsAmmo);
            System.out.println(tempAvaible);
            
            if (tempAvaible.containsAll(ammoToPay)) {

                // posso pagare usando ammo e power up

                // chiedo alla view se lo vuole fare
                Boolean viewAnswer = true;
                System.out.println(ammoToPay);

                if (viewAnswer) {

                    for (EnumColorCardAndAmmo a : ammoToPay) {

                        if (avaibleAmmo.contains(a)) {

                            playerBoard.decreaseAmmo(a);
                        } else if (avaiblePowerUpAsAmmo.contains(a)) {
                        
                        System.out.println("sonoqui");
                         playerBoard.getPlayerPowerUps().remove(a);
                        }else {
                            throw new NotValidAmmoException();
                        }
                    }
                }
            } else {
                throw new NotValidAmmoException();
            }
        }
    }
    
    /**
     * Respawn player .
     *
     * @param actionModel the action model
     */
    public void respawnPlayerController (ActionModel actionModel){
    
        //get dead Player
        ArrayList<Player> deadPlayer = actionModel.getGameModel().getDeadPlayers();
        
        for (Player a: deadPlayer){
            //devo essere rianimaro, pesco due power up ne scelgo uno e vado nel colore di quello che scarto
            
            //mostro 2 powerup peschati dal mazzo e faccio sceglere al utente quale tenere
            PowerUpCard powerUp1 = new PowerUpCard("NEWTON",EnumColorCardAndAmmo.RED);
            PowerUpCard powerUp2= new PowerUpCard("TAGBACKGANATE",EnumColorCardAndAmmo.BLU);
            
            //utente mi dice quale usare da quello prendo il colore
            
            EnumColorCardAndAmmo chosedColor = powerUp2.getColorPowerUpCard();
            
            //adesso devo cercare la qaure di geneazione giusta dopo ho tutti i paramentri da passare
            //Square destSquare = gameModel.getMap().getGenerationSquare(chosedColor);
            
            //actionModel.respawnPlayerController(a,destSquare,powerUp1);
            
            
        }
    }
    
    /**
     * Scoring player board.
     *
     * @param actionModel the action model
     */
    public void scoringPlayerBoardController (ActionModel actionModel){
        
        //get dead Player
        ArrayList<Player> deadPlayer = actionModel.getGameModel().getDeadPlayers();
        
        for (Player a:deadPlayer){
            //incasso una plancia alla volta e gestisco le mort
            actionModel.scoringPlayerBoard(a);
        }
    
    }
    
    
    //GESTIONE DELLE ARMI
    //errori di mappa map exception non dovrebbero mai verificarsi perchè garantisci che non verranno mai sollevate.
    //solo per ricordatelo gli errori di visibilità distanza... inglobano anche errori di mappa oltre agli errori stessi
    
    
    /**
     * Lock rifleweapon.
     *
     * @param gameModel the gameModel model
     * @param weapon    the weapon
     */
    public void LockRifleweapon(GameModel gameModel, LockRifle weapon, RemoteView view) throws RemoteException{
    
        //use always base and optional for second effect
        //necessary from model
        Player currentPlayer = gameModel.getActualPlayer();
        Map map = gameModel.getMap();
        //necessary input
        Player targetBase;
        Player targetSecondLock;
        //get the effect  based on choice input fro view
        boolean choice;
        choice=view.isUseSecondEffect();
        
        if(!choice) {
            //base effect
            if (gameModel.getPlayerById(view.getTarget1()) != null) {
        
                //setto solo target base
                targetBase = gameModel.getPlayerById(view.getTarget1());
                try {
            
                    weapon.baseEffect(map, currentPlayer, targetBase);

                } catch (NotVisibleTarget notVisibleTarget) {

                    System.out.println("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                }
            } else {
                //errore
    
            }
        } else {
            //second lock effect
            if(gameModel.getPlayerById(view.getTarget1()) != null && gameModel.getPlayerById(view.getTarget2())!=null) {
                
                //prendo tutti e due i parametri
                targetBase = gameModel.getPlayerById(view.getTarget1());
                targetSecondLock = gameModel.getPlayerById(view.getTarget2());
            
            try {
        
                if (targetBase != targetSecondLock) {
            
                    weapon.secondLockEffect(map, currentPlayer, targetSecondLock);
                } else {
            
                    throw new NotValidInput();
                }
            } catch (NotValidInput notValidInput) {
        
                System.out.println("ERROR: THE CHOSEN TARGET IS THE SAME OF BASIC EFFECT");
            } catch (NotVisibleTarget notVisibleTarget) {

                System.out.println("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
            }
            }else {
                //errore
            }
    
        }
        
    }
    
    /**
     * Electroscythe weapon.
     *
     * @param gameModel the gameModel model
     * @param weapon    the weapon
     */
    public void ElectroscytheWeapon(GameModel gameModel, Electroscythe weapon, RemoteView view) throws RemoteException{
    
        //necessary from model
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        //necessary input
        WeaponsEffect effect = view.getWeaponsEffect();
        

        switch (effect){
            case BaseMode:

                try {

                    weapon.baseMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {

                    System.out.println("ERROR: NO TARGET IN YOUR SQUARE");
                } catch (MapException mapException){

                    System.out.println("ERROR: MAP ERROR");
                }
    
            case ReaperMode:

                try {
                    
                    weapon.reaperMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {

                    System.out.println("ERROR: NO TARGET IN YOUR SQUARE");
                } catch (MapException mapException ) {

                    System.out.println("ERROR: MAP ERROR");
                }
        }

    }
    
    /**
     * Machine gun.
     *
     * @param gameModel the gameModel model
     * @param weapon    the weapon
     */
    //todo da finire lasciare lla fine
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
        //get the effect  based on choice input fro view
        boolean choice1;
        choice1 = view.isUseSecondEffect();
    
        if (!choice1) {
            //go to base bod
            if (gameModel.getPlayerById(view.getTarget1()) != null) {
            
            }
            //get the target
            target1Base = gameModel.getPlayerById(view.getTarget1());
            target2Base = gameModel.getPlayerById(view.getTarget2());
    
            try {
        
                if (target2Base == null) {
            
                    ArrayList<Player> targetBase = new ArrayList<>();
                    targetBase.add(target1Base);
                    weapon.baseEffect(map, currentPlayer, targetBase);
                } else if (target1Base != target2Base) {
            
                    ArrayList<Player> targetBase = new ArrayList<>();
                    targetBase.add(target1Base);
                    targetBase.add(target2Base);
                    weapon.baseEffect(map, currentPlayer, targetBase);
                } else {
            
                    throw new NotValidInput();
                }
            } catch (NotVisibleTarget notVisibleTarget) {
        
                //gestione target 1 o 2 effetto base non visibili con il curretnPLayer
            } catch (NotValidInput notValidInput) {
        
                //gestione target 1==target 2 effetto base
            }
        } else {
            switch (effect) {
                case FocusShotEffect:
    
                    if (gameModel.getPlayerById(view.getTarget1()) != null && gameModel.getPlayerById(view.getTarget3()) != null) {
    
                        //get the target
                        target1Base = gameModel.getPlayerById(view.getTarget1());
                        target2Base = gameModel.getPlayerById(view.getTarget2());
                        targetFocusShot = gameModel.getPlayerById(view.getTarget3());
                        
    
                        try {
        
                            if (targetFocusShot == target1Base || targetFocusShot == target2Base) {
            
                                weapon.focusShotEffect(currentPlayer, targetFocusShot);
            
                            } else {
            
                                throw new NotValidInput();
                            }
                        } catch (NotValidInput notValidInput) {
        
                            //gestione se target focus shot dovesse essere diverso sia da target 1 sia target 2 effetto base
                        }
                    } else {
                        //errore
                    }
                    break;
                case TurretTripodEffect:
                    
                    if (gameModel.getPlayerById(view.getTarget1()) != null && gameModel.getPlayerById(view.getTarget3()) != null && gameModel.getPlayerById(view.getTarget4()) != null) {
                    
                    //get the target
                    target1Base = gameModel.getPlayerById(view.getTarget1());
                    target2Base = gameModel.getPlayerById(view.getTarget2());
                    targetFocusShot = gameModel.getPlayerById(view.getTarget3());
                    targetTurretTripod = gameModel.getPlayerById(view.getTarget4());
                    
                    //todo da finire
                    
                    break;
                } else  {
                    //errore
                    }
            }
        }
    }
    
    
    /**
     * Tractor beam.
     *
     * @param gameModel the gameModel model
     * @param weapon    the weapon
     */
    public void TractorBeam(GameModel gameModel, TractorBeam weapon, RemoteView view) throws RemoteException {
    
        //necessary from model
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        //necessary input
        WeaponsEffect effect = view.getWeaponsEffect();
        //necessary inpu
        Square destSquareBase;
        Player targetBaseOrPunisher;

        switch(effect) {

            case BaseMode:
                if (gameModel.getPlayerById(view.getTarget1()) != null ) {
                    
                    try {
                        //get the target
                        destSquareBase = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                        targetBaseOrPunisher = gameModel.getPlayerById(view.getTarget1());
                        
                        //do the effect
                        weapon.baseMode(map, destSquareBase, currentPlayer, targetBaseOrPunisher);
                    } catch (NotVisibleTarget notVisibleTarget) {

                        System.out.println("ERROR: THE DESTINATION SQUARE IS NOT VISIBLE FROM YOUR POSITION");
        
                        //gestire il fatto che la il giocatore ha spostato il target in una square che non vede
                    } catch (NotValidDistance notValidDistance) {

                        System.out.println("ERROR: YOU CAN'T MOVE YOUR TARGET MORE THAN TWO MOVES");
        
                        //gestire il fatto che il giocatore prova a muovere il target  più di 2 quadrati
                    } catch (MapException mapException) {

                        System.out.println("ERROR: MAP ERROR");
                    }
                } else {
                    //errore
                }

            case PunisherMode:
    
                if (gameModel.getPlayerById(view.getTarget1()) != null ) {

                    targetBaseOrPunisher = gameModel.getPlayerById(view.getTarget1());
                    try {
        
                        weapon.punisherMode(map, currentPlayer, targetBaseOrPunisher);
                    } catch (NotValidDistance notValidDistance) {

                        System.out.println("ERROR: THE CHOSEN TARGET IS MORE THAN THO MOVES FROM YOU");

                        //gestire se il target scelto non si trova a 0,1,2 quadrate dal currentplayer.
                    } catch (MapException mapException) {

                        System.out.println("ERROR: MAP ERROR");
                    }
                }else{

                    //errore
                }
        }
    }
    
    /**
     * Thor.
     *
     * @param gameModel the gameModel model
     * @param weapon    the weapon
     */

    //todo da rivedere la logica di marco da lasciare alla fine
    public void Thor(GameModel gameModel, Thor weapon, RemoteView view) throws RemoteException {
        
        //necessary from model
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        //necessary input
        Player targetBase;
        Player targetChainReaction;
        Player targetHighVoltage;
        //questa carta limita l'ordine con cui puoi usare i suoi effetti.
        boolean choice1;
        boolean choice2;
        choice1 = view.isUseSecondEffect();
        choice2 = view.isUseThirdEffect();
        
        //go to base effect
        if (gameModel.getPlayerById(view.getTarget1()) != null) {
    
            //get the target
            targetBase = gameModel.getPlayerById(view.getTarget1());
    
            try {
        
                weapon.baseEffect(map, currentPlayer, targetBase);
            } catch (NotVisibleTarget notVisibleTarget) {

                System.out.println("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
        
                // gestione target base non visibile
            }
            if (choice1) {
                //chain reaction
                if (gameModel.getPlayerById(view.getTarget1()) != null && gameModel.getPlayerById(view.getTarget2()) != null) {
    
                    //get the target
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    targetChainReaction = gameModel.getPlayerById(view.getTarget2());
    
                    try {

                        if ((map.isVisible(targetBase, targetChainReaction)) && (targetBase != targetChainReaction)) {
            
                            weapon.chainReactionEffect(currentPlayer, targetChainReaction);
                        } else {
            
                            throw new NotValidInput();
                        }
                    } catch (NotValidInput notValidInput) {

                        System.out.println("ERROR: THE TARGET OF BASE EFFECT DON'T SEE THE CHOSEN TARGET OR THE CHOSEN TARGET IS NOT DIFFERENT " +
                                "FROM THE TARGET OF BASE EFFECT ");
                        //gestisce il fatto che il target effetto base non vede target chain reaction o che target base sia uguale a target chain rection
                    }
                    if (choice2) {
                        //high voltage
                        //usabile solo se si ha usato chain reaction.
                        if (gameModel.getPlayerById(view.getTarget1()) != null && gameModel.getPlayerById(view.getTarget2()) != null && gameModel.getPlayerById(view.getTarget3()) != null) {
        
                            //get the target
                            targetBase = gameModel.getPlayerById(view.getTarget1());
                            targetChainReaction = gameModel.getPlayerById(view.getTarget2());
                            targetHighVoltage = gameModel.getPlayerById(view.getTarget3());
                            try {
            
                                if ((map.isVisible(targetChainReaction, targetHighVoltage)) && (targetChainReaction != targetHighVoltage)
                                        && (targetBase != targetHighVoltage)) {
                
                                    weapon.highVoltageEffect(currentPlayer, targetHighVoltage);
                                } else {
                
                                    throw new NotValidInput();
                                }
                            } catch (NotValidInput notValidInput) {

                                System.out.println("ERROR: THE TARGET OF CHAIN REACTION DON'T SEE THE CHOSEN TARGET OR THE CHOSEN TARGET IS NOT DIFFERENT" +
                                        "FROM THE TARGET OF BASE EFFECT AND FROM THE TARGET OF CHAIN REACTION");
            
                                //gestione il fatto che target chain reaction non veda target high voltage e che i target non siano tutti diversi
                            }
                        }
    
                    } else {
                        //errore high voltage
                    }
                }
            } else {
                //errore  chain
            }
        } else {
            //errore base
        }
    }
    
    /**
     * Plasma gun.
     *
     * @param gameModel the gameModel model
     * @param weapon    the weapon
     */
    //todo da lasciare alla fine
    public void PlasmaGun (GameModel gameModel, PlasmaGun weapon, RemoteView view) throws RemoteException  {
    
        //necessary from model
        Map map= gameModel.getMap();
        Player currentPlayer= gameModel.getActualPlayer();
        //necessary input
        Player targetBase;
        Square destSquarePhaseGlide;
        //necessary input effect
        WeaponsEffect effect = view.getWeaponsEffect();
        //get choise
        boolean choice1;
        choice1 = view.isUseSecondEffect();

        
        if(!choice1){
    
            if (gameModel.getPlayerById(view.getTarget1()) != null ) {
    
                //get the target
                targetBase = gameModel.getPlayerById(view.getTarget1());
                try {
        
                    weapon.baseEffect(map, currentPlayer, targetBase);
                } catch (NotVisibleTarget notVisibleTarget) {

                    System.out.println("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");
                }
    
            } else {
                //errore base mode
            }
        } else {
            //second effect
            switch (effect){
                
                case PhaseGlideEffect:
                    //usabile prima o dopo l'effetto base
                    try {

                        destSquarePhaseGlide = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                        weapon.phaseGlideEffect(map,destSquarePhaseGlide,currentPlayer);
                    } catch (MapException e) {

                        System.out.println("ERROR: MAP ERROR");
                    } catch (NotValidDistance notValidDistance) {

                        System.out.println("ERROR: YOU CAN MOVE YOUR TARGET ONLY ONE OR TWO MOVES");
                    }
                    break;
                case ChargedShotEffect:
                    //nessuna eccezione da lanciare;il target è lo stesso dell'effetto base quindi il controllo sulla visibilità è gia garantito
                    //quando viene chiamaro l'effetto base-->sicuramente l'effetto base è stato usato se usiamo questo effetto.
    
                    if (gameModel.getPlayerById(view.getTarget1()) != null ) {
        
                        //get the target
                        targetBase = gameModel.getPlayerById(view.getTarget1());
                        
                        //effect
                        weapon.chargedShotEffect(currentPlayer,targetBase);
                   
                    break;
            } else {
                    //errore ChargedShotEffect
                    }
            }
        }
    }
    /**
     * Whisper.
     *
     * @param gameModel the gameModel model
     * @param weapon    the weapon
     */
    public void Whisper(GameModel gameModel, Whisper weapon, RemoteView view) throws RemoteException {
    
        //necessary from model
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        //necessary input
        Player targetBase;
        // unico effetto metto solo la chiamata del metodo, non ti metto il case switch, vedrai tu come gestire..marco number one.
    
        if (gameModel.getPlayerById(view.getTarget1()) != null) {
        
            //get the target
            targetBase = gameModel.getPlayerById(view.getTarget1());
    
            try {
        
                weapon.baseEffect(map, currentPlayer, targetBase);
            } catch (NotValidDistance notValidDistance) {

                System.out.println("ERROR: THE CHOSEN TARGET IS NOT AT LEAST 2 MOVES FROM YOU");
                //gestione target distante meno di due movimenti
            } catch (NotVisibleTarget notVisibleTarget) {

                System.out.println("ERROR: THE CHOSEN TARGET IS NON VISIBLE");
            }
        }
        else {
            //errore base mode
        }
    }
    
    /**
     * Vortex cannon.
     *
     * @param gameModel the gameModel model
     * @param weapon    the weapon
     */


    public void VortexCannon (GameModel gameModel, VortexCannon weapon, RemoteView view) throws RemoteException {
    
        //necessary from model
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        //necessary input
        Player targetBase;
        Square vortexSquare;
        Player target1BlackHole;
        Player target2BlackHole; // se viene scelto solo un target questo deve essere messo a null.
        //get choise
        boolean choice1;
        choice1 = view.isUseSecondEffect();

        
        if(!choice1) {
            //base effect
    
            if (gameModel.getPlayerById(view.getTarget1()) != null) {
        
                //get the target
                targetBase = gameModel.getPlayerById(view.getTarget1());
                try {
                    vortexSquare = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    weapon.baseEffect(map, vortexSquare, currentPlayer, targetBase);
                } catch (NotVisibleTarget notVisibleTarget) {

                    System.out.println("ERROR: THE CHOSEN SQUARE IS NOT VISIBLE FROM YOUR POSITION");
        
                    //gestione square scelta non è visibile dal current PLyaer.
                } catch (NotValidDistance notValidDistance) {

                    System.out.println("ERROR: THE CHOSEN SQUARE IN NOT AT LEAST ONE MOVE FROM YOU OR THE CHOSEN TARGET IS NOT " +
                            "ON VORTEX SQUARE OR IS NOT DISTANCE ONE MOVE FROM THE VORTEX SQUARE");
        
                    //gestione square scelta non è ad almeno un movimento di distanza dal current player, oppure viene lanciata se il target scelto non si trova
                    // ne ad un movimento dalla sqaure scelta ne sulla square scelta.
                } catch (MapException mapException) {

                    System.out.println("ERROR: MAP ERROR");
        
                }
    
            } else {
                //error base effect
            }
        } else{
            //black hole effect

            if (gameModel.getPlayerById(view.getTarget1()) != null&& gameModel.getPlayerById(view.getTarget2()) != null && gameModel.getPlayerById(view.getTarget3()) != null) {

                try {
                    //get input
                    vortexSquare = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
                    targetBase = gameModel.getPlayerById(view.getTarget1());
                    target1BlackHole = gameModel.getPlayerById(view.getTarget2());
                    target2BlackHole = gameModel.getPlayerById(view.getTarget3());
                    if (target2BlackHole == null) {

                        if (target1BlackHole != targetBase) {

                            ArrayList<Player> targetsBlackHole = new ArrayList<>();
                            targetsBlackHole.add(target1BlackHole);
                            try {
                                weapon.blackHoleEffect(map, vortexSquare, currentPlayer, targetsBlackHole);
                            } catch (NotValidDistance notValidDistance) {

                                System.out.println("ERROR: THE CHOSEN TARGET IS NOT ON VORTEX SQUARE OR IS NOT DISTANCE " +
                                        "ONE MOVE FROM THE VORTEX SQUARE");
                            } catch (MapException e) {

                                System.out.println("ERROR: MAP ERROR");
                            }
                        } else {

                            System.out.println("THE CHOSEN TARGET IS THE SAME OF BASE EFFECT");
                        }
                    } else {

                        if ((target1BlackHole != target2BlackHole) && (target1BlackHole != targetBase) && (target2BlackHole != targetBase)) {

                            ArrayList<Player> targetsBlackHole = new ArrayList<>();
                            targetsBlackHole.add(target1BlackHole);
                            targetsBlackHole.add(target2BlackHole);
                            weapon.blackHoleEffect(map, vortexSquare, currentPlayer, targetsBlackHole);
                        } else {

                            throw new NotValidInput();
                        }
                    }
                } catch (MapException e) {
                    e.printStackTrace();
                } catch (NotValidDistance notValidDistance) {
                    notValidDistance.printStackTrace();
                } catch (NotValidInput notValidInput) {
                    notValidInput.printStackTrace();
                }
            } else {
                //erroe black hole
            }
        }
    }


    /**
     * Furnace.
     *
     * @param gameModel the gameModel model
     * @param weapon    the weapon
     */
    public void Furnace(GameModel gameModel, Furnace weapon, RemoteView view) throws RemoteException {
    
        //necessary from model
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        //necessary input
        EnumColorSquare roomTarget;
        Square targetSquareCozy;
        //necessary input effect
        WeaponsEffect effect = view.getWeaponsEffect();
    
        try {
            //get input
            targetSquareCozy = gameModel.getMap().getSquare(view.getRow(), view.getColumn());
            roomTarget=view.getColorRoom();
    
            switch (effect){
        
                case BaseMode:
            
                    try{
                
                        weapon.baseMode(map,currentPlayer,roomTarget);
                    }catch (NotValidDistance notValidDistance){

                        System.out.println("ERROR: CHOOSE A DIFFERENT ROOM THAN YOURS ");
                
                        //gestire se la stanza selezionata è le stanza del currente player
                
                    }catch (NotVisibleTarget notVisibleTarget){

                        System.out.println("ERROR: THE CHOSEN ROOM IS NOT VISIBLE ");

                        //gestire se la stanza selezionata non è visibile dal current player.
                    }catch (MapException mapException){

                        System.out.println("ERROR: MAP ERROR");
                
                
                    }
                case CozyFireMode:
            
                    try{
                
                        weapon.cozyFireMode(map,currentPlayer,targetSquareCozy);
                    }catch (NotValidDistance notValidDistance){

                        System.out.println("ERROR: THE CHOSEN SQUARE IS NOT DISTANCE ONE MOVE");
                
                        //quadrato scelto non è distante esattamente un movimento
                    } catch (MapException mapException){

                        System.out.println("ERROR: MAP ERROR");
                
                    }
            }
        } catch (MapException e) {

            System.out.println("ERROR: MAP ERROR");
        }
    }

    public void HeatSeeker(GameModel gameModel, Heatseeker weapon, RemoteView view) throws RemoteException{
    
    
        //necessary from model
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        //necessary input
        Player targetBase;
    
        if (gameModel.getPlayerById(view.getTarget1()) != null) {
    
            //get input
            targetBase = gameModel.getPlayerById(view.getTarget1());
    
            try {
        
                weapon.BaseEffect(map, currentPlayer, targetBase);
            } catch (VisibleTarget visibleTarget) {

                System.out.println("ERROR: THE CHOSEN TARGET IS VISIBLE");
        
                //gestire se il player vede il target o errore di mappa
            }
        } else {
            //errore base mode
        }
      
    }

    public void Hellion(GameModel gameModel, Hellion weapon, RemoteView view) throws RemoteException{
    
        //necessary from model
        Map map = gameModel.getMap();
        Player currentPlayer = gameModel.getActualPlayer();
        //necessary input
        Player targetBaseOrTracer;
        //necessary input effect
        WeaponsEffect effect = view.getWeaponsEffect();
    
        if (gameModel.getPlayerById(view.getTarget1()) != null) {
    
            //get input
            targetBaseOrTracer = gameModel.getPlayerById(view.getTarget1());
            
            switch (effect) {
    
                case BaseMode:
    
                    try {
    
                        weapon.baseMode(map, currentPlayer, targetBaseOrTracer);
                    } catch (NotValidDistance notValidDistance) {

                        System.out.println("ERROR: THE CHOSEN TARGET IN NOT AT LEAST ONE MOVE FROM YOU" );

                        //target non distante almeno 1 movimento
    
                    } catch (NotVisibleTarget notVisibleTarget) {


                        System.out.println("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");

                        // target non visibile

    
                    } catch (MapException mapException) {

                        System.out.println("ERROR: MAP ERROR");
    
                    }
                case NanoTracerMode:
    
                    try {
    
                        weapon.nanoTracerMode(map, currentPlayer, targetBaseOrTracer);
                    } catch (NotValidDistance notValidDistance) {

                        System.out.println("ERROR: THE CHOSEN TARGET IN NOT AT LEAST ONE MOVE FROM YOU" );

                        // target non visibile
    
                    } catch (NotVisibleTarget notVisibleTarget) {

                        System.out.println("ERROR: THE CHOSEN TARGET IS NOT VISIBLE");

    
                    } catch (MapException mapException) {

                        System.out.println("ERROR: MAP ERROR");
                        // eccezzioni di mappa
                    }
            }
        } else {
            //errore input
        }
    }


    //todo da lasciare alla fine
    public void Flamethrower(GameModel gameModel, Flamethrower weapon) throws RemoteException{

        //todo da fare poi
    }


    //todo da lasciare alla fine
    public void GrenadeLauncher(GameModel gameModel, GrenadeLauncher weapon) throws RemoteException{

        Player currentPlayer=new Player();
        Player targetBase=new Player();
        Square destSquareBase=new Square();
        Square targetSquareExtra=new Square();
        Map map=new Map();
        String message="";

        switch (message){

            case"base effect":

                try{

                    weapon.baseEffect(map,targetBase,currentPlayer);

                }catch (NotVisibleTarget notVisibleTarget){

                    //gestione target visibile o errore di mappa
                }

            case"move":
                //non controllo se è stato effettuato l'effetto base prrchè per le armi opzionali l'effetto base è obbligatorio.

                try{

                    weapon.moveTarget(map,targetBase,destSquareBase);
                }catch (NotValidDistance notValidDistance){

                    //distanza di spostamento non valida
                }catch (MapException mapException){

                    //errori di mappa
                }

            case"extra grenade":

                //la move lho messo come metodo a parte perchè può essere usata anche dopo questo effetto

                try{

                    weapon.extraGrenadeEffect(map,currentPlayer,targetSquareExtra);
                }catch (NotVisibleTarget notVisibleTarget){

                    //gestione quadrato scelto non visibile
                }catch (MapException mapExcpetion){

                    //errori di mappa
                }
        }
    }

    //todo lasciare alla fine

    public void RocketLauncher(GameModel gameModel, RocketLauncher weapon) throws RemoteException {

        Player currentPlayer = new Player();
        Map map = new Map();
        Player targetBase = new Player();
        Square destSquareBase = new Square();//se l'utente non vuole fare lo spostamento questo campo sarà null
        Square destSquareJump=new Square();
        String message = "";

        switch (message) {

            case "base effect":

                try {

                    weapon.baseEffect(map, targetBase, currentPlayer, destSquareBase);
                }catch (NotVisibleTarget notVisibleTarget) {

                    //target non visibile

                }catch (NotValidDistance notValidDistance){

                    //errore di distanza,sia se il target è nella tuo stesso quadrato,sia se il movimento che gli facciamo fare al target non è validp
                }catch (MapException mapExcpetion){


                }

            case"rocket jump":

                try{

                    weapon.rocketJumpEffect(map,currentPlayer,destSquareJump);

                }catch (NotValidDistance notValidDistance){

                    //errore di distanza non è 1 o 2.
                }catch (MapException mapException){

                    //errore di mappa
                }
            case"":

                //todo chiedere a marco.
        }

    }

    public void RailGun(GameModel gameModel, Railgun weapon) throws RemoteException{

        Player currentPlayer=new Player();
        Player target1=new Player();
        Player target2=new Player();//se in modalità perforazione viene scelto solo un bersaglio questo è null.
        String direction="";
        String message="";
        Map map=new Map();

        switch (message){

            case"base mode":

                try{

                    weapon.baseMode(map,currentPlayer,target1,direction);
                }catch (NotValidCardinalDirection notValidCardinalDirection){

                    //eccezione lanciata se la stringa passata non è una direzione cardinale
                }catch (NotInDirection notInDirection){

                    //se il player non si trova nella direzione cardinale passata.
                }

            case"piercing":

                try{
                    if(target2!=null){

                        ArrayList<Player> piercingTargets=new ArrayList<>();
                        piercingTargets.add(target1);
                        piercingTargets.add(target2);
                        weapon.piercingMode(map,currentPlayer,piercingTargets,direction);
                    }else{

                        ArrayList<Player> piercingTargets=new ArrayList<>();
                        piercingTargets.add(target1);
                        weapon.piercingMode(map,currentPlayer,piercingTargets,direction);
                    }
                }catch (NotValidCardinalDirection notValidCardinalDirection){

                    ////eccezione lanciata se la stringa passata non è una direzione cardinale
                }catch (NotInDirection notInDirection){

                    //se uno dei player non si trova nella direzione cardinale passata.
                }
        }


    }
    //todo lasciare all fine
    public void Cyberblade(GameModel gameModel, Cyberblade weapon) throws RemoteException{

        Player currentPlayer=new Player();
        Player targetBase=new Player();
        Map map=new Map();
        Square destSquareShadow=new Square();
        Player targetSliceAndDice=new Player();
        String message="";

        switch (message){

            case"base effect":

                try{

                    weapon.baseEffect(map,currentPlayer,targetBase);
                }catch(NotValidDistance notValidDistance){

                    //target non è nella square del curretn player
                }catch (MapException mapException){

                    //errori di mappa
                }

            case"shadowstep":

                try{

                    weapon.shadowstepEffect(map,currentPlayer,destSquareShadow);
                }catch(NotValidDistance notValidDistance){

                    //dest square più di un movimento di distanza
                }catch (MapException mapException){

                    //errori di mappa
                }
            case"Slice and dice":

                try{

                    if(targetBase!=targetSliceAndDice){

                        weapon.sliceAndDiceEffect(map,currentPlayer,targetSliceAndDice);
                    }else{

                        throw new NotValidInput();
                    }
                }catch (NotValidInput notValidInput){

                    //gestione target slice and dice è uguale al target base
                }catch (NotValidDistance notValidDistance){

                    //target slice and dice non è nel quadrato del curretn player.
                }catch (MapException mapException){

                    //errori di mappa
                }
        }
    }

    public void Zx2(GameModel gameModel, Zx2 weapon) throws RemoteException {

        Player currentPlayer = new Player();
        Player target1 = new Player();
        Player target2 = new Player();
        Player target3 = new Player();
        Map map = new Map();
        String message = "";

        switch (message) {

            case "base mode":

                try {

                    weapon.baseMode(map, currentPlayer, target1);
                } catch (NotVisibleTarget notVisibleTarget) {

                    //target non visibile
                }
            case "scanner mode":

                try {

                    if((target3 == null)&&(target2 == null)){

                        ArrayList<Player> scannerModeTargets = new ArrayList<>();
                        scannerModeTargets.add(target1);
                        weapon.scannerMode(map,currentPlayer,scannerModeTargets);
                    }else if(target3==null){

                        if(target1!=target2){

                            ArrayList<Player> scannerModeTargets=new ArrayList<>();
                            scannerModeTargets.add(target1);
                            scannerModeTargets.add(target2);
                            weapon.scannerMode(map,currentPlayer,scannerModeTargets);
                        }else{

                            throw new NotValidInput();
                        }
                    }else{

                        if((target1!=target2)&&(target1!=target3)&&(target2!=target3)){

                            ArrayList<Player> scannerModeTargets=new ArrayList<>();
                            scannerModeTargets.add(target1);
                            scannerModeTargets.add(target2);
                            scannerModeTargets.add(target3);
                            weapon.scannerMode(map,currentPlayer,scannerModeTargets);
                        }else{

                            throw new NotValidInput();
                        }
                    }
                }catch (NotVisibleTarget notVisibleTarget){

                    //uno dei target non è visibile.
                }catch (NotValidInput notValidInput){

                    //errori di imput i target non sono diversi
                }
        }
    }

    //todo lasciare
    public void Shotgun(GameModel gameModel, Shotgun weapon) throws RemoteException{





    }

    //todo laaciare dopo
    public void PowerGlove(GameModel gameModel, PowerGlove weapon) throws RemoteException{


    }

    public void Shockwave(GameModel gameModel, Shockwave weapon) throws RemoteException{

        Player currentPlayer=new Player();
        Player target1Base=new Player();
        Player target2Base=new Player();
        Player target3Base=new Player();
        Map map=new Map();
        ArrayList<Player> allPlayerInGame=new ArrayList<>();
        String message="";

        switch (message){

            case"base mode":

                try{

                    if((target3Base==null)&&(target2Base==null)){

                        weapon.baseMode(map,currentPlayer,target1Base);
                    }else if((target3Base==null)&&(target2Base!=null)) {

                        weapon.baseMode(map, currentPlayer, target1Base, target2Base);
                    }else {

                        weapon.baseMode(map,currentPlayer,target1Base,target2Base,target3Base);
                    }
                }catch (NotValidDistance notValidDistance){

                    //errori di distanza
                }
            case"tsunami":

                try{

                    weapon.tsunamiMode(map,currentPlayer,allPlayerInGame);
                }catch (NotValidDistance notValidDistance){

                    //nessun player è distante un movimento.
                }
        }
    }

    //todo lasciare dopo

    public void Sledgehammer(GameModel gameModel, Sledgehammer weapon) throws RemoteException{

        //todo ripartire da quin
    }










}


