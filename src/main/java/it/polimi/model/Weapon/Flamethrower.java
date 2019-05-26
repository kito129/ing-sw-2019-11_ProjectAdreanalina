package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NoTargetInSquare;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;

import java.util.ArrayList;


public class Flamethrower extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> barbecueModeCost;

    /**
     * Instantiates a new Flame thrower card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to RED.
     * Creates the list of effects setting its value to BaseMode,BarbecueMode.
     * Creates the list of barbecue mode cost (cost of alternative fire mode) settings it to YELLOW,YELLOW.
     */
    //todo inserire tutti gli effetti
    public Flamethrower() {

        super("FLAME THROWER", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects=new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.BarbecueMode);
        setWeaponEffects(weaponEffects);
        barbecueModeCost = new ArrayList<EnumColorCardAndAmmo>();
        barbecueModeCost.add(EnumColorCardAndAmmo.YELLOW);
        barbecueModeCost.add(EnumColorCardAndAmmo.YELLOW);
        setDescription("modalità base: Scegli un quadrato distante 1 movimento e possibilmente un secondo quadrato distante ancora 1 movimento nella stessa direzione.\n" +
                "In ogni quadrato puoi scegliere 1 bersaglio e dargli 1 danno.\n\n" +
                "modalità barbecue: Scegli 2 quadrati come prima. Dai 2 danni a chiunque sia nel primo quadrato e 1 danno a chiunque si trovi nel secondo quadrato.\n\n" +
                "Nota: Quest'arma non può danneggiare nessuno nel tuo quadrato.\n" +
                "Tuttavia può a volte danneggiare un bersaglio che non vedi, le fiamme non passano attraverso i muri ma passano attraverso le porte.\n" +
                "Immaginatelo come una esplosione di fuoco in linea retta che si muove di 2 quadrati in una direzione cardinale.");
    }

    public ArrayList<EnumColorCardAndAmmo> getBarbecueModeCost() {

        return barbecueModeCost;
    }


    //TODO da rifare

    /*

    public void baseMode(Map map, Player currentPlayer,Player target1,Player target2) throws NotValidDistance, NotInSameDirection {

        if ((map.distance(currentPlayer, target1) == 1) && (map.sameDirection(currentPlayer, target1))
                && (map.distance(target1, target2) == 1) && (map.sameDirection(currentPlayer, target2))) {

            target1.singleDamage(currentPlayer.getColor());
            target2.singleDamage(currentPlayer.getColor());
        } else if ((!(map.distance(currentPlayer, target1) == 1)) || (!(map.distance(target1, target2) == 1))){

            throw new NotValidDistance();
        } else if ((!(map.sameDirection(currentPlayer,target1))) || (!(map.sameDirection(target1,target2)))){

            throw new NotInSameDirection();
        }
    }

    public void baseMode(Map map,Player currentPlayer,Player target1)throws NotValidDistance{

        if ((map.distance(currentPlayer, target1) == 1)) {

            target1.singleDamage(currentPlayer.getColor());
        } else if (!(map.distance(currentPlayer, target1) == 1)) {

            throw new NotValidDistance();
        }
    }

    public void barbecueMode(Map map,Player currentPlayer,Square targetSquare1,Square targetSquare2) {

        Square currentPlayerSquare = map.findPlayer(currentPlayer);
        if((map.distance(currentPlayerSquare,targetSquare1) == 1) && (map.sameDirection(currentPlayerSquare,targetSquare1))
                && (map.distance(targetSquare1,targetSquare2) == 1) && (map.sameDirection(currentPlayerSquare,targetSquare2))){


        }

    }


     */
}



