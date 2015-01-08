package com.dood.app.service.impl;

import com.dood.app.multifactorauth.TwoFactorAuthIetfRfc6238Utils;
import com.dood.app.service.TotpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service
public class TotpServiceImpl implements TotpService {
    private static final Logger log = LoggerFactory.getLogger(TotpServiceImpl.class);
    private int intervalVariance = 1;//TODO use some spring magic to load this from config file/db?
    @Autowired
    private TwoFactorAuthIetfRfc6238Utils twoFactorAuthIetfRfc6238Utils;

    @Override
    public String generateNewTotpSecret() {
        return twoFactorAuthIetfRfc6238Utils.generateSharedSecret();
    }

    /**
     * If there is an exception during verification, fails safe, ie will return false
     * @param secret - shared secret used to prime the authenticator
     * @param inputCode - code the user entered
     * @param time time the code was genearted
     * @return
     */
    @Override
    public boolean verifyCode(String secret, int inputCode, Date time) {
        Date now = new Date();
        boolean verified = false;
        try {
            verified = twoFactorAuthIetfRfc6238Utils.verifyCode(secret, inputCode, now, intervalVariance);
        } catch (Exception e) {
            log.error("Error during two factor auth verification", e);
        }
        return verified;
    }
}
