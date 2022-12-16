package service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Service for all the game essential elements like initializing, checking winners, displaying scoreboards
 */
public class GameEssentials {
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();

    /**
     * Method for initiaizing gameboard , a starting point of the game
     * @param width Represents no of rows on gameboard
     * @param height Represents no of columns on gameboard
     * @param playerCount Represents no of players playing the game
     * @return gameboard instance
     */
    //    initialize game board
    public GameBoard initBoard(int width, int height, int playerCount) {
        GameBoard gameBoard = new GameBoard(width, height, playerCount);
        ObstaclesPlacement obstaclesPlacement = new ObstaclesPlacement();
        gameBoard.setObstaclesList(obstaclesPlacement.generateObstacles(gameBoard));
        System.out.println(gameBoard.getBoardColumns());
        return gameBoard;
    }

    /**
     * Method for generating gameboard for FX
     * @param gameBoard used to get gameboad details
     * @return playfeild, an grid for gameboard
     */
    public BoardBlock[][] generateGameBoard(GameBoard gameBoard) {
        BoardBlock[][] playfield = new BoardBlock[gameBoard.getBoardRows()][gameBoard.getBoardColumns()];
//        set width of the application
        double sceneWidth = screenBounds.getWidth();
//        set height of the application
        double sceneHeight = screenBounds.getHeight();
//      set width of the gameboard brick
        double gridWidth = sceneWidth / (gameBoard.getBoardRows() + 4);
//        set height of the gameboard brick
        double gridHeight = sceneHeight / (gameBoard.getBoardColumns() + 4);
        CheckObstacles checkObstacles = new CheckObstacles();
        Map<Coordinates, Obstacle> coords = new HashMap<>();
        gameBoard.getObstaclesList().stream().forEach(x -> coords.putAll(checkObstacles.getArea(x.getCoordinates().getStartLoc(), x.getCoordinates().getEndloc(), x.getObs())));

        // initialize playfield
        for (int i = 0; i < gameBoard.getBoardRows(); i++) {
            for (int j = 0; j < gameBoard.getBoardColumns(); j++) {
                BoardBlock node = null;
                int count = 0;
                for (Coordinates c : coords.keySet()) {
                    // create node
                    if (c.getX() == (i) && c.getY() == (j)) {
//                        System.out.println("inside");
                        node = new BoardBlock(i * gridWidth, j * gridHeight, gridWidth, gridHeight, coords.get(c));
                        break;
                    }
                    count++;
                }
                if (count == coords.size()) {
                    if (j > 0) {
                        node = new BoardBlock(i * gridWidth, j * gridHeight, gridWidth, gridHeight, Color.LIGHTBLUE);
                    } else {
                        node = new BoardBlock(i * gridWidth, j * gridHeight, gridWidth, gridHeight,Color.GREEN);
                    }
                }
                // add to playfield for further reference using an array
                playfield[i][j] = node;

            }
        }
        return playfield;
    }

