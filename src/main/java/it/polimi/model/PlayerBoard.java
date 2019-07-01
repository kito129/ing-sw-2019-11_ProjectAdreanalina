package it.polimi.model;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Player board.
 */
public class PlayerBoard implements Serializable {

    private ArrayList<EnumColorCardAndAmmo> ammo;
    private int boardValue;
    private ArrayList<EnumColorPlayer> damages;
    private ArrayList<EnumColorPlayer> marks;
    private ArrayList<WeaponCard> playerWeapons;
    private ArrayList<PowerUpCard> playerPowerUps;
    
    /**
     * Instantiates a new Player board, setting ammo, board value to the start value.
     * Instantiates two ArrayList of Color player for the damages and marks, an ArrayList of weapon card
     * and an ArrayList of power up card.
     */
    public PlayerBoard() {

        ammo = new ArrayList<EnumColorCardAndAmmo>();
        ammo.add(EnumColorCardAndAmmo.YELLOW);
        ammo.add(EnumColorCardAndAmmo.RED);
        ammo.add(EnumColorCardAndAmmo.BLU);
        ammo.add(EnumColorCardAndAmmo.BLU);
        boardValue = 8;
        damages = new ArrayList<EnumColorPlayer>();
        marks = new ArrayList<EnumColorPlayer>();
        playerWeapons = new ArrayList<WeaponCard>();
        playerPowerUps = new ArrayList<PowerUpCard>();
        
    }
    
    /**
     * Gets ammo.
     *
     * @return the ammo
     */
    public ArrayList<EnumColorCardAndAmmo> getAmmo() {

        return ammo;
    }
    
    /**
     * Gets ammo y.
     *
     * @return the ammo y
     */
    public ArrayList<EnumColorCardAndAmmo> getAmmoY() {

        ArrayList<EnumColorCardAndAmmo> yellowAmmo=new ArrayList<>();
        for(EnumColorCardAndAmmo ammo: this.ammo){

            if(ammo==EnumColorCardAndAmmo.YELLOW){

                yellowAmmo.add(EnumColorCardAndAmmo.YELLOW);
            }
        }
        return yellowAmmo;
    }
    
    /**
     * Gets ammo r.
     *
     * @return the ammo r
     */
    public ArrayList<EnumColorCardAndAmmo> getAmmoR() {

        ArrayList<EnumColorCardAndAmmo> redAmmo=new ArrayList<>();
        for(EnumColorCardAndAmmo ammo: this.ammo){

            if(ammo==EnumColorCardAndAmmo.RED){

                redAmmo.add(EnumColorCardAndAmmo.RED);
            }
        }
        return redAmmo;
    }
    
