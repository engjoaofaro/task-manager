package br.eng.joaofaro.taskmanager.exception;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public class TaskAlreadyCompletedStatusException extends Exception {

    public TaskAlreadyCompletedStatusException() {
        super();
    }

    public TaskAlreadyCompletedStatusException(String message) {
        super(message);
    }

    public TaskAlreadyCompletedStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskAlreadyCompletedStatusException(Throwable cause) {
        super(cause);
    }

    protected TaskAlreadyCompletedStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
