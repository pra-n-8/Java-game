package models;


public class ScoreCard {
    /**
     * Class for saving Scorecard / Leader Board
     * @param name To save the name of the winner
     * @param score to save the score of the player
     */
    private String name;
    private int score;

    public ScoreCard(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
