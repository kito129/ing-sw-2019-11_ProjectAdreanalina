package it.polimi.model;

import java.util.ArrayList;

public class PlayerBoard {

    private EnumColorPlayer color;
    private int ammoY;
    private int ammoR;
    private int ammoB;
    private int boardValue;
    private ArrayList<EnumColorPlayer> damage;
    private ArrayList<EnumColorPlayer> mark;
    private ArrayList<WeaponCard> playerWeapon;
    private ArrayList<PowerUpCard> playerPowerUp;

    public PlayerBoard(EnumColorPlayer color) {

        this.color=color;
        this.ammoY=1;
        this.ammoR=1;
        this.ammoB=1;
        this.boardValue=8;
        this.damage=new ArrayList<EnumColorPlayer>();
        this.mark=new ArrayList<EnumColorPlayer>();
        this.playerWeapon=new ArrayList<WeaponCard>();
        this.playerPowerUp=new ArrayList<PowerUpCard>();
    }

    public EnumColorPlayer getColor() {

        return this.color;
    }

    public int getAmmoY() {

        return this.ammoY;
    }

    public int getAmmoR() {

        return this.ammoR;
    }

    public int getAmmoB() {

        return this.ammoB;
    }

    public int getBoardValue() {

        return this.boardValue;
    }

    public ArrayList<EnumColorPlayer> getDamage() {

        return this.damage;
    }

    public ArrayList<EnumColorPlayer> getMark() {

        return this.mark;
    }

    // TODO getter weapon card e power up card.

    public void increaseAmmo(AmmoCard ammoCard){

        if(this.ammoY+ammoCard.getAmmoY()>3){

            this.ammoY=3;
        }else {

            this.ammoY+=ammoCard.getAmmoY();
        }
        if(this.ammoR+ammoCard.getAmmoY()>3){

            this.ammoR=3;
        }else {

            this.ammoR+=ammoCard.getAmmoR();
        }
        if(this.ammoB+ammoCard.getAmmoB()>3){

            this.ammoB=3;
        }else{

            this.ammoB+=ammoCard.getAmmoB();
        }
    }

    public void decreaseAmmo(int ammoY,int ammoR, int ammoB){

        this.ammoY-=this.ammoY-ammoY;
        this.ammoR-=this.ammoR-ammoR;
        this.ammoB-=this.ammoB-ammoB;
    }

    public void addPowerUp(){
    }

    public void removePowerUp(){

    }

    public void addWeapon(){

    }

    public void removeWeapon(){

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

    public void playerBoardScored(){

        //TODO occhio al valore di ritorno
    }




}
