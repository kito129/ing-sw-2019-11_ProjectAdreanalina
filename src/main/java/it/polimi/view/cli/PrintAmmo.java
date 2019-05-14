package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;

public class PrintAmmo implements Serializable {

    /**
     * Print Client's ammo.
     * @param player   the player who has those ammo
     */
    public static void print(Player player) {

        System.out.println("AMMO BLUE: " +player.getPlayerBoard().getAmmoB());
        System.out.println("AMMO RED: " +player.getPlayerBoard().getAmmoR());
        System.out.println("AMMO YELLOW: " +player.getPlayerBoard().getAmmoY());
    }

}
