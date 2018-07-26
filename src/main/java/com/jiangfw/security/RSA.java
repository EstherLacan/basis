package com.jiangfw.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Objects;


public class RSA {
	public static String src="jiangfw";

	public static void main(String[] args){
		try {
			jdkRSA();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void jdkRSA() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		//初始化发送方的密钥
		KeyPairGenerator senderKeyPairGenerator=KeyPairGenerator.getInstance("RSA");
		senderKeyPairGenerator.initialize(512);
		KeyPair senderKeyPair=senderKeyPairGenerator.generateKeyPair();
		RSAPublicKey rsaPublicKey=(RSAPublicKey) senderKeyPair.getPublic();
		RSAPrivateKey rsaPrivateKey=(RSAPrivateKey) senderKeyPair.getPrivate();
//		System.out.println("rsaPublicKey:"+Base64.encodeBase64String(rsaPublicKey.getEncoded()));
//		System.out.println("rsaPrivateKey:"+Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
		
		
		//加密
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
		KeyFactory keyFactory=KeyFactory.getInstance("RSA");
		PrivateKey privateKey =keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		if(Objects.equals(privateKey, rsaPrivateKey)){
			System.out.println("************************");
		}
		
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] res=cipher.doFinal(src.getBytes());
//		System.out.println(Base64.encodeBase64String(res));
		
		
		//解密
		cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
		byte[] resSrc=cipher.doFinal(res);
		System.out.println(new String(resSrc));
		
	}
	
}
