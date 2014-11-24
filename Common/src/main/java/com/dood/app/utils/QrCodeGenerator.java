package com.dood.app.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Generic class to create a QR code
 */
@Service
public class QrCodeGenerator {
    private static final Logger log = LoggerFactory.getLogger(QrCodeGenerator.class);

    private static final String OTP_AUTH_TOTP_URI_BASE = "otpauth://totp/%s?secret=%s";

    private int height = 125;
    private int width = 125;

    public void generateTwoFactorAuthQrCode(String host, String secret, File qrFile) {
        String otpUrl = String.format(OTP_AUTH_TOTP_URI_BASE, host, secret);
        generateQrCode(otpUrl, qrFile);
    }

    /**
     * Populates the qrFile with a QR code png image
     * @param stuffToEncode
     * @param qrFile
     */
    public void generateQrCode(String stuffToEncode, File qrFile) {
        String fileType = "png";
        try {
            BufferedImage image = createQrImage(stuffToEncode);
            ImageIO.write(image, fileType, qrFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("QR Code Created.  height: {} widht: {} stuffToEncode: {} ", height, width, stuffToEncode);
    }

    public BufferedImage createGoogleAuthQrCode(String host, String secret) throws WriterException {
        String otpUrl = String.format(OTP_AUTH_TOTP_URI_BASE, host, secret);
        return createQrImage(otpUrl);
    }

    /**
     * Returns a bufferedImage containing the contents of the QRCode.
     * @param stuffToEncode
     * @return
     * @throws WriterException
     */
    public BufferedImage createQrImage(String stuffToEncode) throws WriterException {
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(stuffToEncode, BarcodeFormat.QR_CODE, width, height, hintMap);
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
        return image;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}