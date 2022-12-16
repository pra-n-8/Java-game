package ucd.javafx.game_fx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.*;
import service.GameEssentials;
import service.MovePlayer;
import service.SetPlayer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Main Method - Start Point of the Game
 */
public class gameFX extends Application {
    /**
     * Main start point of the game
     * @param turnQueue Turn queue to ensure the player are getting chance turn by turn
     * @param screenBounds To get the screen size of the game
     */
    public static Queue<Player> turnQueue = new LinkedList<>();
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameEssentials gameEssentials = new GameEssentials();
        GameBoard gameBoard = gameEssentials.initGame();
        for (int i = 0; i < gameBoard.getPlayersCount(); i++) {
            SetPlayer sp = new SetPlayer();
            Player p1 = sp.setPlayer(i + 1, gameBoard);
            p1.setLocation(new Coordinates(p1.getLocation().getX(), gameBoard.getBoardColumns() - 1));
            p1.setIconLoc("/player" + (i + 1) + ".png");
            turnQueue.add(p1);
        }

//        Labels for viewing information
        Label playerName = new Label();
        ImageView icon = new ImageView();
        icon.setFitHeight(30);
        icon.setFitWidth(30);
        icon.setPreserveRatio(true);
        Label moveDice = new Label();
        Label directionDice = new Label();
        HBox playerDisplay = new HBox(icon, playerName);
        playerDisplay.setLayoutX(screenBounds.getWidth() - 200);
        playerDisplay.setLayoutY(screenBounds.getHeight() - 600);
        VBox diceOutputs = new VBox(moveDice, directionDice);
        diceOutputs.setLayoutX(screenBounds.getWidth() - 200);
        diceOutputs.setLayoutY(screenBounds.getHeight() - 400);

//        Generating Game Board
        BoardBlock[][] playfield = gameEssentials.generateGameBoard(gameBoard);
        SetPlayer setPlayers = new SetPlayer();
        gameBoard.setBoard(playfield);
        playfield = setPlayers.setPlayersonBoard(turnQueue, gameBoard);
        AtomicReference<GridPane> root = new AtomicReference<>(new GridPane());
        root.get().setLayoutX(0);
        root.get().setLayoutY(10);
        root.set(getGrid(playfield));
        Button btn = new Button("Roll Dice");
//        btn.setAlignment(Pos.BOTTOM_LEFT);

//        hbox.setAlignment(Pos.BOTTOM_RIGHT);


        Button move = new Button("Move PLayer");
        move.setVisible(false);
        HBox hbox = new HBox(btn, move);
        hbox.setLayoutX(screenBounds.getWidth() - 200);
        hbox.setLayoutY(screenBounds.getHeight() - 200);
        Button moveLeft = new Button(" Move Left");
        Button moveRight = new Button("Move Right");
        Button skipTurn = new Button("Skip Turn");
        HBox btns = new HBox(moveLeft, moveRight);
        VBox vbox = new VBox(btns, skipTurn);
        Label label = new Label("Obstacle Ahead !! Select path");
        VBox vbox_moveset = new VBox(label, vbox);
        vbox_moveset.setLayoutX(screenBounds.getWidth() - 200);
        vbox_moveset.setLayoutY(screenBounds.getHeight() - 200);
        vbox_moveset.setVisible(false);
        Group group = new Group(root.get(), hbox, vbox_moveset, diceOutputs, playerDisplay);
        Scene scene = new Scene(group, screenBounds.getWidth(), screenBounds.getHeight());
//        scene.setFill(Color.BLACK);
        scene.getStylesheets().add("/stylesheet.css");
        primaryStage.setScene(scene);
//        btn.setOnAction(x->startGame.startGame(gameBoard, turnQueue, finalPlayfield,root));
        AtomicReference<GamePlay> gamePlay = new AtomicReference<>(new GamePlay(gameBoard));
        MovePlayer mvpl = new MovePlayer();
        playerName.setText(turnQueue.peek().getPlayerName());
        icon.setImage(new Image(turnQueue.peek().geticonLoc()));
        btn.setOnAction(x -> {
            Player p = turnQueue.remove();
            icon.setImage(new Image(p.geticonLoc()));
            gamePlay.get().setPlayer(p);
            gamePlay.get().setGroup(group);
            gamePlay.get().setSteps(p.movementDiceRoll.rollDice());
            moveDice.setVisible(true);
            moveDice.setText("Steps " + gamePlay.get().getSteps());
            gamePlay.get().setDirection(p.directionDiceRoll.rollDice());
            directionDice.setVisible(true);
            directionDice.setText("Direction : " + gamePlay.get().getDirection());
            boolean isObstacle = gameEssentials.checkObstacles(gamePlay.get(), gamePlay.get().getDirection());
            if (!CheckWinner(gamePlay.get())) {
                btn.setVisible(false);
                if (isObstacle) {
                    vbox_moveset.setVisible(true);
                } else {
                    move.setVisible(true);
                }
            } else {
                primaryStage.close();
            }

        });

