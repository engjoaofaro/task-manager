package br.eng.joaofaro.taskmanager.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * <p>Configuration class</p>
 * <p>Here we have all configurations, beans and some security annotations for project</p>
 *
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 *
 * @see KeycloakWebSecurityConfigurerAdapter
 * @see ModelMapper
 *
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Order(1)
public class TaskManagerConfig extends KeycloakWebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
        http.csrf().disable();
    }

    /**
     * <p>Integration method responsible for authentication from <code>keycloak</code></p>
     * @see AuthenticationManagerBuilder
     * @see KeycloakAuthenticationProvider
     *
     * @param auth authentication manager builder from Spring framework
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    /**
     * <p>Method responsible to register a strategy to session management</p>
     *
     * @see SessionAuthenticationStrategy
     * @return RegisterSessionAuthenticationStrategy
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    /**
     * <p>Method responsible get resover for keycloak integratio</p>
     *
     * @see KeycloakConfigResolver
     * @return KeycloakSpringBootConfigResolver
     */
    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    /**
     * <p>Bean of Model Mapper to inject in project</p>
     *
     * @see ModelMapper
     * @return new ModelMapper()
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
