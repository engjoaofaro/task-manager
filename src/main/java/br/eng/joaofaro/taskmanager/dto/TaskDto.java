package br.eng.joaofaro.taskmanager.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public class TaskDto implements Serializable {

    @NotBlank
    private String shortDescription;
    @NotBlank
    private String description;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskDto.class.getSimpleName() + "[", "]")
                .add("shortDescription='" + shortDescription + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
