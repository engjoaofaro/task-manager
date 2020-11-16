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
 * <p>Class responsible to process all <code>task</code> services</p>
 *
 * @see TaskRepository
 * @see TaskManagerException
 * @see TaskNotFoundException
 *
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

    /**
     * <p>Method responsible to save a <code>task</code></p>
     * @param task - task object
     * @throws TaskManagerException - throws a exception to handle result
     */
    public void save(Task task) throws TaskManagerException {
      log.info("Trying to save task into database");
      try {
          repository.save(task);
      }catch (Exception e) {
          log.error("Error when trying to save task into database: {}", e.getMessage());
          throw new TaskManagerException("Error when trying to save task into database: " + e.getMessage());
      }
    }

    /**
     * <p>Method responsible to delete a <code>task</code></p>
     * @param id - Task id
     * @param user - Task user
     * @throws TaskManagerException - throws a exception to handle result
     */
    public void deleteBy(Long id, AccountUser user) throws TaskManagerException {
        log.info("Trying to delete task from database");
        try {
            repository.deleteByIdAndUser(id, user);
            log.info("Task deleted successfuly from database");
        }catch (Exception e) {
            log.error("Error when trying to delete task from database: {}", e.getMessage());
            throw new TaskManagerException("Error when trying to delete task from database: " + e.getMessage());
        }
    }

    /**
     * <p>Method responsible to update a <code>task</code></p>
     * @param task - task object
     * @throws TaskManagerException - throws a exception to handle result
     */
    public void saveAndFlush(Task task) throws TaskManagerException {
        log.info("Trying to save task into database");
        try {
            repository.saveAndFlush(task);
        }catch (Exception e) {
            log.error("Error when trying to save task into database: {}", e.getMessage());
            throw new TaskManagerException("Error when trying to save task into database: " + e.getMessage());
        }
    }

    /**
     * <p>Method responsible to list <code>tasks</code> by status and user</p>
     * @param status - task status
     * @param user - task user
     * @return - List of tasks
     * @throws TaskManagerException - throws a exception to handle result
     * @throws TaskNotFoundException - throws a exception to handle result
     */
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

    /**
     * <p>Method responsible to list <code>tasks</code></p>
     * <p>Could be complemented with status</p>
     * @param status - Task status
     * @return - List of tasks
     * @throws TaskManagerException - throws a exception to handle result
     * @throws TaskNotFoundException - throws a exception to handle result
     */
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

    /**
     * <p>Method responsible to list <code>tasks</code> by User</p>
     * @param user - task user
     * @return - List of tasks
     * @throws TaskManagerException - throws a exception to handle result
     * @throws TaskNotFoundException - throws a exception to handle result
     */
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

    /**
     * <p>Method responsible to find <code>tasks</code> by id</p>
     * @param id - task id
     * @return - Task object
     * @throws TaskManagerException - throws a exception to handle result
     * @throws TaskNotFoundException - throws a exception to handle result
     */
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

    /**
     * <p>Method responsible to find <code>tasks</code> by id and user</p>
     * @param id - task id
     * @param user - task user
     * @return - Task object
     * @throws TaskManagerException throws a exception to handle result
     * @throws TaskNotFoundException throws a exception to handle result
     */
    public Task findByIdAndUser(Long id, AccountUser user) throws TaskManagerException, TaskNotFoundException {
        log.info("Searching task");
        try {
            Optional<Task> task = repository.findByIdAndUser(id, user);
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
