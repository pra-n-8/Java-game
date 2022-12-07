package models;
import java.util.List;

public class GameBoard {
    private int boardRows;
    private int boardColumns;
    private int playersCount;
    private BoardBlock[][] board;

    public BoardBlock[][] getBoard() {
        return board;
    }

    public void setBoard(BoardBlock[][] board) {
        this.board = board;
    }

    public GameBoard(int boardRows, int boardColumns, int playersCount) {
        this.boardRows = boardRows;
        this.boardColumns = boardColumns+1;
        this.playersCount = playersCount;
    }
    public GameBoard(){};
    public int getBoardRows() {
        return boardRows;
    }

    public void setBoardRows(int boardRows) {
        this.boardRows = boardRows;
    }

    public int getBoardColumns() {
        return boardColumns;
    }

    public void setBoardColumns(int boardColumns) {
        this.boardColumns = boardColumns;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public List<BoardObstacles> obstaclesList;

    public List<BoardObstacles> getObstaclesList() {
        return obstaclesList;
    }

    public void setObstaclesList(List<BoardObstacles> obstaclesList) {
        this.obstaclesList = obstaclesList;
    }
}
