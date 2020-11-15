package br.eng.joaofaro.taskmanager.repository.service;

import br.eng.joaofaro.taskmanager.entity.AccountUser;
import br.eng.joaofaro.taskmanager.entity.Task;
import br.eng.joaofaro.taskmanager.enums.TaskStatus;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.exception.TaskNotFoundException;
import br.eng.joaofaro.taskmanager.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void saveAndFlush(Task task) throws TaskManagerException {
        log.info("Trying to save task into database");
        try {
            repository.saveAndFlush(task);
        }catch (Exception e) {
            log.error("Error when trying to save task into database: {}", e.getMessage());
            throw new TaskManagerException("Error when trying to save task into database: " + e.getMessage());
        }
    }

    public List<Task> listBy(TaskStatus status, AccountUser user) throws TaskManagerException, TaskNotFoundException {
        log.info("Searching task by status and user");
        try {
            List<Task> tasks = repository.findAllByStatusAndUser(status, user);
            if (tasks.size() > 0) {
                log.info("Returning tasks");
                return tasks;
            }else {
                log.warn("Tasks not found for User");
                throw new TaskNotFoundException("Tasks not found in database");
            }
        }catch (Exception e) {
            if (e instanceof TaskNotFoundException) {
                log.warn("Tasks not found in database");
                throw new TaskNotFoundException("Tasks not found in database: "+ e.getMessage());
            }else {
                log.error("Error when trying to find task in database: {}", e.getMessage());
                throw new TaskManagerException("Error when trying to find task in database: "+ e.getMessage());
            }
        }
    }

    public List<Task> listAll(TaskStatus status) throws TaskManagerException, TaskNotFoundException {
        log.info("Searching task by status");
        try {
            List<Task> tasks;
            if (status != null) {
                tasks = repository.findAllByStatus(status);
            }else {
                tasks = repository.findAll();
            }
            if (tasks.size() > 0) {
                log.info("Returning tasks");
                return tasks;
            }else {
                log.warn("Tasks not found");
                throw new TaskNotFoundException("Tasks not found in database");
            }
        }catch (Exception e) {
            if (e instanceof TaskNotFoundException) {
                log.warn("Tasks not found in database");
                throw new TaskNotFoundException("Tasks not found in database: "+ e.getMessage());
            }else {
                log.error("Error when trying to find task in database: {}", e.getMessage());
                throw new TaskManagerException("Error when trying to find task in database: "+ e.getMessage());
            }
        }
    }

    public List<Task> listAllBy(AccountUser user) throws TaskManagerException, TaskNotFoundException {
        log.info("Searching task by user");
        try {
            List<Task> tasks = repository.findAllByUser(user);
            if (tasks.size() > 0) {
                log.info("Returning tasks");
                return tasks;
            }else {
                log.warn("Tasks not found");
                throw new TaskNotFoundException("Tasks not found in database");
            }
        }catch (Exception e) {
            if (e instanceof TaskNotFoundException) {
                log.warn("Tasks not found in database");
                throw new TaskNotFoundException("Tasks not found in database: "+ e.getMessage());
            }else {
                log.error("Error when trying to find task in database: {}", e.getMessage());
                throw new TaskManagerException("Error when trying to find task in database: "+ e.getMessage());
            }
        }
    }

    public Task findById(Long id) throws TaskManagerException, TaskNotFoundException {
        log.info("Searching task by id: {}", id);
        try {
            Optional<Task> task = repository.findById(id);
            if (task.isPresent()) {
                log.info("Task has been found");
                return task.get();
            }else {
                log.warn("Tasks not found");
                throw new TaskNotFoundException("Tasks not found in database");
            }
        }catch (Exception e) {
            if (e instanceof TaskNotFoundException) {
                log.warn("Tasks not found in database");
                throw new TaskNotFoundException("Tasks not found in database: "+ e.getMessage());
            }else {
                log.error("Error when trying to find task in database: {}", e.getMessage());
                throw new TaskManagerException("Error when trying to find task in database: "+ e.getMessage());
            }
        }
    }
}
