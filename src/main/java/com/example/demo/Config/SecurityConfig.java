package com.example.demo.Config;


import com.example.demo.Config.auth.exceptionHandler.CustomAccessDeniedHandler;
import com.example.demo.Config.auth.exceptionHandler.CustomAuthenticationEntryPoint;
import com.example.demo.Config.auth.loginHandler.CustomAuthenticationFailureHandler;
import com.example.demo.Config.auth.loginHandler.CustomLoginSuccessHandler;
import com.example.demo.Config.auth.logoutHandler.CustomLogoutHandler;
import com.example.demo.Config.auth.logoutHandler.CustomLogoutSuccessHandler;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private HikariDataSource dataSource;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {

        //CSRF 비활성화
        http.csrf(
                (config)->{ config.disable(); }
        );

        //요청 URL별 접근 제한
        http.authorizeHttpRequests(
                authorize->{
                    authorize.requestMatchers("/","/main","/ownerLogin","/login").permitAll();
                    authorize.requestMatchers("/join").hasRole("ANONYMOUS");
                    authorize.requestMatchers("/css/**","/js/**","/images/**","/file/HamoRestX/**").permitAll();
                    authorize.requestMatchers("/","/main").hasRole("USER"); // ROLE_USER
                    authorize.requestMatchers("/ownerMain").hasRole("MEMBER"); // ROLE_MEMBER
                    authorize.requestMatchers("/admin/adminMain").hasRole("ADMIN"); // ROLE_ADMIN
                    authorize.anyRequest().authenticated();
                }
        );
        //로그인
        http.formLogin(login->{
            login.permitAll();
            login.loginPage("/login");
            login.successHandler(new CustomLoginSuccessHandler());
            login.failureHandler(new CustomAuthenticationFailureHandler());
        });

        //로그아웃
        http.logout(
                (logout)->{
                    logout.permitAll();
                    logout.logoutUrl("/logout");
                    logout.addLogoutHandler(new CustomLogoutHandler());
                    logout.logoutSuccessHandler(new CustomLogoutSuccessHandler());
                }
        );

        //예외처리
        http.exceptionHandling(
                ex->{
                    ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
                    ex.accessDeniedHandler(new CustomAccessDeniedHandler());
                }
        );

        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//
//        userDetailsManager.createUser(User.withUsername("user")
//                .password(passwordEncoder.encode("1234"))
//                .roles("USER")
//                .build());
//
//        userDetailsManager.createUser(User.withUsername("member")
//                .password(passwordEncoder.encode("1234"))
//                .roles("MEMBER")
//                .build());
//
//        userDetailsManager.createUser(User.withUsername("master")
//                .password(passwordEncoder.encode("1234"))
//                .roles("ADMIN")
//                .build());
//
//        return userDetailsManager;
//    }

    // BCryptPasswordEncoder Bean 등록 - 패스워드 검증에 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}