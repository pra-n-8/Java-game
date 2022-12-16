package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeleportersTest {

    @Test
    public void TeleportersTest() {
        Teleporters f = new Teleporters();
        assertTrue(f instanceof Teleporters);
        assertTrue(f instanceof Obstacle);
    }
    @Test
    void getWidth() {
        Teleporters t = new Teleporters();
        assertEquals(1,t.getWidth());
    }

    @Test
    void setWidth() {
        Teleporters t = new Teleporters();
        t.setWidth(2);
        assertEquals(2,t.getWidth());
    }

    @Test
    void getHeight() {
        Teleporters t = new Teleporters();
        assertEquals(1,t.getHeight());
    }

    @Test
    void setHeight() {
        Teleporters t = new Teleporters();
        t.setHeight(2);
        assertEquals(2,t.getHeight());
    }

    @Test
    void getIconloc() {
        Teleporters t = new Teleporters();
        assertEquals("src/main/resources/black-hole.png", t.getIconloc());
    }
}