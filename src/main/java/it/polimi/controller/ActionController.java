package it.polimi.controller;


import it.polimi.model.*;
//chiedere perche devo importare tutto
import it.polimi.model.Exception.*;
import it.polimi.model.Exception.ControllerException.RoudControllerException.SquareNotExistException;
import it.polimi.model.Exception.ModelException.NotValidAmmoException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionMaxDistLimitException;
import it.polimi.model.Exception.ModelException.RoundModelException.RunActionMaxDistLimitException;
import it.polimi.model.Exception.ModelException.RoundModelException.NoPowerUpAvaible;
import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;
import it.polimi.model.Weapon.*;
import it.polimi.view.cli.Game;

import java.util.ArrayList;


public class ActionController {


    public void runActionController (ActionModel actionModel,Map map) throws SquareNotExistException {

        //answer to view an input Square
        Square inputSquare = new Square(1,0,EnumColorSquare.RED);
        //temp variables
         map.existInMap(inputSquare);

        //effective move
        try {
            actionModel.runActionModel(inputSquare);
        } catch (RunActionMaxDistLimitException e) {
            //da gestire se mossa non valida
        }
    }

    public void grabActionController (ActionModel actionModel,Map map) throws SquareNotExistException {

        //answer to view an input Square
        Square inputSquare = new Square(1,0,EnumColorSquare.RED);
        Integer indexWeapon=null;
        //temp variables
        map.existInMap(inputSquare);


        //guardo se la square è di generation, se si devo chidere alla view l'index dell'arma , altrimenti passo a null
        if(map.isGenerationSquare(inputSquare)){

            //chiedi alla view l'index dellarma
            //solo prova
            indexWeapon= 1;
        } else {
            indexWeapon=null;
        }

        //effective catch gia con l'index giusto se è una Generation Square
        try {

            actionModel.grabActionModel(inputSquare,indexWeapon);
        } catch (CatchActionMaxDistLimitException catchActionMaxDistExpetion) {

        } catch (CatchActionFullObjException e) {

        }
    }

    public void usePowerUpController(ActionModel actionModel,PowerUpCard powerUpCard) throws NoPowerUpAvaible {

        //NEWTON
        if (powerUpCard.getClass().equals(Newton.class)) {

            //chiedi i parametri di newton
            Player targetPlayer = new Player(1,"marco", EnumColorPlayer.BLU);
            Square targetSquare = new Square(1,1,EnumColorSquare.RED);
            try {

                actionModel.usePowerUpNewton((Newton)powerUpCard,targetPlayer, targetSquare);
            } catch (NotInSameDirection notInSameDirection) {

                //TODO
            } catch (NotValidDistance notValidDistance) {

                //TODO
            }

        //TAGBACK GRANATE
        } else if (powerUpCard.getClass().equals(TagBackGrenade.class)) {

            //chiedi i parametri di teleporter
            Player targetPlayer = new Player(1,"marco", EnumColorPlayer.BLU);
            try {

                actionModel.usePowerUpTagBackGrenade((TagBackGrenade) powerUpCard,targetPlayer );
            } catch (NotVisibleTarget notVisibleTarget) {
                notVisibleTarget.printStackTrace();
            }

        //TELEPORTER
        } else if (powerUpCard.getClass().equals(Teleporter.class)) {

            //chiedi i parametri di teleporter
            Square targetSquare = new Square(1,1,EnumColorSquare.RED);
            actionModel.usePowerUpTeleporter((Teleporter) powerUpCard, targetSquare);

        //TARGETING SCOPE
        } else if (powerUpCard.getClass().equals(TargetingScope.class)) {

            //chiedi i parametri di TargetingScope
            //gestione di quando farlo verifica sul danno
            Player targetPlayer = new Player(1,"marco", EnumColorPlayer.BLU);
            actionModel.usePowerUpTargetingScope((TargetingScope) powerUpCard, targetPlayer);

        }
    }

