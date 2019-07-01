package it.polimi.view.cli;

import it.polimi.model.GameModel;

import java.io.Serializable;

public class PrintEffectPowerUp implements Serializable {

    //TARGETING SCOPE
    /**
     * Print the effect of this power up
     * @param gameModel   the reference to the GameModel
     */
    public static void printTargetingScope(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE TARGETING SCOPE");
        System.out.println("Who is your target? He must have received at least one damage during this turn (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayerDamaged());
    }

    //NEWTON
    /**
     * Print the effect of this power up
     * @param gameModel   the reference to the GameModel
     */
    public static void printNewton(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE NEWTON");
        System.out.println("Which player do you want to move and in which square do you want to put him? (enter players' ID and row and column for the square)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //TAGBACK GRENADE
    /**
     * Print the effect of this power up
     */
    public static void printTagbackGrenade(){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE TAGBACK GRENADE");
    }

    //TELEPORTER
    /**
     * Print the effect of this power up
     * @param gameModel   the reference to the GameModel
     */
    public static void printTeleporter(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE TELEPORTER");
        System.out.println("In which square do you want to move? (enter row and column for the square)");
    }
}
