package linux;

import java.io.File;

/**
 * Created by jiangfw on 2017/3/24.
 */
public class TestMath {

    public static void main(String[] args) {
        System.out.println(13 / 4);
        for (int i = 0; i < 10000; i++) {
            if (i % 3 == 0 && i % 4 == 1 && i % 5 == 4 && i % 6 == 3 && i % 7 == 0 && i % 8 == 1
                    && i % 9 == 0) {
                System.out.println(i);
            }
        }

        File a = new File("/tmp/ecg");
        a.mkdir();
        a.setExecutable(true);
        a.setReadable(true);
        a.setWritable(true);

        File b = new File("/tmp/ecg2");
        b.mkdir();
        b.setExecutable(true, true);
        b.setReadable(true, true);
        b.setWritable(true, true);


    }
}
