package GameBoardTests;

import models.GameBoard;
import org.junit.jupiter.api.Test;
import service.ObstaclesPlacement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class gameBoardTest {
    @Test
    public void gameBoardCreationTest() {
        GameBoard gb = new GameBoard(10, 10, 5);
        assertEquals(10, gb.getBoardColumns() - 1);
        assertEquals(10, gb.getBoardRows());
        assertTrue(gb instanceof GameBoard);
    }

    @Test
    public void obstacleOnBoardTests() {
        GameBoard gb = new GameBoard(10, 10, 5);
        ObstaclesPlacement op = new ObstaclesPlacement();
        gb.setObstaclesList(op.generateObstacles(gb));
        assertEquals(9, gb.getObstaclesList().size());
    }

}
