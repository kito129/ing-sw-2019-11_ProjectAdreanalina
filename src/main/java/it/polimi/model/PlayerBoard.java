package it.polimi.model;

import java.util.ArrayList;

public class PlayerBoard {

    private int ammoY;
    private int ammoR;
    private int ammoB;
    private int boardValue;
    private int numberOfDeaths;
    private ArrayList<EnumColorPlayer> damages;
    private ArrayList<EnumColorPlayer> marks;
    private ArrayList<WeaponCard> playerWeapons;
    private ArrayList<PowerUpCard> playerPowerUps;

    public PlayerBoard() {

        ammoY = 1;
        ammoR = 1;
        ammoB = 1;
        boardValue = 8;
        numberOfDeaths = 0;
        damages = new ArrayList<EnumColorPlayer>();
        marks = new ArrayList<EnumColorPlayer>();
        playerWeapons = new ArrayList<WeaponCard>();
        playerPowerUps = new ArrayList<PowerUpCard>();
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

    public int getNumberOfDeaths() {

        return numberOfDeaths;
    }

    public ArrayList<EnumColorPlayer> getDamages() {

        return damages;
    }

    public ArrayList<EnumColorPlayer> getMarks() {

        return marks;
    }

    public ArrayList<WeaponCard> getPlayerWeapons() {

        return playerWeapons;
    }

    public ArrayList<PowerUpCard> getPlayerPowerUps() {

        return playerPowerUps;
    }

    public void increaseAmmo(AmmoCard ammoCard) {

        if (this.ammoY + ammoCard.getAmmoY() > 3) {

            this.ammoY = 3;
        } else {

            this.ammoY += ammoCard.getAmmoY();
        }
        if (this.ammoR + ammoCard.getAmmoR() > 3) {

            this.ammoR = 3;
        } else {

            this.ammoR += ammoCard.getAmmoR();
        }
        if (this.ammoB + ammoCard.getAmmoB() > 3) {

            this.ammoB = 3;
        } else {

            this.ammoB += ammoCard.getAmmoB();
        }
    }

    public void decreaseAmmo(int ammoY, int ammoR, int ammoB) {

        this.ammoY -= ammoY;
        this.ammoR -= ammoR;
        this.ammoB -= ammoB;
        //TODO vedere se inserire qui il controllo per il decremento, il decremento è possibile? senno solleva eccezione
    }

    public void increaseNumberOfDeaths() {

        numberOfDeaths += 1;
    }

    public void decreaseBoardValue() {

        if (numberOfDeaths == 0) {

            boardValue = 8;
        } else if (numberOfDeaths == 1) {

            boardValue = 6;
        } else if (numberOfDeaths == 2) {

            boardValue = 4;
        } else if (numberOfDeaths == 3) {

            boardValue = 2;
        } else if (numberOfDeaths == 4) {

            boardValue = 1;
        }
    }

    public void addPowerUp(PowerUpCard powerUpCard) {

        playerPowerUps.add(powerUpCard);
    }

    public void removePowerUp(PowerUpCard powerUpCard) {

        playerPowerUps.remove(powerUpCard);
    }

    public void addWeapon(WeaponCard weaponCard) {

        playerWeapons.add(weaponCard);
    }

    public void removeWeapon(WeaponCard weaponCard) {

        playerWeapons.remove(weaponCard);
    }

    public void increaseDamage(ArrayList<EnumColorPlayer> damages) {

        this.damages.addAll(damages);
    }

    public void resetDamage() {

        damages.clear();
    }

    public void increaseMark(ArrayList<EnumColorPlayer> marks) {

        this.marks.addAll(marks);
    }

    public ArrayList<EnumColorPlayer> removeMarkOfColor(EnumColorPlayer colorOfMark) {

        ArrayList<EnumColorPlayer> markToAdd = new ArrayList<EnumColorPlayer>();

        for (int i = 0; i < marks.size(); i++) {

            if (marks.get(i) == colorOfMark) {

                markToAdd.add(marks.get(i));
                marks.remove(i);
                i--;
            }
        }
        return markToAdd;
    }


   /*  public void removeMarkOfColor1(EnumColorPlayer colorOfMark) {

        for (EnumColorPlayer color:marks) {

            if (color==colorOfMark) {


                marks.remove(color);

                marks.remove(colorOfMark);

            }
        }
    }

    */

    public void avaibleAmmo() {

        System.out.println(ammoY + " Yellow Ammo");
        System.out.println(ammoR + " Red Ammo");
        System.out.println(ammoB + " Blu Ammo ");
    }

    public int playerBoardScored(EnumColorPlayer colorPlayer) {

        int count = 0;
        for (EnumColorPlayer color : damages) {

            if (color == colorPlayer) {

                count++;
            }
        }
        if(damages.get(0)==colorPlayer){

            count ++;
        }

        return count;
    }
}
