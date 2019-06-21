package it.polimi.view.cli;

import it.polimi.model.Player;

import java.io.Serializable;

public class PrintDamagesAndMarks implements Serializable {

    /**
     * Print player's damages
     * @param player      the player choosen
     */
    public static void printDamages(Player player){

        PrintEnumPlayer.print(player.getPlayerBoard().getDamages());
    }

    /**
     * Print player's marks
     * @param player      the player choosen
     */
    public static void printMarks(Player player){

        PrintEnumPlayer.print(player.getPlayerBoard().getMarks());
    }
}