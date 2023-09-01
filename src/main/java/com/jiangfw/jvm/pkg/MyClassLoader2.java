package com.jiangfw.jvm.pkg;

import java.io.FileInputStream;

/**
 * @Author: jiangfw
 * @Date: 2019-11-27
 */
public class MyClassLoader2 extends ClassLoader {

    public static void main(String[] args) throws Exception {
        MyClassLoader2 mcl = new MyClassLoader2();

        // load compiled class from file
        FileInputStream fileinputstream = new FileInputStream(
                "target/classes/com/jiangfw/jvm/pkg/LoadedClass.class" /* <- point to whever these classes
         *    are being compiled to. */
        );
        int numberBytes = fileinputstream.available();
        byte classBytes[] = new byte[numberBytes];
        fileinputstream.read(classBytes);
        fileinputstream.close();

        Class<?> lc = mcl.defineClass("com.jiangfw.jvm.pkg.LoadedClass", classBytes);
        Package myPackage = MyClassLoader2.class.getPackage();
        Package lcPackage = lc.getPackage();
        System.out.println("lc package: " + lcPackage);
        System.out.println("my package: " + myPackage);
        System.out.println("lc ClassLoader: " + lc.getClassLoader());
        System.out.println("lc ClassLoader parent: " +
                lc.getClassLoader().getParent());
        System.out.println("my ClassLoader: " + MyClassLoader2.class.getClassLoader());
        System.out.println("are they equal? " + (lcPackage == myPackage));
        if (lcPackage == myPackage) {
            System.out.println("okay... we should be able to instantiate " +
                    "the package if that's true, lets try");
            lc.newInstance(); // boom as expected
        }
    }

    Class<?> defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
