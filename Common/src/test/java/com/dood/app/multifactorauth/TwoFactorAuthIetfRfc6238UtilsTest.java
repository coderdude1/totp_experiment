package com.dood.app.multifactorauth;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//This is needed to get spring to work
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-application-context.xml" })
public class TwoFactorAuthIetfRfc6238UtilsTest extends TestCase {
    private static final Logger log = LoggerFactory.getLogger(TwoFactorAuthIetfRfc6238Utils.class);
    @Autowired
    private TwoFactorAuthIetfRfc6238Utils twoFactorAuthIetfRfc6238Utils;

    //these numbers are required to genearte a OTP.  I created them at a certain time and
    //used them in the Google Authenticator android app to generate the actual OTP for testing
    private String secret = "NBW7TMRLXGTMXRMC";
    private long timeInterval = 47179314l;
    private long otp = 20238L;//020238 generated from google auth android with above params
    private String otpStr = "020238";//20238 generated from google auth android with above params

    @Test // not really a useful t3est but more for me to run it and look
    public void testGenerateSharedSecret() throws Exception {
        String secret = twoFactorAuthIetfRfc6238Utils.generateSharedSecret();
        log.info("Secret: " + secret);
        assertNotNull(secret);
        assertEquals("length test", 16, secret.length());
    }

    @Test
    public void testVerifyCode() throws NoSuchAlgorithmException, InvalidKeyException {
        long code = twoFactorAuthIetfRfc6238Utils.getCode(secret, timeInterval, 1);
        assertEquals(otp, code);
        String codeStr = twoFactorAuthIetfRfc6238Utils.getCodeAsString(secret, timeInterval, 1);
        assertEquals("String", otpStr, codeStr);
        log.info("timeslice=" + twoFactorAuthIetfRfc6238Utils.getNowTimeIndex());
    }


}