package models;

import javafx.scene.paint.Color;

public class Player {
    private String playerName;
    private Coordinates location=new Coordinates(0,0);
    public MovementDice movementDiceRoll = new MovementDice();
    public DirectionDice directionDiceRoll = new DirectionDice();
    private Boolean isWinner= false;
    private String iconLoc;
    private int score = 0;
    public Boolean getWinner() {
        return isWinner;
    }

    public void setWinner(Boolean winner) {
        isWinner = winner;
    }

    public Player(String playerName, int lane){
        this.playerName=playerName;
        location.setX(lane);
        location.setY(0);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String geticonLoc() {
        return iconLoc;
    }

    public void setIconLoc(String iconLoc) {
        this.iconLoc = iconLoc;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", location=" + location +
                ", movementDiceRoll=" + movementDiceRoll +
                ", directionDiceRoll=" + directionDiceRoll +
                '}';
    }
}
