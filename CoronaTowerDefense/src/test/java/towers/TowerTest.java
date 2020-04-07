package towers;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TowerTest {
    Tower tower;
    
    @Before
    public void setUp() {
        tower = new Tower(4.0, 4.0);
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
    
    @Test
    public void invalidUpgradePathDoesNothing() {
    	tower.upgrade(-1);
    	assertArrayEquals(new int[]{0, 0}, tower.getUpgrades());
    	tower.upgrade(2);
    	assertArrayEquals(new int[]{0, 0}, tower.getUpgrades());
    }
    
    @Test
    public void UpgradesAreCappedAt3() {
    	tower.upgrade(1);
    	tower.upgrade(1);
    	tower.upgrade(1);
    	
    	assertArrayEquals(new int[]{0, 3}, tower.getUpgrades());
    	
    	tower.upgrade(1);

    	assertArrayEquals(new int[]{0, 3}, tower.getUpgrades());
    }
}