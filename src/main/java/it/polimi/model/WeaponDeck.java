package it.polimi.model;

import it.polimi.model.Exception.NoAvaibleCard;
import it.polimi.model.Weapon.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Weapon deck.
 */
public class WeaponDeck {

    private ArrayList<WeaponCard> weaponCards;

    public WeaponDeck(){

        WeaponCard lockRifle= new LockRifle();
        WeaponCard electroscythe= new Electroscythe();
        WeaponCard machineGun =new MachineGun();
        WeaponCard tractorBeam=new TractorBeam();
        WeaponCard thor=new Thor();
        WeaponCard plasmaGun=new PlasmaGun();
        WeaponCard whisper=new Whisper();
        WeaponCard vortexCannon=new VortexCannon();
        WeaponCard furnace=new Furnace();
        WeaponCard heatSeeker=new Heatseeker();
        WeaponCard hellion=new Hellion();
        WeaponCard flameThrower=new Flamethrower();
        WeaponCard grenadeLauncher= new GrenadeLauncher();
        WeaponCard rocketLauncher=new RocketLauncher();
        WeaponCard railgun=new Railgun();
        WeaponCard cyberblade= new Cyberblade();
        WeaponCard zx2=new Zx2();
        WeaponCard shotgun=new Shotgun();
        WeaponCard powerGlove=new PowerGlove();
        WeaponCard shockwave=new Shockwave();
        WeaponCard sledgehammer= new Sledgehammer();
        weaponCards = new ArrayList<>();
        weaponCards.add(lockRifle);
        weaponCards.add(electroscythe);
        weaponCards.add(machineGun);
        weaponCards.add(tractorBeam);
        weaponCards.add(thor);
        weaponCards.add(plasmaGun);
        weaponCards.add(whisper);
        weaponCards.add(vortexCannon);
        weaponCards.add(furnace);
        weaponCards.add(heatSeeker);
        weaponCards.add(hellion);
        weaponCards.add(flameThrower);
        weaponCards.add(grenadeLauncher);
        weaponCards.add(rocketLauncher);
        weaponCards.add(railgun);
        weaponCards.add(cyberblade);
        weaponCards.add(zx2);
        weaponCards.add(shotgun);
        weaponCards.add(powerGlove);
        weaponCards.add(shockwave);
        weaponCards.add(sledgehammer);
        Collections.shuffle(weaponCards);

    }

    public WeaponCard drawWeaponCard() throws NoAvaibleCard {

        if(weaponCards.size()!=0){

            WeaponCard firstCard=weaponCards.get(0);
            weaponCards.remove(0);
            return firstCard;
        }else {

            throw new NoAvaibleCard();
        }
    }


    

}
