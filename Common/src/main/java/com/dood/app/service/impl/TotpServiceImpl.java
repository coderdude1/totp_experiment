package com.dood.app.service.impl;

import com.dood.app.multifactorauth.TwoFactorAuthIetfRfc6238Utils;
import com.dood.app.service.TotpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TotpServiceImpl implements TotpService {
    @Autowired
    private TwoFactorAuthIetfRfc6238Utils twoFactorAuthIetfRfc6238Utils;

    @Override
    public String generateNewTotpSecret() {
        return twoFactorAuthIetfRfc6238Utils.generateSharedSecret();
    }
}
