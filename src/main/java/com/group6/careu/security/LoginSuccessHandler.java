package com.group6.careu.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        CareuUserDetails userDetails = (CareuUserDetails) authentication.getPrincipal();
        String redirectURL = request.getContextPath();
       if (userDetails.getRole().equalsIgnoreCase("doctor")) {
            redirectURL = "/doctor";
        } else if(userDetails.getRole().equalsIgnoreCase("admin")) {
            redirectURL = "/admin";
        } else{
            redirectURL = "/patient";
        }
        response.sendRedirect(redirectURL);

    }
}
