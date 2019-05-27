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
                view.reserInput();
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
     * @param gameModel the game model
     * @param weapon    the weapon
     */
    public void LockRifleweapon(GameModel gameModel, LockRifle weapon,RemoteView view) {


        Player targetBase;
        Player targetSecondLock;

        if(gameModel.getPlayerById(view.getTarget1())!=null) {

             targetBase = gameModel.getPlayerById(view.getTarget1());
        } else
            return;
        if(gameModel.getPlayerById(view.getTarget2())!=null) {

             targetSecondLock = gameModel.getPlayerById(view.getTarget1());
        }else
            return;

        Player currentPlayer =gameModel.getActualPlayer();
        Map map = new Map();
        String message = "base effect";

        switch (message) {
            case "base effect":
                try {

                    weapon.baseEffect(map, currentPlayer, targetBase);
                } catch (NotVisibleTarget notVisibleTarget) {

                }
            case "second Lock Effect":
                try {

                    if (targetBase != targetSecondLock) {

                        weapon.secondLockEffect(map, currentPlayer, targetSecondLock);
                    } else {

                        throw new NotValidInput();
                    }
                } catch (NotValidInput notValidInput) {

                    //gestione targetBase==targetSecondLock

                } catch (NotVisibleTarget notVisibleTarget) {

                    //gestione target non visibile o errore di mappa
                }
        }
        //settare arma scarica
    }
    
    /**
     * Electroscythe weapon.
     *
     * @param gameModel the game model
     * @param weapon    the weapon
     */
    public void ElectroscytheWeapon(GameModel gameModel, Electroscythe weapon){

        Map map=new Map();
        Player currentPlayer= new Player();
        String message="";

        switch (message){
            case "base mode":

                try {

                    weapon.baseMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {

                    //gestione nessun target nella stanza in cui si trova il current player.
                } catch (MapException mapException){

                }
    
            case "reaper mode":

                try {
                    
                    weapon.reaperMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {

                    //gestione nessun target nella stanza in cui si trova il current player
                } catch (MapException mapException ) {

                }
        }
        //settare arma scarica
    }
    
    /**
     * Machine gun.
     *
     * @param gameModel the game model
     * @param weapon    the weapon
     */
    public void MachineGun(GameModel gameModel, MachineGun weapon){

        Player target1Base=new Player();
        Player target2Base=new Player(); // se l'utente per l'effetto base non vuole colpire due target, questo viene messo a null.
        Player targetFocusShot=new Player();
        Player targetTurretTripod=new Player();
        Player currentPlayer=new Player();
        Map map=new Map();
        String message="";

        switch (message){

            case "base effect":

                try{

                    if(target2Base==null){

                        ArrayList<Player> targetBase=new ArrayList<>();
                        targetBase.add(target1Base);
                        weapon.baseEffect(map,currentPlayer,targetBase);
                    }else if(target1Base!=target2Base){

                        ArrayList<Player> targetBase=new ArrayList<>();
                        targetBase.add(target1Base);
                        targetBase.add(target2Base);
                        weapon.baseEffect(map,currentPlayer,targetBase);
                    }else{

                        throw new NotValidInput();
                    }
                }catch (NotVisibleTarget notVisibleTarget){

                    //gestione target 1 o 2 effetto base non visibili con il curretnPLayer
                }catch (NotValidInput notValidInput){

                    //gestione target 1==target 2 effetto base
                }

            case"focus shot effect":

                try{

                    if(targetFocusShot==target1Base || targetFocusShot==target2Base){

                        weapon.focusShotEffect(currentPlayer,targetFocusShot);

                    }else{

                        throw new NotValidInput();
                    }
                }catch (NotValidInput notValidInput){

                    //gestione se target focus shot dovesse essere diverso sia da target 1 sia target 2 effetto base
                }

            case "turret tripod":

                //todo da finire


        }
    }
    
    /**
     * Tractor beam.
     *
     * @param gameModel the game model
     * @param weapon    the weapon
     */
    public void TractorBeam(GameModel gameModel, TractorBeam weapon) {

        Square destSquareBase=new Square();
        Player targetBaseOrPunisher=new Player();
        Player currentPlayer=new Player();
        Map map=new Map();
        String message="";

        switch(message) {

            case "base mode":

                try {

                    weapon.baseMode(map, destSquareBase, currentPlayer, targetBaseOrPunisher);
                } catch (NotVisibleTarget notVisibleTarget) {

                    //gestire il fatto che la il giocatore ha spostato il target in una square che non vede
                } catch (NotValidDistance notValidDistance) {

                    //gestire il fatto che il giocatore prova a muovere il target  più di 2 quadrati
                } catch (MapException mapException){


                }

            case "punisher mode":

                try {

                    weapon.punisherMode(map, currentPlayer, targetBaseOrPunisher);
                } catch (NotValidDistance notValidDistance) {

                    //gestire se il target scelto non si trova a 0,1,2 quadrate dal currentplayer.
                }catch (MapException mapException){


                }
        }
    }
    
    /**
     * Thor.
     *
     * @param gameModel the game model
     * @param weapon    the weapon
     */
    public void Thor(GameModel gameModel, Thor weapon) {

        //questa carta limita l'ordine con cui puoi usare i suoi effetti.
        Player targetBase = new Player();
        Player targetChainReaction = new Player();
        Player targetHighVoltage = new Player();
        Player currentPlayer = new Player();
        Map map = new Map();
        String message = "";

        switch (message) {

            case "base effect":

                try {

                    weapon.baseEffect(map, currentPlayer, targetBase);
                } catch (NotVisibleTarget notVisibleTarget) {

                    // gestione target base non visibile
                }

            case "chain reaction":

                try {

                    if ((map.isVisible(targetBase, targetChainReaction)) && (targetBase != targetChainReaction)) {

                        weapon.chainReactionEffect(currentPlayer, targetChainReaction);
                    } else {

                        throw new NotValidInput();
                    }
                } catch (NotValidInput notValidInput) {

                    //gestisce il fatto che il target effetto base non vede target chain reaction o che target base sia uguale a target chain rection
                }

            case "high voltage":

                //usabile solo se si ha usato chain reaction.

                try {

                    if ((map.isVisible(targetChainReaction, targetHighVoltage)) && (targetChainReaction != targetHighVoltage)
                            && (targetBase != targetHighVoltage)) {

                        weapon.highVoltageEffect(currentPlayer, targetHighVoltage);
                    } else {

                        throw new NotValidInput();
                    }
                } catch (NotValidInput notValidInput) {

                    //gestione il fatto che target chain reaction non veda target high voltage e che i target non siano tutti diversi
                }
        }
    }
    
    /**
     * Plasma gun.
     *
     * @param gameModel the game model
     * @param weapon    the weapon
     */
    public void PlasmaGun (GameModel gameModel,PlasmaGun weapon)  {

        Player targetBase=new Player();
        Player currentPlayer=new Player();
        Square destSquarePhaseGlide=new Square();
        Map map=new Map();
        String message="";

        switch (message){

            case "base effect":

                try{

                    weapon.baseEffect(map,currentPlayer,targetBase);
                }catch(NotVisibleTarget notVisibleTarget){

                    //gestione targetbase non visibile
                }

            case "phase glide":

                //usabile prima o dopo l'effetto base
                try{

                    weapon.phaseGlideEffect(map,destSquarePhaseGlide,currentPlayer);
                }catch(NotValidDistance notValidDistance){

                    //gestire se square di destinazione è distante più di 2 o meno di 1 quadrati dalla square del curretn player
                }catch (MapException mapException){


                }
            case "charged shot":

                //nessuna eccezione da lanciare;il target è lo stesso dell'effetto base quindi il controllo sulla visibilità è gia garantito
                //quando viene chiamaro l'effetto base-->sicuramente l'effetto base è stato usato se usiamo questo effetto.

                weapon.chargedShotEffect(currentPlayer,targetBase);
        }
    }
    
    /**
     * Whisper.
     *
     * @param gameModel the game model
     * @param weapon    the weapon
     */
    public void Whisper(GameModel gameModel,Whisper weapon){

        Player currentPlayer=new Player();
        Player targetBase=new Player();
        Map map=new Map();
        // unico effetto metto solo la chiamata del metodo, non ti metto il case switch, vedrai tu come gestire..marco number one.

        try{

            weapon.baseEffect(map,currentPlayer,targetBase);
        }catch(NotValidDistance notValidDistance){

            //gestione target distante meno di due movimenti
        }catch(NotVisibleTarget notVisibleTarget){

            //gestione target non visibile.
        }
    }
    
    /**
     * Vortex cannon.
     *
     * @param gameModel the game model
     * @param weapon    the weapon
     */
    public void VortexCannon ( GameModel gameModel,VortexCannon weapon) {

        Player currentPlayer = new Player();
        Player targetBase = new Player();
        Square vortexSquare = new Square();
        Player target1BlackHole = new Player();
        Player target2BlackHole = new Player(); // se viene scelto solo un target questo deve essere messo a null.
        Map map = new Map();
        String message = "";

        switch (message) {

            case "base effetc":

                try {

                    weapon.baseEffect(map, vortexSquare, currentPlayer, targetBase);
                } catch (NotVisibleTarget notVisibleTarget) {

                    //gestione square scelta non è visibile dal current PLyaer.
                } catch (NotValidDistance notValidDistance) {

                    //gestione square scelta non è ad almeno un movimento di distanza dal current player, oppure viene lanciata se il target scelto non si trova
                    // ne ad un movimento dalla sqaure scelta ne sulla square scelta.
                }catch (MapException mapException){

                }

            case "black Hole":

                try {

                    if (target2BlackHole == null) {

                        if (target1BlackHole != targetBase) {

                            ArrayList<Player> targetsBlackHole = new ArrayList<>();
                            targetsBlackHole.add(target1BlackHole);
                            weapon.blackHoleEffect(map, vortexSquare, currentPlayer, targetsBlackHole);
                        } else {

                            throw new NotValidInput();
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
                } catch (NotValidDistance notValidDistance) {

                    //gestire se uno dei due bersagli scelti come black hole target non sono nel vortice e non sono neanche
                    //distanti un movimento
                } catch (NotValidInput notValidInput) {

                    //gestire il fatto che i target del buco nero non sono diveri tra di loro e diversi con il target effetto base.
                }catch (MapException mapException){

                }
        }

    }
    
    /**
     * Furnace.
     *
     * @param gameModel the game model
     * @param weapon    the weapon
     */
    public void Furnace(GameModel gameModel,Furnace weapon) {

        EnumColorSquare roomTarget=null;
        Map map=new Map();
        Player currentPlayer=new Player();
        Square targetSquareCozy=new Square();
        String message="";

        switch (message){

            case "base mode":

                try{

                    weapon.baseMode(map,currentPlayer,roomTarget);
                }catch (NotValidDistance notValidDistance){

                    //gestire se la stanza selezionata è le stanza del currente player

                }catch (NotVisibleTarget notVisibleTarget){

                    //gestire se la stanza selezionata non è visibile dal current player.
                }catch (MapException mapException){


                }
            case "cozy fire":

                try{

                    weapon.cozyFireMode(map,currentPlayer,targetSquareCozy);
                }catch (NotValidDistance notValidDistance){

                    //quadrato scelto non è distante esattamente un movimento
                } catch (MapException mapException){

                }


        }
    }

    public void HeatSeeker(GameModel gameModel, Heatseeker weapon){

        Map map=new Map();
        Player currentPlayer=new Player();
        Player targetBase=new Player();
        String message="";

        switch (message){

            case"base effect":

                try{

                    weapon.BaseEffect(map,currentPlayer,targetBase);
                }catch (VisibleTarget visibleTarget){

                    //gestire se il player vede il target o errore di mappa
                }
        }
    }

    public void Hellion(GameModel gameModel,Hellion weapon){

        Player targetBaseOrTracer=new Player();
        Map map=new Map();
        Player currentPlayer=new Player();

        String message="";

        switch (message) {

            case "base mode":

                try {

                    weapon.baseMode(map, currentPlayer, targetBaseOrTracer);
                } catch (NotValidDistance notValidDistance) {
                    // target non visibile

                } catch (NotVisibleTarget notVisibleTarget) {
                    //target non distante almeno 1 movimento

                } catch (MapException mapException) {
                    // eccezzioni di mappa

                }
            case "nano tracer":

                try {

                    weapon.nanoTracerMode(map, currentPlayer, targetBaseOrTracer);
                } catch (NotValidDistance notValidDistance) {
                    // target non visibile

                } catch (NotVisibleTarget notVisibleTarget) {
                    //target non distante almeno 1 movimento

                } catch (MapException mapException) {
                    // eccezzioni di mappa
                }


        }
    }

    public void Flamethrower(GameModel gameModel, Flamethrower weapon){

        //todo da fare poi
    }

    public void GrenadeLauncher(GameModel gameModel,GrenadeLauncher weapon){

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

    public void RocketLauncher(GameModel gameModel,RocketLauncher weapon) {

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

    public void RailGun(GameModel gameModel,Railgun weapon){

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

    public void Cyberblade(GameModel gameModel,Cyberblade weapon){

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

    public void Zx2(GameModel gameModel,Zx2 weapon) {

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

    public void Shotgun(GameModel gameModel,Shotgun weapon){

//todo aspettare lunedi per chiarire lo spostamento
    }


    public void PowerGlove(GameModel gameModel,PowerGlove weapon){


    }

    public void Shockwave(GameModel gameModel,Shockwave weapon){

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

    public void Sledgehammer(GameModel gameModel,Sledgehammer weapon){

        //todo ripartire da quin
    }










}


