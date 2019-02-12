package com.jiangfw.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * Created by jWX542257 on 2019/2/2.
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String classFileName = name + ".class";
        File classFile = new File(classFileName);
        if (classFile.exists()) {
            try (FileChannel fileChannel = new FileInputStream(classFile)
                    .getChannel()) {
                MappedByteBuffer mappedByteBuffer = fileChannel.map(
                        MapMode.READ_ONLY, 0, fileChannel.size()
                );
                byte[] b = mappedByteBuffer.array();
                clazz = defineClass(name, b, 0, b.length);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (clazz == null) {
            throw new ClassNotFoundException();
        }

        return clazz;
    }

    public static void main(String[] args)
            throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.loadClass("Hello");
        Method sayHello = clazz.getMethod("sayHello");
        sayHello.invoke(clazz);
    }
}
