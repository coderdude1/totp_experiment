package com.dood.app.service;

import java.util.Date;

/**
 * for now call TotpService.  If it turns out only google authenticator works with it
 * rename as appropriate
 *
 * Will look for i intervals before and after the entered time, configured in the implementation
 */
public interface TotpService {
    String generateNewTotpSecret();

    /**
     * Used to verify a user entry.
     * @param secret - shared secret used to prime the authenticator
     * @param inputCode - code the user entered
     * @param time time the code was genearted
     * @return
     */
    boolean verifyCode(String secret, int inputCode, Date time);
}
