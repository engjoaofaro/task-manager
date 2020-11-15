package br.eng.joaofaro.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public class ResponseDto {

    @JsonProperty(value = "resourceId")
    private String id;
    private String createdUser;
    private LocalDateTime dateInsert;
    private LocalDateTime dateUpdate;
    private String description;
    private String shortDescription;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public LocalDateTime getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(LocalDateTime dateInsert) {
        this.dateInsert = dateInsert;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ResponseDto.class.getSimpleName() + "[", "]")
                .add("resourceId='" + id + "'")
                .add("createdUser='" + createdUser + "'")
                .add("dateInsert=" + dateInsert)
                .add("dateUpdate=" + dateUpdate)
                .add("description='" + description + "'")
                .add("shortDescription='" + shortDescription + "'")
                .add("status='" + status + "'")
                .toString();
    }
}
