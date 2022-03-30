package com.group6.careu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CareuUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/","/index.html","/login","/register/users","/oauth/**","static/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .successHandler(loginSuccessHandler)
                .permitAll().and()
                .logout().permitAll()
                .and()
                .rememberMe()
                .key("ABBfwowrupncmoh_496241767433")
                .tokenValiditySeconds(7 * 24 * 60 * 60);
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/");


        http.authorizeRequests().antMatchers("/","/index.html","/login","/register/users","/oauth/**","static/**")
                .permitAll().and().oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oauthUserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {

                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

                        CareuUserDetails userDetails = (CareuUserDetails) userDetailsService().loadUserByUsername(oauthUser.getEmail());
                        String redirectURL = request.getContextPath();
                        if (userDetails.getRole().equalsIgnoreCase("doctor")) {
                            redirectURL = "/doctor";
                        } else if(userDetails.getRole().equalsIgnoreCase("admin")) {
                            redirectURL = "/admin";
                        } else{
                            redirectURL = "/patienthomepage";
                        }
                        response.sendRedirect(redirectURL);
                    }
                })
                .permitAll();
    }

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }


}