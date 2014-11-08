package com.dood.app.multifactorauth;

import org.apache.commons.codec.binary.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Utilities for the 2 factor auth as defined by IETF RFC 6238 and implemetend at least
 * by google authentitocator, maybe others.  Will maybe rename after testing
 */
@Component
public class TwoFactorAuthIetfRfc6238Utils {
    private static final Logger log = LoggerFactory.getLogger(TwoFactorAuthIetfRfc6238Utils.class);
    /**
     * Google Auth requires a 16 character key for the shared secret.
     *
     * •A 16 characters Base32 encoded secret key: since Base32 encoding of x bytes generate 8x/5 characters
     * therefore we will use 10 bytes for the secret key.
     * @return
     */
    public String generateSharedSecret() {
        byte[] buffer = new byte[10];
        new SecureRandom().nextBytes(buffer);
        String secret = new String(new Base32().encode(buffer));
        log.info("secret " + secret);
        return secret;
    }

    /**
     * Validates the user input code against the shared secret, and the current time index
     * with variance intervals around the current time index.  Intervals are 30 second chunks
     * to match the client code generator
     *
     * @param secret
     * @param code
     * @param timeIndex
     * @param variance - +/- number of variables to check around the current time interval
     * @return
     * @throws Exception
     */
    public boolean verifyCode(String secret, int code, long timeIndex, int variance)
            throws Exception {
        byte[] secretBytes = new Base32().decode(secret);
        for (int i = -variance; i <= variance; i++) {
            if (getCode(secretBytes, timeIndex + i) == code) {
                return true;
            }
        }
        return false;
    }

    /**
     * Left zero pads the string to 6 digits
     * @param secret
     * @param timeIndex
     * @param varience
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public String getCodeAsString(String secret, long timeIndex, int varience) throws InvalidKeyException, NoSuchAlgorithmException {
        long code = getCode(secret, timeIndex, varience);
        String codeStr = String.format("%06d", code);
        return codeStr;
    }

    public long getCode(String secret, long timeIndex, int varience) throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] secretBytes = new Base32().decode(secret);
        return getCode(secretBytes, timeIndex);
    }

    /**
     * This is currently magic, need to reseach why it is this way as this was gratuitiously
     * copied from https://weblogs.java.net/blog/evanx/archive/2012/11/07/google-authenticator-thus-enabled
     *
     * https://tools.ietf.org/html/rfc6238
     *
     */
    public long getCode(byte[] secret, long timeIndex)
            throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signKey = new SecretKeySpec(secret, "HmacSHA1");
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(timeIndex);
        byte[] timeBytes = buffer.array();
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(timeBytes);
        int offset = hash[19] & 0xf;
        long truncatedHash = hash[offset] & 0x7f;
        for (int i = 1; i < 4; i++) {
            truncatedHash <<= 8;
            truncatedHash |= hash[offset + i] & 0xff;
        }
        return (truncatedHash %= 1000000);
    }

    /**
     * the time interval for the auth change is 30 secons.  this will
     * return the current time index i.e. the number of 30s intervals since the
     * UNIX time epoch.
     * @return
     */
    public static long getTimeIndex() {
        return System.currentTimeMillis()/1000/30;
    }
}
