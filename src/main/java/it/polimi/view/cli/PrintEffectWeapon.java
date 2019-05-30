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
        System.out.println("Who is your target? (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printLockRifleSecondLock(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE SECOND LOCK");
        System.out.println("Select a different target you can see. (enter player's ID)");
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
        System.out.println("Who is/are your target/s? The targets must be different and visible to you (enter players' ID)");
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
        System.out.print("Select the other target and/or select a third target you can see (enter players' ID)");
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
        System.out.print("Who is your target? In which square do you want to move it? (enter players' ID and row and column for the square )");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     */
    public static void printTractorBeamPunisherMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED PUNISHER MODE");
        System.out.print("Who is your target? (enter players' ID)");
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
        System.out.println("Who is your target? (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printThorChainReaction(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE CHAIN REACTION");
        System.out.println("Select a second target that your first target can see (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printThorHighVoltage(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE HIGH VOLTAGE");
        System.out.println("Select a third target that your second target can see (enter player's ID)");
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
        System.out.println("Who is your target? (it must be visible) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printPlasmaGunPhaseGlide(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE PHASE GLIDE");
        System.out.println("In which square do you want to move? (1 or 2 movements) (enter row and column)");
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
        System.out.println("WHISPER EFFECT");
        System.out.println("Who is your target? (it must be visible and at least 2 squares away from you) (enter player's ID)");
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
        System.out.println("Choose a square that you can see at least 1 distance movement; choose also a target in the square where the vortex is located or distant 1 movement. (enter row and column for the square and player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printCannonVortexBlackHole(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BLACK HOLE");
        System.out.println("Choose up to 2 other targets in the square in where the vortex is located or 1 movement away (enter row and column for the square and player's ID)");
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
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Choose a room you can see, but not yours. (enter ID corresponding to the color)");
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
        System.out.println("YOU HAVE SELECTED THE COZY FIRE MODE");
        System.out.println("Choose a square (exactly distant 1 movement from you). (enter row and column)");
    }

    //HEATSEEKER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printHeatseekerEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("HEATSEEKER EFFECT");
        System.out.println("Who is your target? (it mustn't be visible) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //HELLION
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printHellionBasicMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be visible and exactly distant 1 movement from you) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printHellionNanoTracerMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE NANO-TRACER MODE");
        System.out.println("Who is your target? (it must be visible and exactly distant 1 movement from you) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //ZX-2
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printZX2BasicMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be visible) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printZX2ScannerMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE SCANNER MODE");
        System.out.println("How many of these targets do you want to shoot? (max 3 and they must be visible)");
        System.out.println("(first enter the number of targets and then the players' ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //GRENADE LAUNCHER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printGrenadeLauncherBasicEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (it must be visible)");
        System.out.println("After having shot him you also want to move him? (in a square 1 movement away from you)");
        System.out.println("(first enter the player's ID and then 0 to say YES or 1 to say NO and then row and column)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printGrenadeLauncherExtraGrenade(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE EXTRA GRENADE");
        System.out.println("Choose a square you can see (enter row and column)");
    }

    //SHOTGUN
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printShotGunBasicMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be in your square)");
        System.out.println("After having shot him you also want to move him? (in a square 1 movement away from you)");
        System.out.println("(first enter the player's ID and then 0 to say YES or 1 to say NO and then row and column)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printShotGunLongBarrelMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE LONG BARREL MODE");
        System.out.println("Who is your target? (it must be in a square exactly 1 movement away from you)");
        System.out.println("(enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //CYBERBLADE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printCyberbladeBasicEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (it must be in your square) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printCyberbladeShadowstep(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE SHADOWSTEP");
        System.out.println("In which square do you want to move? (max 1 movement) (enter row and column)");
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printCyberbladeSliceAndDice(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE SLICE AND DICE");
        System.out.println("Who is your target? (it must be different form first target and must be in your square) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //SLEDGEHAMMER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printSledgehammerBasicMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be in your square) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printSledgehammerPulverizeMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE PULVERIZE MODE");
        System.out.println("Who is your target? (it must be in your square)");
        System.out.println("After having shot him you also want to move him? (in a square 0, 1 or 2 movements away from you)");
        System.out.println("(first enter the player's ID and then row and column)");
        System.out.println("(if you don't want to move it, insert the coordinates of your square)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //ROCKET LAUNCHER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printRocketLauncherBasicEffect(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (it must be visible and it must be in a square different from yours)");
        System.out.println("After having shot him you also want to move him? (max 1 movement)");
        System.out.println("(first enter the player's ID and then 0 to say YES or 1 to say NO and then row and column)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printRocketLauncherRocketJump(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE ROCKET JUMP");
        System.out.println("In which square do you want to move? (1 or 2 movements) (enter row and column)");
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printRocketLauncherFragmentingWarhead(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE FRAGMENTING WARHEAD");
    }

    //POWER GLOVE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printPowerGloveBasicMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be in a square exactly 1 movement from you) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printPowerGloveRocketFirstMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE ROCKET FIRST MODE");
        System.out.println("In which square do you want to move? (exactly 1 movement) (enter row and column)");
        System.out.println("Do you want to shoot a target after your movement? (max 1 movement)");
        System.out.println("(first enter row and column and then 0 to say YES or 1 to say NO and then player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printPowerGloveRocketFirstMode2(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("Now you can still move exactly 1 square and repeat the same effect");
        System.out.println("(so now pay attention: enter 0 to say YES or 1 to say NO, then enter row and column, then again 0 to say YES or 1 to say NO to choose to shoot end finally player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //SHOCKWAVE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the RemoteGameModel
     */
    public static void printShockwaveBasicMode(RemoteGameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("How many of these targets do you want to shoot? (max 3 and they must be in a square exactly 1 movement from you)");
        System.out.println("(first enter the number of targets and then the players' ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     */
    public static void printShockwaveTsunamiMode(){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE TSUNAMI MODE");
    }
}