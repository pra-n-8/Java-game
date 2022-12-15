package service;

import models.Coordinates;
import models.GameBoard;
import models.Obstacle;
import models.Teleporters;

import java.util.HashMap;
import java.util.Map;

public class CheckObstacles {
    /**
     * Method for calculating area covered by the obsctale between start and end points
     * @param start Represents the starting point of the obstacle
     * @param end   Represents the ending point of the obstacle
     * @param obstacle Represnts the Obstacle
     * @return all the points under obstacle on the board
     */
    public Map<Coordinates, Obstacle> getArea(Coordinates start, Coordinates end, Obstacle obstacle) {
        Map<Coordinates, Obstacle> areaObstacle = new HashMap<Coordinates, Obstacle>();
        for (int i = start.getX(); i < end.getX(); i++) {
            for (int j = start.getY(); j < end.getY(); j++) {
                areaObstacle.put(new Coordinates(i, j), obstacle);
            }
        }
        return areaObstacle;
    }

    /**
     * Method for checking if there is an obstacle in front of the player
     * @param stepsX Represents new location of the player - x coordinate
     * @param stepsY Represents new location of the player - y coordinate
     * @param gameBoard Represents the gameboard
     * @return true if obstacle is present on (stepsX,stepsY) , else false
     */
    public boolean checkForObstacle(int stepsX, int stepsY, GameBoard gameBoard) {
        Map<Coordinates, Obstacle> coords = new HashMap<>();
        gameBoard.getObstaclesList().stream().forEach(x -> coords.putAll(getArea(x.getCoordinates().getStartLoc(), x.getCoordinates().getEndloc(), x.getObs())));
        for (Coordinates c : coords.keySet()) {
            if (c.getX() == (stepsX) && c.getY() == (stepsY)) {
                if (coords.get(c).getClass() == Teleporters.class) {
                    ObstaclesPlacement op = new ObstaclesPlacement();
                    gameBoard.setObstaclesList(op.generateObstacles(gameBoard));
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

}
