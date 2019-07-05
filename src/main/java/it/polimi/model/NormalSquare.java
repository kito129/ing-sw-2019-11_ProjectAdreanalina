package it.polimi.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Normal square.
 */
public class NormalSquare extends Square implements Serializable {

    private AmmoCard ammoCard;
    
    /**
     * Instantiates Normal Square.
     *
     * @param r the row
     * @param c the column
     * @param color the color
     */
    public NormalSquare(int r, int c, EnumColorSquare color) {

       super(r,c,color);
       this.ammoCard= null;

    }

    /**
     * Checks if the square contains an ammo Card.
     *
     * @return true if the ammo card is on Square.
     */
    
    public boolean containAmmoCard(){
    
        return ammoCard != null;
    }
    
    /**
     * Get ammo card.
     *
     * @return the ammo card in the square.
     */
    public AmmoCard getAmmoCard(){

        return ammoCard;
    }
    
    /**
     * Sets ammo card.
     *
     * @param ammoCard the ammo card.
     */
    public void setAmmoCard(AmmoCard ammoCard) {

        this.ammoCard = ammoCard;
    }
    
    /**
     * Get ammo card ammo card and remove from the square.
     *
     * @return the ammo card on this square.
     */
    public AmmoCard catchAmmoCard(){
        
        AmmoCard temp = this.ammoCard;
        this.ammoCard=null;
        return temp;
        
    }
}
