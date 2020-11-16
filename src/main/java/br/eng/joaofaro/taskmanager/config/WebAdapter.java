package br.eng.joaofaro.taskmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
@Configuration
@Order(2)
public class WebAdapter extends WebSecurityConfigurerAdapter {

    public void configure(WebSecurity http) throws Exception {
        http.ignoring()
                .antMatchers("/task-manager/healthcheck", "/h2-console/**", "/actuator/**", "/metrics/**");
    }
}
