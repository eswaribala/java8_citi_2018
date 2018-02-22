package com.polaris.utility;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecrypt {

	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		// here are your inputs
        String keyString = "encryptdata!@$@#$#@$#*&(*&}{23432432432dsfsdf";
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Name to Encrypt");
        String input = scanner.nextLine();

        // setup AES cipher in CBC mode with PKCS #5 padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // setup an IV (initialization vector) that should be
        // randomly generated for each input that's encrypted
        byte[] iv = new byte[cipher.getBlockSize()];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // hash keyString with SHA-256 and crop the output to 128-bit for key
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(keyString.getBytes());
        byte[] key = new byte[16];
        System.arraycopy(digest.digest(), 0, key, 0, key.length);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        // encrypt
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(input.getBytes("UTF-8"));
        System.out.println("encrypted: " + new String(encrypted));

        // include the IV with the encrypted bytes for transport, you'll
        // need the same IV when decrypting (it's safe to send unencrypted)

        // decrypt
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("decrypted: " + new String(decrypted, "UTF-8"));
	}

}
