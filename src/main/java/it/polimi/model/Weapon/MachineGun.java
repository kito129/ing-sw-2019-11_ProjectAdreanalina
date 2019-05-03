package it.polimi.model.Weapon;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.Exception.InvalidActionForThisCard;
import it.polimi.model.Map;
import it.polimi.model.Player;
import it.polimi.model.WeaponCard;

import java.util.ArrayList;

public class MachineGun extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> focusShotCost;
    private ArrayList<EnumColorCardAndAmmo> turretTripodCost;

    /**
     * Instantiates a new Machine Gun card.
     * Creates the list of recharge cost settings its value to BLU,RED.
     * Sets the field color to BLU.
     * Creates the list of focus shot cost(cost of optional effect1) settings it to yellow.
     * Creates the list of turret tripod cost(cost of optional effect2) settings it to yellow.
     */
    public MachineGun(){

        super("MACHINE GUN", EnumColorCardAndAmmo.BLU);
        rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        focusShotCost=new ArrayList<EnumColorCardAndAmmo>();
        focusShotCost.add(EnumColorCardAndAmmo.YELLOW);
        turretTripodCost=new ArrayList<EnumColorCardAndAmmo>();
        turretTripodCost.add(EnumColorCardAndAmmo.BLU);
    }

    public ArrayList<EnumColorCardAndAmmo> getFocusShotCost() {

        return focusShotCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getTurretTripodCost() {

        return turretTripodCost;
    }

    private void baseEffect(Map map,Player currentPlayer,Player baseTarget1,Player baseTarget2)throws InvalidActionForThisCard {

        if (baseTarget2 == null) {

            if (map.isVisible(currentPlayer, baseTarget1)) {

                baseTarget1.singleDamage(currentPlayer.getColor());
            } else {

                throw new InvalidActionForThisCard();
            }
        } else {

            if ((map.isVisible(currentPlayer, baseTarget1)) && (map.isVisible(currentPlayer, baseTarget2)) && (baseTarget1 != baseTarget2)) {

                baseTarget1.singleDamage(currentPlayer.getColor());
                baseTarget2.singleDamage(currentPlayer.getColor());
            } else {

                throw new InvalidActionForThisCard();
            }
        }
    }

    private void focusShot(Player currentPlayer,Player targetOfFocus){

        targetOfFocus.singleDamage(currentPlayer.getColor());
    }

    private void turretTripod(Player currentPlayer,Player targetOfTurret1,Player targetofTurret2){

    }

    // todo capire dove mettere questa macro funzione , messa qui ha bisogno di troppi parametri, stesso discorso per la lockrifle.
    public void effect(Map map,Player currentPlayer,Player baseTarget1,Player baseTarget2,boolean useFocusShot,boolean useTurretTripod){




    }
}
