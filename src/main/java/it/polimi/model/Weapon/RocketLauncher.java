package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class RocketLauncher extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> rocketJumpCost;
    private ArrayList<EnumColorCardAndAmmo> fragmentingWarheadCost;


    /**
     * Instantiates a new RocketLauncher card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to RED,RED.
     * Creates the list of effects setting its value to BaseEffect,RocketJumpEffect,FragmentingWarheadEffect.
     * Creates the list of rocket jump cost(cost of optional effect 1) settings it to BLU.
     * Creates the list of fragment warhead cost(cost of optional effect 1) settings it to YELLOW.
     */
    public RocketLauncher(){

        super("ROCKET LAUNCHER", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffectPlusFragmentingWarheadEffect);
        weaponEffects.add(WeaponsEffect.RocketJumpEffect);
        setWeaponEffects(weaponEffects);
        rocketJumpCost =new ArrayList<EnumColorCardAndAmmo>();
        rocketJumpCost.add(EnumColorCardAndAmmo.BLU);
        fragmentingWarheadCost = new ArrayList<EnumColorCardAndAmmo>();
        fragmentingWarheadCost.add(EnumColorCardAndAmmo.YELLOW);
        setOptional(true);
        setDescription("effetto base: Dai 2 danni a 1 bersaglio che puoi vedere e che non si trova nel tuo quadrato.\n" +
                "Poi puoi muovere il bersaglio di 1 quadrato.\n\n" +
                "razzi portatili: Muovi di 1 o 2 quadrati. Questo effetto può essere usato prima o dopo l'effetto base.\n" +
                "testata a frammentazione: Durante l'effetto base, dai 1 danno a ogni giocatore presente nel quadrato in cui si trovava originariamente il bersaglio, incluso il bersaglio, anche se lo hai mosso.\n\n" +
                "Nota: Se usi i razzi portatili prima dell'effetto base considera solo il tuo nuovo quadrato per determinare se il bersaglio è valido.\n" +
                "Puoi anche spostarti di 1 quadrato per potergli sparare.\n" +
                "Usando la testata a frammentazione dai danno a chiunque si trovi nel quadrato in cui il bersaglio si trovava prima di muoversi.\n" +
                "Il bersaglio riceve così 3 danni in totale.");
    }

    public ArrayList<EnumColorCardAndAmmo> getRocketJumpCost() {

        return rocketJumpCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getFragmentingWarheadCost() {

        return fragmentingWarheadCost;
    }

    public void baseEffect(Map map, Player target1, Player currentPlayer,Square destSquare) throws NotVisibleTarget, NotValidDistance, MapException {

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        Square squareOfTarget1Player = map.findPlayer(target1);
        if((map.isVisible(target1,currentPlayer)) && (!(squareOfCurrentPlayer == squareOfTarget1Player))){

            if((destSquare!=null)&&(map.distance(squareOfTarget1Player,destSquare)==1)){

                map.movePlayer(target1,destSquare);
            }else if((destSquare!=null)&&(!(map.distance(squareOfTarget1Player,destSquare)==1))){

                throw new NotValidDistance();
            }
            ArrayList<EnumColorPlayer> rocketLauncherDamages=new ArrayList<>();
            rocketLauncherDamages.add(currentPlayer.getColor());
            rocketLauncherDamages.add(currentPlayer.getColor());
            target1.multipleDamages(rocketLauncherDamages);
        }else if(!(map.isVisible(target1,currentPlayer))){

            throw new NotVisibleTarget();
        }else if(squareOfCurrentPlayer==squareOfTarget1Player){

            throw new NotValidDistance();
        }
    }

    public void rocketJumpEffect(Map map, Player currentPlayer,Square destSquare) throws NotValidDistance, MapException {

        Square squareOfCurrentPlayer=map.findPlayer(currentPlayer);

        if((map.distance(squareOfCurrentPlayer,destSquare)==1)||(map.distance(squareOfCurrentPlayer,destSquare)==2)){

            map.movePlayer(currentPlayer,destSquare);
        }else{

            throw new NotValidDistance();
        }
    }


    public void baseEffectWithFragmenting(Map map, Player target1, Player currentPlayer,Square destSquare) throws NoTargetInSquare,NotVisibleTarget, NotValidDistance, MapException {

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        Square squareOfTarget1Player = map.findPlayer(target1);
        ArrayList<Player> playersOnTarget1Square= new ArrayList<>(map.playersOnSquare(squareOfTarget1Player));
        if((map.isVisible(target1,currentPlayer)) && (!(squareOfCurrentPlayer == squareOfTarget1Player))
                &&(playersOnTarget1Square.size()!=0)){

            if((destSquare!=null)&&(map.distance(squareOfTarget1Player,destSquare)==1)){

                map.movePlayer(target1,destSquare);

            }else if((destSquare!=null)&&(!(map.distance(squareOfTarget1Player,destSquare)==1))){

                throw new NotValidDistance();
            }
            for(Player p:playersOnTarget1Square){

                p.singleDamage(currentPlayer.getColor());
            }
            ArrayList<EnumColorPlayer> rocketLauncherDamages=new ArrayList<>();
            rocketLauncherDamages.add(currentPlayer.getColor());
            rocketLauncherDamages.add(currentPlayer.getColor());
            target1.multipleDamages(rocketLauncherDamages);
        }else if(!(map.isVisible(target1,currentPlayer))){

            throw new NotVisibleTarget();
        }else if(squareOfCurrentPlayer==squareOfTarget1Player){

            throw new NotValidDistance();
        }else if(playersOnTarget1Square.size()==0){

            throw new NoTargetInSquare();
        }
    }


}
