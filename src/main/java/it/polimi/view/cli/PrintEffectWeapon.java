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

    //TRACTOR BEAM
    /**
     * Print the effect of this weapon
     */
    public static void printTractorBeamBasicMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED BASIC MODE");
        System.out.print("Who is your target? In which square do you want to move it? (insert players' ID and row and column for the square )");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     */
    public static void printTractorBeamPunisherMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED PUNISHER MODE");
        System.out.print("Who is your target? (insert players' ID)");
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

    //PLASMA GUN
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printPlasmaGunBasicEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target (it must be visible)? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printPlasmaGunPhaseGlide(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE PHASE GLIDE");
        System.out.println("In which square do you want to move? (insert row and column)");
    }

    /**
     * Print the effect of this weapon
     */
    public static void printPlasmaGunChargedShot(){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE CHARGE SHOT");
    }

    //WHISPER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printWhisperEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("EFFECT");
        System.out.println("Who is your target (it must be visible and at least 2 squares away)? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //CANNON VORTEX
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printCannonVortexBasicEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Choose a square that you can see at least 1 distance movement; choose also a target in the square where the vortex is located or distant 1 movement. (insert row and column for the square and player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printCannonVortexBlackHole(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BLACK HOLE");
        System.out.println("Choose up to 2 other targets in the square in where the vortex is located or 1 movement away (insert row and column for the square and player's ID)");
        System.out.println("if you want to choose only one player enter -1 as the ID of the second");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //FURNACE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printFurnaceBasicMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC MODE");
        System.out.println("Choose a room you can see, but not yours. (insert ID corresponding to the color)");
        System.out.println("0 -> blue");
        System.out.println("1 -> green");
        System.out.println("2 -> pink");
        System.out.println("3 -> red");
        System.out.println("4 -> white");
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printFurnaceCozyFireMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("COZY FIRE MODE");
        System.out.println("Choose a square (exactly distant 1 movement). (insert row and column)");
    }

    //HEATSEEKER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printHeatseekerEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("EFFECT");
        System.out.println("Who is your target (it mustn't be visible)? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //HELLION
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printHellionBasicMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC MODE");
        System.out.println("Who is your target (it must be visible and exactly distant 1 movement)? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printHellionNanoTracerMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("NANO-TRACER MODE");
        System.out.println("Who is your target (it must be visible and exactly distant 1 movement)? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }
}