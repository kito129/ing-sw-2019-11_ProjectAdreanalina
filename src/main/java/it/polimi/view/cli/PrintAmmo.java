package it.polimi.view.cli;

import it.polimi.model.Player;
import it.polimi.model.PlayerBoard;

import java.io.Serializable;

public class PrintAmmo implements Serializable {

    private int ammoB;
    private int ammoR;
    private int ammoY;

    /**
     * Print Client's ammo.
     */
<<<<<<< HEAD
=======
    public void print(PlayerBoard playerBoard) {

        ammoB = playerBoard.getAmmoB();
        ammoR = playerBoard.getAmmoR();
        ammoY = playerBoard.getAmmoY();

        System.out.println("AMMO BLUE: " +ammoB);
        System.out.println("AMMO RED: " +ammoR);
        System.out.println("AMMO YELLOW: " +ammoY);
    }
>>>>>>> origin/master
}
