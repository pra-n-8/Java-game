package models;

import models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class modelTests {
    @Test
    public void FenceTest() {
        Fence f = new Fence();
        assertTrue(f instanceof Fence);
        assertTrue(f instanceof Obstacle);
    }

    @Test
    public void FireTest() {
        Fire f = new Fire();
        assertTrue(f instanceof Fire);
        assertTrue(f instanceof Obstacle);
    }

    @Test
    public void pitTest() {
        Pit f = new Pit();
        assertTrue(f instanceof Pit);
        assertTrue(f instanceof Obstacle);
    }

    @Test
    public void TeleportersTest() {
        Teleporters f = new Teleporters();
        assertTrue(f instanceof Teleporters);
        assertTrue(f instanceof Obstacle);
    }
}
