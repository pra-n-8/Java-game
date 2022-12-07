package service;

import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObstaclesPlacement {
    public List<BoardObstacles> generateObstacles(GameBoard gameBoard){
        ArrayList<BoardObstacles> obstacles = new ArrayList<BoardObstacles>();
        for(int i = 0; i <3; i ++){
            Fence fence = new Fence();
            ObsCoordinates coords=fence.getObstacleLocation(gameBoard);
            obstacles.add(new BoardObstacles(fence,checkForObs(obstacles,coords)?fence.getObstacleLocation(gameBoard):coords));
        }
        for( int i = 0; i < 3; i++){
            Fire fire = new Fire();
            ObsCoordinates coords = fire.getObstacleLocation(gameBoard);
            obstacles.add(new BoardObstacles(fire,checkForObs(obstacles,coords)?fire.getObstacleLocation(gameBoard):coords));
        }
        for (int i = 0; i<1;i++){
            Teleporters tp = new Teleporters();
            ObsCoordinates coords = tp.getObstacleLocation(gameBoard);
            obstacles.add(new BoardObstacles(tp ,checkForObs(obstacles,coords)?tp.getObstacleLocation(gameBoard):coords));
        }
        for(int i = 0 ; i <2 ;i++){
            pits p = new pits();
            ObsCoordinates coords = p.getObstacleLocation(gameBoard);
            obstacles.add(new BoardObstacles(p,checkForObs(obstacles,coords)?p.getObstacleLocation(gameBoard):coords));
        }
        return obstacles;
    }
    private boolean checkForObs(ArrayList<BoardObstacles> obstacles ,ObsCoordinates coords){
        Map<Coordinates, Obstacle> obs = new HashMap<>();
        CheckObstacles co = new CheckObstacles();
        obstacles.stream().forEach(x->obs.putAll(co.getArea(x.getCoordinates().getStartLoc(), x.getCoordinates().getEndloc(), x.getObs())));
        for(Coordinates c : obs.keySet()){
                if((c.getX()==(coords.getStartLoc().getX()) && c.getY()==(coords.getStartLoc().getY()))||
                        c.getX()==(coords.getEndloc().getX()) && c.getY()==(coords.getEndloc().getY())){
                    return true;
                }
        }
        return false;
    }
}
