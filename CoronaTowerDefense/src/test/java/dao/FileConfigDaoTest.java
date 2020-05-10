package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileConfigDaoTest {
    FileConfigDao fcd;

    @Before
    public void setUp() {
        fcd = new FileConfigDao(420, 91, "test.config");
    }

    @After
    public void clear() {
        File file = new File("test.config");
        file.delete();
    }

    @Test
    public void propertiesFileIsCreated() {
        assertTrue(new File("test.config").exists());
    }

    @Test
    public void defaultValuesAreSetCorrectly() {
        assertEquals(420, fcd.readDisplayWidth());
        assertEquals(91, fcd.readDisplayHeight());
        assertEquals("save.db", fcd.readSaveFileName());
    }

    @Test
    public void emptyConfigReturnsDefaultValues() {
        FileConfigDao fcd0 = new FileConfigDao(1920, 1080, "test0.properties");

        assertEquals(1920, fcd0.readDisplayWidth());
        assertEquals(1080, fcd0.readDisplayHeight());
        assertEquals("save.db", fcd0.readSaveFileName());
    }

    @Test
    public void existingConfigFileIsReadCorrectly() {
        FileConfigDao fcd1 = new FileConfigDao(1920, 1080, "test1.properties");

        assertEquals(9001, fcd1.readDisplayWidth());
        assertEquals(4200, fcd1.readDisplayHeight());
        assertEquals("hups.sql", fcd1.readSaveFileName());
    }
}