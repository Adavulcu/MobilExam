package com.example.mobilexam;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public  class MyAes {




    public  String Encrypt(String data,String mykey,String IV) {
        try {

            byte[] key=Base64.decode(mykey,0);
            byte[] i=Base64.decode(IV,0);
            IvParameterSpec iv = new IvParameterSpec(i);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
            String e=Base64.encodeToString(encrypted,Base64.NO_WRAP);
            return e;
        } catch (Exception ex) {
            Log.e("Encrypt",ex.getMessage());
        }
        return null;
    }

    public  String Decrypt(String data,String mykey,String IV) {
        try {

            byte[] key=Base64.decode(mykey,0);
            byte[] i=Base64.decode(IV,0);
            IvParameterSpec iv = new IvParameterSpec(i);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
            String e=Base64.encodeToString(encrypted,Base64.NO_WRAP);
            return e;
        } catch (Exception ex) {
            Log.e("Decrypt",ex.getMessage());
        }
        return null;
    }

    }



