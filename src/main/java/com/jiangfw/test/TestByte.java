package com.jiangfw.test;

import com.jiangfw.common.media.UUID;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class TestByte {

    public static void main(String[] args) {

        int aa = 10;
        int bb = 21;
        System.out.println("a:" + aa + ",b:" + bb);
        aa = aa ^ bb;
        bb = aa ^ bb;
        aa = aa ^ bb;
//		System.out.println("a:"+aa+",b:"+bb);
//		System.out.println(aa^0);
//		System.out.println(aa^1);
//		System.out.println(Integer.toBinaryString(aa));
//		System.out.println(Integer.toBinaryString(~(aa^0)));
//		System.out.println(Integer.toBinaryString(aa^~0));
//		System.out.println(Integer.toBinaryString(~aa));
//		System.out.println(Integer.toBinaryString(~0));
//		System.out.println(Integer.reverse(-2147483648));
//		System.out.println(~aa);
//		System.out.println(~(~aa-1));

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());


        String value = System.currentTimeMillis() + new Random().nextInt() + "";
        //获取数据指纹，指纹是唯一的
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] b = md.digest(value.getBytes());//产生数据的指纹
            //Base64编码
            BASE64Encoder be = new BASE64Encoder();
            be.encode(b);
            System.out.println(be.encode(b));
//					return be.encode(b);//制定一个编码
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

}
