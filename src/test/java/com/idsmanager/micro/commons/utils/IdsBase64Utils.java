//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.idsmanager.micro.commons.utils;

import com.idsmanager.micro.commons.Constants;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.util.Base64Utils;

public abstract class IdsBase64Utils implements Constants {
    public static final String KEY = "96583a*puq1Nb5h7";
    private static final String AES = "AES";
    private static final String PKCS = "AES/ECB/PKCS5Padding";

    protected IdsBase64Utils() {
    }

    public static String base64Encode(String username, String password) {
        String text = username + ":" + password;
        return base64Encode(text);
    }

    public static String base64Encode(String text) {
        return Base64Utils.encodeToString(text.getBytes(DEFAULT_CHARSET));
    }

    public static String base64Decode(String decodeText) {
        byte[] decode = Base64Utils.decodeFromString(decodeText);
        return new String(decode, DEFAULT_CHARSET);
    }

    public static String encrypt(String sSrc) {
        return encrypt(sSrc, "96583a*puq1Nb5h7");
    }

    public static String encrypt(String sSrc, String key) {
        if (sSrc != null && key != null) {
            try {
                byte[] raw = key.getBytes("UTF-8");
                SecretKeySpec skeySpec = getSecretKeySpec(raw);
                Cipher cipher = getCipher();
                cipher.init(1, skeySpec);
                byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
                return Base64Utils.encodeToString(encrypted);
            } catch (Exception var6) {
                throw new IllegalStateException(var6);
            }
        } else {
            return null;
        }
    }

    public static String decrypt(String sSrc) {
        return decrypt(sSrc, "96583a*puq1Nb5h7");
    }

    public static String decrypt(String sSrc, String key) {
        if (sSrc != null && key != null) {
            try {
                byte[] raw = key.getBytes("UTF-8");
                SecretKeySpec skeySpec = getSecretKeySpec(raw);
                Cipher cipher = getCipher();
                cipher.init(2, skeySpec);
                byte[] encrypted1 = Base64Utils.decodeFromString(sSrc);
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original, "UTF-8");
            } catch (Exception var7) {
                throw new IllegalStateException(var7);
            }
        } else {
            return null;
        }
    }

    private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    private static SecretKeySpec getSecretKeySpec(byte[] raw) {
        return new SecretKeySpec(raw, "AES");
    }
}
