package br.eng.joaofaro.taskmanager.beans;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
public class AccountUserBean {

    private String name;
    private Collection<? extends GrantedAuthority> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                .add("name='" + name + "'")
                .add("role='" + roles + "'")
                .toString();
    }
}
