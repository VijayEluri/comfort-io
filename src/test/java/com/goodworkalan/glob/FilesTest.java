package com.goodworkalan.glob;

import java.io.File;
import static org.testng.Assert.*;

import static org.mockito.Mockito.*;
import org.testng.annotations.Test;

/**
 * Unit tests for the Files utility methods.
 *
 * @author Alan Gutierrez
 */
public class FilesTest {
    /** Test the constructor for the sake of coverage. */
    @Test
    public void constructor() {
        new Files();
    }

    /** Test a failed file deletion in a sub directory. */
    @Test
    public void deleteFailure() {
        File undeletable = mock(File.class);
        when(undeletable.isDirectory()).thenReturn(false);
        when(undeletable.delete()).thenReturn(false);
        File file = mock(File.class);
        when(file.isDirectory()).thenReturn(true);
        when(file.listFiles()).thenReturn(new File[] { undeletable });
        assertFalse(Files.delete(file));
    }
    
    /** Test recursive deletion. */
    @Test
    public void delete() {
        new File("target/junk/path").mkdirs();
        Files.delete(new File("target/junk"));
    }
    
    /** Test a file copy. */
    @Test
    public void copy() {
        File junk = new File("target/junk");
        junk.mkdirs();
        File copy = new File(junk, "FileTest.java");
        Files.copy(new File("src/test/java/com/goodworkalan/glob/FindTest.java"), copy);
        assertTrue(copy.exists());
        copy.delete();
        junk.delete();
    }
    
    /** Test a file copy I/O exception. */
    @Test
    public void copyFailure() {
        try {
            Files.copy(new File("src/test/java/com/goodworkalan/glob/Foo.java"), new File("target/junk"));
        } catch (GlobException e) {
            assertEquals(e.getCode(), GlobException.COPY_FAILURE);
        }
    }
}