package com.cefetransport.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    
    public static String md5(String senha) throws NoSuchAlgorithmException {

        MessageDigest messageDig = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, messageDig.digest(senha.getBytes()));
        return hash.toString(16);

    }

}