package logic.towers;

import logic.viruses.Virus;
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
        assertEquals(2.6, tower.getRange(), 1e12);
        assertTrue(tower.inRange(new Virus(4.0, 1.4, 10, path)));
        assertFalse(tower.inRange(new Virus(4.0, 1.3, 10, path)));
    }
    
    @Test
    public void rangeIsIncreasedProperly() {
        assertFalse(tower.inRange(new Virus(6.32, 2.38, 10, path)));
        assertEquals(2.6, tower.getRange(), 1e12);

        tower.upgrade(1);

        assertTrue(tower.inRange(new Virus(6.32, 2.38, 10, path)));
        assertEquals(2.85, tower.getRange(), 1e12);
    }
    
    @Test
    public void invalidUpgradePathDoesNothing() {
    	tower.upgrade(-1);
    	assertArrayEquals(new int[]{0, 0}, tower.getUpgrades());
    	tower.upgrade(2);
    	assertArrayEquals(new int[]{0, 0}, tower.getUpgrades());
    }
    
    @Test
    public void upgradesAreCappedAt3() {
    	tower.upgrade(1);
    	tower.upgrade(1);
    	tower.upgrade(1);
    	
    	assertArrayEquals(new int[]{0, 3}, tower.getUpgrades());
    	
    	tower.upgrade(1);

    	assertArrayEquals(new int[]{0, 3}, tower.getUpgrades());
    }

    @Test
    public void virusInRangeIsAttacked() {
        Virus[] viruses = new Virus[1];
        viruses[0] = new Virus(4.0, 1.4, 10, path);

        tower.attack(viruses, 1);

        assertEquals(9, viruses[0].getHealth());
    }

    @Test
    public void virusNotInRangeIsNotAttacked() {
        Virus[] viruses = new Virus[1];
        viruses[0] = new Virus(4.0, 1.3, 10, path);

        tower.attack(viruses, 1);

        assertEquals(10, viruses[0].getHealth());
    }

    @Test
    public void onlyFirstVirusInRangeIsAttacked() {
        Virus[] viruses = new Virus[3];
        viruses[0] = new Virus(4.0, 1.3, 10, path);
        viruses[1] = new Virus(4.0, 1.4, 10, path);
        viruses[2] = new Virus(4.0, 1.5, 10, path);

        tower.attack(viruses, 1);

        assertEquals(10, viruses[0].getHealth());
        assertEquals(9, viruses[1].getHealth());
        assertEquals(10, viruses[2].getHealth());
    }

    @Test
    public void attackHasCooldown() {
        Virus[] viruses = new Virus[1];
        viruses[0] = new Virus(4.0, 1.4, 10, path);

        tower.attack(viruses, 1);

        assertEquals(9, viruses[0].getHealth());

        tower.attack(viruses, 0.35);

        assertEquals(9, viruses[0].getHealth());

        tower.attack(viruses, 0.35);

        assertEquals(8, viruses[0].getHealth());
    }

    @Test
    public void deadVirusIsNotAttacked() {
        Virus[] viruses = new Virus[1];
        viruses[0] = new Virus(4.0, 1.4, 1, path);

        tower.attack(viruses, 1);

        assertEquals(0, viruses[0].getHealth());

        tower.attack(viruses, 1);

        assertEquals(0, viruses[0].getHealth());
    }

    @Test
    public void firstLivingVirusIsAttacked() {
        Virus[] viruses = new Virus[2];
        viruses[0] = new Virus(4.0, 1.4, 0, path);
        viruses[1] = new Virus(4.0, 1.4, 5, path);

        tower.attack(viruses, 1);

        assertEquals(0, viruses[0].getHealth());
        assertEquals(4, viruses[1].getHealth());
    }
}