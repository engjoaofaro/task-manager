package br.eng.joaofaro.taskmanager.beans;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
public class AccountUserBean {

    private String username;
    private Collection<? extends GrantedAuthority> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccountUserBean.class.getSimpleName() + "[", "]")
                .add("name='" + username + "'")
                .add("role='" + roles + "'")
                .toString();
    }
}
