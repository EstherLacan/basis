package com.jiangfw.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by jWX542257 on 2019/2/2.
 */
public class TestStringBuilder {


    public static void main(String[] args) {
        final String tudou = "http://tengj.top/2018/01/01/maven/";

        URL url;
        HttpURLConnection conn = null;
        try {
            url = new URL(tudou);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder buf = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                //此处的tostring方法在多次调用后会降低程序性能。
                if (StringUtils.isNotEmpty(buf.toString())) {
                    buf.append("\r\n");
                }
                buf.append(line);
            }
            System.out.println(buf);
            //do something with 'buf
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
}
