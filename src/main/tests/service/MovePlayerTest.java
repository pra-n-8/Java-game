package service;

import models.Coordinates;
import models.GameBoard;
import models.GamePlay;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovePlayerTest {

    GamePlay gp;
    @BeforeEach
    void init(){
        Player p = new Player("test",2);
        p.setLocation(new Coordinates(2,10));
        GameBoard g = new GameBoard(10,10,1);
        MovePlayer mvp = new MovePlayer();
        GamePlay gp = new GamePlay(g);
        gp.setPlayer(p);
        gp.setSteps(2);
        this.gp=mvp.movePlayer(gp,"Forward");
    }
    @Test
    void movePlayer() {
        assertEquals(8,gp.getPlayer().getLocation().getY());
        assertEquals(2,gp.getPlayer().getLocation().getX());

    }
    @Test
    void getScore() {
        assertEquals(50,gp.getPlayer().getScore());

    }
}