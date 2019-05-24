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

    //TODO da sistemare quando faranno il metodo
    /**
     * Print damages on domination board
     * @param player      the player choosen
     */
    public static void printDamagesDominationBoard(Player player){

        PrintEnumPlayer.print(player.getPlayerBoard().getDamages());
    }
}