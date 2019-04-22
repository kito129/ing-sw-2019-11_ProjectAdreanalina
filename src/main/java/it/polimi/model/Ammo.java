package it.polimi.model;

public class Ammo {
    private int red;
    private int blu;
    private int yellow;


    public Ammo(int red,int blu, int yellow){
        this.blu=blu;
        this.red=red;
        this.yellow=yellow;
    }
    public Ammo (Ammo ammo){
        this.yellow=ammo.yellow;
        this.blu=ammo.blu;
        this.red=ammo.red;
    }
    public Ammo getAmmo() throws CloneNotSupportedException {
        return (Ammo) this.clone();
    }

    public void increseAmmo(Ammo ammo){
        if(ammo.red+this.red>=3){
            this.red=3;
        } else this.red+=ammo.red;
        if(ammo.blu+this.blu>=3){
            this.blu=3;
        } else this.blu+=ammo.blu;
        if(ammo.yellow+this.yellow>=3){
            this.yellow=3;
        } else this.yellow+=ammo.yellow;
    }

    public void decreseAmmo(Ammo ammo){
        if(ammo.red+this.red<=0){
            this.red=0;
        } else this.red-=ammo.red;
        if(ammo.blu+this.blu<=0){
            this.blu=0;
        } else this.blu-=ammo.blu;
        if(ammo.yellow+this.yellow<=0){
            this.yellow=0;
        } else this.yellow-=ammo.yellow;
    }

}
