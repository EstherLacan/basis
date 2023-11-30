package com.jiangfw.algo.dp;

/**
 * @author EstherLacan
 * @date 2019/2/11
 * <p>
 * 下面是该算法的主要步骤：
 * <p>
 * 创建一个长度为N的整数数组b，其中N表示天数。数组b存储每一天可以借到的书的数量。
 * 在代码中，数组b的首项b[0]用0填充，无实际意义，因此实际考虑的天数从第1天开始。
 * <p>
 * 创建一个辅助数组aux，用于存储子问题的解。aux的长度也为N。
 * <p>
 * 初始化aux[1]为b[1]，因为在第1天，只有一种选择，就是借b[1]本书。
 * <p>
 * 使用循环遍历从第2天到第N天的每一天，计算最大借书数量。对于每一天i，有两种选择：
 * <p>
 * 借书：如果选择借书，则最大借书数量为aux[i-2]（前一天不借书的最大数量）加上b[i]（当前天借到的书的数量）。
 * 不借书：如果选择不借书，则最大借书数量为aux[i-1]（前一天的最大数量）。
 * 在每一天都计算了最大借书数量后，最终返回aux[N-1]，这表示在所有天数内，可以借到的最大书籍数量。
 */
public class Books {

    public static int getMostBooks(int[] b) {
        //为方便计算，b数组的首项b[0]用0填充，无实际意义
        int length = b.length;
        if (length == 1) {
            return 0;
        }
        //辅助数组，存储子问题的解
        int[] aux = new int[length];
        aux[1] = b[1];
        for (int i = 2; i < length; i++) {
            //对应的在第i天借书或者不借书
            aux[i] = Math.max(aux[i - 2] + b[i], aux[i - 1]);
        }
        return aux[length - 1];
    }

    public static void main(String[] args) {
        int[] ary = {0, 5, 1, 2, 10, 6, 2, 8};
        int result = getMostBooks(ary);
        System.out.println(result);
    }
}