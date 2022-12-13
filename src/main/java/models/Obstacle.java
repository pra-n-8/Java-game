package models;

public class Obstacle {
    private int width;
    private int height;
    private String iconloc;
    private Coordinates start;
    private Coordinates end;

    public Obstacle(int width, int height, String iconloc) {
        this.width = width;
        this.height = height;
        this.iconloc = iconloc;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getIconloc() {
        return iconloc;
    }

    public void setIconloc(String iconloc) {
        this.iconloc = iconloc;
    }

    public Coordinates getStart() {
        return start;
    }

    public void setStart(Coordinates start) {
        this.start = start;
    }

    public Coordinates getEnd() {
        return end;
    }

    public void setEnd(Coordinates end) {
        this.end = end;
    }

    public ObsCoordinates getObstacleLocation(GameBoard gb){
        this.start = new Coordinates(((int)((Math.random()*Math.random()*100)%gb.getBoardRows())),((int)((Math.random()*Math.random()*100)%gb.getBoardColumns())));
        this.end = new Coordinates(this.start.getX()+getWidth() > gb.getBoardRows() ? this.start.getX()-getWidth() : this.start.getX()+getWidth(),
                this.start.getY()+getHeight()>gb.getBoardColumns()?this.start.getY()-getHeight():this.start.getY()+getHeight());
        ObsCoordinates coords = new ObsCoordinates();
        coords.setStartLoc(this.start);
        coords.setEndloc(this.end);
        return coords;
    }
}
