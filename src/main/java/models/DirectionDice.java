package models;


public class DirectionDice{

    private String directionCodes [] = new String[]{"Forward","Forward","Backwards","Skip"};

    public String rollDice() {
        return directionCodes[(int)((Math.random()*100)%getCodesLegth())];
    }

    public int getCodesLegth() {
        return directionCodes.length;
    }
}