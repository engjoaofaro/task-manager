package br.eng.joaofaro.taskmanager.services;

import br.eng.joaofaro.taskmanager.beans.AccountUserBean;
import br.eng.joaofaro.taskmanager.beans.TaskBean;
import br.eng.joaofaro.taskmanager.entity.AccountUser;
import br.eng.joaofaro.taskmanager.entity.Task;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.repository.service.TaskService;
import br.eng.joaofaro.taskmanager.repository.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
@Service
@Slf4j
public class TaskManagerService {

    private final UserAccountService userAccountService;
    private final ModelMapper mapper;
    private final TaskService taskService;

    public TaskManagerService(UserAccountService userAccountService, ModelMapper mapper, TaskService taskService) {
        this.userAccountService = userAccountService;
        this.mapper = mapper;
        this.taskService = taskService;
    }

    public TaskBean createNew(TaskBean taskBean, AccountUserBean user) throws TaskManagerException {
        Task task;
        try {
            log.info("checking user into database: {}", user);
            AccountUser accountUser = userAccountService.getBy(user.getName());
            log.info("Building task");
            task = buildingTask(taskBean, accountUser);
            log.info("Preparing to save task");
            taskService.save(task);
        }catch (Exception e) {
            log.error("Error ocurred when trying to proccess task: {}", e.getMessage());
            throw new TaskManagerException("Error ocurred when trying to proccess task: "+ e.getMessage());
        }
        return mapper.map(task, TaskBean.class);
    }

    private Task buildingTask(TaskBean taskBean, AccountUser accountUser) {
        Task task = mapper.map(taskBean, Task.class);
        task.setDateInsert(LocalDateTime.now());
        task.setDateUpdate(LocalDateTime.now());
        task.setUser(accountUser);
        return task;
    }
}
