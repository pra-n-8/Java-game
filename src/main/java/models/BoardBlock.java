package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardBlock extends StackPane {
    /**
     * Method for putting a blank element on the baard
     * @param x set x value of the board element
     * @param y set y value of the board element
     * @param width set width of the block
     * @param height set height of the block
     * @param color set color of the block
     */
    //    board building block
    public BoardBlock(double x, double y, double width, double height, Color color) {
//       create rectangle
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setStroke(Color.WHITESMOKE);
        rectangle.setFill(color);
        // create label
//        Label label = new Label(i+","+j);
        // set position
        setTranslateX(x);
        setTranslateY(y);
        getChildren().addAll(rectangle);
    }

    /**
     * Method for displaying obstacle on the board
     * @param x set x value of the board element
     * @param y set y value of the board element
     * @param width set width of the block
     * @param height set height of the block
     * @param obs get the obstacle to set on the board
     */
    //    Block for obstacles
    public BoardBlock(double x, double y, double width, double height, Obstacle obs) {
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setFill(Color.WHITESMOKE);
        rectangle.setStroke(Color.LIGHTBLUE);
        ImageView icon = new ImageView();
        if (obs.getClass() == Fence.class) {
            Image image = new Image("/fence.png");
            icon.setImage(image);
        } else if (obs.getClass() == Fire.class) {
            icon.setImage(new Image("/fire.png"));
        } else if (obs.getClass() == Pit.class) {
            icon.setImage(new Image("/hole.png"));
        } else if (obs.getClass() == Teleporters.class) {
            icon.setImage(new Image("/black-hole.png"));
        }
        icon.setPreserveRatio(true);
        icon.setFitHeight(height);
        icon.setFitWidth(width);
        setTranslateX(x);
        setTranslateY(y);
        getChildren().addAll(rectangle, icon);
    }

    /**
     * Method to set player on the board
     * @param x set x value of the board element
     * @param y set y value of the board element
     * @param width set width of the block
     * @param height set height of the block
     * @param player set player on board
     */
    //    Block for PLayer
    public BoardBlock(double x, double y, double width, double height, Player player) {
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setStroke(Color.WHITESMOKE);
        rectangle.setFill(Color.LIGHTBLUE);
        ImageView imageView = new ImageView(new Image(player.geticonLoc()));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        setTranslateX(x);
        setTranslateY(y);
        getChildren().addAll(rectangle, imageView);

    }

}
