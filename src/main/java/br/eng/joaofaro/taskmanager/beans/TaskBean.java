package br.eng.joaofaro.taskmanager.beans;

import br.eng.joaofaro.taskmanager.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public class TaskBean {

    private Long id;
    private LocalDateTime dateInsert;
    private String description;
    private AccountUserBean user;
    private String shortDescription;
    private LocalDateTime dateUpdate;
    private TaskStatus currentStatus = TaskStatus.PENDING;

    public AccountUserBean getUser() {
        return user;
    }

    public void setUser(AccountUserBean user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(LocalDateTime dateInsert) {
        this.dateInsert = dateInsert;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public TaskStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(TaskStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskBean.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("dateInsert=" + dateInsert)
                .add("description='" + description + "'")
                .add("user=" + user)
                .add("shortDescription='" + shortDescription + "'")
                .add("dateUpdate=" + dateUpdate)
                .add("currentStatus=" + currentStatus)
                .toString();
    }
}
