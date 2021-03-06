package nuesoft.helpdroid;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

import nuesoft.helpdroid.crypto.CryptoUtil;
import nuesoft.helpdroid.crypto.HmacType;
import nuesoft.helpdroid.util.Converter;

public class CryptoUtilTest {
    private static final String PKDF2_HMAC_SHA1 = "PBKDF2WithHmacSHA1";
    private static final String ENCRYPTION_ALGORITHM = "AES";

    @Test
    public void testGenerateRandom_success() {

        byte[] random = CryptoUtil.getSecureRandom(32);
        Assert.assertNotNull(random);
    }

    @Test
    public void testGenerateKey_pbkdf2WithHmacSha1() {

        byte[] pin = new byte[16];
        byte[] salt = new byte[16];
        SecretKey secretKey = CryptoUtil.keyDerivationBasedOnPBE(pin, salt, PKDF2_HMAC_SHA1, ENCRYPTION_ALGORITHM, 1000, 512);
        Assert.assertNotNull(secretKey);
    }

    @Test
    public void testEncrypt_aes128() {

        byte[] iv = new byte[16];
        byte[] key = new byte[16];
        byte[] plainText = null;
        try {
            plainText = "This is plain text".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            Assert.fail(e.getMessage());
        }
        byte[] cipherText = CryptoUtil.encryptAesCbcPkcs5Padding(key, iv, plainText);
        Assert.assertNotNull(cipherText);
        String cipherTextString = java.util.Base64.getEncoder().encodeToString(cipherText);
        Assert.assertEquals(cipherTextString, "dZYt1sj/enRcMesqFJ08qR5t1QVYq6GWSzCKQjr18hs=");
    }

    @Test
    public void testDecrypt_aes128() {

        byte[] iv = new byte[16];
        byte[] key = new byte[16];
        byte[] cipherText = java.util.Base64.getDecoder().decode("dZYt1sj/enRcMesqFJ08qR5t1QVYq6GWSzCKQjr18hs=");
        byte[] plainText = CryptoUtil.decryptAesCbcPkcs5Padding(key, iv, cipherText);
        String plainTextString = null;
        try {
            plainTextString = new String(plainText, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
        Assert.assertEquals(plainTextString, "This is plain text");
    }

    @Test
    public void testEncrypt_aes256() {

        byte[] iv = new byte[16];
        byte[] key = new byte[32];
        byte[] plainText = null;
        try {
            plainText = "This is plain text".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            Assert.fail(e.getMessage());
        }
        byte[] cipherText = CryptoUtil.encryptAesCbcPkcs5Padding(key, iv, plainText);
        Assert.assertNotNull(cipherText);
        String cipherTextString = java.util.Base64.getEncoder().encodeToString(cipherText);
        Assert.assertEquals(cipherTextString, "/I7IYZYFF/SEpJ1A8QQZsiD4lAKjALR6v54JMBBg4cE=");
    }

    @Test
    public void testDecrypt_aes256() {

        byte[] iv = new byte[16];
        byte[] key = new byte[32];
        byte[] cipherText = java.util.Base64.getDecoder().decode("/I7IYZYFF/SEpJ1A8QQZsiD4lAKjALR6v54JMBBg4cE=");
        byte[] plainText = CryptoUtil.decryptAesCbcPkcs5Padding(key, iv, cipherText);
        String plainTextString = null;
        try {
            plainTextString = new String(plainText, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
        Assert.assertEquals(plainTextString, "This is plain text");
    }

    @Test
    public void testHmac_sha1() {

        byte[] key = new byte[20];
        byte[] data = null;
        try {
            data = "This is plain text".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
        try {
            byte[] hmac = CryptoUtil.hmac(HmacType.HmacSHA1, key, data);
            String hmacString = java.util.Base64.getEncoder().encodeToString(hmac);
            Assert.assertEquals(hmacString, "XfzCnGusYM78erq6EhiQJX6J6vg=");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testHmac_sha256() {

        byte[] key = new byte[32];
        byte[] data = null;
        try {
            data = "This is plain text".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
        try {
            byte[] hmac = CryptoUtil.hmac(HmacType.HmacSHA256, key, data);
            String hmacString = java.util.Base64.getEncoder().encodeToString(hmac);
            Assert.assertEquals(hmacString, "tYgQGIcUErNsTqx8CJmvSYaKvjNMUnMwk/9GLlamIak=");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testHmac_sha384() {

        byte[] key = new byte[48];
        byte[] data = null;
        try {
            data = "This is plain text".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
        try {
            byte[] hmac = CryptoUtil.hmac(HmacType.HmacSHA384, key, data);
            String hmacString = java.util.Base64.getEncoder().encodeToString(hmac);
            Assert.assertEquals(hmacString, "KFFdQhVxt7r9M2zOFcI+KZJ/0xH+FlC+Rrf3bwMlCnaErSjJpixZPeKhmi+itZql");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testHmac_sha512() {

        byte[] key = new byte[64];
        byte[] data = null;
        try {
            data = "This is plain text".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
        try {
            byte[] hmac = CryptoUtil.hmac(HmacType.HmacSHA512, key, data);
            String hmacString = java.util.Base64.getEncoder().encodeToString(hmac);
            Assert.assertEquals(hmacString, "j6RRloBBwaM+Ju0BZETEt/qM7swGSjlA0N7XKMUpICBAMsytmapjY5DnrLu9FuhAcmz1gCJZVRCL/JaPo3tOIA==");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testDigest_sha128() {

        String value = "This is sample text";
        String digest = Converter.bytesToHexString(CryptoUtil.sha128(value));
        Assert.assertEquals(digest, "451d99c8281f579bdf1e2b0f0a2a63fd23707037".toUpperCase());
    }

    @Test
    public void testDigest_sha256() {

        String value = "This is sample text";
        String digest = Converter.bytesToHexString(CryptoUtil.sha256(value));
        Assert.assertEquals(digest, "bf67a78f571d7ecad67ad2a5ea064edc969def57649c69fbb22eb72f4c56f87a".toUpperCase());
    }

    @Test
    public void testDigest_sha512() {

        String value = "This is sample text";
        String digest = Converter.bytesToHexString(CryptoUtil.sha512(value));
        Assert.assertEquals(digest, "cce3233e201810a61541ecc0e2a69bda7de751d80f35c2fcd70b19bd0612543ae18885a1d7d32f3c460cc5cab91268a93b3d04431d3b14d6eee1e44954a4513b".toUpperCase());
    }
}