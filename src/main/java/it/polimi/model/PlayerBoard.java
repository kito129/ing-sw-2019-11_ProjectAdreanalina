package it.polimi.model;

import it.polimi.model.Exception.*;

import java.util.ArrayList;

/**
 * The type Player board.
 */
public class PlayerBoard {

    private ArrayList<EnumColorCardAndAmmo> ammo;
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

        ammo = new ArrayList<EnumColorCardAndAmmo>();
        ammo.add(EnumColorCardAndAmmo.YELLOW);
        ammo.add(EnumColorCardAndAmmo.RED);
        ammo.add(EnumColorCardAndAmmo.BLU);
        boardValue = 8;
        numberOfDeaths = 0;
        damages = new ArrayList<EnumColorPlayer>();
        marks = new ArrayList<EnumColorPlayer>();
        playerWeapons = new ArrayList<WeaponCard>();
        playerPowerUps = new ArrayList<PowerUpCard>();
    }

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
     * Increases by one the number of deaths of the player.
     */
    public void increaseNumberOfDeaths() {

        this.numberOfDeaths += 1;
    }

    /**
     * Decreases the value of board depending of the number of deaths of the player.
     */
    public void decreaseBoardValue() {

        if (numberOfDeaths == 0) {

            this.boardValue = 8;
        } else if (numberOfDeaths == 1) {

            this.boardValue = 6;
        } else if (numberOfDeaths == 2) {

            this.boardValue = 4;
        } else if (numberOfDeaths == 3) {

            this.boardValue = 2;
        } else if (numberOfDeaths == 4) {

            this.boardValue = 1;
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
     *
     * @param colorOfDamage damage of the player's color who did the damage.
     */
    public void increaseDamages(EnumColorPlayer colorOfDamage) {

        this.damages.add(colorOfDamage);
    }

    /**
     * Increases damages of the damaged player, adding multiple damages of the player's color who did the damages.
     *
     * @param colorOfDamages list of damages of the player's color who did the damage.
     */
    public void increaseDamages(ArrayList<EnumColorPlayer> colorOfDamages) {

        this.damages.addAll(colorOfDamages);
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
     * @param colorOfMark mark of the player's color who made a mark.
     */
    public void increaseMarks(EnumColorPlayer colorOfMark) {

        if (colorOccurrenceInMarks(colorOfMark) < 3) {

            this.marks.add(colorOfMark);
        }
    }

    /**
     * Increases marks of the marked player, adding multiple marks of the player's color who made the marks.
     * The marks of the marked player can be increased until reaching the maximum value of three for each colors.
     *
     * @param colorOfMarks list of marks of the player's color who made the marks.
     */
    public void increaseMarks(ArrayList<EnumColorPlayer> colorOfMarks) {

        if (colorOccurrenceInMarks(colorOfMarks.get(0)) + colorOfMarks.size() > 3) {

            while (colorOccurrenceInMarks(colorOfMarks.get(0)) < 3) {

                this.marks.add(colorOfMarks.get(0));
            }
        } else {

            this.marks.addAll(colorOfMarks);
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


    
    public ArrayList<WeaponCard> getWeaponToCharge(){

        ArrayList<WeaponCard> tempWeapon = new ArrayList<>();
        for (WeaponCard a:playerWeapons){

            if(!a.isCharge){

                tempWeapon.add(a);
            }
        }
        return tempWeapon;
    }




   //todo da qua metodi che forse non servono





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
     * Verifies if one color is present in the list of marks.
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


}


