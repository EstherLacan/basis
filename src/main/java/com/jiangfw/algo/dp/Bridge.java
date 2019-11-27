package com.jiangfw.algo.dp;

import java.util.Arrays;
import org.junit.Test;

/**
 * 【例题1】在一个夜黑风高的晚上，有n（n <= 50）个小朋友在桥的这边， 现在他们需要过桥，但是由于桥很窄，每次只允许不大于两人通过，他们只有一个手电筒，
 * 所以每次过桥的两个人需要把手电筒带回来，i号小朋友过桥的时间为T[i]， 两个人过桥的总时间为二者中时间长者。问所有小朋友过桥的总时间最短是多少。
 * 原文链接：https://blog.csdn.net/u013309870/article/details/75193592
 *
 * @author jWX542257
 */
public class Bridge {

    /**
     * 按人一次排列过桥时间.
     */
    public static Integer[] TIMES = {1, 6, 9, 10, 11, 12, 15, 20};


    /**
     * 过桥人数.
     */
    public static int numbers = 6;

    /**
     * 同时过桥人数的设定。题目中是同时只有两个人过桥.
     * <p>！！！！可以将问题一般化：一个桥同时可以过去n个人，这个时候最短时间为多少??
     * <p>此时的动态规划的算法应该考虑到N-n个人过河和N-1个人过河n个情况的最优解.
     */
    public static int baseNumbers = 2;

    /**
     * 过桥最优解的备忘录.
     */
    public static Integer[] OPT_TIMES = new Integer[TIMES.length];

    static {
        System.arraycopy(TIMES, 0, OPT_TIMES, 0, baseNumbers);
    }

    @Test
    public void testBridge() {
        int testNumber = numbers;
        System.out.println(testNumber + "个人过桥最少消耗时间为 " + passOptTime(testNumber) + " min.");
        System.out.println(Arrays.toString(OPT_TIMES));
    }

    private int passOptTime(int n) {
        if (n <= baseNumbers) {
//            System.out.println("前" + n + "个人一起过桥，耗时 " + OPT_TIMES[n - 1] + " min.");
            return OPT_TIMES[n - 1];
        }

        /*
          我们先将所有人按花费时间递增进行排序.
          假设前i个人过河花费的最少时间为opt[i]，那么考虑前i-1个人过河的情况，即河这边还有1个人，河那边有i-1个人，
          并且这时候手电筒肯定在对岸，所以opt[i] = opt[i-1] + a[1] + a[i] (让花费时间最少的人把手电筒送过来，然后和第i个人一起过河).

          如果河这边还有两个人，一个是第i号，另外一个无所谓，河那边有i-2个人，并且手电筒肯定在对岸，
          所以opt[i] = opt[i-2] + a[1] + a[i] + 2*a[2] (让花费时间最少的人把电筒送过来，然后第i个人和另外一个人一起过河，
          由于花费时间最少的人在这边，所以下一次送手电筒过来的一定是花费次少的，送过来后花费最少的和花费次少的一起过河，解决问题).
          所以 opt[i] = min{opt[i-1] + a[1] + a[i] , opt[i-2] + a[1] + a[i] + 2*a[2] }
         */
        int optTime_1 = passOptTime(n - 1) + TIMES[0] + TIMES[n - 1];
        int optTime_2 = passOptTime(n - 2) + TIMES[0] + TIMES[n - 1] + 2 * TIMES[1];

        OPT_TIMES[n - 1] = Math.min(optTime_1, optTime_2);

        return OPT_TIMES[n - 1];
    }
}
