package com.jiangfw.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

public class DH {
	public static String src="jiangfw";

	public static void main(String[] args){
		try {
			jdkDH();
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
	
	public static void jdkDH() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		//初始化发送方的密钥
		KeyPairGenerator senderKeyPairGenerator=KeyPairGenerator.getInstance("DH");
		senderKeyPairGenerator.initialize(512);
		KeyPair senderKeyPair=senderKeyPairGenerator.generateKeyPair();
		byte[] sendPublicKeyEnc=senderKeyPair.getPublic().getEncoded();//发送方公钥，给接收方
//		System.out.println("sendPublicKeyEnc:"+Base64.encodeBase64String(sendPublicKeyEnc));
		
		
		//2初始化接收方密钥
		KeyFactory receiverKeyFactory =KeyFactory.getInstance("DH");
		X509EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(sendPublicKeyEnc);
		PublicKey receiverPublicKey=receiverKeyFactory.generatePublic(x509EncodedKeySpec);
		DHParameterSpec dhParameterSpec=((DHPublicKey)receiverPublicKey).getParams();
		KeyPairGenerator receiverKeyPairGenerator=KeyPairGenerator.getInstance("DH");
		receiverKeyPairGenerator.initialize(dhParameterSpec);
		KeyPair receiverKeyPair=receiverKeyPairGenerator.generateKeyPair();
		byte[] receiverPublicKeyEnc=receiverKeyPair.getPublic().getEncoded();
		
		//3密钥构建
		KeyAgreement receiverKeyAgreement=KeyAgreement.getInstance("DH");
		receiverKeyAgreement.init(receiverKeyPair.getPrivate());
		receiverKeyAgreement.doPhase(receiverPublicKey, true);
		SecretKey receiverSecretKey=receiverKeyAgreement.generateSecret("DES");
		
		
		KeyFactory senderKeyFactory =KeyFactory.getInstance("DH");
		x509EncodedKeySpec=new X509EncodedKeySpec(receiverPublicKeyEnc);
		PublicKey senderPublicKey=senderKeyFactory.generatePublic(x509EncodedKeySpec);
		KeyAgreement senderKeyAgreement=KeyAgreement.getInstance("DH");
		senderKeyAgreement.init(senderKeyPair.getPrivate());
		senderKeyAgreement.doPhase(senderPublicKey, true);
		
		SecretKey senderSecretKey=senderKeyAgreement.generateSecret("DES");
		
		
		if(Objects.equals(receiverSecretKey, senderSecretKey)){
			System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		}
		
		//加密
		Cipher cipher=Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, senderSecretKey);
		byte[] res=cipher.doFinal(src.getBytes());
//		System.out.println(Base64.encodeBase64String(res));
		
		
		//解密
		cipher.init(Cipher.DECRYPT_MODE, receiverSecretKey);
		byte[] resSrc=cipher.doFinal(res);
		System.out.println(new String(resSrc));
		
	}
	
}
