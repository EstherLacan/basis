package com.jiangfw.algo.dp;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 通过不同的种类的硬币，组合成指定的金额，并且用最少的硬币数. Created by EstherLacan on 2019/2/11.
 */
public class CollectionCoins {

    private static Map<Integer, Integer> map = new HashMap<>();

    /**
     * 前一次操作的记录.
     */

    private static Map<Integer, StringBuilder> before = new HashMap<>();

    /**
     * 收集不同硬币的组合，最后的总额为amount值，并且硬币数量最少.
     */
    private static int numberOfCoins(int[] coins, int amount) {
        if (amount <= 0) {
            return Integer.MAX_VALUE;
        }

        if (ArrayUtils.isNotEmpty(coins)) {
            // 硬币中已经有金额和所需金额匹配，直接返回1就行了
            if (ArrayUtils.contains(coins, amount)) {
                before.put(amount, new StringBuilder("一个" + amount + "元的硬币"));
                return 1;
            }
        } else {
            throw new IllegalArgumentException("coins is empty.");
        }

        if (map.containsKey(amount)) {
            return map.get(amount);
        }

        int[] f = new int[coins.length];
        for (int i = 0; i < coins.length; i++) {
            f[i] = numberOfCoins(coins, amount - coins[i]);
        }

        int index = min(f);
        for (int i = 0; i < f.length; i++) {
            if (index == i) {
                StringBuilder trace = new StringBuilder(before.get(amount - coins[i]));
                before.put(amount, trace.append("\n").append("一个" + coins[i] + "元的硬币"));
            }
        }

        int result = f[index] + 1;
        //缓存已经计算过的定额的硬币数量。
        map.put(amount, result);

        return result;

    }

    private static int min(int[] f) {
        int index = 0;
        if (ArrayUtils.isNotEmpty(f)) {
            for (int i = 1; i < f.length; i++) {
                if (f[index] > f[i]) {
                    index = i;
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        return index;
    }


    public static void main(String[] args) {
        //不是用越大金额的硬币，数量就越少
        //比如 7 = 5+1+1   or 3+4
        int[] coins = {1, 3, 4, 5};
        int count = 12;
        System.out.println(numberOfCoins(coins, count));
        System.out.println(before.get(count).toString());
    }
}