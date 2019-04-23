package it.polimi.model;

public class Player {

    private int id;
    private String name;
    private EnumColorPlayer color;
    private PlayerBoard playerBoard;
    private Square square;
    private int score;
    private boolean alive;

    public Player(int id,String name,EnumColorPlayer color){

        this.id=id;
        this.name=name;
        this.color=color;
        score=0;
        alive=true;
        playerBoard=new PlayerBoard(color);
        // TODO vedere come gestire  questo attributo square in fase di inizializzazione

    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public EnumColorPlayer getColor() {

        return color;
    }

    public PlayerBoard getPlayerBoard() {

        return playerBoard;
    }

    public int getScore() {

        return score;
    }

    public Square getSquare() {

        return square;
    }

    public boolean isAlive() {

        return alive;
    }

    public void increaseScore(int scoreToAdd){

        this.score+=scoreToAdd;

    }

    public void checkDeath(){

        //TODO vedere come gestire la morte del giocatore, mettere questo metodo insieme all'attributo vita di un giocatore?

    }

    public void setSquare(Square square) {

        this.square = square;
    }

    public void setAlive(boolean alive) {

        this.alive = alive;
    }


}
