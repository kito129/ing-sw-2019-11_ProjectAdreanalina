package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Kill shot track.
 */
public class KillShotTrack{

    private EnumColorPlayer doubleKill;
    private ArrayList<KillShotTrackPoint> track;
    
    /**
     * Instantiates a new Kill shot track.
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
     * Skull number int.
     *
     * @return the int
     */
    public int skullNumber(){

        int i = 0;
        for (KillShotTrackPoint a :track){

            if(a.isSkull()) i++;
        }
        return i;
    }
    
    
    /**
     * Update track.
     *
     * @param color the color
     */
    public void updateTrack(ArrayList<EnumColorPlayer> color){
        if(this.skullNumber()>0) {

            for (KillShotTrackPoint a : track) {

                if (a.isSkull()) {
                    
                    a.setSkull(false);
                    a.setMark1(color.get(0));
                    if(color.get(1)!=null){
                        
                        a.setMark2((color.get(1)));
                    }
                    
                }
            }
        }
    }
    
    /**
     * Sets double kill.
     *
     * @param doubleKill the double kill
     */
    public void setDoubleKill(EnumColorPlayer doubleKill) {

        this.doubleKill = doubleKill;
    }
    
    /**
     * Gets color occurance.
     *
     * @param colorPlayer the color player
     * @return the color occurance
     */
    public int getColorOccurance(EnumColorPlayer colorPlayer) {
    
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