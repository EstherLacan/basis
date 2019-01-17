package com.jiangfw.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang.StringUtils;

public class TestStr {

    /**
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String doctorAccount = "aass_xsl";
        if (StringUtils.endsWith(doctorAccount, "_xsl")) {
            doctorAccount = StringUtils.removeEnd(doctorAccount, "_xsl");
            System.out.println(doctorAccount);
        }
        System.out.println(doctorAccount);
    }
}
