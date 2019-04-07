package it.polimi.model;

import java.util.ArrayList;

import static it.polimi.model.ColorCard.SKULL;

public class MortalTrack {
    private ArrayList<ColorCard> track;

    /**
     * initialite mortal track with numer of skull passed by parameter
     */
    public MortalTrack(int initialSkull){
        for (int i=0;i<initialSkull;i++){
            track.add(ColorCard.SKULL);
        }
    }
    public void removeSkull(int pos, ColorCard color, int nMark){

    }

}
