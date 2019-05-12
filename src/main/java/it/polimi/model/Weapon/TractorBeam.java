package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class TractorBeam extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> punisherModeCost;

    /**
     * Instantiates a new Tractor Beam card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to BLU.
     * Creates the list of punisher mode cost (cost of alternative fire mode) settings it to RED,YELLOW.
     */
    public TractorBeam() {

        super("TRACTOR BEAM", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        punisherModeCost = new ArrayList<EnumColorCardAndAmmo>();
        punisherModeCost.add(EnumColorCardAndAmmo.RED);
        punisherModeCost.add(EnumColorCardAndAmmo.YELLOW);
    }

    public ArrayList<EnumColorCardAndAmmo> getPunisherModeCost() {

        return punisherModeCost;
    }

    public void baseMode(Map map,Square destSquare,Player currentPlayer,Player target1) throws NotVisibleTarget, NotValidDistance{

        Square currentPlayerSquare = map.findPlayer(currentPlayer);
        Square target1Square = map.findPlayer(target1);
        if((map.isVisible(destSquare,currentPlayerSquare)) && (map.distance(target1Square,destSquare)<3)){

            map.movePlayer(target1,destSquare);
            target1.singleDamage(currentPlayer.getColor());
        }else if(map.isVisible(destSquare,currentPlayerSquare)==false){

            throw new NotVisibleTarget();
        }else if(map.distance(target1Square,destSquare)<3){

            throw new NotValidDistance();
        }
    }

    public void punisherMode(Map map,Player currentPlayer,Player target1) throws NotValidDistance{

        Square currentPlayerSquare = map.findPlayer(currentPlayer);
        Square target1Square = map.findPlayer(target1);
        if(map.distance(currentPlayerSquare,target1Square)<3){

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




