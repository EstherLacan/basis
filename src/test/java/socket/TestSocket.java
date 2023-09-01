package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: jiangfw
 * @Date: 2020-03-08
 */
public class TestSocket {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8090);
        System.out.println(Thread.currentThread().getName() + ":\tstep1 : new ServerSocket(8090)");
        while (true) {
            Socket client = server.accept();
            System.out.println(Thread.currentThread().getName() + ":\tstep2:client\t" + client.getPort());
            new Thread(() -> {
                try {
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + ":\t" + reader.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}
