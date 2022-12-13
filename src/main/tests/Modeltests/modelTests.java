package Modeltests;
import models.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class modelTests {
    @Test
    public void FenceTest(){
        Fence f = new Fence();
        assertTrue(f instanceof Fence);
        assertTrue(f instanceof Obstacle);
    }
    @Test
    public void FireTest(){
        Fire f = new Fire();
        assertTrue(f instanceof Fire);
        assertTrue(f instanceof Obstacle);
    }
    @Test
    public void pitTest(){
        pits f = new pits();
        assertTrue(f instanceof pits);
        assertTrue(f instanceof Obstacle);
    }
    @Test
    public void TeleportersTest(){
        Teleporters f = new Teleporters();
        assertTrue(f instanceof Teleporters);
        assertTrue(f instanceof Obstacle);
    }
}
