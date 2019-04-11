package it.polimi.model;

public class Player {

    private int id;
    private  String name;
    private EnumColorPlayer color;
    private PlayerBoard playerBoard;
    private Square square;
    private int score;

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


}
