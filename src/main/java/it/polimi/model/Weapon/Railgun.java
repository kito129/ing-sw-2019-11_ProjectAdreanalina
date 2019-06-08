package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotInDirection;
import it.polimi.model.Exception.NotValidCardinalDirection;

import java.util.ArrayList;

public class Railgun extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> piercingModeCost;

    /**
     * Instantiates a new Railgun card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW,YELLOW,BLU.
     * Creates the list of effects setting its value to BaseMode,PiercingMode
     * Creates the list of piercing mode cost (cost of alternative fire mode) settings it to null.
     */
    public Railgun() {

        super("RAILGUN", EnumColorCardAndAmmo.YELLOW);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects = new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.PiercingMode);
        setWeaponEffects(weaponEffects);
        piercingModeCost = new ArrayList<EnumColorCardAndAmmo>();
        piercingModeCost.add(null);
        setDescription("modalità base : Scegli una direzione cardinale e 1 bersaglio in quella direzione.\n" +
                "Dagli 3 danni.\n\n" +
                "modalità perforazione: Scegli una direzione cardinale e 1 o 2 bersagli in quella direzione.\n" +
                "Dai 2 danni a ciascuno.\n\n" +
                "Nota: In pratica spari in linea retta ignorando i muri.\n" +
                "Non hai bisogno di scegliere un bersaglio dall'altro lato del muro, potrebbe anche essere qualcuno nel tuo stesso quadrato, ma sparare attraverso i muri è sicuramente divertente.\n" +
                "Ci sono solo 4 direzioni cardinali.\n" +
                "Immagina di essere di fronte a un muro o una porta, imbracciare il fucile e sparare in quella direzione.\n" +
                "Chiunque si trovi su un quadrato in quella direzione (incluso il tuo) è un bersaglio valido.\n" +
                "In modalità perforazione i 2 bersagli possono essere nello stesso quadrato o in quadrati diversi.");
    }

    public ArrayList<EnumColorCardAndAmmo> getPiercingModeCost() {

        return piercingModeCost;
    }


    public void baseMode(Map map, Player currentPlayer, Player target1, EnumCardinalDirection direction) throws NotInDirection, NotValidCardinalDirection {

        if((direction==EnumCardinalDirection.N)||(direction==EnumCardinalDirection.E)||(direction==EnumCardinalDirection.S)
                ||(direction==EnumCardinalDirection.W)){

            if (direction==EnumCardinalDirection.N) {

                ArrayList<Player> playersOnNorth = map.playerOnMyNorth(currentPlayer);
                if (!(playersOnNorth.contains(target1))) {

                    throw new NotInDirection();
                }
            }
            if (direction==EnumCardinalDirection.E) {

                ArrayList<Player> playersOnEst = map.playerOnMyEst(currentPlayer);
                if (!(playersOnEst.contains(target1))) {

                    throw new NotInDirection();
                }
            }
            if (direction==EnumCardinalDirection.S) {

                ArrayList<Player> playersOnSouth = map.playerOnMySouth(currentPlayer);
                if (!(playersOnSouth.contains(target1))) {

                    throw new NotInDirection();
                }
            }
            if (direction==EnumCardinalDirection.W) {

                ArrayList<Player> playersOnWest = map.playerOnMyWest(currentPlayer);
                if (!(playersOnWest.contains(target1))) {

                    throw new NotInDirection();
                }
            }

            ArrayList<EnumColorPlayer> railGunDamages = new ArrayList<>();
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            target1.multipleDamages(railGunDamages);
        }else{

            throw new NotValidCardinalDirection();
        }
    }

    public void piercingMode(Map map,Player currentPlayer,ArrayList<Player> targets,EnumCardinalDirection direction)throws NotValidCardinalDirection,NotInDirection{

        if((direction==EnumCardinalDirection.N)||(direction==EnumCardinalDirection.E)||(direction==EnumCardinalDirection.S)
                ||(direction==EnumCardinalDirection.W)){

            if (direction==EnumCardinalDirection.N) {

                ArrayList<Player> playersOnNorth = map.playerOnMyNorth(currentPlayer);
                for(Player p:targets) {

                    if (!(playersOnNorth.contains(p))) {

                        throw new NotInDirection();
                    }
                }
            }
            if (direction==EnumCardinalDirection.E) {

                ArrayList<Player> playersOnEst = map.playerOnMyEst(currentPlayer);
                for(Player p:targets) {

                    if (!(playersOnEst.contains(p))) {

                        throw new NotInDirection();
                    }
                }
            }
            if (direction==EnumCardinalDirection.S) {

                ArrayList<Player> playersOnSouth = map.playerOnMySouth(currentPlayer);
                for(Player p:targets) {

                    if (!(playersOnSouth.contains(p))) {

                        throw new NotInDirection();
                    }
                }
            }
            if (direction==EnumCardinalDirection.W) {

                ArrayList<Player> playersOnWest = map.playerOnMyWest(currentPlayer);
                for(Player p:targets) {

                    if (!(playersOnWest.contains(p))) {

                        throw new NotInDirection();
                    }
                }
            }
            ArrayList<EnumColorPlayer> railGunDamages = new ArrayList<>();
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            for(Player p:targets){

                p.multipleDamages(railGunDamages);
            }
        }else{

            throw new NotValidCardinalDirection();
        }
    }



}
/*non serve più
 public void piercingMode(Map map,Player currentPlayer,Player target1,Player target2,String direction)throws NotValidCardinalDirection,NotInDirection{

        if((direction.equals("NORTH"))||(direction.equals("EST"))||(direction.equals("SOUTH"))||(direction.equals("WEST"))){

            if (direction.equals("NORTH")) {

                ArrayList<Player> playersOnNorth = map.playerOnMyNorth(currentPlayer);
                if ((!(playersOnNorth.contains(target1))) && (!(playersOnNorth.contains(target2)))){

                    throw new NotInDirection();
                }
            }
            if (direction.equals("EST")) {

                ArrayList<Player> playersOnEst = map.playerOnMyEst(currentPlayer);
                if ((!(playersOnEst.contains(target1))) && (!(playersOnEst.contains(target2)))) {

                    throw new NotInDirection();
                }
            }
            if (direction.equals("SOUTH")) {

                ArrayList<Player> playersOnSouth = map.playerOnMySouth(currentPlayer);
                if ((!(playersOnSouth.contains(target1))) && (!(playersOnSouth.contains(target2)))) {

                    throw new NotInDirection();
                }
            }
            if (direction.equals("WEST")) {

                ArrayList<Player> playersOnWest = map.playerOnMyWest(currentPlayer);
                if ((!(playersOnWest.contains(target1))) && (!(playersOnWest.contains(target2)))) {

                    throw new NotInDirection();
                }
            }
            ArrayList<EnumColorPlayer> railGunDamages = new ArrayList<>();
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            target1.multipleDamages(railGunDamages);
            target2.multipleDamages(railGunDamages);
        }else{

            throw new NotValidCardinalDirection();
        }
    }

 */
