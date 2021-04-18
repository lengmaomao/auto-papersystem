package xyz.lengmaomao.autopapersystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.lengmaomao.autopapersystem.handle.LoginSuccessHandle;

import javax.annotation.Resource;

@Configuration
public class SecurityConfigNew extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailService;

    @Bean
    PasswordEncoder password(){return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(password());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/index").successHandler(new LoginSuccessHandle())
            .and().authorizeRequests()
                .antMatchers("/subject/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated() //任何请求都必须经过身份验证
            .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/logout/success")
                .deleteCookies("JSESSIONID")
            .and().csrf()
                .disable();
    }
}
