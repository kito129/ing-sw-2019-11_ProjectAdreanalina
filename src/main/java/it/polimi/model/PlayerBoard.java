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
     * Instantiates a new Player board, setting ammo, board value and number of deaths to the start value.
     * Instantiates two ArrayList of Color player for the damages and marks, an ArrayList of weapon card
     * and an ArrayList of power up card.
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
     * Calls the methods increaseAmmo to modify the value of ammo and, if the drawn card has a power up,
     * calls addPowerUp to add a new power up card in the player power up.
     *
     * @param ammoCard the ammo card drawn by the player
     */
    public void catchAmmoCard(AmmoCard ammoCard) {

        increaseAmmo(ammoCard.getAmmoY(), ammoCard.getAmmoR(), ammoCard.getAmmoB());
        if(ammoCard.getPowerUpCard()!=null) {

            addPowerUp(ammoCard.getPowerUpCard());
        }
    }

    /**
     * Increase the value of ammo in the player board up to maximum of three for each field.
     *
     * @param yellowAmmo the number of yellow ammo to add.
     * @param redAmmo the number of red ammo to add.
     * @param bluAmmo the number of blu ammo to add.
     */

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
     * Decrease the value of ammo in the player board.
     *
     * @param ammoY the number of yellow ammo to remove.
     * @param ammoR the number of red ammo to remove.
     * @param ammoB the number of blu ammo to remove.
     */
    public void decreaseAmmo(int ammoY, int ammoR, int ammoB) {

        this.ammoY -= ammoY;
        this.ammoR -= ammoR;
        this.ammoB -= ammoB;
        //TODO vedere se inserire qui il controllo per il decremento, il decremento è possibile? senno solleva eccezione
    }
    
    /**
     * Increase by one the number of deaths.
     */
    public void increaseNumberOfDeaths() {

        numberOfDeaths += 1;
    }
    
    /**
     * Decrease the value of board depending on the number of deaths of the player.
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

    /**
     * Add a new power up card in player power up.
     *
     * @param powerUpCard the power up card to add in player power up.
     */

    private void addPowerUp(PowerUpCard powerUpCard) {

        playerPowerUps.add(powerUpCard);
    }
    
    /**
     * Remove power up card from player power up.
     *
     * @param powerUpCard the power up card to delete from player power up.
     */

    public void removePowerUp(PowerUpCard powerUpCard) {

        playerPowerUps.remove(powerUpCard);
    }
    
    /**
     * Add a new weapon card in player weapons.
     *
     * @param weaponCard the weapon card to add in player weapons.
     */
    public void addWeapon(WeaponCard weaponCard) {

        playerWeapons.add(weaponCard);
    }
    
    /**
     * Remove weapon card from player weapons.
     *
     * @param weaponCard the weapon card to delete from player power up.
     */
    public void removeWeapon(WeaponCard weaponCard) {

        playerWeapons.remove(weaponCard);
    }
    
    /**
     * Increase damages adding a single damage .
     *
     * @param damage color of the player who did the damage.
     */
    public void increaseDamage(EnumColorPlayer damage) {

        this.damages.add(damage);
    }
    
    /**
     * Increase damages adding multiple damage.
     *
     * @param damages list of color of the player who did the damage.
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
     * Increase mark adding a single mark.
     *
     * @param mark color of the player who made a mark.
     */

    public void increaseMark(EnumColorPlayer mark){

        this.marks.add(mark);
    }

    /**
     * Increase marks adding multiple marks.
     *
     * @param marks list of color of the player who made the marks
     */

    public void increaseMark(ArrayList<EnumColorPlayer> marks) {

        this.marks.addAll(marks);
    }

    /**
     * Remove from marks all the occurrences of a color.
     *
     * @param colorOfMark the color we want to delete the occurrences.
     * @return List of color of all occurrences deleted.
     */

    private ArrayList<EnumColorPlayer> removeMarkOfColor(EnumColorPlayer colorOfMark) {

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
     * Uses the function increase damage and remove mark of color to remove marks from the list of marks
     * and add these in the list of damages.
     *
     * @param colorOfMark color of mark to shift from the list of mark to the list of damages
     */

    public void shiftMarks(EnumColorPlayer colorOfMark){

        increaseDamage(removeMarkOfColor(colorOfMark));
    }

    /**
     * Verify if a color is present in the list of marks.
     *
     * @param color color we want to know if is present.
     * @return true if the color is present, false otherwise.
     */

    public boolean isColorInMarks(EnumColorPlayer color) {

        for (EnumColorPlayer c : marks) {

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
     * Count the number of occurrences of a color in the list of damages.
     *
     * @param colorPlayer color we want to know the number of occurrences.
     * @return the number of occurrences of the given color.
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
     * Count the number of occurrences of a color in the list of marks.
     *
     * @param colorPlayer color we want to know the number of occurrences.
     * @return the number of occurrences of the given color.
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


