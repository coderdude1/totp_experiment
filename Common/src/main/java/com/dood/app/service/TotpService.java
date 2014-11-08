package com.dood.app.service;

/**
 * for now call TotpService.  If it turns out only google authenticator works with it
 * rename as appropriate
 */
public interface TotpService {
    String generateNewTotpSecret();
}
