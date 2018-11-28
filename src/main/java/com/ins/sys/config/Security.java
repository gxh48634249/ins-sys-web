package com.ins.sys.config;

import com.ins.sys.OptionFilter;
import com.ins.sys.RoleFilter;
import com.ins.sys.user.domain.SysUserInfoEntity;
import com.ins.sys.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

    @Value("${permit.path}")
    private String permitPath;

    public static Logger logger = LoggerFactory.getLogger(Security.class);

    @Bean
    UserService userService(){
        return new UserService();
    };

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(userService(),passwordEncoder()));
//        auth.userDetailsService(userService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/img/**","/js/**","/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().and()
            .authorizeRequests()
//            .antMatchers(permitPath).permitAll()
                .anyRequest().authenticated()
            .and().formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler())
                .failureHandler(failureHandler())
            .and().logout().permitAll().invalidateHttpSession(true)
            .deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler())
            .and().rememberMe().tokenValiditySeconds(60*60*24*7)
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
//                .and()
//                .addFilter(new JWTLoginFilter(authenticationManager()))
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
//            .and().sessionManagement().maximumSessions(20).expiredUrl("/login.ftl");
        http.addFilterAfter(new RoleFilter(),JWTAuthenticationFilter.class);
        http.addFilterBefore(new OptionFilter(),JWTAuthenticationFilter.class);
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler(){
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
            //                SysUserInfoEntity sysUserInfoEntity = (SysUserInfoEntity) authentication.getPrincipal();
                logger.info("USER："+authentication.getPrincipal()+" LOGIN SUCCESS!");


                super.onAuthenticationSuccess(request,response,authentication);
            }
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                try {
                    SysUserInfoEntity sysUserInfoEntity = (SysUserInfoEntity)authentication.getPrincipal();
                    logger.info("USER："+sysUserInfoEntity.getUserAccount()+" LOGOUT SUCCESS!");
                }catch (Exception e){
                    logger.error("LOGOUT EXCEPTION:"+e.getMessage());
                }
            }
        };
    }
    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return  new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                logger.error("USER LOGIN ERROR!"+e.getMessage());
            }
        };
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://192.168.0.106","http://192.168.28.128"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTION"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
