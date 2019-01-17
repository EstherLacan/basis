package com.jiangfw.common.lang.test.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String args[]) {
        Resource A = new Resource("A");
        Resource B = new Resource("B");
        Resource C = new Resource("C");
        Resource D = new Resource("D");
        Resource E = new Resource("E");
        Map<Resource, Integer> allRes = new HashMap<Resource, Integer>();
        allRes.put(A, 5);//系统可用资源=（5，3， 8， 2，10）
        allRes.put(B, 3);//			  3  3  5  0  5
        allRes.put(C, 8);//			//5  3  8  1  2
        allRes.put(D, 2);            //2  1  2  0  4
        allRes.put(E, 10);            //4  0  7  0  5
        //Work w					//1  2  3  2  5
        //3  2  6  2  9

        Map<Resource, Integer> mapP1 = new HashMap<Resource, Integer>();
        Map<Resource, Integer> mapP2 = new HashMap<Resource, Integer>();
        Map<Resource, Integer> mapP3 = new HashMap<Resource, Integer>();
        Map<Resource, Integer> mapP4 = new HashMap<Resource, Integer>();
        Map<Resource, Integer> mapP5 = new HashMap<Resource, Integer>();
        Map<Resource, Integer> mapP6 = new HashMap<Resource, Integer>();
        //List
        mapP1.put(A, 3);//p1进程分别需要资源数目3  3  5  0  5
        mapP1.put(B, 3);
        mapP1.put(C, 5);
        mapP1.put(D, 0);
        mapP1.put(E, 5);

        mapP2.put(A, 5);//p2进程分别需要资源数目5  3  8  1  2
        mapP2.put(B, 3);
        mapP2.put(C, 8);
        mapP2.put(D, 1);
        mapP2.put(E, 2);

        mapP3.put(A, 2);//p3进程分别需要资源数目2  1  2  0  4
        mapP3.put(B, 1);
        mapP3.put(C, 2);
        mapP3.put(D, 0);
        mapP3.put(E, 4);

        mapP4.put(A, 4);//p4进程分别需要资源数目4  0  7  0  5
        mapP4.put(B, 0);
        mapP4.put(C, 7);
        mapP4.put(D, 0);
        mapP4.put(E, 5);

        mapP5.put(A, 1);//p5进程分别需要资源数目1  2  3  2  5
        mapP5.put(B, 2);
        mapP5.put(C, 3);
        mapP5.put(D, 2);
        mapP5.put(E, 5);

        mapP6.put(A, 3);//p6进程分别需要资源数目3  2  6  2  9
        mapP6.put(B, 2);
        mapP6.put(C, 6);
        mapP6.put(D, 2);
        mapP6.put(E, 9);

        Process p1 = new Process("p1", mapP1);
        Process p2 = new Process("p2", mapP2);
        Process p3 = new Process("p3", mapP3);
        Process p4 = new Process("p4", mapP4);
        Process p5 = new Process("p5", mapP5);
        Process p6 = new Process("p6", mapP6);

        List<Process> procList = new ArrayList<Process>();
        procList.add(p1);
        procList.add(p2);
        procList.add(p3);
        procList.add(p4);
        procList.add(p5);
        procList.add(p6);

        Work w = new Work(procList, allRes);//设置工作过完
        //简单初始设置，初始分配的资源都是空
        Map<Resource, Integer> allocation = new HashMap<Resource, Integer>();
        allocation.put(A, 0);
        allocation.put(B, 0);
        allocation.put(C, 0);
        allocation.put(D, 0);
        allocation.put(E, 0);
        w.initialized(allocation);
        w.setAvailRes(allRes);
        w.doWork();
    }
}
