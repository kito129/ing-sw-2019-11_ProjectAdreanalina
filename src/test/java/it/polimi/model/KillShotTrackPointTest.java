package it.polimi.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KillShotTrackPointTest {

    KillShotTrackPoint killShotTrackPoint;


    @Before

    public void setUp(){

        killShotTrackPoint=new KillShotTrackPoint();
    }

    @Test

    public void setMark1And2Test(){

        killShotTrackPoint.setMark1(EnumColorPlayer.BLU);
        killShotTrackPoint.setMark2(EnumColorPlayer.BLU);
        assertEquals(EnumColorPlayer.BLU,killShotTrackPoint.getMark1());
        assertEquals(EnumColorPlayer.BLU,killShotTrackPoint.getMark2());
    }

    @Test

    public void isSkullTest(){

        assertTrue(killShotTrackPoint.isSkull());
    }

    @Test

    public void setSkullTest(){

        assertTrue(killShotTrackPoint.isSkull());
        killShotTrackPoint.setSkull(false);
        assertFalse(killShotTrackPoint.isSkull());
    }
}
