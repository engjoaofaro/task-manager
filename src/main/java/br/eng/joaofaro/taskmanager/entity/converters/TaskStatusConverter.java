package br.eng.joaofaro.taskmanager.entity.converters;

import br.eng.joaofaro.taskmanager.enums.TaskStatus;
import br.eng.joaofaro.taskmanager.utils.EnumWithCodeConverter;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
public class TaskStatusConverter extends EnumWithCodeConverter<TaskStatus, String> {
    protected TaskStatusConverter() {
        super(TaskStatus.class);
    }
}
