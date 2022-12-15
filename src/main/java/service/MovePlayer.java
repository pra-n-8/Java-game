package service;

import models.Coordinates;
import models.GamePlay;

public class MovePlayer {
    /**
     * Method to move the player
     * Scoring -> forward movement = steps * 25, backward movement = -steps * 15, right / left movement to avoid obstacles = -steps * 30
     * @param gamePlay Current instance of the game
     * @param direction Represents the direction the player will move
     * @return new location of the player on the board in the updated Gameplay object
     */
    public GamePlay movePlayer(GamePlay gamePlay, String direction) {
        switch (direction) {
            case "Forward":
                if (gamePlay.getPlayer().getLocation().getY() - gamePlay.getSteps() > 0) {
                    gamePlay.getPlayer().setLocation(new Coordinates(
                            gamePlay.getPlayer().getLocation().getX(), gamePlay.getPlayer().getLocation().getY() - gamePlay.getSteps()
                    ));
                    gamePlay.getPlayer().setScore(gamePlay.getPlayer().getScore() + (gamePlay.getSteps() * 25));
                } else {
                    gamePlay.getPlayer().setWinner(true);
                }
                break;
            case "Backwards":
                if (gamePlay.getPlayer().getLocation().getY() + gamePlay.getSteps() < gamePlay.getBoard().getBoardColumns()) {
                    gamePlay.getPlayer().setLocation(new Coordinates(
                            gamePlay.getPlayer().getLocation().getX(), gamePlay.getPlayer().getLocation().getY() + gamePlay.getSteps()
                    ));

                } else {
                    gamePlay.getPlayer().setLocation(new Coordinates(
                            gamePlay.getPlayer().getLocation().getX(), gamePlay.getBoard().getBoardColumns() - 1
                    ));
                }
                gamePlay.getPlayer().setScore(gamePlay.getPlayer().getScore() - (gamePlay.getSteps() * 15));
                break;
            case "Left":
                if (gamePlay.getPlayer().getLocation().getX() - gamePlay.getSteps() >= 0) {
                    gamePlay.getPlayer().setLocation(new Coordinates(
                            gamePlay.getPlayer().getLocation().getX() - gamePlay.getSteps(), gamePlay.getPlayer().getLocation().getY()
                    ));
                    gamePlay.getPlayer().setScore(gamePlay.getPlayer().getScore() - (gamePlay.getSteps() * 30));
                }
                break;
            case "Right":
                if (gamePlay.getPlayer().getLocation().getX() + gamePlay.getSteps() < gamePlay.getBoard().getBoardRows()) {
                    gamePlay.getPlayer().setLocation(new Coordinates(
                            gamePlay.getPlayer().getLocation().getX() + gamePlay.getSteps(), gamePlay.getPlayer().getLocation().getY()
                    ));
                }
                gamePlay.getPlayer().setScore(gamePlay.getPlayer().getScore() - (gamePlay.getSteps() * 30));
                break;
        }
        return gamePlay;
    }
}
