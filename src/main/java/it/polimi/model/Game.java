package it.polimi.model;

import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;
    private Player firstPlayer;
    private Turn turn;
    private GameBoard gameBoard;
    private GameState gameState;



    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
