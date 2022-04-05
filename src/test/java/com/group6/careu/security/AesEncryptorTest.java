package com.group6.careu.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
//import javax.security.auth.kerberos.EncryptionKey;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AesEncryptorTest {
    @Autowired
    private AesEncryptor aesEncryptor;

//
//    @Test
//    void testGetKey2() throws UnsupportedEncodingException {
//        //   Diffblue Cover was unable to write a Spring test,
//        //   so wrote a non-Spring test instead.
//        //   Reason: R004 No meaningful assertions found.
//        //   Diffblue Cover was unable to create an assertion.
//        //   Make sure that fields modified by getKey()
//        //   have package-private, protected, or public getters.
//        //   See https://diff.blue/R004 to resolve this issue.
//
//        AesEncryptor aesEncryptor = new AesEncryptor();
//        EncryptionKey encryptionKey = new EncryptionKey("AAAAAAAA".getBytes("UTF-8"), 1);
//
//        aesEncryptor.setKey(encryptionKey);
//        assertSame(encryptionKey, aesEncryptor.getKey());
//    }


    @Test
    void testGetCipher2() throws GeneralSecurityException {

        AesEncryptor aesEncryptor = new AesEncryptor();
        Cipher instance = Cipher.getInstance("AES");
        aesEncryptor.setCipher(instance);
        assertSame(instance, aesEncryptor.getCipher());
    }

    @Test
    void testConvertToDatabaseColumn() {
        assertEquals("cVpKyQS/teQWqy6Cg5UQWu5YofwA1Lqnwcr+rJQ/h/M=",
                this.aesEncryptor.convertToDatabaseColumn("Attribute"));
        assertEquals("ec0UsDBKYCtqzG94LkaZMlrb0gCB2fT0LujldeikHfgzzk4+MQDIA00jICm20Y4rrFWyZZcemFrgjSpNDDaZjUaDnamgB438YXRq"
                + "Am6Y6HLzLr93BvW57T8zbZcfX3a6", this.aesEncryptor.convertToDatabaseColumn(1));
        assertNull(this.aesEncryptor.convertToDatabaseColumn(null));
    }


    @Test
    void testConvertToEntityAttribute2() {
        assertNull(this.aesEncryptor.convertToEntityAttribute(null));
    }

    @Test
    void testConstructor() {
        AesEncryptor actualAesEncryptor = new AesEncryptor();
        assertEquals("AES", actualAesEncryptor.getEncryptionCipher());
        assertEquals("this-is-test-key", actualAesEncryptor.getEncryptionKey());
    }
}

