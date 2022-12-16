package models;

/**
 * Class for saving obstacle with all its coordinate
 */
public class BoardObstacles {
    /**
     * Class for saving obstacle with all its coordinate
     * @param obs to get obs
     * @param coordinates to sav all the coodinates of the obstacle
     */
    private Obstacle obs;
    private ObsCoordinates coordinates;

    public BoardObstacles() {
    }

    public BoardObstacles(Obstacle obs, ObsCoordinates coordinates) {
        this.obs = obs;
        this.coordinates = coordinates;
    }

    public Obstacle getObs() {
        return obs;
    }

    public void setObs(Obstacle obs) {
        this.obs = obs;
    }

    public ObsCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ObsCoordinates coordinates) {
        this.coordinates = coordinates;
    }
}
