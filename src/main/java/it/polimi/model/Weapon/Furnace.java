package it.polimi.model.Weapon;


import it.polimi.model.*;
import it.polimi.model.Exception.*;

import java.util.ArrayList;

public class Furnace extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> cozyFireModeCost;

    /**
     * Instantiates a new Furnace card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to RED,BLU.
     * Creates the list of effects setting its value to BaseMode,CozyFireMode.
     * Creates the list of cozy fire mode cost (cost of alternative fire mode) settings it to null.
     */
    public Furnace() {

        super("FURNACE", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects=new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.CozyFireMode);
        setWeaponEffects(weaponEffects);
        cozyFireModeCost = new ArrayList<EnumColorCardAndAmmo>();
        cozyFireModeCost.add(null);
        setOptional(false);
        setDescription("Basic Mode: Choose a room you can see, but not the room you are in. Deal 1 damage to everyone in that room.\n" +
                "in Cozy Fire Mode: Choose a square exactly one move away. Deal 1 damage and 1 mark to everyone on that square.");
    }

    public ArrayList<EnumColorCardAndAmmo> getCozyFireModeCost() {

        return cozyFireModeCost;
    }


    public void baseMode(Map map, Player currentPlayer,EnumColorSquare targetRoomColor) throws NotVisibleTarget, NotValidDistance, MapException ,NoTargetInSquare{

        Square currentPlayerSquare= map.findPlayer(currentPlayer);
        if((map.isVisibleRoom(currentPlayer,targetRoomColor))&& (currentPlayerSquare.getColor()!=targetRoomColor)){

            ArrayList<Player> targetPlayers=map.playerInRoom(targetRoomColor);
            if(targetPlayers.size()!=0) {
                for (Player p : targetPlayers) {

                    p.singleDamage(currentPlayer.getColor());
                }
            }else{

                throw new NoTargetInSquare();
            }
        }else if(!(map.isVisibleRoom(currentPlayer,targetRoomColor))){

            throw new NotVisibleTarget();
        }else if(currentPlayerSquare.getColor()==targetRoomColor){

            throw new NotValidDistance();
        }
    }

    public void cozyFireMode(Map map,Player currentPlayer,Square targetSquare) throws NotValidDistance,MapException, NoTargetInSquare {

        Square currentPlayerSquare = map.findPlayer(currentPlayer);
        if(map.distance(currentPlayerSquare,targetSquare)==1){

            ArrayList<Player> playersInTargetSquare;
            playersInTargetSquare= new ArrayList<>(map.playersOnSquare(targetSquare));
            if(playersInTargetSquare.size()!=0) {

                for (Player p : playersInTargetSquare) {

                    p.singleDamage(currentPlayer.getColor());
                    p.singleMark(currentPlayer.getColor());
                }
            }else {
                throw new NoTargetInSquare();
            }
        }else{

            throw new NotValidDistance();
        }
    }


}


// metodo rifatto non dovrebe servire piu.
/* public void baseMode1(Map map, Player currentPlayer,EnumColorSquare targetRoomColor) throws NotVisibleTarget, NotValidDistance {

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


 */
