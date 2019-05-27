package it.polimi.view.cli;

import it.polimi.model.GameModel;

import java.io.Serializable;

public class PrintEffectWeapon implements Serializable {

    public static void printLockRifleBaseEffect(GameModel gameModel){

        System.out.println("Which target do you want? (insert player's ID)");
        PrintPlayer.print(gameModel.getPlayers());
    }

    public static void printLockRifleSecondLock(GameModel gameModel){

        System.out.println("Selcet a different target you can see: ");
        PrintPlayer.print(gameModel.getPlayers());
    }
}
