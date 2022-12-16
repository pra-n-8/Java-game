package models;

/**
 * Class for rolling steps
 */
public class MovementDice {
    /**
     * Class for Steps dice
     * @parm diceSides No of sides on the dice
     */
    final static int diceSides = 4;

    public int rollDice() {
        return (int) ((Math.random() * 100) % diceSides + 1);
    }
}
