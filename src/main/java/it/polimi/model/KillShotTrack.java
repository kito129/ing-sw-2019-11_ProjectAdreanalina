package it.polimi.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Kill shot track, array list of killShotTrackPoint, with function to count color and skull.
 */
public class KillShotTrack implements Serializable {

    private EnumColorPlayer doubleKill;
    private ArrayList<KillShotTrackPoint> track;
    
    /**
     * Instantiates a new Kill shot track, with 8 skull in the array.
     */
    public KillShotTrack(){

        this.track = new ArrayList<KillShotTrackPoint>();
        for (int i = 0; i < 8; i++) {

            track.add(new KillShotTrackPoint(true, null, null));
        }
        this.doubleKill=null;
    }
    
    /**
     * Gets track.
     *
     * @return the track
     */
    public ArrayList<KillShotTrackPoint> getTrack() {

        return track;
    }
    
    /**
     * Calcualte the Skull number .
     *
     * @return skull number     */
    public int skullNumber(){

        int i = 0;
        for (KillShotTrackPoint a :track){

            if(a.isSkull()) i++;
        }
        return i;
    }
    
    
    /**
     * Update track, with color gat from playerBoard.
     *
     * @param color the color to add, an array if there are to color to add
     */
    public void updateTrack(ArrayList<EnumColorPlayer> color){
        
        for (KillShotTrackPoint a : track) {

            if (a.isSkull()) {
                
                a.setSkull(false);
                a.setMark1(color.get(0));
                if(color.size()>1){
                    
                    a.setMark2((color.get(1)));
                }
                return;
            }
        }
    }
    
    /**
     * Sets double kill.
     *
     * @param doubleKill color of player do the double kill
     */
    public void setDoubleKill(EnumColorPlayer doubleKill) {

        this.doubleKill = doubleKill;
    }
    
    /**
     * Gets color occurrence.
     *
     * @param colorPlayer the color player
     * @return the color occurance
     */
    public int getColorOccurence (EnumColorPlayer colorPlayer) {
    
        int i = 0;
        for (KillShotTrackPoint a : track) {
    
            if (a.getMark1() == colorPlayer)
                i++;
            if (a.getMark2() == colorPlayer)
                i++;
        }
        return i;
    }
    
}