package it.polimi.model;

import java.util.ArrayList;

public class PlayerBoard {

    private EnumColorPlayer color;
    private int ammoY;
    private int ammoR;
    private int ammoB;
    private ArrayList<EnumColorPlayer> damage;
    private ArrayList<EnumColorPlayer> mark;
    private int boardValue;


    public PlayerBoard(){
        //TODO
    }

    public EnumColorPlayer getColor() {
        return color;
    }

    public int getAmmoR() {
        return ammoR;
    }

    public int getAmmoB() {
        return ammoB;
    }

    public int getAmmoY() {
        return ammoY;
    }

    public ArrayList<EnumColorPlayer> getDamage() {
        return damage;
    }

    public ArrayList<EnumColorPlayer> getMark() {
        return mark;
    }

    public int getBoardValue() {
        return boardValue;
    }

    public void increaseAmmo(AmmoCard ammoCard){

        //TODO fare metodo incrementa ammo

    }

    public void decreaseAmmo(AmmoCard ammoCard){

        //TODO fare metodo diminunisci ammo

    }

    public void increaseDamage(ArrayList<EnumColorPlayer> damage){

        // TODO
    }

    public void resetDamage(){

        // TODO
    }

    public void increaseMark(ArrayList<EnumColorPlayer> mark){

        // TODO
    }

    public void decreseMark(ArrayList<EnumColorPlayer> mark){

        // TODO
    }

    public void decreaseBoardValue(){

        // TODO
    }

    public void PlayerBoardScored(){

        //TODO occhio al valore di ritorno
    }




}
