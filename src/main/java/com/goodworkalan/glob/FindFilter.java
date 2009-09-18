package com.goodworkalan.glob;

import java.io.File;

/**
 * Differs from Java FileName filter in that it file base is not the
 * directory that contains the file, but the base directory where
 * the recursive find was performed.
 * 
 * @author Alan Gutierrez
 */
public interface FindFilter {
    public boolean accept(File base, String filePath);
}