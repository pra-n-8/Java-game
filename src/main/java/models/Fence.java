package models;

public class Fence implements Obstacle{

    private int width = 2;
    private int height = 1;
    private String iconloc = "src/main/resources/fence.png";
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
    private GameBoard gb = new GameBoard();
    private Coordinates start;
    private Coordinates end;
    public Fence(){
    }

    public Coordinates getStart() {
        return start;
    }

    public Coordinates getEnd() {
        return end;
    }

    public void setStart(Coordinates start) {
        this.start = start;
    }

    public void setEnd(Coordinates end) {
        this.end = end;
    }

    public String getIconloc() {
        return iconloc;
    }

    @Override
    public ObsCoordinates getObstacleLocation(GameBoard gb) {
        this.start = new Coordinates(((int)((Math.random()*Math.random()*100)%gb.getBoardRows())),((int)((Math.random()*Math.random()*100)%gb.getBoardColumns())));
        this.end = new Coordinates(this.start.getX()+getWidth() > gb.getBoardRows() ? this.start.getX()-getWidth() : this.start.getX()+getWidth(),
                this.start.getY()+getHeight()>gb.getBoardColumns()?this.start.getY()-getHeight():this.start.getY()+getHeight());
        ObsCoordinates coords = new ObsCoordinates();
        coords.setStartLoc(this.start);
        coords.setEndloc(this.end);
        return coords;
    }
}
