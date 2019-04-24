package it.polimi.model;


import java.util.ArrayList;

public class Turn {


    private Player currentPlayer;

    private ArrayList<Player> deadPlayers;



    public Player getCurrentPlayer() {

        return currentPlayer;
    }




    public ArrayList<Player> getDeadPlayers() {
        return deadPlayers;
    }

    public void setCurrentPlayer(Player currentPlayer) {

        this.currentPlayer = currentPlayer;
    }



    public void addDeadPlayer(Player player){

        //TODo
    }

    public void removeDeadPlayer(Player player){

        //TODO
    }
}
