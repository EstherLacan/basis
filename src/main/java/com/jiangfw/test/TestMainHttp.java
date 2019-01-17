package com.jiangfw.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class TestMainHttp {

    public static void main(String[] args) throws IOException {
        String data = URLEncoder.encode("patientPhone", "UTF-8") + "=" + URLEncoder
                .encode("13814045112", "UTF-8");
        data += "&" + URLEncoder.encode("auth_code", "UTF-8") + "=" + URLEncoder
                .encode("mHealth365_qTbmSvaL5c365d3z", "UTF-8");

        URL url = new URL("http://20.8.0.2:8050/api/iphone/user/ValidateUser");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
        wr.close();
        rd.close();
    }
}
