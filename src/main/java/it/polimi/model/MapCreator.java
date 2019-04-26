package it.polimi.model;

import java.util.ArrayList;

public class MapCreator {
    
    public ArrayList<Square> createA() {
    
        Square s1 = new Square(0, 0, EnumColorSquare.BLU, null);
        Square s2 = new Square(0, 1, EnumColorSquare.BLU, null);
        Square s3 = new Square(0, 2, EnumColorSquare.BLU, null);
        Square s4 = new Square(0, 3, EnumColorSquare.GREEN, null);
        Square s5 = new Square(1, 0, EnumColorSquare.RED, null);
        Square s6 = new Square(1, 1, EnumColorSquare.RED, null);
        Square s7 = new Square(1, 2, EnumColorSquare.YELLOW, null);
        Square s8 = new Square(1, 3, EnumColorSquare.YELLOW, null);
        Square s9 = new Square(2, 1, EnumColorSquare.WHITE, null);
        Square s10 = new Square(2, 2, EnumColorSquare.YELLOW, null);
        Square s11 = new Square(2, 3, EnumColorSquare.YELLOW, null);
    
    
        ArrayList<Square> linkS1 = new ArrayList<>();
        linkS1.add(s2);
        linkS1.add(s5);
        s1.setLink(linkS1);
        ArrayList<Square> linkS2 = new ArrayList<>();
        linkS2.add(s1);
        linkS2.add(s3);
        s2.setLink(linkS2);
        ArrayList<Square> linkS3 = new ArrayList<>();
        linkS3.add(s4);
        linkS3.add(s7);
        linkS3.add(s2);
        s3.setLink(linkS3);
        ArrayList<Square> linkS4 = new ArrayList<>();
        linkS4.add(s8);
        linkS4.add(s3);
        s4.setLink(linkS4);
        ArrayList<Square> linkS5 = new ArrayList<>();
        linkS5.add(s1);
        linkS5.add(s6);
        s5.setLink(linkS5);
        ArrayList<Square> linkS6 = new ArrayList<>();
        linkS6.add(s9);
        linkS6.add(s5);
        s6.setLink(linkS6);
        ArrayList<Square> linkS7 = new ArrayList<>();
        linkS7.add(s10);
        linkS7.add(s3);
        linkS7.add(s8);
        s7.setLink(linkS7);
        ArrayList<Square> linkS8 = new ArrayList<>();
        linkS8.add(s4);
        linkS8.add(s11);
        linkS8.add(s7);
        s8.setLink(linkS8);
        ArrayList<Square> linkS9 = new ArrayList<>();
        linkS9.add(s6);
        linkS9.add(s10);
        s9.setLink(linkS9);
        ArrayList<Square> linkS10 = new ArrayList<>();
        linkS10.add(s9);
        linkS10.add(s7);
        linkS10.add(s11);
        s10.setLink(linkS10);
        ArrayList<Square> linkS11 = new ArrayList<>();
        linkS11.add(s8);
        linkS11.add(s10);
        s11.setLink(linkS11);
    
        ArrayList<Square> temMap = new ArrayList<Square>();
    
        temMap.add(s1);
        temMap.add(s2);
        temMap.add(s3);
        temMap.add(s4);
        temMap.add(s5);
        temMap.add(s6);
        temMap.add(s7);
        temMap.add(s8);
        temMap.add(s9);
        temMap.add(s10);
        temMap.add(s11);
       return temMap;
    }
    
}
