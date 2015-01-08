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
import java.io.IOException;

/**
 *  Used to redirect a successfully authenticated user to the two factor screen if they have 2 factor
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

        System.out.println("place to hang breakpoint");
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

   /*
   get userid
   if(user.2factoraut.enabled
        redirect to enter jcode
   else act like SavedRequestAwareAuthSuccessHandler
    */

        redirectStrategy.sendRedirect(request, response, targetUrl);

    }

    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
        User user = userDao.findByEmail(authentication.getName());
        if(user.isTwoFactorAuthEnabled()) {
            return "/twofactorauth";
        } else {
            //when I extend SavedRequestAwareAuthenticationSuccessHandler
//            super.onAuthenticationSuccess(request, response, authentication);//no 2 factor auth
            return "/";
        }
    }
}
