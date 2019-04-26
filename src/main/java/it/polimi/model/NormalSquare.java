package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Normal square.
 */
public class NormalSquare extends Square {

    private AmmoCard ammoCard;
    
    /**
     * Instantiates a new Normal square.
     *
     * @param row    the row
     * @param column the column
     * @param color  the color
     */
    public NormalSquare(int row, int column,EnumColorSquare color) {
    
        super(row,column,color,null);
    
    }
    
    /**
     * Instantiates a new Normal square.
     *
     * @param r     the r
     * @param c     the c
     * @param color the color
     * @param link  the link
     */
    public NormalSquare(int r, int c, EnumColorSquare color, ArrayList<Square> link) {

       super(r,c,color,link);
       this.ammoCard= new AmmoCard(0,0,0,null);

    }
    
    /**
     * Get ammo card ammo card.
     *
     * @return the ammo card
     */
    public AmmoCard getAmmoCard(){

        return ammoCard;
    }
    
    /**
     * Sets ammo card.
     *
     * @param ammoCard the ammo card
     */
    public void setAmmoCard(AmmoCard ammoCard) {

        this.ammoCard = ammoCard;
    }
    
    /**
     * Catch ammo card ammo card.
     *
     * @return the ammo card
     */
    public AmmoCard catchAmmoCard(){
        AmmoCard temp = this.ammoCard;
        this.ammoCard=null;
        return temp;
        
    }


}
