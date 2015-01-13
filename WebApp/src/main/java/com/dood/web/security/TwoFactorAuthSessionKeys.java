package com.dood.web.security;

/**
 * Keys used to store 2fa related session info, required for filters and such to ensure
 * the 2fa is not bypassed.
 *
 * Since this data really only matters in the scope of a web session, we'll store it in
 * the session to eliminate db lookups on each request.  the info may be sourced from the db
 * at some point, and the db may track audit info around this but request to request stuff
 * avoid db hits
 */
public enum TwoFactorAuthSessionKeys {
    TWO_FACTOR_AUTH_ENABLED,
    TWO_FACTOR_AUTH_SUCCESSFUL
}
