package br.eng.joaofaro.taskmanager.exception;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public class TaskManagerException extends Exception{
    public TaskManagerException() {
        super();
    }

    public TaskManagerException(String message) {
        super(message);
    }

    public TaskManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskManagerException(Throwable cause) {
        super(cause);
    }

    protected TaskManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
