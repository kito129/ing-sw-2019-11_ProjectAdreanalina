package it.polimi.model.Weapon;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NOTATIONDatatypeValidator;
import it.polimi.model.*;
import it.polimi.model.Exception.NoTargetInSquare;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class Furnace extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> cozyFireModeCost;

    /**
     * Instantiates a new Furnace card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to RED,BLU.
     * Creates the list of cozy dire mode cost (cost of alternative fire mode) settings it to null.
     */
    public Furnace() {

        super("FURNACE", EnumColorCardAndAmmo.RED);
        rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        cozyFireModeCost = new ArrayList<EnumColorCardAndAmmo>();
        cozyFireModeCost.add(null);
    }

    public ArrayList<EnumColorCardAndAmmo> getCozyFireModeCost() {

        return cozyFireModeCost;
    }

    public void baseMode(Map map, Player currentPlayer,EnumColorSquare targetRoomColor) throws NotVisibleTarget, NotValidDistance {

        Square currentPlayerSquare = map.findPlayer(currentPlayer);
        ArrayList<Player> playersInTargetRoom = map.playerInRoom(targetRoomColor);


        if ((map.isVisibleRoom(currentPlayer, targetRoomColor) && (targetRoomColor != currentPlayerSquare.getColor()))) {

            for (Player p : playersInTargetRoom) {

                p.singleDamage(currentPlayer.getColor());
            }
        }else if (!(map.isVisibleRoom(currentPlayer,targetRoomColor))){

            throw new  NotVisibleTarget();
        }else if (targetRoomColor == currentPlayerSquare.getColor()){

            throw new NotValidDistance();
        }
    }


    //todo ripartire da qui



    public void cozyFireMode(Map map,Player currentPlayer) throws NoTargetInSquare {


    }


}
