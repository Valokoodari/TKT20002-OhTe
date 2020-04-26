package towers;

import viruses.Virus;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TowerTest {
    Tower tower;
    int[][] path;
    
    @Before
    public void setUp() {
        tower = new Tower(4.0, 4.0);
        path = new int[1][2];
        path[0] = new int[]{0,0};
    }
    
    @Test
    public void inRangeWorksWithoutUpgrades() {
        assertTrue(tower.inRange(new Virus(4.0, 1.4, path)));
        assertFalse(tower.inRange(new Virus(4.0, 1.3, path)));
    }
    
    @Test
    public void rangeIsIncreasedProperly() {
        assertFalse(tower.inRange(new Virus(6.32, 2.38, path)));
        tower.upgrade(1);
        assertTrue(tower.inRange(new Virus(6.32, 2.38, path)));
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