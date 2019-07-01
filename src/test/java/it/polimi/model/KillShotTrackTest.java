package it.polimi.model;

import org.junit.Before;
import org.junit.Test;
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




}
