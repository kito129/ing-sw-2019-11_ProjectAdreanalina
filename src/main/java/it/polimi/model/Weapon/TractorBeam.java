package it.polimi.model.Weapon;

import it.polimi.model.*;

import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

/**
 * The type Tractor beam.
 */
public class TractorBeam extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> punisherModeCost;
    
    /**
     * Instantiates a new Tractor Beam card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to BLU.
     * Creates the list of effects setting its value to BaseMode,PunisherMode.
     * Creates the list of punisher mode cost (cost of alternative fire mode) settings it to RED,YELLOW.
     */
    public TractorBeam() {

        super("TRACTOR BEAM", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.PunisherMode);
        setWeaponEffects(weaponEffects);
        punisherModeCost = new ArrayList<EnumColorCardAndAmmo>();
        punisherModeCost.add(EnumColorCardAndAmmo.RED);
        punisherModeCost.add(EnumColorCardAndAmmo.YELLOW);
        setOptional(false);
        setDescription("Basic Mode: Move a target 0, 1, or 2 squares to a square you can see, and give it 1 damage.\n" +
                "in Punisher Mode: Choose a target 0, 1, or 2 moves away from you. Move the target to your square and deal 3 damage to it.\n" +
                "Notes: You can move a target even if you can't see it. The target ends up in a place where you can see and damage it. The moves do not have to be in the same direction.");
    }
    
    /**
     * get punisherModeCost
     *
     * @return the punisher mode cost
     */
    public ArrayList<EnumColorCardAndAmmo> getPunisherModeCost() {

        return punisherModeCost;
    }
    
    /**
     * Base mode.
     *
     * @param map           the map
     * @param destSquare    the dest square
     * @param currentPlayer the current player
     * @param target1       the target 1
     * @throws NotVisibleTarget the not visible target
     * @throws NotValidDistance the not valid distance
     * @throws MapException     the map exception
     */
    public void baseMode(Map map,Square destSquare,Player currentPlayer,Player target1) throws NotVisibleTarget, NotValidDistance,MapException{

        Square currentPlayerSquare = map.findPlayer(currentPlayer);
        Square target1Square = map.findPlayer(target1);
        if((map.isVisible(currentPlayerSquare,destSquare)) && (map.distance(target1Square,destSquare)<3)
                &&(map.distance(target1Square,destSquare)>=0)){

            map.movePlayer(target1,destSquare);
            target1.singleDamage(currentPlayer.getColor());
        }else if(!map.isVisible(currentPlayerSquare,destSquare)){

            throw new NotVisibleTarget();
        }else if((map.distance(target1Square,destSquare)>2)&&(map.distance(target1Square,destSquare)==-1)){

            throw new NotValidDistance();
        }
    }
    
    /**
     * Punisher mode.
     *
     * @param map           the map
     * @param currentPlayer the current player
     * @param target1       the target 1
     * @throws NotValidDistance the not valid distance
     * @throws MapException     the map exception
     */
    public void punisherMode(Map map,Player currentPlayer,Player target1) throws NotValidDistance, MapException {

        if((map.distance(currentPlayer,target1)<3)&&(map.distance(currentPlayer,target1)!=-1)){

            Square currentPlayerSquare = map.findPlayer(currentPlayer);
            map.movePlayer(target1,currentPlayerSquare);
            ArrayList<EnumColorPlayer> tractorBeamDamages=new ArrayList<EnumColorPlayer>();
            tractorBeamDamages.add(currentPlayer.getColor());
            tractorBeamDamages.add(currentPlayer.getColor());
            tractorBeamDamages.add(currentPlayer.getColor());
            target1.multipleDamages(tractorBeamDamages);
        }else {

            throw new NotValidDistance();
        }





    }


}




