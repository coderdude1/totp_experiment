package com.dood.app.utils;

import junit.framework.TestCase;

import java.io.File;

public class QrCodeGeneratorTest extends TestCase {

    public void testGenerateQrCode() throws Exception {
        File qrTempFile = File.createTempFile("qr_test", ".png");
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        qrCodeGenerator.setHeight(400);
        qrCodeGenerator.setWidth(400);
        qrCodeGenerator.generateTwoFactorAuthQrCode("rwHost", "NBW7TMRLXGTMXRMC", qrTempFile);

        //opened the qr code with my phone, it loaded into GoogleAuthenticator just fine
    }
}