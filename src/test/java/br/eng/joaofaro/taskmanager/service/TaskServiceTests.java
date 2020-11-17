package br.eng.joaofaro.taskmanager.service;

import br.eng.joaofaro.taskmanager.beans.AccountUserBean;
import br.eng.joaofaro.taskmanager.beans.TaskBean;
import br.eng.joaofaro.taskmanager.entity.AccountUser;
import br.eng.joaofaro.taskmanager.entity.Task;
import br.eng.joaofaro.taskmanager.enums.TaskStatus;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.repository.service.TaskService;
import br.eng.joaofaro.taskmanager.repository.service.UserAccountService;
import br.eng.joaofaro.taskmanager.services.TaskManagerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author João Faro    contato@joaofaro.eng.br on 16/11/20
 * @version 1.0.0
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class TaskServiceTests {

    @InjectMocks
    private TaskManagerService taskManagerService;
    @Mock
    private UserAccountService userAccountService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private TaskService taskService;

    private AccountUserBean accountUserBean;
    private TaskBean taskBean;
    private TaskBean taskBeanWithId;
    private Task task;
    private AccountUser accountUser;

    @Before
    public void init() throws TaskManagerException {
        buildAccountUserBean();
        buildTaskBean();
        buildTask();
        buildAccountUser();
        buildTaskWithId();
        when(userAccountService.getBy("user")).thenReturn(accountUser);
        doNothing().when(taskService).save(isA(Task.class));
        when(modelMapper.map(taskBean, Task.class)).thenReturn(task);
        when(modelMapper.map(task, TaskBean.class)).thenReturn(taskBean);
    }

    @Test
    public void whenReceiveANewTask_thenReturnTaskBeanSuccessfulyTest() throws TaskManagerException {
        when(taskManagerService.createNew(taskBean, accountUserBean)).thenReturn(taskBeanWithId);
        Assert.assertEquals(1, (long)taskBeanWithId.getId());
    }

    private void buildAccountUserBean() {
        accountUserBean = new AccountUserBean();
        accountUserBean.setUsername("user");
    }

    private void buildAccountUser() {
        accountUser = new AccountUser();
        accountUserBean.setUsername("user");
    }

    private void buildTaskBean() {
        taskBean = new TaskBean();
        taskBean.setDateInsert(LocalDateTime.now());
        taskBean.setDateUpdate(LocalDateTime.now());
        taskBean.setDescription("Task description");
        taskBean.setShortDescription("task");
        taskBean.setStatus(TaskStatus.PENDING);
        taskBean.setUser(accountUserBean);
    }

    private void buildTaskWithId() {
        taskBeanWithId = new TaskBean();
        taskBeanWithId.setId(1L);
        taskBeanWithId.setDateInsert(LocalDateTime.now());
        taskBeanWithId.setDateUpdate(LocalDateTime.now());
        taskBeanWithId.setDescription("Task description");
        taskBeanWithId.setShortDescription("task");
        taskBeanWithId.setStatus(TaskStatus.PENDING);
        taskBeanWithId.setUser(accountUserBean);
    }

    private void buildTask() {
        task = new Task();
        task.setDateInsert(LocalDateTime.now());
        task.setDateUpdate(LocalDateTime.now());
        task.setDescription("Task description");
        task.setShortDescription("task");
        task.setStatus(TaskStatus.PENDING);
        task.setUser(accountUser);
    }
}
