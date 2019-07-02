package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotValidInput;

import java.util.ArrayList;

public class Sledgehammer extends WeaponCard {
    
    private ArrayList<EnumColorCardAndAmmo> pulverizeModeCost;
    
    /**
     * Instantiates a new Sladgehammer card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW.
     * Creates the list of effects setting its value to BaseMode,PulverizeMode.
     * Creates the list of pulverize mode cost (cost of alternative fire mode) settings it to RED.
     */
    public Sledgehammer () {
    
        super("SLADGEHAMMER", EnumColorCardAndAmmo.YELLOW);
        ArrayList<EnumColorCardAndAmmo> rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponsEffects = new ArrayList<>();
        weaponsEffects.add(WeaponsEffect.BaseMode);
        weaponsEffects.add(WeaponsEffect.PulverizeMode);
        setWeaponEffects(weaponsEffects);
        pulverizeModeCost = new ArrayList<EnumColorCardAndAmmo>();
        pulverizeModeCost.add(EnumColorCardAndAmmo.RED);
        setOptional(false);
        setDescription("modalità base: Dai 2 danni a 1 bersaglio nel quadrato in cui ti trovi.\n\n" +
                "modalità polverizzare: Dai 3 danni a 1 bersaglio nel quadrato in cui ti trovi, poi muovi quel bersaglio di 0, 1 o 2 quadrati in una direzione.\n\n" +
                "Nota: Ricordati che i movimenti passano attraverso le porte ma non attraverso le pareti.");
    }
    
    public ArrayList<EnumColorCardAndAmmo> getPulverizeModeCost () {
        
        return pulverizeModeCost;
    }
    
    public void baseMode (Map map, Player currentPlayer, Player target1) throws NotValidDistance {
        
        if (map.distance(currentPlayer, target1) == 0) {
            
            ArrayList<EnumColorPlayer> sledgehammerDamages = new ArrayList<>();
            sledgehammerDamages.add(currentPlayer.getColor());
            sledgehammerDamages.add(currentPlayer.getColor());
            target1.multipleDamages(sledgehammerDamages);
        } else {
            
            throw new NotValidDistance();
        }
    }
    
    public void pulverizeMode (Map map, Player currentPlayer, Player target1, Square destSquare) throws NotValidDistance, NotInSameDirection, MapException {
        
        Square target1Square = map.findPlayer(target1);
        
        if ((map.distance(currentPlayer, target1) == 0) && (map.distance(target1Square, destSquare) < 3) && (map.sameDirection(target1Square, destSquare))) {
            
            ArrayList<EnumColorPlayer> sledgehammerDamages = new ArrayList<>();
            sledgehammerDamages.add(currentPlayer.getColor());
            sledgehammerDamages.add(currentPlayer.getColor());
            sledgehammerDamages.add(currentPlayer.getColor());
            target1.multipleDamages(sledgehammerDamages);
            map.movePlayer(target1, destSquare);
        } else if (!(map.sameDirection(target1Square, destSquare))) {
            
            throw new NotInSameDirection();
        } else if ((map.distance(currentPlayer, target1) != 0) || (map.distance(target1Square, destSquare) >= 3)) {
            
            throw new NotValidDistance();
            
        }
    }
}

