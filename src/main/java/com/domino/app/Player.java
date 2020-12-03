package com.domino.app;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Domino domino;
    private RandomNumber random;
    private ArrayList<String> dominos = new ArrayList<>();


    public Player(Domino domino, RandomNumber random){
        this.domino = domino;
        this.random = random;
        createHand();
    }

    public void createHand(){
        int startingHand = 7;
        for(int i = 0; i < startingHand; i++){
            giveDomino();
        }
    }

    public void giveDomino(){
        int number = RandomNumber.getRandom();
        String piece = domino.getDomino(number);
        dominos.add(piece);
        random.remaining();
    }

    public String getDomino(int chosen){
        return dominos.get(chosen);
    }

    public void removeDomino(int chosen){
        dominos.remove(chosen);
    }

    public int getDominoCount(){
        return dominos.size();
    }

    public String printHand(){
        StringBuilder hand = new StringBuilder();
        for(int i = 0; i < dominos.size(); i++){
            String[] handPiece = dominos.get(i).split("");
            hand.append(i + 1).append(":").append("[").append(handPiece[0]).append("|").append(handPiece[1]).append("]").append(" ");
        }
        return hand.toString();
    }

    public int dobleMasGrande(){
        //example 66 = [6|6], siempre va a ser mayor a 55 = [5|5]
        int mayorDoble = 0;
        List<Integer> doubles = new ArrayList<>();
        for (String s : dominos) {
            String[] handPiece = s.split("");
            String piece1 = handPiece[0];
            String piece2 = handPiece[1];
            if (piece1.equals(piece2)) {
                int doble = Integer.parseInt(piece1 + piece2);
                doubles.add(doble);
            }
        }
        for (int num : doubles) {
            if (num > mayorDoble) {
                mayorDoble = num;
            }
        }
        return mayorDoble;
    }

    public int fichaMasAlta(){
        int fichaAlta  = 0;
        for (String s : dominos) {
            String[] handPiece = s.split("");
            String piece1 = handPiece[0];
            String piece2 = handPiece[1];
            int piece = Integer.parseInt(piece1 + piece2);
            if(piece > fichaAlta){
                fichaAlta = piece;
            }
        }
        return fichaAlta;
    }

}
