package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.idsmanager.micro.commons.utils.IdsBase64Utils;
import com.idsmanager.oidc.Constants;
import io.micrometer.core.instrument.util.StringUtils;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/*
  Created by IntelliJ IDEA.  
  User: Shaofei Du.  
  Date: 2019/8/1 
*/
public class RsaJweUtils {
    private static final Logger LOG = LoggerFactory.getLogger(RsaJweUtils.class);
    /**
     * 默认两分钟期限
     */
    private static long EXPIRE_TIME = 1000 * 60 * 2;


    /**
     * 使用RSA公钥加密JWE数据
     *
     * @param data            {"password":"123456","timestamp":"1564640346475"}
     * @param base64PublicKey
     * @return
     */
    public static String encryptRsaJwe(String data, String base64PublicKey) {
        if (data == null || data == null) {
            return null;
        }
        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512);
        //Connector公钥
        try {
            RSAPublicKey publicKey = new RsaJsonWebKey(JsonUtil.parseJson(IdsBase64Utils.decrypt(base64PublicKey))).getRsaPublicKey();
            jwe.setKey(publicKey);
            jwe.setPayload(data);
            final String serialization = jwe.getCompactSerialization();
            return IdsBase64Utils.base64Encode(serialization);
        } catch (JoseException e) {
            LOG.warn("[{}] - rsa jwe encrypt serialization error [{}]", 11, e);
            throw new IllegalStateException("rsa jwe encrypt serialization error", e);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用RSA私钥解密JWE数据
     *
     * @param data
     * @param base64PrivateKey
     * @return
     */
    public static String rsaJweDecrypt(String data, String base64PrivateKey) {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512);
        try {
            //Connector私钥
            RSAPrivateKey privateKey = new RsaJsonWebKey(JsonUtil.parseJson(IdsBase64Utils.decrypt(base64PrivateKey))).getRsaPrivateKey();
            jwe.setKey(privateKey);
            final byte[] decode = Base64.getDecoder().decode(data);
            jwe.setCompactSerialization(new String(decode, Constants.DEFAULT_CHARSET));
            return jwe.getPayload();
        } catch (JoseException e) {
            LOG.warn("[{}] - rsa jwe decrypt error [{}]",111, e);
            throw new IllegalStateException("rsa jwe decrypt error", e);
        }
    }

    public static JweUser getRsaUser(String rsaDecrypt) {
        JSONObject jsonObject = JSONObject.parseObject(rsaDecrypt);

        Object timestamp = jsonObject.get("timestamp");
        long currentTimeMillis = System.currentTimeMillis();

        long passwordTimeMillis = Long.valueOf(timestamp.toString());

        //默认密码有效期2分钟
        if (currentTimeMillis - passwordTimeMillis > EXPIRE_TIME) {
            return new JweUser(true);
        }
        Object password = jsonObject.get("password");

         return new JweUser(password.toString(),true);
    }

    public static class JweUser{
        /**
         * 解密后的明文密码
         */
        private String password;
        /**
         * 是否过期
         */
        private boolean expire;

        public JweUser(String password, boolean expire) {
            this.password = password;
            this.expire = expire;
        }

        public JweUser(boolean expire) {
            this.expire = expire;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isExpire() {
            return expire;
        }

        public void setExpire(boolean expire) {
            this.expire = expire;
        }

    }


}
