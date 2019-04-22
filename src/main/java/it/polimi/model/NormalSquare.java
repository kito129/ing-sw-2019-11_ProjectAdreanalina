package it.polimi.model;

import java.util.ArrayList;

public class NormalSquare extends Square {

    private AmmoCard ammoCard;

    public NormalSquare(int r, int c, EnumColorSquare color, ArrayList<Square> link) {
        this.row=r;
        this.column=c;
        this.color=color;
        link=new ArrayList<>();
        setLink(link);
        this.visited=false;
        this.ammoCard= new AmmoCard(null,null);
    }

    public AmmoCard getAmmoCard() {
        return ammoCard;
    }

    public void setAmmoCard(AmmoCard ammoCard) {

        this.ammoCard = ammoCard;
    }

    public void removeAmmoCard(){

        this.ammoCard=null;
    }


}
