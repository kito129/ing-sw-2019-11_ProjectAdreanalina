package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Player board.
 */
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
    
    /**
     * Instantiates a new Player board.
     */
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
    
    /**
     * Gets ammo y.
     *
     * @return the ammo y
     */
    public int getAmmoY() {

        return ammoY;
    }
    
    /**
     * Gets ammo r.
     *
     * @return the ammo r
     */
    public int getAmmoR() {

        return ammoR;
    }
    
    /**
     * Gets ammo b.
     *
     * @return the ammo b
     */
    public int getAmmoB() {

        return ammoB;
    }
    
    /**
     * Gets board value.
     *
     * @return the board value
     */
    public int getBoardValue() {

        return boardValue;
    }
    
    /**
     * Gets number of deaths.
     *
     * @return the number of deaths
     */
    public int getNumberOfDeaths() {

        return numberOfDeaths;
    }
    
    /**
     * Gets damages.
     *
     * @return the damages
     */
    public ArrayList<EnumColorPlayer> getDamages() {

        return damages;
    }
    
    /**
     * Gets marks.
     *
     * @return the marks
     */
    public ArrayList<EnumColorPlayer> getMarks() {

        return marks;
    }
    
    /**
     * Gets player weapons.
     *
     * @return the player weapons
     */
    public ArrayList<WeaponCard> getPlayerWeapons() {

        return playerWeapons;
    }
    
    /**
     * Gets player power ups.
     *
     * @return the player power ups
     */
    public ArrayList<PowerUpCard> getPlayerPowerUps() {

        return playerPowerUps;
    }
    
    /**
     * Catch ammo card.
     *
     * @param ammoCard the ammo card
     */
    public void catchAmmoCard(AmmoCard ammoCard) {

        increaseAmmo(ammoCard.getAmmoY(), ammoCard.getAmmoR(), ammoCard.getAmmoB());
        addPowerUp(ammoCard.getPowerUpCard());
    }

    private void increaseAmmo(int yellowAmmo, int redAmmo, int bluAmmo) {

        if (this.ammoY + yellowAmmo > 3) {

            this.ammoY = 3;
        } else {

            this.ammoY += yellowAmmo;
        }
        if (this.ammoR + redAmmo > 3) {

            this.ammoR = 3;
        } else {

            this.ammoR += redAmmo;
        }
        if (this.ammoB + bluAmmo > 3) {

            this.ammoB = 3;
        } else {

            this.ammoB += bluAmmo;
        }
    }
    
    /**
     * Decrease ammo.
     *
     * @param ammoY the ammo y
     * @param ammoR the ammo r
     * @param ammoB the ammo b
     */
    public void decreaseAmmo(int ammoY, int ammoR, int ammoB) {

        this.ammoY -= ammoY;
        this.ammoR -= ammoR;
        this.ammoB -= ammoB;
        //TODO vedere se inserire qui il controllo per il decremento, il decremento è possibile? senno solleva eccezione
    }
    
    /**
     * Increase number of deaths.
     */
    public void increaseNumberOfDeaths() {

        numberOfDeaths += 1;
    }
    
    /**
     * Decrease board value.
     */
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

    private void addPowerUp(PowerUpCard powerUpCard) {

        playerPowerUps.add(powerUpCard);
    }
    
    /**
     * Remove power up.
     *
     * @param powerUpCard the power up card
     */
    public void removePowerUp(PowerUpCard powerUpCard) {

        playerPowerUps.remove(powerUpCard);
    }
    
    /**
     * Add weapon.
     *
     * @param weaponCard the weapon card
     */
    public void addWeapon(WeaponCard weaponCard) {

        playerWeapons.add(weaponCard);
    }
    
    /**
     * Remove weapon.
     *
     * @param weaponCard the weapon card
     */
    public void removeWeapon(WeaponCard weaponCard) {

        playerWeapons.remove(weaponCard);
    }
    
    /**
     * Increase damage.
     *
     * @param damage the damage
     */
    public void increaseDamage(EnumColorPlayer damage) {

        this.damages.add(damage);
    }
    
    /**
     * Increase damage.
     *
     * @param damages the damages
     */
    public void increaseDamage(ArrayList<EnumColorPlayer> damages) {

        this.damages.addAll(damages);
    }
    
    /**
     * Reset damage.
     */
    public void resetDamage() {

        damages.clear();
    }
    
    /**
     * Increase mark.
     *
     * @param marks the marks
     */
    public void increaseMark(ArrayList<EnumColorPlayer> marks) {

        this.marks.addAll(marks);
    }
    
    /**
     * Update mark of color array list.
     *
     * @param colorOfMark the color of mark
     * @return the array list
     */
    public ArrayList<EnumColorPlayer> updateMarkOfColor(EnumColorPlayer colorOfMark) {

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
    
    /**
     * Is color in marks boolean.
     *
     * @param color the color
     * @return the boolean
     */
    public boolean isColorInMarks(EnumColorPlayer color) {

        for (EnumColorPlayer c : damages) {

            if (c == color) {

                return true;
            }
        }
        return false;
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
    
    /**
     * Color occurence in damages int.
     *
     * @param colorPlayer the color player
     * @return the int
     */
    public int colorOccurenceInDamages(EnumColorPlayer colorPlayer) {

        int count = 0;
        for (EnumColorPlayer color : damages) {

            if (color == colorPlayer) {

                count++;
            }
        }
        if (damages.get(0) == colorPlayer) {      // todo questo pezza non va messo qui...va messo quando si calcolano i punti dela plancia, qui si contano solo le occorrenze

            count++;
        }
        return count;
    }
    
    /**
     * Color occurence in marks int.
     *
     * @param colorPlayer the color player
     * @return the int
     */
    public int colorOccurenceInMarks(EnumColorPlayer colorPlayer) {

        int count = 0;
        for (EnumColorPlayer color : marks) {

            if (color == colorPlayer) {

                count++;
            }
        }
        return count;
    }


}


