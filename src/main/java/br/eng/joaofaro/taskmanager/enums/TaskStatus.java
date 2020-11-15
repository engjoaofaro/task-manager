package br.eng.joaofaro.taskmanager.enums;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
public enum TaskStatus {
    PENDING("pending"),
    COMPLETED("completed");

    private final String code;

    TaskStatus(String code) {
        this.code = code;
    }

    public static TaskStatus getEnumByCode(String code) {
        for (TaskStatus value : TaskStatus.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
