package br.eng.joaofaro.taskmanager.dto;

import java.util.StringJoiner;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public class TaskManagerFormErrorDto {

    private int status;
    private String field;
    private String msg;

    public TaskManagerFormErrorDto(int status, String field, String msg) {
        this.status = status;
        this.field = field;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TaskManagerFormErrorDto.class.getSimpleName() + "[", "]")
                .add("status=" + status)
                .add("field='" + field + "'")
                .add("msg='" + msg + "'")
                .toString();
    }
}
