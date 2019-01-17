package com.jiangfw.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * 文件分割
 * Created by jiangfw on 2017/3/30.
 */
public class FileSpit {

    public static void main(String[] args) throws IOException {
        spit("D:\\tmp\\pom.txt", "d:\\tmp", 5000L);
    }

    private static void spit(String filePath, String saveDir, Long perFileLineNum)
            throws IOException {
        long timer = System.currentTimeMillis();
        int bufferSize = 20 * 1024 * 1024;
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
        BufferedReader input = new BufferedReader(inputStreamReader, bufferSize);
        String line;
        long lineNum = 1L;
        int i = 1;
        List<String> tempList = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            tempList.add(line);
            if (lineNum % perFileLineNum == 0) {
                FileUtils.writeLines(new File(saveDir + "/part" + i + ".txt"), tempList);
                i++;
                tempList = new ArrayList<>();
            }
            lineNum++;
        }
        System.out.println("line number is " + (lineNum - 1));

        if ((lineNum - 1) % perFileLineNum != 0) {
            FileUtils.writeLines(new File("D:/tmp/part" + i + ".txt"), tempList);
        }
        input.close();
        timer = System.currentTimeMillis() - timer;
        System.out.println("time is " + timer);
    }
}
