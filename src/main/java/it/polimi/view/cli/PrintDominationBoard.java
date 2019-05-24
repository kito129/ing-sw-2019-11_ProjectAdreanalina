package it.polimi.view.cli;

import it.polimi.model.KillShotTrackPoint;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintDominationBoard implements Serializable {

    public void print(ArrayList<KillShotTrackPoint> killShotTrackPoints){

        PrintKillShotTrack.print(killShotTrackPoints);

        System.out.println("DOMINATION BOARD:");
        System.out.println();
        System.out.println("SPAWN POINT BLUE:");
        //TODO
        // una roba del tipo
        // PrintDamagesAndMarks.printDamages(player);
        System.out.println("SPAWN POINT RED:");
        //TODO
        System.out.println("SPAWN POINT YELLOW:");
        //TODO

    }
}
