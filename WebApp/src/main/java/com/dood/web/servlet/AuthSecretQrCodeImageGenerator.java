package com.dood.web.servlet;

import com.dood.app.dao.UserDao;
import com.dood.app.entities.User;
import com.dood.app.utils.QrCodeGenerator;
import com.google.zxing.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Given a user id, it will generate a qr code using the user secret, allowing scanning into the google auth app
 */
@WebServlet(urlPatterns = {"/authSecretQrImages"})
public class AuthSecretQrCodeImageGenerator extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(QrCodeGenerator.class);
    @Autowired
    private UserDao userDao;

    public void init(ServletConfig config) {
        try {
            super.init(config);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        response.setContentType("image/png");
        QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();
        BufferedImage bi = null;
        try {
            User user = userDao.findOne(userId);
            bi = qrCodeGenerator.createQrImage(user.getTotpSecret());
        } catch (WriterException e) {
            e.printStackTrace();
        }

        OutputStream out = response.getOutputStream();
        ImageIO.write(bi, "png", out);
        out.close();
    }
}
