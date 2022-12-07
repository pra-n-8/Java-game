package models;

public class BoardObstacles {
    private Obstacle obs;
    private ObsCoordinates coordinates;

    public Obstacle getObs() {
        return obs;
    }

    public BoardObstacles() {
    }

    public BoardObstacles(Obstacle obs, ObsCoordinates coordinates) {
        this.obs = obs;
        this.coordinates = coordinates;
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
