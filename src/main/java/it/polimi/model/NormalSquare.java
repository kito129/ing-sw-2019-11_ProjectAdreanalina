package it.polimi.model;

import java.util.ArrayList;

public class NormalSquare extends Square {

    private AmmoCard ammoCard;

    public NormalSquare(int r, int c, EnumColorSquare color, ArrayList<Square> link) {
<<<<<<< HEAD
        this.row=r;
        this.column=c;
        this.color=color;
        link=new ArrayList<>();
        setLink(link);
        this.visited=false;
        this.ammoCard= new AmmoCard(0,0,0,null);
=======

       super(r,c,color,link);
       this.ammoCard= new AmmoCard(0,0,0,null);
>>>>>>> 1c37509eba6664022950b3d20d1b1ddce0484c7c
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
