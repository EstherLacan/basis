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
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.jiangfw.security.BytesToHex;

/**
 * 数字签名
 * @author jiangfengwei
 *
 */
public class RSASign {

	private static String src="jiangfw";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			sign();
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
	public static void sign() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		// 初始化发送方的密钥
		KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator
				.getInstance("RSA");
		senderKeyPairGenerator.initialize(512);
		KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
		RSAPublicKey rsaPublicKey = (RSAPublicKey) senderKeyPair.getPublic();
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) senderKeyPair.getPrivate();
//		System.out.println("rsaPublicKey:"	+ Base64.encodeBase64String(rsaPublicKey.getEncoded()));
//		System.out.println("rsaPrivateKey:"+ Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
		
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
		KeyFactory keyFactory=KeyFactory.getInstance("RSA");
		PrivateKey privateKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Signature signature=Signature.getInstance("MD5withRSA");
		signature.initSign(privateKey);
		signature.update(src.getBytes());
		byte[] sign=signature.sign();
		System.out.println(BytesToHex.fromBytesToHex(sign));
		
		X509EncodedKeySpec x509EncodedKeySpec =new X509EncodedKeySpec(rsaPublicKey.getEncoded());
		keyFactory=KeyFactory.getInstance("RSA");
		PublicKey publicKey=keyFactory.generatePublic(x509EncodedKeySpec);
		signature=Signature.getInstance("MD5withRSA");
		signature.initVerify(publicKey);
		signature.update(src.getBytes());
		boolean res=signature.verify(sign);
		System.out.println("the result is "+res);

	}
}
