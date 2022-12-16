package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireTest {

    @Test
    public void FireTest() {
        Fire f = new Fire();
        assertTrue(f instanceof Fire);
        assertTrue(f instanceof Obstacle);
    }
    @Test
    void getWidth() {
        Fence f = new Fence();
        assertEquals(2, f.getWidth());
    }

    @Test
    void setWidth() {
        Fence f = new Fence();
        f.setWidth(3);
        assertEquals(3, f.getWidth());
    }

    @Test
    void getHeight() {
        Fence f = new Fence();
        assertEquals(2, f.getWidth());
    }

    @Test
    void setHeight() {
        Fence f = new Fence();
        f.setHeight(2);
        assertEquals(2, f.getWidth());
    }

    @Test
    void getIconloc() {
        Fence f = new Fence();
        assertEquals("src/main/resources/fence.png", f.getIconloc());
    }
}