    /**
     * Gets ammo b.
     *
     * @return the ammo b
     */
    public ArrayList<EnumColorCardAndAmmo> getAmmoB() {

        ArrayList<EnumColorCardAndAmmo> bluAmmo=new ArrayList<>();
        for(EnumColorCardAndAmmo ammo: this.ammo){

            if(ammo==EnumColorCardAndAmmo.BLU){

                bluAmmo.add(EnumColorCardAndAmmo.BLU);
            }
        }
        return bluAmmo;
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
     * Manages the draft of ammo card, increasing the fields of ammo and adding to the list of power up, if is present,
     * the power up shown in the ammo card drawn
     *
     * @param ammoCard the ammo card drawn by the player.
     */
    public void manageAmmoCard(AmmoCard ammoCard) {

        if (ammoCard.getPowerUpCard() != null) {

            addPowerUp(ammoCard.getPowerUpCard());
        }
        increaseAmmo(ammoCard.getAmmo());
    }

    /**
     * Adds a new power up card in player power up.
     *
     * @param powerUpCard the power up card to add in player power up.
     */
    public void addPowerUp(PowerUpCard powerUpCard) {

        this.playerPowerUps.add(powerUpCard);
    }
    
    /**
     * Removes a power up card from player power up.
     *
     * @param powerUpCard the power up card to delete from player power up.
     */
    public void removePowerUp(PowerUpCard powerUpCard) {

        this.playerPowerUps.remove(powerUpCard);
    }

    /**
     * Increases the value of ammo in the player board up to maximum of three for each field.
     *
     * @param ammoToAdd list of colored ammo will be add.
     */
    private void increaseAmmo(ArrayList<EnumColorCardAndAmmo> ammoToAdd) {

        for(EnumColorCardAndAmmo ammo:ammoToAdd){

            if(ammo==EnumColorCardAndAmmo.YELLOW && getAmmoY().size()<3){

                this.ammo.add(EnumColorCardAndAmmo.YELLOW);
            }
            if(ammo==EnumColorCardAndAmmo.RED && getAmmoR().size()<3){

                this.ammo.add(EnumColorCardAndAmmo.RED);
            }
            if(ammo==EnumColorCardAndAmmo.BLU && getAmmoB().size()<3){

                this.ammo.add(EnumColorCardAndAmmo.BLU);
            }
        }
    }
    
    /**
     * Decreases the value of ammo in the player board.
     *
     * @param ammoToDecrease colored ammo will be removed.
     */
    public void decreaseAmmo(EnumColorCardAndAmmo ammoToDecrease) {

        this.ammo.remove(ammoToDecrease);
    }
    
    /**
     * Decreases the value of ammo in the player board.
     *
     * @param ammoToDecrease list of colored ammo will be removed.
     */
    public void decreaseAmmo(ArrayList<EnumColorCardAndAmmo> ammoToDecrease){

        for (EnumColorCardAndAmmo ammo:ammoToDecrease) {

            if (ammo == EnumColorCardAndAmmo.YELLOW) {

                this.ammo.remove(EnumColorCardAndAmmo.YELLOW);
            }
            if (ammo == EnumColorCardAndAmmo.RED) {

                this.ammo.remove(EnumColorCardAndAmmo.RED);
            }
            if(ammo==EnumColorCardAndAmmo.BLU){

                this.ammo.remove(EnumColorCardAndAmmo.BLU);
            }
        }
    }

    /**
     * Decreases the value of board depending of the number of deaths of the player.
     */
    public void decreaseBoardValue() {

        if (boardValue == 2) {

            boardValue = 1;
        } else if (boardValue != 1) {

            boardValue -= 2;
        }
    }



    /**
     * Adds a new weapon card in player weapons.
     *
     * @param weaponCard the weapon card to add in player weapons.
     */
    public void addWeapon(WeaponCard weaponCard) {

        this.playerWeapons.add(weaponCard);
    }
    
    /**
     * Removes weapon card from player weapons.
     *
     * @param weaponCard the weapon card to delete from player power up.
     */
    public void removeWeapon(WeaponCard weaponCard) {

        this.playerWeapons.remove(weaponCard);
    }
    
    /**
     * Increases damages of the damaged player, adding a single damage of the player's color who did the damage.
     * The list of damages can grow up to a maximum of 12 damage.
     *
     * @param damage damage of the player's color who did the damage.
     */
    public void increaseDamages(EnumColorPlayer damage) {

        if(this.damages.size()<12){

            this.damages.add(damage);
        }
    }
    
    /**
     * Increases damages of the damaged player, adding multiple damages of the player's color who did the damages.
     * The list of damages can grow up to a maximum of 12 damage.
     *
     * @param damages list of damages of the player's color who did the damage.
     */
    public void increaseDamages(ArrayList<EnumColorPlayer> damages) {

        for (EnumColorPlayer color : damages) {

            if (this.damages.size() < 12) {

                this.damages.add(color);
            }
        }
        
    }
    
    /**
     * Resets damage in the player board.
     */
    public void resetDamage() {

        this.damages.clear();
    }
    
    /**
     * Increases marks of the marked player, adding a single mark of the player's color who made the mark.
     * Increases marks of the marked player only if, the number of marks of the specified color(the parameter's color), is less then three.
     *
     * @param mark mark of the player's color who made a mark.
     */
    public void increaseMarks(EnumColorPlayer mark) {

        if (colorOccurrenceInMarks(mark) < 3) {

            this.marks.add(mark);
        }
    }
    
    /**
     * Increases marks of the marked player, adding multiple marks of the player's color who made the marks.
     * The marks of the marked player can be increased until reaching the maximum value of three for each colors.
     *
     * @param marks list of marks of the player's color who made the marks.
     */
    public void increaseMarks(ArrayList<EnumColorPlayer> marks) {

        if(marks.size()!=0){

            EnumColorPlayer colorOfMarks= marks.get(0);

            if (colorOccurrenceInMarks(colorOfMarks) + marks.size() > 3) {

                while (colorOccurrenceInMarks(colorOfMarks) < 3) {

                    this.marks.add(colorOfMarks);
                }
            } else {

                this.marks.addAll(marks);
            }
        }
    }

    /**
     * Count the number of occurrences of a color in the list of marks.
     *
     * @param colorPlayer color we want to know the number of occurrences.
     * @return the number of occurrences of the given color.
     */
    private int colorOccurrenceInMarks(EnumColorPlayer colorPlayer) {

        int count = 0;
        for (EnumColorPlayer color : marks) {

            if (color == colorPlayer) {

                count++;
            }
        }
        return count;
    }

    /**
     * Removes from the player's marks all the occurrences of one color.
     *
     * @param colorOfMark the color we want to delete the occurrences.
     * @return List of all marks deleted.
     */
    private ArrayList<EnumColorPlayer> removeMarkOfColor(EnumColorPlayer colorOfMark) {

        ArrayList<EnumColorPlayer> marksRemoved = new ArrayList<EnumColorPlayer>();
        for (int i = 0; i < marks.size(); i++) {

            if (marks.get(i) == colorOfMark) {

                marksRemoved.add(marks.get(i));
                marks.remove(i);
                i--;
            }
        }
        return marksRemoved;
    }
    
    /**
     * Shifts all the occurrences of one color from the list of marks, to the list of damages.
     *
     * @param colorOfMark color of marks to shift from the list of marks to the list of damages.
     */
    public void shiftMarks(EnumColorPlayer colorOfMark) {

        increaseDamages(removeMarkOfColor(colorOfMark));
    }
    
    /**
     * Get all weapon that is not charge.
     *
     * @return all weapon that is not charge.
     */
    public ArrayList<WeaponCard> getWeaponToCharge(){

        ArrayList<WeaponCard> tempWeapon = new ArrayList<>();
        for (WeaponCard a:this.playerWeapons){

            if(!a.isCharge()){

                tempWeapon.add(a);
            }
        }
        return tempWeapon;
    }
    
    
    /**
     * Counts the number of occurrences of one color in the list of damages.
     *
     * @param colorPlayer color we want to know the number of occurrences.
     * @return the number of occurrences of the given color.
     */
    public int colorOccurrenceInDamages(EnumColorPlayer colorPlayer) {

        int count = 0;
        for (EnumColorPlayer color : damages) {

            if (color == colorPlayer) {

                count++;
            }
        }
        return count;
    }

    /**
     * Get first occurrence in damage integer.
     *
     * @param color the color of the player
     * @return index of the first occurrence, null if not found
     */
    public Integer getFirstOccurrenceInDamage (EnumColorPlayer color){

        return damages.indexOf(color);
    }



/*

//todo metodi non più utili


    
    
    /**
     * Verifies if one color is present in the list of marks.
     *
     * @param color color we want to know if is present.
     * @return true if the color is present, false otherwise.

    public boolean isColorInMarks(EnumColorPlayer color) {

        for (EnumColorPlayer c : marks) {

            if (c == color) {

                return true;
            }
        }
        return false;
    }
    
    
    
    // todo chiede al prof perche non va.
    public ArrayList<EnumColorPlayer> removeMarkOfColor1(EnumColorPlayer colorOfMark) {

        ArrayList<EnumColorPlayer> marksRemoved = new ArrayList<EnumColorPlayer>();
        for (EnumColorPlayer color : marks) {

            if (color == colorOfMark) {

                marksRemoved.add(color);
                marks.remove(color);
            }
        }
        return marksRemoved;
    }

 */

    
}


