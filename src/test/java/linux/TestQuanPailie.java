package linux;

import java.util.Arrays;

/**
 * Created by jiangfw on 2017/3/24.
 */
public class TestQuanPailie {
    public static void main(String[] args) {
        int[] arr = {1, 3, 65, 11};
        perm(arr, 0, arr.length - 1);
    }

    private static void perm(int[] arr, int from, int to) {
        if (from == to) {
            System.out.println(Arrays.toString(arr));
        }
        for (int i = from; i <= to; i++) {
            swap(arr, from, i);
            perm(arr, from + 1, to);
            swap(arr, from, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
