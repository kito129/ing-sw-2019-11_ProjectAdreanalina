package it.polimi.model;

import java.util.ArrayList;

public class NormalSquare extends Square {

    private AmmoCard ammoCard;

    public NormalSquare(int r, int c, EnumColorSquare color, ArrayList<Square> link) {

       super(r,c,color,link);
       this.ammoCard= new AmmoCard(0,0,0,null);

    }

    public AmmoCard getAmmoCard(){

        return ammoCard;
    }

    public void setAmmoCard(AmmoCard ammoCard) {

        this.ammoCard = ammoCard;
    }

    public void removeAmmoCard(){

        this.ammoCard=null;
    }


}