    /**
     * Method for calling dialog box to get input from the user
     * @return instance of gameboard object
     */
    public GameBoard initGame() {
//        Generating dialog box to take board size and number of players
        Dialog<Boolean> boardSpecs = new Dialog<>();
        boardSpecs.setTitle("Welcome to SIMON'S RACE");
        boardSpecs.setHeaderText("Enter Board details");
//        adding buttons to dialog window
//        ButtonType bt = new ButtonType("Create Board", ButtonBar.ButtonData.OK_DONE);  // creating ok button
        Button bt = new Button("Create Board");
//        bt.setDisable(true);
        Button showLeaderBoard = new Button("LeaderBoard");
        Button quit = new Button("Quit");
        quit.setOnMouseClicked(event -> {
            boardSpecs.setResult(Boolean.TRUE);
            boardSpecs.hide();
            boardSpecs.close();
        });
        HBox btnRow = new HBox(showLeaderBoard, quit, bt);
        btnRow.setLayoutX(0);
        btnRow.setLayoutY(boardSpecs.getHeight());
        showLeaderBoard.setLayoutX(100);
        showLeaderBoard.setLayoutY(boardSpecs.getHeight() - 50);
        showLeaderBoard.setOnAction(actionEvent -> {
            showLeaderBoard();
        });
//        Pane for width and height
        GridPane boardDetails = new GridPane();
        boardDetails.setBackground(Background.fill(Color.WHITESMOKE));
//        adding text field for width input
        Label errorLabelWidth = new Label("Enter Numbers between 10 & 15");
        errorLabelWidth.setVisible(false);
        errorLabelWidth.setTextFill(Color.RED);
        TextField width = new TextField();
        width.setOnKeyTyped(keyEvent -> {
            try {
                if (!(Integer.parseInt(width.getText()) >= 10 && Integer.parseInt(width.getText()) <= 15)) {
                    errorLabelWidth.setText("Enter Numbers between 10 & 15");
                    errorLabelWidth.setVisible(true);
                } else {
                    errorLabelWidth.setVisible(false);

                }
            } catch (Exception e) {
                errorLabelWidth.setText("Enter Numbers");
                errorLabelWidth.setVisible(true);
            }
        });
        width.setPromptText("Enter Board Width");
        VBox widthDetails = new VBox(errorLabelWidth, width);
        widthDetails.setSpacing(5);
        boardDetails.add(widthDetails, 1, 0);
//        adding text field for height input
        Label errorLabelHeight = new Label("Enter Numbers between 10 & 15");
        errorLabelHeight.setVisible(false);
        errorLabelHeight.setTextFill(Color.RED);
        TextField height = new TextField();
        height.setOnKeyTyped(event -> {
            try {
                if (!(Integer.parseInt(height.getText()) >= 10 && Integer.parseInt(height.getText()) <= 15)) {
                    errorLabelHeight.setText("Enter Numbers between 10 & 15");
                    errorLabelHeight.setVisible(true);
                } else {
                    errorLabelHeight.setVisible(false);
                }
            } catch (Exception e) {
                errorLabelHeight.setText("Enter Numbers");
                errorLabelHeight.setVisible(true);
            }
        });
        height.setPromptText("Enter Board height");
        VBox heightDetails = new VBox(errorLabelHeight, height);
        boardDetails.add(heightDetails, 1, 1);
        ToggleGroup tg = new ToggleGroup();
        Label l = new Label("Select No of players");
        RadioButton r1 = new RadioButton("1");
        r1.setToggleGroup(tg);
        RadioButton r2 = new RadioButton("2");
        r2.setToggleGroup(tg);
        boardDetails.add(l, 1, 4);
        boardDetails.add(r1, 1, 5);
        boardDetails.add(r2, 1, 6);
        HBox msgBox = new HBox(showLeaderBoard);
        VBox box = new VBox(boardDetails, msgBox);
        VBox panel = new VBox(box, btnRow);
        btnRow.setSpacing(20);
        box.setSpacing(20);
        panel.setSpacing(10);
        boardSpecs.getDialogPane().setContent(panel);

        final String[] s = new String[1];
//        adding listner to the group
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) tg.getSelectedToggle();

                if (rb != null) {
                    s[0] = rb.getText();
                }
            }
        });
        AtomicReference<GameBoard> gameBoard = new AtomicReference<>();
        bt.setOnAction(event -> {
            if ((!(errorLabelHeight.isVisible() && errorLabelWidth.isVisible()))
                    && (width.getText() != "" && height.getText() != "" && s[0] != "")) {
                gameBoard.set(initBoard(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()), Integer.parseInt(s[0])));
                boardSpecs.setResult(Boolean.TRUE);
                boardSpecs.close();
            }
        });
