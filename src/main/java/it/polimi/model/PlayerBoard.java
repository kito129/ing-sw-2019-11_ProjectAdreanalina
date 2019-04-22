package it.polimi.model;

import java.util.ArrayList;

public class PlayerBoard {

    private EnumColorPlayer color;
    private Ammo playerAmmo;
    private int boardValue;
    private int numberOfDeaths;
    private ArrayList<EnumColorPlayer> damages;
    private ArrayList<EnumColorPlayer> marks;
    private ArrayList<WeaponCard> playerWeapons;
    private ArrayList<PowerUpCard> playerPowerUps;

    public PlayerBoard(EnumColorPlayer color) {

        this.color=color;
        playerAmmo=new Ammo(1,1,1);
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

    public Ammo getPlayerAmmo() {
        return playerAmmo;
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
