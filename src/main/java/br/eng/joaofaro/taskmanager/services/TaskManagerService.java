package br.eng.joaofaro.taskmanager.services;

import br.eng.joaofaro.taskmanager.beans.AccountUserBean;
import br.eng.joaofaro.taskmanager.beans.TaskBean;
import br.eng.joaofaro.taskmanager.entity.AccountUser;
import br.eng.joaofaro.taskmanager.entity.Task;
import br.eng.joaofaro.taskmanager.enums.TaskStatus;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.exception.TaskNotFoundException;
import br.eng.joaofaro.taskmanager.repository.service.TaskService;
import br.eng.joaofaro.taskmanager.repository.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
            AccountUser accountUser = userAccountService.getBy(user.getUsername());
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

    public List<TaskBean> listAllBy(TaskStatus status, AccountUserBean user) throws TaskNotFoundException, TaskManagerException {
        List<Task> tasks = new ArrayList<>();
        try {
            AccountUser accountUser = userAccountService.getBy(user.getUsername());
            if (status != null) {
                for (GrantedAuthority role : user.getRoles()) {
                    if (StringUtils.contains(role.getAuthority(), "super_user")) {
                        tasks = taskService.listAll(status);
                    }else {
                        tasks = taskService.listBy(status, accountUser);
                    }
                }
            }else {
                for (GrantedAuthority role : user.getRoles()) {
                    if (StringUtils.contains(role.getAuthority(), "super_user")) {
                        tasks = taskService.listAll(null);
                    }else {
                        tasks = taskService.listAllBy(accountUser);
                    }
                }
            }
        } catch (Exception e) {
            if (e instanceof TaskNotFoundException) {
                log.warn("Tasks not found in database");
                throw new TaskNotFoundException("Tasks not found in database: "+ e.getMessage());
            }else {
                log.error("Error when trying to find task in database: {}", e.getMessage());
                throw new TaskManagerException("Error when trying to find task in database: "+ e.getMessage());
            }
        }
        List<TaskBean> taskBeans = new ArrayList<>();
        for (Task task : tasks) {
            TaskBean taskBean = mapper.map(task, TaskBean.class);
            taskBeans.add(taskBean);
        }
        return taskBeans;
    }

    private Task buildingTask(TaskBean taskBean, AccountUser accountUser) {
        Task task = mapper.map(taskBean, Task.class);
        task.setDateInsert(LocalDateTime.now());
        task.setDateUpdate(LocalDateTime.now());
        task.setUser(accountUser);
        return task;
    }
}
