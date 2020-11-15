package br.eng.joaofaro.taskmanager.repository.service;

import br.eng.joaofaro.taskmanager.entity.Task;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
@Service
@Slf4j
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void save(Task task) throws TaskManagerException {
      log.info("Trying to save task into database");
      try {
          repository.save(task);
      }catch (Exception e) {
          log.error("Error when trying to save task into database: {}", e.getMessage());
          throw new TaskManagerException("Error when trying to save task into database: " + e.getMessage());
      }
    }
}
