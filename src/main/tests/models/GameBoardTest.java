package models;

import org.junit.jupiter.api.Test;
import service.GameEssentials;
import service.ObstaclesPlacement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void getBoard() {
        GameBoard gb = new GameBoard();
        assertTrue(gb instanceof GameBoard);
    }

    @Test
    void getBoardRows() {
        GameBoard gb = new GameBoard(10,10,2);
        assertEquals(10,gb.getBoardRows());
    }

    @Test
    void getBoardColumns() {
        GameBoard gb = new GameBoard(10,10,2);
        assertEquals(11,gb.getBoardColumns());
    }

    @Test
    void getPlayersCount() {
        GameBoard gb = new GameBoard(10,10,2);
        assertEquals(2,gb.getPlayersCount());
    }

    @Test
    void getObstaclesList() {
        GameBoard gb = new GameBoard(10,10,2);
        ObstaclesPlacement op = new ObstaclesPlacement();
        gb.setObstaclesList(op.generateObstacles(gb));
        assertTrue(gb.getObstaclesList() instanceof List<BoardObstacles>);

    }

    @Test
    void setObstaclesList() {
        GameBoard gb = new GameBoard(10,10,2);
        ObstaclesPlacement op = new ObstaclesPlacement();
        gb.setObstaclesList(op.generateObstacles(gb));
        assertEquals(9,gb.getObstaclesList().size());
    }
}