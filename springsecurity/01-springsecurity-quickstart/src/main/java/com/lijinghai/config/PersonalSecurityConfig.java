package com.lijinghai.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 方法三:自定义实现类的方法对user和password进行设置
 * 类PersonalSecurityConfig
 * 类MyUserDetailService
 */

@Configuration
@Slf4j
@EnableGlobalMethodSecurity(securedEnabled = true)  //开启注解
public class PersonalSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 定义403无权限页面
        http.exceptionHandling().accessDeniedPage("/error.html");

        // 注销账号
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();


        http.rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)
                .userDetailsService(userDetailsService);

        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/success.html").permitAll()
                .and().authorizeRequests()
                .antMatchers("/", "/user/login", "/test/hello").permitAll()
                //拥有admin权限才能访问/test/index
//                    .antMatchers("/test/index").hasAuthority("admin")
                .antMatchers("/test/index").hasAnyAuthority("admin", "manager")
                //
//                    .antMatchers("/test/index").hasRole("sale")
//                .antMatchers("/test/index").hasAnyRole("sale","manager")
                .anyRequest().authenticated()
                .and().csrf().disable();

        //.and().rememberMe().tokenRepository(persistentTokenRepository())
        //                .tokenValiditySeconds(60)
        //                .userDetailsService(userDetailsService)


    }
}