    public void rechargeController(Player player, ArrayList<WeaponCard> weapon){

        //creo var temporanee
        WeaponCard weaponToCharge = new WeaponCard("ciao",EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo> avaiableAmmo = player.getPlayerBoard().getAmmo();


        //fino a che ho armi disponibili
        while (weapon.size()>0) {

            // faccio scegliere al player quali armi sono scariche,
            // mi tornerà un index, che setto qui sotto
            int viewSelection = 1;

            //seleziono l'arma e vedo il costo
            for (WeaponCard a : weapon) {

                weaponToCharge = weapon.get(viewSelection);
                //prendo il costo di ricarica
                ArrayList<EnumColorCardAndAmmo> rechargeCost = weaponToCharge.getRechargeCost();
                rechargeCost.add(weaponToCharge.getColorWeaponCard());

                try {
                    payAmmo(player,rechargeCost);
                    weaponToCharge.setCharge(true);

                } catch (NotValidAmmoException e) {

                } catch (NoPowerUpAvaible noPowerUpAvaible) {
                    noPowerUpAvaible.printStackTrace();
                }
            }
        }
    }




    public void payAmmo(Player player, ArrayList<EnumColorCardAndAmmo> ammoToPay) throws NotValidAmmoException, NoPowerUpAvaible {

        //prendo playerboard
        PlayerBoard playerBoard = player.getPlayerBoard();

        //var temporanee
        ArrayList<EnumColorCardAndAmmo> avaibleAmmo = new ArrayList<>(playerBoard.getAmmo());
        ArrayList<EnumColorCardAndAmmo> avaiblePowerUpAsAmmo = new ArrayList<>();

        for (PowerUpCard a : playerBoard.getPlayerPowerUps()) {

            avaiblePowerUpAsAmmo.add(a.getColorPowerUpCard());
        }
        if (avaiblePowerUpAsAmmo.size()==0){
            throw  new NoPowerUpAvaible();
        }

        if (avaibleAmmo.containsAll(ammoToPay)) {
            //pago e rendo carica l'arma

            playerBoard.decreaseAmmo(ammoToPay);

        } else {

            // avviso la view che con solo le ammo non può pagare
            //verifico allora se usando i power up può pagare,

            ArrayList<EnumColorCardAndAmmo> tempAvaible = new ArrayList<>();
            tempAvaible.addAll(avaibleAmmo);
            tempAvaible.addAll(avaiblePowerUpAsAmmo);

            if (tempAvaible.containsAll(ammoToPay)) {

                // posso pagare usando ammo e power up

                // chiedo alla view se lo vuole fare
                Boolean viewAnswer = true;

                if (viewAnswer) {

                    for (EnumColorCardAndAmmo a : ammoToPay) {

                        if (avaibleAmmo.contains(a)) {

                            playerBoard.decreaseAmmo(a);
                        } else if (avaiblePowerUpAsAmmo.contains(a)) {

                            for (PowerUpCard b : playerBoard.getPlayerPowerUps()) {

                                if (b.getColorPowerUpCard().equals(a)) {

                                    playerBoard.removePowerUp(b);
                                }
                            }
                        }
                    }
                }
            } else {
                throw new NotValidAmmoException();
            }
        }
    }
    
    public void respawnPlayer(ActionModel actionModel,GameModel gameModel, ArrayList<Player> deadPlayer){
        
        for (Player a: deadPlayer){
            //devo essere rianimaro, pesco due power up ne scelgo uno e vado nel colore di quello che scarto
            
            //mostro 2 powerup peschati dal mazzo e faccio sceglere al utente quale tenere
            PowerUpCard powerUp1 = new PowerUpCard("NEWTON",EnumColorCardAndAmmo.RED);
            PowerUpCard powerUp2= new PowerUpCard("TAGBACKGANATE",EnumColorCardAndAmmo.BLU);
            
            //utente mi dice quale usare da quello prendo il colore
            
            EnumColorCardAndAmmo chosedColor = powerUp2.getColorPowerUpCard();
            
            //adesso devo cercare la qaure di geneazione giusta dopo ho tutti i paramentri da passare
            //Square destSquare = gameModel.getMap().getGenerationSquare(chosedColor);
            
            //actionModel.respawnPlayer(a,destSquare,powerUp1);
            
            
        }
    }
    
    public void scoringPlayerBoard(ArrayList<Player> deadPlayer){
        
        for (Player a:deadPlayer){
            //incasso un plancia lal volta
    
    
          
        }
    
    }
    
    
    //GESTIONE DELLE ARMI


    public void LockRifleweapon(GameModel gameModel, LockRifle weapon) {

        Player targetBase = new Player();
        Player targetSecondLock = new Player();
        Player currentPlayer = new Player();
        Map map = new Map();
        String message = "";

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

                    //gestione target non visibile
                }
        }
        //settare arma scarica
    }

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
                }
            
            case "reaper mode":

                try {
                    
                    weapon.reaperMode(map,currentPlayer);
                } catch (NoTargetInSquare noTargetInSquare) {

                    //gestione nessun target nella stanza in cui si trova il current player
                }
        }
        //settare arma scarica
    }

    public void MachineGun(GameModel gameModel, MachineGun weapon){

        Player target1Base=new Player();
        Player target2Base=new Player(); // se l'utente per l'effetto base non vuole colpire due target, questo viene messo a null.
        Player targetFocusShot=new Player();
        Player targetTurretTripod=new Player();
        Player currentPlayer=new Player();
        Map map=new Map();
        String message="";
        ArrayList<Player> targetBase=new ArrayList<>();

        switch (message){

            case "base effect":

                try{

                    if(target2Base==null){

                        targetBase.add(target1Base);
                        weapon.baseEffect(map,currentPlayer,targetBase);
                    }else if(target1Base!=target2Base){

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

                // todo da finire ...chiedere a marco

        }
    }

    public void TractorBeam(GameModel gameModel, TractorBeam weapon){

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
                }
            case "punisher mode":

                try {

                    weapon.punisherMode(map, currentPlayer, targetBaseOrPunisher);
                } catch (NotValidDistance notValidDistance) {

                    //gestire se il target scelto non si trova a 0,1,2 quadrate dal currentplayer.
                }
        }
    }

    public void Thor(GameModel gameModel, Thor weapon){

        //todo ripartire da qui.
        //questa carta limita l'ordine con cui puoi usare i suoi effetti.
    }
}


