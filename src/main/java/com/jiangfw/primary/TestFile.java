package com.jiangfw.primary;

import java.io.File;
import java.io.UnsupportedEncodingException;
import org.apache.commons.io.FilenameUtils;

public class TestFile {

    public static void main(String[] args) throws UnsupportedEncodingException {
        File sFile = new File("D:\\Files\\mysql.txt");
        // File tFile = new File(outputFile);
        if (sFile.isFile()) {
            System.out.println("file");
            String p = sFile.getParent();
        }

        String f = FilenameUtils.getBaseName("D:\\Files\\mysql.txt");
        String p = FilenameUtils.getExtension("D:\\Files\\mysql.txt");
        System.out.println(f + "......" + p);

    }
}
