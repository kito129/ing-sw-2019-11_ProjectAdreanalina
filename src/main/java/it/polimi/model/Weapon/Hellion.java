package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class Hellion extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> nanoTracerModeCost;

    /**
     * Instantiates a new Hellion card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to RED,YELLOW.
     * Creates the list of nano tracer mode cost (cost of alternative fire mode) settings it to RED.
     */
    public Hellion() {

        super("HELLION", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        setRechargeCost(rechargeCost);
        nanoTracerModeCost = new ArrayList<EnumColorCardAndAmmo>();
        nanoTracerModeCost.add(EnumColorCardAndAmmo.RED);
    }

    public ArrayList<EnumColorCardAndAmmo> getNanoTracerModeCost() {

        return nanoTracerModeCost;
    }

    public void baseMode(Map map, Player currentPlayer,Player targetPlayer) throws NotVisibleTarget,NotValidDistance{

        Square targetSquare = map.findPlayer(targetPlayer);
        ArrayList<Player> playersInTargetSquare;
        playersInTargetSquare=map.playersOnSquare(targetSquare);
        if((map.isVisible(currentPlayer,targetPlayer)) && (map.distance(currentPlayer,targetPlayer)>0)){

            targetPlayer.singleDamage(currentPlayer.getColor());
            for(Player p:playersInTargetSquare){

                p.singleMark(currentPlayer.getColor());
            }
        }else if(!(map.isVisible(currentPlayer,targetPlayer))){

            throw new NotVisibleTarget();
        }else if(map.distance(currentPlayer,targetPlayer)==0){

            throw new NotValidDistance();
        }
    }

    public void nanoTracerMode(Map map, Player currentPlayer,Player targetPlayer) throws NotVisibleTarget,NotValidDistance {

        Square targetSquare = map.findPlayer(targetPlayer);
        ArrayList<Player> playersInTargetSquare;
        playersInTargetSquare=map.playersOnSquare(targetSquare);
        if((map.isVisible(currentPlayer,targetPlayer)) && (map.distance(currentPlayer,targetPlayer)>0)){

            targetPlayer.singleDamage(currentPlayer.getColor());
            ArrayList<EnumColorPlayer> hellionMarks= new ArrayList<>();
            hellionMarks.add(currentPlayer.getColor());
            hellionMarks.add(currentPlayer.getColor());
            for(Player p:playersInTargetSquare){

                p.multipleMarks(hellionMarks);
            }
        }else if(!(map.isVisible(currentPlayer,targetPlayer))){

            throw new NotVisibleTarget();
        }else if(map.distance(currentPlayer,targetPlayer)==0){

            throw new NotValidDistance();
        }
    }






}




