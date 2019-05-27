package it.polimi.view.cli;

import it.polimi.model.RemoteGameModel;

import java.io.Serializable;

public class PrintEffectWeapon implements Serializable {

    public static void printLockRifleBaseEffect(RemoteGameModel gameModel){

        System.out.println("Who is your target? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers());
    }

    public static void printLockRifleSecondLock(RemoteGameModel gameModel){

        System.out.println("Select a different target you can see. (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers());
    }

    public static void printElectroscytheBasicMode(RemoteGameModel gameModel){

    }

    public static void printElectroscytheReaperMode(RemoteGameModel gameModel){

    }

    public static void printThorBasicEffect(RemoteGameModel gameModel){

        System.out.println("Who is your target? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers());
    }

    public static void printThorChainReaction(RemoteGameModel gameModel){

        System.out.println("Select a second target that your first target can see (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers());
    }

    public static void printThorHighVoltage(RemoteGameModel gameModel){

        System.out.println("Select a third target that your second target can see (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers());
    }
}
