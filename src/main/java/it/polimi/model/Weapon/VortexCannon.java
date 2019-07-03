package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;


public class VortexCannon extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> blackHoleCost;

    /**
     * Instantiates a new Vortex Cannon card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to RED,BLU.
     * Creates the list of effects setting its value to BaseEffect,BlackHoleEffect.
     * Creates the list of black hole cost(cost of optional effect 1) settings it to RED.
     */
    public VortexCannon(){

        super("VORTEX CANNON", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects=new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.BlackHoleEffect);
        setWeaponEffects(weaponEffects);
        blackHoleCost =new ArrayList<EnumColorCardAndAmmo>();
        blackHoleCost.add(EnumColorCardAndAmmo.RED);
        setOptional(true);
        setDescription("Basic Effect: Choose a square you can see, but not your square. Call it ''the vortex''. Choose a target on the vortex or 1 move away from it. Move it onto the vortex and give it 2 damage.\n" +
                "with Black Hole: Choose up to 2 other targets on the vortex or 1 move away from it. Move them onto the vortex and give them each 1 damage.\n" +
                "Notes: The 3 targets must be different, but some might start on the same square. It is legal to choose targets on your square, on the vortex, or even on squares you can't see. They all end up on the vortex.");
    }

    /**
     * get blackHoleCost
     */
    public ArrayList<EnumColorCardAndAmmo> getBlackHoleCost() {

        return blackHoleCost;
    }

    public void baseEffect(Map map,Square vortexSquare,Player currentPlayer,Player target1) throws NotVisibleTarget, NotValidDistance,MapException {

        Square currentPlayerSquare= map.findPlayer(currentPlayer);
        if((map.isVisible(currentPlayerSquare,vortexSquare))&&(map.distance(vortexSquare,currentPlayerSquare)>0)){

            Square target1Square=map.findPlayer(target1);
            if(target1Square==vortexSquare){

                ArrayList<EnumColorPlayer> vortexCannonDamages=new ArrayList<>();
                vortexCannonDamages.add(currentPlayer.getColor());
                vortexCannonDamages.add(currentPlayer.getColor());
                target1.multipleDamages(vortexCannonDamages);
            }else if(map.distance(target1Square,vortexSquare)==1){

                map.movePlayer(target1,vortexSquare);
                ArrayList<EnumColorPlayer> vortexCannonDamages= new ArrayList<>();
                vortexCannonDamages.add(currentPlayer.getColor());
                vortexCannonDamages.add(currentPlayer.getColor());
                target1.multipleDamages(vortexCannonDamages);
            }else{

                throw new NotValidDistance();
            }
        }else if(!(map.isVisible(currentPlayerSquare,vortexSquare))){

            throw new NotVisibleTarget();
        }else if(!(map.distance(currentPlayerSquare,vortexSquare)>0)){

            throw new NotValidDistance();
        }
    }

    public void blackHoleEffect(Map map,Square vortexSquare,Player currentPlayer,ArrayList<Player> targetBlackHole) throws NotValidDistance,MapException {

        Square targetSquare;

        for(Player p:targetBlackHole){

            targetSquare=map.findPlayer(p);

            if((map.distance(targetSquare,vortexSquare)>1) || (map.distance(targetSquare,vortexSquare)==-1)){

                throw new NotValidDistance();
            }
        }

        for(Player p:targetBlackHole){

            targetSquare=map.findPlayer(p);
            if(targetSquare==vortexSquare){

                p.singleDamage(currentPlayer.getColor());
            }else if(map.distance(targetSquare,vortexSquare)==1){

                map.movePlayer(p,vortexSquare);
                p.singleDamage(currentPlayer.getColor());
            }
        }
        //todo impossibile vedere se lo spostamento dei playere vadano tutti a buon termine.
    }
}




/*    // metodo rifatto su
    //  vedere magari con metodi overload
    // fargli scegliere il target tra le possibili azioni ... metodo potrebbe essere da rifare
    //  metodo da ricontrollare e rifare chiede secondo me dei controlli lato controller .
    public void baseEffect1(Map map, Square vortexSquare,Player currentPlayer,Player target1) throws NotVisibleTarget, NotValidDistance{

        Square currentPlayerSquare = map.findPlayer(currentPlayer);
        Square targetSquare=map.findPlayer(target1);
        if((map.isVisible(vortexSquare, currentPlayerSquare)) && (map.distance(vortexSquare, currentPlayerSquare) > 0)) {

            if((targetSquare==vortexSquare)){

                ArrayList<EnumColorPlayer> vortexCannonDamages= new ArrayList<>();
                vortexCannonDamages.add(currentPlayer.getColor());
                vortexCannonDamages.add(currentPlayer.getColor());
                target1.multipleDamages(vortexCannonDamages);

            }
            if(map.distance(vortexSquare,targetSquare)==1) {

                map.movePlayer(target1, vortexSquare);
                ArrayList<EnumColorPlayer> vortexCannonDamages = new ArrayList<>();
                vortexCannonDamages.add(currentPlayer.getColor());
                vortexCannonDamages.add(currentPlayer.getColor());
                target1.multipleDamages(vortexCannonDamages);
            }else{

                throw new NotValidDistance();
            }
        } else if (!(map.isVisible(vortexSquare, currentPlayerSquare))) {

            throw new NotVisibleTarget();
        } else if (map.distance(vortexSquare, currentPlayerSquare) == 0) {

            throw new NotValidDistance();
        }
    }

 */