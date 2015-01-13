package com.dood.web.security;

import com.dood.app.dao.UserDao;
import com.dood.app.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Used to redirect after spring successfully authenticates a user to the two factor screen if they have 2 factor
 *  auth enabled.
 *
 *  http://www.baeldung.com/spring_redirect_after_login
 */
@Component
public class TwoFactorAuthSavedRequestAuthenticationHandler implements AuthenticationSuccessHandler { //SavedRequestAwareAuthenticationSuccessHandler { //SimpleUrlAuthenticationSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(TwoFactorAuthSavedRequestAuthenticationHandler.class);
    @Autowired
    private UserDao userDao;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        String targetUrl = determineTargetUrl(request, authentication);
        log.debug("targetURL={}", targetUrl);

        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(HttpServletRequest request, Authentication authentication) {
        User user = userDao.findByEmail(authentication.getName());
        log.debug("userAuthenticated user={}", user);
        if(user.isTwoFactorAuthEnabled()) {
            HttpSession session = ((HttpServletRequest)request).getSession();
            session.setAttribute(TwoFactorAuthSessionKeys.TWO_FACTOR_AUTH_ENABLED.toString(),
                    true);
            return "/twofactorauth";
        } else {
            return "/";
        }
    }
}
