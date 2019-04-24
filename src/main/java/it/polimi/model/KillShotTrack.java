package it.polimi.model;

import java.util.ArrayList;

public class KillShotTrack{

    private EnumColorPlayer doubleKill;
    private ArrayList<KillShotTrackPoint> track;

    public KillShotTrack(){
        this.track = new ArrayList<KillShotTrackPoint>();
        for (int i = 0; i < 8; i++) {
            track.add(new KillShotTrackPoint(true, null, null));
        }
        this.doubleKill=null;
    }

    public ArrayList<KillShotTrackPoint> getTrack() {

        return track;
    }

    public int skullNumber(){
        int i = 0;
        for (KillShotTrackPoint a :track){
            if(a.isSkull()) i++;
        }
        return i;
    }

    public void updateTrack(EnumColorPlayer color1,EnumColorPlayer color2){
        if(this.skullNumber()>0) {
            for (KillShotTrackPoint a : track) {
                if (a.isSkull()) {
                    a.setSkull(false);
                    a.setMark1(color1);
                    a.setMark2(color2);
                }
            }
        }
    }

    public void setDoubleKill(EnumColorPlayer doubleKill) {

        this.doubleKill = doubleKill;
    }

    public int getColorOccurance(EnumColorPlayer colorPlayer){
        int i=0;
        for (KillShotTrackPoint a : track){
            if(a.getMark1()==colorPlayer) i++;
            if(a.getMark2()==colorPlayer) i++;
        }
        if(doubleKill==colorPlayer) i++;
        return i;
    }
}