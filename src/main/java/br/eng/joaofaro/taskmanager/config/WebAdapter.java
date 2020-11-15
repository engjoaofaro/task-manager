package br.eng.joaofaro.taskmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
@Configuration
@Order(1)
public class WebAdapter extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/task-manager/healthcheck").authorizeRequests().anyRequest().permitAll();
    }
}
