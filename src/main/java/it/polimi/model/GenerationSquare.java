package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Generation square.
 */
public class GenerationSquare extends Square{

    private ArrayList<WeaponCard> weaponList;
    
    /**
     * Instantiates a new Generation square.
     *
     * @param r     the r
     * @param c     the c
     * @param color the color
     * @param link  the link
     */
    public GenerationSquare(int r, int c, EnumColorSquare color, ArrayList<Square> link) {

        super(r,c,color,link);
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
     * Catch weapon weapon card.
     *
     * @param pos the pos
     * @return the weapon card
     */
    public WeaponCard catchWeapon(int pos) {

        WeaponCard temp=this.weaponList.get(pos);
        this.weaponList.remove(pos);
        return temp;
    }


}
