package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class RocketLauncher extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> rocketJumpCost;
    private ArrayList<EnumColorCardAndAmmo> fragmentingWarheadCost;


    /**
     * Instantiates a new RocketLauncher card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to RED,RED.
     * Creates the list of effects setting its value to BaseEffect,RocketJumpEffect,FragmentingWarheadEffect.
     * Creates the list of rocket jump cost(cost of optional effect 1) settings it to BLU.
     * Creates the list of fragment warhead cost(cost of optional effect 1) settings it to YELLOW.
     */
    public RocketLauncher(){

        super("ROCKET LAUNCHER", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.RocketJumpEffect);
        weaponEffects.add(WeaponsEffect.FragmentingWarheadEffect);
        setWeaponEffects(weaponEffects);
        rocketJumpCost =new ArrayList<EnumColorCardAndAmmo>();
        rocketJumpCost.add(EnumColorCardAndAmmo.BLU);
        fragmentingWarheadCost = new ArrayList<EnumColorCardAndAmmo>();
        fragmentingWarheadCost.add(EnumColorCardAndAmmo.YELLOW);
    }

    public ArrayList<EnumColorCardAndAmmo> getRocketJumpCost() {

        return rocketJumpCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getFragmentingWarheadCost() {

        return fragmentingWarheadCost;
    }

    public void baseEffect(Map map, Player target1, Player currentPlayer,Square destSquare) throws NotVisibleTarget, NotValidDistance, MapException {

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        Square squareOfTarget1Player = map.findPlayer(target1);
        if((map.isVisible(target1,currentPlayer)) && (!(squareOfCurrentPlayer == squareOfTarget1Player))){

            if((destSquare!=null)&&(map.distance(squareOfTarget1Player,destSquare)==1)){

                map.movePlayer(target1,destSquare);
            }else if((destSquare!=null)&&(!(map.distance(squareOfTarget1Player,destSquare)==1))){

                throw new NotValidDistance();
            }
            ArrayList<EnumColorPlayer> rocketLauncherDamages=new ArrayList<>();
            rocketLauncherDamages.add(currentPlayer.getColor());
            rocketLauncherDamages.add(currentPlayer.getColor());
            target1.multipleDamages(rocketLauncherDamages);
        }else if(!(map.isVisible(target1,currentPlayer))){

            throw new NotVisibleTarget();
        }else if(squareOfCurrentPlayer==squareOfTarget1Player){

            throw new NotValidDistance();
        }
    }

    //todo puo essere usato prima dell'effetto base o dopo
    public void rocketJumpEffect(Map map, Player currentPlayer,Square destSquare) throws NotValidDistance, NotValidInput, MapException {

        Square squareOfCurrentPlayer=map.findPlayer(currentPlayer);

        if((map.distance(squareOfCurrentPlayer,destSquare)==1)||(map.distance(squareOfCurrentPlayer,destSquare)==2)){

            map.movePlayer(currentPlayer,destSquare);
        }else{

            throw new NotValidDistance();
        }
    }

    //todo durante l'effetto base si può usare questo
    // da fare poi
    public void fragmentingWarheadEffect(Map map,Square targetSquare,Player currentPlayer){


    }

}
