package service;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import models.BoardBlock;
import models.GameBoard;
import models.Player;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Service for taking player inputs and adding them on game board
 */
public class SetPlayer {
    /**
     * Method for taking player inputs
     * @param no No of players playing the game
     * @param gameBoard Instance of current gameboard where players should be added
     * @return newly created player
     */
    public Player setPlayer(int no, GameBoard gameBoard) {
        Dialog<Boolean> playerDialog = new Dialog<>();
        playerDialog.setTitle("Player " + no);
        TextField playerName = new TextField();
        playerName.setPromptText("Enter Player Name");
        TextField playerLane = new TextField();
        playerLane.setPromptText("Enter Player lane");
        Label errorLabel = new Label("Enter lane between 0-" + (gameBoard.getBoardRows() - 1));
        errorLabel.setVisible(false);
        playerLane.setOnKeyTyped(keyEvent -> {
            try {
                errorLabel.setVisible(Integer.parseInt(playerLane.getText()) < 0 || Integer.parseInt(playerLane.getText()) > gameBoard.getBoardRows() - 1);
            } catch (Exception e) {
                errorLabel.setVisible(true);
            }
        });
        HBox playerInfo = new HBox(playerName, playerLane);
        playerInfo.setSpacing(3);
        VBox err = new VBox(playerInfo, errorLabel);
        errorLabel.setTextFill(Color.RED);
        err.setSpacing(5);
        Button btn = new Button("Create Player");
        AtomicReference<Player> p = new AtomicReference<>();
        btn.setOnAction(event -> {
            if ((!errorLabel.isVisible()) && playerLane.getText() != "") {
                p.set(new Player(playerName.getText(), Integer.parseInt(playerLane.getText())));
                playerDialog.setResult(true);
                playerDialog.close();
            }
        });
        VBox panel = new VBox(err, btn);
        playerDialog.getDialogPane().setContent(panel);
        playerDialog.showAndWait();
        return p.get();
    }

    /**
     * Method to set players on Board
     * @param players   Represents the queue of players playing the game for adding on the front end
     * @param gameBoard Instace of current game
     * @return fx representation of the gameboard
     */
    public BoardBlock[][] setPlayersonBoard(Queue<Player> players, GameBoard gameBoard) {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        for (Player p : players) {
            BoardBlock b = gameBoard.getBoard()[p.getLocation().getX()][p.getLocation().getY()];
            gameBoard.getBoard()[p.getLocation().getX()][p.getLocation().getY()] = new BoardBlock(b.getTranslateX(), b.getTranslateY(), (screenBounds.getWidth() / (gameBoard.getBoardRows() + 4)), screenBounds.getHeight() / (gameBoard.getBoardColumns() + 4), p);

        }
        return gameBoard.getBoard();
    }
}
