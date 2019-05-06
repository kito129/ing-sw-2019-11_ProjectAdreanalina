package it.polimi.view.cli;

import it.polimi.model.*;

import java.io.Serializable;

public class PrintPlayerBoard implements Serializable {

<<<<<<< HEAD
    
=======
>>>>>>> origin/master

    /**
     * Print attributes of PlayerBoard.
     */
    public void print(Player player){

        System.out.println("AMMO BLUE: " +player.getPlayerBoard().getAmmoB());
        System.out.println("AMMO RED: " +player.getPlayerBoard().getAmmoR());
        System.out.println("AMMO YELLOW: " +player.getPlayerBoard().getAmmoY());
        System.out.println("BOARD VALUE: " +player.getPlayerBoard().getBoardValue());
        System.out.println("N. DEATHS: " +player.getPlayerBoard().getNumberOfDeaths());
        System.out.println("DAMAGES: " +player.getPlayerBoard().getDamages().toString());
        System.out.println("MARKS: " +player.getPlayerBoard().getMarks().toString());


<<<<<<< HEAD

=======
        
>>>>>>> origin/master
    }
}
