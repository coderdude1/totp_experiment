package com.dood.web.security;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by randy on 1/8/2015.
 */
public class TwoFactorAuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
/*
Prevents bypassing of the 2fa screen if a user has 2fa enabled.  If enabled, authenticated, and
has not done 2fa, redirect to the 2fa screen, do not allow them to bypass it
 */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /**
         * check if authenticated (spring?).  if so check if 2fa enabled, if so, did 2fa occur?
         * if not redirct to 2fa page.  verify interaction (or if needed) if the AUthHandler is
         * needed.
         *
         * if the user is authenticated, look for a session var that they have 2fad.  if not reidrect
         * to it.
         */


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

//        String twoFactorAutnEnabledStr = (String) session.getAttribute(TwoFactorAuthSessionKeys.TWO_FACTOR_AUTH_ENABLED.toString());
        Object twoFactorAuthEnabledObj = session.getAttribute(TwoFactorAuthSessionKeys.TWO_FACTOR_AUTH_ENABLED.toString());
        boolean twoFactorAutnEnabled = convertToBoolean(twoFactorAuthEnabledObj);

//        String twoFactorAutnSuccessfulStr = (String) session.getAttribute(TwoFactorAuthSessionKeys.TWO_FACTOR_AUTH_SUCCESSFUL.toString());
        Object twoFactorAutnSuccessfulObj = session.getAttribute(TwoFactorAuthSessionKeys.TWO_FACTOR_AUTH_SUCCESSFUL.toString());
        boolean twoFactorAutnSuccessful = convertToBoolean(twoFactorAutnSuccessfulObj);
        String requestedUrl = httpRequest.getRequestURI();
        if(twoFactorAutnEnabled && !twoFactorAutnSuccessful
                && doesUrlRequire2faRedirect(requestedUrl)) {
            //redirect to 2fa page
            ((HttpServletResponse)response).sendRedirect("/twofactorauth");
            return;
//            ((HttpServletResponse)response).("/twofactorauth");
        }

        chain.doFilter(request, response);

    }

    /*
    rename this method, allow urls that request css/js stuff, ie so the 2fa page can get
    styled.  Currently the style/jquery/stfuff is blocked

    Ignore the 2fa url as that causes a infinite cycle, and avoid the css
    and js dirs so styles get read for the 2fa page
     */
    private boolean doesUrlRequire2faRedirect(String url) {
        boolean retval = true;
        if(url != null
                && (StringUtils.startsWithIgnoreCase(url, "/twofactorauth")
                || StringUtils.startsWithIgnoreCase(url, "/bootstrap")
                || StringUtils.startsWithIgnoreCase(url, "/js"))) {
            retval = false;
        }
        return retval;
    }

    private boolean convertToBoolean(Object objVal) {
        if (objVal == null) {
            return false;
        }
        Boolean boolval = (Boolean)objVal;
        return boolval;
    }

    @Override
    public void destroy() {

    }

}
