package it.polimi.model;

import java.util.ArrayList;

public class PlayerBoard {

    private EnumColorPlayer color;
    private int ammoY;
    private int ammoR;
    private int ammoB;
    private int boardValue;
    private int numberOfDeaths;
    private ArrayList<EnumColorPlayer> damages;
    private ArrayList<EnumColorPlayer> marks;
    private ArrayList<WeaponCard> playerWeapons;
    private ArrayList<PowerUpCard> playerPowerUps;

    public PlayerBoard(EnumColorPlayer color) {

        this.color=color;
        ammoY=1;
        ammoR=1;
        ammoB=1;
        boardValue=8;
        numberOfDeaths=0;
        damages =new ArrayList<EnumColorPlayer>();
        marks =new ArrayList<EnumColorPlayer>();
        playerWeapons =new ArrayList<WeaponCard>();
        playerPowerUps =new ArrayList<PowerUpCard>();
    }

    public EnumColorPlayer getColor() {

        return color;
    }

    public int getAmmoY() {

        return ammoY;
    }

    public int getAmmoR() {

        return ammoR;
    }

    public int getAmmoB() {

        return ammoB;
    }

    public int getBoardValue() {

        return boardValue;
    }

    public ArrayList<EnumColorPlayer> getDamages() {

        return damages;
    }

    public ArrayList<EnumColorPlayer> getMarks() {

        return marks;
    }

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

    public void increaseNumberOfDeaths(){

        numberOfDeaths+=1;
    }

    public void addPowerUp(PowerUpCard powerUpCard){

        playerPowerUps.add(powerUpCard);
    }

    public void removePowerUp(PowerUpCard powerUpCard){

        playerPowerUps.remove(powerUpCard);
    }

    public void addWeapon(WeaponCard weaponCard){

        playerWeapons.add(weaponCard);
    }

    public void removeWeapon(WeaponCard weaponCard){

        playerWeapons.remove(weaponCard);
    }

    public void increaseDamage(ArrayList<EnumColorPlayer> damages){

        this.damages.addAll(damages);
    }

    public void resetDamage(){

        damages.clear();
    }

    public void increaseMark(ArrayList<EnumColorPlayer> marks){

        this.marks.addAll(marks);
    }

    public void removeMarkOfColor(EnumColorPlayer colorOfMark){

        marks.removeIf(mark-> mark==colorOfMark);
    }

    public void decreaseBoardValue(){

        if(numberOfDeaths==0){

            boardValue=8;
        }else if(numberOfDeaths==1){

            boardValue=6;
        }else if(numberOfDeaths==2){

            boardValue=4;
        }else if(numberOfDeaths==3){

            boardValue=2;
        }else if(numberOfDeaths==4){

            boardValue=1;
        }
    }

    public void playerBoardScored(){

        //TODO occhio al valore di ritorno
    }




}
