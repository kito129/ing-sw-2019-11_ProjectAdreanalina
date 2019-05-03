package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.InvalidActionForThisCard;

import java.util.ArrayList;

public class Electroscythe extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> reaperModeCost;

    /**
     * Instantiates a new Lock Electoscythe card.
     * Creates the list of recharge cost setting its value to BLU.
     * Sets the field color to BLU.
     * Creates the list of reaper mode cost (cost of alternative fire mode) settings it to BLU,RED.
     */
    public Electroscythe() {

        super("ELECTOSCYTHE", EnumColorCardAndAmmo.BLU);
        rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        reaperModeCost= new ArrayList<EnumColorCardAndAmmo>();
        reaperModeCost.add(EnumColorCardAndAmmo.BLU);
        reaperModeCost.add(EnumColorCardAndAmmo.RED);
    }

    public ArrayList<EnumColorCardAndAmmo> getReaperModecost() {

        return reaperModeCost;
    }

    public void basicMode(Map map, Player currentPlayer) throws InvalidActionForThisCard {

        ArrayList<Player> allInCurrentSquare=new ArrayList<>();
        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        allInCurrentSquare=map.playersOnSquare(squareOfCurrentPlayer);
        if(allInCurrentSquare.size()>1){

            for(Player p:allInCurrentSquare){

                if(p!=currentPlayer){

                    p.singleDamage(currentPlayer.getColor());
                }
            }
        }else {

            throw new InvalidActionForThisCard();
        }
    }

    public void reaperMode(Map map,Player currentPlayer) throws InvalidActionForThisCard {

        ArrayList<Player> allInCurrentSquare=new ArrayList<>();
        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        allInCurrentSquare=map.playersOnSquare(squareOfCurrentPlayer);
        if(allInCurrentSquare.size()>1){

            for(Player p:allInCurrentSquare){

                if(p!=currentPlayer){

                    p.singleDamage(currentPlayer.getColor());
                    p.singleDamage(currentPlayer.getColor());
                }
            }
        }else {

            throw new InvalidActionForThisCard();
        }
    }





}