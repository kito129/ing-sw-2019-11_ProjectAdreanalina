package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;


public class VortexCannon extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> blackHoleCost;

    /**
     * Instantiates a new Vortex Cannon card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to RED,BLU.
     * Creates the list of black hole cost(cost of optional effect 1) settings it to RED.
     */
    public VortexCannon(){

        super("VORTEX CANNON", EnumColorCardAndAmmo.RED);
        rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        blackHoleCost =new ArrayList<EnumColorCardAndAmmo>();
        blackHoleCost.add(EnumColorCardAndAmmo.RED);
    }

    public ArrayList<EnumColorCardAndAmmo> getBlackHoleCost() {

        return blackHoleCost;
    }
    // todo vedere magari con metodi overload
    //todo fargli scegliere il target tra le possibili azioni ... metodo potrebbe essere da rifare
    // todo metodo da ricontrollare e rifare chiede secondo me dei controlli lato controller .
    public void baseEffect(Map map, Square vortexSquare,Player currentPlayer,Player target1) throws NotVisibleTarget, NotValidDistance{

        Square currentPlayerSquare = map.findPlayer(currentPlayer);
        Square targetSquare=map.findPlayer(target1);
        if((map.isVisible(vortexSquare, currentPlayerSquare)) && (map.distance(vortexSquare, currentPlayerSquare) > 0)) {

            if((targetSquare==vortexSquare)){

                ArrayList<EnumColorPlayer> vortexCannonDamages= new ArrayList<>();
                vortexCannonDamages.add(currentPlayer.getColor());
                vortexCannonDamages.add(currentPlayer.getColor());
                target1.multipleDamage(vortexCannonDamages);

            }
            if(map.distance(vortexSquare,targetSquare)==1) {

                map.movePlayer(target1, vortexSquare);
                ArrayList<EnumColorPlayer> vortexCannonDamages = new ArrayList<>();
                vortexCannonDamages.add(currentPlayer.getColor());
                vortexCannonDamages.add(currentPlayer.getColor());
                target1.multipleDamage(vortexCannonDamages);
            }else{

                throw new NotValidDistance();
            }
        } else if (!(map.isVisible(vortexSquare, currentPlayerSquare))) {

            throw new NotVisibleTarget();
        } else if (map.distance(vortexSquare, currentPlayerSquare) == 0) {

            throw new NotValidDistance();
        }
    }




    //todo da vedere
    public void blackHoleEffect(Map map, Player currentPlayer, Player target2)throws NotVisibleTarget {



    }


}