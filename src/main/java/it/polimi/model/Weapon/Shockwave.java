package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;

import java.util.ArrayList;

public class Shockwave extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> tsunamiModeCost;

    /**
     * Instantiates a new Shockwave card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW.
     * Creates the list of effects setting its value to BaseMode,TsunamiMode.
     * Creates the list of tsunami mode cost (cost of alternative fire mode) settings it to YELLOW.
     */
    public Shockwave() {

        super("SHOCKWAVE", EnumColorCardAndAmmo.YELLOW);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponsEffects= new ArrayList<>();
        weaponsEffects.add(WeaponsEffect.BaseMode);
        weaponsEffects.add(WeaponsEffect.TsunamiMode);
        setWeaponEffects(weaponsEffects);
        tsunamiModeCost = new ArrayList<EnumColorCardAndAmmo>();
        tsunamiModeCost.add(EnumColorCardAndAmmo.YELLOW);
    }

    public ArrayList<EnumColorCardAndAmmo> getTsunamiModeCost() {

        return tsunamiModeCost;
    }

    //todo potrei fare una sola funzione che prende un arraylist di player

    public void baseMode(Map map, Player currentPlayer,Player target1)throws NotValidDistance {

        if(map.distance(currentPlayer,target1)==1){

            target1.singleDamage(currentPlayer.getColor());
        }else {

            throw new NotValidDistance();
        }
    }

    public void baseMode(Map map, Player currentPlayer,Player target1,Player target2)throws NotValidDistance{

        if((map.distance(currentPlayer,target1)==1)&&(map.distance(currentPlayer,target2)==1)&&(map.distance(target1,target2)!=0)){

            target1.singleDamage(currentPlayer.getColor());
            target2.singleDamage(currentPlayer.getColor());
        }else {

            throw new NotValidDistance();
        }
    }

    public void baseMode(Map map, Player currentPlayer,Player target1,Player target2,Player target3)throws NotValidDistance{

        if((map.distance(currentPlayer,target1)==1)&&(map.distance(currentPlayer,target2)==1)&&(map.distance(currentPlayer,target3)==1)
                &&(map.distance(target1,target2)!=0)&&(map.distance(target1,target3)!=0)&&(map.distance(target2,target3)!=0)){

            target1.singleDamage(currentPlayer.getColor());
            target2.singleDamage(currentPlayer.getColor());
            target3.singleDamage(currentPlayer.getColor());
        }else {

            throw new NotValidDistance();
        }
    }

    //todo parametro allPlayers sono tutti i giocatori in gioco
    public void tsunamiMode(Map map,Player currentPlayer,ArrayList<Player> allPlayers) throws NotValidDistance{

        allPlayers.remove(currentPlayer);
        ArrayList<Player> playersAtOne = new ArrayList<>();
        for (Player p : allPlayers) {

            if (map.distance(p, currentPlayer) == 1) {

                playersAtOne.add(p);
                p.singleDamage(currentPlayer.getColor());
            }
        }
        if(playersAtOne.size()==0){

            throw new NotValidDistance();
        }
    }

}
