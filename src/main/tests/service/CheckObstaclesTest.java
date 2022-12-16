package service;

import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckObstaclesTest {
    GameBoard gb;

    @Test
    void checkForObstacle() {
        CheckObstacles co = new CheckObstacles();
        assertEquals(true,co.checkForObstacle(0,0,gb));
    }

    @BeforeEach
    void setUp() {
        gb = new GameBoard(10,10,2);
        ObstaclesPlacement op = new ObstaclesPlacement();
        gb.setObstaclesList(op.generateObstacles(gb));
        List obs = gb.getObstaclesList();
        obs.add(new BoardObstacles(new Fire(),
                new ObsCoordinates(new Coordinates(0,0), new Coordinates(1,1))));
        gb.setObstaclesList(obs);
    }
}