package com.group6.careu.security;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Configuration
public class AesEncryptor implements AttributeConverter<Object, String> {

    private String encryptionKey = "this-is-test-key";

    private final String encryptionCipher = "AES";

    private Key key;
    private Cipher cipher;


    public String getEncryptionKey() {
        return encryptionKey;
    }

    public String getEncryptionCipher() {
        return encryptionCipher;
    }

    public Key getKey() {
        if (key == null){
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
        }
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Cipher getCipher() throws GeneralSecurityException {
        if (cipher==null){
            cipher = cipher.getInstance(encryptionCipher);
        }
        return cipher;
    }

    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }
    private void initCipher(int encryptMode) throws GeneralSecurityException{
        getCipher().init(encryptMode, getKey());
    }

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Object attribute) {
        if (attribute == null){
            return null;
        }
        initCipher(cipher.ENCRYPT_MODE);
        byte[] bytes = SerializationUtils.serialize(attribute);
        return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
    }

    @SneakyThrows
    @Override
    public Object convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        initCipher(cipher.DECRYPT_MODE);
        byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));
        return SerializationUtils.deserialize(bytes);

    }
}
