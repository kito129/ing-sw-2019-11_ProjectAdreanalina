package it.polimi.model;

import java.util.ArrayList;

public class MainChito {
    public static void main(String[] args){
        for (int i=0;i<4;i++){
            System.out.println("\n");
        }

        Square s1 = new Square(0, 0, EnumColorSquare.BLU, null);
        Square s2 = new Square(0, 1, EnumColorSquare.BLU, null);
        Square s3 = new Square(0, 2, EnumColorSquare.YELLOW, null);
        Square s4 = new Square(0, 3, EnumColorSquare.RED, null);
        Square s5 = new Square(1, 1, EnumColorSquare.BLU, null);
        Square s6 = new Square(1, 2, EnumColorSquare.YELLOW, null);
        Square s7 = new Square(1, 3, EnumColorSquare.RED, null);
        Square s8 = new Square(2, 1, EnumColorSquare.BLU, null);
        Square s9 = new Square(2, 2, EnumColorSquare.YELLOW, null);

        ArrayList<Square> linkS1 = new ArrayList<>();
        linkS1.add(s2);
        s1.setLink(linkS1);
        ArrayList<Square> linkS2 = new ArrayList<>();
        linkS2.add(s1);
        linkS2.add(s5);
        linkS2.add(s3);
        s2.setLink(linkS2);
        ArrayList<Square> linkS3 = new ArrayList<>();
        linkS3.add(s2);
        linkS3.add(s6);
        linkS3.add(s4);
        s3.setLink(linkS3);
        ArrayList<Square> linkS4 = new ArrayList<>();
        linkS4.add(s3);
        linkS4.add(s7);
        s4.setLink(linkS4);
        ArrayList<Square> linkS5 = new ArrayList<>();
        linkS5.add(s2);
        linkS5.add(s6);
        linkS5.add(s8);
        s5.setLink(linkS5);
        ArrayList<Square> linkS6 = new ArrayList<>();
        linkS6.add(s3);
        linkS6.add(s7);
        linkS6.add(s5);
        linkS6.add(s9);
        s6.setLink(linkS6);
        ArrayList<Square> linkS7 = new ArrayList<>();
        linkS7.add(s4);
        linkS7.add(s6);
        s7.setLink(linkS7);
        ArrayList<Square> linkS8 = new ArrayList<>();
        linkS8.add(s5);
        s8.setLink(linkS8);
        ArrayList<Square> linkS9 = new ArrayList<>();
        linkS9.add(s6);
        s9.setLink(linkS9);

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

        for (Square a : temMap) {
            for (Square b : a.getLink())
                System.out.println(a.toString() + "link: " + b.toString());
        }
        Map mappa = new Map(temMap);


        //cerco disatnza
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(1, 1, 0, 2, 1));
        //cerco visibilità
        if (mappa.isVisible(1, 1, 0, 2)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();


        //cerco disatnza
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(0, 0, 0, 3, 1));
        //cerco visibilità
        if (mappa.isVisible(0, 0, 0, 3)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();

        //cerco disatnza
        System.out.println("\nDISTANZA MINIMA: " + mappa.distance(0, 0, 1, 1, 1));
        //cerco visibilità
        if (mappa.isVisible(0, 0, 2, 1)) {
            System.out.println("\nVISIBILE\n");
        } else System.out.println("\n NON VISIBILE\n");
        mappa.refreshMap();
    }
}