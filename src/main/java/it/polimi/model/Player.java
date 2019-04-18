package it.polimi.model;

public class Player {

    private int id;
    private  String name;
    private EnumColorPlayer color;
    private PlayerBoard playerBoard;
    private Square square;
    private int score;
    private boolean alive;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public EnumColorPlayer getColor() {
        return color;
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

    public void increaseScore(){

    }

    public void checkDeath(){

    }

    public void setColor(EnumColorPlayer color) {
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSquare(Square square) {

        this.square = square;
    }

    public void setAlive(boolean alive) {

        this.alive = alive;
    }


}
