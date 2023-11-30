package com.jiangfw.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * https://blog.csdn.net/qq_28051453/article/details/105449964
 * https://www.bilibili.com/video/BV1dJ411M7BV
 *
 * @Author: jiangfw
 * @Date: 2020-04-08
 */
public class TestObj {
    static Obj obj = new Obj();

    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("TestObjOut"));
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        oos.writeObject(obj);
        oos.flush();
        oos.close();

        System.out.println("obj.hashCode() = " + Integer.toHexString(obj.hashCode()));
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
