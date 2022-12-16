package models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    public void playerCreationTest() {
        Player p = new Player("Player", 5);
        assertTrue(p instanceof Player);
        assertEquals(5, p.getLocation().getX());
        assertEquals("Player", p.getPlayerName());
    }

    @Test
    public void playerLocationTest() {
        Player p = new Player("Player", 5);
        Coordinates n = new Coordinates(5, 0);
        assertTrue(n.getX() == p.getLocation().getX() && n.getY() == p.getLocation().getY());
    }

    @Test
    public void directionDiceTest() {
        Player p = new Player("Player", 5);
        String[] directions = new String[]{"Forward", "Backwards", "Skip"};
        assertTrue(Arrays.stream(directions).toList().contains(p.directionDiceRoll.rollDice()));
    }

    @Test
    public void stepsDiceTest() {
        Player p = new Player("Player", 5);
        assertTrue(p.movementDiceRoll.rollDice() == 1 ||
                p.movementDiceRoll.rollDice() == 2||
                p.movementDiceRoll.rollDice() == 3||
                p.movementDiceRoll.rollDice() == 4);
    }
}