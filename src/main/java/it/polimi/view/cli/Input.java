package it.polimi.view.cli;

import it.polimi.model.Exception.MapException;
import it.polimi.model.RemoteGameModel;
import it.polimi.model.Square;

import java.io.Serializable;
import java.util.Scanner;

public class Input implements Serializable {

    private static int r;
    private static int c;

    /**
     * Scanner the right target
     * @param gameModel      the gameModel
     */
    public static void TargetPlayer(RemoteGameModel gameModel){

        Scanner input = new Scanner(System.in);

        PrintTarget.print();

        do {

        while (!input.hasNextInt())
            input = new Scanner(System.in);

        } while (input.nextInt()<0 && input.nextInt()>gameModel.getPlayers(false).size()-1 && input.nextInt()==gameModel.getActualPlayer().getId());
    }

    /**
     * Scanner the right target
     * @param gameModel      the gameModel
     */
    public static void TargetSquare(RemoteGameModel gameModel){

        Square target = null;

        while (target==null) {
            Scanner input = new Scanner(System.in);

            PrintSelectMove.printRow();
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            r=(input.nextInt());

            System.out.println();

            PrintSelectMove.printColumn();
            while (!input.hasNextInt())
                input = new Scanner(System.in);
            c=(input.nextInt());

            if(gameModel.getMap().existInMap(r,c)){
                try {
                    target=gameModel.getMap().getSquare(r,c);
                } catch (MapException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}