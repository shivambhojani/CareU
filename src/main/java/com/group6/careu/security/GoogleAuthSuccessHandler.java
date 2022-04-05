package com.group6.careu.security;

import com.group6.careu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GoogleAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
        String redirectURL = request.getContextPath();
        if ((userService.getUserByEmail(oauthUser.getEmail())).getRole().equalsIgnoreCase("doctor")) {
            redirectURL = "/doctor";
        } else if((userService.getUserByEmail(oauthUser.getEmail())).getRole().equalsIgnoreCase("admin")) {
            redirectURL = "/admin";
        } else{
            redirectURL = "/patienthomepage";
        }
        response.sendRedirect(redirectURL);
    }
}
