package it.polimi.model;

import java.io.Serializable;

/**
 * The type Kill shot track point.
 */
public class KillShotTrackPoint implements Serializable {

    private boolean skull;
    private EnumColorPlayer mark1;
    private EnumColorPlayer mark2;

    /**
     * Istantiates KillShotTrackPoint
     */

    public KillShotTrackPoint() {

        skull = true;
        mark1=null;
        mark2=null;

    }
    
    /**
     * Gets mark 1.
     *
     * @return the mark 1
     */
    public EnumColorPlayer getMark1() {

        return mark1;
    }
    
    /**
     * Gets mark 2.
     *
     * @return the mark 2
     */
    public EnumColorPlayer getMark2() {

        return mark2;
    }
    
    /**
     * Is skull boolean.
     *
     * @return the boolean
     */
    public boolean isSkull() {

        return skull;
    }
    
    /**
     * Sets mark 1.
     *
     * @param mark1 the color of mark 1
     */
    public void setMark1(EnumColorPlayer mark1) {

        this.mark1 = mark1;
    }
    
    /**
     * Sets mark 2.
     *
     * @param mark2 the color of the mark 2
     */
    public void setMark2(EnumColorPlayer mark2) {

        this.mark2 = mark2;
    }
    
    /**
     * Sets skull.
     *
     * @param skull true if you want to set the field skull true
     */
    public void setSkull(boolean skull) {

        this.skull = skull;
    }

}
