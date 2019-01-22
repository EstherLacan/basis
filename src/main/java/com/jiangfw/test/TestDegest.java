package com.jiangfw.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

public class TestDegest {

    /**
     * main.
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        //SOAPElement ele= soapBodyElement.addChildElement(envelope.createName("EncryptedData","","http://www.w3.org/2000/11/temp-xmlenc"));
        //ele.addChildElement("DigestMethod").addAttribute(envelope.createName("Algorithm"),"http://www.w3.org/2000/09/xmldsig#sha1");

        byte[] digest = new byte[100];
        ByteArrayOutputStream out = new ByteArrayOutputStream(100);
        MessageDigest md = MessageDigest.getInstance("SHA");
        ObjectOutputStream oos = new ObjectOutputStream(out);
        //要加密的信息
        String data = "<userName xsi:type='soapenc:string' xmlns:soapenc='http://schemas.xmlsoap.org/soap/encoding/'>22774484443</userName><password xsi:type='soapenc:string' xmlns:soapenc='http://schemas.xmlsoap.org/soap/encoding/'>e10adc3949ba59abbe56e057f20f883e</password>";
        byte[] buf = data.getBytes();
        byte[] aa = md.digest();
        byte[] databyte = data.getBytes();
        md.update(buf);
        oos.writeObject(data);
        //oos.writeObject(md.digest());
        digest = out.toByteArray();
        out.close();

        String val = new String(new Base64().encode(digest));
        System.out.println(val);

        System.out.println("*********************************************************************");
        //String aaaaa = "PHVzZXJOYW1lIHhzaTp0eXBlPSdzb2FwZW5jOnN0cmluZycgeG1sbnM6c29hcGVuYz0naHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvc29hcC9lbmNvZGluZy8nPjIyNzc0NDg0NDQzPC91c2VyTmFtZT48cGFzc3dvcmQgeHNpOnR5cGU9J3NvYXBlbmM6c3RyaW5nJyB4bWxuczpzb2FwZW5jPSdodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy9zb2FwL2VuY29kaW5nLyc+ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U8L3Bhc3N3b3JkPg==";
        System.out.println(decrypte(val));

        //ele.addChildElement("DigestValue").addTextNode(new
        //sun.misc.BASE64Encoder().encode(digest));//对加密的信息编码
    }

    /**
     * 解码.
     */
    public static String decrypte(String value) {
        String data = null;
        try {
            ByteArrayInputStream fis = new ByteArrayInputStream(
                    new Base64().decode(value.getBytes()));
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            if (!(o instanceof String)) {
                System.out.println("Unexpected data in string");
                System.exit(-1);
            }
            data = (String) o;
            System.out.println("解密后的值:" + data);
            //o = ois.readObject();
            //if (!(o instanceof byte[])) {
            //    System.out.println("Unexpected data in string");
            //    System.exit(-1);
            //}

            //byte origDigest[] = (byte[]) o;
            //MessageDigest md = MessageDigest.getInstance("SHA");
            //md.update(data.getBytes());
            //if (MessageDigest.isEqual(md.digest(), origDigest)) {
            //    System.out.println("Message 是有效的！");
            //} else {
            //    System.out.println("Message 已经损坏！");
            //}

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
