package it.polimi.model;

public class Map {

    private Square[][] squares;

    public void getSquare (int row, int colomn){

        // TODO metodo che ritorna il riferimento di una sola square indicizzata da r e c
        // il valore di ritorno non sarà void ma Square
    }

    public boolean isReachable(int row, int column){

        return true;

        //  TODO funzione di raggiungibilitò in massimo 3 passi

    }

    public boolean isVisible(int row, int column){

        return true;

        //  TODO funzione di visibiltà

    }

    public void findPlayer(Player player){

        //TODO FUNZIONE CHE RITORNA LA SQUARE IN CUI SI TROVA UN CERTO GIOCATORE
        // valore di ritorno è una square


    }

    public void playersOnSquare(int row, int column){

        // TODO funziona che ritorna arrey list di player su una certa square
    }

    public void spawnPlayer(){

    }

    public void movePlayer(){

    }

    public void addPlayerOnSquare(){

    }

    public void removePlayerFromSquare(){

    }

}
