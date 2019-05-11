package it.polimi.controller;


import it.polimi.model.*;
import it.polimi.model.Exception.ControllerException.RoudControllerException.SquareNotExistException;
import it.polimi.model.Exception.ModelException.NotValidAmmoException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionFullObjException;
import it.polimi.model.Exception.ModelException.RoundModelException.CatchActionMaxDistLimitException;
import it.polimi.model.Exception.ModelException.RoundModelException.RunActionMaxDistLimitException;
import it.polimi.model.Exception.ModelException.RoundModelException.NoPowerUpAvaible;
import it.polimi.model.Exception.NoTargetInSquare;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;
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
            Player targetPlayer = new Player(1,"marco", EnumColorPlayer.BLU);
            actionModel.usePowerUpTargetingScope((TargetingScope) powerUpCard, targetPlayer);
            
        }
    }
    
    public void rechargeController(Player player, ArrayList<WeaponCard> weapon){
    
        //creo var temporanee
        WeaponCard weaponToCharge = null;
    
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
        
        //calcolo powerup come ammo
        for (PowerUpCard a : playerBoard.getPlayerPowerUps()) {
            
            avaiblePowerUpAsAmmo.add(a.getColorPowerUpCard());
        }
        
        if (avaibleAmmo.containsAll(ammoToPay)) {
            //pago
            playerBoard.decreaseAmmo(ammoToPay);
        
        } else if (avaiblePowerUpAsAmmo.size()==0) {
            
            // se non ho power up
            throw new NoPowerUpAvaible();
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
                            
                            //lista di power up che faccio vedere alla view, lui scelge
                            ArrayList<PowerUpCard> powerUpToView = new ArrayList<>();
                            for (PowerUpCard b: playerBoard.getPlayerPowerUps()){
                              powerUpToView.add(b);
                            }
                            //lmi torna l'indice di quale vuole usare
                            String stringWeapon = "NEWTON";
                            
                            // rimuovo quell power up
                            playerBoard.getPlayerPowerUps().removeIf(n ->(n.getNameCard().equals(stringWeapon)));
                        } else {
                            throw new NoPowerUpAvaible();
                        }
                    }
                }
            } else {
                throw new NotValidAmmoException();
            }
        }
    }
    
    public void respawnPlayer(ArrayList<Player> deadPlayer){
        
        for (Player a: deadPlayer){
            //rianimo uno alla volta i player
            
        }
    }
    
    public void scoringPlayerBoard(ArrayList<Player> deadPlayer){
        
        for (Player a:deadPlayer){
            //incasso un plancia lal volta
    
    
            //TODO AVANTI DA QUI 6/05
        }
    
    }
    
    
    //GESTIONE DELLE ARMI
    
    public void ElectroscytheWeapon(GameModel gameModel, Electroscythe weapon){
        
        //chiedi quale dei due effetti vuoi usare
        String message = new String("base mode");
        
        //questi verrano settati dalla view
        Map map = new Map(MapCreator.createA());
        Player currentPlayer = new Player(1,"marco",EnumColorPlayer.BLU);
        
        switch (message){
            case "base mode":
                //gestione per costo base
                
                //public void baseMode(Map map, Player currentPlayer) throws NoTargetInSquare {
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
    
    public void LockRifleweapon(GameModel gameModel, LockRifle weapon){
        
        //chiedi quale dei due effetti vuoi usare
        String message = new String("base mode");
        
        //questi verrano settati dalla view
        Map map = new Map(MapCreator.createA());
        Player currentPlayer = new Player(1,"marco",EnumColorPlayer.BLU);
        Player target1 =new Player(2,"andre",EnumColorPlayer.GREEN);
        Player target2 =new Player(3,"simo",EnumColorPlayer.PINK);
        
        switch (message){
            case "base effect":
                //gestione per costo base
    
                //public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotVisibleTarget {
                try {
                    weapon.baseEffect(map,currentPlayer,target1);
                    weapon.setCharge(false);
                } catch (NotVisibleTarget notVisibleTarget) {
                
                }
                
            case "second Lock Effect":
                //gestione per costo extra
                
                
                // gestire il fatto che target 2 deve essere diverso da target 1
                if(!target1.equals(target2)) {
                    //public void secondLockEffect(Map map, Player currentPlayer, Player target2)throws NotVisibleTarget {
                    try {
        
                        weapon.secondLockEffect(map, currentPlayer, target2);
                        weapon.setCharge(false);
                    } catch (NotVisibleTarget notVisibleTarget) {
        
                        notVisibleTarget.printStackTrace();
                    }
                }
               
                
        }
    }
        
}
    

