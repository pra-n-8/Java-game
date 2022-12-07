package service;

import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckObstacles {
    public Map<Coordinates,Obstacle> getArea(Coordinates start, Coordinates end, Obstacle obstacle) {
        Map<Coordinates,Obstacle> areaObstacle = new HashMap<Coordinates, Obstacle>();
        for(int i = start.getX(); i< end.getX();i++){
            for( int j = start.getY(); j < end.getY();j++){
                areaObstacle.put(new Coordinates(i,j),obstacle);
            }
        }
//        areaObstacle.forEach((key,value)-> System.out.println("x: "+key.getX()+" y: "+key.getY()));
        return areaObstacle;
    }
    public boolean checkForObstacle(int stepsX,int stepsY, GameBoard gameBoard) {
        Map<Coordinates, Obstacle> coords = new HashMap<>();
        gameBoard.getObstaclesList().stream().forEach(x -> coords.putAll(getArea(x.getCoordinates().getStartLoc(), x.getCoordinates().getEndloc(), x.getObs())));
//        coords.forEach((key,value)-> System.out.println("Obs : "+value.getClass()+" Coords : ("+key.getX()+","+key.getY()+")"));
        for (Coordinates c : coords.keySet()) {
            if(c.getX()==(stepsX) && c.getY()==(stepsY)){
               if(coords.get(c).getClass() == Teleporters.class){
                   ObstaclesPlacement op = new ObstaclesPlacement();
                   gameBoard.setObstaclesList(op.generateObstacles(gameBoard));
                   return false;
               }
               else {
                   return true;
               }
            }
        }
        return false;
    }

}
