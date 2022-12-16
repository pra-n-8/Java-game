package models;

/**
 * Class for dice to roll the direction of movement
 */
public class DirectionDice{

    private final String[] directionCodes = new String[]{"Forward", "Forward", "Backwards", "Skip"};

    /**
     * Method for rolling dice
     * @return a directon a player should move
     */
    public String rollDice() {
        return directionCodes[(int) ((Math.random() * 100) % getCodesLegth())];
    }

    private int getCodesLegth() {
        return directionCodes.length;
    }

}