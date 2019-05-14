package it.polimi.view.cli;

import it.polimi.model.*;
import it.polimi.model.Exception.ControllerException.RoudControllerException.SquareNotExistException;

import java.io.Serializable;

public class PrintMenu implements Serializable {

    /**
     * Print menu.
     */
    public static void print(GameModel gameModel){
        System.out.println("\n--------------------------------------------------------");
        System.out.println("WHAT MOVE DO YOU WANT TO MAKE? (PRESS -1 TO ABORT)");
        System.out.println("1) MAKE A MOVE!"); //printSelectMove
        System.out.println("2) VIEW YOUR ATTRIBUTES"); //id, name, position and score (printPlayer)
        System.out.println("3) VIEW YOUR PLAYERBOARD"); //damages, marks and ammo (printPlayerBoard)
        System.out.println("4) VIEW YOUR WEAPONS"); //take weapons from ActualPlayer (printWeapon)
        System.out.println("5) VIEW YOUR POWERUP"); //printPowerUp
        System.out.println("6) VIEW YOUR AMMO"); //only ammo (printAmmo)
        System.out.println("7) VIEW ANOTHER PLAYER'S ATTRIBUTES"); //id, name, position and score (printPlayer)
        System.out.println("8) VIEW ANOTHER PLAYER'S PLAYERBOARD"); //damages, marks and ammo (printPlayerBoard)
        System.out.println("9) VIEW OTHER WEAPONS ON THE MAP"); //take weapons from generation square (printWeapon)
        System.out.println("10) VIEW THE MAP");
        System.out.println("--------------------------------------------------------");

        int choose = 0; //TODO choose da assegnare a una variabile input

        switch (choose){
            case 1:
                PrintSelectMove.print();
                break;
            case 2:
                PrintPlayer.print(gameModel.getActualPlayer());
                break;
            case 3:
                PrintPlayerBoard.print(gameModel.getActualPlayer());
                break;
            case 4:
                PrintWeapon.print(gameModel.getActualPlayer().getPlayerBoard().getPlayerWeapons());
                break;
            case 5:
                PrintPowerUp.print(gameModel.getActualPlayer().getPlayerBoard().getPlayerPowerUps());
                break;
            case 6:
                PrintAmmo.print(gameModel.getActualPlayer());
                break;
            case 7: //passare nome o id
                PrintPlayer.print();
                break;
            case 8: //passare nome o id
                PrintPlayerBoard.print());
                break;
            case 9:
                try {
                    GenerationSquare gs =(GenerationSquare) gameModel.getMap().getGenerationSquare(EnumColorSquare.BLU).
                } catch (SquareNotExistException e) {
                    e.printStackTrace();
                });
                break;
            case 10:
                //TODO
                break;
            default:
                break;

        }
    }
}
