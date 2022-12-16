package models;

import java.util.List;

/**
 * Class for main board
 */
public class GameBoard {
    /**
     * Class for gameboard
     * @param obstaclesList list of all the obstacles on the board
     * @param boardRows number of rows on the board
     * @param boardColumns number of columns on the board
     * @param board FX board representation
     */
    public List<BoardObstacles> obstaclesList;
    private int boardRows;
    private int boardColumns;
    private int playersCount;
    private BoardBlock[][] board;

    public GameBoard(int boardRows, int boardColumns, int playersCount) {
        this.boardRows = boardRows;
        this.boardColumns = boardColumns + 1;
        this.playersCount = playersCount;
    }

    public GameBoard() {
    }

    public BoardBlock[][] getBoard() {
        return board;
    }

    public void setBoard(BoardBlock[][] board) {
        this.board = board;
    }

    public int getBoardRows() {
        return boardRows;
    }

    public int getBoardColumns() {
        return boardColumns;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public List<BoardObstacles> getObstaclesList() {
        return obstaclesList;
    }

    public void setObstaclesList(List<BoardObstacles> obstaclesList) {
        this.obstaclesList = obstaclesList;
    }
}
