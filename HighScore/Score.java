package HighScore;

import java.util.Comparator;

public class Score {
    int coins;
    int wumpus;
    int turns;
    int arrows;
    String player;

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getWumpus() {
        return wumpus;
    }

    public void setWumpus(int wumpus) {
        this.wumpus = wumpus;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Score(){
        this("0","0","0","0","");
    }
    public Score(String[] s){
        this(s[0], s[1], s[2], s[3], s[4]);
    }

    public Score(String c, String w, String t, String a, String p){
        this.coins = Integer.parseInt(c);
        this.wumpus = Integer.parseInt(w);
        this.turns = Integer.parseInt(t);
        this.arrows = Integer.parseInt(a);
        this.player = p;
    }

    public int value(){
        return this.wumpus*50+this.coins+this.arrows*5+100-this.turns;
    }

    @Override
    public String toString(){
        return ""+this.coins+","+this.wumpus+","+this.turns+","+this.arrows+","+this.player;
    }




}


