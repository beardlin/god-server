package net.lantrack.framework.sysbase.util;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import java.io.UnsupportedEncodingException;

public class AESUtils {
	private static final String KEY_AES = "Lantrack_God_2.0";
	private static final String IV_STRING = "A-16-Byte-String";
	private static final String CHARSET = "UTF-8";

	
	/**
	 * 消息加密
	 * @param: 
	 * @author:luoxiaolin
	 * @date:2017年11月1日
	 */
	public static String aesEncryptString(String content)
			throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return aesEncryptString(content, KEY_AES);
	}


	public static String aesEncryptString(String content, String key)
			throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		byte[] contentBytes = content.getBytes(CHARSET);
		byte[] keyBytes = key.getBytes(CHARSET);
		byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(encryptedBytes);
	}


	public static String aesDecryptString(String content)
			throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return aesDecryptString(content, KEY_AES);
	}


	public static String aesDecryptString(String content, String key)
			throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		Decoder decoder = Base64.getDecoder();
		byte[] encryptedBytes = decoder.decode(content);
		byte[] keyBytes = key.getBytes(CHARSET);
		byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
		return new String(decryptedBytes, CHARSET);
	}

	public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
	}

	public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
	}

	private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes,
			int mode) throws UnsupportedEncodingException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

		byte[] initParam = IV_STRING.getBytes(CHARSET);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(mode, secretKey, ivParameterSpec);

		return cipher.doFinal(contentBytes);
	}

	/**
	 * 解密
	 * @param: 
	 * @author:luoxiaolin
	 * @date:2017年11月1日
	 */
	public static String aesDecrypt(String str) throws Exception{
		if(str!=null){
			str = str.replaceAll("\n","");
			if(str.contains("==")){
				return AESUtils.aesDecryptString(str);
			}
		}else{
			str="";
		}
		return str;
	}
	
	
	
	public static void main(String[] args) throws Exception {
	    
		String string = AESUtils.aesEncryptString("root");
		System.out.println(string);
		
//		System.out.println(AESUtils.aesDecrypt("oqXqDsUi9EV9SIYeLoUgsQ=="));
//		Encoder encoder = Base64.getEncoder();
//        String encodeString = encoder.encodeToString("admin:12345".getBytes());
//        System.out.println(encodeString);

	}

}