        move.setOnAction(x -> {
            gamePlay.set(mvpl.movePlayer(gamePlay.get(), gamePlay.get().getDirection()));
            turnQueue.add(gamePlay.get().getPlayer());
            move.setVisible(false);
            btn.setVisible(true);
            refreshBoard(gamePlay.get().getBoard(), root);
            playerName.setText(turnQueue.peek().getPlayerName());
            icon.setImage(new Image(turnQueue.peek().geticonLoc()));
            moveDice.setVisible(false);
            if (gamePlay.get().getPlayer().getWinner()) {
                gameEssentials.DisplayWinner(primaryStage, gamePlay.get().getPlayer());
            }
            directionDice.setVisible(false);
        });
        moveLeft.setOnAction(x -> {
            if (!gameEssentials.checkObstacles(gamePlay.get(), "Left")) {
                gamePlay.set(mvpl.movePlayer(gamePlay.get(), "Left"));
            }
            turnQueue.add(gamePlay.get().getPlayer());
            playerName.setText(turnQueue.peek().getPlayerName());
            icon.setImage(new Image(turnQueue.peek().geticonLoc()));
            refreshBoard(gamePlay.get().getBoard(), root);
            vbox_moveset.setVisible(false);
            moveDice.setVisible(false);
            btn.setVisible(true);
            directionDice.setVisible(false);
        });
        moveRight.setOnAction(x -> {
            gamePlay.get().setDirection("Right");
            if (!gameEssentials.checkObstacles(gamePlay.get(), "Right")) {
                gamePlay.set(mvpl.movePlayer(gamePlay.get(), "Right"));
            }
            turnQueue.add(gamePlay.get().getPlayer());
            playerName.setText(turnQueue.peek().getPlayerName());
            icon.setImage(new Image(turnQueue.peek().geticonLoc()));
            refreshBoard(gamePlay.get().getBoard(), root);
            vbox_moveset.setVisible(false);
            moveDice.setVisible(false);
            btn.setVisible(true);
            directionDice.setVisible(false);
        });
        skipTurn.setOnAction(x -> {
            turnQueue.add(gamePlay.get().getPlayer());
            refreshBoard(gamePlay.get().getBoard(), root);
            playerName.setText(turnQueue.peek().getPlayerName());
            icon.setImage(new Image(turnQueue.peek().geticonLoc()));
            btn.setVisible(true);
            vbox_moveset.setVisible(false);
            moveDice.setVisible(false);
            directionDice.setVisible(false);
        });

//        primaryStage.setResizable(false);
        primaryStage.setTitle("Jumanji");
        primaryStage.show();
    }

    /**
     * Method to update the board values after each player's turn
     * @param gameBoard Instance of ongoing game
     * @param root
     */
    public void refreshBoard(GameBoard gameBoard, AtomicReference<GridPane> root) {
        GameEssentials gameEssentials = new GameEssentials();
        SetPlayer setPlayers = new SetPlayer();
        root.get().getChildren().removeAll();
        gameBoard.setBoard(gameEssentials.generateGameBoard(gameBoard));
        BoardBlock[][] g = setPlayers.setPlayersonBoard(turnQueue, gameBoard);
        root.get().getChildren().addAll(getGrid(g));
    }

    /**
     * Method to check if player has won the game or not
     * @param gamePlay Instance of ongoing game
     * @return true, if player has won the game
     */
    public boolean CheckWinner(GamePlay gamePlay) {
        return (gamePlay.getDirection() == "Forward") &&
                ((gamePlay.getPlayer().getLocation().getY() - gamePlay.getSteps()) >= (gamePlay.getBoard().getBoardColumns() - 1));
    }

    /**
     * Method to get the fx representation of the game
     * @param playfield Instance of ongoing game
     * @return new Grid with updated locations of the players
     */
    public GridPane getGrid(BoardBlock[][] playfield) {
        GridPane root = new GridPane();
        for (int i = 0; i < playfield.length; i++) {
            for (int j = 0; j < playfield[0].length; j++) {
//                root.getChildren().remove(i,j);
                root.add(playfield[i][j], i, j);
                root.getColumnConstraints().add(new ColumnConstraints(0));
                root.getRowConstraints().add(new RowConstraints(0));
            }
        }
        root.setLayoutX(0);
        root.setLayoutY(10);
        return root;
    }

}