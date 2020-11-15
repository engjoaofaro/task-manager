package br.eng.joaofaro.taskmanager.dto;

import java.util.StringJoiner;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public class ErrorDto {

    private final String code;
    private final String description;

    public ErrorDto(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ErrorDto.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
