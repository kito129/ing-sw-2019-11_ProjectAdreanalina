package it.polimi.view.cli;

import it.polimi.model.Player;
import it.polimi.model.PlayerBoard;

import java.io.Serializable;

public class PrintPlayerBoard implements Serializable {

    private int ammoB;
    private int ammoR;
    private int ammoY;
    private int boardValue;
    private int numberOfDeaths;
    

    /**
     * Print attributes of PlayerBoard.
     */
    public void print(Player player, PlayerBoard playerBoard){

        ammoB = playerBoard.getAmmoB();
        ammoR = playerBoard.getAmmoR();
        ammoY = playerBoard.getAmmoY();
        boardValue = playerBoard.getBoardValue();
        numberOfDeaths = playerBoard.getNumberOfDeaths();

        System.out.println("AMMO BLUE: " +ammoB);
        System.out.println("AMMO RED: " +ammoR);
        System.out.println("AMMO YELLOW: " +ammoY);
        System.out.println("BOARD VALUE: " +boardValue);
        System.out.println("N. DEATHS: " +numberOfDeaths);

    }
}
