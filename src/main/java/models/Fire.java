package models;

public class Fire implements Obstacle{
    private int width = 2;
    private int height = 2;
    private GameBoard gb = new GameBoard();
    private Coordinates start ;
    private Coordinates end;
    private String iconloc = "src/main/resources/fire.png";

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

    public Fire(){

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

    public String getIconloc() {
        return iconloc;
    }

    @Override
    public ObsCoordinates getObstacleLocation(GameBoard gb) {
        this.start=new Coordinates(((int)((Math.random()*Math.random()*100)%gb.getBoardRows())),((int)((Math.random()*Math.random()*100)%gb.getBoardColumns())));
        this.end = new Coordinates(this.start.getX()+getWidth()> gb.getBoardRows() ? this.start.getX()-getWidth() : this.start.getX()+getWidth(),
                this.start.getY()+2>gb.getBoardColumns()?this.start.getY()-getHeight():this.start.getY()+getHeight());
        ObsCoordinates coords = new ObsCoordinates();
        coords.setStartLoc(this.start);
        coords.setEndloc(this.end);
        return coords;
    }
}
