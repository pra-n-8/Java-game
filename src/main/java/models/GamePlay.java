package models;

import javafx.scene.Group;

/**
 * Class for having all the elements at one place while game is ongoing
 * For game components communication
 */
public class GamePlay {
    /**
     * Class for passing game values for fx utilization
     * @param board Gameboard details
     * @param player playing player details
     * @param group details of group on FX
     * @param steps No of steps rolled by player
     * @param direction Direction rolled by player
     */
    private GameBoard board;
    private Player player;
    private Group group;
    private int steps;
    private String direction;

    public GamePlay(GameBoard board) {
        this.board = board;
        this.player = player;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GameBoard getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
