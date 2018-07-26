package com.jiangfw.security.signature;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.jiangfw.security.BytesToHex;

/**
 * 数字签名
 * @author jiangfengwei
 *
 */
public class ECDSASign {

	private static String src="jiangfw";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ecdsaSign();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws SignatureException 
	 */
	public static void ecdsaSign() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		// 初始化发送方的密钥
		KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("EC");
		senderKeyPairGenerator.initialize(256);
		KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
		ECPublicKey ecPublicKey = (ECPublicKey) senderKeyPair.getPublic();
		ECPrivateKey ecPrivateKey = (ECPrivateKey) senderKeyPair.getPrivate();
//		System.out.println("ECDSAPublicKey:"	+ Base64.encodeBase64String(ecPublicKey.getEncoded()));
//		System.out.println("ECDSAPrivateKey:"+ Base64.encodeBase64String(ecPrivateKey.getEncoded()));
		
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
		KeyFactory keyFactory=KeyFactory.getInstance("EC");
		PrivateKey privateKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Signature signature=Signature.getInstance("SHA1withECDSA");
		signature.initSign(privateKey);
		signature.update(src.getBytes());
		byte[] sign=signature.sign();
		System.out.println(BytesToHex.fromBytesToHex(sign));
		
		X509EncodedKeySpec x509EncodedKeySpec =new X509EncodedKeySpec(ecPublicKey.getEncoded());
		keyFactory=KeyFactory.getInstance("EC");
		PublicKey publicKey=keyFactory.generatePublic(x509EncodedKeySpec);
		signature=Signature.getInstance("SHA1withECDSA");
		signature.initVerify(publicKey);
		signature.update(src.getBytes());
		boolean res=signature.verify(sign);
		System.out.println("the result is "+res);

	}
}
