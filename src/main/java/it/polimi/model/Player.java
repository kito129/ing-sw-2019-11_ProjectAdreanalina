package it.polimi.model;

public class Player {

    private int id;
    private String name;
    private EnumColorPlayer color;
    private PlayerBoard playerBoard;
    private int row;
    private int column;
    private int score;
    private boolean alive;

    public Player(int id,String name,EnumColorPlayer color){

        this.id=id;
        this.name=name;
        this.color=color;
        score=0;
        alive=true;
        playerBoard=new PlayerBoard();
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

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
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

    private void setColumn(int column) {
        this.column = column;
    }

    private void setRow(int row) {
        this.row = row;
    }

    public void setPosition(int r, int c) {
        this.setRow(r);
        this.setColumn(c);
    }

    public void setAlive(boolean alive) {

        this.alive = alive;
    }


}
