package test;

/**
 * @Author: jiangfw
 * @Date: 2023/11/13
 */
public class TestJava {
    public static void main(String[] args) {

        int a = 1;
        switch (a) {
            case 1:
                System.out.println(1);
                break;
            case 3:
                System.out.println(3);
                break;
            default:
                System.out.println("other");
                break;
            case 2:
                System.out.println(2);
                break;
        }


        int sum = 0;
        for (int b = 1; b <= 101; ) {

            sum += b; // sum = sum+b
            b += 2;
            // b += 2  => b=b+2
        }
        System.out.println(sum);

        int c = 100;
        while (c >= 100) {
            System.out.println(123);
            c--;
        }

        do {
            System.out.println(234);
            c--;
        } while (c > 1);

    }
}
