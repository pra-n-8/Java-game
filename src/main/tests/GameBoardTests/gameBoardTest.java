package GameBoardTests;
import models.*;
import org.junit.jupiter.api.Test;
import service.ObstaclesPlacement;

import static org.junit.jupiter.api.Assertions.*;
public class gameBoardTest {
    @Test
    public void gameBoardCreationTest(){
        GameBoard gb = new GameBoard(10,10,5);
        assertEquals(10,gb.getBoardColumns()-1);
        assertEquals(10,gb.getBoardRows());
        assertTrue(gb instanceof GameBoard);
    }
    @Test
    public void obstacleOnBoardTests(){
        GameBoard gb = new GameBoard(10,10,5);
        ObstaclesPlacement op = new ObstaclesPlacement();
        gb.setObstaclesList(op.generateObstacles(gb));
        assertTrue(gb.getObstaclesList().size() == 9);
    }

}
