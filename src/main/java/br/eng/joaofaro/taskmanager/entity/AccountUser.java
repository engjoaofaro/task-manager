package br.eng.joaofaro.taskmanager.entity;

import javax.persistence.*;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
@Entity
@Table(name = "USER")
public class AccountUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
}
