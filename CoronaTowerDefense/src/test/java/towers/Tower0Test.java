package towers;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class Tower0Test {
    Tower0 tower;
    
    @Before
    public void setUp() {
        tower = new Tower0(4.0, 4.0);
    }
    
    @Test
    public void inRangeWorksWithoutUpgrades() {
        assertTrue(tower.inRange(4.0, 1.4));
        assertFalse(tower.inRange(4.0, 1.3));
    }
    
    @Test
    public void rangeIsIncreasedProperly() {
        assertFalse(tower.inRange(6.32, 2.38));
        tower.upgrade(1);
        assertTrue(tower.inRange(6.32, 2.38));
    }
}