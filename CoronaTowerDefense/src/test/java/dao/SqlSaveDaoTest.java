package dao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqlSaveDaoTest {
    SqlSaveDao ssd;

    @Before
    public void setUp() {
        ssd = new SqlSaveDao("test.db");
    }

    @After
    public void clear() {
        File file = new File("test.db");
        file.delete();
    }

    @Test
    public void databaseFileIsCreated() {
        File file = new File("test.db");
        assertTrue(file.exists());
    }

    @Test
    public void loadCompletionReturnsAnArrayWithCorrectLength() {
        boolean[][] c = ssd.loadCompletion(100, 100);

        assertEquals(100, c.length);
        assertEquals(100, c[0].length);
    }

    @Test
    public void initiallyNothingIsCompleted() {
        boolean[][] c = ssd.loadCompletion(100, 100);

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                assertFalse(c[i][j]);
            }
        }
    }

    @Test
    public void dataCanBeSaved() {
        boolean[][] c = new boolean[3][3];

        assertArrayEquals(c, ssd.loadCompletion(3, 3));

        ssd.saveCompletion(0, 1);
        c[0][1] = true;

        assertArrayEquals(c, ssd.loadCompletion(3, 3));

        ssd.saveCompletion(2, 0);
        c[2][0] = true;

        assertArrayEquals(c, ssd.loadCompletion(3, 3));
    }
}