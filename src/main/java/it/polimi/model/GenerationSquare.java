package it.polimi.model;

import java.util.ArrayList;

public class GenerationSquare extends Square{

    private ArrayList<WeaponCard> weaponList;

    public GenerationSquare(int r, int c, EnumColorSquare color, ArrayList<Square> link) {

        super(r,c,color,link);
        this.weaponList=null;
    }

    public ArrayList<WeaponCard> getWeaponList() {

        return weaponList;
    }

    public void addWeaponCard(WeaponCard weaponCard){

        if(weaponList.size()<=3){

            this.weaponList.add(weaponCard);
        }
    }

    public WeaponCard catchWeapon(int pos){
        WeaponCard temp=this.weaponList.get(pos);
        this.weaponList.remove(pos);
        return temp;
    }


}
