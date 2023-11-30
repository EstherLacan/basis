package test;

/**
 * @Author: jiangfw
 * @Date: 2023/11/18
 */
public class Solution6 {
    public static void main(String[] args) {
        int num = 123456;

//        List<Integer> array = new ArrayList();
//
//        while (num > 10) {
//            int v = num % 10;
//            array.add(v);
//            num = num / 10;
//        }
//        array.add(num);
////        System.out.println(Arrays.toString(array.toArray()));
//
//        for (int i = array.size() - 1; i >= 0; i--) {
//            System.out.println(array.get(i));
//        }


        // ******************************************************
        m1(num);
    }

    public static void m1(int n) {
        if (n >= 100) {
            m1(n / 10);
            System.out.println(n % 10);
        } else if (n > 10) {
            System.out.println(n / 10);
            System.out.println(n % 10);
        }
    }
}
