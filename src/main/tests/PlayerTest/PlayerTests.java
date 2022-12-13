package PlayerTest;
import models.Coordinates;
import models.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class PlayerTests {
    @Test
    public void playerCreationTest(){
        Player p = new Player("Player",5);
        assertTrue(p instanceof Player);
        assertEquals(5,p.getLocation().getX());
        assertEquals("Player", p.getPlayerName());
    }
    @Test
    public void playerLocationTest(){
        Player p = new Player("Player",5);
        Coordinates n = new Coordinates(5,0);
        assertTrue(n.getX()==p.getLocation().getX() && n.getY() == p.getLocation().getY());
    }
    @Test
    public void directionDiceTest(){
        Player p = new Player("Player",5);
        String [] directions =new String[]{"Forward","Backwards","Skip"};
        assertTrue(Arrays.stream(directions).toList().contains(p.directionDiceRoll.rollDice()));
    }
    @Test
    public void stepsDiceTest(){
        Player p = new Player("Player",5);
        int [] steps =new int[]{1,2,3,4};
        assertTrue(Arrays.asList(steps).contains(p.movementDiceRoll.rollDice()));
    }
}
