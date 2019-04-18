package it.polimi.model;

public class KillShotTrackPoint {

    private boolean skull;
    private EnumColorPlayer mark1;
    private EnumColorPlayer mark2;

    public EnumColorPlayer getMark1() {
        return mark1;
    }

    public EnumColorPlayer getMark2() {
        return mark2;
    }

    public boolean isSkull() {
        return skull;
    }

    public void setMark1(EnumColorPlayer mark1) {
        this.mark1 = mark1;
    }

    public void setMark2(EnumColorPlayer mark2) {
        this.mark2 = mark2;
    }

    public void setSkull(boolean skull) {
        this.skull = skull;
    }
}
