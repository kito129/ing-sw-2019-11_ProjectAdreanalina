package it.polimi.view.cli;

import it.polimi.model.GameModel;

import java.io.Serializable;

public class PrintEffectWeapon implements Serializable {

    //LOCK RIFLE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printLockRifleBasicEffect(GameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printLockRifleSecondLock(GameModel gameModel){

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
    public static void printMachineGunBasicEffect(GameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is/are your target/s? The targets must be different and visible to you (enter players' ID)");
        System.out.println("if you want to shoot only one player enter -1 as the ID of the second");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     */
    public static void printMachineGunFocusShot(GameModel gameModel){

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
    public static void printMachineGunTurretTripod(GameModel gameModel){

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
    public static void printTractorBeamBasicMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED BASIC MODE");
        System.out.print("Who is your target? In which square do you want to move it? (enter players' ID and row and column for the square )");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     */
    public static void printTractorBeamPunisherMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED PUNISHER MODE");
        System.out.print("Who is your target? (enter players' ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //THOR
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printThorBasicEffect(GameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printThorChainReaction(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE CHAIN REACTION");
        System.out.println("Select a second target that your first target can see (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printThorHighVoltage(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE HIGH VOLTAGE");
        System.out.println("Select a third target that your second target can see (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //PLASMA GUN
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printPlasmaGunBasicEffect(GameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (it must be visible) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printPlasmaGunPhaseGlide(GameModel gameModel){

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
     * @param gameModel   the reference to the GameModel
     */
    public static void printWhisperEffect(GameModel gameModel){

        System.out.println();
        System.out.println("WHISPER EFFECT");
        System.out.println("Who is your target? (it must be visible and at least 2 squares away from you) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //CANNON VORTEX
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printCannonVortexBasicEffect(GameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Choose a square that you can see at least 1 distance movement; choose also a target in the square where the vortex is located or distant 1 movement. (enter row and column for the square and player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printCannonVortexBlackHole(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BLACK HOLE");
        System.out.println("Choose up to 2 other targets in the square in where the vortex is located or 1 movement away (enter row and column for the square and player's ID)");
        System.out.println("if you want to choose only one player enter -1 as the ID of the second");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //FURNACE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printFurnaceBasicMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Choose a room you can see, but not yours. (enter ID corresponding to the color)");
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printFurnaceCozyFireMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE COZY FIRE MODE");
        System.out.println("Choose a square (exactly distant 1 movement from you). (enter row and column)");
    }

    //HEATSEEKER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printHeatseekerEffect(GameModel gameModel){

        System.out.println();
        System.out.println("HEATSEEKER EFFECT");
        System.out.println("Who is your target? (it mustn't be visible) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //HELLION
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printHellionBasicMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be visible and exactly distant 1 movement from you) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printHellionNanoTracerMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE NANO-TRACER MODE");
        System.out.println("Who is your target? (it must be visible and exactly distant 1 movement from you) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //ZX-2
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printZX2BasicMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be visible) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printZX2ScannerMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE SCANNER MODE");
        System.out.println("How many of these targets do you want to shoot? (max 3 and they must be visible)");
        System.out.println("(first enter the number of targets and then the players' ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //GRENADE LAUNCHER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printGrenadeLauncherBasicEffect(GameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (it must be visible)");
        System.out.println("After having shot him you also want to move him? (in a square 1 movement away from you)");
        System.out.println("(first enter the player's ID, then 0 to say YES or 1 to say NO and finally row and column)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printGrenadeLauncherExtraGrenade(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE EXTRA GRENADE");
        System.out.println("Choose a square you can see (enter row and column)");
    }

    //SHOTGUN
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printShotGunBasicMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be in your square)");
        System.out.println("After having shot him you also want to move him? (in a square 1 movement away from you)");
        System.out.println("(first enter the player's ID, then 0 to say YES or 1 to say NO and finally row and column)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printShotGunLongBarrelMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE LONG BARREL MODE");
        System.out.println("Who is your target? (it must be in a square exactly 1 movement away from you)");
        System.out.println("(enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //CYBERBLADE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printCyberbladeBasicEffect(GameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (it must be in your square) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printCyberbladeShadowstep(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE SHADOWSTEP");
        System.out.println("In which square do you want to move? (max 1 movement) (enter row and column)");
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printCyberbladeSliceAndDice(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE SLICE AND DICE");
        System.out.println("Who is your target? (it must be different form first target and must be in your square) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //SLEDGEHAMMER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printSledgehammerBasicMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be in your square) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printSledgehammerPulverizeMode(GameModel gameModel){

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
     * @param gameModel   the reference to the GameModel
     */
    public static void printRocketLauncherBasicEffect(GameModel gameModel){

        System.out.println();
        System.out.println("BASIC EFFECT");
        System.out.println("Who is your target? (it must be visible and it must be in a square different from yours)");
        System.out.println("After having shot him you also want to move him? (max 1 movement)");
        System.out.println("(first enter the player's ID, then 0 to say YES or 1 to say NO and finally row and column)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printRocketLauncherRocketJump(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE ROCKET JUMP");
        System.out.println("In which square do you want to move? (1 or 2 movements) (enter row and column)");
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printRocketLauncherFragmentingWarhead(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE FRAGMENTING WARHEAD");
    }

    //POWER GLOVE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printPowerGloveBasicMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (it must be in a square exactly 1 movement from you) (enter player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printPowerGloveRocketFirstMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE ROCKET FIRST MODE");
        System.out.println("In which square do you want to move? (exactly 1 movement) (enter row and column)");
        System.out.println("Do you want to shoot a target after your movement? (max 1 movement)");
        System.out.println("(first enter row and column, then 0 to say YES or 1 to say NO and finally player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printPowerGloveRocketFirstMode2(GameModel gameModel){

        System.out.println();
        System.out.println("Now you can still move exactly 1 square and repeat the same effect");
        System.out.println("(so now pay attention: enter 0 to say YES or 1 to say NO, then enter row and column, then again 0 to say YES or 1 to say NO to choose to shoot and finally player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //SHOCKWAVE
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printShockwaveBasicMode(GameModel gameModel){

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

    //RAILGUN
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printRailgunBasicMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is your target? (you can choose who you want)");
        System.out.println("(first enter the cardinal direction and then the player's ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printRailgunPiercingMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE PIERCING MODE");
        System.out.println("Who is/are your target/s? (you can choose who you want, but max 2");
        System.out.println("(first enter the cardinal direction, than the number of targets and finally the players' ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //FLAMETHROWER
    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printFlamethrowerBasicMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BASIC MODE");
        System.out.println("Who is/are your target/s? (1 or 2; they must be in different square and the squares must be 1 movement from your square and from first square selected and also in the same direction");
        System.out.println("(first enter the number of targets and then enter players' ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    /**
     * Print the effect of this weapon
     * @param gameModel   the reference to the GameModel
     */
    public static void printFlamethrowerBarbecueMode(GameModel gameModel){

        System.out.println();
        System.out.println("YOU HAVE SELECTED THE BARBECUE MODE");
        System.out.println("Who is/are your target/s? (1 or 2; they must be in different square and the squares must be 1 movement from your square and from first square selected and also in the same direction");
        System.out.println("(first enter the number of targets and then enter players' ID)");
        PrintPlayer.print(gameModel.getPlayers(false));
    }

    //------------------------------------------------------------------------------------------------------

    /**
     * Print the weapons
     */
    public static void printSelectWeapon(){

        System.out.println();
        System.out.println("WHICH WEAPON DO YOU WANT TO USE?");
    }

    /**
     * Print the effect of weapons
     */
    public static void printSelectWeaponEffect(){

        System.out.println();
        System.out.println("WHICH WEAPON EFFECT DO YOU WANT TO USE?");
    }
}