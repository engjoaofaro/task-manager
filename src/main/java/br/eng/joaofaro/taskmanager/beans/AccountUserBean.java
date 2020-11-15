package br.eng.joaofaro.taskmanager.beans;

import java.util.StringJoiner;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
public class AccountUserBean {

    private String name;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccountUserBean.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("role='" + role + "'")
                .toString();
    }
}
