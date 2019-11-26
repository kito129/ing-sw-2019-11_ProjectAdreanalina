package it.polimi.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class KillShotTrackTest {

    KillShotTrack killShotTrack;

    @Before

    public void setUp(){

        killShotTrack=new KillShotTrack();
    }

    @Test

    public void skullNumberTest(){

        assertEquals(8,killShotTrack.skullNumber());
    }

    @Test

    public void updateTrackTest(){

        assertEquals(8,killShotTrack.skullNumber());
        ArrayList<EnumColorPlayer>colorPlayers=new ArrayList<>();
        colorPlayers.add(EnumColorPlayer.BLU);
        colorPlayers.add(EnumColorPlayer.BLU);
        killShotTrack.updateTrack(colorPlayers);
        assertEquals(7,killShotTrack.skullNumber());
        int blu=0;
        int numberOfSkull=0;
        for(KillShotTrackPoint killShotTrackPoint:killShotTrack.getTrack()){

            if(killShotTrackPoint.getMark1()==EnumColorPlayer.BLU){

                blu++;
            }
            if(killShotTrackPoint.getMark2()==EnumColorPlayer.BLU){

                blu++;
            }
            if(killShotTrackPoint.isSkull()){
                numberOfSkull++;
            }
        }
        assertEquals(7,numberOfSkull);
        assertEquals(2,blu);
        numberOfSkull=0;
        blu=0;
        ArrayList<EnumColorPlayer>colorPlayers1=new ArrayList<>();
        colorPlayers1.add(EnumColorPlayer.BLU);
        killShotTrack.updateTrack(colorPlayers1);
        assertEquals(6,killShotTrack.skullNumber());
        for(KillShotTrackPoint killShotTrackPoint:killShotTrack.getTrack()){

            if(killShotTrackPoint.getMark1()==EnumColorPlayer.BLU){

                blu++;
            }
            if(killShotTrackPoint.getMark2()==EnumColorPlayer.BLU){

                blu++;
            }
            if(killShotTrackPoint.isSkull()){
                numberOfSkull++;
            }
        }
        assertEquals(6,numberOfSkull);
        assertEquals(3,blu);
    }

    @Test

    public void setDoubleKillTest(){

        assertNull(killShotTrack.getDoubleKill());
        killShotTrack.setDoubleKill(EnumColorPlayer.BLU);
        assertEquals(EnumColorPlayer.BLU,killShotTrack.getDoubleKill());
    }

    @Test

    public void getColorOccurenceTest(){

        assertEquals(0,killShotTrack.colorOccurence(EnumColorPlayer.BLU));
        assertEquals(0,killShotTrack.colorOccurence(EnumColorPlayer.YELLOW));
        assertEquals(0,killShotTrack.colorOccurence(EnumColorPlayer.PINK));
        assertEquals(0,killShotTrack.colorOccurence(EnumColorPlayer.GREEN));
        assertEquals(0,killShotTrack.colorOccurence(EnumColorPlayer.GREY));
        ArrayList<EnumColorPlayer> k1=new ArrayList<>();
        k1.add(EnumColorPlayer.BLU);
        k1.add(EnumColorPlayer.BLU);
        ArrayList<EnumColorPlayer> k2=new ArrayList<>();
        k2.add(EnumColorPlayer.BLU);
        ArrayList<EnumColorPlayer> k3=new ArrayList<>();
        k3.add(EnumColorPlayer.YELLOW);
        k3.add(EnumColorPlayer.GREEN);
        killShotTrack.updateTrack(k1);
        assertEquals(2,killShotTrack.colorOccurence(EnumColorPlayer.BLU));
        killShotTrack.updateTrack(k2);
        assertEquals(3,killShotTrack.colorOccurence(EnumColorPlayer.BLU));
        killShotTrack.updateTrack(k3);
        assertEquals(3,killShotTrack.colorOccurence(EnumColorPlayer.BLU));
        assertEquals(1,killShotTrack.colorOccurence(EnumColorPlayer.YELLOW));
        assertEquals(1,killShotTrack.colorOccurence(EnumColorPlayer.GREEN));

    }
}
