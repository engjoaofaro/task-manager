package br.eng.joaofaro.taskmanager.exception;

import br.eng.joaofaro.taskmanager.dto.ErrorDto;
import br.eng.joaofaro.taskmanager.dto.TaskManagerFormErrorDto;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>An important class that handle all <i><code>Exceptions</code></i> in application</p>
 * <p>Return a custom response</p>
 *
 * @see MessageSource
 *
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
@RestControllerAdvice
public class TaskManagerExceptionHandler {

    private final MessageSource messageSource;

    public TaskManagerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * <p>Internal server error handle</p>
     *
     * @param e - exception
     * @return Error model
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TaskManagerException.class)
    public ErrorDto taskManagerExceptionHandle(Exception e) {
        return new ErrorDto(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
    }

    /**
     * <p>Validation handle when create a new task</p>
     *
     * @return list of errors model
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<TaskManagerFormErrorDto> DtoValidationHandle(MethodArgumentNotValidException exception) {
        ArrayList<TaskManagerFormErrorDto> erros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            TaskManagerFormErrorDto errorDto = new TaskManagerFormErrorDto(HttpStatus.BAD_REQUEST.value(),
                    e.getField(), message);
            erros.add(errorDto);
        });
        return erros;
    }

    /**
     * <p>Task not found handle</p>
     *
     * @param e - exception
     * @return Error model
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskNotFoundException.class)
    public ErrorDto taskManagerNotFoundExceptionHandle(Exception e) {
        return new ErrorDto(String.valueOf(HttpStatus.NOT_FOUND.value()), e.getMessage());
    }

    /**
     * <p>Status not found handle</p>
     *
     * @param e - exception
     * @return Error model
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({StatusNotFoundException.class, TaskAlreadyCompletedStatusException.class})
    public ErrorDto StatusExceptionHandle(Exception e) {
        return new ErrorDto(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage());
    }
}
