package com.jiangfw.primary;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class TestFile {

    public static void main(String[] args) throws UnsupportedEncodingException {
        File sFile = new File("~\\Documents\\temp\\Files\\mysql.txt");
        // File tFile = new File(outputFile);
        if (sFile.isFile()) {
            System.out.println("file");
            String p = sFile.getParent();
        }

        String f = FilenameUtils.getBaseName("~\\Documents\\temp\\Files\\mysql.txt");
        String p = FilenameUtils.getExtension("~\\Documents\\temp\\Files\\mysql.txt");
        System.out.println(f + "......" + p);

    }
}
