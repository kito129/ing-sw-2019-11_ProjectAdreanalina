package it.polimi.model;

public class Square {
    private ColorCard color;
    private int posX;
    private int getPosY;
    private Square portToSquare;

    public ColorCard getColor() {
        return color;
    }

    public int getGetPosY() {
        return getPosY;
    }

    public int getPosX() {
        return posX;
    }

    public Square getPortToSquare() {
        return portToSquare;
    }

    public void setGetPosY(int getPosY) {
        this.getPosY = getPosY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
}
