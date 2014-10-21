package com.dood.app.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * simple utility to output a bcrypt hash to put into the db for default admin.  also
 * to play around with the spring bcrypt stuff
 */
public class PasswordUtils {
    public static void main(String[] args) {
        String password = "tmp14now";
        String gensalt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, gensalt);
        System.out.println("Salt=" + gensalt);
        System.out.println("Hashed Password: " + hashedPassword);
        System.out.println("Password Lenght: " + hashedPassword.length());

        String oneHash = "$2a$10$z010gdbxLC15X.NWpXfoEuypspGeKi8NKlO2R6LL2DDmLQxI56yTm";
        String twoHash = "$2a$10$ipMbW.Ij2VvkePc3TJTA3OcH8OYA0fxgf7Smg/cAeYhMlFCbaBbjW";
        System.out.println("tmp14now=" + BCrypt.checkpw(password, oneHash));
        System.out.println("tmp14now=" + BCrypt.checkpw(password, twoHash));
    }
}
