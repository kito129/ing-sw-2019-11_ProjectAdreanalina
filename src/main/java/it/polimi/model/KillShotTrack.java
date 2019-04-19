package it.polimi.model;

import java.util.ArrayList;

public class KillShotTrack{

    private int finalScore;
    private int numberColorPlayer;
    private int max;

    private EnumColorPlayer doubleKill;

    private ArrayList<KillShotTrackPoint> track;

    public ArrayList<KillShotTrackPoint> getTrack() {
        return track;
    }



    public void decreaseKillShotTrack(){

        track.remove(0);

    }

    public void insertKillShotTrackPoint(KillShotTrackPoint killShotTrackPoint){

        track.add(new ArrayList<KillShotTrackPoint>);

    }

    public int numberColorKSTP(EnumColorPlayer colorPlayer, KillShotTrackPoint KSTP){

        for (i = 0; i < track.size; i++){

            if(colorPlayer.equals(KSTP.getMark1())){
                numberColorPlayer++;
            }

            if(colorPlayer.equals(KSTP.getMark2())){
                numberColorPlayer++;
            }

        }

        return numberColorPlayer;

    }

        return numberColorPlayer;

    }

    public int finalScore(Player player){

        player.getColor();





        return finalScore;

    }


}