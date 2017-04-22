package com.redoc.idu.utils.cache;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilities of Cache manager.
 * Created by limen on 2017/4/21.
 */

public class CacheUtils {

    /**
     * Append byte array b to byte array a.
     * @param org The former byte array.
     * @param to The later byte array.
     * @param length Length of the later byte array.
     * @return Appended bytes.
     */
    public static byte[] append(byte[] org, byte[] to, int length) {
        byte[] newByte = new byte[org.length + length];
        System.arraycopy(org, 0, newByte, 0, org.length);
        System.arraycopy(to, 0, newByte, org.length, length);
        return newByte;
    }

    /**
     * Generate hash key from url.
     * @param url The url.
     * @return Hash key.
     */
    public static String hashKeyFromUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b: bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if(hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
