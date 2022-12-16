package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PitTest {
    @Test
    public void pitTest() {
        Pit f = new Pit();
        assertTrue(f instanceof Pit);
        assertTrue(f instanceof Obstacle);
    }
    @Test
    void getWidth() {
        Pit f = new Pit();
        assertEquals(1,f.getWidth());
    }

    @Test
    void setWidth() {
        Pit f = new Pit();
        f.setWidth(10);
        assertEquals(10,f.getWidth());
    }

    @Test
    void getHeight() {
        Pit f = new Pit();
        assertEquals(1,f.getWidth());
    }

    @Test
    void setHeight() {
        Pit f = new Pit();
        f.setWidth(10);
        assertEquals(10,f.getWidth());
    }

    @Test
    void getIconloc() {
        Pit f = new Pit();
        assertEquals("src/main/resources/hole.png", f.getIconloc());
    }
}