package br.eng.joaofaro.taskmanager.resources;

import br.eng.joaofaro.taskmanager.beans.AccountUserBean;
import br.eng.joaofaro.taskmanager.beans.TaskBean;
import br.eng.joaofaro.taskmanager.dto.ResponseDto;
import br.eng.joaofaro.taskmanager.dto.TaskDto;
import br.eng.joaofaro.taskmanager.enums.TaskStatus;
import br.eng.joaofaro.taskmanager.exception.StatusNotFoundException;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.exception.TaskNotFoundException;
import br.eng.joaofaro.taskmanager.services.TaskManagerService;
import br.eng.joaofaro.taskmanager.services.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author João Faro    contato@joaofaro.eng.br on 13/11/20
 * @version 1.0.0
 */
@RestController
@RequestMapping("/task-manager")
@Slf4j
public class TaskManagerImpl implements TaskManager{

    private final ModelMapper mapper;
    private final UserInfoService userInfoService;
    private final TaskManagerService taskManagerService;

    public TaskManagerImpl(ModelMapper mapper, UserInfoService userInfoService, TaskManagerService taskManagerService) {
        this.mapper = mapper;
        this.userInfoService = userInfoService;
        this.taskManagerService = taskManagerService;
    }

    @Override
    public ResponseEntity<?> healthCheck() {
        log.debug("Calling health check");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResponseDto> create(@RequestBody TaskDto task, UriComponentsBuilder uriBuilder) throws TaskManagerException {
        log.info("Receiving Task: {}", task);
        log.info("Getting user details");
        AccountUserBean userInfo = userInfoService.getUserInfo();
        log.info("User: {}", userInfo);

        log.info("Mapping task to Bean");
        TaskBean taskBean = mapper.map(task, TaskBean.class);
        log.info("Task: {}", taskBean);
        TaskBean taskNew = taskManagerService.createNew(taskBean, userInfo);
        ResponseDto responseDto = buildResponse(taskNew, userInfo);
        URI uri = uriBuilder.path("/task-manager/tasks/{id}")
                .buildAndExpand(responseDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    @Override
    public ResponseEntity<List<ResponseDto>> list(String status) throws TaskManagerException, TaskNotFoundException, StatusNotFoundException {
        AccountUserBean userInfo = userInfoService.getUserInfo();
        List<TaskBean> taskBeanList;
        if (!StringUtils.isBlank(status)) {
            if (!checkStatus(status)) {
                throw new StatusNotFoundException("Status type not found");
            }
            log.info("Listing all tasks for User by status: {}, {}", userInfo, status);
             taskBeanList = taskManagerService.listAllBy(TaskStatus.getEnumByCode(StringUtils.lowerCase(status)), userInfo);
        }else {
            taskBeanList = taskManagerService.listAllBy(null, userInfo);
        }
        List<ResponseDto> list = buildResponseList(taskBeanList);

        return ResponseEntity.ok(list);
    }

    private List<ResponseDto> buildResponseList(List<TaskBean> taskBeanList) {
        log.info("Building response with list of tasks");
        ArrayList<ResponseDto> dtos = new ArrayList<>();
        for (TaskBean taskBean : taskBeanList) {
            ResponseDto dto = mapper.map(taskBean, ResponseDto.class);
            dtos.add(dto);
        }
        return dtos;
    }

    private boolean checkStatus(String status) {
        log.info("Checking status type: {}", status);
        TaskStatus taskStatus = TaskStatus.getEnumByCode(StringUtils.lowerCase(status));
        return taskStatus != null;
    }

    private ResponseDto buildResponse(TaskBean taskNew, AccountUserBean userInfo) {
        log.info("Building response with task: {}", taskNew);
        ResponseDto dto = mapper.map(taskNew, ResponseDto.class);
        log.info("returnig Response: {}", dto);
        return dto;
    }
}
