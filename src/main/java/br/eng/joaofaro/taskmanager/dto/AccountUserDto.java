package br.eng.joaofaro.taskmanager.dto;

import java.util.StringJoiner;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public class AccountUserDto {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccountUserDto.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .toString();
    }
}