//        boardSpecs.getDialogPane().setMinWidth(screenBounds.getWidth()/5);
        boardSpecs.getDialogPane().setMinHeight(screenBounds.getHeight() / 3);
        boardSpecs.showAndWait();

        return gameBoard.get();
    }

    /**
     * Method to check if obstacle is in the particular direction
     * @param gamePlay Represents the curent active game
     * @param direction Represents the direction rolled / selected by the player
     * @return true if there is an obstacle on the destination of the player
     */
    public boolean checkObstacles(GamePlay gamePlay, String direction) {
        CheckObstacles checkObstacles = new CheckObstacles();
        switch (direction) {
            case "Forward":
                return checkObstacles.checkForObstacle(
                        gamePlay.getPlayer().getLocation().getX(),
                        gamePlay.getPlayer().getLocation().getY() - gamePlay.getSteps(),
                        gamePlay.getBoard());
            case "Backwards":
                return checkObstacles.checkForObstacle(
                        gamePlay.getPlayer().getLocation().getX(),
                        gamePlay.getPlayer().getLocation().getY() + gamePlay.getSteps(),
                        gamePlay.getBoard()
                );
            case "Left":
                return checkObstacles.checkForObstacle(
                        gamePlay.getPlayer().getLocation().getX() - gamePlay.getSteps(),
                        gamePlay.getPlayer().getLocation().getY(),
                        gamePlay.getBoard()
                );
            case "Right":
                return checkObstacles.checkForObstacle(
                        gamePlay.getPlayer().getLocation().getX() + gamePlay.getSteps(),
                        gamePlay.getPlayer().getLocation().getY(),
                        gamePlay.getBoard()
                );
            case "Skip":
                return false;
        }
        return false;
    }

    /**
     * Method to display the winner on a player winning the game
     * @param stage Represents the window of the game
     * @param player Represents the player who won the game
     */
    public void DisplayWinner(Stage stage, Player player) {
        addPlayertoLeaderBoard(player);
        Label winnerTag = new Label("Winner !!");
        winnerTag.setStyle("-fx-font-size: 30px;-fx-font-weight: bold;");
        Label winner = new Label(player.getPlayerName());
        winner.setStyle("-fx-font-size: 25px;-fx-font-weight: bold");
        Label playerScore = new Label(Integer.toString(player.getScore()));
        playerScore.setStyle("-fx-font-size: 22px");
        winnerTag.setLayoutX(stage.getWidth() / 2);
        winnerTag.setLayoutY(stage.getHeight() / 3);
        winner.setLayoutX(winnerTag.getLayoutX());
        winner.setLayoutY(winnerTag.getLayoutY() + 50);
        playerScore.setLayoutX(winner.getLayoutX());
        playerScore.setLayoutY(winner.getLayoutY() + 50);
        Button closeBtn = new Button("Close");
        closeBtn.setLayoutX((stage.getWidth() / 2) - 30);
        closeBtn.setLayoutY(playerScore.getLayoutY() + 50);
        closeBtn.setOnAction(event -> {
            stage.close();
        });
        Button displayLeaderBoard = new Button("LeaderBoard");
        displayLeaderBoard.setOnAction(event -> {
            showLeaderBoard();
        });
        displayLeaderBoard.setLayoutX((stage.getWidth() / 2) + 30);
        displayLeaderBoard.setLayoutY(playerScore.getLayoutY() + 50);
        Group g = new Group(winnerTag, winner, playerScore, closeBtn, displayLeaderBoard);
        Scene scene = new Scene(g);
        stage.setScene(scene);
        stage.setFullScreen(true);
    }

    /**
     * Method to display the leader board / scoreboard
     */
    public void showLeaderBoard() {
        String line = "";
        Dialog leaderboard = new Dialog();
        leaderboard.getDialogPane().setMinHeight(screenBounds.getHeight() / 2);
        leaderboard.setTitle("Simon's Race - LeaderBoard");
        ScrollPane scrollPane = new ScrollPane();
        VBox table = new VBox();
        ArrayList<ScoreCard> winnerList = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/LeaderBoard.csv"));
            while ((line = bf.readLine()) != null) {
                String[] winners = line.split(",");
                winnerList.add(new ScoreCard(winners[0], Integer.parseInt(winners[1])));
            }
            Collections.sort(winnerList, new Comparator<ScoreCard>() {
                @Override
                public int compare(ScoreCard o1, ScoreCard o2) {
                    if (o1.getScore() == o2.getScore()) {
                        return 0;
                    } else if (o1.getScore() < o2.getScore()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            Rectangle scoreHeader = new Rectangle(100, 50);
            scoreHeader.setFill(Color.GOLD);
            Label sHeaderLabel = new Label("SCORE");
            StackPane sHeaderSP = new StackPane(scoreHeader, sHeaderLabel);
            Rectangle playerHeader = new Rectangle(100, 50);
            playerHeader.setFill(Color.GOLD);
            Label pHeaderLabel = new Label("WINNER NAME");
            StackPane pHeaderSP = new StackPane(playerHeader, pHeaderLabel);
            HBox header = new HBox(sHeaderSP, pHeaderSP);
            table.getChildren().add(header);
            winnerList.subList(0, 10).stream().forEach(x -> {
                Rectangle score = new Rectangle(100, 50);
                score.setFill(Color.TRANSPARENT);
                Label scoreLabel = new Label(Integer.toString(x.getScore()));
                StackPane scoreSP = new StackPane(score, scoreLabel);
                Rectangle playerName = new Rectangle(100, 50);
                playerName.setFill(Color.TRANSPARENT);
                Label playerNameLabel = new Label(x.getName());
                StackPane playerNameSP = new StackPane(playerName, playerNameLabel);
                HBox entries = new HBox(scoreSP, playerNameSP);
                table.getChildren().addAll(entries);
            });
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scrollPane.setContent(table);
        leaderboard.getDialogPane().setContent(scrollPane);
        leaderboard.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        leaderboard.showAndWait();
    }

    /**
     * Method to add winner to the scoreboard
     * @param player Represents the player who won the game
     */
    public void addPlayertoLeaderBoard(Player player) {
        try {
            File leaderBoard = new File("src/main/resources/LeaderBoard.csv");
            if (leaderBoard.isFile()) {
                FileWriter writer = new FileWriter(leaderBoard, true);
                writer.append(String.join(",", player.getPlayerName(), Integer.toString(player.getScore())));
                writer.append("\n");
                writer.flush();
                writer.close();
            } else {
                FileWriter newCSV = new FileWriter("src/main/resources/LeaderBoard.csv");
                newCSV.append(String.join(",", player.getPlayerName(), Integer.toString(player.getScore())));
                newCSV.append("\n");
                newCSV.flush();
                newCSV.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
