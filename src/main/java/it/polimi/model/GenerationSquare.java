package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Generation square.
 */
public class GenerationSquare extends Square{

    private ArrayList<WeaponCard> weaponList;
    
    /**
     * Instantiates a new Generation Square.
     *
     * @param r          the row
     * @param c          the ccolumn
     * @param color      the color
     */
    public GenerationSquare(int r, int c, EnumColorSquare color) {

        super(r,c,color);
        this.weaponList=null;
    }
    
    /**
     * Gets weapon list.
     *
     * @return the weapon list
     */
    public ArrayList<WeaponCard> getWeaponList() {

        return weaponList;
    }
    
    /**
     * Add weapon card.
     *
     * @param weaponCard the weapon card
     */
    public void addWeaponCard(WeaponCard weaponCard) {

        if(weaponList.size()<=3){

            this.weaponList.add(weaponCard);
        }
    }
    
    /**
     * Get weapon weapon card, adn remove from Generation square.
     *
     * @param pos the pos of the Weapon
     * @return the weapon card and remove from Generation square
     */
    public WeaponCard catchWeapon(int pos) {

        WeaponCard temp=this.weaponList.get(pos);
        this.weaponList.remove(pos);
        return temp;
    }


}
