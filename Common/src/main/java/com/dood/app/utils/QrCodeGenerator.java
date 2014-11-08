package com.dood.app.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Generic class to create a QR code
 */
public class QrCodeGenerator {
    public static final String authCodeUrl = "otpauth://totp/%s@%s%%3Fsecret%%3D%s";
    private static final String OTP_AUTH_TOTP_URI_BASE = "otpauth://totp/%s?secret=%s";

    public void generateTwoFactorAuthQrCode(String host, String secret, File qrFile) {
        String otpUrl = String.format(OTP_AUTH_TOTP_URI_BASE, host, secret);
        generateQrCode(otpUrl, qrFile);
    }

    public void generateQrCode(String stuffToEncode, File qrFile) {
        int size = 125;
        String fileType = "png";
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(stuffToEncode, BarcodeFormat.QR_CODE, size, size, hintMap);
            int qrCodeWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(qrCodeWidth, qrCodeWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, qrCodeWidth, qrCodeWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < qrCodeWidth; i++) {
                for (int j = 0; j < qrCodeWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, qrFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nYou have successfully created QR Code.");
    }
}