package logic.viruses;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VirusTest {
    int[][] path;
    Virus virus;

    @Before
    public void setUp() {
        path = new int[3][2];
        path[0] = new int[]{0, 0};
        path[1] = new int[]{1, 0};
        path[2] = new int[]{1, 1};

        virus = new Virus(0, -1, 10, path);
    }

    @Test
    public void virusHasCorrectStartValues() {
        assertArrayEquals(new double[]{0.0, -1.0}, virus.getPos(), 1e12);

        assertEquals(10, virus.getHealth());
        assertTrue(virus.alive());
    }

    @Test
    public void virusMovesAlongThePath() {
        assertArrayEquals(new double[]{0.0, -1.0}, virus.getPos(), 1e12);
        virus.update(1);
        assertArrayEquals(new double[]{0.0, 0.0}, virus.getPos(), 1e12);
    }

    @Test
    public void virusStopsAtTheEndOfThePath() {
        virus.update(1);
        virus.update(1);
        for (int i = 0; i < 100; i++) {
            virus.update(1);
            assertArrayEquals(new double[]{1.0, 1.0}, virus.getPos(), 1e12);
        }
    }

    @Test
    public void virusDiesAtTheEndOfThePath() {
        for (int i = 0; i < 3; i++) {
            virus.update(1);
            assertTrue(virus.alive());
        }

        virus.update(1);

        assertFalse(virus.alive());
    }

    @Test
    public void virusAttacksThePlayerWhenThePathEnds() {
        for (int i = 0; i < 3; i++) {
            assertEquals(0, virus.update(1));
        }

        assertEquals(1, virus.update(1));
    }

    @Test
    public void virusCanBeDamaged() {
        virus.takeDamage(5);
        assertEquals(5, virus.getHealth());
        virus.takeDamage(1);
        assertEquals(4, virus.getHealth());
    }

    @Test
    public void virusCantBeHealed() {
        virus.takeDamage(-1);
        assertEquals(10, virus.getHealth());
        virus.takeDamage(-5);
        assertEquals(10, virus.getHealth());
    }
}