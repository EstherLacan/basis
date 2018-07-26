package com.jiangfw.leetcode.interger;

/**
 * 7. 反转整数（LeetCode 题解）
 题目描述：
 给定一个 32 位有符号整数，将整数中的数字进行反转。
 示例 1:
 输入：123
 输出：321
 示例 2:
 输入：-123
 输出：-321
 示例 3:
 输入：120
 输出：21
 注意:
 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 弹出和推入数字 & 溢出前进行检查：

 思路
 我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。

 算法
 反转整数的方法可以与反转字符串进行类比。
 我们想重复 “弹出” x 的最后一位数字，并将它 “推入” 到 rev 的后面。最后，rev 将与 x 相反。
 要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。
 但是，这种方法很危险，因为当 temp = rev ⋅ 10 + pop 时会导致溢出。
 幸运的是，事先检查这个语句是否会导致溢出很容易。
 为了便于解释，我们假设 rev 是正数。
 当 rev 为负时可以应用类似的逻辑。
 Java 代码实现
 复杂度分析
 时间复杂度：O(log(x))，x 中大约有 log10(x) 位数字。
 空间复杂度：O(1)。
 * Created by jiangfw on 2018/7/10.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);//2147483647   ==== 0x7fffffff
        System.out.println(0xffffffff+1);//2147483647   ==== 0x7fffffff
        System.out.println(Integer.MIN_VALUE);//-2147483648  ==== 0x80000000
        System.out.println(reverse(-1463847412));
    }

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if(rev > Integer.MAX_VALUE/10 ||(rev == Integer.MAX_VALUE/10 && pop > 7)) return 0;
            if(rev < Integer.MIN_VALUE/10 ||(rev == Integer.MIN_VALUE/10 && pop < -8)) return 0;
            rev =  rev*10+pop;
        }
        return rev;
    }
}
