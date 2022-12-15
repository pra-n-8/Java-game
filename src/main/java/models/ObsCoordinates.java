package models;

public class ObsCoordinates{
    private Coordinates startLoc;
    private Coordinates endLoc;

    public ObsCoordinates(Coordinates startLoc, Coordinates endLoc) {
        this.startLoc = startLoc;
        this.endLoc = endLoc;
    }

    public ObsCoordinates() {

    }

    public Coordinates getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(Coordinates startLoc) {
        this.startLoc = startLoc;
    }

    public Coordinates getEndloc() {
        return endLoc;
    }

    public void setEndloc(Coordinates endloc) {
        this.endLoc = endloc;
    }
}
