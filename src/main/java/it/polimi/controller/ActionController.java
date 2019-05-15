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
import it.polimi.model.Weapon.Electroscythe;
import it.polimi.model.Weapon.LockRifle;
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


    public void LockRifleweapon(GameModel gameModel, LockRifle weapon){

        Player target1=new Player();
        Player target2=new Player();
        Player currentPlayer= new Player();
        Map map = new Map();
        String message="";

        switch (message){
            case "base effect":
                try{

                    weapon.baseEffect(map,currentPlayer,target1);
                }catch (NotVisibleTarget notVisibleTarget){

                }
            case "second Lock Effect":
                try{

                    if(target1!=target2){

                        weapon.secondLockEffect(map,currentPlayer,target2);
                    }else{

                        throw new NotValidInput();
                    }
                }catch (NotValidInput notValidInput){

                    //gestione target1==target2

                }catch (NotVisibleTarget notVisibleTarget){

                    //gestione target non visibile
                }
        }
    }

    //todo ripartire da qui
    
    public void ElectroscytheWeapon(GameModel gameModel, Electroscythe weapon){

        Map map=new Map();
        Player currentPlayer= new Player();
        String message="";

        switch (message){
            case "base mode":

                try {
                    
                    weapon.baseMode(map,currentPlayer);
                    weapon.setCharge(false);
                } catch (NoTargetInSquare noTargetInSquare) {
                
                }
            
            case "reaper mode":
                //gestione per costo extra
                
                //public void reaperMode(Map map,Player currentPlayer) throws NoTargetInSquare {
                try {
                    
                    weapon.reaperMode(map,currentPlayer);
                    weapon.setCharge(false);
                } catch (NoTargetInSquare noTargetInSquare) {
                
                }
        }
    }
    


}


