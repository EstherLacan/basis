package com.jiangfw.common.lang.test.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TestXStream {

    /**
     * <p>Description:</p>
     *
     * @author jiangfengwei
     */
    public static void main(String[] args) {
        Person aa = new Person("jiangfw", "cf", 25);
        XStream xs = new XStream(new DomDriver());
        xs.alias("Person", Person.class);
        String xml = xs.toXML(aa);
        System.out.println(xml);
    }


    /**
     * <p>Description:</p>
     *
     * @author jiangfengwei
     * @since 2013-8-5
     */
    public static class Person {

        private String name;
        private String clazz;
        private Integer age;

        public Person() {

        }

        public Person(String name, String clazz, Integer age) {
            this.name = name;
            this.clazz = clazz;
            this.age = age;
        }
    }

}

