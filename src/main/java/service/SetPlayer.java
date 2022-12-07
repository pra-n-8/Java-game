package service;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import models.BoardBlock;
import models.GameBoard;
import models.Player;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

public class SetPlayer {

    public Player setPlayer(int no, GameBoard gameBoard){
        Dialog<Boolean> playerDialog = new Dialog<>();
        playerDialog.setTitle("Player "+no);
        TextField playerName = new TextField();
        playerName.setPromptText("Enter Player Name");
        TextField playerLane = new TextField();
        playerLane.setPromptText("Enter Player lane");
        Label errorLabel = new Label("Enter lane between 0-" + (gameBoard.getBoardRows()-1));
        errorLabel.setVisible(false);
        playerLane.setOnKeyTyped(keyEvent -> {
            try{
            if(Integer.parseInt(playerLane.getText()) >= 0 && Integer.parseInt(playerLane.getText())<= gameBoard.getBoardRows()-1){
                errorLabel.setVisible(false);
            }
            else{
                errorLabel.setVisible(true);
            }}
            catch (Exception e){
                errorLabel.setVisible(true);
            }
        });
        HBox playerInfo = new HBox(playerName,playerLane);
        playerInfo.setSpacing(3);
        VBox err = new VBox(playerInfo,errorLabel);
        errorLabel.setTextFill(Color.RED);
        err.setSpacing(5);
        Button btn = new Button("Create Player");
        AtomicReference<Player> p = new AtomicReference<>();
        btn.setOnAction(event->{
            if((!errorLabel.isVisible() ) && playerLane.getText() != ""){
                p.set(new Player(playerName.getText(), Integer.parseInt(playerLane.getText())));
                playerDialog.setResult(true);
                playerDialog.close();
            }
        });
        VBox panel = new VBox(err,btn);
        playerDialog.getDialogPane().setContent(panel);
        playerDialog.showAndWait();
        return p.get();
    }
    public BoardBlock[][] setPlayersonBoard(Queue<Player> players,GameBoard gameBoard){
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        for (Player p : players){
            BoardBlock b = gameBoard.getBoard()[p.getLocation().getX()][p.getLocation().getY()];
            gameBoard.getBoard()[p.getLocation().getX()][p.getLocation().getY()] = new BoardBlock(b.getTranslateX(),b.getTranslateY(),(screenBounds.getWidth()/(gameBoard.getBoardRows()+4)),screenBounds.getHeight()/(gameBoard.getBoardColumns()+4),p);

        }
        return gameBoard.getBoard();
    }
}
