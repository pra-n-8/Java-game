package models;

public class Coordinates {
    /**
     * Class for saving location of players and ostacles
     * @param x to store the value of xth lane of board
     * @param y to store the value of yth position on board
     */
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
