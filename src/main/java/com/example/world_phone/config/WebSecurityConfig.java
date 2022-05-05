package com.example.world_phone.config;

import com.example.world_phone.entity.StaffEntity;
import com.example.world_phone.service.impl.StaffDetailsServiceImpl;
import com.example.world_phone.service.impl.StaffServiceImpl;
import com.example.world_phone.until.CookieUtil;
import com.example.world_phone.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final CookieUtil cookieUtil;

    @Autowired
    private StaffDetailsServiceImpl staffDetailsService;
    private final StaffServiceImpl staffService;

    private final SessionUtil sessionUtil;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(staffDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/staff").hasAnyAuthority("ROLE_ADMIN")
                .and().exceptionHandling().accessDeniedPage("/");
        http.formLogin()
                .loginProcessingUrl("/login-check")
                .loginPage("/login")
                .defaultSuccessUrl("/staff")
                .failureUrl("/login?status=login_false")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    String username = userDetails.getUsername();
                    log.info("Login to " + username + " account successfully!");
                    System.out.println(request.getContextPath());
                    response.sendRedirect("/staff");

                    System.out.println(username + " config");
                    cookieUtil.add("username", username, 168); //7 days
                    sessionUtil.addObject("username", username);
                })
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?status=logout");

        http.authorizeRequests().and() //
                .rememberMe().rememberMeParameter("remember")
                .tokenValiditySeconds(24 * 60 * 60); // 24h
    }
}
