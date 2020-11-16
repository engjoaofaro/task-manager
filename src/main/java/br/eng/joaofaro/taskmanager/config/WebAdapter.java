package br.eng.joaofaro.taskmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>Second Configuration class for web <i>endpoints</i> without security token</p>
 *
 * @see WebSecurityConfigurerAdapter
 * @see WebSecurity
 *
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
@Configuration
@Order(2)
public class WebAdapter extends WebSecurityConfigurerAdapter {

    /**
     * <p>Methos responsible to config endpoints to handle web resources</p>
     * @param http WebSecurity class
     */
    public void configure(WebSecurity http) {
        http.ignoring()
                .antMatchers("/task-manager/healthcheck", "/h2-console/**", "/actuator/**", "/metrics/**");
    }
}
