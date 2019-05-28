package it.polimi.view.cli;

import it.polimi.model.RemoteGameModel;

import java.io.Serializable;

public class PrintEffectWeapon implements Serializable {

    //LOCK RIFLE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printLockRifleBasicEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printLockRifleSecondLock(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE SECOND LOCK");
        System.out.println("Select a different target you can see. (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //ELECTROSCYTHE
    /**
     * Print the effect of this weapon
     */
    public static void printElectroscytheBasicMode(){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
    }

    /**
     * Print the effect of this weapon
     */
    public static void printElectroscytheReaperMode(){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE REAPER MODE");
    }

    //MACHINE GUN
    /**
     * Print the effect of this weapon
     */
    public static void printMachineGunBasicEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is/are your target/s? The targets must be different and visible to you (insert players' ID)");
        System.out.println("if you want to shoot only one player enter -1 as the ID of the second");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     */
    public static void printMachineGunFocusShot(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED FOCUS SHOT");
        System.out.print("Select one of the two targets");
        System.out.println("0 -> first target");
        System.out.println("1 -> second target");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     */
    public static void printMachineGunTurretTripod(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED TURRET TRIPOD");
        System.out.print("Select the other target and/or select a third target you can see (insert players' ID)");
        System.out.println("if you want to shoot only the third player enter -1 as first choice");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //THOR
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printThorBasicEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printThorChainReaction(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE CHAIN REACTION");
        System.out.println("Select a second target that your first target can see (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printThorHighVoltage(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE HIGH VOLTAGE");
        System.out.println("Select a third target that your second target can see (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }
}
