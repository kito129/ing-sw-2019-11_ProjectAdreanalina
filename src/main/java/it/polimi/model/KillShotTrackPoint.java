package it.polimi.model;

/**
 * The type Kill shot track point.
 */
public class KillShotTrackPoint {

    private boolean skull;
    private EnumColorPlayer mark1;
    private EnumColorPlayer mark2;
    
    /**
     * Instantiates a new Kill shot track point.
     *
     * @param skull the skull
     * @param mark1 the mark 1
     * @param mark2 the mark 2
     */
    public KillShotTrackPoint(boolean skull, EnumColorPlayer mark1, EnumColorPlayer mark2){

        if(!skull){

            this.mark1=mark1;
            this.mark2=mark2;
        }
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
     * @param mark1 the mark 1
     */
    public void setMark1(EnumColorPlayer mark1) {

        this.mark1 = mark1;
    }
    
    /**
     * Sets mark 2.
     *
     * @param mark2 the mark 2
     */
    public void setMark2(EnumColorPlayer mark2) {

        this.mark2 = mark2;
    }
    
    /**
     * Sets skull.
     *
     * @param skull the skull
     */
    public void setSkull(boolean skull) {

        this.skull = skull;
    }

}
