package br.com.yfsmsystem.admin.security;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AdminServerProperties adminServer;

    public WebSecurityConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.getContextPath() + "/applications");

        http.authorizeRequests(
                (authorizeRequests) -> authorizeRequests.antMatchers(this.adminServer.path("/assets/**")).permitAll()
                        .antMatchers(this.adminServer.getContextPath() + "/login").permitAll()
                        .requestMatchers(EndpointRequest.to("health")).permitAll()
                        .anyRequest().authenticated()
        ).formLogin(
                (formLogin) -> formLogin.loginPage(this.adminServer.path("/login"))
                        .successHandler(successHandler)
                        .defaultSuccessUrl(this.adminServer.getContextPath() + "/applications", true)
                        .and())
                .logout(
                        (logout) -> logout.logoutUrl(this.adminServer.getContextPath() + "/logout"))
                .httpBasic(Customizer.withDefaults())
                .csrf().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;

    }
}