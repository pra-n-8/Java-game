package models;

public class MovementDice {
    final static int diceSides = 4;
    public int rollDice(){
        return (int)((Math.random()*100)%diceSides+1);
    }
}
